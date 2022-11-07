package com.ywz.nkcommunity.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ywz.nkcommunity.entity.DiscussPost;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author YWZ
 * @date 2022/11/5 - 16:30
 */
@Mapper
public interface DiscussPostMapper extends BaseMapper<DiscussPost> {

    List<DiscussPost> pageDiscussPost(int userId,int current,int limit);

    int selectDiscussPostRows(@Param("userId") int userId);

}
