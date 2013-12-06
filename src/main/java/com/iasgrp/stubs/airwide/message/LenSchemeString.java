package com.iasgrp.stubs.airwide.message;

public class LenSchemeString {

	private byte len;
	private byte scheme;
	private String str;

	public LenSchemeString(byte len, byte scheme, String str) {
		this.len = len;
		this.scheme = scheme;
		this.str = str;
	}

	public byte getScheme() {
		return scheme;
	}

	public void setScheme(byte scheme) {
		this.scheme = scheme;
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
