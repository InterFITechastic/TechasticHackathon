package com.hackathon.techastic.services;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import com.hackathon.techastic.to.UserDetails;
import com.hackathon.techastic.util.TechasticUtil;

@Service
public class ExcelService {
	

	public List<UserDetails> getData() throws IOException{
		List<UserDetails> details = new ArrayList();
		InputStream in = new ClassPathResource("Data.xlsx").getInputStream();
		try{
			XSSFWorkbook wb = new XSSFWorkbook(in); 
			XSSFSheet sheet = wb.getSheetAt(0); 
			Iterator<Row> itr = sheet.iterator(); 
			itr.next();
			while (itr.hasNext()) {
				details.add(TechasticUtil.getUserDetail(itr.next()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			in.close();
		}
		return details;
	}


	public void saveDiversityData(List<UserDetails> details) throws IOException {

		InputStream in = new ClassPathResource("Data.xlsx").getInputStream();
		try{
			XSSFWorkbook wb = new XSSFWorkbook(in); 
			XSSFSheet sheet = wb.getSheetAt(0); 
			
			
			/**
			 *get UserDetails object one by one using inde and compare the dunsName with the one in
			 *excel and create cells for K, L columns and set the values
			 */
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			in.close();
		}
	
	}
}
