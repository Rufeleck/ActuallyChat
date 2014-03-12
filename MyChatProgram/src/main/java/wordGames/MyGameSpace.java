package main.java.wordGames;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import main.java.chat.component.ChatBox;
import main.java.wordGames.synonym.Synonym;

public class MyGameSpace implements GameSpace, ActionListener {
	
	ChatBox ctBx;

	public MyGameSpace() {
	}

	@Override
	public void run() {
		JFrame frame = new JFrame("ChatBox");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ctBx = new ChatBox();
        ctBx.replaceActionListener(this);
        frame.add(ctBx);
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);
        ctBx.writeToDisplay("What game Would you like to play?"
        		+ "\n0: quit"
    			+ "\n1: Synonym");

	}


	@Override
	public void runGame(Game game) {
		game.run();
		closeGames(game);

	}

	@Override
	public void closeGames(Game game) {
		game.closeGame();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
        String str = ctBx.getUserInput().toLowerCase();
        ctBx.clearUserInput();
        if (str.equals("1")) 
        	runGame(new Synonym(ctBx));
        else if (str.equals("0"))
        	System.exit(0);
	}
}
