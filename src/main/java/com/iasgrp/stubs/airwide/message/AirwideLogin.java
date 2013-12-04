package com.iasgrp.stubs.airwide.message;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

public class AirwideLogin {

	private AirwideHeader header;
	private byte applicationIdentifierLen = 4;
	private int applicationIdentifier = 0;

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
		ByteBuf buf = Unpooled.buffer();
		buf.writeBytes(header.toByteBuf());
		buf.writeByte(applicationIdentifierLen);
		buf.writeInt(Integer.reverseBytes(applicationIdentifier));
		return buf;
	}
	

}
