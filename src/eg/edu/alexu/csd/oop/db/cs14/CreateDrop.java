package eg.edu.alexu.csd.oop.db.cs14;

import java.sql.SQLException;

import eg.edu.alexu.csd.oop.db.Command;
import eg.edu.alexu.csd.oop.db.Database;

public class CreateDrop implements Command{
	
	private Database database;

	public CreateDrop(Database database){
		this.database = database;
	}
  
	@Override
	public boolean execute(String query) {
		if (database != null) {
			try {
				return database.executeStructureQuery(query);
				//System.out.println(database.executeStructureQuery(query));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}

}
