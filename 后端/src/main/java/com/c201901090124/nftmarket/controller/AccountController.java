package com.c201901090124.nftmarket.controller;

import com.c201901090124.nftmarket.service.AccountService;
import com.c201901090124.nftmarket.utils.Result;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    @Autowired
    AccountService accountService;


    /**
     * 登录
     * @return
     */
    @PostMapping(value = "/login")
    public Result login(HttpServletRequest request, @RequestBody Map<String, String> map){
        return accountService.login(map.get("account"), map.get("password"),request);
    }

    /**
     * 注册账号
     * @param map
     * @return
     */
    @PostMapping("registerAccount")
    public Result registerAccount(@RequestBody Map<String, String> map) {
        return accountService.registerAccount(map.get("account"),map.get("username"),map.get("password"),map.get("code"));
    }

    @PostMapping("resetPassword")
    public Result resetPassword(@RequestBody Map<String, String> map) {
        return accountService.resetPassword(map.get("account"),map.get("password"),map.get("code"));
    }

    /**
     * 获得验证码
     * @param map
     * @throws Exception
     */
    @PostMapping("getCode")
    public Result getCode(@RequestBody Map<String, String> map) throws Exception {
        return accountService.getCode(map.get("account"));
    }

    /**
     * 重置密码时的验证码
     * @param map
     * @return
     * @throws Exception
     */
    @PostMapping("getCode1")
    public Result getCode1(@RequestBody Map<String, String> map) throws Exception {
        return accountService.getCode1(map.get("account"));
    }

    /**
     * 上传头像
     * @param account
     * @param file
     * @return
     * @throws Exception
     */
    @PostMapping("uploadAvatar")
    public Result uploadAvatar(@RequestParam("account")String account,@RequestParam("file") MultipartFile file) throws Exception {
        return accountService.uploadAvatar(account,file);
    }

    /**
     * 根据账号获得账号信息
     * @param account
     * @return
     * @throws Exception
     */
    @PostMapping("getAccount")
    public Result getAccount(@RequestParam("account")String account) throws Exception {
        return accountService.getAccount(account);
    }
}
