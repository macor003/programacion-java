package com.macor.CodilityTFS;

import java.util.Arrays;

public class CyclicRotation {

	public static void main(String[] args) {
		int[] A = { 3, 8, 9, 7, 6 };
		int K = 3;

		System.out.println("###### START ######");

		System.out.println("RESULTADO: " + Arrays.toString(solution(A, K)));

		System.out.println("###### FINISH ######");

	}

	public static int[] solution(int[] A, int K) {
		int N = A.length;
		if (N == 0) {
			return A;
		}

		if (K >= N) {
			K %= N;
		}

		if (K == 0) {
			return A;
		}

		int[] rotA = new int[N];
		for (int i = 0; i < N; i++)
			rotA[i] = (i < K) ? A[N + i - K] : A[i - K];
		return rotA;
	}

}
