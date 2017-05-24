package fff.views;

import fff.models.Rating;
import fff.models.Restaurant;
import fff.models.users.Customer;
import fff.models.users.Owner;
import fff.models.users.UserAccount;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;

public class User_Acc extends _View_ {
	
	@FXML private Text detailsTitle;
	@FXML private Text ID;
	@FXML private Text username;
	@FXML private Text fullName;
	@FXML private Text email;
	@FXML private Button backButton;
	@FXML private TabPane tabs;
	@FXML private Tab resTab;
	@FXML private Tab ratTab;
	
	@FXML private TableView<Restaurant> restaurantTableView;
	@FXML private TableColumn<Restaurant,String> restaurantName;
	@FXML private TableColumn<Restaurant,Integer> restaurantPostcode;
	@FXML private TableColumn<Restaurant,String> restaurantCuisine;
	@FXML private TableColumn<Restaurant,String> restaurantOwner;
	@FXML private TableColumn<Restaurant,Float> restaurantRating;
	@FXML private TableColumn<Restaurant,Float> restaurantPrice;
	
	@FXML private TableView<Rating> ratingTableView;
	@FXML private TableColumn<Rating,String> ratingResName;
	@FXML private TableColumn<Rating,Integer> ratingValue;
	
	private UserAccount user;
	
	@FXML public void initialize(){
		this.backButton.setOnAction(e->goBack());
	}
	
	private void goBack(){
		getMain().changeCenter(getPreviousPage());
	}
	
	public void setUser(UserAccount user) {
		this.user = user;
		this.detailsTitle.setText(this.user.getClass().getSimpleName()+" Details");
		this.ID.setText(this.user.getID());
		this.username.setText(this.user.getUsername());
		this.fullName.setText(this.user.getFullName());
		this.email.setText(this.user.getEmail());
		
		switch (this.user.getClass().getSimpleName()){
			case "Admin":
				this.tabs.setVisible(false);
				break;
			case "Owner":
				this.ratTab.setDisable(true);
				this.ratTab.setText("");
				this.tabs.getSelectionModel().select(0);
				
				restaurantTableView.setItems(((Owner) user).getOwnedRestaurants());
				restaurantName.setCellValueFactory(data->data.getValue().nameProperty());
				restaurantPostcode.setCellValueFactory(data->data.getValue().postcodeProperty().asObject());
				restaurantCuisine.setCellValueFactory(data->data.getValue().cuisineProperty());
				restaurantOwner.setCellValueFactory(data->data.getValue().ownerIDProperty());
				restaurantRating.setCellValueFactory(data->data.getValue().averageRatingsProperty().asObject());
				restaurantPrice.setCellValueFactory(data->data.getValue().priceRangeProperty().asObject());
				break;
			case "Customer":
				this.resTab.setDisable(true);
				this.resTab.setText("");
				this.tabs.getSelectionModel().select(1);
				
				ratingTableView.setItems(((Customer) user).getRatings());
				ratingResName.setCellValueFactory(data->data.getValue().getRestaurantObjectProperty().nameProperty());
				ratingValue.setCellValueFactory(data->data.getValue().ratingProperty().asObject());
				break;
			default:
				break;
		}
	}
}
