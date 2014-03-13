package main.java.wordGames.madLibs;

import static main.java.chat.util.Util.randomFromArray;
import main.java.chat.component.ChatGUI;
import main.java.wordGames.Game;
import main.java.wordGames.GameSpace;

public class MadLib implements GameSpace {
	
	private ChatGUI chat;
	//verb, adverb, pronoun, noun, adjective, past noun
	private int[] needed;

	public MadLib(ChatGUI c) {
		chat = c;
		needed = pickRandomMadLibRequirements();
	}
	
	private int[] pickRandomMadLibRequirements(){
		switch(randomFromArray("1")){
		case "1":
			//zoo
			int[] i = {1,2,0,3,4,2};
			return i;
		case "2":
			int[] i2 = {0,0,0,0,0,0};
			return i2;
		}
		return null;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

	}

	@Override
	public void runGame(Game game) {
		// TODO Auto-generated method stub

	}

	@Override
	public void closeGames(Game game) {
		// TODO Auto-generated method stub

	}
	

	public static String GenerateMadLib() {
		return null;

	}
}
