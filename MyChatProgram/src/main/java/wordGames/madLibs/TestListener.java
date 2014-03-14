package main.java.wordGames.madLibs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import main.java.chat.component.ChatBox;

public class TestListener implements ActionListener {
	
	private ChatBox chat;
	private MadLib mad;
	private TagSet tags;
	
	public TestListener(ChatBox c, MadLib m){
		chat = c;
		mad = m;
		tags = new TagSet();
	}
	
	//verb, adverb, pronoun, noun, adjective, past verb
	@Override
	public void actionPerformed(ActionEvent e) {
		tags.addString(chat.getUserInput());
		chat.clearUserInput();
		if(mad.checkRequirements(tags))
			chat.writeToDisplay(mad.GenerateMadLib(tags));
		else{
			int[] req = mad.returnRequirements(tags);
			chat.writeToDisplay("You still need " + req[0] + " Verbs, " + req[1] + " Adverbs, "
					+ req[2] + " Pronouns, " + req[3] + " Nouns, " + req[4] + " Adjectives, "  + req[5] + " Past Tense Verbs");
		}
	}

}
