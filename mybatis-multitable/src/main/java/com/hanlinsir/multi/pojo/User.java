package com.hanlinsir.multi.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class User implements Serializable { // 开启 Mybatis 二级缓存需要实现序列化

    private Integer id;

    private String username;

    private String password;

    private String birthday;

    //表示用户关联的订单
    private List<Order> orderList = new ArrayList<>();

    //表示用户关联的角色
    private List<Role> roleList = new ArrayList<>();
}
