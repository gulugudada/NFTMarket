package com.c201901090124.nftmarket.service;

import com.c201901090124.nftmarket.entity.Action;
import org.springframework.stereotype.Service;

/**
 * @author 小小怪
 * @title ActionService
 * @create 2022/11/6 1:06
 **/
@Service
public interface ActionService {
    void thumbsUp(Action action);
    void thumbsDown(Action action);
}
