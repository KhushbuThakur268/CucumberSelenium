package com.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

public class ReadDataExcel
{
	
	public static Object[][] readDataExcel(String sheetname) 		//For making util add praram and remove @test		
	{
		File file = new File("C:\\Users\\Java Project\\SeleniumWebDriverProject\\src\\test\\resources\\Book.xlsx");
		Object[][] obj = null;
		
		try {
			InputStream is = new FileInputStream(file);
			XSSFWorkbook workbook = new XSSFWorkbook(is);
			XSSFSheet sheet1 = workbook.getSheet(sheetname);
	//		XSSFSheet sheet1 = workbook.getSheet("Sheet1");						////For making util remove
			obj = new Object[sheet1.getLastRowNum()][];							//For making util add
			
			for(int i =1; i<=sheet1.getLastRowNum(); i++) 
			{
				obj[i-1] = new Object[sheet1.getRow(i).getPhysicalNumberOfCells()];			////For making util add
				for(int j =0; j<sheet1.getRow(i).getPhysicalNumberOfCells(); j++) 
				{
					obj[i-1][j] = sheet1.getRow(i).getCell(j).getStringCellValue();				////For making util add
					//System.out.print(sheet1.getRow(i).getCell(j).getStringCellValue()); 		//For making util remove
				}
			//	sheet1.getRow(i).createCell(2).setCellValue("Valid User");                no need    //For write operation		//For making util remove
			//	System.out.println(" ");										        //For making util remove
			}
			is.close();
	/*		For making util remove
			OutputStream os = new FileOutputStream(file); 			//For write operation
			workbook.write(os);										//For write operation
			os.close();												//For write operation
		*/	  
			workbook.close();
		} 
		catch (FileNotFoundException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return obj;
	}
}
