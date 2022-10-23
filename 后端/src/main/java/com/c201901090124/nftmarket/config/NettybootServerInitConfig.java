package com.c201901090124.nftmarket.config;

import com.c201901090124.nftmarket.service.impl.NettyServer;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * @author 小小怪
 * @title NettybootServerInitConfig
 * @create 2022/10/3 16:21
 **/
@Component
public class NettybootServerInitConfig implements ApplicationListener<ContextRefreshedEvent> {
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if(event.getApplicationContext().getParent() == null){
            NettyServer.getInstance().start();
        }
    }
}
