package com.c201901090124.nftmarket.entity;

import lombok.Data;

/**
 * 操作实体类
 * @author 小小怪
 * @title Action
 * @create 2022/11/5 20:40
 **/
@Data
public class Action {
    private int id;
    private String account;
    private int actionId;
    private String type;
    private String actionType;
    private String actionTime;

    public Action(String account, int actionId, String type, String actionType, String actionTime) {
        this.account = account;
        this.actionId = actionId;
        this.type = type;
        this.actionType = actionType;
        this.actionTime = actionTime;
    }

    public Action() {

    }
}
