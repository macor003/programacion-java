package com.macor.CodeWars;

public class DetectPangram {

	public static void main(String[] args) {

		String pangram1 = "The quick brown fox jumps over the lazy dog.";

		String pangram2 = "You shall not pass!";

		String text = pangram2.replaceAll(" ", "");
		String[] abecedario = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q",
				"R", "S", "T", "U", "V", "W", "X", "Y", "Z" };
		boolean really;

		for (String abc : abecedario) {
			System.out.println("letters: " + abc);
			if (!text.equalsIgnoreCase(abc)) {
				System.out.println("true");
				really = true;
				break;
			} else {
				System.out.println("false");
				really = false;
			}

		}
	}

}
