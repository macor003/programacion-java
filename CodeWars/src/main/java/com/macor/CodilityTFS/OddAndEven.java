package com.macor.CodilityTFS;

public class OddAndEven {

	public static void main(String[] args) {

		String S = "011100";
		System.out.println("RESULTADO: " + steps(S));
	}

	public static int steps(String S) {
		int steps = 0;

		int V = Integer.parseInt(S, 2);

		while (V > 0) {
			if ((V % 2) == 0) {
				V = V / 2;
				System.out.println(V / 2);
				steps++;
			} else {
				V = V - 1;
				steps++;
			}
		}

		return steps;

	}

}
