package fff.views;

import fff.App;
import fff.models.Restaurant;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.Random;

public class Default_View extends _View_{
	
	@FXML private Pane featuredRestaurantPane;
	@FXML private Text featuredRestaurantName;
	@FXML private Text featuredRestaurantCuisine;
	@FXML private Text featuredRestaurantRating;
	
	@FXML private Pane searchRestaurant;
	@FXML private Pane feelingLucky;
	@FXML private Pane top25;
	@FXML private Pane browseRestaurants;
	
	private Restaurant featuredRestaurant;
	private Restaurant luckyRestaurant;
	
	@FXML public void initialize(){
		Random random = new Random(System.currentTimeMillis());
		int size = _Overview_.getDatabase().getRestaurants().size();
		
		this.featuredRestaurant =
			_Overview_.getDatabase().getRestaurants().get(random.nextInt(size));
		this.featuredRestaurantName.setText(featuredRestaurant.getName());
		this.featuredRestaurantCuisine.setText("Cuisine: "+featuredRestaurant.getCuisine());
		this.featuredRestaurantRating.setText("Rating: "+
			String.valueOf(
				(int)featuredRestaurant.getAverageRatings())+"/10");
		
		this.featuredRestaurantPane.setOnMousePressed(e->{
			if (e.isPrimaryButtonDown() && e.getClickCount()==2)
				goToRestaurantPage(featuredRestaurant);
		});
		
		this.searchRestaurant.setOnMousePressed(e->{
			if (e.isPrimaryButtonDown() && e.getClickCount()==2)
				goToSearchPage();
		});
		
		this.browseRestaurants.setOnMousePressed(e->{
			if (e.isPrimaryButtonDown() && e.getClickCount()==2)
				goToSearchPage();
		});
		
		this.top25.setOnMousePressed(e->{
			if (e.isPrimaryButtonDown() && e.getClickCount()==2)
				goToSearchPage();
		});
		
		this.feelingLucky.setOnMousePressed(e->{
			if (e.isPrimaryButtonDown() && e.getClickCount()==2){
				this.luckyRestaurant =
					_Overview_.getDatabase().getRestaurants().get(random.nextInt(size));
				goToRestaurantPage(luckyRestaurant);
			}
		});
	}
	
	private void goToRestaurantPage(Restaurant r){
		if(r!=null){
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(App.class.getResource("views/Res_Acc.fxml"));
			Node prevPage = getMain().getCenterPiece();
			try {
				getMain().changeCenter(loader.load());
			} catch (IOException e) {
				e.printStackTrace();
			}
			Res_Acc view = loader.getController();
			view.setMain(getMain());
			view.setPreviousPage(prevPage);
			view.setRestaurant(r);
		}
	}
	
	private void goToSearchPage(){
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(App.class.getResource("views/Search_Page.fxml"));
		Node prevPage = getMain().getCenterPiece();
		try {
			getMain().changeCenter(loader.load());
		} catch (IOException e) {
			e.printStackTrace();
		}
		Search_Page view = loader.getController();
		view.setMain(getMain());
		view.setPreviousPage(prevPage);
	}
}
