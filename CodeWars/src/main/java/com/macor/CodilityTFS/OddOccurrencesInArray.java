package com.macor.CodilityTFS;

import java.util.HashSet;

public class OddOccurrencesInArray {

	public static void main(String[] args) {

		int[] a = { 7, 6, 6, 3, 3, 7, 9 };

		System.out.println("###### START ######");

		System.out.println("RESULTADO: " + solution(a));

		System.out.println("###### FINISH ######");

	}

	public static int unpaired(int[] a) {
		int result = 0;

		for (int i = 0; i < a.length; i++) {
			result ^= a[i];
		}

		return result;

	}

	public static int solution(int[] A) {
		// write your code in Java SE 8
		int elem = 0;

		for (int i = 0; i < A.length; i++) {
			elem ^= A[i];
		}
		return elem;
	}

	public static int solution2(int[] A) {

		HashSet<Integer> inter = new HashSet<Integer>();
		int resultado = -1;

		for (int i = 0; i < A.length; i++) {
			if (inter.add(A[i])) {

				resultado = A[i];
			}

		}

		return resultado;
	}

}
