package com.c201901090124.nftmarket.service.impl;

import com.c201901090124.nftmarket.dao.AccountMapper;
import com.c201901090124.nftmarket.dao.UserInfoMapper;
import com.c201901090124.nftmarket.entity.UserInfo;
import com.c201901090124.nftmarket.service.UserInfoService;
import com.c201901090124.nftmarket.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 小小怪
 * @title UserInfoServiceImpl
 * @create 2022/9/22 15:32
 **/
@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Resource
    AccountMapper accountMapper;

    @Resource
    UserInfoMapper userInfoMapper;

    @Resource
    RedisService redisService;

    //只读缓存
    @Override
    public Result getAuthorRecommend() {
        if (!redisService.exists("authorRecommend")) {
            updateAuthorRecommend();
        }
        List<Object> authorList = redisService.lRange("authorRecommend",0,15);
        return Result.ok(authorList);
    }

    /**
     * 定时更新！
     * 每天晚上12点更新Redis中的作者推荐
     */
    @Scheduled(cron = "0 0 0 * * *")
    public void updateAuthorRecommend() {
        //获取访问量前15的账号信息
        //多表映射获取每个账号的头像（已经实现）
        List<UserInfo> authorList = userInfoMapper.getAuthorRecommend();
        if (redisService.exists("authorRecommend")) {
            redisService.remove("authorRecommend");
        }
        for (UserInfo userInfo:authorList) {
            redisService.lPush("authorRecommend",userInfo);
        }
    }

}
