package com.wang.dao;

import com.wang.pojo.User;
import org.mybatis.spring.support.SqlSessionDaoSupport;

/**
 * Created by wang on 17-6-2.
 */
public class UserDaoImpl extends SqlSessionDaoSupport implements IUserDao {

    public User get(String id) {
        return this.getSqlSession().selectOne("com.wang.pojo.User.get", id);
    }
}
