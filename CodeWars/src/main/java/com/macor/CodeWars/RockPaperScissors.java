package com.macor.CodeWars;

public class RockPaperScissors {

	public static void main(String[] args) {

		/*
		 * @author: Mario Ortega
		 * 
		 * @version: 14/08/2017 ROCK PAPER SCISSORS CodeWars. I did it the first
		 * solution The second one was made it by the community of CodeWars. The sysout
		 * are return
		 */

		String player1 = "scissors";
		String player2 = "rock";

		// First Solution

		if (player1 == player2) {
			System.out.println("Draw!");
		} else if (player1 == "rock" && player2 == "scissors") {
			System.out.println("Player 1 won!");
		} else if (player1 == "scissors" && player2 == "paper") {
			System.out.println("Player 1 won!");
		} else if (player1 == "paper" && player2 == "rock") {
			System.out.println("Player 1 won!");
		} else {
			System.out.println("Player 2 won!");
		}

		// Second Solution

		if (player1 == player2)
			System.out.println("Draw!");
		int p = (player1 + player2).equals("scissorspaper") || (player1 + player2).equals("rockscissors")
				|| (player1 + player2).equals("paperrock") ? 1 : 2;
		System.out.println("Player " + p + " won!");
		;
	}

}
