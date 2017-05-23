package fff.models;

import fff.models.users.Customer;
import javafx.beans.property.*;

public class Rating {
	private StringProperty ratingID;
	private StringProperty restaurantID;
	private StringProperty customerID;
	private IntegerProperty rating;
	
	private ObjectProperty<Restaurant> restaurantObjectProperty;
	private ObjectProperty<Customer> customerObjectProperty;
	
	public Rating(String ratingID, String restaurantID, String customerID, int rating) {
		this.ratingID = new SimpleStringProperty(ratingID);
		this.restaurantID = new SimpleStringProperty(restaurantID);
		this.customerID = new SimpleStringProperty(customerID);
		this.rating = new SimpleIntegerProperty(rating);
		
		this.restaurantObjectProperty = new SimpleObjectProperty<>();
		this.customerObjectProperty = new SimpleObjectProperty<>();
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
	
	public Restaurant getRestaurantObjectProperty() {
		return restaurantObjectProperty.get();
	}
	
	public ObjectProperty<Restaurant> restaurantObjectPropertyProperty() {
		return restaurantObjectProperty;
	}
	
	public void setRestaurantObjectProperty(Restaurant restaurantObjectProperty) {
		this.restaurantObjectProperty.set(restaurantObjectProperty);
	}
	
	public Customer getCustomerObjectProperty() {
		return customerObjectProperty.get();
	}
	
	public ObjectProperty<Customer> customerObjectPropertyProperty() {
		return customerObjectProperty;
	}
	
	public void setCustomerObjectProperty(Customer customerObjectProperty) {
		this.customerObjectProperty.set(customerObjectProperty);
	}
}
