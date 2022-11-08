package com.ywz.nkcommunity.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ywz.nkcommunity.entity.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * @author YWZ
 * @date 2022/11/6 - 17:01
 */
public interface UserService extends IService<User> {

    User findUserById(int id);

    Map<String,Object> register(User user);

    int activation(int userId,String activationCode);

}
