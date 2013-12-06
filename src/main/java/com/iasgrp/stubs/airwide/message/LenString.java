package com.iasgrp.stubs.airwide.message;

public class LenString {

	private byte len;
	private String str;

	public LenString(byte len, String str) {
		this.len = len;
		this.str = str;
	}

	public byte getLen() {
		return len;
	}

	public void setLen(byte len) {
		this.len = len;
	}

	public String getStr() {
		return str;
	}

	public void setStr(String str) {
		this.str = str;
	}

}
