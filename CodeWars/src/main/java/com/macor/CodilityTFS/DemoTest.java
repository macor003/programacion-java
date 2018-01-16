package com.macor.CodilityTFS;

import java.util.Arrays;

public class DemoTest {

	public static void main(String[] args) {

		int[] A = { 1, 5, 6, 4, 1, 2 };
		System.out.println("MAYOR: " + getMissingNumber(A));
		//System.out.println("ORDENADO: " + ordenado(A));
	}

	public static int demoMayor(int[] A) {
		int result = 1;
		int mayor = 0;
		int cadidato = 0;
		int rest = 1;

		for (int i : A) {
			if (i > mayor) {
				result = i;
				mayor = i;
			}
		}
		
		Arrays.sort(A);
		String b = Arrays.toString(A);
		
		for (int i : A) {
			
		}

		return result;
	}

	public static int getMissingNumber(int[] A) {
		int longitud = A.length;
		boolean[] usados = new boolean[longitud + 1];

		for (int i = 0; i < longitud; i++) {
			if (A[i] <= longitud && A[i] >= 0)
				usados[A[i]] = true;
		}

		for (int k = 1; k <= longitud; k++) {
			if (usados[k] == false)
				return k;
		}
		return longitud + 1;
	}

	public static String ordenado(int[] A) {
		Arrays.sort(A);
		String b = Arrays.toString(A);

		return b;
	}

}
