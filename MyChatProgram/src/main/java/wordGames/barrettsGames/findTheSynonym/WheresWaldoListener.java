package main.java.wordGames.barrettsGames.findTheSynonym;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import main.java.FindTheSynonym;
import main.java.GrammarGauntlet;
import main.java.chat.Responder;
import main.java.chat.ResponderAction;
import main.java.chat.component.ChatBox;

public class WheresWaldoListener implements ActionListener {
	private ChatBox chat;
	private FindTheSynonym waldo;

	private Responder respond;

	public WheresWaldoListener(FindTheSynonym s, ChatBox c, Responder r) {
		respond = r;
		chat = c;
		waldo = s;
		chat.writeToDisplay("STRANGER: " + waldo.printResultsString());
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		chat.writeToDisplay("USER: " + chat.getUserInput());
		if(waldo.evaluateResponse(chat.getUserInput()))
			chat.writeToDisplay("STRANGER: " + "You are correct!!");
		else
			chat.writeToDisplay("STRANGER: " + "Unfortunatly you are incorrect :<");
		chat.clearUserInput();
        ResponderAction act = new ResponderAction(respond, chat);
        chat.replaceActionListener(act);

	}

}
