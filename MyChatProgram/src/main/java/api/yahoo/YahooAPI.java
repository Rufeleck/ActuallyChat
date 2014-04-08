package main.java.api.yahoo;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLEncoder;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

public class YahooAPI {
	private YahooAPI(){
	}
	//give it a question as a string returns an answer from yahoo answers as a string
	public static String answerMeThis(String qball) throws IOException{
		String baseUrl = "https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20answers.search%20where%20query%3D%22";
		String baseUrl2 = "%22%20and%20type%3D%22resolved%22&format=json&diagnostics=true&callback=";
		qball = URLEncoder.encode(qball, "UTF-8");
		String fullUrl = baseUrl + qball + baseUrl2;
		URL myUrl = new URL(fullUrl);

		Exception e = null;
		JSONObject result = new JSONObject();
		// Repeatedly(do-while) establish the InputStream(inside the
		// try-catch-finally) until an exception is not thrown
		do {
			e = null;
			InputStream is = null;
			try {
				is = myUrl.openStream();
				JSONTokener tok = new JSONTokener(is);
				result = new JSONObject(tok);
				is.close();
			} catch (Exception x) {
				System.out.println("x Exception");
				e = x;
			} 
		} while (!(e == null));// do/while

		JSONObject query = result.getJSONObject("query");
		JSONObject results = query.getJSONObject("results");
		JSONArray question = results.getJSONArray("Question");
		JSONObject element = question.optJSONObject(0);
		String yourPrayersHaveBeenAnswered = element.getString("ChosenAnswer");
		
		return yourPrayersHaveBeenAnswered;
	}
	
}
