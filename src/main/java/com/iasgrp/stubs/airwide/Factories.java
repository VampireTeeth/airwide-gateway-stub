package com.iasgrp.stubs.airwide;

import com.iasgrp.stubs.airwide.utils.ApplicationIdentifierFactory;
import com.iasgrp.stubs.airwide.utils.DialogReferenceFactory;
import com.iasgrp.stubs.airwide.utils.MsisdnFactory;
import com.iasgrp.stubs.airwide.utils.UssdStringFactory;

public class Factories {
	private final static MsisdnFactory msisdn = new MsisdnFactory();
	private final static ApplicationIdentifierFactory applicationIdentifier = new ApplicationIdentifierFactory();
	private final static UssdStringFactory ussdString = new UssdStringFactory();
	private final static DialogReferenceFactory dialogReference = new DialogReferenceFactory();
	
	public static final MsisdnFactory msisdnFactory() {
		return msisdn;
	}
	
	public static final ApplicationIdentifierFactory applicationidentifierFactory() {
		return applicationIdentifier;
	}
	
	public static final UssdStringFactory ussdStringFactory() {
		return ussdString;
	}
	
	public static final DialogReferenceFactory dialogReferenceFactory() {
		return dialogReference;
	}
	

}
