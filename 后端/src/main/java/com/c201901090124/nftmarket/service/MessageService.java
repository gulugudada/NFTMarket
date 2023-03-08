package com.c201901090124.nftmarket.service;

import com.c201901090124.nftmarket.utils.Result;
import org.springframework.stereotype.Service;


/**************************************************
 * @Copyright: 2023 4see.tech All rights reserved.
 * @Author: Yan Zixiang (yanzixiang@4see.tech)
 ***************************************************/
@Service
public interface MessageService {
    Result getCommentMessage(String account, int pageNum);
    void clearCommentMessage(String account);
    void deleteCommentMessage(int id);
}
