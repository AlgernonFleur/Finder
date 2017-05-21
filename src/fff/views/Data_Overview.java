package fff.views;

import fff.models.Database;
import fff.models.users.UserAccount;
import javafx.stage.Stage;

public final class Data_Overview {
	
	private static Database database;
	private static UserAccount userAccount;
	private static Stage stage;
	
	private Data_Overview(){
	
	}
	
	public static Database getDatabase() {
		return database;
	}
	
	public static void setDatabase(Database database) {
		if (Data_Overview.database == null)
			Data_Overview.database = database;
		else Data_Overview.throwError();
	}
	
	public static UserAccount getUserAccount() {
		return userAccount;
	}
	
	public static void setUserAccount(UserAccount userAccount) {
		Data_Overview.userAccount = userAccount;
	}
	
	public static Stage getStage() {
		return stage;
	}
	
	public static void setStage(Stage stage) {
		if (Data_Overview.stage == null)
			Data_Overview.stage = stage;
		else Data_Overview.throwError();
	}
	
	private static void throwError(){
		throw new RuntimeException("Variable can only be assigned once");
	}
}
