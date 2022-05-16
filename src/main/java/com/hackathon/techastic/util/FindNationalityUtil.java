package com.hackathon.techastic.util;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Set;

public class FindNationalityUtil {

    public static void main(String[] args) {
        String gender = null;
        try {

            //String myKey = "insert your server key here";
            URL url = new URL("https://ono.4b.rs/v1/nat?key=0ab808d14ef8450da38af5f7f32c59e0&fn="
                    + URLEncoder.encode("Zuly", StandardCharsets.UTF_8)
                    + "&sn=" + URLEncoder.encode("Sanguino", StandardCharsets.UTF_8));
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Error: " + conn.getResponseCode());
            }

            InputStreamReader input = new InputStreamReader(conn.getInputStream());
            BufferedReader reader = new BufferedReader(input);

            Gson gson = new Gson();
            JsonObject json = gson.fromJson(reader, JsonObject.class);
            /*gender = json.get("countries").getAsString();
            System.out.println("Gender: " + gender); // Gender: male*/
            JsonElement element = JsonParser.parseString(String.valueOf(json));
            JsonObject obj = element.getAsJsonObject(); //since you know it's a JsonObject
            Set<Map.Entry<String, JsonElement>> entries = obj.entrySet();//will return members of your object
            for (Map.Entry<String, JsonElement> entry: entries) {
                System.out.println(entry.getValue());
            }
            conn.disconnect();

        } catch (IOException  e) {
            e.printStackTrace();
        }
    }
}
