// Copyright (c) 2002 InterAcct Solutions
package com.iasgrp.stubs.airwide.message;

import static com.iasgrp.stubs.airwide.AirwideConstants.MESSAGE_TYPE_ERROR;
import static com.iasgrp.stubs.airwide.AirwideConstants.MESSAGE_TYPE_INVOKE;
import static com.iasgrp.stubs.airwide.AirwideConstants.MESSAGE_TYPE_RESULT;

/**
 * A Class class.
 * <P>
 * 
 * @author JLM
 */
public class AirwideHeader {
	public static final byte DATA_CODING_UCS2 = 0x48;
	public static final byte DATA_CODING_RESERVED_SEMA = 15;

	private static int nextOperationReference = 1;

	private int operationReference = 0;
	private byte messageType = 0;
	private byte operation = 0;
	private short overallMessageLength = 0;

	/**
	 * Constructor
	 */
	public AirwideHeader() {
	}

	public void initialise() {
		operationReference = 0;
		overallMessageLength = 0;
	}

	public int getOperationReference() {
		return operationReference;
	}

	public void setOperationReference(int operationReference) {
		this.operationReference = operationReference;
	}

	public void incOperationReference() {
		operationReference = nextOperationReference++;
	}

	public String getOperationStr() {
		switch (operation) {
		case 1:
			return "LOGIN";
		case 2:
			return "PROCESS_USSDATA";
		case 3:
			return "PROCESS_USSREQUEST";
		case 4:
			return "USSREQUEST";
		case 5:
			return "USSNOTIFY";
		case 6:
			return "USSEND";
		default:
			return "UNKNOWN";
		}
	}

	public String getMessageTypeStr() {
		if (messageType == MESSAGE_TYPE_INVOKE)
			return "INVOKE";
		if (messageType == MESSAGE_TYPE_RESULT)
			return "RESULT";
		if (messageType == MESSAGE_TYPE_ERROR)
			return "ERROR";
		return "Unknown Message Type";
	}

	public String getErrorMessage() {
		switch (operation) {
		case 1:
			return "Invalid Argument. The operation was rejected because it did not contain the arguments expected, or the arguments did not contain valid data.";
		case 3:
			return "Busy. The operation cannot be performed because of resource shortages in the recipient of the invoke.";
		case 6:
			return "Destination Unavailable. The recipient of the  invoke is currently not available.";
		case 11:
			return "Protocol Error. The operation has not been recognised by the performer, or is not supported by the performer.";
		case 20:
			return "User Barred. The operation cannot be performed for the specified mobile user.";
		case 23:
			return "Error in Recipient. The recipient has detected an error not covered by any other error code.";
		case 24:
			return "Unknown Subscriber. The recipient of the  invoke is not known to the mobile network.";
		case 25:
			return "Network Operator Barred. The mobile network operator has barred access to the recipient.";
		case 120:
			return "Network Failure. The recipient has detected a major failure of the communications network.";

		default:
			return "Unknown Error Message - check reference manual for updates";
		}
	}

	public byte getMessageType() {
		return messageType;
	}

	public void setMessageType(byte messageType) {
		this.messageType = messageType;
	}

	public boolean isMessageTypeError() {
		if (getMessageType() == MESSAGE_TYPE_ERROR)
			return true;
		else
			return false;
	}

	public byte getOperation() {
		return operation;
	}

	public void setOperation(byte operation) {
		this.operation = operation;
	}

	public void setOverallMessageLength(short overallMessageLength) {
		this.overallMessageLength = overallMessageLength;
	}

	public short getOverallMessageLength() {
		return overallMessageLength;
	}

	@Override
	public String toString() {
		return "AirwideHeader [operationReference=" + 
				operationReference + ", messageType=" + 
				messageType + ", operation=" + 
				operation + ", overallMessageLength=" + 
				overallMessageLength + "]";
	}

}
