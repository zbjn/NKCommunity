package com.ywz.nkcommunity.service.imp;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ywz.nkcommunity.entity.User;
import com.ywz.nkcommunity.mapper.UserMapper;
import com.ywz.nkcommunity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author YWZ
 * @date 2022/11/6 - 17:11
 */

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User findUserById(int id) {
        return userMapper.findUserById(id);
    }
}
