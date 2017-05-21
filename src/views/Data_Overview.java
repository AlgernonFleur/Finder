package views;

import models.Database;
import models.users.UserAccount;

public final class Data_Overview {
	
	private static Database database;
	private static UserAccount userAccount;
	
	private Data_Overview(){
	
	}
	
	public static Database getDatabase() {
		return database;
	}
	
	public static void setDatabase(Database database) {
		Data_Overview.database = database;
	}
	
	public static UserAccount getUserAccount() {
		return userAccount;
	}
	
	public static void setUserAccount(UserAccount userAccount) {
		Data_Overview.userAccount = userAccount;
	}
}
