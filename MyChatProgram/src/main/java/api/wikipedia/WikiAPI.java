//
//package main.java.api.wikipedia;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.net.URL;
//
//import org.json.JSONException;
//import org.json.JSONObject;
//
//public class WikiAPI {
//
//	public WikiAPI(){
//	}
//		
//	@SuppressWarnings("static-access")
//	public static void main(String args[]) throws IOException{
//			String baseUrl = "http://en.wikipedia.org/w/api.php?action=opensearch&search=";
//			String userTimeline = "heavyweight";
//			
//			String fullUrl = baseUrl + userTimeline;
//			URL myUrl = new URL(fullUrl);
//			InputStream is = myUrl.openStream();
//			
//			//making array of similar searches in wikipedia, not nessery to use to get points.
//			BufferedReader br = new BufferedReader(new InputStreamReader(is));
//			
//			String line = "";
//			//using for extra search characters, this case it's going to be "heavyweight" with MMA contained.
//			String search = "MMA";
//			line = br.readLine();
//			System.out.println(line);
//			String[] result;
//			String stop = "\"";
//			result = line.split(stop);
//			for(int i = 0; i < result.length; i++){
//				result[i] = result[i].replaceAll(" ", "+");
//				System.out.println(result[i]);
//				if(result[i].contains(search)){
//					break;
//				}
//			}
//			
//			//important part: getting definitions and information here.
//			String usable = result[3]; //using "Cotton+candy", can be changed to that.
//			String URLSearch = "https://en.wikipedia.org/w/api.php?action=query&prop=extracts&format=json&exlimit=10&exintro=&explaintext=&titles=";
//			String totalURL = URLSearch + usable;
//			URL myUrl2 = new URL(totalURL);
//			
//			InputStream is2 = myUrl2.openStream();
//			BufferedReader streamReader = new BufferedReader(new InputStreamReader(is2, "UTF-8"));
//			StringBuilder responseStrBuilder = new StringBuilder();
//			
//			String inputStr;
//			String str = "";
//			while((inputStr = streamReader.readLine()) != null){
//				responseStrBuilder.append(inputStr);
//			}
//			
//			JSONObject json = new JSONObject(str);//responseStrBuilder.toString()
//			JSONObject query = json.getJSONObject("query");
//			JSONObject pages = query.getJSONObject("pages");
//			JSONObject nestedObject = null;
//			
//		    String[] keys = pages.getNames(pages);
//
//		    // Find the intro text for the page
//		    for (int i = 0; i < keys.length; i++){
//		    	try{
//		    		nestedObject = pages.getJSONObject(keys[i]);
//		    		if (nestedObject.has("pageid"))
//		    			break;
//		    	} catch (JSONException e) {}
//		    }
//			
////			URL myUrl2 = new URL(totalURL);
////			InputStream is2 = myUrl2.openStream();
//
////			JSONTokener tok2 = new JSONTokener(is2);
////			JSONObject result2 = new JSONObject(tok2);
////			is2.close();
//			
//			String s = nestedObject.getString("extract");
//		    
//			if(s != ""){
//				 System.out.println(s);
//			}
//			else{
//				System.out.println("not found");
//			}
//			
//			
//	}
//
//	}
