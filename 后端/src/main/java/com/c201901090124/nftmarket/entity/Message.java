package com.c201901090124.nftmarket.entity;

import lombok.Data;

/**
 * @author 小小怪
 * @title Message
 * @create 2022/10/3 19:29
 **/
@Data
public class Message {
    private int id;
    private String from;
    private String to;
    private String content;
    private String sendTime;

    public Message(String from,String to,String content,String sendTime) {
        this.from = from;
        this.to = to;
        this.content = content;
        this.sendTime = sendTime;
    }
}
