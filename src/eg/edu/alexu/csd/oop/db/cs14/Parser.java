package eg.edu.alexu.csd.oop.db.cs14;

import eg.edu.alexu.csd.oop.db.Command;
import eg.edu.alexu.csd.oop.db.Database;
import eg.edu.alexu.csd.oop.db.IParser;

public class Parser implements IParser{
	
	private Database database;
	private Command command;
	private String query;

	public Parser(Database database) {
		this.database = database;
	 }

	private boolean placeCommands(){
		return command.execute(getInput());	
	}
	
	private String getInput() {		
		return query;		
	}

	public boolean setInput(String query) {
        query = query.trim().replaceAll(" +", " ");
		this.query = query;
		
		String arr[] = query.split(" ", 2);
		String firstWord = arr[0];
		
		if (firstWord.equalsIgnoreCase("select")
				|| firstWord.equalsIgnoreCase("select*")) {
			this.command = new Select(database);
			return placeCommands();
		} else if (firstWord.equalsIgnoreCase("INSERT") 
				|| firstWord.equalsIgnoreCase("UPDATE")
				|| firstWord.equalsIgnoreCase("DELETE")) {
			this.command = new Operation(database);
			return placeCommands();
		} else if (firstWord.equalsIgnoreCase("DROP") 
				|| firstWord.equalsIgnoreCase("CREATE")) {
			this.command = new CreateDrop(database);
			return placeCommands();
		} else {
			Dealwithxml data = Dealwithxml.newInstance();
			data.setValid(false);
			return false;
		}
	}
}
