package com.iasgrp.stubs.airwide.utils;

import java.util.Random;

public class DialogReferenceFactory {
	private Random random = new Random();

	public int newDialogueReference() {
		return random.nextInt();
	}

}
