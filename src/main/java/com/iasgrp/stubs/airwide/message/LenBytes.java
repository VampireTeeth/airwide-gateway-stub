package com.iasgrp.stubs.airwide.message;

public class LenBytes {

	private byte len;
	private byte[] bytes;

	public LenBytes(byte len, byte[] bytes) {
		this.len = len;
		this.bytes = bytes;
	}

	public byte getLen() {
		return len;
	}

	public void setLen(byte len) {
		this.len = len;
	}

	public byte[] getBytes() {
		return bytes;
	}

	public void setBytes(byte[] bytes) {
		this.bytes = bytes;
	}

}
