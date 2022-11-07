package com.ywz.nkcommunity.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ywz.nkcommunity.entity.DiscussPost;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author YWZ
 * @date 2022/11/5 - 16:31
 */
public interface DiscussPostService extends IService<DiscussPost> {

    List<DiscussPost> pageDiscussPost(int userId,int current,int limit);

    int selectDiscussPostRows(int userId);



}
