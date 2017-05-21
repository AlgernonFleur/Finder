package views;

import models.Database;
import models.users.UserAccount;

public class PageController {
	
	private static Database database = new Database();
	private static UserAccount userAccount;
	
	public static Database getDatabase() {
		return database;
	}
	
	public static UserAccount getUserAccount() {
		return userAccount;
	}
	
	public static void setUserAccount(UserAccount userAccount) {
		PageController.userAccount = userAccount;
	}
}
