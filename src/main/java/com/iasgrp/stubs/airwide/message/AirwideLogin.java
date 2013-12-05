package com.iasgrp.stubs.airwide.message;

import static com.iasgrp.stubs.airwide.AirwideConstants.OPERATION_LOGIN;

import com.iasgrp.stubs.airwide.AirwideConstants;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

public class AirwideLogin {

	private AirwideHeader header = new AirwideHeader();
	private byte applicationIdentifierLen = 4;
	private int applicationIdentifier = 0;

	public AirwideLogin() {
		header.setOperation(OPERATION_LOGIN);
	}
	
	public AirwideHeader getHeader() {
		return header;
	}

	public void setHeader(AirwideHeader header) {
		this.header = header;
	}

	public byte getApplicationIdentifierLen() {
		return applicationIdentifierLen;
	}

	public void setApplicationIdentifierLen(byte applicationIdentifierLen) {
		this.applicationIdentifierLen = applicationIdentifierLen;
	}

	public int getApplicationIdentifier() {
		return applicationIdentifier;
	}

	public void setApplicationIdentifier(int applicationIdentifier) {
		this.applicationIdentifier = applicationIdentifier;
	}

	@Override
	public String toString() {
		return "AirwideLogin [header=" + header + ", applicationIdentifierLen=" + applicationIdentifierLen + ", applicationIdentifier=" + applicationIdentifier + "]";
	}
	
	public ByteBuf toByteBuf() {
		ByteBuf bodyBuf = Unpooled.buffer();
		bodyBuf.writeByte(applicationIdentifierLen);
		bodyBuf.writeInt(Integer.reverseBytes(applicationIdentifier));
		header.setOverallMessageLength((short)bodyBuf.readableBytes());
		return Unpooled.copiedBuffer(header.toByteBuf(), bodyBuf);
	}
	

}
