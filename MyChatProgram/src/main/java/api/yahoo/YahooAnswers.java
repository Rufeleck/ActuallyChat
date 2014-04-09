//package main.java.api.yahoo;
//
////Import Note: My JSON-java folder is located in scr\main\JSON\
//import java.io.IOException;
//import java.io.InputStream;
//import java.net.URL;
//import java.net.URLEncoder;
//
//import org.json.JSONArray;
//import org.json.JSONObject;
//import org.json.JSONTokener;
//
//public class YahooAnswers {
//
//	public static void main(String[] args) throws IOException {
//		String baseUrl = "https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20answers.search%20where%20query%3D%22";
//		String baseUrl2 = "%22%20and%20type%3D%22resolved%22&format=json&diagnostics=true&callback=";
//		String userTimeline = "how to bake a cake"; // this works: how to bake a
//													// cake
//													// //how%20to%20bake%20a%20cake
//
//		// doesn't work: userTimeline = userTimeline.replaceAll(" ", "%20");
//
//		userTimeline = URLEncoder.encode(userTimeline, "UTF-8");
//		String fullUrl = baseUrl + userTimeline + baseUrl2;
//		URL myUrl = new URL(fullUrl);
//
//		// Test
//		System.out
//				.println("!@#Prep For Do/While try-catch InputStream connection/tokenizer");
//
//		Exception e = null;
//		JSONObject result = new JSONObject();
//		// Repeatedly(do-while) establish the InputStream(inside the
//		// try-catch-finally) until an exception is not thrown
//		do {
//			e = null;
//			InputStream is = null;
//			try {
//				is = myUrl.openStream();
//				JSONTokener tok = new JSONTokener(is);
//				result = new JSONObject(tok);
//				is.close();
//			} catch (Exception x) {
//				System.out.println("x Exception");
//				// x.printStackTrace();
//				e = x;
//			} finally {
//				System.out
//						.println("!@#Exception e is null?: "
//								+ (e == null)
//								+ "\n"
//								+ (e == null ? "Therefore, proceding with JSON Parsing"
//										: "Therefor, failed to procede to JSON parsing. Possible SSLHE error."));
//			}// try/catch/finally
//		} while (!(e == null));// do/while
//
//		JSONObject query = result.getJSONObject("query");
//		// Test
//		// System.out.println("\n!@#Query\n"+query.toString());
//		JSONObject results = query.getJSONObject("results");
//		// Test
//		// System.out.println("\n!@#Results\n"+results.toString());
//		JSONArray question = results.getJSONArray("Question");
//		// Test
//		// System.out.println("\n!@#QuestionArray\n"+question.toString(2));
//		JSONObject element = question.optJSONObject(0);
//		// Test
//		// System.out.println("\n!@#Element\n"+results.toString());
//		String finalString = element.getString("ChosenAnswer");
//		// TEST
//		System.out.print("\n!@#FinalStringElement\n" + finalString);
//
//	}
//
//}// class
//
//// Error might occur but repeat run will resolve it
//// javax.net.ssl.SSLHandshakeException:
//// sun.security.validator.ValidatorException: PKIX path building failed:
//// sun.security.provider.certpath.SunCertPathBuilderException: unable to
//// find valid certification path to requested target
//// (YahooAnswerAPI.java:24)
//// Caused by: sun.security.validator.ValidatorException: PKIX path
//// building failed:
//// sun.security.provider.certpath.SunCertPathBuilderException: unable to
//// find valid certification path to requested target