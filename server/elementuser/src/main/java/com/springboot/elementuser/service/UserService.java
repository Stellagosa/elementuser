package com.springboot.elementuser.service;

import com.springboot.elementuser.entity.User;

import java.util.List;

public interface UserService {
    List<User> queryAll();

    User queryById(Integer id);

    void saveUser(User user);

    void updateUser(User user);

    void deleteById(Integer id);

    List<User> queryByPage(Integer currentPage, Integer pageSize);

    Long queryCount();
}
