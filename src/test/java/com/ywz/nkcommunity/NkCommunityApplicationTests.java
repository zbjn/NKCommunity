package com.ywz.nkcommunity;

import com.ywz.nkcommunity.entity.DiscussPost;
import com.ywz.nkcommunity.entity.User;
import com.ywz.nkcommunity.mapper.DiscussPostMapper;
import com.ywz.nkcommunity.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;

@Slf4j
@SpringBootTest
class NkCommunityApplicationTests {

    @Autowired
    private DiscussPostMapper discussPostMapper;
    @Autowired
    private UserMapper userMapper;

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



}
