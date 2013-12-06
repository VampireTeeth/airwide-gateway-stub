package com.iasgrp.stubs.airwide;

import static junit.framework.Assert.assertEquals;

import java.nio.ByteBuffer;

import org.apache.commons.codec.binary.Hex;
import org.junit.Before;
import org.junit.Test;

import com.iasgrp.stubs.airwide.utils.BytesUtils;

public class BytesUtilsTest {
	private final static int NEG_TEST_INTEGER = -287;
	private final static int TEST_INTEGER = 6;
	private ByteBuffer buf;
	
	@Before
	public void setUp() throws Exception {
		buf = ByteBuffer.allocate(10);
	}
	
	@Test
	public void shouldParseUnsignedBytesToInt() throws Exception {
		byte[] bytes = positiveIntegerBytes();
		buf.get(bytes);
		assertEq(TEST_INTEGER , BytesUtils.unsignedBytesToInt(bytes));
	}

	
	@Test
	public void shouldParseUnsignedBytesToIntReversedGivenNegTestInteger() throws Exception {
		byte[] bytes = negativeIntegerBytes();
		buf.get(bytes);
		assertEq(Integer.reverseBytes(NEG_TEST_INTEGER), BytesUtils.unsignedBytesToIntReversed(bytes));
	}
	
	@Test
	public void shouldParseUnsignedBytesToIntReversed() throws Exception {
		byte[] bytes = positiveIntegerBytes();
		buf.get(bytes);
		assertEq(Integer.reverseBytes(TEST_INTEGER), BytesUtils.unsignedBytesToIntReversed(bytes));
	}
	
	@Test
	public void shouldParseEncodedOctString() throws Exception {
		byte[] bytes = "Hello,world!".getBytes();
		System.out.println(Hex.encodeHexString(bytes));
		System.out.println(Hex.encodeHexString(BytesUtils.encodeOctetString(bytes)));
		System.out.println(Hex.encodeHexString(BytesUtils.encodeBytes(bytes)));
		
	}

	private <T> void assertEq(T expected, T actual) {
		System.out.format("Expected = %s, Actual = %s%n", expected, actual);
		assertEquals(expected, actual);
	}
	
	private byte[] positiveIntegerBytes() {
		return intBytes0(TEST_INTEGER);
	}

	private byte[] negativeIntegerBytes() {
		return intBytes0(NEG_TEST_INTEGER);
	}

	private byte[] intBytes0(int v) {
		buf.putInt(v);
		buf.flip();
		byte[] bytes = new byte[4];
		return bytes;
	}
	
}
