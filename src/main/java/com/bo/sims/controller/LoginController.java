package com.bo.sims.controller;

import com.bo.sims.pojo.User;
import com.bo.sims.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

/**
 * @author Bo
 * @create 2021-06-18 0:21
 */
@Controller
@RequestMapping
public class LoginController {

    @Autowired
    UserService userService;

    /**
     * 访问/admin直接跳转到管理后台登录页
     * @return
     */
    @GetMapping(value = {"/","/admin"})
    public String loginPage(){

        return "admin/login";
    }


    /**
     * 解决直接在浏览器中输入http://localhost:8080/admin/login访问出错
     * 因为在浏览器中直接输入为get请求，进不去postLogin方法中，会报错
     * 这个方法的作用是让页面跳转到login.html再重新填写表单再以post方式提交
     * @return
     */
    @GetMapping("/admin/login")
    public String getLogin(){

        return "admin/login";
    }

    /**
     * 检查登录的用户名和密码是否正确
     * @param username
     * @param password
     * @return
     */
    @PostMapping("/admin/login")
    public String postLogin(@RequestParam("username") String username,
                            @RequestParam("password") String password,
                            HttpSession session,
                            Model model){

        User user = userService.checkUser(username, password);
        if (user != null){
            //登录成功，把用户信息放在session中返回给页面
            user.setPassword(null);
            session.setAttribute("user",user);
            return "redirect:/admin/bill/payment";
        }else {
            //登录失败，把错误信息放在model中请求转发回admin
            model.addAttribute("message","用户名或密码错误");
            return "admin/login";
        }
    }


    /**
     * 注销
     * @param session
     * @return
     */
    @GetMapping("/admin/logout")
    public String logout(HttpSession session){
        //清空session中的已登录用户信息，再重定向回登录页面
        session.removeAttribute("user");
        return "redirect:/admin/login";
    }


}
