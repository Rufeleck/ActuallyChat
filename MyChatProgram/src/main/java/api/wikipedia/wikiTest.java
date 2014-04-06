package main.java.api.wikipedia;

import java.io.IOException;

public class wikiTest {

	public static void main(String args[]) {
		try {
			System.out.println(Wikipedia.searchWikiDis("George Washington"));
			System.out.println(Wikipedia.selectDisambiguation("0", Wikipedia.searchWikiDis("George Washington")));
			System.out.println(Wikipedia.getIntro(Wikipedia.selectDisambiguation("0", Wikipedia.searchWikiDis("George Washington"))));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
