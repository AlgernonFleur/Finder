package fff.views;

import fff.App;
import fff.models.Food;
import fff.models.Rating;
import fff.models.Restaurant;
import fff.models.users.Customer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.text.Text;

import java.io.IOException;

public class Res_Acc extends _View_ {
	
	@FXML private Text resID;
	@FXML private Text resName;
	@FXML private Text resCuisine;
	@FXML private Text resPostcode;
	@FXML private Text resRating;
	@FXML private Text resAveragePrice;
	
	@FXML private TableView<Food> menu;
	@FXML private TableColumn<Food,String> foodNames;
	@FXML private TableColumn<Food,String> foodPrices;
	
	@FXML private TableView<Rating> ratings;
	@FXML private TableColumn<Rating,String> ratingsCustomer;
	@FXML private TableColumn<Rating,String> ratingsValue;
	@FXML private Button viewUser;
	
	@FXML private Button backButton;
	@FXML private Button ownerButton;
	
	private Restaurant restaurant;
	
	@FXML public void initialize(){
		this.backButton.setOnAction(e->goBack());
		this.ownerButton.setOnAction(e->goToOwner());
		this.viewUser.setOnAction(e->goToUser());
		this.ratings.setOnMousePressed(e->{
			if (e.isPrimaryButtonDown() && e.getClickCount()==2) goToUser();
		});
	}
	
	private void goBack(){
		getMain().changeCenter(getPreviousPage());
	}
	
	private void goToOwner(){
		if(restaurant.getOwnerObjectProperty()!=null){
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(App.class.getResource("views/User_Acc.fxml"));
			Node prevPage = getMain().getCenterPiece();
			try {
				getMain().changeCenter(loader.load());
			} catch (IOException e) {
				e.printStackTrace();
			}
			User_Acc view = loader.getController();
			view.setMain(getMain());
			view.setPreviousPage(prevPage);
			view.setUser(restaurant.getOwnerObjectProperty());
		}
	}
	
	private void goToUser(){
		Customer c = ratings.getSelectionModel().getSelectedItem().getCustomerObjectProperty();
		if(c!=null){
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(App.class.getResource("views/User_Acc.fxml"));
			Node prevPage = getMain().getCenterPiece();
			try {
				getMain().changeCenter(loader.load());
			} catch (IOException e) {
				e.printStackTrace();
			}
			User_Acc view = loader.getController();
			view.setMain(getMain());
			view.setPreviousPage(prevPage);
			view.setUser(c);
		}
	}
	
	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
		this.resID.setText(this.restaurant.getID());
		this.resName.setText(this.restaurant.getName());
		this.resCuisine.setText(this.restaurant.getCuisine());
		this.resPostcode.setText(String.valueOf(this.restaurant.getPostcode()));
		this.resRating.setText(String.valueOf((int)this.restaurant.getAverageRatings()));
		this.resAveragePrice.setText(String.format("%2.2f",this.restaurant.getPriceRange()));
		
		this.menu.setItems(this.restaurant.getMenu());
		this.foodNames.setCellValueFactory(data->data.getValue().nameProperty());
		this.foodPrices.setCellValueFactory(data->data.getValue().priceProperty().asString("%2.2f"));
		
		this.ratings.setItems(this.restaurant.getRatings());
		this.ratingsCustomer.setCellValueFactory(data->data.getValue().getCustomerObjectProperty().fullNameProperty());
		this.ratingsValue.setCellValueFactory(data->data.getValue().ratingProperty().asString());
	}
}