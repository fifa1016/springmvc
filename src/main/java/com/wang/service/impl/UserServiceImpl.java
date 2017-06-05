package com.wang.service.impl;

import com.wang.dao.IUserDao;
import com.wang.pojo.User;
import com.wang.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by wang on 17-6-2.
 */
@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private IUserDao userDao;

    public User get(String id) {
        return userDao.getUser(id);
    }
}
