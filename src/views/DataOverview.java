package views;

import models.Database;
import models.users.UserAccount;

public final class DataOverview {
	
	private static Database database;
	private static UserAccount userAccount;
	
	private DataOverview(){
	
	}
	
	public static Database getDatabase() {
		return database;
	}
	
	public static void setDatabase(Database database) {
		DataOverview.database = database;
	}
	
	public static UserAccount getUserAccount() {
		return userAccount;
	}
	
	public static void setUserAccount(UserAccount userAccount) {
		DataOverview.userAccount = userAccount;
	}
}
