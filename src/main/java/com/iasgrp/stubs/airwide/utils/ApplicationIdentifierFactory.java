package com.iasgrp.stubs.airwide.utils;

import java.util.Random;

public class ApplicationIdentifierFactory {
	private Random random = new Random();

	public int newApplicationIdentifier() {
		return random.nextInt();
	}

}
