package eg.edu.alexu.csd.oop.db;

import java.util.HashMap;

public interface DealWithXml {
public int insert(String tableName, HashMap<String, String> newRow);
public int update(String tableName, String columnCondition, String valueCondition, HashMap<String, String> updates, String operation, boolean checkString);
public Object[][] select(String tableName, String columnName, String valueCondition, String columnCondition, String operation, boolean checkString);
public int delete(String tableName, String columnCondition, String valueCondition, String operation, boolean checkString);
public boolean getValid();
public void setValid(boolean state);

}
