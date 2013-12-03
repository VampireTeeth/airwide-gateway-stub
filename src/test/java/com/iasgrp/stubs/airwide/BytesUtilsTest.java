package com.iasgrp.stubs.airwide;

import static org.junit.Assert.*;

import org.apache.commons.codec.binary.Hex;
import org.junit.Test;

import com.iasgrp.stubs.airwide.utils.BytesUtils;

public class BytesUtilsTest {
	@Test
	public void shouldReturnInteger() throws Exception {
		byte[] bytes =  new byte[4];
		bytes[0] = (byte)0;
		bytes[1] = (byte)0;
		bytes[2] = (byte)0;
		bytes[3] = (byte)4;
		System.out.println(BytesUtils.unsignedBytesToInt(bytes));
		
	}

}
