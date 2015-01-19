package edu.gatech.i3l.HealthPort.ccdparse;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.json.JSONObject;

public class DBOps {
	static final String serverName = "localhost";
	static final String url = "jdbc:mysql://" + serverName;
	static final String username = "root";
	static final String password = "a";
    
	public static void connect(String refresh){
		if (refresh.equals("True")){
			dropDatabase("OMOP");
		    createDatabase("OMOP");
		}
		
	  }
	
	public static void createDatabase(String dbName){
		String driverName = "org.gjt.mm.mysql.Driver";			    
	    Connection conn = null;
	    Statement stmt = null;
	    try {
			Class.forName(driverName);
			conn = DriverManager.getConnection(url, username, password);
			stmt = conn.createStatement();
		    String sql = "CREATE DATABASE " + dbName;
		    stmt.executeUpdate(sql);
		} catch (SQLException se) {
			// TODO Auto-generated catch block
			se.printStackTrace();
		}catch(Exception e){
		      //Handle errors for Class.forName
		      e.printStackTrace();
	   }finally{
	      //finally block used to close resources
	      try{
	         if(stmt!=null)
	            conn.close();
	      }catch(SQLException se){
	      }// do nothing
	      try{
	         if(conn!=null)
	            conn.close();
	      }catch(SQLException se){
	         se.printStackTrace();
	      }//end finally try
	   }//end try
	}
	
	public static void dropDatabase(String dbName){
		String driverName = "org.gjt.mm.mysql.Driver";		
	    //String mydatabase = "omop";	    
	    Connection conn = null;
	    Statement stmt = null;
	    try {
			Class.forName(driverName);
			conn = DriverManager.getConnection(url, username, password);
			stmt = conn.createStatement();
		    String sql = "DROP DATABASE " + dbName;
		    stmt.executeUpdate(sql);
		} catch (SQLException se) {
			// TODO Auto-generated catch block
			se.printStackTrace();
		}catch(Exception e){
		      //Handle errors for Class.forName
		      e.printStackTrace();
	   }finally{
	      //finally block used to close resources
	      try{
	         if(stmt!=null)
	            conn.close();
	      }catch(SQLException se){
	      }// do nothing
	      try{
	         if(conn!=null)
	            conn.close();
	      }catch(SQLException se){
	         se.printStackTrace();
	      }//end finally try
	   }//end try
	}

	public static void populateDatabase(String dbName, String tableName, JSONObject xmlJSONObj){
		String driverName = "org.gjt.mm.mysql.Driver";			    
	    Connection conn = null;
	    Statement stmt = null;
	    try {
			Class.forName(driverName);
			String URL = url + "/" + dbName;
			conn = DriverManager.getConnection(URL, username, password);
			stmt = conn.createStatement();
			
		    if (tableName.equals("person")){//USER
		    	String name = "'" + SyntheticEHR.getPatientName(xmlJSONObj) + "'";
		    	//String race = "'" + SyntheticEHR.getPatientRace(xmlJSONObj) + "'";
				String sql = "INSERT INTO person " +
			    		"values (" + SyntheticEHR.getPatientID(xmlJSONObj) + ", " + name + ", " + SyntheticEHR.getPatientGenderID(xmlJSONObj) + ", " + SyntheticEHR.getBirthYear(xmlJSONObj) + ", " + SyntheticEHR.getBirthMonth(xmlJSONObj)  + ", " + SyntheticEHR.getBirthDay(xmlJSONObj)  + ", null, null)";
				stmt.executeUpdate(sql);
			}
			else if (tableName.equals("condition_occurrence")){//CONDITION
				//String sql = "INSERT INTO condition_occurrence (person_id, condition_id, condition_start_date, condition_end_date, condition_name, stop_reason)" + 
			    		//"values (" + SyntheticEHR.getPatientID(xmlJSONObj) + ", " + SyntheticEHR.getCond(xmlJSONObj) + ", " + SyntheticEHR.getCondStart(xmlJSONObj) + ", " + SyntheticEHR.getCondEnd(xmlJSONObj)  + ", " + SyntheticEHR.getCondSID(xmlJSONObj) + ", null, null, null, null, null)";//, null, null, null, null, null, null, null, null)";
				String[] cond = SyntheticEHR.getCond(xmlJSONObj);
				for (int i = 0; i < cond.length; i++){
					if (cond[i] != null){
						String[] data = cond[i].split("\\|");
						String condID = "'" + data[0] + "'";
						String condStart = "'" + data[2] + "'";
						String condName = "'" + data[1] + "'";
						String condEnd = "'N/A'";
						String sql = "INSERT INTO condition_occurrence (person_id, condition_id, condition_start_date, condition_end_date, condition_name, stop_reason)" +
								"values (" + SyntheticEHR.getPatientID(xmlJSONObj) + ", " + condID + ", " + condStart + ", " + condEnd + ", " + condName + ", null)";
						stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);	
					}
					
				}
					
			}
			else if (tableName.equals("drug_exposure")){//MEDICATION
				//String sql = "INSERT INTO drug_exposure (person_id, drug_concept_id, drug_exposure_start_date, drug_exposure_end_date, drug_type_concept_id, drug_dose_quantity, drug_source_value) " + 
			    		//"values (" + SyntheticEHR.getPatientID(xmlJSONObj) + ", " + SyntheticEHR.getDrugID(xmlJSONObj) + ", " + SyntheticEHR.getDrugStart(xmlJSONObj) + ", " + SyntheticEHR.getDrugEnd(xmlJSONObj)  + ", null, " + SyntheticEHR.getDrugDose(xmlJSONObj) + ", " + SyntheticEHR.getDrugName(xmlJSONObj) + ")";//, null, null, null, null, null, null, null, null)";
				//stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
				String[] med = SyntheticEHR.getMeds(xmlJSONObj);
				for (int i = 0; i < med.length; i++){
					if (med[i] != null){
						String[] data = med[i].split("\\|");
						String drugID = "'" + data[0] + "'";
						String drugName = "'" + data[1] + "'";
						String drugStatus = "'" + data[2] + "'";
						String drugStart = "'" + data[3] + "'";
						String drugDosage = "'" + data[4] + " " + data[5] + "'";
						String sql = "INSERT INTO drug_exposure (person_id, drug_id, drug_name, drug_dosage, last_filled_date, status)" +
								"values (" + SyntheticEHR.getPatientID(xmlJSONObj) + ", " + drugID + ", " + drugName + ", " + drugDosage + ", " + drugStart + "," +  drugStatus + ")";
						stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);	
					}
					
				}
			}
			else if (tableName.equals("observation")){//OBSERVATION
				String[] obs = SyntheticEHR.getObs(xmlJSONObj);
				for (int i = 0; i < obs.length; i++){
					if (obs[i] != null){
						String[] data = obs[i].split("\\|");
						if (data.length > 1){
							String obsDate = "'" + String.valueOf(data[0]).substring(0, 10) + "'";
							String obsTime = "'" + String.valueOf(data[0]).substring(11,19) + "'";
							String height = "'" + data[1] + "'";
							String weight = "'" + data[2] + "'";
							String respRate = "'" + data[3] + "'";
							String Pulse = "'" + data[4] + "'";
							String SysBP = "'" + data[5] + "'";
							String DiaBP = "'" + data[6] + "'";
							String Temp = "'" + data[7] + "'";
							String heightID = "'8302-2'";
							String weightID = "'3141-9'";
							String respRateID = "'9279-1'";
							String pulseID = "'8867-4'";
							String sysBPID = "'8480-6'";
							String diaBPID = "'8462-4'";
							String tempID = "'8310-5'";
							String sql = "INSERT INTO observation (person_id, observation_concept_id, observation_date, observation_time, observation_value)" +
									"values (" + SyntheticEHR.getPatientID(xmlJSONObj) + ", " + heightID + ", " + obsDate + ", " + obsTime + "," + height + ")";
							stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);	
							sql = "INSERT INTO observation (person_id, observation_concept_id, observation_date, observation_time, observation_value)" +
									"values (" + SyntheticEHR.getPatientID(xmlJSONObj) + ", " + weightID + ", " + obsDate + ", " + obsTime + "," + weight + ")";
							stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
							sql = "INSERT INTO observation (person_id, observation_concept_id, observation_date, observation_time, observation_value)" +
									"values (" + SyntheticEHR.getPatientID(xmlJSONObj) + ", " + respRateID + ", " + obsDate + ", " + obsTime + "," + respRate + ")";
							stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
							sql = "INSERT INTO observation (person_id, observation_concept_id, observation_date, observation_time, observation_value)" +
									"values (" + SyntheticEHR.getPatientID(xmlJSONObj) + ", " + pulseID + ", " + obsDate + ", " + obsTime + "," + Pulse + ")";
							stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
							sql = "INSERT INTO observation (person_id, observation_concept_id, observation_date, observation_time, observation_value)" +
									"values (" + SyntheticEHR.getPatientID(xmlJSONObj) + ", " + sysBPID + ", " + obsDate + ", " + obsTime + "," + SysBP + ")";
							stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
							sql = "INSERT INTO observation (person_id, observation_concept_id, observation_date, observation_time, observation_value)" +
									"values (" + SyntheticEHR.getPatientID(xmlJSONObj) + ", " + diaBPID + ", " + obsDate + ", " + obsTime + "," + DiaBP + ")";
							stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
							sql = "INSERT INTO observation (person_id, observation_concept_id, observation_date, observation_time, observation_value)" +
									"values (" + SyntheticEHR.getPatientID(xmlJSONObj) + ", " + tempID + ", " + obsDate + ", " + obsTime + "," + Temp + ")";
							stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
						}
					}
					
				}
				//String height = "'" + SyntheticEHR.getHeight(xmlJSONObj)  + "'";
				//String heightID = "'8302-2'"; 
				//String ObsDate = "'" + SyntheticEHR.getObsDate(xmlJSONObj) + "'";
				//String sql = "INSERT INTO observation (person_id, observation_concept_id, observation_date, observation_time, value_as_string) " +
			    		//"values (" + SyntheticEHR.getPatientID(xmlJSONObj) + ", " + heightID + ", " + ObsDate + ", null, " + height  + ")";//, null, null, null, null, null, null, null, null)";
				//stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
				
			}
		    
		} catch (SQLException se) {
			// TODO Auto-generated catch block
			se.printStackTrace();
		}catch(Exception e){
		      //Handle errors for Class.forName
		      e.printStackTrace();
	   }finally{
	      //finally block used to close resources
	      try{
	         if(stmt!=null)
	            conn.close();
	      }catch(SQLException se){
	      }// do nothing
	      try{
	         if(conn!=null)
	            conn.close();
	      }catch(SQLException se){
	         se.printStackTrace();
	      }//end finally try
	   }//end try
		
	}

}
