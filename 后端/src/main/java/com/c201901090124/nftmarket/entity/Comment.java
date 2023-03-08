package com.c201901090124.nftmarket.entity;

import lombok.Data;

/**
 * @author 小小怪
 * @title MallMapper
 * @create 2022/11/28 13:53
 **/
@Data
public class Comment {
    private int id;
    private String account;
    private String content;
    private String type;
    //评论账号
    private String commentAccount;
    private int commentContentId;
    private String commentTime;
}
