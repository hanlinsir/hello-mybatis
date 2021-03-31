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

        System.out.println("----------------新增测试---------------------");
//        User user = new User();
//        user.setUsername("test insert");
//        user.setPassword("1211111");
//        user.setBirthday("2019-12-12");
//        userDao.insertUser(user);

        System.out.println("----------------修改测试---------------------");
//        User updateUser = userDao.findByCondition(user);
//        if (updateUser != null) {
//            System.out.println("单挑查询数据： " + updateUser);
//            updateUser.setUsername("test update");
//        }
//        System.out.println(userDao.updateUser(updateUser));

        System.out.println("----------------删除测试---------------------");
        User user2 = new User();
        user2.setUsername("test update");
        user2 = userDao.findByCondition(user2);
        if (user2 != null) {
            userDao.deleteUser(user2);
        }

        System.out.println("----------------查询列表测试---------------------");
//        List<User> users = userDao.findAll();
//        users.forEach(System.out::println);
    }
}
