package main.java.chat.util;

public class PROTEST {

	public static void main(String[] args) {
		MyPOS pos = new MyPOS("the dog runs");
		for(String s :pos.getTags())
			System.out.println(s);
		String[] english = pos.getTags();
		for(int i = 0; i < english.length; i++)
			english[i] = pos.translateTag(english[i]);
		for(String s :english)
			System.out.println(s);
	}

}