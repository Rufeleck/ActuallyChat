package main.java.chat;

import static main.java.chat.util.Util.*;

import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import main.java.chat.component.ChatBox;

import javax.swing.JFrame;

import main.java.chat.component.ChatBox;

/**
 * 
 * @author alex
 * Modified By Samuel Miller
 */
public final class MyChat implements Chat
{
    boolean chat;
    Responder responder;
    Scanner scan;

    public MyChat()
    {
        chat = true;
        scan = new Scanner( System.in );
    }

    @Override
    public void initialize( Responder responderIn )
    {
    	Object[] options = {"Male",
                "Female"};
        	int n = JOptionPane.showOptionDialog(null,
        			"What gender should the person you're talking to be?",
        			"StartUp", JOptionPane.YES_NO_OPTION,
        			JOptionPane.QUESTION_MESSAGE, null, options, null);
        	//System.out.print(n);
        responder = responderIn;
        if (n == 0)
        	responder.readConfigFile( "..\\..\\..\\resources\\maleConfig.chat" );
        else
        	responder.readConfigFile( "..\\..\\..\\resources\\femaleConfig.chat" );
        	
    }

    @Override
    public String getSentence()
    {
        return scan.nextLine();
    }

    @Override
    public void chat()
    {

		JFrame frame = new JFrame("ChatBox");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ChatBox ctBx = new ChatBox();
        ResponderAction act = new ResponderAction(responder, ctBx);
        ctBx.replaceActionListener(act);
        frame.add(ctBx);
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);
        ctBx.writeToDisplay("Welcome to chat");
//
//        while( chat )
//        {
//            String sentence = getSentence();
//
//            responder.respond( sentence );
//        }
    }
}
