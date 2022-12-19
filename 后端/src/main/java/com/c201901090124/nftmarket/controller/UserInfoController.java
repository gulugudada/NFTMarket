package com.c201901090124.nftmarket.controller;

import com.c201901090124.nftmarket.service.UserInfoService;
import com.c201901090124.nftmarket.utils.Result;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author 小小怪
 * @title UserInfoController
 * @create 2022/9/22 15:30
 **/
@Api(tags="用户信息接口类")
@RestController
@CrossOrigin
public class UserInfoController {

    @Autowired
    UserInfoService userInfoService;

    /**
     * 获取博客作者推荐
     * @return
     */
    @PostMapping(value = "/getAuthorRecommend")
    public Result getAuthorRecommend(){
        return userInfoService.getAuthorRecommend();
    }
}
