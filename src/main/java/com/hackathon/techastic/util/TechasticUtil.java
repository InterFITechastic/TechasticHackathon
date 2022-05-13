package com.hackathon.techastic.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.hackathon.techastic.to.UserDetails;

public class TechasticUtil {
	
	public static final String GOOGLE_SEARCH_URL = "https://www.google.com/search";
	public static final String USER_AGENT =
            "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.1 (KHTML, like Gecko) Chrome/101.0.4951.54 Safari/535.1";
	public static List<String> diversityKeys = Arrays.asList(new String[] {"Black", "Africal American", "Hispanic","Latino",
			"Asian","Pacific","Indian","LGBTQIA+","Veteran"});

	public static UserDetails getUserDetail(Row row) {
		UserDetails user = new UserDetails();
		
		Iterator<Cell> cellIterator = row.cellIterator(); 
		//while (cellIterator.hasNext()) {
				user.setDunsNum(cellIterator.next().getNumericCellValue());
				user.setDunsName(cellIterator.next().getStringCellValue());
				user.setCounty(cellIterator.next().getStringCellValue());
				user.setStreetAddress(cellIterator.next().getStringCellValue());
				user.setCity(cellIterator.next().getStringCellValue());
				user.setState(cellIterator.next().getStringCellValue());
				user.setZip(cellIterator.next().getNumericCellValue());
				user.setPhone(cellIterator.next().getNumericCellValue());
				user.setExecutiveContact1(cellIterator.next().getStringCellValue());
				user.setExecutiveContact2(cellIterator.next().getStringCellValue());
		//}
		System.out.println("User: "+user);
		return user;
	}
	
	public static List<String> searchGoogle(UserDetails user) throws UnsupportedEncodingException {
		String searchURL = GOOGLE_SEARCH_URL + "?q="+URLEncoder.encode(user.getDunsName(), "utf-8")  ;
		//without proper User-Agent, we will get 403 error
		Document htmlDocument = null;
		try {
			htmlDocument = getHtmlDocument(searchURL);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return readHrefLinksFromSite(htmlDocument);
	}
	
	
	private static List<String> readHrefLinksFromSite(Document htmlDocument) throws UnsupportedEncodingException{
		List<String> linksList = new ArrayList();
		Elements results = htmlDocument.select("a[href]");

		for (Element link : results) {
			String url = link.absUrl("href").toLowerCase(); // Google returns URLs in format "http://www.google.com/url?q=<url>&sa=U&ei=<someKey>".
			if(url.indexOf('=') != -1 && url.indexOf('&') != -1)
				url = URLDecoder.decode(url.substring(url.indexOf('=') + 1, url.indexOf('&')), "UTF-8");
		    
		    if (!url.startsWith("http")) {
		        continue; // Ads/news/etc.
		    }
		    
		    /*
		     * url.contains("facebook") || url.contains("twitter") || url.contains(user.getDunsName()) || url.contains(user.getStreetAddress()) ||
		    		title.contains(user.getDunsName()) || title.contains(user.getStreetAddress())
		     */
		    if(!url.contains("google")){
		    	String title = link.text();
			    System.out.println("Title: " +title );
			    System.out.println("URL: " + url);
			    
		    	linksList.add(url);
		    }
		}
		return linksList;
	}
	
	
	public static boolean searchSiteForKeyWords(String url, List<String> keyWords) throws Exception {
		Document htmlDocument = getHtmlDocument(url);
		for(String keyWord : keyWords) {
			return htmlDocument.text().toLowerCase().contains(keyWord);
		}
		return false;
	}
	
	public static String searchSiteForKeyWord(String url, String keyWord)throws Exception {
		Document htmlDocument = getHtmlDocument(url);
		List<String> linksList = readHrefLinksFromSite(htmlDocument);
		for (String subLink : linksList) {
			if (subLink.toLowerCase().contains("lead") || subLink.toLowerCase().contains("about")
					|| subLink.toLowerCase().contains("news") || subLink.toLowerCase().contains("contact")) {

				System.out.println("***subLink*****" + subLink);
				htmlDocument = getHtmlDocument(subLink);
				if (htmlDocument.text().toLowerCase().contains(keyWord)) {
					System.out.println("============Diversified=====================" + htmlDocument.text());
				}
			}
		}
		return htmlDocument.text().toLowerCase().contains(keyWord) ? htmlDocument.text() : null;
	}
	
	private static Document getHtmlDocument(String url) throws IOException {
		Connection connection = Jsoup.connect(url).userAgent(USER_AGENT);
		
		Document htmlDocument = connection.get();
		if(connection.response().statusCode() != 200 || !connection.response().contentType().contains("text/html")) {
			throw new IOException();
		}
		return htmlDocument;
	}
}
