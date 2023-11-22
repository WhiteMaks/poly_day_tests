package com.whitemaks.github.polyday.web.editor.utils;

import java.util.Random;

public class RandomGenerator {
	private static final String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private static final String lower = upper.toLowerCase();
	private static final String digits = "0123456789";
	private static final String alphanum = upper + lower + digits;

	public static String randomString(int length) {
		var buf = new char[length];
		var symbols = alphanum.toCharArray();
		var random = new Random();

		for (int idx = 0; idx < buf.length; ++idx)
			buf[idx] = symbols[random.nextInt(symbols.length)];
		return new String(buf);
	}
}
