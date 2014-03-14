package main.java.wordGames.madLibs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;

import main.java.chat.component.ChatBox;

public class MadTest {

	public static void main(String[] args) {	
		JFrame frame = new JFrame("ChatBox");
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    ChatBox chat = new ChatBox();
	    frame.add(chat);
	    frame.setResizable(false);
	    frame.pack();
	    frame.setVisible(true);
	    chat.writeToDisplay("TESTING...");
		MadLib lib = new MadLib(chat);
		TagSet set = new TagSet();
		TagSet set2 = new TagSet();
	    chat.replaceActionListener(new TestListener(chat, lib));
		ArrayList<String> str = new ArrayList<String>();
		str.add("dog");
		str.add("run");
		str.add("running");
		str.add("pretty");
		set.addFromArray(str);
		chat.writeToDisplay(set.toString());
		//verb, adverb, pronoun, noun, adjective, past verb
		//{1,2,0,3,4,2}
		set2.addVerb("run");
		set2.addAdverb("how");
		set2.addAdverb("when");
		set2.addNoun("dog");
		set2.addNoun("cat");
		set2.addNoun("elephant");
		set2.addAdjective("blue");
		set2.addAdjective("yellow");
		set2.addAdjective("orange");
		set2.addAdjective("bouncy");
		set2.addPastVerb("ran");
		set2.addPastVerb("jumped");
		int[] req = lib.returnRequirements(new TagSet());
		//int[] req = lib.returnRequirements(set2);
		chat.writeToDisplay("You still need " + req[0] + " Verbs, " + req[1] + " Adverbs, "
				+ req[2] + " Pronouns, " + req[3] + " Nouns, " + req[4] + " Adjectives, "  + req[5] + " Past Tense Verbs");
		

	}


}
