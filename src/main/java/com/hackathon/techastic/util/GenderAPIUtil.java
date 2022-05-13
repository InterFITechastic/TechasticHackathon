package com.hackathon.techastic.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class GenderAPIUtil {

	public static void main(String args[]) {
		String gender = null;
		try {

			//String myKey = "insert your server key here";
			URL url = new URL("https://gender-api.com/get?key=VZSH4HP7sfqYAEep8MvAmz42NjwDzswTfACw&name=" + URLEncoder.encode("komala", "utf-8"));
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Error: " + conn.getResponseCode());
			}

			InputStreamReader input = new InputStreamReader(conn.getInputStream());
			BufferedReader reader = new BufferedReader(input);

			Gson gson = new Gson();
			JsonObject json = gson.fromJson(reader, JsonObject.class);
			gender = json.get("gender").getAsString();
			System.out.println("Gender: " + gender); // Gender: male
			conn.disconnect();
			
			
			//
			
			
			/*URL url1 = new URL("https://v2.namsor.com/NamSorAPIv2/api2/json/country/Marie%20Curie");
			HttpURLConnection conn1 = (HttpURLConnection) url.openConnection();

			HttpResponse<String> response = Unirest.get("https://v2.namsor.com/NamSorAPIv2/api2/json/country/Marie%20Curie")
					  .header("Accept", "application/json")
					  .header("X-API-KEY", "your-api-key")
					  .asString();
*/
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}