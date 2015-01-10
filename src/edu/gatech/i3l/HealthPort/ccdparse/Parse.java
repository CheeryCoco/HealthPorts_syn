package edu.gatech.i3l.HealthPort.ccdparse;

//import java.io.BufferedReader;
//import java.io.ByteArrayInputStream;
import java.io.File;
//import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
//import java.io.InputStream;
//import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
//import java.sql.*;
/*
import org.openhealthtools.mdht.uml.cda.CDAPackage;
import org.openhealthtools.mdht.uml.cda.ClinicalDocument;
import org.openhealthtools.mdht.uml.cda.ccd.AlertsSection;
import org.openhealthtools.mdht.uml.cda.ccd.CCDPackage;
import org.openhealthtools.mdht.uml.cda.ccd.ContinuityOfCareDocument;
import org.openhealthtools.mdht.uml.cda.ccd.impl.ContinuityOfCareDocumentImpl;
import org.openhealthtools.mdht.uml.cda.consol.ConsolPackage;
import org.openhealthtools.mdht.uml.cda.util.CDAUtil;
*/
import java.nio.file.Paths;
import java.nio.file.Files;

import org.json.JSONObject;
import org.json.JSONException;
import org.json.XML;

import java.sql.Connection;
import java.sql.Statement;

public class Parse {


  	public static void main(String[] args) throws Exception {
 
		// TODO Auto-generated method stub
  		///try {
			///DBConnect.connect();
		///} catch (Exception e) {
			///e.printStackTrace();
		///}
  		
		File path = new File("src/resources"); //path to your folder. eg. C:\\P4logs
		for(File f: path.listFiles()) { // this loops through all the files + directories
		    if(f.isFile() && !f.isHidden()) { // checks if it is a file, not a directory  
		    	//System.out.println("src/resources/"+f.getName());
		    	
		    	//JSONObject xmlJSONObj = null;
				//try {
					//xmlJSONObj = XML.toJSONObject(new String(Files.readAllBytes(Paths.get("src/resources/"+f.getName()))));
				//} catch (JSONException | IOException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
				//}
                //String jsonPrettyPrintString = null;
				//try {
					//jsonPrettyPrintString = xmlJSONObj.toString(4);
				//} catch (JSONException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
				//}
                //System.out.println(jsonPrettyPrintString);
                
		    	
		    	try {
		            JSONObject xmlJSONObj = XML.toJSONObject(new String(Files.readAllBytes(Paths.get("src/resources/"+f.getName()))));
		            
		            //System.out.println(xmlJSONObj.getJSONObject("ClinicalDocument").getJSONObject("recordTarget").getJSONObject("patientRole").getJSONObject("patient").getJSONObject("name"));
		            //SyntheticEHR.getResults(xmlJSONObj);
		            
		            //System.out.println("Patient file: " + f.getName());
		            //System.out.println("Time of Birth: " + SyntheticEHR.getPatientBirthTime(xmlJSONObj));
		            //System.out.println("Gender: " + SyntheticEHR.getPatientGender(xmlJSONObj));
		            System.out.println("Weight: " + SyntheticEHR.getWeight(xmlJSONObj));
		            System.out.println("Height: " + SyntheticEHR.getHeight(xmlJSONObj));
		            System.out.println("Systolic BP: " + SyntheticEHR.getSysBP(xmlJSONObj));
		            System.out.println("Diastolic BP: " + SyntheticEHR.getDiaBP(xmlJSONObj));
		            System.out.println("Pulse: " + SyntheticEHR.getPulse(xmlJSONObj));
		            System.out.println("Respiration Rate: " + SyntheticEHR.getRespRate(xmlJSONObj));
		            System.out.println("Problems: " + SyntheticEHR.getProblems(xmlJSONObj)); 
		            //System.out.println("Testing: " + SyntheticEHR.getPatientAddr(xmlJSONObj));
		            Object temp = SyntheticEHR.getResults(xmlJSONObj);
		            if (temp != null){
		            	System.out.println(temp);
		            } 
		            System.out.println("\n");		            
		            /*
		            try {
		            	Connection conn = DBConnect.connect();
		        		//DBConnect.insertIntoTable(con);
		        		String sql;
		                sql = "DROP TABLE location";
		                try (Statement s = conn.createStatement()) {
		                    s.executeUpdate(sql);
		                } catch (Exception e) {
		                    // assume table did not previously exist
		                }
		    			//Statement stmt = null;
		    		    //stmt = conn.createStatement();
		    		    //int loc = 1;
		    	        sql = "CREATE TABLE location (location_id int(11) primary key generated always as identity, address_1 varchar(50), address_2  varchar(50), city varchar(50), state varchar(2), zip varchar(9))";
		    	        try (Statement s = conn.createStatement()) {
		    	            s.executeUpdate(sql);
		    	        }
		    		    sql = "insert into location " +
		    		    		"values (1, 'GT', 'Tech', null, 'GA', '30363', 'Fulton', 'Atlanta')";
		    		    try (Statement s = conn.createStatement()) {
		    	            s.executeUpdate(sql);
		    	        }
		    		    //stmt.executeUpdate(query);

		    		} catch (Exception e) {
		    			e.printStackTrace();
		    		}*/
		            //SyntheticEHR.getWeight(xmlJSONObj);
		            //System.out.println(SyntheticEHR.getCCDObject(xmlJSONObj, "ClinicalDocument"));
		            //SyntheticEHR.getCCDObject(xmlJSONObj);
		        } catch (JSONException | IOException je) {
		            System.out.println(je.toString());
		        }
		    	
		    	
		    	//*
		    	//System.out.println(f);
		    	//String ccd = null;
				//try {
					//ccd = new Scanner(f).useDelimiter("\\Z").next();
				//} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
				//}
		    	//System.out.println(ccd);
		    	//ConsolPackage.eINSTANCE.eClass();
				//ContinuityOfCareDocument ccdDocument = null;
				//try {
					//InputStream is = new ByteArrayInputStream(ccd.getBytes());
					//ccdDocument = (ContinuityOfCareDocument) CDAUtil.load(is);
					//System.out.println(ccdDocument);
					//} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
					//} catch (Exception e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
					//}
		    	//System.out.println("Hello Eclipse!");    
		    	//readFile(f);
                //*
		    	//try {
					//ClinicalDocument ccdDoc = CDAUtil.load(new FileInputStream(f));
					//System.out.println(ccdDoc.getSections());
				//} catch (Exception e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
				//}
		    
		    	//CDAUtil.loadPackages();
		    	//ConsolPackage.eINSTANCE.eClass();
		    	//try {
		    		//String ccd = (String) new FileInputStream("src/resources/000008119_5.XML");
		    		//InputStream is = new ByteArrayInputStream(ccd.getBytes());
		    		//ContinuityOfCareDocument ccdDoc = (ContinuityOfCareDocument)  CDAUtil.load(new FileInputStream("src/resources/000008119_5.XML"));
		    		//System.out.println(ccdDoc);
					//if (ccdDoc instanceof ContinuityOfCareDocument) {
						//	System.out.println((ContinuityOfCareDocument)ccdDoc);
					//}
					//else{
						//System.out.println(ccdDoc);
					//}
					//AlertsSection alertsSection = ccdDoc.getAlertsSection();
				//} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
				//} catch (Exception e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
				//}
		    	 
		    	
		    }
		}
		//String filename= "src/files/input.XML";
		//readFile(filename);
		
  		/*
  		try {
			DBConnect.connect();
		} catch (Exception e) {
			e.printStackTrace();
		}
		*/
	}
	
	
	public static void readFile(File file){
		//File file = new File (filename);
		List<String> list = new ArrayList<>();
		Scanner scanner = null;
		try {
		    scanner = new Scanner(file);
		    while (scanner.hasNextLine()) {
		        list.add(scanner.nextLine());
		    }
		} catch (FileNotFoundException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		} finally {
		    if (scanner!=null)
		        scanner.close();
		}
		System.out.println(file);
		System.out.println(list);
		//FileInputStream inputStream = new FileInputStream(file);
		//List<String> lines = IOUtils.readLines(inputStream);
		//return lines;
	}
}

//Implicit initialization
//CDAUtil.loadPackages();