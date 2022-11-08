package com.ywz.nkcommunity.service.imp;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ywz.nkcommunity.entity.User;
import com.ywz.nkcommunity.entity.vo.Page;
import com.ywz.nkcommunity.mapper.UserMapper;
import com.ywz.nkcommunity.service.UserService;
import com.ywz.nkcommunity.util.CommunityConstant;
import com.ywz.nkcommunity.util.CommunityUtil;
import com.ywz.nkcommunity.util.MailClient;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @author YWZ
 * @date 2022/11/6 - 17:11
 */


@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService, CommunityConstant {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MailClient mailClient;

    @Autowired
    private TemplateEngine templateEngine;

    @Value("${community.path.domain}")
    private String domain;

    @Override
    public User findUserById(int id) {
        return userMapper.findUserById(id);
    }

    @Override
    public Map<String, Object> register(User user) {
        Map<String,Object> map = new HashMap<>();

        if (user==null){
            throw new IllegalArgumentException("参数不能为空");
        }
        if (StringUtils.isBlank(user.getUserName())){
            map.put("usernameMsg","账号不能为空！");
            return map;
        }
        if (StringUtils.isBlank(user.getPassword())){
            map.put("passwordMsg","密码不能为空！");
        }
        if (StringUtils.isBlank(user.getEmail())){
            map.put("emailMsg","邮箱不能为空！");
        }

        //验证账号
        User u = userMapper.selectByUserName(user.getUserName());
        if (u!=null){
            map.put("usernameMsg","该账号已被注册");
        }
        User e = userMapper.selectByEmail(user.getEmail());
//        if (e!=null){
//            map.put("emailMsg","该邮箱已被注册");
//        }

        //注册用户
        user.setSalt(CommunityUtil.generateUUID().substring(0,5));
        user.setPassword(CommunityUtil.MD5(user.getPassword()+user.getSalt()));
        user.setType(0);
        user.setStatus(0);
        user.setActivationCode(CommunityUtil.generateUUID());

        user.setHeaderUrl(String.format("http://images.nowcoder.com/head/%dt.png",new Random().nextInt(1000)));

        user.setCreateTime(new Date());

        userMapper.insertUser(user);

        //激活邮件
        Context context = new Context();
        context.setVariable("email",user.getEmail());
        // http://localhost:8081/activation/#{userId}/#{UUID}
        String url = domain + "/activation" +"/"+user.getId()+"/"+user.getActivationCode();
        context.setVariable("url",url);
        String content = templateEngine.process("/mail/activation",context);
        mailClient.sendMail(user.getEmail(),"激活账号",content);

        return map;
    }

    public int activation(int userId,String activationCode){

        User user = userMapper.findUserById(userId);
        Integer status = user.getStatus();

        if (status==1){
            return ACTIVATION_REPEAT;
        }else if (user.getActivationCode().equals(activationCode)){
            userMapper.updateStatus(userId,1);
            return ACTIVATION_SUCCESS;
        }else {
            return ACTIVATION_FAILURE;
        }


    }

}
