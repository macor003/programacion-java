package com.macor.CodeWars;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class CountingDuplicates {

	public static void main(String[] args) {

		String str = "abcdA";

		int q = printDuplicateChar(str);

		System.out.println("Quantity: " + q);
	}

	private static int printDuplicateChar(String str) {
		String st = str.toUpperCase();
		char[] cha = st.toCharArray();
		int value = 0;

		Map<Character, Integer> map = new HashMap<Character, Integer>();
		for (Character c : cha) {
			if (map.containsKey(c)) {
				map.put(c, map.get(c) + 1);
			} else {
				map.put(c, 1);
			}
		}

		Set<Map.Entry<Character, Integer>> entrySet = map.entrySet();
		for (Map.Entry<Character, Integer> entry : entrySet) {
			if (entry.getValue() > 1) {
				value = value + 1;
			}
		}

		return value;

	}

}
