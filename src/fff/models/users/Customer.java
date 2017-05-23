package fff.models.users;

import fff.models.Rating;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Customer extends UserAccount {
	
	private ObservableList<Rating> ratings;
	
	public Customer(String ID, String username, String password, String fullName, String email) {
		super(ID, username, password, fullName, email);
		this.ratings = FXCollections.observableArrayList();
	}
	
	public void addRating(Rating r){
		ratings.add(r);
	}
	
	public ObservableList<Rating> getRatings() {
		return ratings;
	}
}
