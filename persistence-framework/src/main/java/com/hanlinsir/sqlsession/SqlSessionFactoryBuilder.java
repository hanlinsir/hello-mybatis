package com.hanlinsir.sqlsession;

import com.hanlinsir.config.XmlConfigParser;
import com.hanlinsir.pojo.MyConfiguration;
import org.dom4j.DocumentException;

import java.beans.PropertyVetoException;
import java.io.InputStream;

/**
 * SqlSessionFactory 包装器
 *
 * @author <a href="mailto:allen_lu_hh@163.com">lin</a>
 * @since 1.0
 */
public class SqlSessionFactoryBuilder {

    public static SqlSessionFactory builder(InputStream inputStream) throws PropertyVetoException, DocumentException {
        // 1. 读取流中配置文件信息
        XmlConfigParser xmlConfigParser = new XmlConfigParser();
        MyConfiguration configuration = xmlConfigParser.parse(inputStream);

        // 2. 创建 SqlSessionFactory 对象
        return new DefaultSqlSessionFactory(configuration);
    }
}
