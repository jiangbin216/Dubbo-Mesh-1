/*
 * ProviderClient.java
 * Copyright 2019 Qunhe Tech, all rights reserved.
 * Qunhe PROPRIETARY/CONFIDENTIAL, any form of usage is subject to approval.
 */

package com.alibaba.dubbo.performance.demo.agent.netty.provider.client;

import com.alibaba.dubbo.performance.demo.agent.registry.Endpoint;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoop;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xinba
 */
public class ProviderClient {

    public static final Map<String, Channel> map = new HashMap<>();

    private final EventLoop eventLoop;

    private Channel channel;

    public ProviderClient(final EventLoop eventLoop) {
        this.eventLoop = eventLoop;
    }

    public Channel getChannel() {
        return channel;
    }

    public void connect(final Endpoint endpoint) {
        final Bootstrap bootstrap = new Bootstrap();
        channel = bootstrap.group(eventLoop)
                .channel(NioSocketChannel.class)
                .handler(new ProviderOutInitializer())
                .connect(endpoint.getHost(), endpoint.getPort()).channel();
    }
}
