package com.c201901090124.nftmarket.service.impl;

import com.c201901090124.nftmarket.dao.ActionMapper;
import com.c201901090124.nftmarket.entity.Action;
import com.c201901090124.nftmarket.service.ActionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author 小小怪
 * @title ActionServiceImpl
 * @create 2022/11/6 1:06
 **/
@Service
public class ActionServiceImpl implements ActionService {

    @Resource
    ActionMapper actionMapper;

    @Override
    @Transactional //原子性
    public void thumbsUp(Action action) {
        actionMapper.thumbsUp(action);
        actionMapper.addAction(action);
    }

    @Override
    @Transactional //原子性
    public void thumbsDown(Action action) {
        actionMapper.thumbsDown(action);
        actionMapper.addAction(action);
    }
}
