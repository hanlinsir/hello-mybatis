package com.hanlinsir.sqlsession;

import com.hanlinsir.pojo.MappedStatement;
import com.hanlinsir.pojo.MyConfiguration;

import java.lang.reflect.*;
import java.util.List;

/**
 * SqlSession 默认实现类
 *
 * @author <a href="mailto:allen_lu_hh@163.com">lin</a>
 * @since 1.0
 */
public class DefaultSqlSession implements SqlSession {

    private static final SqlExecutor executor = new DefaultSqlExecutor();

    private MyConfiguration configuration;

    public DefaultSqlSession(MyConfiguration configuration) {
        this.configuration = configuration;
    }

    @Override
    public <E> List<E> findAll(String statementId, Object... params) throws Exception {
        return executor.query(configuration, configuration.getStatementMap().get(statementId), params);
    }

    @Override
    public <E> E findOne(String statementId, Object... params) throws Exception {
        List<E> results = executor.query(configuration, configuration.getStatementMap().get(statementId), params);

        if (!results.isEmpty()) {
            return results.get(0);
        }
        return null;
    }

    @Override
    public int insert(String statementId, Object... params) throws Exception {
        return executor.update(configuration, configuration.getStatementMap().get(statementId), params);
    }

    @Override
    public int update(String statementId, Object... params) throws Exception {
        return executor.update(configuration, configuration.getStatementMap().get(statementId), params);
    }

    @Override
    public int delete(String statementId, Object... params) throws Exception {
        return executor.update(configuration, configuration.getStatementMap().get(statementId), params);
    }


    public <T> T getMapper(Class<T> mapperClass) {
        Object mapper = Proxy.newProxyInstance(DefaultSqlSession.class.getClassLoader(), new Class[]{mapperClass}, (proxy, method, args) -> {
            // 底层都还是去执行JDBC代码
            // 准备参数 1：statmentid :sql语句的唯一标识：namespace.id= 接口全限定名.方法名
            // 方法名：findAll
            String methodName = method.getName();
            String className = method.getDeclaringClass().getName();

            String statementId = className + "." + methodName;

            // 准备参数2：params:args
            // 获取被调用方法的返回值类型
            Type genericReturnType = method.getGenericReturnType();

            MappedStatement ms = configuration.getStatementMap().get(statementId);
            String sql = ms.getSql();
            if (sql != null ) {
                if (sql.toLowerCase().startsWith("insert")) {
                    return insert(statementId, args);
                }
                if (sql.toLowerCase().startsWith("update")) {
                    return update(statementId, args);
                }
                if (sql.toLowerCase().startsWith("delete")) {
                    return delete(statementId, args);
                }
            }
            // 判断是否进行了 泛型类型参数化
            if (genericReturnType instanceof ParameterizedType) {
                return findAll(statementId, args);
            }

            return findOne(statementId, args);
        });

        return (T) mapper;
    }

}
