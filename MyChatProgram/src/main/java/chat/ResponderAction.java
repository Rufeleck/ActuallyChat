package main.java.chat;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import main.java.GrammarGauntlet;
import main.java.chat.component.ChatBox;
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
				//TODO wiki handler
			}
			else if(response.equals("game")){
				chat.writeToDisplay("Jigsaw: " + "Would you like to play a game?");
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
