package com.ywz.nkcommunity.controller;

import com.ywz.nkcommunity.entity.DiscussPost;
import com.ywz.nkcommunity.entity.User;
import com.ywz.nkcommunity.entity.vo.Page;
import com.ywz.nkcommunity.service.DiscussPostService;
import com.ywz.nkcommunity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author YWZ
 * @date 2022/11/6 - 17:43
 */

@RequestMapping("/community")
@Controller
public class HomeController {

    @Autowired
    private DiscussPostService discussPostService;

    @Autowired
    private UserService userService;

    @GetMapping("/index")
    public String getIndexPage(Model model, Page page){

        page.setRows(discussPostService.selectDiscussPostRows(0));
        page.setPath("/community/index");


        List<DiscussPost> postList = discussPostService.pageDiscussPost(0, page.getCurrent(), page.getLimit());
        ArrayList<Map<String, Object>> arrayList = new ArrayList<>();

        if (postList!=null){
            for (DiscussPost post :postList) {
                HashMap<String, Object> Map = new HashMap<>();
                Map.put("post",post);
                Integer userId = post.getUserId();
                User user = userService.findUserById(userId);
                Map.put("User",user);
                arrayList.add(Map);
            }
        }
        model.addAttribute("discussPosts",arrayList);
        return "/index";
    }

}
