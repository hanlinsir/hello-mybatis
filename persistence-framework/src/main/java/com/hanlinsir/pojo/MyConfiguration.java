package com.hanlinsir.pojo;

import lombok.Data;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * Mybatis 中需要被解析的配置信息存储类
 *
 * @author <a href="mailto:allen_lu_hh@163.com">lin</a>
 * @since 1.0
 */
@Data
public class MyConfiguration {

    private DataSource dataSource;

    /**
     * 存放 statementId: {namespace,id} 与 MappedStatement xml语句参数映射
     */
    private Map<String, MappedStatement> statementMap = new HashMap<>();

}
