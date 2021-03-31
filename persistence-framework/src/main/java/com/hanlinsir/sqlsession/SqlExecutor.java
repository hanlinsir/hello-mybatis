package com.hanlinsir.sqlsession;

import com.hanlinsir.pojo.MappedStatement;
import com.hanlinsir.pojo.MyConfiguration;

import java.util.List;

/**
 * Sql 语句执行器
 *
 * @author <a href="mailto:allen_lu_hh@163.com">lin</a>
 * @since 1.0
 */
public interface SqlExecutor {

    <E> List<E> query(MyConfiguration configuration, MappedStatement statement, Object... params) throws Exception;

    int update(MyConfiguration configuration, MappedStatement statement, Object... params) throws Exception;
}
