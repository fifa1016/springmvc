package com.wang.dao;

import com.wang.pojo.User;

import java.util.List;

/**
 * Created by wang on 17-6-1.
 */
public interface IUserDao {
    public User getUser(String id);

    public void insertUser(User user);

    public List<User> getList();
}
