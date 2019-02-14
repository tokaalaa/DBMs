package eg.edu.alexu.csd.oop.db.cs14;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParserSelect {
	private String query;

	public ParserSelect(String query) {
		this.query = query;
	}
	
	@SuppressWarnings("unused")
	public Object[][] setInput(String query) {
		int count1 = 0, count2 = 0, countAll1 = 0, countAll2 = 0;
		int countAll3 = 0, countAll4 = 0;
		int countAll5 = 0, countAll6 = 0;
		int countAll7 = 0, countAll8 = 0;
		int count = 0;
		String[] arr = new String[3];
		String table_name = null;
		String column_name = null;
		String condition = null;
		int i = 0;
		String type = null;
		Object[][] sent = null;
		boolean valid = true;
 
        query = query.trim().replaceAll(" +", " ");
		this.query = query;
		
		if (query.toLowerCase().contains("select from")) {
			type = "error";
			valid = false;
		} else if (query.toLowerCase().contains("select *")||
				query.toLowerCase().contains("select*")) {
			type = "select all";
		} else if (query.toLowerCase().contains("select")) {
			type = "select";
		} else {
			type = "error";
			valid = false;
		}
		
		if (!type.equals( "error")) {
			
		//for select without condition
		String regexSelect1= "((?<=(SELECT\\s))[\\w\\d_]+(?=\\s+))|((?<=(FROM\\s))[\\w\\d_]+)";
		Pattern patternSelect1 = Pattern.compile(regexSelect1, Pattern.CASE_INSENSITIVE);
		Matcher m1 = patternSelect1.matcher(this.query);
		while (m1.find()) {
		    count1++;
		}
		
		//for select with condition
		String regexSelect2=  "((?<=(SELECT\\s))[\\w\\d_]+(?=\\s+))|((?<=(FROM\\s))[\\w\\d_]+)"
				+ "|((?<=(where\\s))[\\w\\d=<>\\'\\s]+)";
		Pattern patternSelect2 = Pattern.compile(regexSelect2, Pattern.CASE_INSENSITIVE);
		Matcher m2 = patternSelect2.matcher(this.query);
		while (m2.find()) {
		    count2++;
		}
		
		//for select all without condition
		String regexSelectAll1 = "((?<=(SELECT\\s\\*\\sFROM\\s))[\\w\\d_]+)";
		Pattern patternSelectAll1 = Pattern.compile(regexSelectAll1, Pattern.CASE_INSENSITIVE);
		Matcher mAll1 = patternSelectAll1.matcher(this.query);
		while (mAll1.find()) {
		    countAll1++;
		}
		
		//for select all without condition
		String regexSelectAll2 = "((?<=(SELECT\\s\\*FROM\\s))[\\w\\d_]+)";
		Pattern patternSelectAll2 = Pattern.compile(regexSelectAll2, Pattern.CASE_INSENSITIVE);
		Matcher mAll2 = patternSelectAll2.matcher(this.query);
		while (mAll2.find()) {
		    countAll2++;
		}
		
		//for select all without condition
		String regexSelectAll3 = "((?<=(SELECT\\*\\sFROM\\s))[\\w\\d_]+)";
		Pattern patternSelectAll3 = Pattern.compile(regexSelectAll3, Pattern.CASE_INSENSITIVE);
		Matcher mAll3 = patternSelectAll3.matcher(this.query);
		while (mAll3.find()) {
		    countAll3++;
		}
				
		//for select all with condition
		String regexSelectAll4 = "((?<=(SELECT\\s\\*\\sFROM\\s))[\\w\\d_]+)"
				+ "|((?<=(where\\s))[\\w\\d=<>\\'\\s]+)";
		Pattern patternSelectAll4 = Pattern.compile(regexSelectAll4, Pattern.CASE_INSENSITIVE);
		Matcher mAll4 = patternSelectAll4.matcher(this.query);
		while (mAll4.find()) {
		    countAll4++;
		}
				
		//for select all with condition
		String regexSelectAll5 = "((?<=(SELECT\\s\\*FROM\\s))[\\w\\d_]+)"
				+ "|((?<=(where\\s))[\\w\\d=<>\\']+)";
		Pattern patternSelectAll5 = Pattern.compile(regexSelectAll5, Pattern.CASE_INSENSITIVE);
		Matcher mAll5 = patternSelectAll5.matcher(this.query);
		while (mAll5.find()) {
		    countAll5++;
		}
				
		//for select all with condition
		String regexSelectAll6 = "((?<=(SELECT\\*\\sFROM\\s))[\\w\\d_]+)"
				+ "|((?<=(where\\s))[\\w\\d=<>\\']+)";
		Pattern patternSelectAll6 = Pattern.compile(regexSelectAll6, Pattern.CASE_INSENSITIVE);
		Matcher mAll6 = patternSelectAll6.matcher(this.query);
		while (mAll6.find()) {
		    countAll6++;
		}
		
		//for select all without condition
		String regexSelectAll7 = "((?<=(SELECT\\*FROM\\s))[\\w\\d_]+)";
		Pattern patternSelectAll7 = Pattern.compile(regexSelectAll7, Pattern.CASE_INSENSITIVE);
		Matcher mAll7 = patternSelectAll7.matcher(this.query);
		while (mAll7.find()) {
		    countAll7++;
		}
		
		//for select all with condition
		String regexSelectAll8 = "((?<=(SELECT\\*FROM\\s))[\\w\\d_]+)"
				+ "|((?<=(where\\s))[\\w\\d=<>\\']+)";
		Pattern patternSelectAll8 = Pattern.compile(regexSelectAll8, Pattern.CASE_INSENSITIVE);
		Matcher mAll8 = patternSelectAll8.matcher(this.query);
		while (mAll8.find()) {
		    countAll8++;
		}
		Dealwithxml data;
		
		if (count2 == 3 && type.equals("select")) {
			m2 = patternSelect2.matcher(query);
			count = 0;
			while (m2.find()) {
				arr[count] = m2.group(0);
				count++;
			}
			column_name = arr[0].replaceAll(" ", "");
			table_name = arr[1];
			boolean read = CreateDTD.readNamesAndTypes(table_name);
			if (!table_name.equalsIgnoreCase(ParserCreateTable.currentTable) && !read) {
				valid = false;
			}
			condition = arr[2];
			String split[] = new String[2];
			boolean checkString ;
			String operation = null;
			if (condition.contains("==")) {
				valid = false;
			} else if (condition.contains("=")) {
				split = arr[2].split("=");
				operation = "=";
			} else if (condition.contains(">")) {
				split = arr[2].split(">");
				operation = ">";
			} else if (condition.contains("<")) {
				split = arr[2].split("<");
				operation = "<";
			}
			if (split.length != 2) {
				valid = false;
			}
			if (split[1].contains("'")) {
				checkString = true;
				split[1] = split[1].replaceAll("'", "");
				split[1] = split[1].replaceAll(" ", "");
			} else {
				checkString = false;
			}
			if ((!operation.equals("=")) && checkString == true) {
				valid = false;
			}
			split[0] = split[0].replace(" ", "");
			split[1] = split[1].replaceAll(" ", "");
			
			if(valid == true) {
				data = Dealwithxml.newInstance();
				data.setValid(valid);
				sent = data.select(table_name, column_name, split[1], split[0], operation, checkString);
			}
		} else if (count1 == 2 && type.equals("select")) {
			m1 = patternSelect1.matcher(query);
			while (m1.find()) {
				arr[count] = m1.group(0);
				count++;
			}
			column_name = arr[0].replaceAll(" ", "");
			table_name = arr[1];
			boolean read = CreateDTD.readNamesAndTypes(table_name);
			if (!table_name.equalsIgnoreCase(ParserCreateTable.currentTable) && !read) {
				valid = false;
			}
			if(valid == true) {
				data = Dealwithxml.newInstance();
				data.setValid(valid);
				sent = data.select(table_name, column_name, null, null, null, false);
			}
		} else if (countAll4 == 2 && type.equals("select all")) {
			mAll4 = patternSelectAll4.matcher(query);
			while (mAll4.find()) {
				arr[count] = mAll4.group(0);
				count++;
			}

			table_name = arr[0];
			boolean read = CreateDTD.readNamesAndTypes(table_name);
			if (!table_name.equalsIgnoreCase(ParserCreateTable.currentTable) && !read) {
				valid = false;
			}
			condition = arr[1].replaceAll(" ", "");
			String split[] = null;
			boolean checkString ;
			String operation = null;
			if (condition.contains("==")) {
				valid = false;
			} else if (condition.contains("=")) {
				split = arr[1].split("=");
				operation = "=";
			} else if (condition.contains(">")) {
				split = arr[1].split(">");
				operation = ">";
			} else if (condition.contains("<")) {
				split = arr[1].split("<");
				operation = "<";
			}
			if (split.length != 2) {
				valid = false;
			}
			if (split[1].contains("'")) {
				checkString = true;
				split[1] = split[1].replaceAll("'", "");
				split[1] = split[1].replaceAll(" ", "");
			} else {
				checkString = false;
			}
			if ((!operation.equals("=")) && checkString == true) {
				valid = false;
			}
			split[0] = split[0].replaceAll(" ", "");
			split[1] = split[1].replaceAll(" ", "");
			if(valid == true) {
				data = Dealwithxml.newInstance();
				data.setValid(valid);
				sent = data.select(table_name, null, split[1], split[0], operation, checkString);
			}
		} else if (countAll5 == 2 && type.equals("select all")) {
			mAll5 = patternSelectAll5.matcher(query);
			while (mAll5.find()) {
				arr[count] = mAll5.group(0);
				count++;
			}
			table_name = arr[0];
			boolean read = CreateDTD.readNamesAndTypes(table_name);
			if (!table_name.equalsIgnoreCase(ParserCreateTable.currentTable) && !read) {
				valid = false;
			}
			condition = arr[1].replaceAll(" ", "");
			String split[] = null;
			boolean checkString ;
			String operation = null;
			if (condition.contains("==")) {
				valid = false;
			} else if (condition.contains("=")) {
				split = arr[1].split("=");
				operation = "=";
			} else if (condition.contains(">")) {
				split = arr[1].split(">");
				operation = ">";
			} else if (condition.contains("<")) {
				split = arr[1].split("<");
				operation = "<";
			}
			if (split.length != 2) {
				valid = false;
			}
			if (split[1].contains("'")) {
				checkString = true;
				split[1] = split[1].replaceAll("'", "");
				split[1] = split[1].replaceAll(" ", "");
			} else {
				checkString = false;
			}
			if ((!operation.equals("=")) && checkString == true) {
				valid = false;
			}
			split[0] = split[0].replaceAll(" ", "");
			split[1] = split[1].replaceAll(" ", "");
			if(valid == true) {
				data = Dealwithxml.newInstance();
				data.setValid(valid);
				sent = data.select(table_name, null, split[1], split[0], operation, checkString);
			}
		} else if (countAll6 == 2 && type.equals("select all")) {
			mAll6 = patternSelectAll6.matcher(query);
			while (mAll6.find()) {
				arr[count] = mAll6.group(0);
				count++;
			}
			table_name = arr[0];
			boolean read = CreateDTD.readNamesAndTypes(table_name);
			if (!table_name.equalsIgnoreCase(ParserCreateTable.currentTable) && !read) {
				valid = false;
			}
			condition = arr[1];
			String split[] = null;
			boolean checkString ;
			String operation = null;
			if (condition.contains("==")) {
				valid = false;
			} else if (condition.contains("=")) {
				split = arr[1].split("=");
				operation = "=";
			} else if (condition.contains(">")) {
				split = arr[1].split(">");
				operation = ">";
			} else if (condition.contains("<")) {
				split = arr[1].split("<");
				operation = "<";
			}
			if (split.length != 2) {
				valid = false;
			}
			if (split[1].contains("'")) {
				checkString = true;
				split[1] = split[1].replaceAll("'", "");
				split[1] = split[1].replaceAll(" ", "");
			} else {
				checkString = false;
			}
			if ((!operation.equals("=")) && checkString == true) {
				valid = false;
			}
			split[0] = split[0].replaceAll(" ", "");
			split[1] = split[1].replaceAll(" ", "");
			if(valid == true) {
				data = Dealwithxml.newInstance();
				data.setValid(valid);
				sent = data.select(table_name, null, split[1], split[0], operation, checkString);
			}
		} else if (countAll8 == 2 && type.equals("select all")) {
			mAll6 = patternSelectAll6.matcher(query);
			while (mAll6.find()) {
				arr[count] = mAll6.group(0);
				count++;
			}
			table_name = arr[0];
			boolean read = CreateDTD.readNamesAndTypes(table_name);
			if (!table_name.equalsIgnoreCase(ParserCreateTable.currentTable) && !read) {
				valid = false;
			}
			condition = arr[1];
			String split[] = null;
			boolean checkString ;
			String operation = null;
			if (condition.contains("==")) {
				valid = false;
			} else if (condition.contains("=")) {
				split = arr[1].split("=");
				operation = "=";
			} else if (condition.contains(">")) {
				split = arr[1].split(">");
				operation = ">";
			} else if (condition.contains("<")) {
				split = arr[1].split("<");
				operation = "<";
			}
			if (split.length != 2) {
				valid = false;
			}
			if (split[1].contains("'")) {
				checkString = true;
				split[1] = split[1].replaceAll("'", "");
				split[1] = split[1].replaceAll(" ", "");
			} else {
				checkString = false;
			}
			if ((!operation.equals("=")) && checkString == true) {
				valid = false;
			}
			split[0] = split[0].replaceAll(" ", "");
			split[1] = split[1].replaceAll(" ", "");
			if(valid == true) {
				data = Dealwithxml.newInstance();
				data.setValid(valid);
				sent = data.select(table_name, null, split[1], split[0], operation, checkString);
			}
		} else if (countAll1 == 1 && type.equals("select all")) {
			mAll1 = patternSelectAll1.matcher(query);
			while (mAll1.find()) {
				arr[count] = mAll1.group(0);
				count++;
			}
			table_name = arr[0];
			boolean read = CreateDTD.readNamesAndTypes(table_name);
			if (!table_name.equalsIgnoreCase(ParserCreateTable.currentTable) && !read) {
				valid = false;
			}
			if(valid == true) {
				data = Dealwithxml.newInstance();
				data.setValid(valid);
				sent = data.select(table_name, null, null, null, null, false);
			}
		} else if (countAll2 == 1 && type.equals("select all")) {
			mAll2 = patternSelectAll2.matcher(query);
			while (mAll2.find()) {
				arr[count] = mAll2.group(0);
				count++;
			}
			table_name = arr[0];
			boolean read = CreateDTD.readNamesAndTypes(table_name);
			if (!table_name.equalsIgnoreCase(ParserCreateTable.currentTable) && !read) {
				valid = false;
			}
			if(valid == true) {
				data = Dealwithxml.newInstance();
				data.setValid(valid);
				sent = data.select(table_name, null, null, null, null, false);
			}
		} else if (countAll3 == 1 && type.equals("select all")) {
			mAll3 = patternSelectAll3.matcher(query);
			while (mAll3.find()) {
				arr[count] = mAll3.group(0);
				count++;
			}
			table_name = arr[0];
			boolean read = CreateDTD.readNamesAndTypes(table_name);
			if (!table_name.equalsIgnoreCase(ParserCreateTable.currentTable) && !read) {
				valid = false;
			}
			if(valid == true) {
				data = Dealwithxml.newInstance();
				data.setValid(valid);
				sent = data.select(table_name, null, null, null, null, false);
			}
		} else if (countAll7 == 1 && type.equals("select all")) {
			mAll7 = patternSelectAll7.matcher(query);
			while (mAll7.find()) {
				arr[count] = mAll7.group(0);
				count++;
			}
			table_name = arr[0];
			boolean read = CreateDTD.readNamesAndTypes(table_name);
			if (!table_name.equalsIgnoreCase(ParserCreateTable.currentTable) && !read) {
				valid = false;
			}
			if(valid == true) {
				data = Dealwithxml.newInstance();
				data.setValid(valid);
				sent = data.select(table_name, null, null, null, null, false);
			}
		} else {
			valid = false;
		}
		
	  }
		return sent;
	}
	
}
