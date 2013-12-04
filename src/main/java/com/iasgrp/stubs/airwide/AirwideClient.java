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
	
	public void sendMessage(int opertionRef, byte messageType, byte operation, byte[] payload) throws IOException {
		ByteBuffer writeBuf = ByteBuffer.allocate(1024);
		writeBuf.putInt(Integer.reverseBytes(opertionRef));
		writeBuf.put(messageType);
		writeBuf.put(operation);
		writeBuf.putShort((short) 0);
		writeBuf.putShort(Short.reverseBytes((short)(payload.length)));
		writeBuf.put(payload);
		writeBuf.flip();
		
		sc.write(writeBuf);
//		
//		
	}
	
	public void receiveMessage() throws IOException {
		ByteBuffer readBuf = ByteBuffer.allocate(1024);
		int read = sc.read(readBuf);
		readBuf.flip();
		byte[] header = new byte[10];
		byte[] returnPayload = new byte[read - 10];
		readBuf.get(header);
		readBuf.get(returnPayload);
		System.out.format("Header: %s%n", Hex.encodeHexString(header));
		System.out.format("Payload: %s%n", Hex.encodeHexString(returnPayload));
	}
	
	public void sendMultipleByteBufferForOneMessage(int opertionRef, byte messageType, byte operation, byte[] payload) throws IOException, InterruptedException {
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
		AirwideClient client3 = new AirwideClient("localhost", 17000);
		
		try{
			client1.sendMessage(2, (byte)1, (byte)0, "Hello, World!".getBytes());
			client1.receiveMessage();
			client2.sendMultipleByteBufferForOneMessage(1, (byte)2, (byte)0, "Hello, World!".getBytes());
			client2.receiveMessage();
			
			ByteBuffer loginPayloadBuf = ByteBuffer.allocate(5);
			loginPayloadBuf.put((byte)4);
			loginPayloadBuf.putInt(Integer.reverseBytes(29));
			loginPayloadBuf.flip();
			byte[] loginPayload = new byte[5];
			loginPayloadBuf.get(loginPayload);
			client3.sendMessage(2, (byte)1, (byte)1, loginPayload);
			client3.receiveMessage();
		}finally {
			client1.tearDown();
			client2.tearDown();
		}
	}

}
