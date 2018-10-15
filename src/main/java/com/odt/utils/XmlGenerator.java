package com.odt.utils;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlInclude;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import com.odt.wrapper.ApplicationWarppers;


public class XmlGenerator extends ApplicationWarppers{

	private static XmlSuite suite;

	private static XmlTest test;

	//	private static ArrayList<String> classesList = new ArrayList<>();
	private static Map<String,Map<String,String>> testNgData = new HashMap<String,Map<String,String>>();
	private static XmlClass xmlClaz;
	private static List<XmlClass> xmlClassesList = new ArrayList<XmlClass>();

	private static String basepkg = "com.odt.testcases";

//	public static void main(String[] args) {
//		
//		System.out.println("**************In XMLgenClass Main***************");
//		xmlInit();
//		new XmlGenerator().scanExcel();
//		suiteGen();
//		String xml = getXML();
//		writeToXML(xml);
//
//	}
	
	
	
	public void XmlGeneratorclass()
	{
		System.out.println("**************In XMLgenClass***************");
		xmlInit();
		new XmlGenerator().scanExcel();
		suiteGen();
		String xml = getXML();
		
		System.out.println("Output of getXML : "+xml);
		writeToXML(xml);
	}

	private static void xmlInit() {
		
		
		suite = new XmlSuite();
		suite.setName("API Suite");
		test = new XmlTest(suite);
		test.setName("API Test");
		xmlClassesList = new ArrayList<XmlClass>();
		System.out.println("XMLInit Method executed");
	}

	/*private static void suiteGen() {
		List<XmlInclude> methodIncludes = new ArrayList<XmlInclude>();
		for(Entry<String, Entry<String,String>> claz : testNgData.entrySet()){
			String clazName = claz.getKey();
			ArrayList<String> methodList = claz.getValue();			
			xmlClaz = new XmlClass(basepkg + "." + clazName);
			for(String method : methodList){
//				System.out.println(method);
				methodIncludes = xmlClaz.getIncludedMethods();
				methodIncludes.add(new XmlInclude(method));
			}
//			List<XmlInclude> includedMethods = methodList.stream().map(XmlInclude::new).collect(Collectors.toList());
//			xmlClaz.setIncludedMethods(includedMethods);
			xmlClassesList.add(xmlClaz);
		}
		test.setXmlClasses(xmlClassesList);
	}*/

	private static void suiteGen() {
		List<XmlInclude> methodIncludes = new ArrayList<XmlInclude>();
		for(Entry<String, Map<String,String>> claz : testNgData.entrySet()){
			String clazName = claz.getKey();
			Map<String,String> methodList = claz.getValue();
			System.out.println(clazName);
			xmlClaz = new XmlClass(basepkg + "." + clazName);
			for(Entry<String,String>method : methodList.entrySet()){
				System.out.println("The method name is "+method);
				String methodName =method.getKey();
				String parameter = method.getValue();
				methodIncludes = xmlClaz.getIncludedMethods();
				XmlInclude methodInc = new XmlInclude(methodName);
				methodInc.addParameter("Env", parameter);
				methodIncludes.add(methodInc);				
			}
			//			List<XmlInclude> includedMethods = methodList.stream().map(XmlInclude::new).collect(Collectors.toList());
			//			xmlClaz.setIncludedMethods(includedMethods);
			xmlClassesList.add(xmlClaz);
		}
		test.setXmlClasses(xmlClassesList);
		//		System.out.println(test);
		
		
		System.out.println("suiteGen Method executed");
	}



	private static String getXML() {
		return suite.toXml();
	}


	private static void writeToXML(String xml) {
		// System.out.println(xml);
		String path = System.getProperty("user.dir");
		
		System.out.println("Path from writeToXML method : "+path);
		try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path +"\\.xml"), "utf-8"))) 
		{
			writer.write(xml);
			System.out.println("Write to XML method completed");
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	private  void scanExcel(){
		System.out.println("Scan Excel Method");
		
		String filename = getDataFromPropertiesFile("config.properties", "TestDataExcelName");
		System.out.println("The file name is " +filename);
		Workbook wb;		
		try {
			System.out.println("In TRY");
			wb = WorkbookFactory.create(new FileInputStream("./TestData/"+filename+".xlsx"));
			Sheet sheet = wb.getSheetAt(0);
			
			rowloop: for (Row row : sheet) 
			{

				System.out.println("Inside for Loop");
				
				Cell cell2 = row.getCell(2);
				System.out.println("Cell2 before IF-CONDITION : "+cell2);
				if (cell2 == null) 
				{
					break rowloop;
				}

				String strToRun = cell2.getStringCellValue();
				
				System.out.println("Cell2 value : "+strToRun);
				if("Run".equalsIgnoreCase(strToRun) || "No".equalsIgnoreCase(strToRun)){
					continue;
				}				
				Cell cell0 = row.getCell(0);
				if (cell0 == null) {
					break rowloop;
				}
				Cell cell1 = row.getCell(1);
				if (cell1 == null) {
					break rowloop;
				}
				Cell cell3 = row.getCell(3);
				if (cell3 == null) {
					break rowloop;
				}
				
				Cell cell4 = row.getCell(4);
				if (cell4 == null) {
					break rowloop;
				}
				
				Cell cell5 = row.getCell(5);
				if (cell5 == null) {
					break rowloop;
				}
			
				String strClassName = cell0.getStringCellValue();
				String strMethodName = cell1.getStringCellValue();
				String strEnvironmentName = cell3.getStringCellValue();
				
				testCaseName = cell1.getStringCellValue();
				testDescription = cell4.getStringCellValue();
				testCaseGroup = cell5.getStringCellValue();
				
				System.out.println("TestcaseName : "+testCaseName+ " "+" TestDesc : "+testDescription+" "+" TestGroup : "+testCaseGroup);
				System.out.println("ClassName : "+strClassName+ " "+" MethodName : "+strMethodName+" "+" EnvName : "+strEnvironmentName);
				

				//				ArrayList<String> methodCol = testNgData.computeIfAbsent(strClassName, clsNm -> new ArrayList<String>());
				Map<String,String> methodcol = testNgData.computeIfAbsent(strClassName, clsNm -> new HashMap<String,String>());
				methodcol.put(strMethodName, strEnvironmentName);
				//				methodCol.add(strMethodName);
			}
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
}
