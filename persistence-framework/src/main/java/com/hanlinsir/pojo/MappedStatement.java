package com.hanlinsir.pojo;

import lombok.Data;

/**
 * xml 执行语句与参数映射类
 *
 * @author <a href="mailto:allen_lu_hh@163.com">lin</a>
 * @since 1.0
 */
@Data
public class MappedStatement {

    private String id;

    private String paramType;

    private String resultType;

    private String sql;

}
