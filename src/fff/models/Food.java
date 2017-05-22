package fff.models;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Food {
	private StringProperty name;
	private FloatProperty price;
	
	public Food(String name, Float price) {
		this.name = new SimpleStringProperty(name);
		this.price = new SimpleFloatProperty(price);
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
	
	public float getPrice() {
		return price.get();
	}
	
	public FloatProperty priceProperty() {
		return price;
	}
	
	public void setPrice(float price) {
		this.price.set(price);
	}
}
