package com.macor.CodilityTFS;

public class Coins {

	public static void main(String[] args) {
		int[] A = { 1, 1, 0, 1, 1, 0, 0, 1 };

		System.out.println("RESULTADO: " + revert(A));
	}

	public static int revert(int[] A) {

		int heads = 0;
		int tails = 0;

		for (int i : A) {
			if (i == 0) {
				heads++;
			} else if (i == 1) {
				tails++;
			}
		}

		return heads > tails ? tails : heads;

	}

}
