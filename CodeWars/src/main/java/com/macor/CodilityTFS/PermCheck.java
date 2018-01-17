package com.macor.CodilityTFS;

import java.util.HashMap;
import java.util.Map;

public class PermCheck {

	private static final int MAX_LENGTH = 100000;

	public static void main(String[] args) {

		int[] a = { 4, 1, 3, 3 };

		System.out.println("##### START #####");
		System.out.println("RESULTADO: " + permutation(a));
		System.out.println("##### FINISH #####");

	}

	public static int permutation(int[] a) {
		Map<Integer, Boolean> map = new HashMap<Integer, Boolean>();

		long longitud = a.length;

		if (longitud > MAX_LENGTH) {
			return -1;
		}

		long suma = 0;
		long resultadoSuma = longitud * (longitud + 1) / 2;

		for (int i : a) {

			suma = suma + i;
			Boolean esta = map.get(i);
			if (esta == null) {
				map.put(i, true);
			} else {
				return 0;
			}
		}

		return suma == resultadoSuma ? 1 : 0;

	}

}
