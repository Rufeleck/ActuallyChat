package main.java.api.wikipedia;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

import org.json.JSONObject;

public class Wikipedia {

	private Wikipedia(){
	}
		
	//returns the disambiguation list
	public static ArrayList<String> searchWikiDis(String searchElement) throws IOException{
			String baseUrl = "http://en.wikipedia.org/w/api.php?action=opensearch&search=";
			searchElement = URLEncoder.encode(searchElement,"UTF-8");
			String fullUrl = baseUrl + searchElement;
			URL myUrl = new URL(fullUrl);
			InputStream is = myUrl.openStream();
			
			//making array of similar searches in wikipedia, not nessery to use to get points.
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			
			String line = "";
			line = br.readLine();
			String[] result;
			String stop = "\"";
			result = line.split(stop);
			ArrayList<String> fixed = new ArrayList<String>();
			ArrayList<String> removal = new ArrayList<String>();
			removal.add("[");
			removal.add(",[");
			removal.add(",");
			removal.add("]]");
			for(int i = 0; i < result.length; i++){
				fixed.add(result[i]);
			}
			fixed.removeAll(removal);
			fixed.remove(0);
			return fixed;
	}
	
	//returns the element in the disambiguation array that is specified by the user
	public static String selectDisambiguation(String userInput, ArrayList<String> dis){
		int loc = 0;
		try{
			loc = Integer.parseInt(userInput);
		}
		catch(Exception e){
			for(int i = 0; i< dis.size(); i++){
				if(userInput.toLowerCase().contains(dis.get(i).toLowerCase()))
					loc = i;
			}
				
		}
			
		if (loc < 0 || loc >= dis.size())
			return "These are not the droids you are looking for";
					
		return dis.get(loc);
		
	}

	
	//get the intro for the specified page
	public static String getIntro(String page) throws IOException{
		
		String s = null;
			
					
			//important part: getting definitions and information here.
			String URLSearch = "https://en.wikipedia.org/w/api.php?action=query&prop=extracts&format=json&exlimit=10&exintro=&explaintext=&titles=";
			String totalURL = URLSearch + URLEncoder.encode(page, "UTF-8");
			URL myUrl2 = new URL(totalURL);
			
			InputStream is2 = myUrl2.openStream();
			BufferedReader streamReader = new BufferedReader(new InputStreamReader(is2, "UTF-8"));
			//StringBuilder responseStrBuilder = new StringBuilder();
			
			String inputStr;
			String input = "";
			while((inputStr = streamReader.readLine()) != null){
				input += inputStr;
			}
			JSONObject json = new JSONObject(input);
			JSONObject query = json.getJSONObject("query");
			JSONObject pages = query.getJSONObject("pages");
		    String[] keys = JSONObject.getNames(pages);
			JSONObject nestedObject = pages.getJSONObject(keys[0]);
//		    // Find the intro text for the page
//		    for (int i = 0; i < keys.length; i++){
//		    	try{
//		    		nestedObject = pages.getJSONObject(keys[i]);
//		    		if (nestedObject.has("pageid"))
//		    			break;
//		    	} catch (JSONException e) {}
//		    }
			try{
			s = nestedObject.getString("extract");
			}
			catch(Exception e){
				return "Nothing here but us chickens.";
			}
			
			return s;
	}
}