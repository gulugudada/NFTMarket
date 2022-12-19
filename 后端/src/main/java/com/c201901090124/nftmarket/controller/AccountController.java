package com.c201901090124.nftmarket.controller;

import com.c201901090124.nftmarket.service.AccountService;
import com.c201901090124.nftmarket.utils.Result;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author 小小怪
 * @title LoginController
 * @create 2022/9/11 10:01
 **/
@Api(tags="账号接口类")
@RestController
@CrossOrigin
public class AccountController {

    @Resource
    AccountService accountService;

    /**
     * 登录
     */
    @PostMapping(value = "/login")
    public Result login(HttpServletRequest request, @RequestBody Map<String, String> map){
        return accountService.login(map.get("account"), map.get("password"),request);
    }

    /**
     * 注册账号
     */
    @PostMapping("registerAccount")
    public Result registerAccount(@RequestBody Map<String, String> map) {
        return accountService.registerAccount(map.get("account"),map.get("userName"),map.get("password"),map.get("code"));
    }

    /**
     * 重置密码
     */
    @PostMapping("resetPassword")
    public Result resetPassword(@RequestBody Map<String, String> map) {
        return accountService.resetPassword(map.get("account"),map.get("password"),map.get("code"));
    }

    /**
     * 获得验证码
     */
    @PostMapping("getCode")
    public Result getCode(@RequestBody Map<String, String> map) throws Exception {
        return accountService.getCode(map.get("account"));
    }

    /**
     * 重置密码时的验证码
     */
    @PostMapping("getCode1")
    public Result getCode1(@RequestBody Map<String, String> map) throws Exception {
        return accountService.getCode1(map.get("account"));
    }

    /**
     * 上传头像
     */
    @PostMapping("uploadAvatar")
    public Result uploadAvatar(@RequestParam("account")String account,@RequestParam("file") MultipartFile file) throws Exception {
        return accountService.uploadAvatar(account,file);
    }

    /**
     * 根据账号获得账号信息
     */
    @PostMapping("getAccount")
    public Result getAccount(@RequestBody Map<String, String> map) {
        return accountService.getAccount(map.get("account"));
    }
}
