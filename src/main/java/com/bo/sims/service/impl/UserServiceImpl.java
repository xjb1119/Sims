package com.bo.sims.service.impl;


import com.bo.sims.dao.UserMapper;
import com.bo.sims.pojo.User;
import com.bo.sims.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Bo
 * @create 2021-06-18 11:10
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Transactional
    @Override
    public User checkUser(String username, String password) {

        return userMapper.getByUsernameAndPassword(username, password);
    }


}
