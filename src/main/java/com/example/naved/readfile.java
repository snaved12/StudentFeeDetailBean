package com.example.naved;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
public class readfile {

	public static void main(String[] args) throws FileNotFoundException, IOException, ParseException {
		try {
		//creating a json parser object
		JSONParser jsonparse=new JSONParser();
		//parsing the content of the json file
		JSONObject jsonobject=(JSONObject) jsonparse.parse(new FileReader("json\\student_fee.json"));
		JSONArray jsonArray=(JSONArray) jsonparse.parse(new FileReader("json\\stufee.json"));
		//reading the data from the json file
		/*String date=(String) jsonobject.get("Date");
		String name=(String) jsonobject.get("StudentName");
		String descc=(String) jsonobject.get("Description");
		Long amt=(Long) jsonobject.get("Amount");
		String category=(String) jsonobject.get("Category");
		Long bal=(Long) jsonobject.get("Balance");*/
		
		List<Student> list = new ArrayList<>();

	    for (Object obj : jsonArray) {
	        JSONObject jsonObject = (JSONObject) obj;

	        String date = (String) jsonObject.get("Date");
	        String name = (String) jsonObject.get("StudentName");
	        String descc = (String) jsonObject.get("Description");
	        Long amt = (Long) jsonObject.get("Amount");
	        String category = (String) jsonObject.get("Category");
	        Long bal = (Long) jsonObject.get("Balance");

	        Student student = new Student(date, name, descc, amt, category, bal);
	        list.add(student);
	    }
	    
		/*System.out.println(date);
		System.out.println(name);
		System.out.println(desc);
		System.out.println(amt);
		System.out.println(category);
		System.out.println(bal);*/
		
		/*Student student1=new Student(date,name,desc,amt,category,bal);
		
		List<Student> list=new ArrayList<Student>();
		list.add(student1);*/
		
		/*
		for (Student student : list) {
            System.out.println("Date: " + student.getDate());
            System.out.println("Name: " + student.getName());
            System.out.println("Description: " + student.getDesc());
            System.out.println("Amount: " + student.getAmt());
            System.out.println("Category: " + student.getCategory());
            System.out.println("Balance: " + student.getBal());
            System.out.println();
        }
		*/
	    
	    Class.forName("com.mysql.cj.jdbc.Driver");
	    Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/newdb","root","Abd@69877");
	    
	    
	   // PreparedStatement ps=con.prepareStatement("insert into fee_report values('04-10-23','nn','ECE',1000,'Done',0)");
	    for (Object obj : jsonArray) {
	        JSONObject jsonObject = (JSONObject) obj;

	        String date = (String) jsonObject.get("Date");
	        String name = (String) jsonObject.get("StudentName");
	        String descc = (String) jsonObject.get("Description");
	        Long amt = (Long) jsonObject.get("Amount");
	        String category = (String) jsonObject.get("Category");
	        Long bal = (Long) jsonObject.get("Balance");

	        PreparedStatement ps=con.prepareStatement("insert into fee_report values('"+date+"','"+name+"','"+descc+"','"+amt+"','"+category+"','"+bal+"')");
	        int i=ps.executeUpdate();
		    if(i>0) {
		    	System.out.println("Success");
		    }
		    else {
		    	System.out.println("Fail");
		    }
	    }
	   /* int i=ps.executeUpdate();
	    if(i>0) {
	    	System.out.println("Success");
	    }
	    else {
	    	System.out.println("Fail");
	    }*/
	    
	    
	    
		String filePath="C:\\Users\\ssyed\\eclipse-workspace\\jsonojasper\\src\\main\\resources\\exc.jrxml";
		
		JRBeanCollectionDataSource dataSource=new JRBeanCollectionDataSource(list);
		
		Map<String,Object> parameters=new HashMap<String,Object>();
		parameters.put("orgname","SCOREME");
		parameters.put("TableData",dataSource);
		
		JasperReport report=JasperCompileManager.compileReport(filePath);
		
		//JRBaseTextField textField = (JRBaseTextField)report.getTitle().getElementByKey("f_name");
		
		//textField.setForecolor(Color.RED);
		
		//JasperPrint print=JasperFillManager.fillReport(report,parameters,dataSource);
		JasperPrint print=JasperFillManager.fillReport(report,parameters,new JREmptyDataSource());
		
		JasperExportManager.exportReportToPdfFile(print,"C:\\Users\\ssyed\\OneDrive\\Desktop\\SCOREME\\JJJ.pdf");
		
		JRXlsxExporter exporter=new JRXlsxExporter();
		exporter.setExporterInput(new SimpleExporterInput(print));
		exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(new FileOutputStream(new File("C:\\Users\\ssyed\\OneDrive\\Desktop\\SCOREME\\StudentFeeDetails.xlsx"))));
		
		exporter.exportReport();
		System.out.println("Report Created");
		
	}catch(Exception e) {
			System.out.println("Exception while creating report");
		}
		
	}

}
