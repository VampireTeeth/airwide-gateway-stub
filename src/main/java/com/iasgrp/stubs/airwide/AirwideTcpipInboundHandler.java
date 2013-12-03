package com.iasgrp.stubs.airwide;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import com.iasgrp.stubs.airwide.message.AirwideHeader;

public class AirwideTcpipInboundHandler extends ChannelInboundHandlerAdapter{
	
	private final static int TCPIP_HEADER_LENGTH = AirwideConstants.TCPIP_HEADER_LENGTH;

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		ByteBuf buf = (ByteBuf)msg;
		AirwideHeader header = new AirwideHeader();
		System.out.println("Parsing header");
		int operationReference = Integer.reverseBytes(buf.readInt());
		byte messageType = buf.readByte();
		byte operation = buf.readByte();
		buf.skipBytes(2);
		short overallMessageLength = Short.reverseBytes(buf.readShort());
		
		header.setOperationReference(operationReference);
		header.setOperation(operation);
		header.setMessageType(messageType);
		header.setOverallMessageLength(overallMessageLength);
		
		System.out.println("Parsed: " + header.toString());
		
//		int readableBytes = buf.readableBytes();
			
//		byte[] header = new byte[TCPIP_HEADER_LENGTH];
//		buf.readBytes(header);
//		System.out.format("Header: %s%n", Hex.encodeHexString(header));
//		if(readableBytes > TCPIP_HEADER_LENGTH) {
//			byte[] payload = new byte[readableBytes - TCPIP_HEADER_LENGTH];
//			buf.readBytes(payload);
//			System.out.format("Payload: %s%n", Hex.encodeHexString(payload));
//		}
//		
		
	}
	
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		System.out.println("AirwideTcpipInboundHandler.channelActive()");
	}
	
	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		System.out.println("AirwideTcpipInboundHandler.channelInactive()");
	}
	
}
