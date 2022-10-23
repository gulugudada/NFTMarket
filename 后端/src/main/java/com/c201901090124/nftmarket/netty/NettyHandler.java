package com.c201901090124.nftmarket.netty;

import com.alibaba.fastjson.JSONObject;
import com.c201901090124.nftmarket.dao.MessageMapper;
import com.c201901090124.nftmarket.entity.Message;
import com.c201901090124.nftmarket.utils.SpringUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelId;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 上文中需要自定义处理的handler
 * TextWebSocketFrame  用于为websockt处理文本的对象
 * @author 小小怪
 * @title NettyHandler
 * @create 2022/10/3 16:08
 **/
public class NettyHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {
    //用于记录和管理所有客户端的channel
    private static ChannelGroup clients =
            new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    //记录账号号码与通道id的关系  account --> channelid  channelid --> account
    //必须要是静态常量，否则每次记录的时候Map都是空的
    static Map<String, ChannelId> channelMap = new HashMap<String,ChannelId>();
    static Map<String,String> channelMap1 = new HashMap<String,String>();

    //通过Spring获得Mapper，从而使用数据库
    //自动注入在这里无效，因为NettyHandler这个类不归Spring管理
    private static final MessageMapper messageMapper;
    static {
        messageMapper = SpringUtil.getBean(MessageMapper.class);
    }

    //客户端创建的时候触发，当客户端连接上服务端之后，就可以获取该channel，然后放到channelGroup中进行统一管理
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        clients.add(ctx.channel());
        channelMap1.put(ctx.channel().id().asShortText(),"");
        System.out.println("客户端连接，当前连接的channel的短ID是：" +ctx.channel().id().asShortText());
        System.out.println(channelMap);
    }

    //客户端销毁的时候触发，
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        //当handlerRemoved 被触发时候，channelGroup会自动移除对应的channel
        //clients.remove(ctx.channel());
        channelMap.remove(channelMap1.get(ctx.channel().id().asShortText()));
        channelMap1.remove(ctx.channel().id().asShortText());
        System.out.println("客户端断开，当前被移除的channel的短ID是：" +ctx.channel().id().asShortText());
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        //客户端传递过来的消息
        if (msg.text().contains("{")) {//判断是Json
            System.out.println("客户端的消息是:" + msg.text());
            JSONObject message = (JSONObject) JSONObject.parse(msg.text());
            String from = message.getString("from");
            String to = message.getString("to");
            String content = message.getString("content");
            Message message1 = new Message(from,to,content,message.getString("sendTime"));
            if (channelMap.containsKey(to)) {
                clients.find(channelMap.get(to)).writeAndFlush(new TextWebSocketFrame(JSONObject.toJSONString(message1)));
            }
            else {
                messageMapper.addMessage(message1);
            }
        }
        else {//身份信息
            String content = msg.text();
            channelMap.remove(content);
            channelMap.put(content,ctx.channel().id());
//            if (channelMap1.containsValue(content)) {
//
//            }
            channelMap1.replace(ctx.channel().id().asShortText(),content);
            if (messageMapper.haveMessage(content) > 0) {
                List<Message> messageList = messageMapper.findMessage(content);
                for (Message message : messageList) {
                    ctx.channel().writeAndFlush(new TextWebSocketFrame(JSONObject.toJSONString(message)));
                }
                messageMapper.deleteMessageAll(content);
            }
            System.out.println("接收到了客户端的消息是:" + content);
        }
        //将客户端发送过来的消息刷到所有的channel中
//        for(Channel channel : clients){
//            channel.writeAndFlush(
//                    new TextWebSocketFrame("[服务器接收到了客户端的消息:]" + LocalDateTime.now()+",消息为:" + content));
//        }
    }
}
