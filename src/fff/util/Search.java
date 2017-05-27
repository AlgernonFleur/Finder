package fff.util;

import fff.models.Restaurant;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public final class Search {

	
	public static ObservableList<Restaurant> getSearchResultsName(ObservableList<Restaurant> restaurants, String searchInput) {
		
		ObservableList<Restaurant> searchResults = FXCollections.observableArrayList();
		
		int i = 0;

		while (i < restaurants.size()) {
			if (restaurants.get(i).getName().contains(searchInput)) {
				searchResults.add(restaurants.get(i));
			}
			i++;
		}
		return searchResults;
	}


	public static ObservableList<Restaurant> getSearchResultsPostcode(ObservableList<Restaurant> restaurants, String searchInput) {
		
		ObservableList<Restaurant> searchResults = FXCollections.observableArrayList();
		
		int i = 0;
		
		while (i < restaurants.size()) {
			if (Integer.toString(restaurants.get(i).getPostcode()).contains(searchInput)) {
				searchResults.add(restaurants.get(i));
			}
			i++;
		}
		return searchResults;
	}
	
	
	public static ObservableList<Restaurant> getSearchResultsCuisine(ObservableList<Restaurant> restaurants, String searchInput) {
		
		ObservableList<Restaurant> searchResults = FXCollections.observableArrayList();
		
		int i = 0;
		
		while (i < restaurants.size()) {
			if (restaurants.get(i).getCuisine().contains(searchInput)) {
				searchResults.add(restaurants.get(i));
			}
			i++;
		}
		return searchResults;
	}
	
	
	public static ObservableList<Restaurant> getSearchResultsRatings(ObservableList<Restaurant> restaurants, String searchInput) {
		
		ObservableList<Restaurant> searchResults = FXCollections.observableArrayList();
		
		int i = 0;
		
		float inputRating = Float.MAX_VALUE;
		try {
			inputRating = Float.parseFloat(searchInput);
		} catch (NumberFormatException e) {
			
		}
		
		while (i < restaurants.size()) {
			if (restaurants.get(i).getAverageRatings() >= inputRating) {
				searchResults.add(restaurants.get(i));
			}
			i++;
		}
		return searchResults;
	}
	
}
