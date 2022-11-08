package com.ywz.nkcommunity.controller;

import com.ywz.nkcommunity.entity.User;

import com.ywz.nkcommunity.service.UserService;
import com.ywz.nkcommunity.util.CommunityConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author YWZ
 * @date 2022/11/8 - 15:40
 */

@Controller
public class LoginController implements CommunityConstant {

    @Autowired
    private UserService userService;


    @GetMapping("/register")
    public String getRegisterPage(){
        return "/site/register";
    }

    @GetMapping("/login")
    public String getLogin(){
        return "/site/login";
    }

    @PostMapping("/register")
    public String register(User user, Model model){

        Map<String, Object> map = userService.register(user);
        if (map == null||map.isEmpty()){
            model.addAttribute("msg","注册成功,已向您的邮箱发送了一封邮件,请尽快激活");
            model.addAttribute("target","/index");
            return "/site/operate-result";
        }
        else {
            model.addAttribute("usernameMsg",map.get("usernameMsg"));
            model.addAttribute("passwordMsg",map.get("emailMsg"));
            model.addAttribute("emailMsg",map.get("emailMsg"));
            return "/site/register";
        }

    }

    @GetMapping("/activation/{userId}/{code}")
    public String activation(@PathVariable("userId")int userId,
                             @PathVariable("code")String code,Model model){

        int result = userService.activation(userId,code);
        if (result == ACTIVATION_FAILURE){
            model.addAttribute("msg","激活失败,请检查您的操作！");
            model.addAttribute("target","/index");
        }else if (result == ACTIVATION_REPEAT){
            model.addAttribute("msg","无效操作,您的账号已经激活过了！");
            model.addAttribute("target","/index");
        }else {
            model.addAttribute("msg","激活成功,您的账号可以正常使用了！");
            model.addAttribute("target","/login");
        }

        return "/site/operate-result";

    }

}
