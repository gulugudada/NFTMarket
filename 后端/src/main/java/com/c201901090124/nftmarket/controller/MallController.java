package com.c201901090124.nftmarket.controller;

import com.c201901090124.nftmarket.service.MallService;
import com.c201901090124.nftmarket.utils.Result;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 小小怪
 * @title MallController
 * @create 2022/9/11 10:02
 **/
@Api(tags="商城接口类")
@RestController
@CrossOrigin
public class MallController {

    @Autowired
    MallService mallService;

    @PostMapping("findAllCommodity")
    public Result findAllCommodity(){
        return mallService.findAllCommodity();
    }
}
