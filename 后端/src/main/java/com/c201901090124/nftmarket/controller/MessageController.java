package com.c201901090124.nftmarket.controller;

import com.c201901090124.nftmarket.service.MessageService;
import com.c201901090124.nftmarket.utils.Result;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author 小小怪
 * @title MessageController
 * @create 2022/11/28 20:22
 **/
@Api(tags="消息类")
@RestController
@CrossOrigin
public class MessageController {

    @Resource
    MessageService messageService;

    /**
     * 获得账号的相关评论
     * @param map
     * @return
     */
    @PostMapping(value = "/getCommentMessage")
    public Result getCommentMessage(@RequestBody Map<String, String> map) {
        return Result.ok(messageService.getCommentMessage(map.get("account"), Integer.parseInt(map.get("pageNum"))));
    }

    /**
     * 清空所有评论信息
     * @return
     */
    @PostMapping(value = "/clearCommentMessage")
    public void clearComment(@RequestBody Map<String, String> map) {
        messageService.clearCommentMessage(map.get("account"));
    }

    /**
     * 删除评论信息
     * @param map
     */
    @PostMapping(value = "/deleteCommentMessage")
    public void deleteCommentMessage(@RequestBody Map<String, Integer> map) {
        messageService.deleteCommentMessage(map.get("id"));
    }

}
