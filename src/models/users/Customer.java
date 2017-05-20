package models.users;

import models.Rating;
import javafx.collections.ObservableList;

public class Customer extends UserAccount {
	
	private ObservableList<Rating> ratings;
	
	public Customer(String ID, String username, String password, String fullName, String email) {
		super(ID, username, password, fullName, email);
	}
}
