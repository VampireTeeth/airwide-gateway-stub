package com.iasgrp.stubs.airwide;

import static com.iasgrp.stubs.airwide.AirwideConstants.MESSAGE_TYPE_INVOKE;
import static com.iasgrp.stubs.airwide.AirwideConstants.MESSAGE_TYPE_RESULT;
import static com.iasgrp.stubs.airwide.AirwideConstants.OPERATION_LOGIN;
import static com.iasgrp.stubs.airwide.AirwideConstants.OPERATION_PROCESS_USSDATA;
import static com.iasgrp.stubs.airwide.AirwideConstants.OPERATION_PROCESS_USSREQUEST;
import static com.iasgrp.stubs.airwide.AirwideConstants.OPERATION_USSEND;
import static com.iasgrp.stubs.airwide.AirwideConstants.OPERATION_USSNOTIFY;
import static com.iasgrp.stubs.airwide.AirwideConstants.OPERATION_USSREQUEST;
import static com.iasgrp.stubs.airwide.Factories.*;
import static com.iasgrp.stubs.airwide.utils.BytesUtils.*;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

import com.iasgrp.stubs.airwide.message.AirwideLogin;
import com.iasgrp.stubs.airwide.message.AirwideProcessRequestInvoke;
import com.iasgrp.stubs.airwide.message.AirwideRequestInvoke;

public class AirwideInboundHandler extends ChannelInboundHandlerAdapter{
	
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		ByteBuf buf = (ByteBuf)msg;
		buf.markReaderIndex();
		try{
			byte operation = buf.getByte(buf.readerIndex() + 5);
			byte messageType = buf.getByte(buf.readerIndex() + 4);
			switch(operation){
			case OPERATION_LOGIN:
				AirwideLogin login = parseLogin(buf);
				ByteBuf loginBuf = login.encode();
				System.out.format("Hex dump login: %s%n", hexDump(loginBuf));
				ChannelFuture future = echo(ctx, buf);
				future.addListener(new ProcessRequestInvokeChannelFutureListener(login.getHeader(), 
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
				ctx.close();
				break;
			case OPERATION_USSNOTIFY:
				break;
			case OPERATION_USSREQUEST:
				System.out.format("Received request result: %s%n", hexDump(buf));
				
				switch(messageType) {
				case MESSAGE_TYPE_INVOKE:
					AirwideRequestInvoke invoke = new AirwideRequestInvoke();
					invoke.decode(buf);
					System.out.format("Parsed request invoke: %s%n", invoke);
					AirwideProcessRequestInvoke newInvoke = new AirwideProcessRequestInvoke();
					newInvoke.getHeader().setOperationReference(invoke.getHeader().getOperationReference());;
					newInvoke.setMsisdn(msisdnFactory().newMsisdn());
					newInvoke.setDialogueReference(dialogReferenceFactory().newDialogueReference());
					newInvoke.setApplicationIdentifier(applicationidentifierFactory().newApplicationIdentifier());
					newInvoke.setDataCodingScheme((byte) 0);
					newInvoke.setUssdString(ussdStringFactory().newUssdString());
					ByteBuf invokeBuf = invoke.encode();
					ctx.writeAndFlush(invokeBuf);
					break;
				case MESSAGE_TYPE_RESULT:
					break;
				}
				
				
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

	private AirwideLogin parseLogin(ByteBuf buf) throws Exception {
		AirwideLogin login = new AirwideLogin();
		login.decode(buf);
		System.out.println("Parsed login: " + login.toString());
		return login;
	}
}
