package com.iasgrp.stubs.airwide;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;


public class AirwideClient {
	private SocketChannel sc;
	
	public AirwideClient(String ip, int port) throws IOException {
		sc = SocketChannel.open();
		sc.socket().connect(new InetSocketAddress(ip, port));
	}
	
	public void testMessage(int opertionRef, byte messageType, byte operation, byte[] payload) throws IOException {
		ByteBuffer writeBuf = ByteBuffer.allocate(1024);
		writeBuf.putInt(Integer.reverseBytes(opertionRef));
		writeBuf.put(messageType);
		writeBuf.put(operation);
		writeBuf.putShort((short) 0);
		writeBuf.putShort(Short.reverseBytes((short)(payload.length)));
		writeBuf.put(payload);
		writeBuf.flip();
		
		sc.write(writeBuf);
//		ByteBuffer readBuf = ByteBuffer.allocate(1024);
		
//		int read = sc.read(readBuf);
//		readBuf.flip();
//		byte[] header = new byte[10];
//		byte[] returnPayload = new byte[read - 10];
//		readBuf.get(header);
//		readBuf.get(returnPayload);
//		System.out.format("Header: %s%n", Hex.encodeHexString(header));
//		System.out.format("Payload: %s%n", new String(returnPayload));
//		
	}
	
	public void testMultipleByteBufferForOneMessage(int opertionRef, byte messageType, byte operation, byte[] payload) throws IOException, InterruptedException {
		ByteBuffer writeBuf1 = ByteBuffer.allocate(256);
		ByteBuffer writeBuf2 = ByteBuffer.allocate(256);
		ByteBuffer writeBuf3 = ByteBuffer.allocate(256);
		writeBuf1.putInt(Integer.reverseBytes(opertionRef));
		writeBuf1.put(messageType);
		writeBuf1.put(operation);
		writeBuf2.putShort((short) 0);
		writeBuf2.putShort(Short.reverseBytes((short)(payload.length)));
		writeBuf3.put(payload);
		writeBuf1.flip();
		writeBuf2.flip();
		writeBuf3.flip();
		
		sc.write(writeBuf1);
		Thread.sleep(1000);
		sc.write(writeBuf2);
		Thread.sleep(1000);
		sc.write(writeBuf3);
//		ByteBuffer readBuf = ByteBuffer.allocate(1024);
		
//		int read = sc.read(readBuf);
//		readBuf.flip();
//		byte[] header = new byte[10];
//		byte[] returnPayload = new byte[read - 10];
//		readBuf.get(header);
//		readBuf.get(returnPayload);
//		System.out.format("Header: %s%n", Hex.encodeHexString(header));
//		System.out.format("Payload: %s%n", new String(returnPayload));
	}
	
	void tearDown() throws IOException {
		this.sc.close();
	}
	
	public static void main(String[] args) throws Exception {
		AirwideClient client1 = new AirwideClient("localhost", 17000);
		AirwideClient client2 = new AirwideClient("localhost", 17000);
		
		try{
			client1.testMessage(2, (byte)1, (byte)3, "Hello, World!".getBytes());
			client2.testMultipleByteBufferForOneMessage(1, (byte)2, (byte)3, "Hello, World!".getBytes());
		}finally {
			client1.tearDown();
			client2.tearDown();
		}
	}

}
