package com.c201901090124.nftmarket.entity;

import lombok.Data;

/**
 * @author 小小怪
 * @title Blog
 * @create 2022/11/5 12:20
 **/
@Data
public class Blog {
    private int id;
    private String name;
    private String account;
    private String userName;
    private String content;
    private int thumbsUpCount;
    private int thumbsDownCount;
    private int visits;
    private String createTime;
    private String cover;

    public Blog(String name, String account, String content,String createTime) {
        this.name = name;
        this.account = account;
        this.content = content;
        this.createTime = createTime;
    }
}
