package main.java.wordGames.madLibs;

import static main.java.chat.util.Util.randomFromArray;

import java.util.ArrayList;

import edu.smu.tspell.wordnet.WordNetDatabase;
import main.java.chat.component.ChatGUI;
import main.java.wordGames.Game;
import main.java.wordGames.GameSpace;

/**
*@author Samuel Miller
*
*/

public class MadLib implements Game {
	
	private ChatGUI chat;
	//verb, adverb, pronoun, noun, adjective, past verb
	private int[] needed;
	private int madLibID;
	private final int NUMBEROFMADLIBS = 1;
	
	private final int[] zoo =  {1,2,0,3,4,2};

	public MadLib(ChatGUI c) {
		chat = c;
		initialize();
	}
	
	private int pickRandomMadLibID(){
		return (int)(Math.random()* NUMBEROFMADLIBS );
	}
	

	public String GenerateMadLib(TagSet set) {
		String mad = null;
		if(checkRequirements(set))
			switch(madLibID){
				case 0:
					mad = "Today I went to the zoo. I saw a " + set.getAdjective().get(0) + " "
							+ set.getNoun().get(0) + " jumping up "
							+ "and down in its tree. He " + set.getPastVerb().get(0) + " "
							+ set.getAdverb().get(0) + " through "
							+ "the large tunnel that led to its "
							+ set.getAdjective().get(1) + " " + set.getNoun().get(1)
							+ ". I got some peanuts and passed them through the cage to a gigantic gray "
							+ set.getNoun().get(2) + " towering above my head."
							+ " Feeding that animal made me hungry. I went to get a "
							+ set.getAdjective().get(2) + " scoop of ice "
							+ "cream. It filled my stomach. Afterwards I had to "
							+ set.getVerb().get(0) + " " + set.getAdverb().get(1)
							+ " to catch our bus. When I got home I " + set.getPastVerb().get(1)
							+ " my mom for a " + set.getAdjective().get(3) + " day at the zoo.";
					break;
			}
		return mad;
	}
	
	public int[] countTagSet(TagSet set){
		int[] count = new int[6];
		//verb, adverb, pronoun, noun, adjective, past noun
		count[0] = set.getVerb().size();
		count[1] = set.getAdverb().size();
		count[2] = set.getPronoun().size();
		count[3] = set.getNoun().size();
		count[4] = set.getAdjective().size();
		count[5] = set.getPastVerb().size();
		return count;
	}
	
	public int[] returnRequirements(TagSet set){
		int[] require = new int[6];
		int[] tags = countTagSet(set);
		for(int i = 0; i < require.length; i++){
			require[i] = needed[i] - tags[i];
			if(require[i] < 0)
				require[i] = 0;
		}
		return require;
	}
	
	public boolean checkRequirements(TagSet set){
		boolean check = true;
		for(int i: returnRequirements(set))
			if(i > 0)
				check = false;
		return check;
	}

	@Override
	public void initialize() {
		madLibID = pickRandomMadLibID();
		switch(madLibID){
		case 0:
			needed = zoo;
			break;
		}
	}

	public int[] getNeeded(){
		return needed;
	}
	
	@Override
	public void closeGame() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
}
