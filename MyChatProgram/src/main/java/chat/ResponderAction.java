package main.java.chat;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import main.java.chat.util.*;
import main.java.chat.component.ChatBox;
import main.java.wordGames.madLibs.MadLib;
import main.java.wordGames.madLibs.listeners.MadPlayerListener;
import main.java.wordGames.synonym.Synonym;
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
			if (response != null){
				chat.writeToDisplay("STRANGER: " + response);
				chat.clearUserInput();
			}
			else{
				chat.writeToDisplay("STRANGER: " + "Would you like to play a game?");
				chat.clearUserInput();
				game = true;
			}
		}
		else{
			if(chat.getUserInput().toLowerCase().equals("yes") || chat.getUserInput().toLowerCase().equals("y") 
					|| chat.getUserInput().toLowerCase().equals("sure")){
				chat.clearUserInput();
				String[] array = {"synonym", "madlibs"};
		    	int rand = (int) Math.floor( Math.random() * array.length );
				if(array[rand].equals("synonym")){
					chat.replaceActionListener(new SymPlayerListener(chat, respond));
				}
				else{
					MadLib mad = new MadLib();
					chat.replaceActionListener(new MadPlayerListener(mad, chat, respond));
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
