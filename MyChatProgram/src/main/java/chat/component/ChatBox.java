package main.java.chat.component;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;


/**
*@author Samuel Miller
*
*/

public class ChatBox extends JPanel  implements ActionListener, ChatGUI {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField userInputField;
    private JTextArea display;
    private JScrollPane scrolling;
    private JButton enterButton;
    
	public ChatBox() {
		super(new GridBagLayout());
		
		userInputField = new JTextField(50);
		userInputField.addActionListener(this);
		
		display = new JTextArea("",30,50);
		display.setEditable(false);
		display.setLineWrap(true);
		scrolling = new JScrollPane(display);
		scrolling.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		GridBagConstraints constraint = new GridBagConstraints();
		constraint.gridwidth = GridBagConstraints.REMAINDER;
		constraint.fill = GridBagConstraints.BOTH;
		GridBagConstraints constraint2 = new GridBagConstraints();
		constraint.fill = GridBagConstraints.BOTH;
		
		enterButton = new JButton();
		enterButton.addActionListener(this);
		enterButton.setText("Enter");
		enterButton.setToolTipText("Click this to send message, or press enter");
		
		add(scrolling, constraint);
		add(userInputField, constraint2);
		add(enterButton,constraint);
		

		
	}
	
	public ChatBox(ActionListener l){
		super(new GridBagLayout());
		
		userInputField = new JTextField(100);
		userInputField.addActionListener(l);
		
		enterButton = new JButton();
		enterButton.addActionListener(l);
		
		display = new JTextArea("",30,100);
		display.setEditable(false);
		display.setLineWrap(true);
		scrolling.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		GridBagConstraints constraint = new GridBagConstraints();
		constraint.gridwidth = GridBagConstraints.REMAINDER;
		constraint.fill = GridBagConstraints.BOTH;
		GridBagConstraints constraint2 = new GridBagConstraints();
		constraint.fill = GridBagConstraints.BOTH;
		
		
		add(scrolling, constraint);
		add(userInputField, constraint2);
		add(enterButton,constraint);
		
	}
	
	@Override
	public void writeToDisplay(String str){
        display.append(str + "\n");
        display.selectAll();
	}
	
	@Override
	public String writeUserInputToDisplay(){
		String str = userInputField.getText();
        display.append(userInputField.getText() + "\n");
        return str;
		
	}
	
	@Override
	public void clearDisplay(){
		display.setText("");
	}
	
	@Override
	public String getUserInput(){
		return userInputField.getText();
	}
	
	@Override
	public void clearUserInput(){
        userInputField.setText("");
	}
	
	@Override
	public void replaceActionListener(ActionListener l){
		for(ActionListener al : userInputField.getActionListeners())
			userInputField.removeActionListener(al);
		for(ActionListener al : enterButton.getActionListeners())
			enterButton.removeActionListener(al);

		enterButton.addActionListener(l);
		userInputField.addActionListener(l);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String text = userInputField.getText();
        display.append(text + "\n");
        display.selectAll();
        userInputField.setText("");
//        display.append("USER: " + userInputField.getText() + "\n");
//		display.append("CHATTER: " + respond.respond(userInputField.getText()) + "\n");
//		this.clearUserInput();
//        display.setCaretPosition(display.getDocument().getLength());
	}

}
