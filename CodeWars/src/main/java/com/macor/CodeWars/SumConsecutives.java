package com.macor.CodeWars;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class SumConsecutives {

	public static void main(String[] args) {

		// List<Integer> list = new LinkedList<Integer>(Arrays.asList(1, 4, 4, 4, 0, 4,
		// 3, 3, 1));
		List<Integer> list = new LinkedList<Integer>(Arrays.asList(2, 2, -4, 4, 5, 5, 6, 6, 6, 6, 6, 1));

		practica(list);
		// System.out.println("RESULTADO: " + bestPractice(list));

	}

	public static List<Integer> marioPractice(List<Integer> list) {
		List<Integer> resultado = new ArrayList<Integer>();
		int sum = 0;
		for (int i = 0; i <= list.size() - 1; i++) {
			System.out.println(list.get(i));
			if (i == list.size() - 1) {
				resultado.add(i, list.get(i));

			} else if (list.get(i) == list.get(i + 1)) {
				sum = (list.get(i) + list.get(i + 1));
				if ((list.get(i) == list.get(i + 2))) {
					sum = sum + list.get(i);

					if ((list.get(i) == list.get(i + 3))) {
						sum = sum + list.get(i);

						if ((list.get(i) == list.get(i + 4))) {
							sum = sum + list.get(i);
							list.remove(i + 2);
						}
						list.remove(i + 2);
					}

					list.remove(i + 2);
				}
				resultado.add(i, sum);
				list.remove(i);
			} else {
				resultado.add(i, list.get(i));
			}
		}

		return resultado;

	}

	/*
	 * MEJOR PRACTICA SEGUN CODE WARS
	 */
	public static LinkedList<Integer> bestPractice(List<Integer> s) {
		int previous = Integer.MAX_VALUE;
		LinkedList<Integer> l = new LinkedList<>();

		for (Integer v : s) {
			System.out.println("PREVIOUS: " + previous);
			l.add(v == previous ? l.pollLast() + v : v);
			previous = v;
			System.out.println("pollLast: " + l.pollLast());
		}
		return l;
	}

	public static void practica(List<Integer> list) {
		LinkedList<Integer> l = new LinkedList<>();
		for (Integer num : list) {
			l.add(num);
			System.out.println("pollLast: " + l.pollLast());
		}

	}

}
