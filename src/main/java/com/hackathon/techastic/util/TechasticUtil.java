package com.hackathon.techastic.util;

import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import com.hackathon.techastic.to.UserDetails;

public class TechasticUtil {

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
}
