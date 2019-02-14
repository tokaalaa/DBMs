package eg.edu.alexu.csd.oop.jdbc.cs14;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Array;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Date;
import java.sql.NClob;
import java.sql.Ref;
import java.sql.ResultSetMetaData;
import java.sql.RowId;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Map;
import org.apache.log4j.Logger;

import eg.edu.alexu.csd.oop.db.Database;

public class resultset implements java.sql.ResultSet {
	private Object[][] resultset;
	private int row;
	private Statement currentStatement;
	private Database database;
	private String path;
	private Logger logger;

	public resultset(Object[][] o , java.sql.Statement s, Database db, String path, org.apache.log4j.Logger logger) {
		// TODO Auto-generated constructor stub
		row = -1;
		resultset = o;
		currentStatement = s;
		 database = db;
		 this.path = path;
		 this.logger = logger;
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
	public boolean absolute(int i) throws SQLException {
		// TODO Auto-generated method stub
		if (path != null) {
		if (i > 0 && i < resultset.length && resultset.length > 0) {
			row = i - 1;
	        logger.info("You moved the row pointer to row number " + row);
			return true;
		}
        logger.info("You can't move the row pointer to row number " + row);
		return false;
		} else {
	        logger.warn("There is SQLException", new SQLException());
			throw new SQLException();
		}
	}

	@Override
	public void afterLast() throws SQLException {
		// TODO Auto-generated method stub
		if (path != null) {
			row = resultset.length;
	        logger.info("You moved the row pointer to row number " + row);
		} else {
	        logger.warn("There is SQLException", new SQLException());
			throw new SQLException();
		}
	}

	@Override
	public void beforeFirst() throws SQLException {
		// TODO Auto-generated method stub
		if (path != null) {
			row = -1;
	        logger.info("You moved the row pointer to row number " + row);
		} else {
	        logger.warn("There is SQLException", new SQLException());
			throw new SQLException();
		}
	}

	@Override
	public void cancelRowUpdates() throws SQLException {
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
		path = null;
        logger.info("you close your resultset");
	}

	@Override
	public void deleteRow() throws SQLException {
		throw new java.lang.UnsupportedOperationException();
		
	}

	@Override
	public int findColumn(String arg0) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public boolean first() throws SQLException {
		// TODO Auto-generated method stub
		if (path != null) {
		if (resultset.length > 0) {
			row = 0;
	        logger.info("You moved the row pointer to row number " + row);
			return true;
		} else {
	        logger.info("You can't move the row pointer to row number 0");
			return false;	
		}
		} else {
	        logger.warn("There is SQLException", new SQLException());
			throw new SQLException();
		}
	}

	@Override
	public Array getArray(int arg0) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public Array getArray(String arg0) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public InputStream getAsciiStream(int arg0) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public InputStream getAsciiStream(String arg0) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public BigDecimal getBigDecimal(int arg0) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public BigDecimal getBigDecimal(String arg0) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public BigDecimal getBigDecimal(int arg0, int arg1) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public BigDecimal getBigDecimal(String arg0, int arg1) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public InputStream getBinaryStream(int arg0) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public InputStream getBinaryStream(String arg0) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public Blob getBlob(int arg0) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public Blob getBlob(String arg0) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public boolean getBoolean(int arg0) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public boolean getBoolean(String arg0) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public byte getByte(int arg0) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public byte getByte(String arg0) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public byte[] getBytes(int arg0) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public byte[] getBytes(String arg0) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public Reader getCharacterStream(int arg0) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public Reader getCharacterStream(String arg0) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public Clob getClob(int arg0) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public Clob getClob(String arg0) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public int getConcurrency() throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public String getCursorName() throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public Date getDate(int arg0) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public Date getDate(String arg0) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public Date getDate(int arg0, Calendar arg1) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public Date getDate(String arg0, Calendar arg1) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public double getDouble(int arg0) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public double getDouble(String arg0) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
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
	public float getFloat(int arg0) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public float getFloat(String arg0) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public int getHoldability() throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public int getInt(int arg0) throws SQLException {
		// TODO Auto-generated method stub
		if (path != null) {
		if (resultset.length > 0 && resultset[0].length >= arg0) {
			try {
				int i = (int)resultset[row][arg0 - 1];
				logger.info("the value of the designated column with columnIndex " + arg0 + 
						" in row " + row +" is " + i);
				return i;
			}catch(NumberFormatException nfe)  {
		        logger.warn("There is SQLException", new SQLException());
				throw new SQLException();
			}
		}
        logger.warn("There is SQLException", new SQLException());
		throw new SQLException();
		} else {
	        logger.warn("There is SQLException", new SQLException());
			throw new SQLException();
		}
		
	}

	@Override
	public int getInt(String arg0) throws SQLException {
		// TODO Auto-generated method stub
		if (path != null) {
		String[][] names = database.columnName();
		for (int j = 0; j <  names.length; j++) {
			if (names[j][0].equalsIgnoreCase(arg0)) {
				logger.info("the value of the designated column with column_name " + arg0 + 
						" in row " + row +" is " + (j + 1));
				return (j + 1);
			}
		}
        logger.warn("There is SQLException", new SQLException());
		throw new SQLException();
		} else {
	        logger.warn("There is SQLException", new SQLException());
			throw new SQLException();
		}
	}

	@Override
	public long getLong(int arg0) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public long getLong(String arg0) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public ResultSetMetaData getMetaData() throws SQLException {
		// TODO Auto-generated method stub
		if (path != null) {
	        logger.info("you start new resultSetMetaData");
	        ResultSetMetaData resultSMD = new resultSetMetaData(resultset, row, database);
	        return resultSMD;
		} else {
	        logger.warn("There is SQLException", new SQLException());
			throw new SQLException();
		}
	}

	@Override
	public Reader getNCharacterStream(int arg0) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public Reader getNCharacterStream(String arg0) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public NClob getNClob(int arg0) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public NClob getNClob(String arg0) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public String getNString(int arg0) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public String getNString(String arg0) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public Object getObject(int arg0) throws SQLException {
		// TODO Auto-generated method stub
		if (path != null) {
		if (resultset.length > 0 && resultset[0].length >= arg0) {
			logger.info("the value of the designated column with columnIndex " + arg0 + 
					" in row " + row +" is " + resultset[row][arg0-1]);
			return resultset[row][arg0-1];
		}
        logger.warn("There is SQLException", new SQLException());
		throw new SQLException();
		} else {
	        logger.warn("There is SQLException", new SQLException());
			throw new SQLException();
		}
	}

	@Override
	public Object getObject(String arg0) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public Object getObject(int arg0, Map<String, Class<?>> arg1) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public Object getObject(String arg0, Map<String, Class<?>> arg1) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public <T> T getObject(int arg0, Class<T> arg1) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public <T> T getObject(String arg0, Class<T> arg1) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public Ref getRef(int arg0) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public Ref getRef(String arg0) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public int getRow() throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public RowId getRowId(int arg0) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public RowId getRowId(String arg0) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public SQLXML getSQLXML(int arg0) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public SQLXML getSQLXML(String arg0) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public short getShort(int arg0) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public short getShort(String arg0) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public Statement getStatement() throws SQLException {
		// TODO Auto-generated method stub
        logger.info("the statement of this resultset is " + currentStatement);
		return currentStatement;
	}

	@Override
	public String getString(int arg0) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public String getString(String arg0) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public Time getTime(int arg0) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public Time getTime(String arg0) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public Time getTime(int arg0, Calendar arg1) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public Time getTime(String arg0, Calendar arg1) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public Timestamp getTimestamp(int arg0) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public Timestamp getTimestamp(String arg0) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public Timestamp getTimestamp(int arg0, Calendar arg1) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public Timestamp getTimestamp(String arg0, Calendar arg1) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public int getType() throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public URL getURL(int arg0) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public URL getURL(String arg0) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public InputStream getUnicodeStream(int arg0) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public InputStream getUnicodeStream(String arg0) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public SQLWarning getWarnings() throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public void insertRow() throws SQLException {
		throw new java.lang.UnsupportedOperationException();
		
	}

	@Override
	public boolean isAfterLast() throws SQLException {
		// TODO Auto-generated method stub
		boolean check;
		if (row == resultset.length) {
			check = true;
	        logger.info(" the cursor is after the last row");
		} else {
			check = false;
	        logger.info(" the cursor isn't after the last row");
		}
		return check;
	}

	@Override
	public boolean isBeforeFirst() throws SQLException {
		// TODO Auto-generated method stub
		boolean check;
		if (row == -1) {
			check = true;
	        logger.info(" the cursor is after the last row");
		} else {
			check = false;
	        logger.info(" the cursor isn't after the last row");
		}
		return check;
	}

	@Override
	public boolean isClosed() throws SQLException {
		// TODO Auto-generated method stub
		if (path != null) {
	        logger.info("your statement isn't closed");
			return false;
		}
        logger.info("you already closed your statement");
		return true;
	}

	@Override
	public boolean isFirst() throws SQLException {
		// TODO Auto-generated method stub
		if (path != null) {
		if (resultset.length > 0) {
			boolean check;
			if (row == 0) {
				check = true;
		        logger.info(" the cursor is on the first row");
			} else {
				check = false;
		        logger.info(" the cursor isn't on the first row");
			}
			return check;
		} else {
	        logger.info(" the cursor isn't on the first row");
			return false;
		}
		} else {
	        logger.warn("There is SQLException", new SQLException());
			throw new SQLException();
		}
	}

	@Override
	public boolean isLast() throws SQLException {
		// TODO Auto-generated method stub
		if (path != null) {
		if (resultset.length > 0) {
			boolean check;
			if (row ==  resultset.length - 1) {
				check = true;
		        logger.info(" the cursor is on the last row");
			} else {
				check = false;
		        logger.info(" the cursor isn't on the last row");
			}
			return check;
		} else {
	        logger.info(" the cursor isn't on the last row");
			return false;
		}
		} else {
	        logger.warn("There is SQLException", new SQLException());
			throw new SQLException();
		}
	}

	@Override
	public boolean last() throws SQLException {
		// TODO Auto-generated method stub
		//Moves the cursor to the last row
		if (path != null) {
		if (resultset.length > 0) {
			row = resultset.length - 1;
	        logger.info("you moves the cursor to the last row");
			return true;
		}
        logger.info("you can't move the cursor to the last row");
		return false;
		} else {
	        logger.warn("There is SQLException", new SQLException());
			throw new SQLException();
		}
	}

	@Override
	public void moveToCurrentRow() throws SQLException {
		throw new java.lang.UnsupportedOperationException();
		
	}

	@Override
	public void moveToInsertRow() throws SQLException {
		throw new java.lang.UnsupportedOperationException();
		
	}

	@Override
	public boolean next() throws SQLException {
		// TODO Auto-generated method stub
		if (path != null) {
		if (row < (resultset.length - 1) && resultset.length > 0) {
			row++;
	        logger.info("you moves the cursor to the next row and row be " + row);
			return true;
		}
        logger.info("you can't move the cursor to the next row because this row doesn't have next");
		return false;
		} else {
	        logger.warn("There is SQLException", new SQLException());
			throw new SQLException();
		}
	}

	@Override
	public boolean previous() throws SQLException {
		// TODO Auto-generated method stub
		if (path != null) {
		if (row >= 0 && resultset.length > 0) {
			row--;
	        logger.info("you moves the cursor to the previous row and row be " + row);
			return true;
		}
        logger.info("you can't move the cursor to the next row because this row doesn't have previous");
		return false;
		} else {
	        logger.warn("There is SQLException", new SQLException());
			throw new SQLException();
		}
	}

	@Override
	public void refreshRow() throws SQLException {
		throw new java.lang.UnsupportedOperationException();
		
	}

	@Override
	public boolean relative(int arg0) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public boolean rowDeleted() throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public boolean rowInserted() throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public boolean rowUpdated() throws SQLException {
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
	public void updateArray(int arg0, Array arg1) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
		
	}

	@Override
	public void updateArray(String arg0, Array arg1) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
		
	}

	@Override
	public void updateAsciiStream(int arg0, InputStream arg1) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
		
	}

	@Override
	public void updateAsciiStream(String arg0, InputStream arg1) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
		
	}

	@Override
	public void updateAsciiStream(int arg0, InputStream arg1, int arg2) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
		
	}

	@Override
	public void updateAsciiStream(String arg0, InputStream arg1, int arg2) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
		
	}

	@Override
	public void updateAsciiStream(int arg0, InputStream arg1, long arg2) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
		
	}

	@Override
	public void updateAsciiStream(String arg0, InputStream arg1, long arg2) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
		
	}

	@Override
	public void updateBigDecimal(int arg0, BigDecimal arg1) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
		
	}

	@Override
	public void updateBigDecimal(String arg0, BigDecimal arg1) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
		
	}

	@Override
	public void updateBinaryStream(int arg0, InputStream arg1) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
		
	}

	@Override
	public void updateBinaryStream(String arg0, InputStream arg1) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
		
	}

	@Override
	public void updateBinaryStream(int arg0, InputStream arg1, int arg2) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
		
	}

	@Override
	public void updateBinaryStream(String arg0, InputStream arg1, int arg2) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
		
	}

	@Override
	public void updateBinaryStream(int arg0, InputStream arg1, long arg2) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
		
	}

	@Override
	public void updateBinaryStream(String arg0, InputStream arg1, long arg2) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
		
	}

	@Override
	public void updateBlob(int arg0, Blob arg1) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
		
	}

	@Override
	public void updateBlob(String arg0, Blob arg1) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
		
	}

	@Override
	public void updateBlob(int arg0, InputStream arg1) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
		
	}

	@Override
	public void updateBlob(String arg0, InputStream arg1) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
		
	}

	@Override
	public void updateBlob(int arg0, InputStream arg1, long arg2) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
		
	}

	@Override
	public void updateBlob(String arg0, InputStream arg1, long arg2) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
		
	}

	@Override
	public void updateBoolean(int arg0, boolean arg1) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
		
	}

	@Override
	public void updateBoolean(String arg0, boolean arg1) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
		
	}

	@Override
	public void updateByte(int arg0, byte arg1) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
		
	}

	@Override
	public void updateByte(String arg0, byte arg1) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
		
	}

	@Override
	public void updateBytes(int arg0, byte[] arg1) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
		
	}

	@Override
	public void updateBytes(String arg0, byte[] arg1) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
		
	}

	@Override
	public void updateCharacterStream(int arg0, Reader arg1) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
		
	}

	@Override
	public void updateCharacterStream(String arg0, Reader arg1) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
		
	}

	@Override
	public void updateCharacterStream(int arg0, Reader arg1, int arg2) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
		
	}

	@Override
	public void updateCharacterStream(String arg0, Reader arg1, int arg2) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
		
	}

	@Override
	public void updateCharacterStream(int arg0, Reader arg1, long arg2) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
		
	}

	@Override
	public void updateCharacterStream(String arg0, Reader arg1, long arg2) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
		
	}

	@Override
	public void updateClob(int arg0, Clob arg1) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
		
	}

	@Override
	public void updateClob(String arg0, Clob arg1) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
		
	}

	@Override
	public void updateClob(int arg0, Reader arg1) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
		
	}

	@Override
	public void updateClob(String arg0, Reader arg1) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
		
	}

	@Override
	public void updateClob(int arg0, Reader arg1, long arg2) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
		
	}

	@Override
	public void updateClob(String arg0, Reader arg1, long arg2) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
		
	}

	@Override
	public void updateDate(int arg0, Date arg1) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
		
	}

	@Override
	public void updateDate(String arg0, Date arg1) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
		
	}

	@Override
	public void updateDouble(int arg0, double arg1) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
		
	}

	@Override
	public void updateDouble(String arg0, double arg1) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
		
	}

	@Override
	public void updateFloat(int arg0, float arg1) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
		
	}

	@Override
	public void updateFloat(String arg0, float arg1) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
		
	}

	@Override
	public void updateInt(int arg0, int arg1) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
		
	}

	@Override
	public void updateInt(String arg0, int arg1) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
		
	}

	@Override
	public void updateLong(int arg0, long arg1) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
		
	}

	@Override
	public void updateLong(String arg0, long arg1) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
		
	}

	@Override
	public void updateNCharacterStream(int arg0, Reader arg1) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
		
	}

	@Override
	public void updateNCharacterStream(String arg0, Reader arg1) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
		
	}

	@Override
	public void updateNCharacterStream(int arg0, Reader arg1, long arg2) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
		
	}

	@Override
	public void updateNCharacterStream(String arg0, Reader arg1, long arg2) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
		
	}

	@Override
	public void updateNClob(int arg0, NClob arg1) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
		
	}

	@Override
	public void updateNClob(String arg0, NClob arg1) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
		
	}

	@Override
	public void updateNClob(int arg0, Reader arg1) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
		
	}

	@Override
	public void updateNClob(String arg0, Reader arg1) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
		
	}

	@Override
	public void updateNClob(int arg0, Reader arg1, long arg2) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
		
	}

	@Override
	public void updateNClob(String arg0, Reader arg1, long arg2) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
		
	}

	@Override
	public void updateNString(int arg0, String arg1) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
		
	}

	@Override
	public void updateNString(String arg0, String arg1) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
		
	}

	@Override
	public void updateNull(int arg0) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
		
	}

	@Override
	public void updateNull(String arg0) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
		
	}

	@Override
	public void updateObject(int arg0, Object arg1) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
		
	}

	@Override
	public void updateObject(String arg0, Object arg1) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
		
	}

	@Override
	public void updateObject(int arg0, Object arg1, int arg2) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
		
	}

	@Override
	public void updateObject(String arg0, Object arg1, int arg2) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
		
	}

	@Override
	public void updateRef(int arg0, Ref arg1) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
		
	}

	@Override
	public void updateRef(String arg0, Ref arg1) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
		
	}

	@Override
	public void updateRow() throws SQLException {
		throw new java.lang.UnsupportedOperationException();
		
	}

	@Override
	public void updateRowId(int arg0, RowId arg1) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
		
	}

	@Override
	public void updateRowId(String arg0, RowId arg1) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
		
	}

	@Override
	public void updateSQLXML(int arg0, SQLXML arg1) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
		
	}

	@Override
	public void updateSQLXML(String arg0, SQLXML arg1) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
		
	}

	@Override
	public void updateShort(int arg0, short arg1) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
		
	}

	@Override
	public void updateShort(String arg0, short arg1) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
		
	}

	@Override
	public void updateString(int arg0, String arg1) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
		
	}

	@Override
	public void updateString(String arg0, String arg1) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
		
	}

	@Override
	public void updateTime(int arg0, Time arg1) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
		
	}

	@Override
	public void updateTime(String arg0, Time arg1) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
		
	}

	@Override
	public void updateTimestamp(int arg0, Timestamp arg1) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
		
	}

	@Override
	public void updateTimestamp(String arg0, Timestamp arg1) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
		
	}

	@Override
	public boolean wasNull() throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

}
