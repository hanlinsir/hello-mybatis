package com.hanlinsir.sqlsession;

import com.hanlinsir.pojo.MyConfiguration;

/**
 * SqlSessionFactory 默认实现类
 *
 * @author <a href="mailto:allen_lu_hh@163.com">lin</a>
 * @since 1.0
 */
public class DefaultSqlSessionFactory implements SqlSessionFactory {

    private MyConfiguration configuration;

    public DefaultSqlSessionFactory(MyConfiguration configuration) {
        this.configuration = configuration;
    }

    @Override
    public SqlSession create() {
        return new DefaultSqlSession(configuration);
    }
}
