package com.example.naved;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class Report {
	public static void main(String[] args) {
		try {
			
			String filePath="C:\\Users\\ssyed\\eclipse-workspace\\Report_Card\\src\\main\\resources\\design.jrxml";
			
			Student student1=new Student("21BCT0366","SYED NAVED",95,"S");
			Marks student2=new Student("21BCI0247","VAKA RAMA KRISHNA",94,"S");
			Marks student3=new Student("21BDS0248","G V LIKITH",80,"A");
			List<Student> list=new ArrayList<Student>();
			list.add(student1);
			list.add(student2);
			list.add(student3);
			
			
			JRBeanCollectionDataSource dataSource=new JRBeanCollectionDataSource(list);
			
			Map<String,Object> parameters=new HashMap<String,Object>();
			parameters.put("Faculty_Name","NAGARAJA G");
			parameters.put("TableData",dataSource);
			
			JasperReport report=JasperCompileManager.compileReport(filePath);
			
			//JRBaseTextField textField = (JRBaseTextField)report.getTitle().getElementByKey("f_name");
			
			//textField.setForecolor(Color.RED);
			
			//JasperPrint print=JasperFillManager.fillReport(report,parameters,dataSource);
			JasperPrint print=JasperFillManager.fillReport(report,parameters,new JREmptyDataSource());
			
			JasperExportManager.exportReportToPdfFile(print,"C:\\Users\\ssyed\\OneDrive\\Desktop\\SCOREME\\Report.pdf");
			
			System.out.println("Report Created");
			
		}catch(Exception e) {
		System.out.println("Exception while creating report");
	}
	}
}

