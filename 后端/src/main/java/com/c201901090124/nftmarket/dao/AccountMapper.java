package com.c201901090124.nftmarket.dao;

import com.c201901090124.nftmarket.entity.Account;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 小小怪
 * @title AccountMapper
 * @create 2022/9/11 10:16
 **/
@Mapper
public interface AccountMapper {
    String findPasswordByAccount(String account);
    Account findUserNameAvatarByAccount(String account);
    int findAccountByAccount(String account);
    int findAccountByUsername(String username);
    int addAccount(Account account);
    int resetPassword(String account,String password);
    void updateLastTime(String account, String lasttime);
    void updateLastLoginIp(String account, String ipAddress);
    int updateAvatar(String account,String avatarurl);
}
