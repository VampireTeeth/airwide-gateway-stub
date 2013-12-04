// Copyright (c) 2002 InterAcct Solutions
package com.iasgrp.stubs.airwide.message;

import static com.iasgrp.stubs.airwide.AirwideConstants.MESSAGE_TYPE_ERROR;
import static com.iasgrp.stubs.airwide.AirwideConstants.MESSAGE_TYPE_RESULT;
import static com.iasgrp.stubs.airwide.AirwideConstants.OPERATION_USSNOTIFY;
import static com.iasgrp.stubs.airwide.AirwideConstants.TCPIP_HEADER_LENGTH;

import java.util.Date;

/**
 * A Class class.
 * <P>
 * 
 * @author JLM
 */
public class AirwideNotifyResult {
	private AirwideHeader header = new AirwideHeader(); 
	private int applicationIdentifier = 0;
	private int dialogueReference = 0;
	private byte applicationIdentifierLen = 4;
	private byte dialogueReferenceLen = 4;

	/**
	 * Constructor
	 */
	public AirwideNotifyResult() {
		header.setMessageType(MESSAGE_TYPE_RESULT);
		header.setOperation(OPERATION_USSNOTIFY);
	}

	public void initialise() {
		applicationIdentifier = 0;
		dialogueReference = 0;
	}

	public int getCommandLength() {
		return TCPIP_HEADER_LENGTH + header.getOverallMessageLength();
	}

	public int getApplicationIdentifier() {
		return applicationIdentifier;
	}


	public void setApplicationIdentifier(int applicationIdentifier) {
		this.applicationIdentifier = applicationIdentifier;
		setApplicationIdentifierLen((byte) 4);
	}

	public int getDialogueReference() {
		return dialogueReference;
	}


	public void setDialogueReference(int dialogueReference) {
		this.dialogueReference = dialogueReference;
		setDialogueReferenceLen((byte) 4);
	}

	public byte getApplicationIdentifierLen() {
		return applicationIdentifierLen;
	}

	public byte getDialogueReferenceLen() {
		return dialogueReferenceLen;
	}

	public void setApplicationIdentifierLen(byte applicationIdentifierLen) {
		this.applicationIdentifierLen = applicationIdentifierLen;
	}

	public void setDialogueReferenceLen(byte dialogueReferenceLen) {
		this.dialogueReferenceLen = dialogueReferenceLen;
	}

	public AirwideHeader getHeader() {
		return header;
	}

	public void setHeader(AirwideHeader header) {
		this.header = header;
	}
	

}
