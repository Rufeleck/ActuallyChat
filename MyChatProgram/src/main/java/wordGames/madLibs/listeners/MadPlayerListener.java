package main.java.wordGames.madLibs.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import main.java.chat.Responder;
import main.java.chat.ResponderAction;
import main.java.chat.component.ChatBox;
import main.java.wordGames.madLibs.MadLib;
import main.java.wordGames.madLibs.TagSet;


/**
*@author Samuel Miller
*
*/

//Player responds to computer's madLib
public class MadPlayerListener implements ActionListener {
	private ChatBox chat;
	private MadLib mad;
	private TagSet tags;
	private boolean first;
	private int tries;
	
	private Responder respond;

	public MadPlayerListener( MadLib m, ChatBox c, Responder r) {
		respond = r;
		chat = c;
		mad = m;
		tags = new TagSet();
		chat.writeToDisplay("STRANGER: Okay then. So how about a mad lib? I actually have one here,"
				+ " They're always worth a laugh.");
		first = true;
		tries = 0;
	}

	@Override
	public void actionPerformed(ActionEvent e) {	
		chat.writeToDisplay("USER: " + chat.getUserInput());
		if (!first){
			if(tries < 15){
				tags.addString(chat.getUserInput());
				chat.clearUserInput();
				if(mad.checkRequirements(tags)){
					chat.writeToDisplay("STRANGER: " + mad.GenerateMadLib(tags));
					chat.writeToDisplay("lol, I really do love mad libs");
			        ResponderAction act = new ResponderAction(respond, chat);
			        chat.replaceActionListener(act);
				}
				else{
					int[] req = mad.returnRequirements(tags);
					chat.writeToDisplay("STRANGER: I still need " + (req[0] > 0 ? req[0] + " Verbs, " : "") 
							+ (req[1] > 0 ? req[1] + " Adverbs, " : "")
							+ (req[2] > 0 ? req[2] + " Pronouns, " : "")
							+ (req[3] > 0 ? req[3] + " Nouns, " : "")
							+ (req[4] > 0 ? req[4] + " Adjectives, " : "")
							+ (req[5] > 0 ? req[5] + " Past Tense Verbs." : ""));
					tries++;
				}
			}
			else{
				chat.writeToDisplay("STRANGER: If you're not really interested,"
						+ " maybe we should do something else...");
		        ResponderAction act = new ResponderAction(respond, chat);
		        chat.replaceActionListener(act);
			}
				
		}
		else{
			int[] req = mad.returnRequirements(tags);
			chat.writeToDisplay("STRANGER: Okay so I'm gonna need "  + (req[0] > 0 ? req[0] + " Verbs, " : "") 
					+ (req[1] > 0 ? req[1] + " Adverbs, " : "")
					+ (req[2] > 0 ? req[2] + " Pronouns, " : "")
					+ (req[3] > 0 ? req[3] + " Nouns, " : "")
					+ (req[4] > 0 ? req[4] + " Adjectives, " : "")
					+ (req[5] > 0 ? req[5] + " Past Tense Verbs." : ""));
			first = false;
		}

	}

}
