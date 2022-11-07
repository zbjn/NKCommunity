package com.ywz.nkcommunity.controller;

import com.ywz.nkcommunity.entity.DiscussPost;
import com.ywz.nkcommunity.entity.User;
import com.ywz.nkcommunity.service.DiscussPostService;
import com.ywz.nkcommunity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author YWZ
 * @date 2022/11/5 - 16:35
 */

@RestController
public class DiscussPostController {

    @Autowired
    private DiscussPostService discussPostService;

    @Autowired
    private UserService userService;

    /*List<DiscussPost> pageDiscussPost(int userId,int current,int limit);*/
    @GetMapping("/pageDiscussPost/{userId}")
    public List<DiscussPost> pageDiscussPost(@PathVariable("userId") int userId,
                                             @PathVariable("current") int current,
                                             @PathVariable("limit") int limit){

        List<DiscussPost> discussPosts = discussPostService.pageDiscussPost(userId,current,limit);
        return discussPosts;
    }

    @GetMapping("/selectDiscussPostRows/{userId}")
    public int selectDiscussPostRows(@PathVariable("userId")int userId){
        return discussPostService.selectDiscussPostRows(userId);
    }

    @GetMapping("/findUserById/{id}")
    public User findUserById(@PathVariable("id")int id){
        User userById = userService.findUserById(id);
        return userById;
    }



}
