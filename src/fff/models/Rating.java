package fff.models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Rating {
	private StringProperty ratingID;
	private StringProperty restaurantID;
	private StringProperty customerID;
	private IntegerProperty rating;
	
	public Rating(String ratingID, String restaurantID, String customerID, int rating) {
		this.ratingID = new SimpleStringProperty(ratingID);
		this.restaurantID = new SimpleStringProperty(restaurantID);
		this.customerID = new SimpleStringProperty(customerID);
		this.rating = new SimpleIntegerProperty(rating);
	}
	
	public String getRatingID() {
		return ratingID.get();
	}
	
	public StringProperty ratingIDProperty() {
		return ratingID;
	}
	
	public void setRatingID(String ratingID) {
		this.ratingID.set(ratingID);
	}
	
	public String getRestaurantID() {
		return restaurantID.get();
	}
	
	public StringProperty restaurantIDProperty() {
		return restaurantID;
	}
	
	public void setRestaurantID(String restaurantID) {
		this.restaurantID.set(restaurantID);
	}
	
	public String getCustomerID() {
		return customerID.get();
	}
	
	public StringProperty customerIDProperty() {
		return customerID;
	}
	
	public void setCustomerID(String customerID) {
		this.customerID.set(customerID);
	}
	
	public int getRating() {
		return rating.get();
	}
	
	public IntegerProperty ratingProperty() {
		return rating;
	}
	
	public void setRating(int rating) {
		this.rating.set(rating);
	}
}
