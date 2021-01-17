package com.springboot.elementuser.dao;

import com.springboot.elementuser.entity.User;

import java.util.List;

public interface UserDao {

    List<User> queryAll();

    User queryById(Integer id);

    void saveUser(User user);

    void updateUser(User user);

    void deleteById(Integer id);

    List<User> queryByPage(Integer start, Integer pageSize);

    Long queryCount();
}
