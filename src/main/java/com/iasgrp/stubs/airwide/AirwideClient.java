package com.iasgrp.stubs.airwide;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

import org.apache.commons.codec.binary.Hex;


public class AirwideClient {
	private SocketChannel sc;
	
	public AirwideClient(String ip, int port) throws IOException {
		sc = SocketChannel.open();
		sc.socket().connect(new InetSocketAddress(ip, port));
	}
	
	public void testMessage(int opertionRef, byte messageType, byte operation, byte[] payload) throws IOException {
		ByteBuffer writeBuf = ByteBuffer.allocate(1024);
		writeBuf.putInt(opertionRef);
		writeBuf.put(messageType);
		writeBuf.put(operation);
		writeBuf.putShort((short) 0);
		writeBuf.putShort((short)(payload.length));
		writeBuf.put(payload);
		writeBuf.flip();
		
		sc.write(writeBuf);
		ByteBuffer readBuf = ByteBuffer.allocate(1024);
		
		int read = sc.read(readBuf);
		readBuf.flip();
		byte[] header = new byte[10];
		byte[] returnPayload = new byte[read - 10];
		readBuf.get(header);
		readBuf.get(returnPayload);
		System.out.format("Header: %s%n", Hex.encodeHexString(header));
		System.out.format("Payload: %s%n", new String(returnPayload));
		
	}
	
	public void testMultipleByteBufferForOneMessage(int opertionRef, byte messageType, byte operation, byte[] payload) throws IOException, InterruptedException {
		ByteBuffer writeBuf1 = ByteBuffer.allocate(256);
		ByteBuffer writeBuf2 = ByteBuffer.allocate(256);
		ByteBuffer writeBuf3 = ByteBuffer.allocate(256);
		writeBuf1.putInt(opertionRef);
		writeBuf1.put(messageType);
		writeBuf1.put(operation);
		writeBuf2.putShort((short) 0);
		writeBuf2.putShort((short)(payload.length));
		writeBuf3.put(payload);
		writeBuf1.flip();
		writeBuf2.flip();
		writeBuf3.flip();
		
		sc.write(writeBuf1);
		Thread.sleep(1000);
		sc.write(writeBuf2);
		Thread.sleep(1000);
		sc.write(writeBuf3);
		ByteBuffer readBuf = ByteBuffer.allocate(1024);
		
		int read = sc.read(readBuf);
		readBuf.flip();
		byte[] header = new byte[10];
		byte[] returnPayload = new byte[read - 10];
		readBuf.get(header);
		readBuf.get(returnPayload);
		System.out.format("Header: %s%n", Hex.encodeHexString(header));
		System.out.format("Payload: %s%n", new String(returnPayload));
	}
	
	public static void main(String[] args) throws Exception {
		AirwideClient airwideClient = new AirwideClient("localhost", 17000);
//		airwideClient.testMessage(0, (byte)0, (byte)0, "Hello, World!".getBytes());
		airwideClient.testMultipleByteBufferForOneMessage(0, (byte)0, (byte)0, "Hello, World!".getBytes());
	}

}
