package com.c201901090124.nftmarket.entity.VO;

import lombok.Data;

/**
 * @author 小小怪
 * @title CommentVO
 * @create 2022/11/28 14:40
 **/
@Data
public class CommentVO {
    private int id;
    private String account;
    private String content;
    private String type;
    //评论账号
    private String commentAccount;
    private String username;
    private String avatar;
    private Object object;
    private String commentTime;

    public CommentVO(int id, String account, String content, String type, String commentAccount, String commentTime) {
        this.id = id;
        this.account = account;
        this.content = content;
        this.type = type;
        this.commentAccount = commentAccount;
        this.commentTime = commentTime;
    }
}
