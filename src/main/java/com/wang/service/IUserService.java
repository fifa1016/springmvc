package com.wang.service;

import com.wang.pojo.User;

import java.util.List;

/**
 * Created by wang on 17-6-2.
 */
public interface IUserService {
    public User get(String id);

    public void insert(User user);

    public List<User> getList();
}
