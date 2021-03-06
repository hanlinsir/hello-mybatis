package com.hanlinsir.pojo;

import com.hanlinsir.utils.ParameterMapping;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * sql 与 #{} 中参数的包装类
 *
 * @author <a href="mailto:allen_lu_hh@163.com">lin</a>
 * @since 1.0
 */
@Data
@AllArgsConstructor
public class BoundSql {

    private String sql;

    private List<ParameterMapping> parameterMappings;

}
