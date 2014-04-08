package main.java.wordGames.barrettsGames.grammer;

import javax.swing.JFrame;

import main.java.GrammarGauntlet;
import main.java.chat.MyResponder;
import main.java.chat.ResponderAction;
import main.java.chat.component.ChatBox;

public class ExampleGrammarGauntlet {

	public static void main(String[] args) {
		//Step 1: Create a new GrammarGauntlet object (with, or without, a user sentence)
		GrammarGauntlet game=new GrammarGauntlet("lib/en-pos-maxent.bin", "Hello, this is my sentence.");
		//OR
		GrammarGauntlet game2=new GrammarGauntlet("lib/en-pos-maxent.bin");
		
		//Step 2: Use .printResultsString() OR Use the getters for the individual variables in order to display or use the variable values.
		//game.printResultsString()
		System.out.println(game.printResultsString()+"\n");
		//or
		//game.get: CorrectWords
		String[] stringArray=game.getCorrectWords();
		for(String s: stringArray){
			System.out.println(s);
		}
		
		//Step 3: Use .evaluateResponse() to compare the user's 
		System.out.print("\n"+game.evaluateResponse("User's Answer"));


		JFrame frame = new JFrame("ChatBox");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ChatBox ctBx = new ChatBox();
        frame.add(ctBx);
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);
        MyResponder respond = new MyResponder();
		GrammarGauntlet grammer = new GrammarGauntlet("lib/en-pos-maxent.bin");
		ctBx.replaceActionListener(new GrammerListener(grammer, ctBx, respond));

		

	}//main

}//class
