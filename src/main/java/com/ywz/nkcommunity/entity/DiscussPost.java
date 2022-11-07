package com.ywz.nkcommunity.entity;

import lombok.Data;

import java.sql.Date;

/**
 * @author YWZ
 * @date 2022/11/5 - 16:27
 */

@Data
public class DiscussPost {
    private Integer id;
    private Integer userId;
    private String title;
    private String content;
    private Integer type;
    private Integer status;
    private Date createTime;
    private Integer commentCount;
    private Double score;

}
