package com.c201901090124.nftmarket.dao;

import com.c201901090124.nftmarket.entity.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 小小怪
 * @title MessageMapper
 * @create 2022/11/28 11:53
 **/
@Mapper
public interface MessageMapper {
    List<Comment> getCommentMessage(@Param("account") String account, @Param("start") int start, @Param("count") int count);
    Comment getCommentById(int id);
    int getCommentMessageNum(String account);
    void clearCommentMessage(String account);
    void deleteCommentMessage(int id);
}
