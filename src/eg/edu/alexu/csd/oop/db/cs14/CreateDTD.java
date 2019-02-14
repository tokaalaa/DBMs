package eg.edu.alexu.csd.oop.db.cs14;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;

public class CreateDTD {
	private String tableName;

	private static String[][] table;
	
	public CreateDTD(String table_name) {
		this.tableName = table_name;
		table = null;
	}

	@SuppressWarnings("unused")
	public void create() throws IOException {
		Dealwithxml data = Dealwithxml.newInstance();
		myDataBase database = new myDataBase();
		FileWriter fileWriter;
		
		File main_folder = new File("main folder");
		if (!main_folder.exists()) {
			main_folder.mkdir();
			fileWriter = new FileWriter("main folder"+ System.getProperty("file.separator")
			+ myDataBase.currentDatabase + System.getProperty("file.separator") +  tableName + "type2.dtd");
		} else {
			fileWriter = new FileWriter("main folder"+ System.getProperty("file.separator")
			+ myDataBase.currentDatabase + System.getProperty("file.separator") +  tableName + "type2.dtd");
		}
		
	    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
     	bufferedWriter.write("<!ELEMENT " + tableName +" (row)*>\n");
     	bufferedWriter.write("<!ELEMENT " + "row (");
     	for (int i = 0; i < getTable().length; i++) {
     		if (i == (getTable().length - 1)) {
             	bufferedWriter.write(getTable()[i][0] +")>\n");
     		} else {
             	bufferedWriter.write(getTable()[i][0] +",");
     		}
     	}
     	for (int i = 0; i < getTable().length; i++) {
         	bufferedWriter.write("<!ELEMENT " + getTable()[i][0] +" (#PCDATA)>\n");
     	}
        bufferedWriter.close();
        writeNamesAndTypes();
	}
	
	public String[][] get(String path) throws FileNotFoundException {
	     String line = null;
	     String[] Column_name = new String[5];
	     String table_name;
	     String allColumn;
	     int noColumn;
	     
	     //System.out.println(path);
	     FileReader fileReader =  new FileReader(path);
	     BufferedReader bufferedReader = new BufferedReader(fileReader);
	        try {
	        	line = bufferedReader.readLine();
	            int firstIndex = line.indexOf("(row)*"); 
	            table_name = line.substring(10, firstIndex - 1);

	        	line = bufferedReader.readLine();
	        	allColumn = line.substring(12 + table_name.length(), 14 + table_name.length());
	        	Column_name = allColumn.split(",");
	        	noColumn = Column_name.length;
	        	
	        	setTable(new String[noColumn][2]);
	        	for (int i = 0; i < noColumn; i++) {
		        	line = bufferedReader.readLine();
		        	String[] row = line.split("#",2);
		        	getTable()[i][0] = Column_name[i];
		        	getTable()[i][1] = row[1].substring(0, row[1].length() - 2);
	        	}
	            bufferedReader.close(); 
	        } catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		return getTable();	
	}
	
	public static String[][] getTable() {
		//System.out.println("gettable");
		return table;
	}

	@SuppressWarnings("static-access")
	public void setTable(String[][] table) {
		//System.out.println("settable");
		this.table = table;
	}
	
	public static boolean readNamesAndTypes(String tableName) {
		boolean exist = false;
		File folder = new File("main folder"+ System.getProperty("file.separator")
		+ myDataBase.currentDatabase);
		File[] listOfFiles = folder.listFiles();
		
		for (int i = 0; i < listOfFiles.length; i++) {
		  if (listOfFiles[i].isFile()) {
			  if (listOfFiles[i].getName().equalsIgnoreCase(tableName + ".xml")) {
				 ParserCreateTable.currentTable = tableName;
				 System.out.println(tableName);
				  exist = true;
				  readfile(tableName);
				  break;
			  }
		  }}
		
		return exist;
		
	} 
	
	private static void readfile(String tableName) {
		// TODO Auto-generated method stub
		File file = new File("main folder"+ System.getProperty("file.separator")
		+ myDataBase.currentDatabase + System.getProperty("file.separator") +  tableName + "type3.txt"); 
		  
		  BufferedReader br;
		  ArrayList<String>s1 = new ArrayList<String>();
		  ArrayList<String>s2 = new ArrayList<String>();
		try {
			br = new BufferedReader(new FileReader(file)); 
			  String st; 
			  int i = 0;
				while ((st = br.readLine()) != null) { 
				   String[] s = st.split(":");
				   s1.add(s[0]);
				   s2.add(s[1]);
				  }
				br.close();
				table = new String[s1.size()][2];
				for (i = 0; i < s1.size() ; i++) {
					table[i][0] = s1.get(i);
					table[i][1] = s2.get(i);
				}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void writeNamesAndTypes() {
		try {
			FileWriter fileWriter = new FileWriter("main folder"+ System.getProperty("file.separator")
			+ myDataBase.currentDatabase + System.getProperty("file.separator") +  tableName + "type3.txt");
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			for (int i = 0; i < table.length; i++) {
				bufferedWriter.write(table[i][0] + ":" + table[i][1] + "\n");
			}
			bufferedWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
