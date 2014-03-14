package main.java.wordGames.madLibs;

import java.util.ArrayList;

import main.java.chat.util.MyPOS;

public class TagSet {
	
	ArrayList<String> verbs;
	ArrayList<String> adjective;
	ArrayList<String> adverb;
	ArrayList<String> pronoun;
	ArrayList<String> noun;
	ArrayList<String> pastVerb;

	public TagSet() {
		verbs = new ArrayList<String>();
		adjective = new ArrayList<String>();
		adverb = new ArrayList<String>();
		pronoun = new ArrayList<String>();
		noun = new ArrayList<String>();
		pastVerb = new ArrayList<String>();
	}
	
	//give an array list and add all the tagged words to the object
	public void addFromArray(ArrayList<String> str){
		if(str != null){
			String input = "";
			for(String s : str)
				input += s + " ";
			MyPOS pos = new MyPOS(input);
			String[] tags = pos.getTags();
			for(int i = 0; i < tags.length; i++){
				if(MyPOS.translateTag(tags[i]).equalsIgnoreCase("Verb, Past Tense"))
					pastVerb.add(str.get(i));
				else
					switch(MyPOS.translateSimpleTag(tags[i]).toLowerCase()){
						case "verb":
							verbs.add(str.get(i));
							break;
						case "adjective":
							adjective.add(str.get(i));
							break;
						case "adverb":
							adverb.add(str.get(i));
							break;
						case "pronoun":
							pronoun.add(str.get(i));
							break;
						case "noun":
							noun.add(str.get(i));
							break;
					}
			}
		}
	}
	
	public void addString(String str){
		if(str != null){
			MyPOS pos = new MyPOS(str);
			String[] tags = pos.getTags();
			String[] sent = pos.getSent();
			
			for(int i = 0; i < tags.length; i++){
				if(MyPOS.translateTag(tags[i]).equalsIgnoreCase("Verb, Past Tense"))
					pastVerb.add(sent[i]);
				else{
					switch(MyPOS.translateSimpleTag(tags[i]).toLowerCase()){
						case "verb":
							verbs.add(sent[i]);
							break;
						case "adjective":
							adjective.add(sent[i]);
							break;
						case "adverb":
							adverb.add(sent[i]);
							break;
						case "pronoun":
							pronoun.add(sent[i]);
							break;
						case "noun":
							noun.add(sent[i]);
							break;
					}
				}
			}
		}
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
	
	public void addPastVerb(String s){
		pastVerb.add(s);
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
	
	public ArrayList<String> getPastVerb(){
		return pastVerb;
	}
	
	public ArrayList<String> getVerb(){
		return verbs;
	}

	//verb, adverb, pronoun, noun, adjective, past verb
	public String toString(){
		String str = "Verbs: ";
		for(String s: verbs)
			str += s + " ";
		str += "\n";
		str += "Adverbs: ";
		for(String s: adverb)
			str += s + " ";
		str += "\n";
		str += "Pronouns: ";
		for(String s: pronoun)
			str += s + " ";
		str += "\n";
		str += "Nouns: ";
		for(String s: noun)
			str += s + " ";
		str += "\n";
		str += "Adjectives: ";
		for(String s: adjective)
			str += s + " ";
		str += "\n";
		str += "Past Verbs: ";
		for(String s: pastVerb)
			str += s + " ";
		str += "\n";
		
		return str;
	}

}
