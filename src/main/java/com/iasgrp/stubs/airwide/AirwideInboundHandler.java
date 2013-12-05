package com.iasgrp.stubs.airwide;

import static com.iasgrp.stubs.airwide.AirwideConstants.OPERATION_LOGIN;
import static com.iasgrp.stubs.airwide.AirwideConstants.OPERATION_PROCESS_USSDATA;
import static com.iasgrp.stubs.airwide.AirwideConstants.OPERATION_PROCESS_USSREQUEST;
import static com.iasgrp.stubs.airwide.AirwideConstants.OPERATION_USSEND;
import static com.iasgrp.stubs.airwide.AirwideConstants.OPERATION_USSNOTIFY;
import static com.iasgrp.stubs.airwide.AirwideConstants.OPERATION_USSREQUEST;
import static com.iasgrp.stubs.airwide.Factories.*;
import static com.iasgrp.stubs.airwide.utils.BytesUtils.*;

import org.apache.commons.codec.binary.Hex;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
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
				ByteBuf loginBuf = login.toByteBuf();
				System.out.format("Hex dump login: %s%n", hexDump(loginBuf));
				ChannelFuture future = echo(ctx, buf);
				future.addListener(new ProcessRequestInvokeChannelFutureListener(header, 
						msisdnFactory(), 
						dialogReferenceFactory(), 
						ussdStringFactory(), 
						applicationidentifierFactory(), 
						ctx));
				break;
			case OPERATION_PROCESS_USSDATA:
				break;
			case OPERATION_PROCESS_USSREQUEST:
				System.out.format("Received process request result: %s%n", hexDump(buf));
				break;
			case OPERATION_USSEND:
				System.out.format("Received end result: %s%n", hexDump(buf));
				break;
			case OPERATION_USSNOTIFY:
				break;
			case OPERATION_USSREQUEST:
				break;
			default:
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
