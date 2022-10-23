package com.c201901090124.nftmarket.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.c201901090124.nftmarket.dao.UserInfoMapper;
import com.c201901090124.nftmarket.service.UserInfoService;
import com.c201901090124.nftmarket.utils.PicUtil;
import com.c201901090124.nftmarket.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author 小小怪
 * @title UserInfoServiceImpl
 * @create 2022/9/22 15:32
 **/
@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    UserInfoMapper userInfoMapper;
}
