package eg.edu.alexu.csd.oop.jdbc.cs14;

import java.sql.SQLException;

import eg.edu.alexu.csd.oop.db.Database;

public class resultSetMetaData implements java.sql.ResultSetMetaData{
	private Object[][] resultset;
	private int row;
	private Database database;
	private String[][] nameColumn;

	public resultSetMetaData(Object[][] o, int row, Database db) {
		this.resultset = o;
		this.row = row;
		 database = db;
		 nameColumn = database.columnName();

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
	public String getCatalogName(int arg0) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public String getColumnClassName(int arg0) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public int getColumnCount() throws SQLException {
		// TODO Auto-generated method stub
		return resultset[row].length;
	}

	@Override
	public int getColumnDisplaySize(int arg0) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public String getColumnLabel(int arg0) throws SQLException {
		// TODO Auto-generated method stub
		if (arg0 <= nameColumn.length) {
			return nameColumn[arg0 - 1][0];
		} else {
			throw new SQLException();
		}
	}

	@Override
	public String getColumnName(int arg0) throws SQLException {
		// TODO Auto-generated method stub
		if (arg0 <= nameColumn.length) {
		return nameColumn[arg0 - 1][0];
		} else {
			throw new SQLException();
		}
	}

	@Override
	public int getColumnType(int arg0) throws SQLException {
		// TODO Auto-generated method stub
		if (arg0 <= nameColumn.length) {
			if(nameColumn[arg0 - 1][1].equals("int")) {
				return java.sql.Types.INTEGER;
			} else {
				return java.sql.Types.VARCHAR;
			}	 
		} else {
			throw new SQLException();
		}
	}

	@Override
	public String getColumnTypeName(int arg0) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public int getPrecision(int arg0) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public int getScale(int arg0) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public String getSchemaName(int arg0) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public String getTableName(int arg0) throws SQLException {
		// TODO Auto-generated method stub
		return database.getTableName();
	}

	@Override
	public boolean isAutoIncrement(int arg0) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public boolean isCaseSensitive(int arg0) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public boolean isCurrency(int arg0) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public boolean isDefinitelyWritable(int arg0) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public int isNullable(int arg0) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public boolean isReadOnly(int arg0) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public boolean isSearchable(int arg0) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public boolean isSigned(int arg0) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public boolean isWritable(int arg0) throws SQLException {
		throw new java.lang.UnsupportedOperationException();
	}

}
