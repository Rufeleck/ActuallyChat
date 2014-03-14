package main.java.wordGames.synonym;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;

import main.java.chat.component.ChatGUI;
import main.java.wordGames.Game;
import main.java.wordGames.synonym.listeners.SymPlayerListener;
import edu.smu.tspell.wordnet.*;

/**
*@author Samuel Miller
*
*/

public class Synonym implements Game {
	
	private ChatGUI ctBx;
	private static WordNetDatabase database;

	//computer gives user a word to find synonyms of (takes in a path to a dictionary file)
	public static String generateWord(String dictionaryPath) {
		try {
			//dictionary array
			ArrayList<String> dictionary = new ArrayList<String>();
			
			//read dictionary
			BufferedReader read = new BufferedReader(new FileReader(dictionaryPath));
			
			String line = null;
			//read the dictionary file line by line
			while((line = read.readLine()) != null){
				//add words to dictionary array
				dictionary.add(line.toLowerCase());
			}
			read.close();
			//find a word that has at least 1 synonym
			String word = "";
			do{
				//grab a random word from the dictionary
				word = dictionary.get(((int) (Math.random() * dictionary.size())) - 1);
			//while there is no synonym to word
			}while( analyzeSynonym(word) == null);
			
			return word;
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//player gives computer a word to find synonyms of
	//returns synonyms of word, if no synonyms are found returns null
	public static ArrayList<String> analyzeSynonym(String word) {
		//get the synsets for the word
		Synset[] synsets = database.getSynsets(word);
		//array for the synonyms
		ArrayList<String> wordFormList = new ArrayList<String>();
		
		//if synsets are found
		if (synsets.length > 0){
			//add all the word forms to the word form list
			for (int i = 0; i < synsets.length; i++){
				
				String[] wordForms = synsets[i].getWordForms();
				
				for (int j = 0; j < wordForms.length; j++){
					wordFormList.add(wordForms[j].toLowerCase());
				}
				
			}
			//remove duplicates
			while(wordFormList.remove(word.toLowerCase()));
			HashSet<String> listToSet = new HashSet<String>(wordFormList);
			ArrayList<String> wordFormFix = new ArrayList<String>(listToSet);
			if(wordFormList.size() > 0)
				return wordFormFix;
		}
		return null;
	}
	
	//Analyzes the player's response to a word to find synonyms of (returns the number of synonyms)
	//(word is the word to compare to, user is the user input)
	public static ArrayList<String> analyzeSynonymResponse(String word, String user){
		//split up the sentence into elements
		String[] split = user.toLowerCase().split( "\\s+" );
		//find all the synonyms to the word given
		ArrayList<String> synonyms = analyzeSynonym(word);
		//integer to keep track of score
		//int score = 0;
		ArrayList<String> match = new ArrayList<String>();
		//for every element in the sentence
		for(int i = 0; i < split.length; i++){
			//if the element in the sentence matches a synonym, add a point
			if (synonyms.contains(split[i]))
				match.add(split[i]);
				//score++;
		}
		return match;
	}

	public Synonym(ChatGUI c) {
		ctBx = c;
		initialize();
	}

	@Override
	public void initialize() {
		System.setProperty("wordnet.database.dir", "lib\\WordNet\\2.1\\dict");
		database = WordNetDatabase.getFileInstance();
		
	}

	@Override
	public void run() {
		//ctBx.replaceActionListener(new SymPlayerListener());

	}
	
	@Override
	public void closeGame(){
		// TODO Auto-generated method stub		
		
	}

	

}
