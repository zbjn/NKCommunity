package com.ywz.nkcommunity.controller;

import com.google.code.kaptcha.Producer;
import com.ywz.nkcommunity.entity.User;

import com.ywz.nkcommunity.service.UserService;
import com.ywz.nkcommunity.util.CommunityConstant;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.mail.Session;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;

/**
 * @author YWZ
 * @date 2022/11/8 - 15:40
 */

@Controller
public class LoginController implements CommunityConstant {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private Producer kaptcha;


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

    @GetMapping("/kaptcha")
    public void getKaptchaImage(HttpServletResponse response, HttpSession session){

        String text = kaptcha.createText();
        BufferedImage image = kaptcha.createImage(text);

        //将验证码存入session
        session.setAttribute("kaptcha",text);

        //将图片直接输出给浏览器
        response.setContentType("image/png");
        try {
            ServletOutputStream outputStream = response.getOutputStream();
            ImageIO.write(image,"png",outputStream);
        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.error("响应验证码失败:"+e.getMessage());
        }
    }

    @PostMapping("/login")
    public String login(Model model,String username,
                        String password,
                        String code,
                        boolean rememberme, HttpSession session,
                        HttpServletResponse response){

        String kaptcha = (String)session.getAttribute("kaptcha");
        if (StringUtils.isBlank(code)||StringUtils.isBlank(kaptcha)||!kaptcha.equalsIgnoreCase(code)){
            model.addAttribute("codeMsg","验证码不正确");
            return "/site/login";
        }

        int time = rememberme ? REMEMBER_EXPIRED_SECONDS : DEFAULT_EXPIRED_SECONDS;

        Map<String,Object> map = userService.login(username,password,time);

        if (map.containsKey("ticket")){

            Cookie cookie = new Cookie("ticket",map.get("ticket").toString());
            cookie.setPath("/");
            cookie.setMaxAge(time);
            response.addCookie(cookie);
            return "redirect:/index";
        }else {
            model.addAttribute("usernameMsg",map.get("usernameMsg"));
            model.addAttribute("passwordMsg",map.get("passwordMsg"));
            return "/site/login";
        }
    }

    @GetMapping("/logout")
    public String logout(@CookieValue("ticket")String ticket){

        userService.logout(ticket);

        return "/site/login";
    }



}
