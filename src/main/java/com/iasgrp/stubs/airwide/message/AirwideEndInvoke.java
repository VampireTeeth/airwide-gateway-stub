package com.iasgrp.stubs.airwide.message;

import static com.iasgrp.stubs.airwide.AirwideConstants.MESSAGE_TYPE_INVOKE;
import static com.iasgrp.stubs.airwide.AirwideConstants.OPERATION_USSEND;
import static com.iasgrp.stubs.airwide.AirwideConstants.TCPIP_HEADER_LENGTH;


public class AirwideEndInvoke {
	private AirwideHeader header = new AirwideHeader();
	private int applicationIdentifier = 0;
	private int dialogueReference = 0;
	private byte endType = 1;

	private byte applicationIdentifierLen = 4;
	private byte dialogueReferenceLen = 4;
	private byte endTypeLen = 1;

	/**
	 * Constructor
	 */
	public AirwideEndInvoke() {
		header.setMessageType(MESSAGE_TYPE_INVOKE);
		header.setOperation(OPERATION_USSEND);
	}

	public void initialise() {
		applicationIdentifier = 0;
		dialogueReference = 0;
		endType = 1;
	}

	public AirwideHeader getHeader() {
		return header;
	}

	public void setHeader(AirwideHeader header) {
		this.header = header;
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

	public byte getEndType() {
		return endType;
	}

	public void setEndType(byte endType) {
		this.endType = endType;
		setEndTypeLen((byte) 1);
	}

	public byte getApplicationIdentifierLen() {
		return applicationIdentifierLen;
	}

	public byte getDialogueReferenceLen() {
		return dialogueReferenceLen;
	}

	public byte getEndTypeLen() {
		return endTypeLen;
	}

	public void setApplicationIdentifierLen(byte applicationIdentifierLen) {
		this.applicationIdentifierLen = applicationIdentifierLen;
	}

	public void setDialogueReferenceLen(byte dialogueReferenceLen) {
		this.dialogueReferenceLen = dialogueReferenceLen;
	}

	public void setEndTypeLen(byte endTypeLen) {
		this.endTypeLen = endTypeLen;
	}

}
