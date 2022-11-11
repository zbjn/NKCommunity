package com.ywz.nkcommunity.util;

/**
 * @author YWZ
 * @date 2022/11/8 - 20:40
 */
public interface CommunityConstant {

    /**
     * 激活成功
     */
    int ACTIVATION_SUCCESS = 0;

    /**
     * 重复激活
     */

    int ACTIVATION_REPEAT = 1;

    /**
     * 激活失败
     */

    int ACTIVATION_FAILURE = 2;

    /**
     * 默认状态登陆凭证超时时间
     */

    int DEFAULT_EXPIRED_SECONDS = 3600 * 12;

    /**
     * 记住我状态登陆凭证超时时间
     */

    int REMEMBER_EXPIRED_SECONDS = 3600 * 24 * 100;



}
