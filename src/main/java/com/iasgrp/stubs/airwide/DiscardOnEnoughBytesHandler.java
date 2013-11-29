package com.iasgrp.stubs.airwide;

import java.io.ByteArrayOutputStream;

import org.apache.commons.codec.binary.Hex;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

public class DiscardOnEnoughBytesHandler extends ChannelInboundHandlerAdapter{

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		ByteBuf buf = (ByteBuf)msg;
		if(buf.readableBytes() > 20) {
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			buf.readBytes(out, buf.readableBytes());
			System.out.format("Received hex:%s%n", Hex.encodeHexString(out.toByteArray()));
			ReferenceCountUtil.release(msg);
			ctx.close();
		}else {
			System.out.format("No enough bytes received, will wait for more to discard.%n");
		}
	}
	
}
