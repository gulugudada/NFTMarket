package com.c201901090124.nftmarket.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.c201901090124.nftmarket.dao.AccountMapper;
import com.c201901090124.nftmarket.dao.ActionMapper;
import com.c201901090124.nftmarket.dao.BlogMapper;
import com.c201901090124.nftmarket.dao.UserInfoMapper;
import com.c201901090124.nftmarket.entity.Account;
import com.c201901090124.nftmarket.entity.UserInfo;
import com.c201901090124.nftmarket.service.AccountService;
import com.c201901090124.nftmarket.utils.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author 小小怪
 * @title LoginService
 * @create 2022/9/11 10:17
 **/
@Service
public class AccountServiceImpl implements AccountService {

    @Resource
    AccountMapper accountMapper;

    @Resource
    BlogMapper blogMapper;

    @Resource
    UserInfoMapper userInfoMapper;

    @Resource
    ActionMapper actionMapper;

    @Resource
    RedisService redisUtils;

    @Override
    public Result login(String account, String password, HttpServletRequest request) {
        if (accountMapper.findAccountByAccount(account) == 0) {
            return Result.error(-1, "该账号未注册");
        }
        password = SHA_1.jdkSHA1(password.replace("\"", ""));
        if (accountMapper.findPasswordByAccount(account).equals(password)) {
            accountMapper.updateLastTime(account,DateUtil.getDate());
            accountMapper.updateLastLoginIp(account,IpAddressUtil.getIpAddr(request));
            Result result = new Result();
            result.put("account", account);
            Account account1 = accountMapper.findUserNameAvatarByAccount(account);
            result.put("username", account1.getUsername());
            result.put("avatar",account1.getAvatar());
            result.put("msg", "登录成功");
            return result;
        }
        return Result.error(-1, "密码错误");
    }

    @Override
    public Result registerAccount(String account, String username, String password, String code) {
        if (redisUtils.exists("verifyCode"+account)) {
            JSONObject json = (JSONObject)redisUtils.get("verifyCode"+account);
            if(code.equals(json.get("code"))) {
                if (accountMapper.findAccountByUsername(username) == 0) {
                    password = SHA_1.jdkSHA1(password);
                    Account account1 = new Account();
                    account1.setAccount(account);
                    account1.setUsername(username);
                    account1.setPassword(password);
                    account1.setCreateTime(DateUtil.getDate());
                    account1.setAvatar("https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png");
                    if (accountMapper.addAccount(account1) > 0) {
                        redisUtils.remove("verifyCode"+account);
                        UserInfo userInfo = new UserInfo();
                        userInfo.setAccount(account);
                        userInfo.setProfile("TA很懒，还没有添加简介");
                        if (userInfoMapper.addUserInfo(userInfo) > 0) {
                            return Result.ok("注册成功");
                        }
                    }
                }
                return Result.error(-1, "该用户名已被使用");
            }
            return Result.error(-1,"验证码输入错误");
        }
        return Result.error(-1,"验证码未获取或已经过时效");
    }

    @Override
    public Result resetPassword(String account, String password, String code) {
        if (redisUtils.exists("verifyCode" + account)) {
            JSONObject json = (JSONObject) redisUtils.get("verifyCode" + account);
            if (code.equals(json.get("code"))) {
                password = SHA_1.jdkSHA1(password);
                if (accountMapper.resetPassword(account,password) > 0) {
                    return Result.ok("重置成功");
                }
                return Result.error(-1, "重置失败");
            }
            return Result.error(-1, "验证码输入错误");
        }
        return Result.error(-1,"验证码未获取或已经过时效");
    }

    @Override
    public Result getCode(String account) throws Exception {
        if(accountMapper.findAccountByAccount(account) != 0) {
            return Result.error(-1,"该账号已存在");
        }
        String code = SMS.getSMS(account);
        //在Redis中创建key为”verifyCode“加账号的项
        if (redisUtils.exists("verifyCode"+account)) {
            redisUtils.remove("verifyCode"+account);
        }
        JSONObject json = new JSONObject();
        json.put("account",account);
        json.put("code",code);
        json.put("createTime",DateUtil.getDate());
        if (redisUtils.set("verifyCode"+account,json,5L, TimeUnit.MINUTES)) {
            return Result.ok("获取成功");
        }
        return Result.error(-1,"获取失败");
    }

    @Override
    public Result getCode1(String account) throws Exception {
        if(accountMapper.findAccountByAccount(account) == 0) {
            return Result.error(-1,"该账号不存在，请先注册！");
        }
        String code = SMS.getSMS(account);
        if (redisUtils.exists("verifyCode"+account)) {
            redisUtils.remove("verifyCode"+account);
        }
        JSONObject json = new JSONObject();
        json.put("account",account);
        json.put("code",code);
        json.put("createTime",DateUtil.getDate());
        if (redisUtils.set("verifyCode"+account,json,5L, TimeUnit.MINUTES)) {
            return Result.ok("获取成功");
        }
        return Result.error(-1,"获取失败");
    }

    @Override
    public Result uploadAvatar(String account, MultipartFile file) throws IOException {
        JSONObject json = PicUtil.singleFileUpload(file, "/avatar");
        if (json.getInteger("code") == -1) {
            return Result.error(-1,json.getString("msg"));
        }
        String avatarURL = "/avatar/" + json.getString("msg");
        if (accountMapper.updateAvatar(account,avatarURL) == 1) {
            Result result = new Result();
            result.put("msg","上传成功");
            result.put("avatarurl",avatarURL);
            return result;
        }
        return Result.error(-1,"上传失败");
    }

    @Override
    public Result getAccount(String account) {
        return Result.ok(accountMapper.findUserNameAvatarByAccount(account));
    }

    @Override
    public Result updateLoginStatus(String account, HttpServletRequest request) {
        if (accountMapper.updateLastTime(account,DateUtil.getDate()) > 0) {
            if (accountMapper.updateLastLoginIp(account,IpAddressUtil.getIpAddr(request)) > 0) {
                return Result.ok("登录状态更新成功");
            }
        }
        return Result.error(-1,"登录状态更新失败");
    }

    @Override
    public Result getBlogAuthor(String account) {
        Account account1 = accountMapper.findAccount(account);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("account", account1.getAccount());
        jsonObject.put("username", account1.getUsername());
        jsonObject.put("avatar", account1.getAvatar());
        jsonObject.put("createTime", account1.getCreateTime().split(" ")[0]);
        Map<String, Integer> map = blogMapper.getBlogNum(account);
        jsonObject.put("blogCount", map.get("COUNT(*)"));
        //todo  访问量需要加动态的访问量
        jsonObject.put("visits", map.get("SUM(visits)"));
        jsonObject.put("thumbsUpCount", map.get("SUM(thumbsUpCount)"));
//        jsonObject.put("commentCount", );
        jsonObject.put("collectCount", actionMapper.getAuthorCollectCount(account));
        return Result.ok(jsonObject);
    }

}
