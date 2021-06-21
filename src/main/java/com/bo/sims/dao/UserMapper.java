package com.bo.sims.dao;

import com.bo.sims.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author Bo
 * @create 2021-06-18 11:05
 */
@Mapper
@Repository
public interface UserMapper{

    /**
     * 查询用户名和密码
     * @param username
     * @param password
     * @return
     */
    User getByUsernameAndPassword(String username,String password);

}
