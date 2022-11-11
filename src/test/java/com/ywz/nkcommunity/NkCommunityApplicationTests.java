package com.ywz.nkcommunity;

import com.ywz.nkcommunity.entity.DiscussPost;
import com.ywz.nkcommunity.entity.LoginTicket;
import com.ywz.nkcommunity.entity.User;
import com.ywz.nkcommunity.mapper.DiscussPostMapper;
import com.ywz.nkcommunity.mapper.LoginTicketMapper;
import com.ywz.nkcommunity.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.Date;
import java.util.List;

@Slf4j
@SpringBootTest
class NkCommunityApplicationTests {

    @Autowired
    private DiscussPostMapper discussPostMapper;
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private LoginTicketMapper loginTicketMapper;


    @Test
    public void testSelectPost(){
        List<DiscussPost> list = discussPostMapper.pageDiscussPost(0,1,10);
        for (DiscussPost discussPost :list) {
            System.out.println(list);
        }

        int rows = discussPostMapper.selectDiscussPostRows(0);
        System.out.println(rows);
    }

    @Test
    public void getUserById(){
        User user = userMapper.findUserById(101);
        System.out.println(user);
    }

    @Test
    public void testLoginTicket(){
        LoginTicket loginTicket = new LoginTicket();
        loginTicket.setUserId(101);
        loginTicket.setStatus(0);
        loginTicket.setTicket("abc");
        loginTicket.setExpired(new Date(System.currentTimeMillis()+1000*60*10));
        loginTicketMapper.insertLoginTicket(loginTicket);
    }

    @Test
    public void updateTicket(){
        LoginTicket loginTicket = loginTicketMapper.selectByTicket("abc");
        System.out.println(loginTicket);

        loginTicketMapper.updateStatus("abc",1);

    }


}
