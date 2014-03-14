package main.java.wordGames.synonym.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import main.java.chat.MyResponder;
import main.java.chat.Responder;
import main.java.chat.ResponderAction;
import main.java.chat.component.ChatBox;
import main.java.wordGames.synonym.Synonym;


/**
*@author Samuel Miller
*
*/

//Player responds to computer's word
public class SymPlayerListener implements ActionListener {
	ChatBox chat;
	Synonym syn;
	boolean first;
	String word;
	Responder respond;

	public SymPlayerListener(Synonym s, ChatBox c, Responder r) {
		respond = r;
		chat = c;
		syn = s;
		first = true;
		chat.writeToDisplay("Okay then. So I'll give you a word, and you try to name as many"
				+ " synonyms to the word as you possibly can, sound good?");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(!first){
		    ArrayList<String> analysis = Synonym.analyzeSynonymResponse(word, chat.getUserInput());
		    chat.clearUserInput();
	    	String str = "";
	    	if(analysis.size() > 1){
			    for(String s : analysis)
			    	str += s + " ";
			    chat.writeToDisplay("I'm not very good at this, but I think " + str + "are synonyms,"
			    		+" but I'm not sure about the rest");
	    	}
	    	else
	    		chat.writeToDisplay("I guess we're both pretty bad at this :)");
	        ResponderAction act = new ResponderAction(respond, chat);
	        chat.replaceActionListener(act);
		}
		else{
		    chat.clearUserInput();
			word = Synonym.generateWord("src\\main\\resources\\dictionary.txt");
			chat.writeToDisplay("Okay, how about \"" + word + "\"");
			first = false;
		}
		

	}

}
