package main.java.chat;

import static main.java.chat.util.Util.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import main.java.api.yahoo.YahooAPI;
import main.java.chat.component.Keyword;
import main.java.chat.component.Response;
import main.java.chat.util.ConfigReader;

/**
 * 
 * @author alex
 *Modified By Samuel Miller
 */
public final class MyResponder implements Responder
{
    private static List<Keyword> keywords;
    private int offTopic;
    private final int GameInit = 3;

    public MyResponder()
    {
    	offTopic = 0;
    }

    @Override
    public void readConfigFile( String relativePath )
    {
    	keywords = new ArrayList<Keyword>();
    	ConfigReader.readConfig( keywords, relativePath );
    }

    @Override
    public String respond( final String inputSentence )
    {
    	String[] split = inputSentence.toLowerCase().split( "\\s+" );

        search: for( Keyword keyword : keywords )
        {
        	/*
        	 * If phrase, do one thing, else, it's a word match so do something else. 
        	 */
        	if( keyword.getType().equals( Keyword.KeywordType.PHRASE ) )
        	{
        		for( String k : keyword.getKeywords() )
    			{
        			if( ( keyword.getSentenceMatch().equals( Keyword.MatchType.EXACT ) && inputSentence.equals( k ) )
        				|| ( keyword.getSentenceMatch().equals( Keyword.MatchType.STARTS_WITH ) && inputSentence.startsWith( k ) )
        				|| ( keyword.getSentenceMatch().equals( Keyword.MatchType.ENDS_WITH ) && inputSentence.endsWith( k ) )
        				|| ( keyword.getSentenceMatch().equals(Keyword.MatchType.CONTAINS ) && inputSentence.contains( k ) ) )
        			{
    					return pickResponse( inputSentence, keyword.getResponses() );
        			}
        		}
        		
        	}
        	// Word
        	else
        	{
        		for( int i = 0; i < split.length; i++ )
        		{
        			String word = split[ i ].trim();
        			if( i > 0 && keyword.getSentenceMatch().equals( Keyword.MatchType.STARTS_WITH )
        					|| split.length > 1 && keyword.getSentenceMatch().equals( Keyword.MatchType.EXACT ) )
        			{
        				continue search;
        			}
        			
        			boolean cont = false;
        			
        			if (keyword.getSentenceMatch().equals( Keyword.MatchType.ENDS_WITH )){
        				cont = true;
        				
	        			for( String key : keyword.getKeywords() )
	        			{
	        				if(key.contains(split[split.length - 1]))
	        					cont = false;
	        			}
        			}
        			
        			if (cont == true)
        				continue search;
        			
        			for( String k : keyword.getKeywords() )
        			{
        				if( keyword.getWordMatch().equals( Keyword.MatchType.EXACT ) && word.equals( k )
        						|| keyword.getWordMatch().equals( Keyword.MatchType.STARTS_WITH ) && word.startsWith( k ) 
        						|| keyword.getWordMatch().equals( Keyword.MatchType.ENDS_WITH ) && word.endsWith( k )
        						|| keyword.getWordMatch().equals( Keyword.MatchType.CONTAINS ) && word.contains( k ) )
        				{
        					return pickResponse(inputSentence, keyword.getResponses() );
        				}
        			}
        		}
        	}
        }
        
        return	pickGenericResponse(inputSentence);
    	
    }
    
    private boolean isQuestion(String inputSentence){
    	return inputSentence.contains( "?" ) || startsWith( inputSentence, "do", "how", "is", "were", "can", "when", "who", "what", "where", "why" );
    }
    
    private String pickResponse( final String inputSentence, final List<Response> responses )
    {
    	boolean question = isQuestion(inputSentence);
    	
    	search: for( Response response : responses )
    	{
    		if( response.getQuestionFlag().equals( Response.QuestionFlag.QUESTION_ONLY ) && !question
    				|| response.getQuestionFlag().equals( Response.QuestionFlag.STATEMENT_ONLY) && question )
    		{
    			continue search;
    		}
    		
    		for( String keyword : response.getKeywords() )
    		{
    			if( inputSentence.contains( keyword ) || keyword.equals( "" ) )
    			{		
    				offTopic = 0;
    				return randomFromArray( response.getResponses() ) ;
    			}
    		}
    	}
   		return pickGenericResponse(inputSentence);
    }
    
    private String pickGenericResponse(String input)
    {
    	offTopic++;
    	if(isQuestion(input)){
    		try {
				return YahooAPI.answerMeThis(input);
			} catch (IOException e) {
				e.printStackTrace();
				return "ERROR 404: ANSWER NOT FOUND";
			}
    	}
    	if(offTopic < GameInit)
	    	return randomFromArray(
	    			"whatever",
	    			"hmm... not sure",
	    			"I don't really know about that.",
	    			"Can we talk about something else?",
	    			"A team of highly trained monkeys has been dispatched to your location."
	    		) ;
    	offTopic = 0;
    	return "game";
    }
}
