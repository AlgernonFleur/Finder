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
		this.averageRatings = new SimpleFloatProperty(0);
		this.averageRatings = new SimpleFloatProperty(0);
	}
	
	public void addRating(Rating r){
		ratings.add(r);
	}
	
	public void addFood(Food f){
		menu.add(f);
	}
	
	//<editor-fold desc="--Variable Getters and Setters--">
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
	
	public float getAverageRatings() {
		return averageRatings.get();
	}
	
	public FloatProperty averageRatingsProperty() {
		return averageRatings;
	}
	
	public void setAverageRatings(float averageRatings) {
		this.averageRatings.set(averageRatings);
	}
	
	public float getPriceRange() {
		return priceRange.get();
	}
	
	public FloatProperty priceRangeProperty() {
		return priceRange;
	}
	
	public void setPriceRange(float priceRange) {
		this.priceRange.set(priceRange);
	}
	//</editor-fold>
	
	public ObservableList<Rating> getRatings() {
		return ratings;
	}
	
	public ObservableList<Food> getMenu() {
		return menu;
	}
	
	public void calculateRatings(){
		float avrgRate = 0;
		for(Rating r:ratings){
			avrgRate+=r.getRating();
		}
		avrgRate /= ratings.size();
		this.averageRatings.set(avrgRate);
	}
	
	public void calculateMenu(){
		float avrgPrice = 0;
		for(Food f:menu){
			avrgPrice+=f.getPrice();
		}
		avrgPrice /= menu.size();
		this.priceRange.set(avrgPrice);
	}
}
