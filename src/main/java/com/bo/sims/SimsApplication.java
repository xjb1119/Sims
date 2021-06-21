package com.bo.sims;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

// 由于部署上服务器，继承SpringBootServletInitializer extends SpringBootServletInitializer
@SpringBootApplication
public class SimsApplication {

    public static void main(String[] args) {
        SpringApplication.run(SimsApplication.class, args);
    }

    //重写此方法
//    @Override
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
//
//        return super.configure(builder);
//    }

}
