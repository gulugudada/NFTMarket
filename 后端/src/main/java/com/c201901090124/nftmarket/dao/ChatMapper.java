package com.c201901090124.nftmarket.dao;

import com.c201901090124.nftmarket.entity.Chat;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author 小小怪
 * @title ChatMapper
 * @create 2022/10/3 21:52
 **/
@Mapper
public interface ChatMapper {
    public int addChat(Chat chat);
    public int deleteChatAll(String account);
    public int haveChat(String account);
    public List<Chat> findChat(String account);
}
