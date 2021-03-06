package com.hanlinsir.main;

import com.hanlinsir.dao.IUserDao;
import com.hanlinsir.entity.User;
import com.hanlinsir.io.MyResource;
import com.hanlinsir.sqlsession.SqlSession;
import com.hanlinsir.sqlsession.SqlSessionFactory;
import com.hanlinsir.sqlsession.SqlSessionFactoryBuilder;
import org.dom4j.DocumentException;

import java.beans.PropertyVetoException;
import java.io.InputStream;
import java.sql.*;
import java.util.List;

/**
 * 测试入口类
 *
 * @author <a href="mailto:allen_lu_hh@163.com">lin</a>
 * @since 1.0
 */
public class IPersistenceTest {

    public static void main(String[] args) throws Exception {
        InputStream in = MyResource.getResourceAsStream("sqlMapConfig.xml");

        SqlSessionFactory builder = SqlSessionFactoryBuilder.builder(in);
        SqlSession sqlSession = builder.create();

        IUserDao userDao = sqlSession.getMapper(IUserDao.class);

        List<User> users = userDao.findAll();
        users.forEach(System.out::println);

        User user = new User();
        user.setId(1);
        user.setUsername("lucy");
        User user2 = userDao.findByCondition(user);

        System.out.println(user2);
    }
}
