package main.java.wordGames.barrettsGames.findTheSynonym;


import java.io.IOException;

import main.java.FindTheSynonym;

public class ExampleFindTheSynonym {

	public static void main(String[] args) throws IOException {
		// Step 1: Create a new FindTheSynonym object
		FindTheSynonym game = new FindTheSynonym("src\\main\\resources\\dictionary.txt","lib\\WordNet\\2.1\\dict");

		// Step 2: Use printResultsString() OR Use the getters for individual
		// variables in order to display or use the variable values
		// game.printResultsString()
		System.out.println(game.printResultsString());
		// or
		// game.get: CorrectWord, CorrectSynonym, IncorrectWord1,
		// IncorrectSynonym1, IncorrectWord2, IncorrectSynonym2, IncorrectWord3,
		// IncorrectSynonym3,
		System.out.println(game.getCorrectWord() + "="
				+ game.getCorrectSynonym() + "\n" + game.getIncorrectWord1()
				+ "=" + game.getIncorrectSynonym1() + "\n"
				+ game.getIncorrectWord2() + "=" + game.getIncorrectSynonym2()
				+ "\n" + game.getIncorrectWord3() + "="
				+ game.getIncorrectSynonym3() + "\n");
		// Step 3: Use .evaluateResponse(String) to check the user's answer
		// against the correct answers
		System.out.println(game.evaluateResponse(new String("UserAnswerHere")));

	}// main

}// class
