package com.iasgrp.stubs.airwide;

import java.io.ByteArrayOutputStream;

import org.apache.commons.codec.binary.Hex;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

public class DiscardServerHandler extends ChannelInboundHandlerAdapter{

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try{
			ByteBuf buf = (ByteBuf)msg;
			System.out.format("Received %d bytes from channel, will discard it.%n", buf.readableBytes());
			buf.readBytes(out, buf.readableBytes());
			ReferenceCountUtil.release(msg);
			System.out.format("Received bytes: %s%n", Hex.encodeHexString(out.toByteArray()));
		}finally{
			ctx.close();
			out.close();
		}
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace(System.err);
		ctx.close();
	}
	
	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		System.out.println("Channel is inactive");
	}
	
	@Override
	public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
		System.out.println("Channel is unregistered");
	}
}
