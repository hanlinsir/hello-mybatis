package com.hanlinsir.sqlsession;

import java.util.List;

/**
 * sql 执行会话
 *
 * @author <a href="mailto:allen_lu_hh@163.com">lin</a>
 * @since 1.0
 */
public interface SqlSession {

    <E> List<E> findAll(String statementId, Object... params) throws Exception;

    <E> E findOne(String statementId, Object... params) throws Exception;

    int insert(String statementId, Object... params) throws Exception;

    int update(String statementId, Object... params) throws Exception;

    int delete(String statementId, Object... params) throws Exception;

    <T> T getMapper(Class<T> mapperClass);
}
