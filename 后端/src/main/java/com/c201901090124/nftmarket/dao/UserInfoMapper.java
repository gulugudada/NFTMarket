package com.c201901090124.nftmarket.dao;

import com.c201901090124.nftmarket.entity.UserInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author 小小怪
 * @title UserInfoMapper
 * @create 2022/9/12 10:36
 **/
@Mapper
public interface UserInfoMapper {
    int addUserInfo(UserInfo userInfo);
    List<UserInfo> getAuthorRecommend();
}
