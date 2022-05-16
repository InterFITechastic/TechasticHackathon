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

	public static String getGender(String name) {
		String gender = null;
		try {
			URL url = new URL("https://gender-api.com/get?key=VZSH4HP7sfqYAEep8MvAmz42NjwDzswTfACw&name=" + URLEncoder.encode(name, "utf-8"));
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
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return gender;
	}
}