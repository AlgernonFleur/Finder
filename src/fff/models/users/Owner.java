package fff.models.users;

import fff.models.Restaurant;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Owner extends UserAccount {
	
	private ObservableList<Restaurant> ownedRestaurants;
	
	public Owner(String ID, String username, String password, String fullName, String email) {
		super(ID, username, password, fullName, email);
		this.ownedRestaurants = FXCollections.observableArrayList();
	}
	
	public void addRestaurant(Restaurant r){
		ownedRestaurants.add(r);
	}
}
