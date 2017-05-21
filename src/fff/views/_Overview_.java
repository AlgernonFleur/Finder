package fff.views;

import fff.models.Database;
import fff.models.users.UserAccount;
import javafx.stage.Stage;

@SuppressWarnings("WeakerAccess")
public final class _Overview_ {
	
	private static Database database;
	private static UserAccount userAccount;
	private static Stage stage;
	
	private _Overview_(){
	
	}
	
	public static Database getDatabase() {
		return database;
	}
	
	public static void setDatabase(Database database) {
		if (_Overview_.database == null)
			_Overview_.database = database;
		else _Overview_.throwError();
	}
	
	public static UserAccount getUserAccount() {
		return userAccount;
	}
	
	public static void setUserAccount(UserAccount userAccount) {
		_Overview_.userAccount = userAccount;
	}
	
	public static Stage getStage() {
		return stage;
	}
	
	public static void setStage(Stage stage) {
		if (_Overview_.stage == null)
			_Overview_.stage = stage;
		else _Overview_.throwError();
	}
	
	private static void throwError(){
		throw new RuntimeException("Variable can only be assigned once");
	}
}
