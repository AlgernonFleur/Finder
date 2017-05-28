package fff.views;

import fff.App;
import fff.models.Rating;
import fff.models.Restaurant;
import fff.models.users.Customer;
import fff.models.users.Owner;
import fff.models.users.UserAccount;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class User_Acc extends _View_ {
	
	@FXML private Text detailsTitle;
	@FXML private Text type;
	@FXML private Text ID;
	@FXML private Text username;
	@FXML private Text fullName;
	@FXML private Text email;
	@FXML private Button backButton;
	@FXML private Button changeAccDetailsButton;
	@FXML private TabPane tabs;
	@FXML private Tab resTab;
	@FXML private Tab ratTab;
	@FXML private Tab resTab1;
	
	@FXML private TableView<Restaurant> restaurantTableView;
	@FXML private TableColumn<Restaurant,String> restaurantName;
	@FXML private TableColumn<Restaurant,Integer> restaurantPostcode;
	@FXML private TableColumn<Restaurant,String> restaurantCuisine;
	@FXML private TableColumn<Restaurant,String> restaurantRating;
	@FXML private TableColumn<Restaurant,String> restaurantPrice;
	@FXML private Button viewRestaurant1;
	
	@FXML private TableView<Restaurant> restaurantTableView1;
	@FXML private TableColumn<Restaurant,String> restaurantName1;
	@FXML private TableColumn<Restaurant,Integer> restaurantPostcode1;
	@FXML private TableColumn<Restaurant,String> restaurantCuisine1;
	@FXML private TableColumn<Restaurant,String> restaurantRating1;
	@FXML private TableColumn<Restaurant,String> restaurantPrice1;
	@FXML private Button viewRestaurant11;
	
	@FXML private TableView<Rating> ratingTableView;
	@FXML private TableColumn<Rating,String> ratingResName;
	@FXML private TableColumn<Rating,Integer> ratingValue;
	@FXML private Button viewRestaurant2;
	@FXML private Button removeFave;
	
	@FXML private Button editRating;
	
	private UserAccount user;
	
	@FXML public void initialize(){
		this.backButton.setOnAction(e->goBack());
		this.viewRestaurant1.setOnAction(e->goToRestaurant1());
		this.restaurantTableView.setOnMousePressed(e->{
			if (e.isPrimaryButtonDown() && e.getClickCount()==2) goToRestaurant1();
		});
		this.viewRestaurant2.setOnAction(e->goToRestaurant2());
		this.ratingTableView.setOnMousePressed(e->{
			if (e.isPrimaryButtonDown() && e.getClickCount()==2) goToRestaurant2();
		});
		this.viewRestaurant11.setOnAction(e->goToRestaurant3());
		this.restaurantTableView1.setOnMousePressed(e->{
			if (e.isPrimaryButtonDown() && e.getClickCount()==2) goToRestaurant3();
		});
		this.editRating.setOnAction(e->editSelectedRating());
	}
	
	private void goBack(){
		getMain().changeCenter(getPreviousPage());
	}
	
	private void goToRestaurant1(){
		Restaurant r = restaurantTableView.getSelectionModel().getSelectedItem();
		if(r!=null) changeToRes(r);
	}
	
	private void goToRestaurant2(){
		Restaurant r = ratingTableView.getSelectionModel().getSelectedItem().getRestaurantObjectProperty();
		if(r!=null) changeToRes(r);
	}
	
	private void goToRestaurant3(){
		Restaurant r = restaurantTableView1.getSelectionModel().getSelectedItem();
		if(r!=null) changeToRes(r);
	}
	
	private void changeToRes(Restaurant r){
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
	
	public void setUser(UserAccount user) {
		this.user = user;
		this.type.setText(this.user.getClass().getSimpleName().toUpperCase());
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
				this.resTab1.setDisable(true);
				this.resTab1.setText("");
				this.tabs.getSelectionModel().select(0);
				
				restaurantTableView.setItems(((Owner) user).getOwnedRestaurants());
				restaurantName.setCellValueFactory(data->data.getValue().nameProperty());
				restaurantPostcode.setCellValueFactory(data->data.getValue().postcodeProperty().asObject());
				restaurantCuisine.setCellValueFactory(data->data.getValue().cuisineProperty());
				restaurantRating.setCellValueFactory(data->data.getValue().averageRatingsProperty().asString("%.1f"));
				restaurantPrice.setCellValueFactory(data->data.getValue().priceRangeProperty().asString("%2.2f"));
				break;
			case "Customer":
				this.resTab.setDisable(true);
				this.resTab.setText("");
				this.tabs.getSelectionModel().select(1);
				
				ratingTableView.setItems(((Customer) user).getRatings());
				ratingResName.setCellValueFactory(data->data.getValue().getRestaurantObjectProperty().nameProperty());
				ratingValue.setCellValueFactory(data->data.getValue().ratingProperty().asObject());
				
				restaurantTableView1.setItems(((Customer) user).getFavourites());
				restaurantName1.setCellValueFactory(data->data.getValue().nameProperty());
				restaurantPostcode1.setCellValueFactory(data->data.getValue().postcodeProperty().asObject());
				restaurantCuisine1.setCellValueFactory(data->data.getValue().cuisineProperty());
				restaurantRating1.setCellValueFactory(data->data.getValue().averageRatingsProperty().asString("%.1f"));
				restaurantPrice1.setCellValueFactory(data->data.getValue().priceRangeProperty().asString("%2.2f"));
				break;
			default:
				break;
		}
		if(_Overview_.getUserAccount()!=null &&
			_Overview_.getUserAccount().getClass().getSimpleName().equals("Admin")||
			this.user.equals(_Overview_.getUserAccount())){
			this.changeAccDetailsButton.setOnAction(e->openChangeDetailsDialog());
			this.editRating.setVisible(true);
		}else{
			this.editRating.setVisible(false);
			this.changeAccDetailsButton.setVisible(false);
		}
		
		if(_Overview_.getUserAccount()!=null)
			if(_Overview_.getUserAccount().equals(user)){
				this.removeFave.setOnAction(e->removeFavourite());
			}else{
				this.removeFave.setVisible(false);
			}
		else this.removeFave.setVisible(false);
	}
	
	private void openChangeDetailsDialog(){
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(
				App.class.getResource("views/Edit_User.fxml"));
			
			Stage loginDialog = new Stage();
			loginDialog.setScene(new Scene(loader.load()));
			
			Edit_User dialog = loader.getController();
			dialog.setUser(user);
			
			loginDialog.setTitle("Change Details");
			loginDialog.initModality(Modality.WINDOW_MODAL);
			loginDialog.initOwner(_Overview_.getStage());
			loginDialog.setResizable(false);
			loginDialog.sizeToScene();
			loginDialog.showAndWait();
			
			this.username.setText(this.user.getUsername());
			this.fullName.setText(this.user.getFullName());
			this.email.setText(this.user.getEmail());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void editSelectedRating(){
		Rating r = ratingTableView.getSelectionModel().getSelectedItem();
		if (r != null) {
			openRatingEditDialog(r);
		}
	}
	
	private void openRatingEditDialog(Rating r){
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
	
	private void removeFavourite(){
		int selected = restaurantTableView1.getSelectionModel().getSelectedIndex();
		if(selected>=0)restaurantTableView1.getItems().remove(selected);
	}
}
