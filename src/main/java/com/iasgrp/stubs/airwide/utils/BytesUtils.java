// Copyright (c) 2002 InterAcct Solutions Services
package com.iasgrp.stubs.airwide.utils;

import static org.apache.commons.codec.binary.Hex.encodeHexString;

import com.iasgrp.stubs.airwide.message.LenBytes;
import com.iasgrp.stubs.airwide.message.LenSchemeString;
import com.iasgrp.stubs.airwide.message.LenString;

import io.netty.buffer.ByteBuf;

/**
 * A Class class.
 * <P>
 * 
 * @author JLM
 */
public class BytesUtils extends Object {
	static final int END_OCTET_STRING = 15;
	static final long LONG_HIGH_ORDER_VALUE = 0X100000000L;
	static final long INT_HIGH_ORDER_VALUE = 0X10000L;
	static final int MAX_DUMP_LENGTH = 600;
	private static final int CHARIAGE_RETURN_BYTE = 13;

	/**
	 * Constructor
	 */
	public BytesUtils() {
	}

	public static int unsignedByteToInt(byte b) {
		return (int) b & 0xFF;
	}

	public static short unsignedByteToShort(byte b) {
		return (short) (b & 0xFF);
	}

	public static String byteToBits(byte b) {
		int i = b & 0xFF;
		String bitString = Integer.toBinaryString(i);
		while (bitString.length() < 8)
			bitString = "0" + bitString;
		return bitString;

	}

	public static String byteToHex(byte b) {
		int i = b & 0xFF;
		String hexString = Integer.toHexString(i);
		if (hexString.length() == 1)
			hexString = "0" + hexString;
		return hexString;
	}

	public static long unsignedIntToLong(int i) {
		if (i < 0)
			return LONG_HIGH_ORDER_VALUE + i;
		else
			return (long) i;
	}

	public static int unsignedShortToInt(short i) {
		if (i < 0)
			return (short) (INT_HIGH_ORDER_VALUE + i);
		else
			return (int) i;
	}

	public static byte[] unsignedIntToBytes(int intInput) {
		byte byteStr[] = { 0, 0, 0, 0 };
		long theIntInput = BytesUtils.unsignedIntToLong(intInput);

		if (theIntInput > 0Xffffff) {
			byteStr[0] = (byte) (theIntInput / 0X1000000L);
			theIntInput = theIntInput % 0X1000000L;
		}

		if (theIntInput > 0Xffff) {
			byteStr[1] = (byte) (theIntInput / 0X10000L);
			theIntInput = theIntInput % 0X10000L;
		}

		if (theIntInput > 0Xff) {
			byteStr[2] = (byte) (theIntInput / 0X100L);
			theIntInput = theIntInput % 0X100L;
		}

		byteStr[3] = (byte) (theIntInput);

		return byteStr;
	}

	public static byte[] unsignedShortToBytesReversed(short shortInput) {
		byte byteStr[] = BytesUtils.unsignedShortToBytes(shortInput);
		byte byteStrReversed[] = new byte[2];

		byteStrReversed[0] = byteStr[1];
		byteStrReversed[1] = byteStr[0];

		return byteStrReversed;
	}

	public static byte[] unsignedIntToBytesReversed(int intInput) {
		byte byteStr[] = BytesUtils.unsignedIntToBytes(intInput);
		byte byteStrReversed[] = new byte[4];

		byteStrReversed[0] = byteStr[3];
		byteStrReversed[1] = byteStr[2];
		byteStrReversed[2] = byteStr[1];
		byteStrReversed[3] = byteStr[0];

		return byteStrReversed;
	}

	public static byte[] unsignedShortToBytesRaw(short shortInput) {
		byte byteStr[] = { 0, 0 };
		int theShortInput = BytesUtils.unsignedShortToInt(shortInput);

		byteStr[0] = (byte) (theShortInput / 0X100L);
		theShortInput = (short) (theShortInput % 0X100L);
		byteStr[1] = (byte) (theShortInput);

		return byteStr;
	}

	public static byte[] unsignedShortToBytes(short shortInput) {
		byte byteStr[] = { 0, 0 };
		int theShortInput = BytesUtils.unsignedShortToInt(shortInput);

		if (theShortInput > 0Xff) {
			byteStr[0] = (byte) (theShortInput / 0X100L);
			theShortInput = (short) (theShortInput % 0X100L);
		}

		byteStr[1] = (byte) (theShortInput);

		return byteStr;
	}

	public static boolean compareBytesToInt(int intInput, byte[] inputBytes) {
		int checkInt = unsignedBytesToInt(inputBytes);
		return (checkInt == intInput);
	}

	public static int unsignedBytesToIntReversed(byte[] inputBytes) {
		int checkInt = 0;
		int pos = 3;

		if (inputBytes.length < 0)
			return 0;

		if (inputBytes.length == 2) {
			pos = 1;
			checkInt += BytesUtils.unsignedByteToInt(inputBytes[pos--]) << 8;
			checkInt += BytesUtils.unsignedByteToInt(inputBytes[pos--]) << 0;
		} else {
			pos = 3;
			checkInt += BytesUtils.unsignedByteToInt(inputBytes[pos--]) << 24;
			checkInt += BytesUtils.unsignedByteToInt(inputBytes[pos--]) << 16;
			checkInt += BytesUtils.unsignedByteToInt(inputBytes[pos--]) << 8;
			checkInt += BytesUtils.unsignedByteToInt(inputBytes[pos--]) << 0;
		}

		return checkInt;
	}

	public static long unsignedBytesToLongReversed(byte[] inputBytes) {
		long checkLong = 0;
		int pos = 7;

		if (inputBytes.length < 8)
			return 0;

		checkLong += BytesUtils.unsignedByteToInt(inputBytes[pos--]) << 56;
		checkLong += BytesUtils.unsignedByteToInt(inputBytes[pos--]) << 48;
		checkLong += BytesUtils.unsignedByteToInt(inputBytes[pos--]) << 40;
		checkLong += BytesUtils.unsignedByteToInt(inputBytes[pos--]) << 32;
		checkLong += BytesUtils.unsignedByteToInt(inputBytes[pos--]) << 24;
		checkLong += BytesUtils.unsignedByteToInt(inputBytes[pos--]) << 16;
		checkLong += BytesUtils.unsignedByteToInt(inputBytes[pos--]) << 8;
		checkLong += BytesUtils.unsignedByteToInt(inputBytes[pos--]) << 0;

		return checkLong;
	}

	public static int unsignedBytesToInt(byte[] inputBytes) {
		int checkInt = 0;
		int pos = 0;

		if (inputBytes.length < 0)
			return 0;

		if (inputBytes.length == 2) {
			checkInt += BytesUtils.unsignedByteToInt(inputBytes[pos++]) << 8;
			checkInt += BytesUtils.unsignedByteToInt(inputBytes[pos++]) << 0;
		} else {
			checkInt += BytesUtils.unsignedByteToInt(inputBytes[pos++]) << 24;
			checkInt += BytesUtils.unsignedByteToInt(inputBytes[pos++]) << 16;
			checkInt += BytesUtils.unsignedByteToInt(inputBytes[pos++]) << 8;
			checkInt += BytesUtils.unsignedByteToInt(inputBytes[pos++]) << 0;
		}

		return checkInt;
	}

	public static short unsignedBytesToShortReversed(byte[] inputBytes) {
		short checkShort = 0;

		if (inputBytes.length < 0)
			return 0;

		checkShort += BytesUtils.unsignedByteToInt(inputBytes[1]) << 8;
		checkShort += BytesUtils.unsignedByteToInt(inputBytes[0]) << 0;

		return checkShort;
	}

	public static short unsignedBytesToShort(byte[] inputBytes) {
		short checkShort = 0;

		if (inputBytes.length < 0)
			return 0;

		checkShort += BytesUtils.unsignedByteToInt(inputBytes[0]) << 8;
		checkShort += BytesUtils.unsignedByteToInt(inputBytes[1]) << 0;

		return checkShort;
	}

	public static String stringToHex(String theString) {
		byte[] theByteArray = theString.getBytes();
		String hexString = "";

		for (int i = 0; i < theString.length(); i++) {
			hexString += BytesUtils.byteToHex(theByteArray[i]);
		}
		return hexString;
	}

	public static String stringToHex(byte[] theByteArray) {
		String hexString = "";

		for (int i = 0; i < theByteArray.length; i++) {
			hexString += BytesUtils.byteToHex(theByteArray[i]);
		}
		return hexString;
	}

	public static void main(String[] args) {
		byte[] transactionIdBytes = new byte[4];
		byte maxByte = (byte) 0xff;
		int transactionId = 0;

		transactionIdBytes[0] = maxByte;
		transactionIdBytes[1] = maxByte;
		transactionIdBytes[2] = maxByte;
		transactionIdBytes[3] = maxByte;
		transactionId = BytesUtils.unsignedBytesToInt(transactionIdBytes);

		System.out.println("Tran ID: " + transactionId + " (" + Integer.toHexString(transactionId));
	}

	public static byte[] encodeOctetString(byte[] decodedBytes) {
		int idx = 0;
		int outIdx = 0;

		byte[] getEncodedOctetBytes = new byte[((decodedBytes.length + 1) / 2)];

		for (idx = 0; idx < decodedBytes.length; idx++) {
			if ((idx % 2) == 0)
				getEncodedOctetBytes[outIdx] += (byte) decodedBytes[idx] & 0X0F;
			else {
				getEncodedOctetBytes[outIdx] += (byte) ((decodedBytes[idx] & 0X0F) << 4);
				outIdx++;
			}
		}
		if ((idx % 2) > 0)
			getEncodedOctetBytes[getEncodedOctetBytes.length - 1] += (byte) (END_OCTET_STRING << 4);

		return getEncodedOctetBytes;
	}

	public static  String  decodeOctetString(byte[] encodedString)
	  {
	    int      idx        = 0;
	    byte     highOrderByte;
	    byte     lowOrderByte;
	    String   resultDecodedString = "";
	
	    for (idx = 0; idx < encodedString.length;  idx++)
	    {
	      highOrderByte = (byte)((encodedString[idx] & 0xF0) >> 4);
	      lowOrderByte  = (byte)(encodedString[idx] & 0x0F);
	      resultDecodedString += Byte.toString(lowOrderByte);
	      if (highOrderByte == END_OCTET_STRING)
	          break;
	      resultDecodedString += Byte.toString(highOrderByte);
	    }
	    return resultDecodedString;
	  }

	public static  byte[]  decodeBytes(byte[] encodedString)
	  {
	    int      idx            = 0;
	    int      outIdx         = 0;
	    int      maskIdx        = 0;
	    byte[]   maskLowByte    = {127,(byte)-130,(byte)-132,(byte)-136,(byte)-144,(byte)-160,(byte)-192};
	    byte[]   maskHighByte   = {0, 1, 3, 7, 15, 31, 63};
	
	    byte[]   decodedBytes  = new byte[encodedString.length + (encodedString.length / 7)];
	
	    for (idx = 0; idx < encodedString.length; outIdx++, idx++)
	    {
	      decodedBytes[outIdx] = (byte)((encodedString[idx] << maskIdx )& maskLowByte[maskIdx]);
	
	      if (idx > 0)
	      {
	          decodedBytes[outIdx] = (byte)(decodedBytes[outIdx] |
	                ((encodedString[idx - 1] >> (8 - maskIdx)) & maskHighByte[maskIdx]));
	      }
	      maskIdx = ++maskIdx % 7;
	      if (maskIdx  == 0)
	      {
	        outIdx++;
	        decodedBytes[outIdx] = (byte)((encodedString[idx] >> 1) & 127);
	      }
	    }
	    if (decodedBytes[decodedBytes.length - 1] == CHARIAGE_RETURN_BYTE)
	    {
	        byte[] decodedBytesTrimmed = new byte[decodedBytes.length - 1];
	        for (idx = 0; idx < decodedBytesTrimmed.length; idx++) {
	            decodedBytesTrimmed[idx] = decodedBytes[idx];
	        }
	        return decodedBytesTrimmed;
	    }
	    else
	    {
	      return decodedBytes;
	    }
	  }

	public static byte[] encodeBytes(byte[] decodedBytes) {
		int idx = 0;
		int outIdx = 0;
		int maskIdx = 0;
		int decodedLenMinus1 = decodedBytes.length - 1;
		byte[] maskByte = { -128, -64, -32, -16, -8, -4, -2 };
		byte[] maskLowByte = { 127, 63, 31, 15, 7, 3, 1 };
		byte[] encodedBytes = new byte[decodedBytes.length - (decodedBytes.length / 8)];

		for (idx = 0; idx < decodedBytes.length; idx++, outIdx++) {
			if (idx < decodedLenMinus1)
				encodedBytes[outIdx] = (byte) ((decodedBytes[idx + 1] << (7 - maskIdx) & maskByte[maskIdx]));

			encodedBytes[outIdx] = (byte) (encodedBytes[outIdx] | ((decodedBytes[idx] >> maskIdx) & maskLowByte[maskIdx]));
			maskIdx = ++maskIdx % 7;
			if (maskIdx == 0)
				idx++;
		}
		if ((decodedBytes.length % 8) == 7) // last byte unfilled - could cause
											// @ to be displayed in decode
			encodedBytes[outIdx - 1] = (byte) (encodedBytes[outIdx - 1] | (CHARIAGE_RETURN_BYTE << 1 & maskByte[6]));

		return encodedBytes;

	}
	
	public static String hexDump(ByteBuf buf) {
		int len = buf.readableBytes();
		byte[] bytes = new byte[len];
		buf.getBytes(buf.readerIndex(), bytes, 0, len);
		return encodeHexString(bytes);
	}
	
	public static void writeLenSchemeEncodedOctetString(ByteBuf buf, byte len, byte scheme, String str) {
		if(len > 0) {
			buf.writeByte(len + 1);
			buf.writeByte(scheme);
			buf.writeBytes(encodeOctetString(str.getBytes()));
		}else {
			buf.writeByte(len);
		}
	}
	
	public static void writeLenEncodedOctetString(ByteBuf buf, byte len, String str){
		buf.writeByte(len);
		buf.writeBytes(encodeOctetString(str.getBytes()));
	}

	public static void writeLenEncodedBytes(ByteBuf buf, byte len, String str) {
		buf.writeByte(len);
		buf.writeBytes(encodeBytes(str.getBytes()));
	}

	public static LenSchemeString readLenSchemeEncodedOctetString(ByteBuf buf) {
		byte len = buf.readByte();
		byte scheme = 0;
		String str = "";
		
		if(len > 0) {
			scheme= buf.readByte();
			byte[] msisdnBytes = new byte[len - 1];
			buf.readBytes(msisdnBytes);
			str = decodeOctetString(msisdnBytes);
		}
		
		return new LenSchemeString(len, scheme, str);
	}
	
	public static LenString readLenEncodedOctetString(ByteBuf buf) {
		byte len = buf.readByte();
		String str = "";
		if(len > 0){
			byte[] dst = new byte[len];
			buf.readBytes(dst);
			str = decodeOctetString(dst);
		}
		return new LenString(len, str);
	}

	public static LenBytes readLenEncodedBytes(ByteBuf buf) {
		byte len = buf.readByte();
		byte[] decoded = "".getBytes();
		if(len > 0) {
			byte[] bytes = new byte[len];
			buf.readBytes(bytes);
			decoded = decodeBytes(bytes);
		}
		return new LenBytes(len, decoded);
	}
}
