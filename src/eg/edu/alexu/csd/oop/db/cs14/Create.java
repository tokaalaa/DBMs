package eg.edu.alexu.csd.oop.db.cs14;

import eg.edu.alexu.csd.oop.db.Command;
import eg.edu.alexu.csd.oop.db.Database;

public class Create implements Command{
	
	private Database database;

	public Create(Database database){
		this.database = database;
	}
 
	@Override
	public boolean execute(String query) {
		if (database != null) {
			database.createDatabase(query, true);
			return true;
		}
		return false;		
	}

}
