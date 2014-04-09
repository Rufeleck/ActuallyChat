package main.java.chat;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import main.java.FindTheSynonym;
import main.java.GrammarGauntlet;
import main.java.api.wikipedia.WikiListener;
import main.java.chat.component.ChatBox;
import main.java.wordGames.barrettsGames.findTheSynonym.WheresWaldoListener;
import main.java.wordGames.barrettsGames.grammer.GrammerListener;
import main.java.wordGames.madLibs.MadLib;
import main.java.wordGames.madLibs.listeners.MadPlayerListener;
import main.java.wordGames.synonym.listeners.SymPlayerListener;

/**
*@author Samuel Miller
*
*/
public class ResponderAction implements ActionListener{

	private Responder respond;
	private ChatBox chat;
	private boolean game;
	
	public ResponderAction(Responder r, ChatBox c) {
		respond = r;
		chat = c;
		game = false;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		chat.writeToDisplay("USER: " + chat.getUserInput());
			
		if (!game){
			String response = respond.respond(chat.getUserInput());
			if (response.equals("wiki")){
				chat.replaceActionListener(new WikiListener(chat, respond));
			}
			else if(response.equals("game")){
				chat.writeToDisplay("JIGSAW: " + "Would you like to play a game?");
				chat.clearUserInput();
				game = true;
			}
			else{
				chat.writeToDisplay("STRANGER: " + response);
				chat.clearUserInput();
			}
		}
		else{
			if(chat.getUserInput().toLowerCase().contains("yes") || chat.getUserInput().toLowerCase().equals("y") 
					|| chat.getUserInput().toLowerCase().contains("sure")){
				chat.clearUserInput();
				String[] array = {"synonym", "madlibs", "grammer","where's waldo"};
		    	int rand = (int) Math.floor( Math.random() * array.length );
		    	switch(array[rand]){
		    		case"synonym":
		    			chat.replaceActionListener(new SymPlayerListener(chat, respond));
		    			break;
		    		case"madlibs":
						MadLib mad = new MadLib();
						chat.replaceActionListener(new MadPlayerListener(mad, chat, respond));
		    			break;
		    		case"grammer":
		    			GrammarGauntlet grammer = new GrammarGauntlet("lib/en-pos-maxent.bin");
		    			chat.replaceActionListener(new GrammerListener(grammer, chat, respond));
		    			break;
		    		case"where's waldo":
		    			FindTheSynonym waldo = null;
					try {
						waldo = new FindTheSynonym("src\\main\\resources\\dictionary.txt", "lib\\WordNet\\2.1\\dict");
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					chat.replaceActionListener(new WheresWaldoListener(waldo, chat, respond));
		    			break;
		    	}
			}
			else{
				chat.writeToDisplay("STRANGER: " + "Nevermind then");
				chat.clearUserInput();
			}
			game = false;
		}	
	}
}
