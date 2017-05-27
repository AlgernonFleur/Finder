package fff.models.users;

import fff.models.Rating;
import fff.models.Restaurant;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Customer extends UserAccount {
	
	private ObservableList<Rating> ratings;
	private ObservableList<Restaurant> favourites;
	
	public Customer(String ID, String username, String password, String fullName, String email) {
		super(ID, username, password, fullName, email);
		this.ratings = FXCollections.observableArrayList();
		this.favourites = FXCollections.observableArrayList();
	}
	
	public void addRating(Rating r){
		ratings.add(r);
	}
	
	public ObservableList<Rating> getRatings() {
		return ratings;
	}
	
	public void addFave(Restaurant r){
		favourites.add(r);
	}
	
	public ObservableList<Restaurant> getFavourites(){
		return favourites;
	}
}
