package com.c201901090124.nftmarket.service;

import com.c201901090124.nftmarket.utils.Result;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author 小小怪
 * @title LoginService
 * @create 2022/9/11 10:17
 **/
@Service
public interface AccountService {
    Result login(String account, String password, HttpServletRequest request);
    Result registerAccount(String account,String username,String password,String code);
    Result resetPassword(String account,String password,String code);
    Result getCode(String account) throws Exception;
    Result getCode1(String account) throws Exception;
    Result uploadAvatar(String account, MultipartFile file) throws IOException;
    Result getAccount(String account);
    Result updateLoginStatus(String account, HttpServletRequest request);
    Result getBlogAuthor(String account);
}
