package com.hanlinsir.utils;

import lombok.Data;

/**
 * 存放站位符中的变量名
 *
 * @author <a href="mailto:allen_lu_hh@163.com">lin</a>
 * @since 1.0
 */
@Data
public class ParameterMapping {

    private String content;

    public ParameterMapping(String content) {
        this.content = content;
    }
}
