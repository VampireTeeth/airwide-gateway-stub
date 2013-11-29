package com.iasgrp.stubs.airwide;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class AirwideTcpipInboundHandler extends ChannelInboundHandlerAdapter{
	
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		ByteBuf buf = (ByteBuf)msg;
		if(buf.readableBytes() < 10) {
			return;
		}
		
		int operationReference = buf.readInt();
		byte messageType = buf.readByte();
		byte operation = buf.readByte();
		
	}
	
}
