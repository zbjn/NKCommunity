package com.ywz.nkcommunity.mapper;

import com.ywz.nkcommunity.entity.LoginTicket;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author YWZ
 * @date 2022/11/11 - 12:22
 */

@Mapper
public interface LoginTicketMapper {

    int insertLoginTicket(LoginTicket loginTicket);

    LoginTicket selectByTicket(@Param("ticket")String ticket);

    int updateStatus(@Param("ticket")String ticket,@Param("status") int status);

}
