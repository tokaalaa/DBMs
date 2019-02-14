package eg.edu.alexu.csd.oop.jdbc.cs14;

import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.util.ArrayList;
import org.apache.log4j.Logger;
import eg.edu.alexu.csd.oop.db.Database;

public class statement implements java.sql.Statement {
private int timeLimit;
private ArrayList<String> batches;
private Connection currentConnection;
private Database database;
private String path;
private Logger logger;
private int count;

	public statement(java.sql.Connection connection, Database db, String path, org.apache.log4j.Logger logger2) {
		currentConnection = connection;
		count = 0;
		batches = new ArrayList<String>();
		database = db;
		this.path = path;
		 this.logger = logger2;
	} 
	@Override
	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public <T> T unwrap(Class<T> iface) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public void addBatch(String arg0) throws SQLException {
		// TODO Auto-generated method stub
		if (path != null) {
	        logger.info("you add this query(" + arg0 + ") in Batch");
			batches.add(arg0);
		} else {
	        logger.warn("There is SQLException", new SQLException());
			throw new SQLException();
		}
	}

	@Override
	public void cancel() throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public void clearBatch() throws SQLException {
		// TODO Auto-generated method stub
		if (path != null) {
			 logger.info("you clear Batch");
			batches.clear();
		} else {
	        logger.warn("There is SQLException", new SQLException());
			throw new SQLException();
		}
	}

	@Override
	public void clearWarnings() throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public void close() throws SQLException {
		// TODO Auto-generated method stub
		//DbUtils.closeQuietly(this);
        logger.info("you close your statement");
		path = null;
	}

	@Override
	public void closeOnCompletion() throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public boolean execute(String arg0) throws SQLException {
		// TODO Auto-generated method stub
		if (path != null) {
		arg0 = arg0.replaceAll("\\s{2,}", " ").trim();
		String[] query = arg0.split(" ");
		String firstWord = query[0];
		String secondWord;
		boolean retur;
		if (query.length >= 3) {
		secondWord = query[1];
		}else secondWord = "";
		if (firstWord.equalsIgnoreCase("drop") && secondWord.equalsIgnoreCase("database")) {
			String s = exist(query[2]);
			if (s.equals("*not found*")) {
				logger.info("you enter query: " + arg0 + " and it isn't created");
				count = EXECUTE_FAILED;
				return false;
			} else {
				String newQ = firstWord + " " + secondWord + " " +
						path + System.getProperty("file.separator") + s;
						retur = database.executeStructureQuery(newQ);
						if (retur) {
							count = 1;
							logger.info("you enter query: " + arg0 + " and it was already created");
						} else {
							count = EXECUTE_FAILED;
							logger.info("you enter query: " + arg0 + " and it isn't created");
						}
						return retur;	
			}

		}else if ((firstWord.equalsIgnoreCase("create"))
				&& secondWord.equalsIgnoreCase("database")) {
			String newQ = firstWord + " " + secondWord + " " +
			path + System.getProperty("file.separator") + query[2];
			retur = database.executeStructureQuery(newQ);
			if (retur) {
				logger.info("you enter query: " + arg0 + " and it was already created");
				count = 1;
			} else {
				logger.info("you enter query: " + arg0 + " and it isn't created");
				count = EXECUTE_FAILED;
			}
			return retur;
		} else if (firstWord.equalsIgnoreCase("create") || 
			firstWord.equalsIgnoreCase("drop")) {
			retur = database.executeStructureQuery(arg0);
			if (retur) {
				count = 1;
				logger.info("you enter query: " + arg0 + " and it was already "+ firstWord);
			} else {
				count = EXECUTE_FAILED;
				logger.info("you enter query: " + arg0 + " and it isn't "+ firstWord);
			}
			return retur;
		} else if (firstWord.equalsIgnoreCase("update") || 
				firstWord.equalsIgnoreCase("insert") || 
				firstWord.equalsIgnoreCase("delete")) {
			if ((count = database.executeUpdateQuery(arg0)) == -1 ) {
				logger.info("you enter query: " + arg0 + " and it isn't "+ firstWord);
				count = EXECUTE_FAILED;
				return false;
				
			} else {
				logger.info("you enter query: " + arg0 + " and it was already " + firstWord);
				return true;
			}
			
		} else if (firstWord.equalsIgnoreCase("select")) {
			Object[][] result = database.executeQuery(arg0);
			if (result.length > 0) {
				count = 1;
				logger.info("you enter query: " + arg0 + " and it was already " + firstWord +
						"\n and number of row = " + result.length);
				return true;
			} else {
				count = EXECUTE_FAILED;
				logger.info("you enter query: " + arg0 + " and it isn't "+ firstWord);
				return false;
			}
		} else {
			count = EXECUTE_FAILED;
	        logger.error("you enter invalid query");
			return false;
		}
		} else {
			count = EXECUTE_FAILED;
	        logger.warn("There is SQLException", new SQLException());
			throw new SQLException();
		}
	}

	private String exist(String string) {
		// TODO Auto-generated method stub
		String s = "*not found*";
		File folder = new File("main folder" + System.getProperty("file.separator") +
				path);
		File[] listOfFiles = folder.listFiles();
		if (listOfFiles != null) {
		for (int i = 0; i < listOfFiles.length; i++) {
		  if (listOfFiles[i].isDirectory()) {
			 if (listOfFiles[i].getName().equalsIgnoreCase(string)) {
				 s = listOfFiles[i].getName();
				 return s;
			 }
		  }
		  }
		}
		return s;
	}
	@Override
	public boolean execute(String arg0, int arg1) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public boolean execute(String arg0, int[] arg1) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public boolean execute(String arg0, String[] arg1) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public int[] executeBatch() throws SQLException {
		// TODO Auto-generated method stub
		if (path != null) {
		int[] count = new int[batches.size()];
		int i = 0;
		for (String batch: batches) {
			logger.info("you do this query(" + batch + ") from Batch");
			execute(batch);
			count[i++] = this.count;
		}
		return count;
		} else {
	        logger.warn("There is SQLException", new SQLException());
			throw new SQLException();
		}
	}

	@Override
	public ResultSet executeQuery(String arg0) throws SQLException {
		// TODO Auto-generated method stub
		if (path != null) {
		Object[][] o = database.executeQuery(arg0);
        logger.info("you start new resultSet");
		ResultSet r = new resultset(o, this, database, path, logger);
		return r;
		} else {
	        logger.warn("There is SQLException", new SQLException());
			throw new SQLException();
		}
	}

	@Override
	public int executeUpdate(String arg0) throws SQLException {
		// TODO Auto-generated method stub
		if (path != null) {
			logger.info("you enter query: " + arg0);
			return database.executeUpdateQuery(arg0);
		} else {
	        logger.warn("There is SQLException", new SQLException());
			throw new SQLException();
		}
	}

	@Override
	public int executeUpdate(String arg0, int arg1) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public int executeUpdate(String arg0, int[] arg1) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public int executeUpdate(String arg0, String[] arg1) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public Connection getConnection() throws SQLException {
		// TODO Auto-generated method stub
        logger.info("the Connection of this statement is " + currentConnection);
		return currentConnection;
	}

	@Override
	public int getFetchDirection() throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public int getFetchSize() throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public ResultSet getGeneratedKeys() throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public int getMaxFieldSize() throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public int getMaxRows() throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public boolean getMoreResults() throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public boolean getMoreResults(int arg0) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public int getQueryTimeout() throws SQLException {
		// TODO Auto-generated method stub
		if (path != null) {
	        logger.info("the timeLimit of this statement is " + timeLimit);
			return timeLimit;
		} else {
	        logger.warn("There is SQLException", new SQLException());
			throw new SQLException();
		}
	}

	@Override
	public ResultSet getResultSet() throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public int getResultSetConcurrency() throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public int getResultSetHoldability() throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public int getResultSetType() throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public int getUpdateCount() throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public SQLWarning getWarnings() throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public boolean isCloseOnCompletion() throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public boolean isClosed() throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public boolean isPoolable() throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public void setCursorName(String arg0) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
		
	}

	@Override
	public void setEscapeProcessing(boolean arg0) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
		
	}

	@Override
	public void setFetchDirection(int arg0) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
		
	}

	@Override
	public void setFetchSize(int arg0) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
		
	}

	@Override
	public void setMaxFieldSize(int arg0) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
		
	}

	@Override
	public void setMaxRows(int arg0) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
		
	}

	@Override
	public void setPoolable(boolean arg0) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
		
	}

	@Override
	public void setQueryTimeout(int arg0) throws SQLException {
		// TODO Auto-generated method stub
		if (path != null) {
			timeLimit = arg0;
	        logger.info("you set timeLimit of this statement to be " + timeLimit);
		} else {
	        logger.warn("There is SQLException", new SQLException());
			throw new SQLException();
		}
	}

}
