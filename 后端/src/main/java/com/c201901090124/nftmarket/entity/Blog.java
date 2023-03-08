package com.c201901090124.nftmarket.entity;

import com.c201901090124.nftmarket.utils.DateUtil;
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
    private String avatar;
    private String userName;
    private String content;
    private String abstractContent;
    private int thumbsUpCount;
    private int thumbsDownCount;
    private int visits;
    private String createTime;
    private String publishTime;
    private String modified;
    private String cover;

    public Blog() {

    }

    public Blog(String name, String account, String content) {
        this.name = name;
        this.account = account;
        this.content = content;
        createTime = DateUtil.getDate();
    }

    public Blog(int id, String name, String account, String content, String abstractContent) {
        this.id = id;
        this.name = name;
        this.account = account;
        this.content = content;
        this.abstractContent = abstractContent;
        createTime = DateUtil.getDate();
        publishTime = createTime;
        modified = createTime;
    }
}
