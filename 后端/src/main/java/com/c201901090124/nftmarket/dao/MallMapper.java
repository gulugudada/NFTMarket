package com.c201901090124.nftmarket.dao;

import com.c201901090124.nftmarket.entity.Commodity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author 小小怪
 * @title MallMapper
 * @create 2022/9/11 13:53
 **/
@Mapper
public interface MallMapper {
    public List<Commodity> findAllCommodity();
}
