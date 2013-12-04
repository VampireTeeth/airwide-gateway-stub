package com.iasgrp.stubs.airwide.message;

import static com.iasgrp.stubs.airwide.AirwideConstants.DATA_CODING_UCS2;
import static com.iasgrp.stubs.airwide.AirwideConstants.MESSAGE_TYPE_RESULT;
import static com.iasgrp.stubs.airwide.AirwideConstants.OPERATION_PROCESS_USSREQUEST;
import static com.iasgrp.stubs.airwide.AirwideConstants.TCPIP_HEADER_LENGTH;

public class AirwideProcessRequestResult {
	private AirwideHeader header = new AirwideHeader();
	private int applicationIdentifier = 0;
	private byte dataCodingScheme = 0;
	private String ussdString = "";

	private byte applicationIdentifierLen = 4;
	private byte dataCodingSchemeLen = 1;
	private byte ussdStringLen = 0;

	/**
	 * Constructor
	 */
	public AirwideProcessRequestResult() {
		header.setMessageType(MESSAGE_TYPE_RESULT);
		header.setOperation(OPERATION_PROCESS_USSREQUEST);
	}

	public void initialise() {
		applicationIdentifier = 0;
		dataCodingScheme = 0;
		ussdString = "";
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

	public void setUssdString(String ussdString, byte dataCodingScheme) {
		if (ussdString == null)
			this.ussdString = "";
		else
			this.ussdString = new String(ussdString);

		setDataCodingScheme(dataCodingScheme);

		if (dataCodingScheme == DATA_CODING_UCS2)
			setUssdStringLen((byte) (ussdString.length() * 2));
		else
			setUssdStringLen((byte) (ussdString.length() - (ussdString.length() / 8)));

	}

	public byte getApplicationIdentifierLen() {
		return applicationIdentifierLen;
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

	public void setUssdString(String ussdString) {
		this.ussdString = ussdString;
	}

}
