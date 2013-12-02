
package com.iasgrp.stubs.airwide;

public class AirwideConstants {

    public static final byte     DEFAULT_NUMBER_SCHEME          = (byte)0X91;
    public static final byte     OPERATION_LOGIN                = 1;
    public static final byte     OPERATION_PROCESS_USSDATA      = 2;
    public static final byte     OPERATION_PROCESS_USSREQUEST   = 3;
    public static final byte     OPERATION_USSREQUEST           = 4;
    public static final byte     OPERATION_USSNOTIFY            = 5;
    public static final byte     OPERATION_USSEND               = 6;

    public static final byte     MESSAGE_TYPE_INVOKE            = 0;
    public static final byte     MESSAGE_TYPE_RESULT            = 1;
    public static final byte     MESSAGE_TYPE_ERROR             = 2;

    public static final byte     END_TYPE_NORMAL                = 0;
    public static final byte     END_TYPE_ABORT                 = 1;

    public static final short    TCPIP_HEADER_LENGTH            = 10;
}

 