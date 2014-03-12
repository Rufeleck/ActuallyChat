package main.java.chat;


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

        chat.chat();
    }
}
