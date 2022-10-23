package com.c201901090124.nftmarket.controller;

import com.c201901090124.nftmarket.utils.Result;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 小小怪
 * @title HomeController
 * @create 2022/9/11 9:39
 **/
@Api(tags="主页接口类")
@RestController
@CrossOrigin
public class HomeController {

    @PostMapping("findAllAccount")
    public Result findAllAccount() {
        return null;
    }
}
