package com.hanlinsir.config;

import com.hanlinsir.pojo.MappedStatement;
import com.hanlinsir.pojo.MyConfiguration;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * XMLMapper 配置文件解析器
 *
 * @author <a href="mailto:allen_lu_hh@163.com">lin</a>
 * @since 1.0
 */
public class XMLMapperParser {

    private MyConfiguration configuration;

    public XMLMapperParser(MyConfiguration configuration) {
        this.configuration = configuration;
    }

    public void parse(InputStream inputStream) throws DocumentException {
        Document document = new SAXReader().read(inputStream);
        Element rootElement = document.getRootElement();

        String namespace = rootElement.attributeValue("namespace");

        List<Element> list = new ArrayList<>();
        list.addAll(rootElement.selectNodes("//select"));
        list.addAll(rootElement.selectNodes("//insert"));
        list.addAll(rootElement.selectNodes("//update"));
        list.addAll(rootElement.selectNodes("//delete"));
        for (Element element : list) {
            String id = element.attributeValue("id");
            String resultType = element.attributeValue("resultType");
            String paramterType = element.attributeValue("paramterType");
            String sqlText = element.getTextTrim();
            MappedStatement mappedStatement = new MappedStatement();
            mappedStatement.setId(id);
            mappedStatement.setResultType(resultType);
            mappedStatement.setParamType(paramterType);
            mappedStatement.setSql(sqlText);
            String key = namespace+"."+id;
            configuration.getStatementMap().put(key,mappedStatement);
        }
    }
}
