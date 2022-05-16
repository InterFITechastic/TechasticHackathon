package com.hackathon.techastic.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
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
	
	public static UserDetails getUserDetail(Row row) {
		UserDetails user = new UserDetails();
		
		Iterator<Cell> cellIterator = row.cellIterator(); 
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
		return user;
	}
	
	public static void searchGoogle(UserDetails user) throws UnsupportedEncodingException {
		String searchURL = GOOGLE_SEARCH_URL + "?q="+URLEncoder.encode(user.getDunsName()+" Linkedin" , "utf-8")  ;
		
		Document htmlDocument = null;
		try {
			htmlDocument = getHtmlDocument(searchURL);
			String linkedInCompanyLink = readLinkedInProfileOfCompany(htmlDocument);
			
			Document linkedInHtmlDocument = getHtmlDocument(linkedInCompanyLink);
			Elements linkedinElements = linkedInHtmlDocument.getAllElements();
			
			for(Element element : linkedinElements) {
				if(element.text().contains("Website")) {
					
					/**
					 * find the company's official website and navigate to all the href links that can be feched from the htmlDocument of the company website
					 * and can search for executive names 1 and 2, once the hyperlink is idetified, same one can be used to fetch
					 * other leaders and owners of the company and then can make user of Gender and Nationality APIs.
					 */
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	private static String readLinkedInProfileOfCompany(Document htmlDocument) throws UnsupportedEncodingException{
		Elements results = htmlDocument.select("a[href]");
		String companyName = "";
		for(Element result: results) {
			if(result.absUrl("href").startsWith("https://www.linkedin.com/company/")) {
				companyName = result.absUrl("href");
			}
			break;
		}
		return companyName;
	}
	
	
	private static List<String> readHrefLinksFromSite(Document htmlDocument) throws UnsupportedEncodingException{
		List<String> linksList = new ArrayList();
		Elements results = htmlDocument.select("a[href]");

		for (Element link : results) {
			String url = link.absUrl("href").toLowerCase();
			if(url.indexOf('=') != -1 && url.indexOf('&') != -1)
				url = URLDecoder.decode(url.substring(url.indexOf('=') + 1, url.indexOf('&')), "UTF-8");
		    
		    if (!url.startsWith("http")) {
		        continue;
		    }
		   
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
					//keyWord is found
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
