package com.deng.files.controller;

import com.deng.files.entity.User;
import com.deng.files.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 登录方法
     */
    @PostMapping("/login")
    public String login(User user, HttpSession session) {
        User UserDb = userService.login(user);
        if (UserDb != null) {
            session.setAttribute("user", UserDb);
            return "redirect:/file/findAllFiles";
        }else {
            return "redirect:/index";
        }
    }
}
