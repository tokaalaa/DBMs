package eg.edu.alexu.csd.oop.jdbc.cs14;

import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import eg.edu.alexu.csd.oop.db.Database;
import eg.edu.alexu.csd.oop.db.cs14.Dealwithxml;
import eg.edu.alexu.csd.oop.db.cs14.myDataBase;

public class Main2 {

	@SuppressWarnings({ "resource", "unused"})
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Driver driver = new Driver();
    	Database database = new myDataBase();
    	Connection connection = null;
    	Statement statement = null;
    	ResultSet resultset = null;
    	ResultSetMetaData resultSetMetaData = null;
	    Scanner scanner = new Scanner( System.in );
		boolean continu = true;
    	Dealwithxml toGetValid = Dealwithxml.newInstance();
    	int firstInput = 0;
    	
    	System.out.println("Enter SQL Url");
    	String url = scanner.nextLine();
    	
	    while(continu) {
	    		try {
	    			if (driver.acceptsURL(url)) {
	    				System.out.println("Enter your path");
	    		    	String path = scanner.nextLine();
	    		    	if (Check4(path)) {
	    		    		Properties info = new Properties();
	    		    		File dbDir = new File("sample" + System.getProperty("file.separator") + path);
	    		            info.put("path", dbDir.getAbsoluteFile());
		    				connection = driver.connect(url, info);
	    		    	} else {
		    				System.out.println("You enter invalid path");
		    				break;
		    			}
	    			} else {
	    				System.out.println("You enter invalid url");
	    				break;
	    			}
	    		} catch (SQLException e) {
	    			// TODO Auto-generated catch block
	    			System.out.println( "you enter invalid thing" );
	    		}
	    	
	    		System.out.println("Enter 1 to continue\nOR 0 to exit");
	    		String close = scanner.nextLine();
	    		if (close.equals("1")) {
	    			try {
	    				statement = connection.createStatement();
	    			} catch (SQLException e) {
	    				// TODO Auto-generated catch block
	    				System.out.println( "you enter invalid thing" );
	    			}
	    		} else if(close.equals("0")) {
	    			break;
	    		} else {
	    			System.out.println("You enter invalid input");
	    			break;
	    		}
		    	System.out.println( "Enter your query to create/drop your database : " );
		    	String query1 = scanner.nextLine();	    	
		    	if ((Check(query1) == false)) {
		    		System.out.println( "you enter invalid thing" );
		    		break;
		    	}
		    	try {
					statement.execute(query1);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
		    		System.out.println( "you enter invalid thing" );
				}
		    	boolean check = false;
		    	int first = 0;
		    	while (continu) {
		    			if (first > 0) {
			    			System.out.println( "Enter 1 to execute Batch or 2 to clear Batch\n"
			    					+ " or 3 to continue and not do any of them" );
				    		String enter = scanner.nextLine();
			    			if (enter.equals("1")) {
			    				int[] result = null;
								try {
									result = statement.executeBatch();
								} catch (SQLException e) {
									// TODO Auto-generated catch block
			    					System.out.println( "can't execute batch" );
								}
			    				try {
									statement.clearBatch();
								} catch (SQLException e) {
									// TODO Auto-generated catch block
			    					System.out.println( "can't clear batch" );
								}
			    				first = 0;
			    			} else if (enter.equals("2")) {
			    				try {
									statement.clearBatch();
								} catch (SQLException e) {
									// TODO Auto-generated catch block
			    					System.out.println( "can't clear batch" );
								}
			    				first = 0;
			    			} else if (enter.equals("3")) {
			    				
			    			} else {
				    			System.out.println( "you enter invalid thing and will not do any of them" );
			    			}
		    			}
		    			System.out.println( "Enter your query : " );//to do any operation in this table
			    		String query3 = scanner.nextLine();
				        query3 = query3.trim().replaceAll(" +", " ");
		    			System.out.println( "Enter 1 to add your query in batch OR 0 to not do this" );//to put in Batch
		    			String put = scanner.nextLine();
		    			boolean putBatch = false;
		    			if (put.equals("1")) {
		    				putBatch = true;
		    				first++;
		    			} else if (put.equals("0")) {
		    				putBatch = false;
		    			} else {
			    			System.out.println( "you enter invalid thing and will not add this query in batch" );
		    				putBatch = false;
		    			}
			    		if (query3.toLowerCase().contains("drop database")) {
				    		if (Check2(query3) == false) {
				    			System.out.println( "you enter invalid thing" );
				    			break;
				    		} else {
				    				try {
				    					if(!putBatch) {
				    					check = statement.execute(query3);
				    					break;
				    					} else {
						    				statement.addBatch(query3);
						    			}
				    				} catch (SQLException e) {
				    					// TODO Auto-generated catch block
				    					System.out.println( "you enter invalid thing" );
				    				}
				    		}
				    	}
			    		if (query3.toLowerCase().contains("drop table")) {
				    		if (Check3(query3) == false) {
				    			System.out.println( "you enter invalid thing" );
				    			break;
				    		} else {
				    			try {
				    				if(!putBatch) {
						    		check = statement.execute(query3);
									break;
				    				} else {
					    				statement.addBatch(query3);
					    			}
								} catch (SQLException e) {
									// TODO Auto-generated catch block
						    		System.out.println( "you enter invalid thing" );
								}
				    			
				    		}
				    	}
			    		if (query3.toLowerCase().contains("select")) {
			    			try {
				    			if (!putBatch) {
					    		check = statement.execute(query3);
				    			} else {
				    				statement.addBatch(query3);
				    			}
							} catch (SQLException e) {
								// TODO Auto-generated catch block
					    		System.out.println( "you enter invalid thing" );
							}
			    		} else {
			    		try {
			    			if (!putBatch) {
				    		check = statement.execute(query3);
			    			} else {
			    				statement.addBatch(query3);
			    			}
						} catch (SQLException e) {
							// TODO Auto-generated catch block
				    		System.out.println( "you enter invalid thing" );
						}
			    		}
			    		if (toGetValid.getValid() == false && !check && !putBatch) {
				    		System.out.println( "you enter invalid thing" );
				    		break;
				    	} else {
				    		if(query3.toLowerCase().contains("select") && !putBatch) {
				    			try {
				    				resultset = statement.executeQuery(query3);
				    				while (resultset.next()) {
				    					resultSetMetaData = resultset.getMetaData();
					    				int columnsNumber = resultSetMetaData.getColumnCount();
				    				    for (int i = 1; i <= columnsNumber; i++) {
				    				        if (i > 1) System.out.print(",  ");
				    				        System.out.print(i + " : " + resultSetMetaData.getColumnName(i)
				    				        + " : " + resultset.getObject(i));
				    				    }
				    				    System.out.println("");
				    				}
								} catch (SQLException e) {
									// TODO Auto-generated catch block
						    		System.out.println( "you enter invalid thing" );
								}
				    		}
				    		toGetValid.setValid(false);
				    	}
		    		System.out.println( "Enter 1 to continue enter query to do any operation in this table\n"
		    				+ "OR 0 to get out of this table: " );
		    		String query4 = scanner.nextLine();
		    		if (query4.equals("1")) {
		    			continu = true;
		    		} else if (query4.equals("0")){
		    			break;
		    		} else {
		    			System.out.println( "you enter invalid thing" );
		    			break;
		    		}
		    	}
		    	try {
				statement.close();
				connection.close();
		    	} catch (SQLException e) {
					// TODO Auto-generated catch block
		    		System.out.println( "you enter invalid thing" );
				}
	    }
	    }
	private static boolean Check4(String path) {
		// TODO Auto-generated method stub
        //C:\Users\lenovo\git\our-team\our-team\sample\34728
		if (path.contains(":") || path.contains(" ")) {
			return false;
		}
		int count = 0;
		String regexCreate1 = "([a-zA-Z0-9_.-]+)+\\\\?";
		Pattern patternCreate1 = Pattern.compile(regexCreate1, Pattern.CASE_INSENSITIVE);
		Matcher mCreate1 = patternCreate1.matcher(path);
		while (mCreate1.find()) {
			count++;
		}
		if (count >= 1) {
			return true;	
		} else {
			return false;
		}
	}
	private static boolean Check3(String query2) {
			// TODO Auto-generated method stub
			int count = 0;
			String regexCreate1 = "((?<=(DROP TABLE\\s))\\w+)";	
			Pattern patternCreate1 = Pattern.compile(regexCreate1, Pattern.CASE_INSENSITIVE);
			Matcher mCreate1 = patternCreate1.matcher(query2);
			while (mCreate1.find()) {
				count++;
			}
			if (count == 1) {
				return true;	
			} else {
				return false;
			}
	}
	private static boolean Check2(String query1) {
		// TODO Auto-generated method stub
			int count = 0;
	       String regexCreate1 = "((?<=(DROP DATABASE\\s))\\w+)";
	       Pattern patternCreate1 = Pattern.compile(regexCreate1, Pattern.CASE_INSENSITIVE);
	       Matcher mCreate1 = patternCreate1.matcher(query1);
	       while (mCreate1.find()) {
	       	count++;
	       }
	       if (count == 1) {
	   		return true;	
	       } else {
	   		return false;
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

