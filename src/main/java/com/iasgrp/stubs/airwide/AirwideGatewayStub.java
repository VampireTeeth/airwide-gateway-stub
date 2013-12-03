package com.iasgrp.stubs.airwide;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

import java.nio.ByteOrder;
import java.util.concurrent.ThreadFactory;

public class AirwideGatewayStub {

	private static final boolean FAIL_FAST = true;
	private static final int INITIAL_BYTES_TO_STRIP = 0;
	private static final int LENGTH_ADJUSTMENT = 0;
	private static final int LENGTH_FIELD_LENGTH = 2;
	private static final int LENGTH_FIELD_OFFSET = 8;

	private enum Group {
		BOSS, WORKER;
	}

	private class DiscardServerThreadFactory implements ThreadFactory {

		private Group group;

		private DiscardServerThreadFactory(Group group) {
			this.group = group;
		}

		@Override
		public Thread newThread(Runnable r) {
			Thread t = new Thread(r);
			String name = group.name() + ":Discard server event loop group thread[" + t.hashCode() + "]";
			t.setName(name);
			return t;
		}

	}

	private int port;

	public AirwideGatewayStub(int port) {
		this.port = port;
	}

	public void run() throws Exception {
		EventLoopGroup bossGroup = new NioEventLoopGroup(100, new DiscardServerThreadFactory(Group.BOSS));
		EventLoopGroup workerGroup = new NioEventLoopGroup(500, new DiscardServerThreadFactory(Group.WORKER));
		try{
			ServerBootstrap b = new ServerBootstrap();
			b.group(bossGroup, workerGroup)
			 .channel(NioServerSocketChannel.class)
			 .childHandler(new ChannelInitializer<SocketChannel>() {
	
				@Override
				protected void initChannel(SocketChannel ch) throws Exception {
					ch.pipeline().addLast(airwideLengthFieldBasedFrameDecoder());
					
					ch.pipeline().addLast(new AirwideTcpipInboundHandler());
				}
			 })
			 .childOption(ChannelOption.SO_KEEPALIVE, true)
			 .option(ChannelOption.SO_BACKLOG, 128)
			 ;
			
			ChannelFuture f = b.bind(port).sync();
			f.channel().closeFuture().sync();
		}finally{
			workerGroup.shutdownGracefully();
			bossGroup.shutdownGracefully();
		}
	}

	private LengthFieldBasedFrameDecoder airwideLengthFieldBasedFrameDecoder() {
		return new LengthFieldBasedFrameDecoder(
				ByteOrder.LITTLE_ENDIAN, 
				Integer.MAX_VALUE, 
				LENGTH_FIELD_OFFSET, 
				LENGTH_FIELD_LENGTH, 
				LENGTH_ADJUSTMENT, 
				INITIAL_BYTES_TO_STRIP, 
				FAIL_FAST);
	}
	
	public static void main(String[] args) throws Exception {
		new AirwideGatewayStub(17000).run();
	}
}
