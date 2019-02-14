	package eg.edu.alexu.csd.oop.db.cs14;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import eg.edu.alexu.csd.oop.db.Database;
import eg.edu.alexu.csd.oop.db.IParser;

public class Main {
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		boolean continu = true;
		boolean continuPr = true;
    	Database database = new myDataBase();
    	IParser parser = new Parser(database);
    	Dealwithxml toGetValid = Dealwithxml.newInstance();
    	int firstInput = 0;
    	
	    System.out.println( "welcome to you in Simple DBMS" );
	    Scanner scanner = new Scanner( System.in );
	    while (continuPr) {
			continu = true;
			if (firstInput == 0) {
	    	System.out.println( "Enter your query to enter in required database : " );
	    	String query1 = scanner.nextLine();
	    	if (Check(query1) == false) {
	    		System.out.println( "you enter invalid thing" );
	    		break;
	    	}
    		parser.setInput(query1); 		
	    	firstInput++;
			} else {
		    	System.out.println( "Enter 1 to continue \nOR 0 to exit" );
		    	String out = scanner.nextLine();
		    	if (out.equals("1")) {
			    	System.out.println( "Enter your query to enter in required database : " );
			    	String query1 = scanner.nextLine();
			    	if (Check(query1) == false) {
			    		System.out.println( "you enter invalid thing" );
			    		break;
			    	}
		    		parser.setInput(query1);
		    	} else if (out.equals("0")) {
		    		break;
		    	} else {
		    		System.out.println( "you enter invalid thing" );
	    			break;
		    	}
			}	
	    	System.out.println( "Enter your query to enter in required table : " );
	    	String query2 = scanner.nextLine();
    		parser.setInput(query2);
	    	if (toGetValid.getValid() == false && parser.setInput(query2)) {
	    		System.out.println( "you enter invalid thing" );
	    		break;
	    	} else {
	    		toGetValid.setValid(false);
	    	}
	    	while (continu) {
	    			System.out.println( "Enter your query to do any operation in this table : " );
		    		String query3 = scanner.nextLine();
		    		parser.setInput(query3);
		    		if (toGetValid.getValid() == false && !parser.setInput(query3)) {
			    		System.out.println( "you enter invalid thing" );
			    		break;
			    	} else {
			    		toGetValid.setValid(false);
			    	}
	    		System.out.println( "Enter 1 to continue enter query to do any operation in this table\n"
	    				+ "OR 0 to get out of this table: " );
	    		String query4 = scanner.nextLine();
	    		if (query4.equals("1")) {
	    			continu = true;
	    		} else if (query4.equals("0")){
	    			continu = false;
	    		} else {
	    			System.out.println( "you enter invalid thing" );
	    			break;
	    		}
	    	}
	    	}
	    }
	
	private static boolean Check(String query) {
		 //for create database
		int count = 0;
        String regexCreate1 = "((?<=(CREATE DATABASE\\s))\\w+)";
        Pattern patternCreate1 = Pattern.compile(regexCreate1, Pattern.CASE_INSENSITIVE);
        Matcher mCreate1 = patternCreate1.matcher(query);
        while (mCreate1.find()) {
        	count++;
        }
        if (count == 1) {
    		return true;	
        } else {
    		return false;
        }
	}
}
