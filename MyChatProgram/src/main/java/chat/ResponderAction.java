package main.java.chat;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import main.java.chat.component.ChatBox;

public class ResponderAction implements ActionListener{

	private Responder respond;
	private ChatBox chat;
	
	public ResponderAction(Responder r, ChatBox c) {
		respond = r;
		chat = c;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		chat.writeToDisplay("USER: " + chat.getUserInput());
		chat.writeToDisplay("STRANGER: " + respond.respond(chat.getUserInput()));
		chat.clearUserInput();
	}

}
