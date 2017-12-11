package com.macor.CodeWars;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidPhoneNumber {

	public static void main(String[] args) {
		String phone = "123 456-7890";

		String regex = "[0-9]\\s[0-9]-[0-9]";
		Pattern pattern = Pattern.compile(regex);
		Matcher mat = pattern.matcher(phone);
		boolean result = mat.find() ? true : false;

		System.out.println("RESULTADO: " + result);

	}

}
