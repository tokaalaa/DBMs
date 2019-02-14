package eg.edu.alexu.csd.oop.jdbc.cs14;

import java.sql.Array;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.DatabaseMetaData;
import java.sql.NClob;
import java.sql.PreparedStatement;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Savepoint;
import java.sql.Statement;
import java.sql.Struct;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;
import org.apache.log4j.Logger;
import eg.edu.alexu.csd.oop.db.Database;

public class connection implements java.sql.Connection{
	private Database database;
	private String path;
	private Logger logger;
	
	public connection(Database db, String path, org.apache.log4j.Logger logger2) {
		 database = db;
		 this.path = path;
		 this.logger = logger2;
	}

	@Override
	public boolean isWrapperFor(Class<?> arg0) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public <T> T unwrap(Class<T> arg0) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public void abort(Executor arg0) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public void clearWarnings() throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public void close() throws SQLException {
		// TODO Auto-generated method stub
		//DbUtils.closeQuietly(this);
        logger.info("you close your connection");
		path = null;
	}

	@Override
	public void commit() throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public Array createArrayOf(String arg0, Object[] arg1) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public Blob createBlob() throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public Clob createClob() throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public NClob createNClob() throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public SQLXML createSQLXML() throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public Statement createStatement() throws SQLException {
		// TODO Auto-generated method stub
		if (path != null) {
            logger.info("you start new statement");
			return new statement(this, database, path, logger);
		} else {
	        logger.warn("There is SQLException" , new SQLException());
			throw new SQLException();
		}
	}

	@Override
	public Statement createStatement(int arg0, int arg1) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public Statement createStatement(int arg0, int arg1, int arg2) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public Struct createStruct(String arg0, Object[] arg1) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public boolean getAutoCommit() throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public String getCatalog() throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public Properties getClientInfo() throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public String getClientInfo(String arg0) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public int getHoldability() throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public DatabaseMetaData getMetaData() throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public int getNetworkTimeout() throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public String getSchema() throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public int getTransactionIsolation() throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public Map<String, Class<?>> getTypeMap() throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public SQLWarning getWarnings() throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public boolean isClosed() throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public boolean isReadOnly() throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public boolean isValid(int arg0) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public String nativeSQL(String arg0) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public CallableStatement prepareCall(String arg0) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public CallableStatement prepareCall(String arg0, int arg1, int arg2) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public CallableStatement prepareCall(String arg0, int arg1, int arg2, int arg3) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public PreparedStatement prepareStatement(String arg0) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public PreparedStatement prepareStatement(String arg0, int arg1) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public PreparedStatement prepareStatement(String arg0, int[] arg1) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public PreparedStatement prepareStatement(String arg0, String[] arg1) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public PreparedStatement prepareStatement(String arg0, int arg1, int arg2) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public PreparedStatement prepareStatement(String arg0, int arg1, int arg2, int arg3) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public void releaseSavepoint(Savepoint arg0) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public void rollback() throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public void rollback(Savepoint arg0) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public void setAutoCommit(boolean arg0) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public void setCatalog(String arg0) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public void setClientInfo(Properties arg0) throws SQLClientInfoException {
		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public void setClientInfo(String arg0, String arg1) throws SQLClientInfoException {
		throw new java.lang.UnsupportedOperationException();

	}

	@Override
	public void setHoldability(int arg0) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public void setNetworkTimeout(Executor arg0, int arg1) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public void setReadOnly(boolean arg0) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public Savepoint setSavepoint() throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public Savepoint setSavepoint(String arg0) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public void setSchema(String arg0) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public void setTransactionIsolation(int arg0) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public void setTypeMap(Map<String, Class<?>> arg0) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

}
