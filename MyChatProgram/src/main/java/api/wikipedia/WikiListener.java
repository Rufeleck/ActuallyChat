package main.java.api.wikipedia;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import main.java.chat.Responder;
import main.java.chat.ResponderAction;
import main.java.chat.component.ChatBox;

public class WikiListener implements ActionListener {
	private ChatBox chat;
	private ArrayList<String> dis = new ArrayList<String>();
	private Responder respond;

	public WikiListener(ChatBox c, Responder r) {
		respond = r;
		chat = c;
		
		try {
			dis = Wikipedia.searchWikiDis(chat.getUserInput());
		} catch (IOException e) {
			e.printStackTrace();
		}
		chat.clearUserInput();
		if(!dis.isEmpty() && !dis.get(0).equals(",[]]")){
			if(dis.size() == 1){
				try {
					String intro = Wikipedia.getIntro(Wikipedia.selectDisambiguation(
							"0", dis));
					if(intro.toLowerCase().contains("nothing here but us chickens"))
						chat.writeToDisplay("STRANGER: " + respond.getGeneric());
					else
						chat.writeToDisplay("STRANGER: " 
								+ intro);
				} catch (IOException e) {
					e.printStackTrace();
					chat.writeToDisplay("STRANGER: " + respond.getGeneric());
				}
		        ResponderAction act = new ResponderAction(respond, chat);
		        chat.replaceActionListener(act);
			}
			else{
				chat.writeToDisplay("STRANGER: I'm a little unclear what you meant, which of these did you mean?");
				for(int i = 0; i< dis.size(); i++)
					chat.writeToDisplay(i + ") " + dis.get(i)); 
			}
		}
		else{
			chat.writeToDisplay("STRANGER: " + respond.getGeneric());
	        ResponderAction act = new ResponderAction(respond, chat);
	        chat.replaceActionListener(act);
		}
			
			
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		chat.writeToDisplay("USER: " + chat.getUserInput());
		try {
			chat.writeToDisplay("STRANGER: " 
					+ Wikipedia.getIntro(Wikipedia.selectDisambiguation(
							chat.getUserInput(), dis)));
		} catch (IOException e1) {
			chat.writeToDisplay("STRANGER: I'm afraid I'm terribly confused can we talk about something else?");
		}
		chat.clearUserInput();
        ResponderAction act = new ResponderAction(respond, chat);
        chat.replaceActionListener(act);

	}

}
