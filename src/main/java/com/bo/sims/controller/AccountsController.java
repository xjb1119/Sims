package com.bo.sims.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Bo
 * @create 2021-06-18 13:55
 */
@Controller
@RequestMapping("/admin")
public class AccountsController {

    @GetMapping("/accounts")
    public String accounts(){
        return "admin/daily-accounts";
    }
}
