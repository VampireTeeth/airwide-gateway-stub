package com.iasgrp.stubs.airwide;

import static com.iasgrp.stubs.airwide.utils.BytesUtils.hexDump;


import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;

import com.iasgrp.stubs.airwide.message.AirwideHeader;
import com.iasgrp.stubs.airwide.message.AirwideProcessRequestInvoke;
import com.iasgrp.stubs.airwide.utils.ApplicationIdentifierFactory;
import com.iasgrp.stubs.airwide.utils.DialogReferenceFactory;
import com.iasgrp.stubs.airwide.utils.MsisdnFactory;
import com.iasgrp.stubs.airwide.utils.UssdStringFactory;

public class ProcessRequestInvokeChannelFutureListener implements ChannelFutureListener{

	private AirwideHeader header;
	private MsisdnFactory msisdnFactory;
	private DialogReferenceFactory dialogReferenceFactory;
	private UssdStringFactory ussdStringFactory;
	private ApplicationIdentifierFactory applicationIdentifierFactory;
	private ChannelHandlerContext ctx;

	ProcessRequestInvokeChannelFutureListener(AirwideHeader header, MsisdnFactory msisdnFactory, 
			DialogReferenceFactory dialogReferenceFactory, UssdStringFactory ussdStringFactory,
			ApplicationIdentifierFactory applicationIdentifierFactory, 
			ChannelHandlerContext ctx) {
		
		this.header = header;
		this.msisdnFactory = msisdnFactory;
		this.dialogReferenceFactory = dialogReferenceFactory;
		this.ussdStringFactory = ussdStringFactory;
		this.applicationIdentifierFactory = applicationIdentifierFactory;
		this.ctx = ctx;
	}

	@Override
	public void operationComplete(ChannelFuture future) throws Exception {
		future.removeListener(this);
		AirwideProcessRequestInvoke invoke = new AirwideProcessRequestInvoke();
		invoke.getHeader().setOperationReference(header.getOperationReference());;
		invoke.setMsisdn(msisdnFactory.newMsisdn());
		invoke.setDialogueReference(dialogReferenceFactory.newDialogueReference());
		invoke.setApplicationIdentifier(applicationIdentifierFactory.newApplicationIdentifier());
		invoke.setDataCodingScheme((byte) 0);
		invoke.setUssdString(ussdStringFactory.newUssdString());
		ByteBuf invokeBuf = invoke.toByteBuf();
		
		System.out.format("Sending process request invoke: %s%n", hexDump(invokeBuf));
		ctx.writeAndFlush(invokeBuf);
		
//		newFuture.addListener(new ProcessRequestInvokeChannelFutureListener(header, msisdnFactory, 
//				dialogReferenceFactory, ussdStringFactory, applicationIdentifierFactory, ctx));
	}

}