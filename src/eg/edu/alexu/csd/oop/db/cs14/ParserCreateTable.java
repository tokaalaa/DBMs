package eg.edu.alexu.csd.oop.db.cs14;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class ParserCreateTable {
	public static String currentTable = null;
	private String query;
	private String[][] table;
	
	public ParserCreateTable(String query) {
		this.query = query;
	}
	
	public String getInput() {		
		return query;		
	}
 	
	@SuppressWarnings("unused")
	public boolean setInput(String query) throws SQLException {
        query = query.trim().replaceAll(" +", " ");
		this.query = query;
		int count = 0;
		boolean valid = true;
		String[] arr = new String[2];
		String table_name = null;
		int noColumns = 0;
		int i = 0;

		//for create table
        /*String s4 = "CREATE TABLE Persons (PersonID int,LastName varchar,"+
        	    "FirstName varchar,Address varchar,City varchar)";*/
		String regexCreate = "((?<=(CREATE TABLE\\s))\\w+)|((?<=\\()([\\w\\s_,]+)+(?=\\)))";
		Pattern patternCreate = Pattern.compile(regexCreate, Pattern.CASE_INSENSITIVE);
		Matcher m = patternCreate.matcher(this.query);
		while (m.find()) {
			arr[count] = m.group(0);
			count++;
		}
		if (count != 2) {
			valid = false;
			throw new  SQLException();
		} else {
			table_name = arr[0];
			String split[] = arr[1].split(", ");
			noColumns = split.length;
			 table = new String[split.length][2];

			while(i < split.length) {
				if (split[i] == null) {
					valid = false;
					break;
				}
				String split2[] = split[i].split(" ");
				if (split2.length != 2) {
					valid = false;
					break;
				}
				table[i][0] = split2[0];
				
				if (!(split2[1].equalsIgnoreCase("varchar") || split2[1].equalsIgnoreCase("int"))) {
					valid = false;
					break;
				} else {
					table[i][1] = split2[1];
				}

				i++;
			}
		}
		Path p = Paths.get("main folder" + System.getProperty("file.separator") + myDataBase.currentDatabase +
				System.getProperty("file.separator") + table_name + ".xml");
		Path p2 = Paths.get("main folder" + System.getProperty("file.separator") + myDataBase.currentDatabase +
				System.getProperty("file.separator") + table_name + "type2.dtd");
		if(Files.exists(p) && Files.exists(p2)) {
		 valid = false;	
		}
		if(valid == true) {
			Dealwithxml data = Dealwithxml.newInstance();
			data.setValid(valid);
			CreateDTD dtd = new CreateDTD(table_name);
			createTable(table_name);
			dtd.setTable(table);
			try {
				dtd.create();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return valid;
		
	}
	
	private void createTable(String table_name) {
		DocumentBuilderFactory documentBuilderfactory = DocumentBuilderFactory.newInstance();
		try {
		DocumentBuilder documentBuilder = documentBuilderfactory.newDocumentBuilder();
		Document document = documentBuilder.newDocument();
		Element table = document.createElement("table");
		document.appendChild(table);
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		try {
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(document);
			// main folder + database name
			StreamResult streamResult = new StreamResult(
			new File("main folder" + System.getProperty("file.separator") + myDataBase.currentDatabase +
					System.getProperty("file.separator") + table_name + ".xml"));
			
				transformer.transform(source, streamResult);
		currentTable = table_name;
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} catch (ParserConfigurationException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
}
