package com.oetsky.project.netty5service;

import com.oetsky.project.observer.Subject;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import java.util.HashMap;
import java.util.Map;

public class NettyDomain {

    // 服务端口启动KEY
    private static Map<Integer, String> NETTY_SERVICE_PROTECT = new HashMap<>();

    public static Map<String, Subject> nettyMsgListener = new HashMap<>();
    // 获取服务端口启动KEY
    public synchronized static String getNettyServicePortect(Integer ipPort) {
        return NettyDomain.NETTY_SERVICE_PROTECT.get(ipPort);
    }

    // 设置服务端口启动KEY
    public synchronized static void setNettyServicePortect(Integer ipPort, String protectKeys) {
        NettyDomain.NETTY_SERVICE_PROTECT.put(ipPort, protectKeys);
    }

    // 删除服务端口启动KEY
    public synchronized static void removeNettyServicePortect(Integer ipPort) {
        NettyDomain.NETTY_SERVICE_PROTECT.remove(ipPort);
    }


    private static Map<Integer, ChannelFuture> NETTY_SERVICE_CHANNEL_FUTURE = new HashMap<>();
    // 根据端口获取服务端口通道
    public synchronized static ChannelFuture getNettyServiceChannelFuture(Integer ipPort) {
        return NettyDomain.NETTY_SERVICE_CHANNEL_FUTURE.get(ipPort);
    }

    // 根据端口设置服务端口通道
    public synchronized static void setNettyServiceChannelFuture(Integer ipPort, ChannelFuture cf) {
        NettyDomain.NETTY_SERVICE_CHANNEL_FUTURE.put(ipPort, cf);
    }

    // 根据端口删除服务端口通道
    public synchronized static void removeNettyServiceChannelFuture(Integer ipPort) {
        NettyDomain.NETTY_SERVICE_CHANNEL_FUTURE.remove(ipPort);
    }

    // 服务端口心跳线程KEY
    private static Map<Integer, String> NETTY_SERVICE_CONTEST = new HashMap<>();

    // 获取服务端口心跳线程KEY
    public synchronized static String getNettyServiceContext(Integer ipPort) {
        return NettyDomain.NETTY_SERVICE_CONTEST.get(ipPort);
    }

    // 设置服务端口心跳线程KEY
    public synchronized static void setNettyServiceContext(Integer ipPort, String protectKeys) {
        NettyDomain.NETTY_SERVICE_CONTEST.put(ipPort, protectKeys);
    }

    public synchronized static void removeNettyServiceContext(Integer ipPort) {
        NettyDomain.NETTY_SERVICE_CONTEST.remove(ipPort);
    }


    // 服务端口电能表线程KEY
    private static Map<Integer, String> NETTY_SERVICE_ELECTRIC = new HashMap<>();
    // 获取服务端口心跳线程KEY
    public synchronized static String getNettyServiceElectric(Integer ipPort) {
        return NettyDomain.NETTY_SERVICE_ELECTRIC.get(ipPort);
    }

    // 设置服务端口心跳线程KEY
    public synchronized static void setNettyServiceElectric(Integer ipPort, String protectKeys) {
        NettyDomain.NETTY_SERVICE_ELECTRIC.put(ipPort, protectKeys);
    }

    public synchronized static void removeNettyServiceElectric(Integer ipPort) {
        NettyDomain.NETTY_SERVICE_ELECTRIC.remove(ipPort);
    }













    // 服务端口中连接到的客户端连接通道
    private static Map<Integer, Map<String, ChannelHandlerContext>> channelPool = new HashMap<>();

    // 获取服务端口中连接到的客户端连接通道
    public synchronized static Map<String, ChannelHandlerContext> getChannelPool(Integer ipPort) {
        return channelPool.get(ipPort);
    }

    // 设置服务端口中连接到的客户端连接通道
    public synchronized static void setChannelPool(Integer ipPort, String ipAddres,
        ChannelHandlerContext ctx) {
        Map<String, ChannelHandlerContext> stringChannelHandlerContextMap = channelPool.get(ipPort);
        if (stringChannelHandlerContextMap == null) {
            stringChannelHandlerContextMap = new HashMap<>();
        }
        stringChannelHandlerContextMap.put(ipAddres, ctx);
        channelPool.put(ipPort, stringChannelHandlerContextMap);
    }

    // 删除服务端口中连接到的客户端连接通道
    public synchronized static void removeChannelPool(Integer ipPort) {
        channelPool.remove(ipPort);
    }

    // 删除获取服务端口中连接到的客户端连接通道（获取服务端口，再根据客户端IP地址进行删除）
    public synchronized static void removeChannelPoolContext(Integer ipPort, String ipAddres) {
        Map<String, ChannelHandlerContext> stringChannelHandlerContextMap = channelPool.get(ipPort);
        stringChannelHandlerContextMap.remove(ipAddres);
    }
}
