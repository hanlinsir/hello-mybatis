package com.hanlinsir.sqlsession;

import com.hanlinsir.pojo.BoundSql;
import com.hanlinsir.pojo.MappedStatement;
import com.hanlinsir.pojo.MyConfiguration;
import com.hanlinsir.utils.GenericTokenParser;
import com.hanlinsir.utils.ParameterMapping;
import com.hanlinsir.utils.ParameterMappingTokenHandler;

import javax.sql.DataSource;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

/**
 * SqlExecutor 默认实现类
 *
 * @author <a href="mailto:allen_lu_hh@163.com">lin</a>
 * @since 1.0
 */
public class DefaultSqlExecutor implements SqlExecutor {

    @Override
    public <E> List<E> query(MyConfiguration configuration, MappedStatement statement, Object... params) throws Exception {
        ArrayList<Object> results = new ArrayList<>();

        PreparedStatement preparedStatement = initPreparedStatement(configuration, statement, params);
        ResultSet resultSet = preparedStatement.executeQuery();

        // 处理结果集
        String resultType = statement.getResultType();
        Class<?> resultClz = getTypeClass(resultType);
        while (resultSet.next()) {
            Object result = resultClz.newInstance();

            ResultSetMetaData metaData = resultSet.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                // 字段名
                String columnName = metaData.getColumnName(i);
                // 字段的值
                Object value = resultSet.getObject(columnName);

                //使用反射或者内省，根据数据库表和实体的对应关系，完成封装
                PropertyDescriptor propertyDescriptor = new PropertyDescriptor(columnName, resultClz);
                Method writeMethod = propertyDescriptor.getWriteMethod();
                writeMethod.invoke(result,value);
            }
            results.add(result);
        }

        return (List<E>) results;
    }

    @Override
    public int update(MyConfiguration configuration, MappedStatement statement, Object... params) throws Exception {
        PreparedStatement preparedStatement = initPreparedStatement(configuration, statement, params);
        return preparedStatement.executeUpdate();
    }

    private PreparedStatement initPreparedStatement(MyConfiguration configuration, MappedStatement statement, Object... params) throws Exception{
        /**
         * 暂时不做关闭处理
         */
        // 处理 sql
        BoundSql boundSql = getBoundSql(statement.getSql());
        DataSource dataSource = configuration.getDataSource();

        Connection connection = dataSource.getConnection();

        // 预处理
        PreparedStatement preparedStatement = connection.prepareStatement(boundSql.getSql());
        List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
        String paramType = statement.getParamType();
        for (int i = 0; i < parameterMappings.size(); i++) {
            String content = parameterMappings.get(i).getContent();
            Class<?> clz = getTypeClass(paramType);
            Field paramTypeField = clz.getDeclaredField(content);
            paramTypeField.setAccessible(true);
            Object value = paramTypeField.get(params[0]);

            preparedStatement.setObject(i+1, value);
        }
        return preparedStatement;
    }

    private Class<?> getTypeClass(String clzType) throws ClassNotFoundException {
        if (clzType != null) {
            return Class.forName(clzType);
        }
        throw new RuntimeException("clzType can`t be null.");
    }

    private BoundSql getBoundSql(String sql) {
        ParameterMappingTokenHandler handler = new ParameterMappingTokenHandler();
        GenericTokenParser genericTokenParser = new GenericTokenParser("#{", "}", handler);
        String parseSql = genericTokenParser.parse(sql);
        return new BoundSql(parseSql, handler.getParameterMappings());
    }
}
