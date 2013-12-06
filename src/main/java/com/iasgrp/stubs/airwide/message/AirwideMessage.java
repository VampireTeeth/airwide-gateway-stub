package com.iasgrp.stubs.airwide.message;

import io.netty.buffer.ByteBuf;

public interface AirwideMessage {

	void decode(ByteBuf buf) throws Exception;
	
	ByteBuf encode() throws Exception;
}
