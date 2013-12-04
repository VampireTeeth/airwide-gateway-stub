package com.iasgrp.stubs.airwide;

import static com.iasgrp.stubs.airwide.AirwideConstants.*;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

import com.iasgrp.stubs.airwide.message.AirwideHeader;
import com.iasgrp.stubs.airwide.message.AirwideLogin;

public class AirwideInboundHandler extends ChannelInboundHandlerAdapter{
	
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		ByteBuf buf = (ByteBuf)msg;
		buf.markReaderIndex();
		try{
			AirwideHeader header = parseHeader(buf);
			switch(header.getOperation()){
			case OPERATION_LOGIN:
				AirwideLogin login = parseLogin(buf, header);
				ChannelFuture future = echo(ctx, buf);
				future.addListener(new ChannelFutureListener() {
					
					@Override
					public void operationComplete(ChannelFuture future) throws Exception {
						
					}
				});
				break;
			case OPERATION_PROCESS_USSDATA:
				echo(ctx, buf);
				break;
			case OPERATION_PROCESS_USSREQUEST:
				echo(ctx, buf);
				break;
			case OPERATION_USSEND:
				echo(ctx, buf);
				break;
			case OPERATION_USSNOTIFY:
				echo(ctx, buf);
				break;
			case OPERATION_USSREQUEST:
				echo(ctx, buf);
				break;
			default:
				echo(ctx, buf);
			}
		}finally{
			ReferenceCountUtil.release(msg);
		}
		
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		System.out.println("AirwideTcpipInboundHandler.channelActive()");
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		System.out.println("AirwideTcpipInboundHandler.channelInactive()");
	}

	private ChannelFuture echo(ChannelHandlerContext ctx, ByteBuf buf) {
		buf.resetReaderIndex();
		ByteBuf retbuf = buf.copy();
		return ctx.writeAndFlush(retbuf);
	}

	private AirwideLogin parseLogin(ByteBuf buf, AirwideHeader header) {
		byte applicationIdentifierLen = buf.readByte();
		int  applicationIdentifier = Integer.reverseBytes(buf.readInt());
		AirwideLogin login = new AirwideLogin();
		login.setHeader(header);
		login.setApplicationIdentifier(applicationIdentifier);
		login.setApplicationIdentifierLen(applicationIdentifierLen);
		System.out.println("Parsed login: " + login.toString());
		return login;
	}

	private AirwideHeader parseHeader(ByteBuf buf) {
		AirwideHeader header = new AirwideHeader();
		int operationReference = Integer.reverseBytes(buf.readInt());
		byte messageType = buf.readByte();
		byte operation = buf.readByte();
		buf.skipBytes(2);
		short overallMessageLength = Short.reverseBytes(buf.readShort());
		
		header.setOperationReference(operationReference);
		header.setOperation(operation);
		header.setMessageType(messageType);
		header.setOverallMessageLength(overallMessageLength);
		
		System.out.println("Parsed header: " + header.toString());
		return header;
	}
	
}
