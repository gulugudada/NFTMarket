package com.c201901090124.nftmarket.service.impl;

import com.c201901090124.nftmarket.netty.NettyServerInitialzer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;

/**
 * 服务端基本配置，通过一个静态单例类，保证启动时候只被加载一次
 * @author 小小怪
 * @title NettyServer
 * @create 2022/10/3 15:56
 **/
@Component
@Slf4j
public class NettyServer {
    /**
     * 单例静态内部类
     */
    public static class SingletionNettyerver{
        static final NettyServer instance = new NettyServer();
    }

    public static NettyServer getInstance(){
        return SingletionNettyerver.instance;
    }

    private EventLoopGroup mainGroup ;
    private EventLoopGroup subGroup;
    private ServerBootstrap server;
    private ChannelFuture future;

    public NettyServer(){
        mainGroup = new NioEventLoopGroup();
        subGroup = new NioEventLoopGroup();
        server = new ServerBootstrap();
        server.group(mainGroup, subGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new NettyServerInitialzer());//添加自定义初始化处理器
    }
    public void start(){
        future = this.server.bind(8088);
        log.info("netty 服务端启动完毕 .....");
    }
}
