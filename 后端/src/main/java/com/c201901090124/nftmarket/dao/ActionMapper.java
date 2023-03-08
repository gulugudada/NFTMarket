package com.c201901090124.nftmarket.dao;

import com.c201901090124.nftmarket.entity.Action;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 小小怪
 * @title ActionMapper
 * @create 2022/11/5 19:37
 **/
@Mapper
public interface ActionMapper {
    void thumbsUp(Action action);
    void thumbsDown(Action action);
    void addAction(Action action);
    int getBlogCollectNum(int id);
    int getAuthorCollectCount(String account);
}
