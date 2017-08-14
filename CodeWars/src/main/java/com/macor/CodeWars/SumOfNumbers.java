package com.macor.CodeWars;

public class SumOfNumbers {

	public static void main(String[] args) {

		/*
		 * @author: Mario Ortega
		 * 
		 * @version: 14/08/2017 Sum of Number CodeWars. I did it the first
		 * solution The second one was made it by the community of CodeWars. The sysout
		 * are return
		 */

		int a = -1;
		int b = 2;

		// First Solution

		int max = Math.max(a, b);
		int min = Math.min(a, b);
		int result = 0;

		if (min == max) {
			System.out.println(min);
		} else {
			for (int i = min; i <= max; i++) {
				result += i;
			}
			System.out.println(result);
		}

		// Second Solution

		System.out.println((a + b) * (Math.abs(a - b) + 1) / 2);

	}

}
