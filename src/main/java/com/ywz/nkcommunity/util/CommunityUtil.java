package com.ywz.nkcommunity.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.message.ReusableMessage;
import org.springframework.util.DigestUtils;

import java.io.UnsupportedEncodingException;
import java.util.UUID;

/**
 * @author YWZ
 * @date 2022/11/8 - 15:51
 */
public class CommunityUtil {

    //生成随机字符串
    public static String generateUUID(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }

    //MD5加密
    public static String MD5(String key){
        if (StringUtils.isBlank(key))
            return null;
        return DigestUtils.md5DigestAsHex(key.getBytes());
    }

}
