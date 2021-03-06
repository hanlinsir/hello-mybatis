package com.hanlinsir.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.hanlinsir.io.MyResource;
import com.hanlinsir.pojo.MyConfiguration;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.beans.PropertyVetoException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

/**
 * Xml 配置文件解析类
 *
 * @author <a href="mailto:allen_lu_hh@163.com">lin</a>
 * @since 1.0
 */
public class XmlConfigParser {

    public MyConfiguration parse(InputStream inputStream) throws DocumentException, PropertyVetoException {
        Document document = new SAXReader().read(inputStream);
        //<configuration>
        Element rootElement = document.getRootElement();
        List<Element> list = rootElement.selectNodes("//property");
        Properties properties = new Properties();
        for (Element element : list) {
            String name = element.attributeValue("name");
            String value = element.attributeValue("value");
            properties.setProperty(name,value);
        }

        // 使用 c3p0 线程池，只做演示使用
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(properties.getProperty("driverClass"));
        dataSource.setUrl(properties.getProperty("jdbcUrl"));
        dataSource.setUsername(properties.getProperty("username"));
        dataSource.setPassword(properties.getProperty("password"));

        MyConfiguration configuration = new MyConfiguration();

        configuration.setDataSource(dataSource);

        //mapper.xml解析: 拿到路径--字节输入流---dom4j进行解析
        List<Element> mapperList = rootElement.selectNodes("//mapper");

        for (Element element : mapperList) {
            String mapperPath = element.attributeValue("resource");
            InputStream resourceAsSteam = MyResource.getResourceAsStream(mapperPath);
            XMLMapperParser xmlMapperBuilder = new XMLMapperParser(configuration);
            xmlMapperBuilder.parse(resourceAsSteam);

        }
        return configuration;
    }
}
