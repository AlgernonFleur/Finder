package fff.views;

import fff.App;
import fff.models.Food;
import fff.models.Rating;
import fff.models.Restaurant;
import fff.models.users.Customer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

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
	@FXML private Button favouriteButton;
	@FXML private Button ratingButton;
	
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
		this.resRating.setText(String.format("%1.1f",this.restaurant.getAverageRatings()));
		this.resAveragePrice.setText(String.format("%2.2f",this.restaurant.getPriceRange()));
		
		this.menu.setItems(this.restaurant.getMenu());
		this.foodNames.setCellValueFactory(data->data.getValue().nameProperty());
		this.foodPrices.setCellValueFactory(data->data.getValue().priceProperty().asString("%2.2f"));
		
		this.ratings.setItems(this.restaurant.getRatings());
		this.ratingsCustomer.setCellValueFactory(data->data.getValue().getCustomerObjectProperty().fullNameProperty());
		this.ratingsValue.setCellValueFactory(data->data.getValue().ratingProperty().asString());
		
		if(_Overview_.getUserAccount()==null){
			this.ratingButton.setOnAction(e->notLoggedInAlert());
			this.favouriteButton.setOnAction(e->notLoggedInAlert());
		} else if(_Overview_.getUserAccount().getClass().getSimpleName().equals("Customer")){
			boolean customerAlreadyRated = false;
			for(Rating r:restaurant.getRatings()){
				if (r.getCustomerObjectProperty().equals(_Overview_.getUserAccount())){
					this.ratingButton.setText("Edit Review");
					this.ratingButton.setOnAction(e->editRating(r));
					customerAlreadyRated = true;
				}
			}
			if(!customerAlreadyRated){
				this.ratingButton.setText("Review");
				this.ratingButton.setOnAction(e->customerNewRating());
			}
			
			Customer c = (Customer) _Overview_.getUserAccount();
			if(c.getFavourites().contains(restaurant)){
				this.favouriteButton.setText("Remove fave");
				this.favouriteButton.setOnAction(e->removeFromFavourites(c));
			}else{
				this.favouriteButton.setText("Add fave");
				this.favouriteButton.setOnAction(e->addToFavourites(c));
			}
		} else {
			this.ratingButton.setOnAction(e->notLoggedInAlert());
			this.favouriteButton.setOnAction(e->notLoggedInAlert());
		}
	}
	
	private void removeFromFavourites(Customer c){
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Remove from favourites");
		alert.setHeaderText(null);
		alert.setContentText("Do you wish to remove "+restaurant.getName()+" from your favourites?");
		
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK){
			c.getFavourites().remove(restaurant);
			this.favouriteButton.setText("Add fave");
			this.favouriteButton.setOnAction(e->addToFavourites(c));
		}
	}
	
	private void addToFavourites(Customer c){
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Add to favourites");
		alert.setHeaderText(null);
		alert.setContentText("Do you wish to add " + restaurant.getName() + " to your favourites?");
		
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {
			c.getFavourites().add(restaurant);
			this.favouriteButton.setText("Remove fave");
			this.favouriteButton.setOnAction(e -> removeFromFavourites(c));
		}
	}
	
	private void notLoggedInAlert(){
		Alert alert = new Alert(Alert.AlertType.WARNING);
		alert.setTitle("Must be logged in");
		alert.setHeaderText(null);
		alert.setContentText("You must be logged in as a Customer to do that!");
		
		alert.showAndWait();
	}
	
	private void editRating(Rating r){
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(
				App.class.getResource("views/Edit_Rating.fxml"));
			
			Stage loginDialog = new Stage();
			loginDialog.setScene(new Scene(loader.load()));
			
			Edit_Rating dialog = loader.getController();
			dialog.setRating(r);
			
			loginDialog.setTitle("Change Details");
			loginDialog.initModality(Modality.WINDOW_MODAL);
			loginDialog.initOwner(_Overview_.getStage());
			loginDialog.setResizable(false);
			loginDialog.sizeToScene();
			loginDialog.showAndWait();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void customerNewRating(){
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(
				App.class.getResource("views/Edit_Rating.fxml"));
			
			Stage loginDialog = new Stage();
			loginDialog.setScene(new Scene(loader.load()));
			
			String newRatingID = ""+
				String.format("%05d",_Overview_.getDatabase().getRatings().size());
			Rating r = new Rating(newRatingID, restaurant.getID(), _Overview_.getUserAccount().getID(), 1);
			r.setRestaurantObjectProperty(restaurant);
			r.setCustomerObjectProperty((Customer) _Overview_.getUserAccount());
			
			Edit_Rating dialog = loader.getController();
			dialog.setRating(r);
			
			loginDialog.setTitle("Change Details");
			loginDialog.initModality(Modality.WINDOW_MODAL);
			loginDialog.initOwner(_Overview_.getStage());
			loginDialog.setResizable(false);
			loginDialog.sizeToScene();
			loginDialog.showAndWait();
			
			if(dialog.isRatingApproved()){
				_Overview_.getDatabase().getRatings().add(r);
				restaurant.getRatings().add(r);
				this.ratingButton.setText("Edit Review");
				this.ratingButton.setOnAction(e->editRating(r));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
