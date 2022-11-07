package com.ywz.nkcommunity.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sun.xml.internal.org.jvnet.staxex.Base64EncoderStream;
import com.ywz.nkcommunity.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author YWZ
 * @date 2022/11/6 - 17:03
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    User findUserById(@Param("id")int id);

}
