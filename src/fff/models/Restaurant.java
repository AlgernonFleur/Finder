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
}
