package com.c201901090124.nftmarket.service;

import com.c201901090124.nftmarket.entity.UserInfo;
import com.c201901090124.nftmarket.utils.Result;
import org.springframework.stereotype.Service;


/**
 * @author 小小怪
 * @title UserInFService
 * @create 2022/9/22 15:31
 **/
@Service
public interface UserInfoService {
    Result getAuthorRecommend();
}
