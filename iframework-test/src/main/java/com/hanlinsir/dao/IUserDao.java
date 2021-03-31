package com.hanlinsir.dao;

import com.hanlinsir.entity.User;

import java.util.List;

/**
 * User 持久层接口
 *
 * @author <a href="mailto:allen_lu_hh@163.com">lin</a>
 * @since 1.0
 */
public interface IUserDao {

    List<User> findAll();

    User findByCondition(User user);

    int insertUser(User user);

    int updateUser(User user);

    int deleteUser(User user);

}
