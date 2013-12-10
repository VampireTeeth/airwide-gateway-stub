package com.iasgrp.stubs.airwide;

import static com.iasgrp.stubs.airwide.AirwideConstants.MESSAGE_TYPE_INVOKE;
import static com.iasgrp.stubs.airwide.AirwideConstants.MESSAGE_TYPE_RESULT;
import static com.iasgrp.stubs.airwide.AirwideConstants.OPERATION_LOGIN;
import static com.iasgrp.stubs.airwide.AirwideConstants.OPERATION_PROCESS_USSDATA;
import static com.iasgrp.stubs.airwide.AirwideConstants.OPERATION_PROCESS_USSREQUEST;
import static com.iasgrp.stubs.airwide.AirwideConstants.OPERATION_USSEND;
import static com.iasgrp.stubs.airwide.AirwideConstants.OPERATION_USSNOTIFY;
import static com.iasgrp.stubs.airwide.AirwideConstants.OPERATION_USSREQUEST;
import static com.iasgrp.stubs.airwide.Factories.applicationidentifierFactory;
import static com.iasgrp.stubs.airwide.Factories.dialogReferenceFactory;
import static com.iasgrp.stubs.airwide.Factories.msisdnFactory;
import static com.iasgrp.stubs.airwide.Factories.ussdStringFactory;
import static com.iasgrp.stubs.airwide.utils.BytesUtils.hexDump;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

import com.iasgrp.stubs.airwide.message.AirwideLogin;
import com.iasgrp.stubs.airwide.message.AirwideProcessRequestInvoke;
import com.iasgrp.stubs.airwide.message.AirwideProcessRequestResult;
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
				System.out.format("Received process data: %s%n", hexDump(buf));
				sendRequestInvoke(ctx, dialogReferenceFactory().newDialogueReference());
				break;
			case OPERATION_PROCESS_USSREQUEST:
				System.out.format("Received process request: %s%n", hexDump(buf));
				switch(messageType) {
				case MESSAGE_TYPE_INVOKE:
					AirwideProcessRequestInvoke invoke = new AirwideProcessRequestInvoke();
					invoke.decode(buf);
					System.out.format("Parsed process request invoke: %s%n", invoke);
					break;
				case MESSAGE_TYPE_RESULT:
					AirwideProcessRequestResult result = new AirwideProcessRequestResult();
					result.decode(buf);
					System.out.format("Parsed process request result: %s%n", result);
					break;
				}
				sendRequestInvoke(ctx, dialogReferenceFactory().newDialogueReference());
				break;
			case OPERATION_USSEND:
				System.out.format("Received end: %s%n", hexDump(buf));
				sendRequestInvoke(ctx, dialogReferenceFactory().newDialogueReference());
//				ctx.close();
				break;
			case OPERATION_USSNOTIFY:
				System.out.format("Received notify: %s%n", hexDump(buf));
				sendRequestInvoke(ctx, dialogReferenceFactory().newDialogueReference());
				break;
			case OPERATION_USSREQUEST:
				System.out.format("Received request: %s%n", hexDump(buf));
				
				switch(messageType) {
				case MESSAGE_TYPE_INVOKE:
					AirwideRequestInvoke invoke = new AirwideRequestInvoke();
					invoke.decode(buf);
					System.out.format("Parsed request invoke: %s%n", invoke);
					sendRequestInvoke(ctx, invoke.getHeader().getOperationReference());
					break;
				case MESSAGE_TYPE_RESULT:
					break;
				}
				
				
				break;
			default:
				System.out.format("Unknown operation: %s%n", hexDump(buf));
			}
		}finally{
			ReferenceCountUtil.release(msg);
		}
		
	}

	private ChannelFuture sendRequestInvoke(ChannelHandlerContext ctx, int operationReference) throws Exception {
		AirwideProcessRequestInvoke newInvoke = new AirwideProcessRequestInvoke();
		newInvoke.getHeader().setOperationReference(operationReference);;
		newInvoke.setMsisdn(msisdnFactory().newMsisdn());
		newInvoke.setDialogueReference(dialogReferenceFactory().newDialogueReference());
		newInvoke.setApplicationIdentifier(applicationidentifierFactory().newApplicationIdentifier());
		newInvoke.setDataCodingScheme((byte) 0);
		newInvoke.setUssdString(ussdStringFactory().newUssdString());
		ByteBuf newBuf = newInvoke.encode();
		return ctx.writeAndFlush(newBuf);
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
