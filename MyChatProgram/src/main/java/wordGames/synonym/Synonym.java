package main.java.wordGames.synonym;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import main.java.chat.component.ChatBox;
import main.java.wordGames.Game;
import main.java.wordGames.synonym.listeners.PlayerListener;
import edu.smu.tspell.wordnet.*;

public class Synonym implements Game, ActionListener {
	
	ChatBox ctBx;

	//computer gives user a word to find synonyms of
	String generateWord() {
		return null;
	}
	
	//player gives computer a word to find synonyms of
	//returns synonyms of word
	String[] analyzeSynonym(String str) {
		return null;
	}
	
	//analyses the player's response to a word to find synonyms of
	String analyseSynonymResponse(String str){
		return null;
	}

	public Synonym(ChatBox c) {
		ctBx = c;
		initialize();
	}

	@Override
	public void initialize() {
		System.setProperty("wordnet.database.dir", "lib\\WordNet\\2.1\\dict");
		ctBx.replaceActionListener(new PlayerListener());
		
	}

	@Override
	public void run() {
		

	}
	
	@Override
	public void closeGame(){
		// TODO Auto-generated method stub		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
