package com.bo.sims;

import com.bo.sims.dao.UserMapper;
import com.bo.sims.pojo.User;
import com.bo.sims.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SimsApplicationTests {


    @Autowired
    UserService userService;

    @Test
    void contextLoads() {
        System.out.println(1);
    }

    @Test
    void test1(){
        User user = userService.checkUser("admin", "123456");
        System.out.println(user);
    }

}
