package main.java.wordGames.madLibs;

import java.util.ArrayList;

public class TagSet {
	
	ArrayList<String> verbs;
	ArrayList<String> adjective;
	ArrayList<String> adverb;
	ArrayList<String> pronoun;
	ArrayList<String> noun;
	ArrayList<String> pastNoun;
	

	public TagSet() {
		
	}
	
	public void addVerb(String s){
		verbs.add(s);
	}
	
	public void addAdjective(String s){
		adjective.add(s);
	}
	
	public void addAdverb(String s){
		adverb.add(s);
	}
	
	public void addPronoun(String s){
		pronoun.add(s);
	}
	
	public void addNoun(String s){
		noun.add(s);
	}
	
	public void addPastNoun(String s){
		pastNoun.add(s);
	}
	
	public ArrayList<String> getAdjective(){
		return adjective;
	}
	
	public ArrayList<String> getAdverb(){
		return adverb;
	}
	
	public ArrayList<String> getPronoun(){
		return pronoun;
	}
	
	public ArrayList<String> getNoun(){
		return noun;
	}
	
	public ArrayList<String> getPastNoun(){
		return pastNoun;
	}
	
	public ArrayList<String> getVerb(){
		return verbs;
	}

}
