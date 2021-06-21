package com.bo.sims.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



/**
 * @author Bo
 * @create 2021-06-18 10:23
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private Long id;    //用户id
    private String username;    //用户名
    private String password;    //密码

}
