//package main.java.wordGames.synonym;
//
//import java.util.ArrayList;
//import java.util.Scanner;
//
//import javax.swing.JFrame;
//
//import main.java.chat.ResponderAction;
//import main.java.chat.component.ChatBox;
//
///**
//*@author Samuel Miller
//*
//*/
//
//@SuppressWarnings("unused")
//public class SynonymTest {
//
//	
//	public static void main(String[] args) {
//
//		JFrame frame = new JFrame("ChatBox");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        ChatBox ctBx = new ChatBox();
//        frame.add(ctBx);
//        frame.setResizable(false);
//        frame.pack();
//        frame.setVisible(true);
//        ctBx.writeToDisplay("TESTING...");
//		Synonym synonym = new Synonym(ctBx);
//		String word = Synonym.generateWord("src\\main\\resources\\dictionary.txt");
//		ctBx.writeToDisplay("Generated word:\n" + word + "\n\nSynonyms:");
//		for(String s :Synonym.analyzeSynonym(word))
//			ctBx.writeToDisplay(s);
//		ctBx.writeToDisplay("\ninput guesses");
//	    Scanner in = new Scanner(System.in);
//	    String input = in.nextLine();
//    	ctBx.writeToDisplay("Guesses: " + input);
//	    ArrayList<String> analysis = Synonym.analyzeSynonymResponse(word, input); 
//    	ctBx.writeToDisplay("Matched Guesses:");
//	    for(String s : analysis)
//	    	ctBx.writeToDisplay(s);
//	    in.close();
//	}
//
//}
