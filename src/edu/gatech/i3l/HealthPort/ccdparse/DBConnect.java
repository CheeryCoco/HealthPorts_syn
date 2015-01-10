package edu.gatech.i3l.HealthPort.ccdparse;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class DBConnect {
	public static Connection connect() throws Exception {
	    String driverName = "org.gjt.mm.mysql.Driver";
	    Class.forName(driverName);

	    String serverName = "localhost";
	    String mydatabase = "omop";
	    String url = "jdbc:mysql://" + serverName + "/" + mydatabase; 

	    String username = "root";
	    String password = "apoo123";
	    Connection con = DriverManager.getConnection(url, username, password);
	    //insertIntoTable(conn);
		return con;
	  }
	
	public static void viewTable(Connection con) throws SQLException {
	    Statement stmt = null;
	    String query = "select * from USER";
	    stmt = con.createStatement();
	    ResultSet rs = stmt.executeQuery(query);

	    while (rs.next()) {
	    	String Name = rs.getString("time_of_birth");
	        int orgID = rs.getInt("person_id");        
	        System.out.println(Name + ", " + orgID);
      }
	}
	
	public static void insertIntoTable(Connection con) throws SQLException {
	    Statement stmt = null;
	    stmt = con.createStatement();
	    int loc = 1;
	    
	    String query = "insert into location " +
	    		"values (2000, 'GT', 'Tech', null, 'GA', '30363', 'Fulton', 'Atlanta')";
	    stmt.executeUpdate(query);
	    
	    
	    String query2 = "insert into care_site " +
	    		"values (11, 'GT', null, 1000, 'Tech', 'Atlanta')";
	    stmt.executeUpdate(query2);

	}
}
