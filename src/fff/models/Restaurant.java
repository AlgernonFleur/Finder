package fff.models;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Restaurant {
	private StringProperty ID;
	private StringProperty name;
	private IntegerProperty postcode;
	private StringProperty cuisine;
	private StringProperty ownerID;
	private ObservableList<Rating> ratings;
	private ObservableList<Food> menu;
	
	private FloatProperty averageRatings;
	private FloatProperty priceRange;
	
	public Restaurant(String ID, String name, int postcode, String cuisine, String ownerID){
		this.ID = new SimpleStringProperty(ID);
		this.name = new SimpleStringProperty(name);
		this.postcode = new SimpleIntegerProperty(postcode);
		this.cuisine = new SimpleStringProperty(cuisine);
		this.ownerID = new SimpleStringProperty(ownerID);
		this.ratings = FXCollections.observableArrayList();
		this.menu = FXCollections.observableArrayList();
	}
	
	public void addRating(Rating r){
		ratings.add(r);
	}
	
	public void addFood(Food f){
		menu.add(f);
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
	
	public String getName() {
		return name.get();
	}
	
	public StringProperty nameProperty() {
		return name;
	}
	
	public void setName(String name) {
		this.name.set(name);
	}
	
	public int getPostcode() {
		return postcode.get();
	}
	
	public IntegerProperty postcodeProperty() {
		return postcode;
	}
	
	public void setPostcode(int postcode) {
		this.postcode.set(postcode);
	}
	
	public String getCuisine() {
		return cuisine.get();
	}
	
	public StringProperty cuisineProperty() {
		return cuisine;
	}
	
	public void setCuisine(String cuisine) {
		this.cuisine.set(cuisine);
	}
	
	public String getOwnerID() {
		return ownerID.get();
	}
	
	public StringProperty ownerIDProperty() {
		return ownerID;
	}
	
	public void setOwnerID(String ownerID) {
		this.ownerID.set(ownerID);
	}
}
