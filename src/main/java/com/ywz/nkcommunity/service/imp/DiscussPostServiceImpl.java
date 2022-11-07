package com.ywz.nkcommunity.service.imp;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ywz.nkcommunity.entity.DiscussPost;
import com.ywz.nkcommunity.mapper.DiscussPostMapper;
import com.ywz.nkcommunity.service.DiscussPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author YWZ
 * @date 2022/11/5 - 16:32
 */

@Service
public class DiscussPostServiceImpl extends ServiceImpl<DiscussPostMapper, DiscussPost> implements DiscussPostService {

    @Autowired
    private DiscussPostMapper discussPostMapper;

    @Override
    public List<DiscussPost> pageDiscussPost(int userId,int current,int limit) {
        return discussPostMapper.pageDiscussPost(userId,current,limit);
    }

    @Override
    public int selectDiscussPostRows(int userId) {
        return discussPostMapper.selectDiscussPostRows(userId);
    }
}
