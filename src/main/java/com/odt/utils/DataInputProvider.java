package com.odt.utils;

import java.io.File;
import java.io.FileInputStream;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.odt.wrapper.GenericWrappers;



public class DataInputProvider extends GenericWrappers {

	public static String[][] getSheet(String excelFileName,String excelSheetName) {

		String[][] data = null;

		System.out.println("In DataProvider Class");
		System.out.println("Excel : "+excelFileName+ " "+" sheet : "+excelSheetName);
		try {
			FileInputStream fis = new FileInputStream(new File("./TestData/" + excelFileName + ".xlsx"));
			System.out.println("*******************************************");
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			XSSFSheet sheet = workbook.getSheet(excelSheetName);
			
			System.out.println("Sheet Name : "+sheet.getSheetName());

			// get the number of rows
			int rowCount = sheet.getLastRowNum();
			
			System.out.println("Row Count : "+rowCount);

			// get the number of columns
			int columnCount = sheet.getRow(0).getLastCellNum();
			data = new String[rowCount][columnCount];

			// loop through the rows
			for (int i = 1; i < rowCount + 1; i++) {
				try {
					XSSFRow row = sheet.getRow(i);
					for (int j = 0; j < columnCount; j++) { // loop through the
															// columns
						try {
							String cellValue = "";
							try {
								cellValue = row.getCell(j).getStringCellValue();
								
								System.out.println("Cell Value : "+cellValue);
							} catch (NullPointerException e) {

							}

							data[i - 1][j] = cellValue; // add to the data array
						} catch (Exception e) {
							e.printStackTrace();
						}
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			fis.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("Data from DataProvider Class : "+data);
		return data;

	}

}
