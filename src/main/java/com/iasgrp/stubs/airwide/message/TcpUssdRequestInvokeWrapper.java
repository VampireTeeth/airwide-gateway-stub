package com.iasgrp.stubs.airwide.message;

import java.util.Arrays;

import TCPIP.TCPIPException;
import TCPIP.TcpipUssdRequestInvoke;

public class TcpUssdRequestInvokeWrapper {

	private TcpipUssdRequestInvoke invoke = new TcpipUssdRequestInvoke();

	public byte[] getOperationReferenceBytes() {
		return invoke.getOperationReferenceBytes();
	}

	public byte[] getOperationReferenceBytesReversed() {
		return invoke.getOperationReferenceBytesReversed();
	}

	public String getOperationReferenceStr() {
		return invoke.getOperationReferenceStr();
	}

	public int getOperationReference() {
		return invoke.getOperationReference();
	}

	public void initialise() {
		invoke.initialise();
	}

	public void setOperationReference(int operationReference) {
		invoke.setOperationReference(operationReference);
	}

	public int hashCode() {
		return invoke.hashCode();
	}

	public void incOperationReference() {
		invoke.incOperationReference();
	}

	public String getOperationStr() {
		return invoke.getOperationStr();
	}

	public String getMessageTypeStr() {
		return invoke.getMessageTypeStr();
	}

	public int getCommandLength() {
		return invoke.getCommandLength();
	}

	public int getApplicationIdentifier() {
		return invoke.getApplicationIdentifier();
	}

	public String getErrorMessage() {
		return invoke.getErrorMessage();
	}

	public byte[] getApplicationIdentifierReversed() {
		return invoke.getApplicationIdentifierReversed();
	}

	public void setApplicationIdentifier(int applicationIdentifier) {
		invoke.setApplicationIdentifier(applicationIdentifier);
	}

	public int getDialogueReference() {
		return invoke.getDialogueReference();
	}

	public byte[] getDialogueReferenceReversed() {
		return invoke.getDialogueReferenceReversed();
	}

	public void setDialogueReference(int dialogueReference) {
		invoke.setDialogueReference(dialogueReference);
	}

	public void setMsisdn(String msisdn) {
		invoke.setMsisdn(msisdn);
	}

	public byte getMsisdnNumberScheme() {
		return invoke.getMsisdnNumberScheme();
	}

	public byte getVlrNumberScheme() {
		return invoke.getVlrNumberScheme();
	}

	public void setMsisdnNumberScheme(byte msisdnNumberScheme) {
		invoke.setMsisdnNumberScheme(msisdnNumberScheme);
	}

	public void setVlrNumberScheme(byte vlrNumberScheme) {
		invoke.setVlrNumberScheme(vlrNumberScheme);
	}

	public String getMsisdn() {
		return invoke.getMsisdn();
	}

	public boolean equals(Object obj) {
		return invoke.equals(obj);
	}

	public byte getMessageType() {
		return invoke.getMessageType();
	}

	public byte[] getMsisdnBytes() {
		return invoke.getMsisdnBytes();
	}

	public void setMessageType(byte messageType) {
		invoke.setMessageType(messageType);
	}

	public void setExternalNodeNo(String externalNodeNo) {
		invoke.setExternalNodeNo(externalNodeNo);
	}

	public boolean isMessageTypeError() {
		return invoke.isMessageTypeError();
	}

	public String getExternalNodeNo() {
		return invoke.getExternalNodeNo();
	}

	public byte getOperation() {
		return invoke.getOperation();
	}

	public byte[] getExternalNodeNoBytes() {
		return invoke.getExternalNodeNoBytes();
	}

	public void setOperation(byte operation) {
		invoke.setOperation(operation);
	}

	public byte getDataCodingScheme() {
		return invoke.getDataCodingScheme();
	}

	public void setOverallMessageLength(short overallMessageLength) {
		invoke.setOverallMessageLength(overallMessageLength);
	}

	public void setDataCodingScheme(byte dataCodingScheme) {
		invoke.setDataCodingScheme(dataCodingScheme);
	}

	public byte[] getOverallMessageLengthBytes() {
		return invoke.getOverallMessageLengthBytes();
	}

	public String getUssdString() {
		return invoke.getUssdString();
	}

	public byte[] getOverallMessageLengthBytesReversed() {
		return invoke.getOverallMessageLengthBytesReversed();
	}

	public byte[] getUssdStringBytes() {
		return invoke.getUssdStringBytes();
	}

	public void setUssdString(String ussdString, byte dataCodingScheme) {
		invoke.setUssdString(ussdString, dataCodingScheme);
	}

	public short getOverallMessageLength() {
		return invoke.getOverallMessageLength();
	}

	public String getVlrNo() {
		return invoke.getVlrNo();
	}

	public byte[] getVlrNoBytes() {
		return invoke.getVlrNoBytes();
	}

	public void setVlrNo(String vlrNo) {
		invoke.setVlrNo(vlrNo);
	}

	public String getImsi() {
		return invoke.getImsi();
	}

	public byte[] getImsiBytes() {
		return invoke.getImsiBytes();
	}

	public void setImsi(String imsi) {
		invoke.setImsi(imsi);
	}

	public byte getApplicationIdentifierLen() {
		return invoke.getApplicationIdentifierLen();
	}

	public byte getDialogueReferenceLen() {
		return invoke.getDialogueReferenceLen();
	}

	public byte getMsisdnLen() {
		return invoke.getMsisdnLen();
	}

	public byte getExternalNodeNoLen() {
		return invoke.getExternalNodeNoLen();
	}

	public byte getImsiLen() {
		return invoke.getImsiLen();
	}

	public byte getVlrNoLen() {
		return invoke.getVlrNoLen();
	}

	public byte getDataCodingSchemeLen() {
		return invoke.getDataCodingSchemeLen();
	}

	public byte getUssdStringLen() {
		return invoke.getUssdStringLen();
	}

	public int getUssdStringLenInt() {
		return invoke.getUssdStringLenInt();
	}

	public void setApplicationIdentifierLen(byte applicationIdentifierLen) {
		invoke.setApplicationIdentifierLen(applicationIdentifierLen);
	}

	public void setDialogueReferenceLen(byte dialogueReferenceLen) {
		invoke.setDialogueReferenceLen(dialogueReferenceLen);
	}

	public void setMsisdnLen(byte msisdnLen) {
		invoke.setMsisdnLen(msisdnLen);
	}

	public void setExternalNodeNoLen(byte externalNodeNoLen) {
		invoke.setExternalNodeNoLen(externalNodeNoLen);
	}

	public void setImsiLen(byte imsiLen) {
		invoke.setImsiLen(imsiLen);
	}

	public void setVlrNoLen(byte vlrNoLen) {
		invoke.setVlrNoLen(vlrNoLen);
	}

	public void setDataCodingSchemeLen(byte dataCodingSchemeLen) {
		invoke.setDataCodingSchemeLen(dataCodingSchemeLen);
	}

	public void setUssdStringLen(byte ussdStringLen) {
		invoke.setUssdStringLen(ussdStringLen);
	}

	public void setOverallMessageLength() {
		invoke.setOverallMessageLength();
	}

	public byte[] toUTS8Bytes() {
		return invoke.toUTS8Bytes();
	}

	public byte[] getUssdStringAsUTF16ByteArray() {
		return invoke.getUssdStringAsUTF16ByteArray();
	}

	public void parse(byte[] inParseMessage) throws TCPIPException {
		invoke.parse(inParseMessage);
	}

	public void print() {
		invoke.print();
	}

	@Override
	public String toString() {
		return "TcpUssdRequestInvokeWrapper [getOperationReferenceBytes()=" + Arrays.toString(getOperationReferenceBytes()) + ", getOperationReferenceBytesReversed()="
				+ Arrays.toString(getOperationReferenceBytesReversed()) + ", getOperationReferenceStr()=" + getOperationReferenceStr() + ", getOperationReference()=" + getOperationReference()
				+ ", getOperationStr()=" + getOperationStr() + ", getMessageTypeStr()=" + getMessageTypeStr() + ", getCommandLength()=" + getCommandLength() + ", getApplicationIdentifier()="
				+ getApplicationIdentifier() + ", getErrorMessage()=" + getErrorMessage() + ", getApplicationIdentifierReversed()=" + Arrays.toString(getApplicationIdentifierReversed())
				+ ", getDialogueReference()=" + getDialogueReference() + ", getDialogueReferenceReversed()=" + Arrays.toString(getDialogueReferenceReversed()) + ", getMsisdnNumberScheme()="
				+ getMsisdnNumberScheme() + ", getVlrNumberScheme()=" + getVlrNumberScheme() + ", getMsisdn()=" + getMsisdn() + ", getMessageType()=" + getMessageType() + ", getMsisdnBytes()="
				+ Arrays.toString(getMsisdnBytes()) + ", getExternalNodeNo()=" + getExternalNodeNo() + ", getOperation()=" + getOperation() + ", getExternalNodeNoBytes()="
				+ Arrays.toString(getExternalNodeNoBytes()) + ", getDataCodingScheme()=" + getDataCodingScheme() + ", getOverallMessageLengthBytes()="
				+ Arrays.toString(getOverallMessageLengthBytes()) + ", getUssdString()=" + getUssdString() + ", getOverallMessageLengthBytesReversed()="
				+ Arrays.toString(getOverallMessageLengthBytesReversed()) + ", getUssdStringBytes()=" + Arrays.toString(getUssdStringBytes()) + ", getOverallMessageLength()="
				+ getOverallMessageLength() + ", getVlrNo()=" + getVlrNo() + ", getVlrNoBytes()=" + Arrays.toString(getVlrNoBytes()) + ", getImsi()=" + getImsi() + ", getImsiBytes()="
				+ Arrays.toString(getImsiBytes()) + ", getApplicationIdentifierLen()=" + getApplicationIdentifierLen() + ", getDialogueReferenceLen()=" + getDialogueReferenceLen()
				+ ", getMsisdnLen()=" + getMsisdnLen() + ", getExternalNodeNoLen()=" + getExternalNodeNoLen() + ", getImsiLen()=" + getImsiLen() + ", getVlrNoLen()=" + getVlrNoLen()
				+ ", getDataCodingSchemeLen()=" + getDataCodingSchemeLen() + ", getUssdStringLen()=" + getUssdStringLen() + ", getUssdStringLenInt()=" + getUssdStringLenInt() + "]";
	}
	
	
	
}
