package com.bo.sims.service;


import com.bo.sims.pojo.User;

/**
 * @author Bo
 * @create 2021-06-18 11:05
 */
public interface UserService{

    /**
     * 检查用户名和密码是否正确
     * @param username
     * @param password
     * @return
     */
    User checkUser(String username, String password);

}
