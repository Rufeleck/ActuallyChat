package main.java.wordGames.barrettsGames.grammer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import main.java.GrammarGauntlet;
import main.java.chat.Responder;
import main.java.chat.ResponderAction;
import main.java.chat.component.ChatBox;
import main.java.wordGames.madLibs.MadLib;
import main.java.wordGames.madLibs.TagSet;
import main.java.wordGames.synonym.Synonym;

public class GrammerListener implements ActionListener {
	private ChatBox chat;
	private GrammarGauntlet grammer;

	private Responder respond;
	
	public GrammerListener(GrammarGauntlet g, ChatBox c, Responder r) {
		respond = r;
		chat = c;
		grammer = g;
		chat.writeToDisplay("STRANGER: " + grammer.printResultsString());
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		chat.writeToDisplay("USER: " + chat.getUserInput());
		chat.writeToDisplay("STRANGER: " + grammer.evaluateResponseToString(chat.getUserInput()));
		chat.clearUserInput();
        ResponderAction act = new ResponderAction(respond, chat);
        chat.replaceActionListener(act);

	}

}
