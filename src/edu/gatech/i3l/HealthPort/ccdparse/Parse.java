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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Parse {


  	public static void main(String[] args) throws Exception {
  		//DBOps.connect("True");
		File path = new File("src/resources"); //path to your folder. eg. C:\\P4logs
		for(File f: path.listFiles()) { // this loops through all the files + directories
		    if(f.isFile() && !f.isHidden()) { // checks if it is a file, not a directory  
		    	try {
		            JSONObject xmlJSONObj = XML.toJSONObject(new String(Files.readAllBytes(Paths.get("src/resources/"+f.getName()))));
		            
		            DBOps.populateDatabase("OMOP", "person", xmlJSONObj);
		            DBOps.populateDatabase("OMOP", "condition_occurrence", xmlJSONObj);
		            DBOps.populateDatabase("OMOP", "drug_exposure", xmlJSONObj);
		            DBOps.populateDatabase("OMOP", "observation", xmlJSONObj);
		            
		            //System.out.println("Name: " + SyntheticEHR.getPatientName(xmlJSONObj).getClass());
		            //System.out.println("Date: " + SyntheticEHR.getObsDate(xmlJSONObj)); 
		            
		            /*String [] cond = SyntheticEHR.getObs(xmlJSONObj);
		            for (int i = 0 ; i< cond.length; i++){
		            	if (cond[i] != null){
		            		System.out.println("Cond: " + cond[i]);
		            		//String[] data = cond[i].split("\\|");
		            		//System.out.println(data[1]);
		            	}	
		            }*/
		            /*
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
		            
		            //String input = "some input string";
		            int hashCode = Math.abs(f.getName().hashCode());
		            System.out.println("input primary key = " + hashCode);
		            System.out.println("\n");
		            
		            
		            try {
		            	Connection connection = DBConnect.connect();
		        		//DBConnect.insertIntoTable(con);
		            	String tableName = "person";
		            	populateTable(connection, tableName);
		        		/*
		            	String sql;
		                sql = "DROP TABLE location";
		                try (Statement s = connection.createStatement()) {
		                    s.executeUpdate(sql);
		                } catch (Exception e) {
		                    // assume table did not previously exist
		                }
		    			//Statement stmt = null;
		    		    //stmt = conn.createStatement();
		    		    //int loc = 1;
		    	        sql = "CREATE TABLE location (location_id int(11) primary key generated always as identity, address_1 varchar(50), address_2  varchar(50), city varchar(50), state varchar(2), zip varchar(9))";
		    	        try (Statement s = connection.createStatement()) {
		    	            s.executeUpdate(sql);
		    	        }
		    		    sql = "insert into location " +
		    		    		"values (1, 'GT', 'Tech', null, 'GA', '30363', 'Fulton', 'Atlanta')";
		    		    try (Statement s = connection.createStatement()) {
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
		    }
		}
		    	
	}
	
	public static void populateTable(Connection connection, String tableName){
		if (tableName.equals("person")){//USER
			Statement statement = null;
			ResultSet rs = null;
			String sql = "insert into location " +
		    		"values (1, 'GT', 'Tech', null, 'GA', '30363', 'Fulton', 'Atlanta')";
			try {
				statement = connection.createStatement();
				statement.executeUpdate("INSERT INTO trn_employee (EMP_NAME, EMP_SALARY) VALUES('Manish',19000)");

				rs = statement.getGeneratedKeys();

				if (rs.next()) {
					System.out.println("Auto Generated Primary Key " + rs.getInt(1)); 
				}
			} catch (SQLException e) {
				System.out.println("SQLException Occured..");
			}
		}
		else if (tableName.equals("condition_occurrence")){//CONDITION
			
		}
		else if (tableName.equals("drug_exposure")){//MEDICATION
			
		}
		else if (tableName.equals("observation")){//OBSERVATION
			
		}
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

