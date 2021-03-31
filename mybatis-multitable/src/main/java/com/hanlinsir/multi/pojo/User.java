package com.hanlinsir.multi.pojo;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Table(name = "user")
public class User implements Serializable { // 开启 Mybatis 二级缓存需要实现序列化

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String username;

    private String password;

    private String birthday;

    // 由于 tk.mybatis 中没有过滤实体属性的功能，因此直接注释
    //表示用户关联的订单
    private List<Order> orderList = new ArrayList<>();

    //表示用户关联的角色
    private List<Role> roleList = new ArrayList<>();
}
