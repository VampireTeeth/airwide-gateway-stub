package com.iasgrp.stubs.airwide.message;

import static com.iasgrp.stubs.airwide.AirwideConstants.MESSAGE_TYPE_RESULT;
import static com.iasgrp.stubs.airwide.AirwideConstants.OPERATION_USSEND;
import static com.iasgrp.stubs.airwide.AirwideConstants.TCPIP_HEADER_LENGTH;

public class AirwideEndResult {

	private AirwideHeader header = new AirwideHeader();
	private int applicationIdentifier = 0;
	private int dialogueReference = 0;
	private byte endType = 0;
	private byte applicationIdentifierLen = 4;

	/**
	 * Constructor
	 */
	public AirwideEndResult() {
		header.setMessageType(MESSAGE_TYPE_RESULT);
		header.setOperation(OPERATION_USSEND);
	}

	public void initialise() {
		applicationIdentifier = 0;
		dialogueReference = 0;
		endType = 0;
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

	public byte getApplicationIdentifierLen() {
		return applicationIdentifierLen;
	}

	public void setApplicationIdentifierLen(byte applicationIdentifierLen) {
		this.applicationIdentifierLen = applicationIdentifierLen;
	}

	public AirwideHeader getHeader() {
		return header;
	}

	public void setHeader(AirwideHeader header) {
		this.header = header;
	}

	public int getDialogueReference() {
		return dialogueReference;
	}

	public void setDialogueReference(int dialogueReference) {
		this.dialogueReference = dialogueReference;
	}

	public byte getEndType() {
		return endType;
	}

	public void setEndType(byte endType) {
		this.endType = endType;
	}

}
