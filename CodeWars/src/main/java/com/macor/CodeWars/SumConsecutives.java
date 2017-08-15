package com.macor.CodeWars;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class SumConsecutives {

	public static void main(String[] args) {

		List<Integer> list = new LinkedList<Integer>(Arrays.asList(1, 4, 4, 4, 0, 4, 3, 3, 1));

		List<Integer> resultado = new ArrayList<Integer>();
		int sum = 0;

		for (int i = 0; i <= list.size() - 1; i++) {
			if (i == list.size() - 1) {
				resultado.add(i, list.get(i));
			} else if (list.get(i) == list.get(i + 1)) {
				
				sum = (list.get(i) + list.get(i + 1));
				resultado.add(i, sum);
				list.remove(i);
			} else {
				resultado.add(i, list.get(i));
			}
			System.out.println(resultado);
		}
	}

}
