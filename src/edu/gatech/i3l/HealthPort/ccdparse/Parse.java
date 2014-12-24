package edu.gatech.i3l.HealthPort.ccdparse;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Parse {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		File path = new File("src/files"); //path to your folder. eg. C:\\P4logs
		for(File f: path.listFiles()) { // this loops through all the files + directories
		    if(f.isFile() && !f.isHidden()) { // checks if it is a file, not a directory           
		    	//System.out.println(f);
		    	//System.out.println("Hello Eclipse!");    
		    	readFile(f);
		    }
		}
		//String filename= "src/files/input.XML";
		//readFile(filename);
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
		System.out.println(list);
		//FileInputStream inputStream = new FileInputStream(file);
		//List<String> lines = IOUtils.readLines(inputStream);
		//return lines;
	}
}

//Implicit initialization
//CDAUtil.loadPackages();