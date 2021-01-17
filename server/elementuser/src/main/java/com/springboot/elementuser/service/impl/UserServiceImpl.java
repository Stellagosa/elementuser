package com.springboot.elementuser.service.impl;

import com.springboot.elementuser.dao.UserDao;
import com.springboot.elementuser.entity.User;
import com.springboot.elementuser.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    @Transactional(readOnly = true)
    public List<User> queryAll() {
        return userDao.queryAll();
    }

    @Override
    @Transactional(readOnly = true)
    public User queryById(Integer id) {
        return userDao.queryById(id);
    }

    @Override
    public void saveUser(User user) {
        userDao.saveUser(user);
    }

    @Override
    public void updateUser(User user) {
        userDao.updateUser(user);
    }

    @Override
    public void deleteById(Integer id) {
        userDao.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> queryByPage(Integer currentPage, Integer pageSize) {
        return userDao.queryByPage((currentPage - 1) * pageSize, pageSize);
    }

    @Override
    @Transactional(readOnly = true)
    public Long queryCount() {
        return userDao.queryCount();
    }

}
