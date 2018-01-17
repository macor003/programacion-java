package com.macor.CodilityTFS;

public class FormatNumber {

	private static int number1 = 14;
	private static int number2 = 10;
	private static int number3 = 9;

	public static void main(String[] args) {

		String number = "0 - 22 1985--324";
		System.out.println("REUSLTADO: " + format2(number));

	}

	public static String format(String number) {


		if (number == null) {
			return null;
		}
		String clean2 = number.replaceAll("\\D", "");
		System.out.println("LIMPIO: " + clean2);

		System.out.println("longitud " + clean2.length());

		if ((clean2.length() == number1)) {

			String cad1 = clean2.substring(0, 3);
			String cad2 = clean2.substring(3, 6);
			String cad3 = clean2.substring(6, 9);
			String cad4 = clean2.substring(9, 12);
			String cad5 = clean2.substring(12, 14);

			String resultado = cad1 + "-" + cad2 + "-" + cad3 + "-" + cad4 + "-" + cad5;

			return resultado;

		} else if (clean2.length() == number2) {
			String cad1 = clean2.substring(0, 3);
			String cad2 = clean2.substring(3, 6);
			String cad3 = clean2.substring(6, 8);
			String cad4 = clean2.substring(8, 10);

			String resultado = cad1 + "-" + cad2 + "-" + cad3 + "-" + cad4;
			return resultado;
		} else if (clean2.length() == number3) {
			String cad1 = clean2.substring(0, 3);
			String cad2 = clean2.substring(3, 6);
			String cad3 = clean2.substring(6, 9);

			String resultado = cad1 + "-" + cad2 + "-" + cad3;

			return resultado;
		}

		return null;

	}

	public static String format2(String number) {
		String result = "-1";

		if (number == null) {
			return null;
		}

		String numero = number.replaceAll("\\D", "").replaceAll("(\\d{3})(?=\\d{2}$)|(\\d{3})(?!$)", "$1$2-");

		return numero;
	}

}
