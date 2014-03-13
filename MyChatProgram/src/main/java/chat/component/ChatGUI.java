package main.java.chat.component;

import java.awt.event.ActionListener;

public interface ChatGUI {

	public void writeToDisplay(String str);
	public String writeUserInputToDisplay();
	public void clearDisplay();
	public String getUserInput();
	public void clearUserInput();
	public void replaceActionListener(ActionListener l);
	
}
