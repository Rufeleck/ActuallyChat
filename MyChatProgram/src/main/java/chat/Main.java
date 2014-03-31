package main.java.chat;

import main.java.wordGames.synonym.Synonym;


/**
 * 
 * @author alex
 *Modified By Samuel Miller
 */
public class Main
{
    public static void main( String[] args )
    {
        Chat chat = new MyChat();
        chat.initialize( new MyResponder() );
        Synonym.initialize();
        chat.chat();
    }
}
