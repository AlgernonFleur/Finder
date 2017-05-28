package fff.util;

import fff.models.Restaurant;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public final class Search {
	
	public static ObservableList<Restaurant>
	getSearchResultsName(ObservableList<Restaurant> restaurants, String searchInput) {
		ObservableList<Restaurant> searchResults = FXCollections.observableArrayList();
		
		for (Restaurant restaurant : restaurants)
			if (restaurant.getName().toLowerCase().contains(searchInput.toLowerCase()))
				searchResults.add(restaurant);
		
		return searchResults;
	}
	
	public static ObservableList<Restaurant>
	getSearchResultsCuisine(ObservableList<Restaurant> restaurants, String searchInput) {
		ObservableList<Restaurant> searchResults = FXCollections.observableArrayList();
		
		for (Restaurant restaurant : restaurants)
			if (restaurant.getCuisine().toLowerCase().contains(searchInput.toLowerCase()))
				searchResults.add(restaurant);
		
		return searchResults;
	}
	
	public static ObservableList<Restaurant>
	getSearchResultsPostcode(ObservableList<Restaurant> restaurants, String searchInput) {
		ObservableList<Restaurant> searchResults = FXCollections.observableArrayList();
		
		for (Restaurant restaurant : restaurants)
			if (Integer.toString(restaurant.getPostcode()).contains(searchInput))
				searchResults.add(restaurant);
		return searchResults;
	}
	
	public static ObservableList<Restaurant>
	getSearchResultsRatings(ObservableList<Restaurant> restaurants, String searchInput1, String searchInput2)
	throws NumberFormatException{
		ObservableList<Restaurant> searchResults = FXCollections.observableArrayList();
		
		float in1 = Float.parseFloat(searchInput1);
		float in2 = Float.parseFloat(searchInput2);
		
		for (Restaurant restaurant : restaurants)
			if (restaurant.getAverageRatings() >= in1 && restaurant.getAverageRatings() <= in2)
				searchResults.add(restaurant);
		return searchResults;
	}
	
	public static ObservableList<Restaurant>
	getSearchResultsPrice(ObservableList<Restaurant> restaurants, String searchInput1, String searchInput2)
	throws NumberFormatException{
		ObservableList<Restaurant> searchResults = FXCollections.observableArrayList();
		float in1,in2;
		if(searchInput1==null){
			in1 = 0;
		}else{
			in1 = Float.parseFloat(searchInput1);
		}if(searchInput2==null){
			in2 = 0;
		}else{
			in2 = Float.parseFloat(searchInput2);
		}
		
		for (Restaurant restaurant : restaurants)
			if (restaurant.getPriceRange() >= in1 && restaurant.getPriceRange() <= in2)
				searchResults.add(restaurant);
		return searchResults;
	}
}
