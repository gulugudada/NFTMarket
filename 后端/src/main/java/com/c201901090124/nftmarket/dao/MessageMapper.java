package com.c201901090124.nftmarket.dao;

import com.c201901090124.nftmarket.entity.Message;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author 小小怪
 * @title MessageMapper
 * @create 2022/10/3 21:52
 **/
@Mapper
public interface MessageMapper {
    public int addMessage(Message message);
    public int deleteMessageAll(String account);
    public int haveMessage(String account);
    public List<Message> findMessage(String account);
}
