package com.iasgrp.stubs.airwide;

import static org.apache.commons.codec.binary.Hex.decodeHex;
import io.netty.buffer.Unpooled;

import org.apache.commons.codec.DecoderException;
import org.junit.Before;
import org.junit.Test;

import com.iasgrp.stubs.airwide.message.AirwideRequestInvoke;

public class AirwideRequestInvokeTest {
	
	private static final String HEX_DUMP_STR = "632e980a000400008900040000000004632e980a0891111654126868f2000000010f6fad96b43c4687e5e772aba588b9404379999ca6bf8865719a0e1a86e56485cc059a8ed165729d5d2683a4e5313a2c3f97153317c8faae8fd165b982e60241c3796898ada8b940c4b4bc3ca6838865719aaeb0b9404379999ca637cbb2aae2e60221d373fa5b9e57c0602e10f2dd2e0300";
	private static final byte[] HEX_DUMP;
	
	static {
		try {
			HEX_DUMP = decodeHex(HEX_DUMP_STR.toCharArray());
		} catch (DecoderException e) {
			throw new ExceptionInInitializerError(e);
		}
	}
	private AirwideRequestInvoke invoke;
	
	@Before
	public void setUp() throws Exception { 
		invoke = new AirwideRequestInvoke();
	}
	
	@Test
	public void shouldDecodeHexDump() throws Exception {
		invoke.decode(Unpooled.copiedBuffer(HEX_DUMP));
		System.out.println(invoke.toString());
	}

}
