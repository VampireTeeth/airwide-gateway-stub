
// Copyright (c) 2002 InterAcct Solutions Services
package com.iasgrp.stubs.airwide.utils;


/**
 * A Class class.
 * <P>
 * @author JLM
 */
public class BytesUtils extends Object
{
    static final long LONG_HIGH_ORDER_VALUE = 0X100000000L;
    static final long INT_HIGH_ORDER_VALUE  = 0X10000L;
    static final int  MAX_DUMP_LENGTH       = 600;

  /**
   * Constructor
   */
  public BytesUtils() {
  }

  public static int unsignedByteToInt(byte b) {
    return (int) b & 0xFF;
  }

  public static short unsignedByteToShort(byte b) {
    return (short)( b & 0xFF);
  }

  public static String byteToBits(byte b) {
    int i = b & 0xFF;
    String bitString = Integer.toBinaryString(i); 
    while(bitString.length() < 8)
      bitString = "0" +  bitString;
    return bitString;   

  }

  public static String byteToHex(byte b) {
    int i = b & 0xFF;
    String hexString = Integer.toHexString(i);
    if (hexString.length() == 1)
      hexString = "0" +  hexString;
    return hexString;
  }

  public static long unsignedIntToLong(int i)
  {
    if (i < 0)
      return LONG_HIGH_ORDER_VALUE + i;
    else
      return (long)i;
  }

  public static int unsignedShortToInt(short i)
  {
    if (i < 0)
      return (short)(INT_HIGH_ORDER_VALUE + i);
    else
      return (int)i;
  }

  public static byte[] unsignedIntToBytes(int intInput)
  {
    byte byteStr[] = {0,0,0,0};
    long theIntInput = BytesUtils.unsignedIntToLong(intInput);

    if(theIntInput > 0Xffffff)
    {
      byteStr[0]   = (byte)(theIntInput / 0X1000000L);
      theIntInput = theIntInput % 0X1000000L;
    }

    if(theIntInput > 0Xffff)
    {
      byteStr[1]   = (byte)(theIntInput  / 0X10000L);
      theIntInput = theIntInput % 0X10000L;
    }

    if(theIntInput > 0Xff)
    {
      byteStr[2]   = (byte)(theIntInput / 0X100L);
      theIntInput = theIntInput % 0X100L;
    }

    byteStr[3] = (byte)(theIntInput);

    return byteStr;
  }

  public static byte[] unsignedShortToBytesReversed(short shortInput)
  {
    byte byteStr[] = BytesUtils.unsignedShortToBytes(shortInput);
    byte byteStrReversed[] = new byte[2];

    byteStrReversed[0]   = byteStr[1];
    byteStrReversed[1]   = byteStr[0];

    return byteStrReversed;
  }

  public static byte[] unsignedIntToBytesReversed(int intInput)
  {
    byte byteStr[] = BytesUtils.unsignedIntToBytes(intInput);
    byte byteStrReversed[] = new byte[4];

    byteStrReversed[0]   = byteStr[3];
    byteStrReversed[1]   = byteStr[2];
    byteStrReversed[2]   = byteStr[1];
    byteStrReversed[3]   = byteStr[0];

    return byteStrReversed;
  }

  public static byte[] unsignedShortToBytesRaw(short shortInput)
  {
    byte byteStr[] = {0,0};
    int theShortInput = BytesUtils.unsignedShortToInt(shortInput);

    byteStr[0]   = (byte)(theShortInput / 0X100L);
    theShortInput = (short)(theShortInput % 0X100L);
    byteStr[1] = (byte)(theShortInput);

    return byteStr;
  }

  public static byte[] unsignedShortToBytes(short shortInput)
  {
    byte byteStr[] = {0,0};
    int theShortInput = BytesUtils.unsignedShortToInt(shortInput);

    if(theShortInput > 0Xff)
    {
      byteStr[0]   = (byte)(theShortInput / 0X100L);
      theShortInput = (short)(theShortInput % 0X100L);
    }

    byteStr[1] = (byte)(theShortInput);

    return byteStr;
  }

  public static boolean compareBytesToInt(int intInput, byte[] inputBytes)
  {
    int pos = 0;
    int checkInt = unsignedBytesToInt(inputBytes);
    return (checkInt == intInput);
  }

  public static int unsignedBytesToIntReversed(byte[] inputBytes)
  {
    int checkInt  = 0;
    int pos       = 3;

    if (inputBytes.length < 0) return 0;

    if (inputBytes.length == 2) 
    {
      pos = 1;
      checkInt += BytesUtils.unsignedByteToInt(inputBytes[pos--]) << 8;
      checkInt += BytesUtils.unsignedByteToInt(inputBytes[pos--]) << 0;
    }
    else
    {
      pos = 3;
      checkInt += BytesUtils.unsignedByteToInt(inputBytes[pos--]) << 24;
      checkInt += BytesUtils.unsignedByteToInt(inputBytes[pos--]) << 16;
      checkInt += BytesUtils.unsignedByteToInt(inputBytes[pos--]) << 8;
      checkInt += BytesUtils.unsignedByteToInt(inputBytes[pos--]) << 0;
    }

    return checkInt;
  }

  public static long unsignedBytesToLongReversed(byte[] inputBytes)
  {
    long checkLong  = 0;
    int pos       = 7;

    if (inputBytes.length < 8) return 0;


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

  public static int unsignedBytesToInt(byte[] inputBytes)
  {
    int checkInt = 0;
    int pos = 0;

    if (inputBytes.length < 0) return 0;
    
    if (inputBytes.length == 2) 
    {
      checkInt += BytesUtils.unsignedByteToInt(inputBytes[pos++]) << 8;
      checkInt += BytesUtils.unsignedByteToInt(inputBytes[pos++]) << 0;
    }
    else
    {
      checkInt += BytesUtils.unsignedByteToInt(inputBytes[pos++]) << 24;
      checkInt += BytesUtils.unsignedByteToInt(inputBytes[pos++]) << 16;
      checkInt += BytesUtils.unsignedByteToInt(inputBytes[pos++]) << 8;
      checkInt += BytesUtils.unsignedByteToInt(inputBytes[pos++]) << 0;
    }


    return checkInt;
  }

  public static short unsignedBytesToShortReversed(byte[] inputBytes)
  {
    short checkShort = 0;

    if (inputBytes.length < 0) return 0;

    checkShort += BytesUtils.unsignedByteToInt(inputBytes[1]) << 8;
    checkShort += BytesUtils.unsignedByteToInt(inputBytes[0]) << 0;

    return checkShort;
  }

  public static short unsignedBytesToShort(byte[] inputBytes)
  {
    short checkShort = 0;
    int pos = 0;

    if (inputBytes.length < 0) return 0;

    checkShort += BytesUtils.unsignedByteToInt(inputBytes[0]) << 8;
    checkShort += BytesUtils.unsignedByteToInt(inputBytes[1]) << 0;

    return checkShort;
  }

  public static String stringToHex(String theString)
  {
    byte[]  theByteArray  =  theString.getBytes();
    String  hexString     =  "";

    for (int i = 0; i < theString.length(); i++)
    {
      hexString   += BytesUtils.byteToHex(theByteArray[i]);
    }
    return hexString;
  }


  public static String stringToHex(byte[]  theByteArray)
  {
    String  hexString     =  "";

    for (int i = 0; i < theByteArray.length; i++)
    {
      hexString   += BytesUtils.byteToHex(theByteArray[i]);
    }
    return hexString;
  }

  public static void main(String[] args)
  {
    byte[] transactionIdBytes = new byte[4];
    byte   maxByte            = (byte)0xff;
    int    transactionId      = 0;
    
    transactionIdBytes[0] = maxByte;
    transactionIdBytes[1] = maxByte;
    transactionIdBytes[2] = maxByte;
    transactionIdBytes[3] = maxByte;
    transactionId         = BytesUtils.unsignedBytesToInt(transactionIdBytes);

    System.out.println("Tran ID: " + transactionId + " (" + Integer.toHexString(transactionId));
  }
}

