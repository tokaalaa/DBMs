package eg.edu.alexu.csd.oop.jdbc.cs14;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverPropertyInfo;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import eg.edu.alexu.csd.oop.db.Database;
import eg.edu.alexu.csd.oop.db.cs14.myDataBase;

public class Driver implements java.sql.Driver{
	private Database database;
    private static Logger logger;

	public Driver() {
		 logger = Logger.getLogger(Driver.class.getSimpleName());
		PropertyConfigurator.configure("log4j.properties");
		database = new myDataBase();
	}
		
	@Override
	public boolean acceptsURL(String arg0) throws SQLException {
		// TODO Auto-generated method stub
		int count = 0;
		String serverName = null;

		//String test = "jdbc:xmldb://localhost";
		String regexInsert = "((?<=(jdbc:xmldb://))[\\w\\d_]+)";
		Pattern patternInsert = Pattern.compile(regexInsert, Pattern.CASE_INSENSITIVE);
		Matcher m = patternInsert.matcher(arg0);
		while (m.find()) {
			serverName = m.group(0);
			count++;
		}
		if (count != 1) {
			return false;
		}
		if (!arg0.substring(13,arg0.length()).equalsIgnoreCase(serverName)) {
			return false;
		}

		return true;
	}

	@Override
	public Connection connect(String url, Properties info) throws SQLException {
		// TODO Auto-generated method stub
		String path = "default";
		if (acceptsURL(url)) {
			if (info != null) {
				Set<Entry<Object, Object>> entries = info.entrySet();
			    for (Entry<Object, Object> entry : entries) {
			    	if (((String) entry.getKey()).equalsIgnoreCase("path")){
			    		File f = (File) entry.getValue();
			    		String[] p = f.toString().split("\\\\");
			    		int i = 0;
			    		int j = p.length;// to not enter the second loop if condition doesn't exist
			    		for ( i = 0; i < p.length ; i++) {
			    			if(p[i].equalsIgnoreCase("sample")) {
			    				path = p[i];
			    				j = i;
			    				break;
			    			}
			    		}
			    		for ( i = j + 1; i<p.length ; i++) {
			    			path = path + System.getProperty("file.separator") + p[i];
			    		}
			    		break;//info
			    	}
			    }
			}
            logger.info("you start new connection with path" + path);
			return new connection(database, path, logger);
		} else {
	        logger.warn("the path which you enter is Unacceptable");
			return null;
		}

	}

	@Override
	public int getMajorVersion(){
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public int getMinorVersion() {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public DriverPropertyInfo[] getPropertyInfo(String url, Properties info) throws SQLException {
		// TODO Auto-generated method stub
		DriverPropertyInfo[] arr = new DriverPropertyInfo[info.size()];
		int i = 0;
		for (String key : info.stringPropertyNames()) {	
		arr[i++] = new DriverPropertyInfo(key, info.getProperty(key));}
		return arr;
	}

	@Override
	public boolean jdbcCompliant() {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public java.util.logging.Logger getParentLogger() throws SQLFeatureNotSupportedException {
		throw new java.lang.UnsupportedOperationException();
	}

}
