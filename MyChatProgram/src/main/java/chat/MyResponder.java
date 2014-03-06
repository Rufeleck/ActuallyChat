package main.java.chat;

import static main.java.chat.util.Util.*;

import java.util.ArrayList;
import java.util.List;

import main.java.chat.component.Keyword;
import main.java.chat.component.Response;
import main.java.chat.util.ConfigReader;

/**
 * 
 * @author alex
 *
 */
public final class MyResponder implements Responder
{
    private static List<Keyword> keywords;

    public MyResponder()
    {
    }

    @Override
    public void readConfigFile( String relativePath )
    {
    	keywords = new ArrayList<Keyword>();
    	ConfigReader.readConfig( keywords, relativePath );
    }

    @Override
    public void respond( final String inputSentence )
    {
    	String[] split = inputSentence.split( "\\s+" );
    	boolean respond = false;

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
    					respond = true;
    					pickResponse( inputSentence, keyword.getResponses() );
    					break search;
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
        					|| i < split.length - 1 && keyword.getSentenceMatch().equals( Keyword.MatchType.ENDS_WITH )
        					|| split.length > 1 && keyword.getSentenceMatch().equals( Keyword.MatchType.EXACT ) )
        			{
        				continue search;
        			}
        			
        			for( String k : keyword.getKeywords() )
        			{
        				if( keyword.getWordMatch().equals( Keyword.MatchType.EXACT ) && word.equals( k )
        						|| keyword.getWordMatch().equals( Keyword.MatchType.STARTS_WITH ) && word.startsWith( k ) 
        						|| keyword.getWordMatch().equals( Keyword.MatchType.ENDS_WITH ) && word.endsWith( k )
        						|| keyword.getWordMatch().equals( Keyword.MatchType.CONTAINS ) && word.contains( k ) )
        				{
        					respond = true;
        					pickResponse(inputSentence, keyword.getResponses() );
        					break search;
        				}
        			}
        		}
        	}
        }
        
    	if( ! respond )
    	{
        	pickGenericResponse();
    	}
    }
    
    private void pickResponse( final String inputSentence, final List<Response> responses )
    {
    	boolean question = inputSentence.contains( "?" ) || startsWith( inputSentence, "do", "how", "is", "were", "can", "when", "who", "what", "where", "why" );
    	boolean respond = false;
    	
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
    				respond = true;	
    				print( randomFromArray( response.getResponses() ) );
    				break search;
    			}
    		}
    	}
    	if( ! respond )
    	{
    		pickGenericResponse();
    	}    	
    }
    
    private void pickGenericResponse()
    {
    	print( randomFromArray(
    			"whatever",
    			"hmm... not sure",
    			"I don't really know about that.",
    			"Can we talk about something else?",
    			"A team of highly trained monkeys has been dispatched to your location."
    		) );
    }
}
