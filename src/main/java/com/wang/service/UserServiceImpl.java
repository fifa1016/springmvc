package com.wang.service;

import com.wang.dao.IUserDao;
import com.wang.pojo.User;

/**
 * Created by wang on 17-6-2.
 */
public class UserServiceImpl implements IUserService {
    private IUserDao userDao;

    public void setUserDao(IUserDao userDao) {
        this.userDao = userDao;
    }

    public User get(String id) {
        return userDao.get(id);
    }
}
