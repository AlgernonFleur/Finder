package fff.views;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class Search_Type_Redirect extends _View_{
	
	@FXML private Button backButton;
	@FXML private Pane name;
	@FXML private Pane cuisine;
	@FXML private Pane postcode;
	@FXML private Pane rating;
	@FXML private Pane priceRange;
	
	@FXML public void initialize(){
		this.backButton.setOnAction(e->goBack());
		this.name.setOnMousePressed(e->{
			if (e.isPrimaryButtonDown() && e.getClickCount()==2)
				searchByName();
		});
		this.cuisine.setOnMousePressed(e->{
			if (e.isPrimaryButtonDown() && e.getClickCount()==2)
				searchByCuisine();
		});
		this.postcode.setOnMousePressed(e->{
			if (e.isPrimaryButtonDown() && e.getClickCount()==2)
				searchByPostcode();
		});
		this.rating.setOnMousePressed(e->{
			if (e.isPrimaryButtonDown() && e.getClickCount()==2)
				searchByRating();
		});
		this.priceRange.setOnMousePressed(e->{
			if (e.isPrimaryButtonDown() && e.getClickCount()==2)
				searchByPriceRange();
		});
	}
	
	private void goBack(){
		getMain().changeCenter(getPreviousPage());
	}
	
	private void searchByName(){
	
	}
	
	private void searchByCuisine(){
	
	}
	
	private void searchByPostcode(){
	
	}
	
	private void searchByRating(){
	
	}
	
	private void searchByPriceRange(){
	
	}
}
