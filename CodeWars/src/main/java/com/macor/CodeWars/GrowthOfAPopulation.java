package com.macor.CodeWars;

public class GrowthOfAPopulation {

	public static void main(String[] args) {

		int p0 = 1500000;
		double percent = 0.25;
		int aug = 1000;
		int p = 2000000;

		System.out.println("###### START ######");

		System.out.println("RESULTADO: " + nbYear(p0, percent, aug, p));

		System.out.println("###### FINISH ######");

	}

	public static int nbYear(int p0, double percent, int aug, int p) {
		int years = 0;
		int goal = 0;

		while (goal < p) {
			goal = (int) (p0 + (p0 * (percent / 100)) + aug);
			p0 = goal;
			years++;
		}

		return years;
	}

	public static int bestPractice(int p0, double percent, int aug, int p) {
		int years = 0;
		while (p0 < p) {
			p0 += p0 * percent / 100 + aug;
			years++;
		}
		return years;
	}

}
