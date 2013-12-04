package com.iasgrp.stubs.airwide.message;

import static com.iasgrp.stubs.airwide.AirwideConstants.*;

public class AirwideProcessRequestInvoke {
	private AirwideHeader header = new AirwideHeader();
	private int applicationIdentifier = 0;
	private int dialogueReference = 0;
	private byte msisdnNumberScheme = DEFAULT_NUMBER_SCHEME;
	private String msisdn = "";
	private byte hlrNumberScheme = DEFAULT_NUMBER_SCHEME;
	private String hlrNo = "";
	private String imsi = "";
	private byte vlrNumberScheme = DEFAULT_NUMBER_SCHEME;
	private String vlrNo = "";
	private byte dataCodingScheme = 0;
	private String ussdString = "";

	private byte applicationIdentifierLen = 4;
	private byte dialogueReferenceLen = 4;
	private byte msisdnLen = 0;
	private byte hlrNoLen = 0;
	private byte imsiLen = 0;
	private byte vlrNoLen = 0;
	private byte dataCodingSchemeLen = 1;
	private byte ussdStringLen = 0;

	/**
	 * Constructor
	 */
	public AirwideProcessRequestInvoke() {
		header.setMessageType(MESSAGE_TYPE_INVOKE);
		header.setOperation(OPERATION_PROCESS_USSREQUEST);
	}

	public void initialise() {
		applicationIdentifier = 0;
		dialogueReference = 0;
		msisdnNumberScheme = DEFAULT_NUMBER_SCHEME;
		msisdn = "";
		hlrNumberScheme = DEFAULT_NUMBER_SCHEME;
		hlrNo = "";
		imsi = "";
		vlrNumberScheme = DEFAULT_NUMBER_SCHEME;
		vlrNo = "";
		dataCodingScheme = 0;
		ussdString = "";
		msisdnLen = 0;
		imsiLen = 0;
		hlrNoLen = 0;
		vlrNoLen = 0;
		ussdStringLen = 0;
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

	public void setMsisdn(String msisdn) {
		this.msisdn = new String(msisdn);
		setMsisdnLen((byte) ((this.msisdn.length() + 1) / 2));
	}

	public byte getMsisdnNumberScheme() {
		return msisdnNumberScheme;
	}

	public byte getHlrNumberScheme() {
		return hlrNumberScheme;
	}

	public byte getVlrNumberScheme() {
		return vlrNumberScheme;
	}

	public String getMsisdn() {
		return msisdn;
	}

	public byte[] getMsisdnBytes() {
		return msisdn.getBytes();
	}

	public byte getDataCodingScheme() {
		return dataCodingScheme;
	}

	public void setDataCodingScheme(byte dataCodingScheme) {
		this.dataCodingScheme = dataCodingScheme;
		setDataCodingSchemeLen((byte) 1);
	}

	public String getUssdString() {
		return ussdString;
	}

	public byte[] getUssdStringBytes() {
		return ussdString.getBytes();
	}

	public void setUssdString(String ussdString) {
		if (ussdString == null)
			this.ussdString = "";
		else
			this.ussdString = new String(ussdString);

		setUssdStringLen((byte) (ussdString.length() - (ussdString.length() / 8)));
	}

	public String getVlrNo() {
		return vlrNo;
	}

	public byte[] getVlrNoBytes() {
		return vlrNo.getBytes();
	}

	public void setVlrNo(String vlrNo) {
		if (vlrNo == null)
			this.vlrNo = "";
		else
			this.vlrNo = new String(vlrNo);

		vlrNoLen = (byte) ((this.vlrNo.length() + 1) / 2);
	}

	public String getImsi() {
		return imsi;
	}

	public byte[] getImsiBytes() {
		return imsi.getBytes();
	}

	public void setImsi(String imsi) {
		if (imsi == null)
			this.imsi = "";
		else
			this.imsi = new String(imsi);

		setImsiLen((byte) ((this.imsi.length() + 1) / 2));
	}

	public String getHlrNo() {
		return hlrNo;
	}

	public byte[] getHlrNoBytes() {
		return hlrNo.getBytes();
	}

	public void setHlrNo(String hlrNo) {
		if (hlrNo == null)
			this.hlrNo = "";
		else
			this.hlrNo = new String(hlrNo);
		hlrNoLen = (byte) ((this.hlrNo.length() + 1) / 2);
	}

	public byte getApplicationIdentifierLen() {
		return applicationIdentifierLen;
	}

	public byte getDialogueReferenceLen() {
		return dialogueReferenceLen;
	}

	public byte getMsisdnLen() {
		return msisdnLen;
	}

	public byte getHlrNoLen() {
		return hlrNoLen;
	}

	public byte getImsiLen() {
		return imsiLen;
	}

	public byte getVlrNoLen() {
		return vlrNoLen;
	}

	public byte getDataCodingSchemeLen() {
		return dataCodingSchemeLen;
	}

	public byte getUssdStringLen() {
		return ussdStringLen;
	}


	public void setApplicationIdentifierLen(byte applicationIdentifierLen) {
		this.applicationIdentifierLen = applicationIdentifierLen;
	}

	public void setDialogueReferenceLen(byte dialogueReferenceLen) {
		this.dialogueReferenceLen = dialogueReferenceLen;
	}

	public void setMsisdnLen(byte msisdnLen) {
		this.msisdnLen = msisdnLen;
	}

	public void setHlrNoLen(byte hlrNoLen) {
		this.hlrNoLen = hlrNoLen;
	}

	public void setImsiLen(byte imsiLen) {
		this.imsiLen = imsiLen;
	}

	public void setVlrNoLen(byte vlrNoLen) {
		this.vlrNoLen = vlrNoLen;
	}

	public void setDataCodingSchemeLen(byte dataCodingSchemeLen) {
		this.dataCodingSchemeLen = dataCodingSchemeLen;
	}

	public void setUssdStringLen(byte ussdStringLen) {
		this.ussdStringLen = ussdStringLen;
	}

	public AirwideHeader getHeader() {
		return header;
	}

	public void setHeader(AirwideHeader header) {
		this.header = header;
	}

	public void setMsisdnNumberScheme(byte msisdnNumberScheme) {
		this.msisdnNumberScheme = msisdnNumberScheme;
	}

	public void setHlrNumberScheme(byte hlrNumberScheme) {
		this.hlrNumberScheme = hlrNumberScheme;
	}

	public void setVlrNumberScheme(byte vlrNumberScheme) {
		this.vlrNumberScheme = vlrNumberScheme;
	}

	
}
