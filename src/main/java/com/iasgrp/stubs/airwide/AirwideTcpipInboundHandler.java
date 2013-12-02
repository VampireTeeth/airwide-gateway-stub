package com.iasgrp.stubs.airwide;

import org.apache.commons.codec.binary.Hex;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class AirwideTcpipInboundHandler extends ChannelInboundHandlerAdapter{
	
	private final static int TCPIP_HEADER_LENGTH = AirwideConstants.TCPIP_HEADER_LENGTH;

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		ByteBuf buf = (ByteBuf)msg;
		int readableBytes = buf.readableBytes();
			
		byte[] header = new byte[TCPIP_HEADER_LENGTH];
		buf.readBytes(header);
		System.out.format("Header: %s%n", Hex.encodeHexString(header));
		if(readableBytes > TCPIP_HEADER_LENGTH) {
			byte[] payload = new byte[readableBytes - TCPIP_HEADER_LENGTH];
			buf.readBytes(payload);
			System.out.format("Payload: %s%n", new String(payload));
		}
		
	}
	
}
