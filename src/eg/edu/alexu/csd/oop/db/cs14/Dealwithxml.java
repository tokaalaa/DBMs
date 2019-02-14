package eg.edu.alexu.csd.oop.db.cs14;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import eg.edu.alexu.csd.oop.db.DealWithXml;
public class Dealwithxml implements DealWithXml{
	private String[][] table;
	private File xmlFile;
	private DocumentBuilderFactory documentBuilderfactory;
	private DocumentBuilder documentBuilder;
	private Document document;
	private String currentTable = null;
	private static DealWithXml obj;
	private NodeList list;
	private boolean valid = false;
	
 	private Dealwithxml() {
 		
 		// for the first using of document
 		documentBuilderfactory = DocumentBuilderFactory.newInstance();
		try {
			documentBuilder = documentBuilderfactory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		document = documentBuilder.newDocument();
 		list = document.getElementsByTagName("row");
 		document.appendChild(document.createElement("table"));
 	
 	}
 	
 	public static Dealwithxml newInstance() {
 		if(obj == null) {
 			obj = new Dealwithxml();
 		} 		
 		return (Dealwithxml) obj;
 	}
 	
	@SuppressWarnings("rawtypes")
	@Override
	public int insert(String tableName, HashMap<String, String> newRow) {
		setTable(tableName);
		Element root = document.getDocumentElement();
		Element row = document.createElement("row");
		for(Map.Entry m : newRow.entrySet()) {
			Element column = document.createElement((String) m.getKey());
			column.appendChild(document.createTextNode((String) m.getValue()));
			row.appendChild(column);
		}
		root.appendChild(row);
		writeToXml();
		return 0;
	}

	@Override
	public int delete(String tableName, String columnCondition, String valueCondition,
			String operation, boolean checkString) {
		// TODO Auto-generated method stub
		Path p = Paths.get("main folder" + System.getProperty("file.separator") + myDataBase.currentDatabase +
				System.getProperty("file.separator") + tableName + ".xml");
		if(Files.exists(p)) {
		setTable(tableName);
		//remove all rows
		if (columnCondition == null) {
			int deletedrow = list.getLength();
			for(int i = 0; i < deletedrow; i++) {
				Node node = list.item(list.getLength() - 1);
				if(node.getNodeType()  == Node.ELEMENT_NODE) {
					Element element = (Element) node;
			element.getParentNode().removeChild(element);
			}
		}
			writeToXml();
			return deletedrow;
		} else {
			return deleteWithCondition(columnCondition, operation,valueCondition, checkString);
	}
		}
		return 0;
	}
	private int deleteWithCondition(String columnCondition, String operation, 
			String valueCondition, boolean checkString) {
		int deletedrows = 0;
		int count = 1;
		boolean found = false;
		// TODO Auto-generated method stub
		int length = list.getLength();
		for(int i = 0; i < length; i++) {
			Node node = list.item(list.getLength() - count);
			if(node.getNodeType()  == Node.ELEMENT_NODE) {
				Element element = (Element) node;
				NodeList childs = element.getChildNodes();
				for(int j = 0; j < childs.getLength(); j++) {
					Node n = childs.item(j);
					if(n.getNodeType()  == Node.ELEMENT_NODE) {
						Element e = (Element) n;
						// if string check column condition direct
						if (checkString && e.getTagName().equalsIgnoreCase(columnCondition)
								&& e.getTextContent().equalsIgnoreCase(valueCondition)) {
							element.getParentNode().removeChild(element);
							deletedrows++;
							found = true;
							break;
						}else if (!checkString && e.getTagName().equalsIgnoreCase(columnCondition)
								&& checkOperation(operation, e.getTextContent(), valueCondition )) {
							element.getParentNode().removeChild(element);
							deletedrows++;
							found = true;
							break;
						}
					}
				}
				if (!found) {
					count++;
				}
		}
	}
		writeToXml();
		return deletedrows;
	}

	@Override
	public int update(String tableName, String columnCondition, String valueCondition,
			HashMap<String, String> updates, String operation, boolean checkString) {
		Path p = Paths.get("main folder" + System.getProperty("file.separator") + myDataBase.currentDatabase +
				System.getProperty("file.separator") + tableName + ".xml");
		if(!Files.exists(p)) {
			return -1;
		}
			setTable(tableName);
		// update column without condition
		if(columnCondition == null) {
			return updateColumnWithoutCondition(updates);
		}else {
		// update column with condition
			return updateColumnWithCondition(columnCondition, valueCondition, updates);
		}
		}
	
	public Object[][] select(String tableName, String columnName, String valueCondition,
			String columnCondition, String operation, boolean checkString) {

		setTable(tableName);
		if (list.getLength() == 0) {
		return new Object[0][];	
		}
		ArrayList<ArrayList<String>> table = new ArrayList<ArrayList<String>>();

		if (columnCondition != null  && columnName == null) {
		//  select all with condition
			table = SelectAllWithCondition(checkString, operation,
					columnCondition, valueCondition);
		} else if (columnCondition == null && columnName == null) {
		//  select all without condition
			table = SelectAllWithoutCondition();
		} else if (columnCondition != null && columnName != null){
			// select column with condition 
			return SelectColumnWithCondition(checkString,
		operation, columnCondition, valueCondition, columnName);
		} else {
			
		}
	return convertToArray2d(table);	
	}
	
	private Object[][] convertColumn(ArrayList<String> a, String columnName) {
		// TODO Auto-generated method stub
		
		table = CreateDTD.getTable();
		String type = null;
		for(int j = 0; j < table.length; j++) {
			if (table[j][0].equalsIgnoreCase(columnName)) {
				type = table[j][1];
			}
		}
		Object[][] array = new Object[a.size()][1];
		for (int i = 0; i < a.size(); i++) {
			if(type.equalsIgnoreCase("int")) {
				array[i][0] = Integer.parseInt(a.get(i));
			} else {
				array[i][0] = a.get(i);
			}
		}
		return array;
	}

	private boolean checkOperation(String operation, String textContent, String valueCondition) {
		// TODO Auto-generated method stub
		int oldValue = Integer.parseInt(textContent);
		int condition = Integer.parseInt(valueCondition);
		switch (operation) {
		case ">": return oldValue > condition; 
		case "<": return oldValue < condition;
		case "=": return oldValue == condition;
		default : return false;
		}
	}
	
	private void readXml() {
		try {
			xmlFile = new File("main folder" + System.getProperty("file.separator") + myDataBase.currentDatabase +
					System.getProperty("file.separator") + currentTable + ".xml");
			documentBuilderfactory = DocumentBuilderFactory.newInstance();
			documentBuilder = documentBuilderfactory.newDocumentBuilder();
			document = documentBuilder.parse(xmlFile);
			
				list = document.getElementsByTagName("row");
		} catch (SAXException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
			
	}
	private void writeToXml() {
		xmlFile = new File("main folder" + System.getProperty("file.separator") + myDataBase.currentDatabase +
				System.getProperty("file.separator") + currentTable	+ ".xml");
		
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		try {
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(document);
			// main folder + database name
			StreamResult streamResult = new StreamResult(xmlFile);
			
				transformer.transform(source, streamResult);
		
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 *  write to xml if new table name != current table name
	 *  and read the new file
	 * @param tableName
	 */
	private void setTable(String tableName) {
		if(currentTable == null) {
			currentTable = tableName;	
		}else if(!tableName.equals(currentTable)) {

		currentTable = tableName;
		readXml();
		}
	}
	/**
	 * convert array list To Array2d
	 * @param array list
	 * @return 2d array of objects
	 */
	private Object[][] convertToArray2d(ArrayList<ArrayList<String>> a){
		Object[][] array = new Object[a.size()][a.get(0).size()];
		table = CreateDTD.getTable();
		for (int i = 0; i < a.size(); i++) {
			ArrayList<String> row = a.get(i);
			for(int j = 0; j < row.size(); j++) {
				if (table[j][1].equalsIgnoreCase("int")) {
				array[i][j] = Integer.parseInt(row.get(j));
				} else {
					array[i][j] = row.get(j);
				}
			}
		}
		return array;	
	}
	/**
	 * update Column Without Condition
	 * @param new updates
	 * @return number of updates
	 */
	@SuppressWarnings("rawtypes")
	private int updateColumnWithoutCondition(HashMap<String, String> updates) {
		for(int i = 0; i < list.getLength(); i++) {
			Node node = list.item(i);
			if(node.getNodeType()  == Node.ELEMENT_NODE) {
				Element element = (Element) node;
				NodeList childs = element.getChildNodes();
				for(int j = 0; j < childs.getLength(); j++) {
					Node n = childs.item(j);
					if(n.getNodeType()  == Node.ELEMENT_NODE) {
						Element e = (Element) n;
						for(Map.Entry m : updates.entrySet()) {
						if(e.getTagName().equalsIgnoreCase((String)m.getKey())) {
							e.setTextContent((String)m.getValue());
						}
						}
							}}
			}
			}
		writeToXml();
		return list.getLength();	
	}
	/**
	 * update Column With Condition
	 * @param column Condition
	 * @param value Condition
	 * @param new updates
	 * @return number of updates
	 */
	@SuppressWarnings("rawtypes")
	private int updateColumnWithCondition(String columnCondition, String valueCondition,
			HashMap<String, String> updates) {
		int numOfUpdates = 0;
		for(int i = 0; i < list.getLength(); i++) {
			Node node = list.item(i);
			if(node.getNodeType()  == Node.ELEMENT_NODE) {
				Element element = (Element) node;
				NodeList childs = element.getChildNodes();
				for(int j = 0; j < childs.getLength(); j++) {
					Node n = childs.item(j);
					if(n.getNodeType()  == Node.ELEMENT_NODE) {
						Element e = (Element) n;
						if (e.getTagName().equalsIgnoreCase(columnCondition)
								&& e.getTextContent().equalsIgnoreCase(valueCondition)) {
							numOfUpdates++;
							for(int k = 0; k < childs.getLength(); k++) {
								Node n1 = childs.item(k);
								if(n1.getNodeType()  == Node.ELEMENT_NODE) {
									Element e1 = (Element) n1;
								for(Map.Entry m : updates.entrySet()) {
									if(((String)m.getKey()).equalsIgnoreCase(e1.getTagName())) {
										e1.setTextContent((String)m.getValue());
									}
								}
								}
							}
							break;
						}
					}
				}
		}
	}
		writeToXml();
		return numOfUpdates;	
	}
	/**
	 * SelectAllWithoutCondition
	 * @return array list
	 */
	private ArrayList<ArrayList<String>> SelectAllWithoutCondition (){
		ArrayList<ArrayList<String>> table = new ArrayList<ArrayList<String>>();
		for(int i = 0; i < list.getLength(); i++) {
			Node node = list.item(i);
			if(node.getNodeType()  == Node.ELEMENT_NODE) {
				Element element = (Element) node;
				NodeList childlist = element.getChildNodes();
				ArrayList<String> row = new ArrayList<String>();
				for(int j = 0; j < childlist.getLength(); j++) {
					Node n = childlist.item(j);
					if(node.getNodeType()  == Node.ELEMENT_NODE) {
						Element e = (Element) n;
						row.add(e.getTextContent());
						}
				}
				table.add(row);
				}
			}
		return table;
	}
	
private Object[][] SelectColumnWithCondition(boolean checkString, String operation, String columnCondition, String valueCondition, String columnName) {
	ArrayList<String> column = new ArrayList<String>();
	for(int i = 0; i < list.getLength(); i++) {
		Node node = list.item(i);
		if(node.getNodeType()  == Node.ELEMENT_NODE) {
			Element element = (Element) node;
			NodeList childs = element.getChildNodes();
			for(int j = 0; j < childs.getLength(); j++) {
				Node n = childs.item(j);
				if(n.getNodeType()  == Node.ELEMENT_NODE) {
					Element e = (Element) n;
					// if string check column condition direct
					if (checkString && e.getTagName().equalsIgnoreCase(columnCondition)
							&& e.getTextContent().equalsIgnoreCase(valueCondition)) {
						for(int k = 0; k < childs.getLength(); k++) {
							Node n1 = childs.item(k);
							if(n1.getNodeType()  == Node.ELEMENT_NODE) {
								Element e1 = (Element) n1;
								if((e1.getTagName()).equalsIgnoreCase(columnName)) {
									column.add(e1.getTextContent());
							}
							}
						}
						break;
					}else if (!checkString && e.getTagName().equalsIgnoreCase(columnCondition)
							&& checkOperation(operation, e.getTextContent(), valueCondition )) {
						for(int k = 0; k < childs.getLength(); k++) {
							Node n1 = childs.item(k);
							if(n1.getNodeType()  == Node.ELEMENT_NODE) {
								Element e1 = (Element) n1;
								if((e1.getTagName()).equalsIgnoreCase(columnName)) {
									column.add(e1.getTextContent());
							}
							}
						}
						break;
					}
				}
			}
	}
}
	return convertColumn(column,columnName);	
}
/**
 * Select All With Condition
 * @param checkString
 * @param operation
 * @param columnCondition
 * @param valueCondition
 * @return 2d array
 */
private ArrayList<ArrayList<String>> SelectAllWithCondition(boolean checkString, String operation,
		String columnCondition, String valueCondition) {
	ArrayList<ArrayList<String>> table = new ArrayList<ArrayList<String>>();
	for(int i = 0; i < list.getLength(); i++) {
		Node node = list.item(i);
		if(node.getNodeType()  == Node.ELEMENT_NODE) {
			Element element = (Element) node;
			NodeList childs = element.getChildNodes();
			for(int j = 0; j < childs.getLength(); j++) {
				Node n = childs.item(j);
				if(n.getNodeType()  == Node.ELEMENT_NODE) {
					Element e = (Element) n;
					// if string check column condition direct
					if (checkString && e.getTagName().equalsIgnoreCase(columnCondition)
							&& e.getTextContent().equalsIgnoreCase(valueCondition)) {
						ArrayList<String> row = new ArrayList<String>();
						for(int k = 0; k < childs.getLength(); k++) {
							Node n1 = childs.item(k);
							if(n1.getNodeType()  == Node.ELEMENT_NODE) {
								Element e1 = (Element) n1;
								row.add(e1.getTextContent());
								}	
							}
						table.add(row);
						break;

					}else if (!checkString && e.getTagName().equalsIgnoreCase(columnCondition)
							&& checkOperation(operation, e.getTextContent(), valueCondition )) {
						ArrayList<String> row = new ArrayList<String>();
						for(int k = 0; k < childs.getLength(); k++) {
							Node n1 = childs.item(k);
							if(n1.getNodeType()  == Node.ELEMENT_NODE) {
								Element e1 = (Element) n1;
								row.add(e1.getTextContent());
								}	
							}
						table.add(row);
						break;
					}
				}
			}
	}
}
	return table;	
}

	@Override
	public boolean getValid() {
		return valid;
	}

	@Override
	public void setValid(boolean state) {
		valid = state;
	}
}
