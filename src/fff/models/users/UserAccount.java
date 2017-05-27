package fff.models.users;

import fff.models.Restaurant;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public abstract class UserAccount {
	
	private StringProperty ID;
	private StringProperty username;
	private StringProperty password;
	private StringProperty fullName;
	private StringProperty email;
	
	private ObservableList<Restaurant> favourites;
	
	public UserAccount(String ID,String username,String password, String fullName, String email){
		this.ID = new SimpleStringProperty(ID);
		this.username = new SimpleStringProperty(username);
		this.password = new SimpleStringProperty(password);
		this.fullName = new SimpleStringProperty(fullName);
		this.email = new SimpleStringProperty(email);
		this.favourites = FXCollections.observableArrayList();
	}
	
	public String getID() {
		return ID.get();
	}
	
	public StringProperty IDProperty() {
		return ID;
	}
	
	public void setID(String ID) {
		this.ID.set(ID);
	}
	
	public String getUsername() {
		return username.get();
	}
	
	public StringProperty usernameProperty() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username.set(username);
	}
	
	public String getPassword() {
		return password.get();
	}
	
	public StringProperty passwordProperty() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password.set(password);
	}
	
	public String getFullName() {
		return fullName.get();
	}
	
	public StringProperty fullNameProperty() {
		return fullName;
	}
	
	public void setFullName(String fullName) {
		this.fullName.set(fullName);
	}
	
	public String getEmail() {
		return email.get();
	}
	
	public StringProperty emailProperty() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email.set(email);
	}
}
