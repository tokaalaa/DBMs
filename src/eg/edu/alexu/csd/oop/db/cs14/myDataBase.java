package eg.edu.alexu.csd.oop.db.cs14;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.regex.Pattern;

import eg.edu.alexu.csd.oop.db.Database;

public class myDataBase implements Database{
	protected static  String currentDatabase = null;
	private boolean flagCreateDB = false;
  
	@Override
	public String createDatabase(String databaseName, boolean dropIfExists) {
		//check if name is valid
		if (!validName(databaseName)) {
			return null;
		}
		File f1 = new File("main folder" + System.getProperty("file.separator") +
		databaseName);
		String query;
		//check if the database exists to delete it
		if (dropIfExists && f1.exists()) {
			try {
				query = "DROP DATABASE " + databaseName;
				executeStructureQuery(query);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		} 
		if (!f1.exists()) {
		query = "CREATE DATABASE " + databaseName;
		try {
			executeStructureQuery(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		return f1.getAbsolutePath();
	}

	@Override
	public boolean executeStructureQuery(String query) throws SQLException {
		// TODO Auto-generated method stub
		String name;
		String createDatabase = "CREATE DATABASE ";
		String createTable = "CREATE TABLE ";
		String dropDatabase = "DROP DATABASE ";
		String dropTable = "DROP TABLE ";
		query = query.replaceAll("\\s{2,}", " ").trim();
		String q = query.toUpperCase();
		if (q.indexOf(createDatabase) == 0) {
			name = query.substring(createDatabase.length(), q.length());
			if(validName(name)) {
			File f1 = new File("main folder" + System.getProperty("file.separator") +
					name);
					f1.mkdirs();
					try {
						f1.createNewFile();
						currentDatabase = name;
						flagCreateDB = true;
					} catch (IOException e1) {
						
						e1.printStackTrace();
					}
					return true;}
			return false;
		}else if(q.indexOf(dropDatabase) == 0) {
			name = query.substring(dropDatabase.length(), q.length());
			File file = new File("main folder" + System.getProperty("file.separator") +
					name);
			if (file.exists()) {
				try {
					//Deleting the directory recursively.
					delete(file);
					if (currentDatabase != null && currentDatabase.equals(name)) {
						currentDatabase = null;
						flagCreateDB = false;
					}
					return true;
				} catch (IOException e) {
					
					e.printStackTrace();
				}} else {
					return false;
				}
		}else if(q.indexOf(createTable) == 0) {
			if (flagCreateDB == false) {
					throw new  SQLException();
			}
			else {
				ParserCreateTable hsd = new ParserCreateTable(query);

					return hsd.setInput(query);
			}
		}else if(q.indexOf(dropTable) == 0){
			name = query.substring(dropTable.length(), q.length());
			File folder = new File("main folder" + System.getProperty("file.separator") +
					currentDatabase);
			File[] listOfFiles = folder.listFiles();

			for (int i = 0; i < listOfFiles.length; i++) {
			  if (listOfFiles[i].isFile()) {
				  if (name.equalsIgnoreCase(listOfFiles[i].getName())) {
					  name = listOfFiles[i].getName();
					  break;
					  }
				  }
			  }
			Path table = Paths.get("main folder" + System.getProperty("file.separator") +
					currentDatabase + System.getProperty("file.separator") + name + ".xml");
			Path dtd = Paths.get("main folder" + System.getProperty("file.separator") +
					currentDatabase + System.getProperty("file.separator") + name + "type2.dtd");
			Path types = Paths.get("main folder" + System.getProperty("file.separator") +
					currentDatabase + System.getProperty("file.separator") + name + "type3.txt");
			File f1 = new File("main folder" + System.getProperty("file.separator") +
					currentDatabase + System.getProperty("file.separator") + name + ".xml");
			File f2 = new File("main folder" + System.getProperty("file.separator") +
					currentDatabase + System.getProperty("file.separator") + name + "type2.dtd");
			File f3 = new File("main folder" + System.getProperty("file.separator") +
					currentDatabase + System.getProperty("file.separator") + name + "type3.txt");
			if (Files.exists(table) && Files.exists(dtd) && Files.exists(types)) {
				if (!f1.delete() || !f2.delete() || !f3.delete()) {
					try {
						throw new IOException();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					return true;
				}
			}
		} 
	
		return false;
	}

	@Override
	public Object[][] executeQuery(String query) throws SQLException {
		// TODO Auto-generated method stub
		ParserSelect select = new ParserSelect(query);
		return select.setInput(query);
	}

	@Override
	public int executeUpdateQuery(String query) throws SQLException {
		ParserOperation par = new ParserOperation(query);
		int count = par.setInput(query);
		if (count != -1) {
			return count;
		} else throw new SQLException();
	}
	/**
	 * Delete a file or a directory and its children.
	 * @param file The directory to delete.
	 * @throws IOException Exception when problem occurs during deleting the directory.
	 */
	private static void delete(File file) throws IOException {
 
		for (File childFile : file.listFiles()) {
 
			if (childFile.isDirectory()) {
				delete(childFile);
			} else {
				if (!childFile.delete()) {
					throw new IOException();
				}
			}
		}
 
		if (!file.delete()) {
			throw new IOException();
		}
	}
/**
 * check valid name for the folder of database or table
 * @param name
 * @return true if valid
 */
	private boolean validName(String name) {
		// special characters is forbidden.
		if((Pattern.compile("[////# % & * ^ : ; ? < > + = $]").matcher(name).find()) ||
				name == null || name.equals("") || name.equals("//s")) {
			return false;
		}
		return true;
	}

	@Override
	public String[][] columnName() {
		// TODO Auto-generated method stub
		return CreateDTD.getTable();
	}

	@Override
	public String getTableName() {
		// TODO Auto-generated method stub
		return ParserCreateTable.currentTable;
	}


}
