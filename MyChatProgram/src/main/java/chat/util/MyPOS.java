package main.java.chat.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.util.Sequence;
/**
*@author Samuel Miller
*
*/
public class MyPOS {

	// instances
	private Sequence[] topSequences = null;
	private String[] tags=null;
	private String[] sent=null;
	private double[] probs;

	public MyPOS(String user) {
		init(user);
	}

	//init from online
	public void init(String user) {

		//instantiate POS
		POSModel model = null;
	
		// Tagging
		InputStream modelIn = null;
		try {
			modelIn = new FileInputStream(
					"lib/en-pos-maxent.bin");
			model = new POSModel(modelIn); 
		} catch (IOException e) {
			// Model loading failed, handle the error
			e.printStackTrace();
		} finally {
			if (modelIn != null) {
				try {
					modelIn.close();
				} catch (IOException e) {
				}
			}
		}
		
		POSTaggerME tagger = new POSTaggerME(model);
		

		String delimsPuctuation = "[|,.?/;:{}\\-\\!\\)\\(]";
		String[] split = user.split(" ");
		sent = split;
		
//		sent = new String[] { "Most", "large", "cities", "in",
//				"the", "US", "had", "morning", "and", "afternoon",
//				"newspapers", "." };
		tags = tagger.tag(split);
		probs = tagger.probs();
		topSequences = tagger.topKSequences(split);
		
	}//init
	
	
	public static String translateSimpleTag(String incomingTag){
		//result String
		String result="";
		//ensure upperCase
		incomingTag=incomingTag.toUpperCase();
		
		//switch
		switch(incomingTag){
		case "JJR":
		case "JJS":
		case "JJ":
			result="Adjective";
			break;
		case "NNPS":
		case "NNS":
		case "NNP":
		case "NN":
			result="Noun";
			break;
		case "PRP$":
		case "PRP":
			result="Pronoun";
		case "RBS":
		case "RB":
		case "RBR":
			result="Adverb";
			break;
		case "VBG":
		case "VB":
		case "VBD":
		case "VBN":
		case "VBZ":
		case "VBP":
			result="Verb";
			break;
		}//switch
	
		return result;
	}//translateTag
	
	//Switch Statement Made By Barrett Sharpe
	public static String translateTag(String incomingTag){
		//result String
		String result=null;
		//ensure upperCase
		incomingTag=incomingTag.toUpperCase();
		
		//switch
		switch(incomingTag){
		case "CC":
			result="Coordinating Conjununction";
			break;
		case "CD":
			result="Cardinal Number";
			break;
		case "DT":
			result="Determiner";
			break;
		case "EX":
			result="Existemtial there";
			break;
		case "FW":
			result="Foreign Word";
			break;
		case "IN":
			result="Preposition or Subordinating Conjection";
			break;
		case "JJ":
			result="Adjective";
			break;
		case "JJR":
			result="Adjective, Comparitive";
			break;
		case "JJS":
			result="Adjective, Superlative";
			break;
		case "LS":
			result="List Item Marker";
			break;
		case "MD":
			result="Modal";
			break;
		case "NN":
			result="Noun, Singular or Mass";
			break;
		case "NNS":
			result="Noun, Plural";
			break;
		case "NNP":
			result="Noun Proper, Singular";
			break;
		case "NNPS":
			result="Noun Propor, Plural";
			break;
		case "PDT":
			result="Predeterminer";
			break;
		case "POS":
			result="Posessive Ending";
			break;
		case "PRP":
			result="Pronoun, Personal";
			break;
		case "PRP$":
			result="Pronoun, Posessive";
			break;
		case "RB":
			result="Adverb";
			break;
		case "RBR":
			result="Adverb, Comparative";
			break;
		case "RBS":
			result="Adverb, Superlative";
			break;
		case "RP":
			result="Particle";
			break;
		case "SYM":
			result="Symbol";
			break;
		case "TO":
			result="'To'";
			break;
		case "UH":
			result="Interjection";
			break;
		case "VB":
			result="Verb, Base Form";
			break;
		case "VBD":
			result="Verb, Past Tense";
			break;
		case "VBG":
			result="Verb, Present Participal";
			break;
		case "VBN":
			result="Verb: Past Participle";
			break;
		case "VBP":
			result="Verb: Non 3rd-Person Singular Present";
			break;
		case "VBZ":
			result="Verb: 3rd-Person Singular Present";
			break;
		case "WDT":
			result="Whdeterminer";
			break;
		case "WP":
			result="Whpronoun";
			break;
		case "WP$":
			result="Possessive Whpronoun";
			break;
		case "WRB":
			result="Whadverb";
			break;	
		}//switch
	
		return result;
	}//translateTag
	
//Get/Sets
	public Sequence[] getTopSequences() {
		return topSequences;
	}

	public String[] getTags() {
		return tags;
	}

	public String[] getSent() {
		return sent;
	}
	
	public double[] getProbability() {
		return probs;
	}


	
	

}// class



//public enum Word_Type {Verb, Adjective}; //this is just so I remember enum format




//try {
//	// \esc\
//  dataIn = new FileInputStream("src/test2api/en-pos-maxent.bin");
//  ObjectStream<String> lineStream = new PlainTextByLineStream(dataIn, "UTF-8");
//  ObjectStream<POSSample> sampleStream = new WordTagSampleStream(lineStream);
//
//  model = POSTaggerME.train("en", sampleStream, TrainingParameters.defaultParams(), null, null);
//}
//catch (IOException e) {
//  // Failed to read or parse training data, training failed
//	System.out.println("this is an error");
//  e.printStackTrace();
//}
//finally {
//  if (dataIn != null) {
//    try {
//      dataIn.close();
//    }
//    catch (IOException e) {
//      // Not an issue, training already finished.
//      // The exception should be logged and investigated
//      // if part of a production system.
//      e.printStackTrace();
//    }
//  }
//}
//
////Output Training
//OutputStream modelOut = null;
//try {
//  modelOut = new BufferedOutputStream(new FileOutputStream("lib/output1/modelFile1.train"));
//  model.serialize(modelOut);
//}
//catch (IOException e) {
//  // Failed to save model
//  e.printStackTrace();
//}
//finally {
//  if (modelOut != null) {
//  try {
//     modelOut.close();
//  }
//  catch (IOException e) {
//    // Failed to correctly save model.
//    // Written model might be invalid.
//    e.printStackTrace();
//  }
//}
//}
