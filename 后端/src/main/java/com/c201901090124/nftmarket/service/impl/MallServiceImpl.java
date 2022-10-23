package com.c201901090124.nftmarket.service.impl;

import com.c201901090124.nftmarket.dao.MallMapper;
import com.c201901090124.nftmarket.entity.Commodity;
import com.c201901090124.nftmarket.service.MallService;
import com.c201901090124.nftmarket.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 小小怪
 * @title MallServiceImpl
 * @create 2022/9/11 13:52
 **/
@Service
public class MallServiceImpl implements MallService {

    @Autowired
    MallMapper mallMapper;

    @Override
    public Result findAllCommodity() {
        List<Commodity> commodityList = mallMapper.findAllCommodity();
        if (commodityList == null){
            return Result.error(-1,"获取失败");
        }
        return Result.ok(commodityList);
    }
}
