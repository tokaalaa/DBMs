package eg.edu.alexu.csd.oop.db.cs14;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {
	
	public static void main(String[] args) {/*
        String test = "INSERT INTO table_name (c1,c2,c3) VALUES (abc,def,ghi) , (jkl,mno,pqr)";
        String reg = "((?<=(SELECT\\s\\*\\sFROM\\s))[\\w\\d_]+(?=\\s+))|((?<=(where\\s))([\\w\\d_,]+))";
        String regex = "((?<=(INSERT\\sINTO\\s))[\\w\\d_]+(?=\\s+))|((?<=\\()([\\w\\d_,]+)+(?=\\)))";

        Pattern re = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Pattern r = Pattern.compile(reg, Pattern.CASE_INSENSITIVE);

        final String string = "INSERT INTO table_name (c1,c2,c3) VALUES (abc,def,ghi) , (jkl,mno,pqr)";
        String s = "SELECT   *  from Customers where Country='Mexico'";
		String after = string.trim().replaceAll(" +", " ");
		String after2 = s.trim().replaceAll(" +", " ");

		//final Pattern pattern = Pattern.compile(regex);
		 Matcher m = re.matcher(after);
	        while (m.find()) {
	            System.out.println(m.group(0));
	        }
	      //final Pattern pattern = Pattern.compile(regex);
			 Matcher m2 = r.matcher(after2);
			 int i = 0;
		        while (m2.find()) {
		            System.out.println(m2.group(0));
		            System.out.println(i);
		            i++;
		        }*/
		String query;
		String all = "SELECT * FROM table_name1 WHERE coluMN_NAME2 < 6";
		int count1 = 0, count2 = 0, countAll1 = 0, countAll2 = 0;
		int countAll3 = 0, countAll4 = 0;
		int countAll5 = 0, countAll6 = 0;
		String type = null;

			
		all = all.trim().replaceAll(" +", " ");
		query = all;
		
		if (query.toLowerCase().contains("select from")) {
			type = "error";
			System.out.println("error");
		} else if (query.toLowerCase().contains("select *")||
				query.toLowerCase().contains("select*")) {
			System.out.println("select all");
			type = "select all";
		} else if (query.toLowerCase().contains("select")) {
			System.out.println("select");
			type = "select";
		} else {
			type = "error";
			System.out.println("error");
		}
		
		//for select without condition
        String s = "SELECT Customers from Country";
		String after = s.trim().replaceAll(" +", " ");
		String regexSelect1= "((?<=(SELECT\\s))[\\w\\d_]+(?=\\s+))|((?<=(FROM\\s))[\\w\\d_]+)";
		Pattern patternSelect1 = Pattern.compile(regexSelect1, Pattern.CASE_INSENSITIVE);
		Matcher m1 = patternSelect1.matcher(query);
		while (m1.find()) {
		    count1++;
		}
		//for select witht condition
        String sgjd = "SELECT column_name1 FROM table_name13 WHERE coluMN_NAME2 < 5";
		String aft = sgjd.trim().replaceAll(" +", " ");
		String regexSel= "((?<=(SELECT\\s))[\\w\\d_]+(?=\\s+))|((?<=(FROM\\s))[\\w\\d_]+)"
				+ "|((?<=(where\\s))[\\w\\d=<>\\'\\s]+)";
		Pattern patternSel = Pattern.compile(regexSel, Pattern.CASE_INSENSITIVE);
		Matcher ml1 = patternSel.matcher(aft);
		while (ml1.find()) {
			//System.out.println(ml1.group(0));
		}
		
		//for select all without condition
        String s1 = "SELECT * from Customers";
		String after1 = s1.trim().replaceAll(" +", " ");
		String regexSelectAll1 = "((?<=(SELECT\\s\\*\\sFROM\\s))[\\w\\d_]+)";
		Pattern patternSelectAll1 = Pattern.compile(regexSelectAll1, Pattern.CASE_INSENSITIVE);
		Matcher mAll1 = patternSelectAll1.matcher(query);
		while (mAll1.find()) {
		    countAll1++;
		}
		
		//for select all without condition
        String s2 = "SELECT *from Customers";
		String after2 = s2.trim().replaceAll(" +", " ");
		String regexSelectAll2 = "((?<=(SELECT\\s\\*FROM\\s))[\\w\\d_]+)";
		Pattern patternSelectAll2 = Pattern.compile(regexSelectAll2, Pattern.CASE_INSENSITIVE);
		Matcher mAll2 = patternSelectAll2.matcher(query);
		while (mAll2.find()) {
		    countAll2++;
		}
		
		//for select all without condition
        String s3 = "SELECT* from Customers";
		String after3 = s3.trim().replaceAll(" +", " ");
		String regexSelectAll3 = "((?<=(SELECT\\*\\sFROM\\s))[\\w\\d_]+)";
		Pattern patternSelectAll3 = Pattern.compile(regexSelectAll3, Pattern.CASE_INSENSITIVE);
		Matcher mAll3 = patternSelectAll3.matcher(query);
		while (mAll3.find()) {
		    countAll3++;
		}
		
		//for select with condition
        String s8 = "SELECT Customers from Country WHERE Country='Mexico'";
		String after8 = s8.trim().replaceAll(" +", " ");
		String regexSelect2 = "((?<=(SELECT\\s))[\\w\\d_]+(?=\\s+))|((?<=(FROM\\s))[\\w\\d_]+)"
				+ "|((?<=(where\\s))[\\w\\d=<>\\'\\s]+)";
		Pattern patternSelect2 = Pattern.compile(regexSelect2, Pattern.CASE_INSENSITIVE);
		Matcher m2 = patternSelect2.matcher(after8);
		while (m2.find()) {
		    count2++;
		}
				
		//for select all with condition
        String s11 = "SELECT * from Customers WHERE Country='Mexico'";
		String after11 = s11.trim().replaceAll(" +", " ");
		String regexSelectAll4 = "((?<=(SELECT\\s\\*\\sFROM\\s))[\\w\\d_]+)"
				+ "|((?<=(where\\s))[\\w\\d=<>\\'\\s]+)";
		Pattern patternSelectAll4 = Pattern.compile(regexSelectAll4, Pattern.CASE_INSENSITIVE);
		Matcher mAll4 = patternSelectAll4.matcher(query);
		while (mAll4.find()) {
		    countAll4++;
		}
				
		//for select all without condition
        String s21 = "SELECT *from Customers WHERE Country='Mexico'";
		String after21 = s21.trim().replaceAll(" +", " ");
		String regexSelectAll5 = "((?<=(SELECT\\s\\*FROM\\s))[\\w\\d_]+)"
				+ "|((?<=(where\\s))[\\w\\d=<>\\'\\s]+)";
		Pattern patternSelectAll5 = Pattern.compile(regexSelectAll5, Pattern.CASE_INSENSITIVE);
		Matcher mAll5 = patternSelectAll5.matcher(query);
		while (mAll5.find()) {
		    countAll5++;
		}
				
		//for select all without condition
        String s31 = "SELECT* from Customers WHERE Country='Mexico'";
		String after31 = s31.trim().replaceAll(" +", " ");
		String regexSelectAll6 = "((?<=(SELECT\\*\\sFROM\\s))[\\w\\d_]+)"
				+ "|((?<=(where\\s))[\\w\\d=<>\\'\\s]+)";
		Pattern patternSelectAll6 = Pattern.compile(regexSelectAll6, Pattern.CASE_INSENSITIVE);
		Matcher mAll6 = patternSelectAll6.matcher(query);
		while (mAll6.find()) {
		    countAll6++;
		}
		
		/*
		 * String testdelete2 = "DELETE FROM Customers
		 *  WHERE CustomerName='Alfreds Futterkiste'";
        String regexDelete2 = "((?<=(delete\\sFROM\\s))[\\w\\d_]+)
        |((?<=(where\\s))[\\w\\d=<>\\'\\s]+)";
		if (count >= Math.max(count3, Math.max(count1, count2))) {
			System.out.println("count"+count);
			m = patternSelect.matcher(after);
			while (m.find()) {
				System.out.println(m.group(0));
			}
		} else if (count1 >= Math.max(count3, Math.max(count, count2))) {
			System.out.println("count1"+count1);
			m1 = patternSelect1.matcher(after1);
			while (m1.find()) {
				System.out.println(m1.group(0));
			}
		} else if (count2 >= Math.max(count3, Math.max(count1, count))) {
			System.out.println("count2"+count2);
			m2 = patternSelect2.matcher(after2);
			while (m2.find()) {
				System.out.println(m2.group(0));
			}
		} else if (count3 >= Math.max(count, Math.max(count1, count2))) {
			System.out.println("count3"+count3);
			m3 = patternSelect3.matcher(after3);
			while (m3.find()) {
				System.out.println(m3.group(0));
			}
		}
		*/
		
		int largest = Collections.max(Arrays.asList(count1, count2, countAll1,
				countAll2, countAll3, countAll4, countAll5, countAll6));
		
		 if (count2 == 3 && type.equals("select")) {
			m2 = patternSelect2.matcher(query);
			while (m2.find()) {
				System.out.println(m2.group(0));
			}
			System.out.println("count2");
		} if (count1 == 2 && type.equals("select")) {
			m1 = patternSelect1.matcher(query);
			while (m1.find()) {
				System.out.println(m1.group(0));
			}
			System.out.println("count1");
		} else if (countAll4 == 2 && type.equals("select all")) {
			mAll4 = patternSelectAll4.matcher(query);
			while (mAll4.find()) {
				System.out.println(mAll4.group(0));
			}
			System.out.println("countAll4");
		} else if (countAll5 == 2 && type.equals("select all")) {
			mAll5 = patternSelectAll5.matcher(query);
			while (mAll5.find()) {
				System.out.println(mAll5.group(0));
			}
			System.out.println("countAll5");
		} else if (countAll6 == 2 && type.equals("select all")) {
			mAll6 = patternSelectAll6.matcher(query);
			while (mAll6.find()) {
				System.out.println(mAll6.group(0));
			}
			System.out.println("countAll6");
		} else if (countAll1 == 1 && type.equals("select all")) {
			mAll1 = patternSelectAll1.matcher(query);
			while (mAll1.find()) {
				System.out.println(mAll1.group(0));
			}
			System.out.println("countAll1");
		} else if (countAll2 == 1 && type.equals("select all")) {
			mAll2 = patternSelectAll2.matcher(query);
			while (mAll2.find()) {
				System.out.println(mAll2.group(0));
			}
			System.out.println("countAll2");
		} else if (countAll3 == 1 && type.equals("select all")) {
			mAll3 = patternSelectAll3.matcher(query);
			while (mAll3.find()) {
				System.out.println(mAll3.group(0));
			}
			System.out.println("countAll3");
		} else {
			System.out.println("error");
		}
		System.out.println("end");
		//for create table
        String s4 = "CREATE TABLE table_name1(column_name1 varchar, column_name2 int, column_name3 varchar)";
		String after4 = s4.trim().replaceAll(" +", " ");
		String regexCreate = "((?<=(CREATE TABLE\\s))\\w+)|((?<=\\()([\\w\\s_,]+)+(?=\\)))";
		Pattern patternCreate = Pattern.compile(regexCreate, Pattern.CASE_INSENSITIVE);
		Matcher m4 = patternCreate.matcher(after4);
		System.out.println("vsjkioiov");
		while (m4.find()) {
			System.out.println(m4.group(0));
		}
		
		System.out.println("start");

		//for insert into table
		//"INSERT INTO table_name1(column_NAME1, COLUMN_name3, column_name2) VALUES ('value1', 'value3', 4)"
		String test = "INSERT INTO table_name1(column_NAME1, COLUMN_name3, column_name2) VALUES ('value1', 'value3', 4)";
		char[] stringToCharArray = test.toCharArray();
		 if (BalancedParan.areParenthesisBalanced(stringToCharArray)) 
	            System.out.println("Balanced "); 
	          else
	            System.out.println("Not Balanced ");  
        String regex = "((?<=(INSERT\\sINTO\\s))[\\w\\d_]+)|((?<=\\()([\\w\\d,\\s\\']+)+(?=\\)))";
        Pattern re = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher m5 = re.matcher(test);
        while (m5.find()) {
            System.out.println(m5.group(0));
        }
        
		String[] arra = new String[4];
		int count = 0;
      //for insert into table
      	//"INSERT INTO table_name1(column_NAME1, COLUMN_name3, column_name2) VALUES ('value1', 'value3', 4)"
      		String test2 = "INSERT INTO table_name1(column_NAME1, COLUMN_name3, column_name2) VALUES ('value1', 'value3', 4)";
      		char[] stringToCharArray2 = test2.toCharArray();
      		 if (BalancedParan.areParenthesisBalanced(stringToCharArray2)) 
      	            System.out.println("Balanced "); 
      	          else
      	            System.out.println("Not Balanced ");  
              String regex2 = "((?<=(INSERT\\sINTO\\s))[\\w\\d_]+)|((?<=\\()([\\w\\d,\\s\\']+)+(?=\\)))";
              Pattern re2 = Pattern.compile(regex2, Pattern.CASE_INSENSITIVE);
              Matcher m52 = re2.matcher(test2);
              while (m52.find()) {
					arra[count] = m52.group(0);
                  System.out.println(arra[count]);
                  count++;
               }
					String table_name = arra[0];
	                  System.out.println(table_name);
					int norow = count - 1;
			        HashMap<String, String> hash = new HashMap<>();
			        int i = 0;
			        boolean valid = true;
					while(i < norow) {
						String row[] = arra[i + 1].split(",");
						for (int j = 0; j < 2; j++) {
								//System.out.println("enter");
								if (row[j].contains("'")) {
									row[j] = row[j].replace(" '", "");
									row[j] = row[j].replaceAll("'", "");
					                System.out.println(row[j]);
								} else {
									row[j] = row[j].replaceAll(" +", "");
					                System.out.println(row[j]);
								}
						}
						i++;
					}
        
        //for delete column without condition
        String testdelete1 = "DELETE FROM Customers";
        String regexDelete1 = "((?<=(delete\\sFROM\\s))[\\w\\d_]+)";
        Pattern patternDelete1 = Pattern.compile(regexDelete1, Pattern.CASE_INSENSITIVE);
        Matcher mDelete1 = patternDelete1.matcher(testdelete1);
        while (mDelete1.find()) {
            System.out.println(mDelete1.group(0));
        }
        
      //for delete column with condition
        String testdelete2 = "DELETE FROM Customers WHERE CustomerName='Alfreds Futterkiste'";
        String regexDelete2 = "((?<=(delete\\sFROM\\s))[\\w\\d_]+)|((?<=(where\\s))[\\w\\d=<>\\'\\s]+)";
        Pattern patternDelete2 = Pattern.compile(regexDelete2, Pattern.CASE_INSENSITIVE);
        Matcher mDelete2 = patternDelete2.matcher(testdelete2);
        while (mDelete2.find()) {
            System.out.println(mDelete2.group(0));
        }
        System.out.println("startnew");

      //for update column without condition
        String testupdate1 = "UPDATE wrong_table_name9 SET column_name1='value1', column_name2=15, column_name3='value2'";
        String regexUpdate1 = "((?<=(UPDATE\\s))\\w+)|((?<=SET\\s)([\\w\\s_,=\\'\\d]+)+)";
        Pattern patternUpdate1 = Pattern.compile(regexUpdate1, Pattern.CASE_INSENSITIVE);
        Matcher mUpdate1 = patternUpdate1.matcher(testupdate1);
        while (mUpdate1.find()) {
            System.out.println(mUpdate1.group(0));
        }
        
      //for update column with condition
        String testupdate2 = "UPDATE wrong_table_name9 SET column_name1='value1', column_name2=15, column_name3='value2'";
        String arr[] = testupdate2.split("\\s*(?i)where\\s*", 2);
		String firstWord = arr[0];
        String regexUpdate2 = "((?<=(UPDATE\\s))\\w+)"
        		+ "|((?<=SET\\s)([\\w\\s,=\\'\\d]+))";
        String regexUpdateCon = "((?<=(where\\s))[\\w\\d=<>\\'\\s]+)";
        Pattern patternUpdate2 = Pattern.compile(regexUpdate2, Pattern.CASE_INSENSITIVE);
        Matcher mUpdate2 = patternUpdate2.matcher(firstWord);
        Pattern patternUpdateCon = Pattern.compile(regexUpdateCon, Pattern.CASE_INSENSITIVE);
        Matcher mUpdateCon = patternUpdateCon.matcher(testupdate2);
        while (mUpdate2.find()) {
            System.out.println(mUpdate2.group(0));
        }
        while (mUpdateCon.find()) {
            System.out.println(mUpdateCon.group(0));
        }
        
        //for create database
        String j = "CreATE  testDB";
        String regexCreate1 = "((?<=(CREATE DATABASE\\s))\\w+)";
        Pattern patternCreate1 = Pattern.compile(regexCreate1, Pattern.CASE_INSENSITIVE);
        Matcher mCreate1 = patternCreate1.matcher(j);
        while (mCreate1.find()) {
            System.out.println(mCreate1.group(0));
        }
        
	}

}
