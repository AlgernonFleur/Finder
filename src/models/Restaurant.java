package models;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
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
}
