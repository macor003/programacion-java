package com.macor.CodilityTFS;

public class FrogJmp {

	public static void main(String[] args) {
		int x = 3;
		int y = 999111321;
		int d = 7;

		System.out.println("RESULTADO: " + jump(x, y, d));

	}

	public static int jump(int x, int y, int d) {
		int result = 0;

		if (x == y) {
			return 0;
		}

		while (x < y) {
			x = x + d;			
			result++;
		}

		return result;
	}

}
