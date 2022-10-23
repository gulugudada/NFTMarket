package com.c201901090124.nftmarket.service;

import com.c201901090124.nftmarket.utils.Result;
import org.springframework.stereotype.Service;

/**
 * @author 小小怪
 * @title MallService
 * @create 2022/9/11 13:51
 **/
@Service
public interface MallService {
    public Result findAllCommodity();
}
