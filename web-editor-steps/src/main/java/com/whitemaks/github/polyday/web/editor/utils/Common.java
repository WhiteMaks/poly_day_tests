package com.whitemaks.github.polyday.web.editor.utils;

public class Common {

	public static void sleep(int millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	public static void sleep() {
		sleep(500);
	}
}
