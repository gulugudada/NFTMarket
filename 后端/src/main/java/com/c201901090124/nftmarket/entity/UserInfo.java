package com.c201901090124.nftmarket.entity;

import lombok.Data;

/**
 * @author 小小怪
 * @title UserInfo
 * @create 2022/9/12 10:32
 **/
@Data
public class UserInfo {
    private int id;
    private String account;
    private String userName;
    private String profile;
    private char sex;
    private String birthday;
    private int visits;
    private String avatar;
}
