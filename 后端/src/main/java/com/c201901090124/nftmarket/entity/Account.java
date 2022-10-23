package com.c201901090124.nftmarket.entity;

import lombok.Data;

/**
 * @author 小小怪
 * @title Account
 * @create 2022/9/11 11:00
 **/
@Data
public class Account {
    private int id;
    private String account;
    private String username;
    private String password;
    private String createtime;
    private String avatar;
}
