package fff.views;

import fff.App;
import fff.models.Rating;
import fff.models.Restaurant;
import fff.models.users.Admin;
import fff.models.users.Customer;
import fff.models.users.Owner;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class Admin_View extends _View_{
	
	//<editor-fold desc="Table Variables">
	@FXML private TableView<Admin> adminTableView;
	@FXML private TableColumn<Admin,String> adminID;
	@FXML private TableColumn<Admin,String> adminUserName;
	@FXML private TableColumn<Admin,String> adminPassword;
	@FXML private TableColumn<Admin,String> adminFullName;
	@FXML private TableColumn<Admin,String> adminEmail;
	
	@FXML private TableView<Owner> ownerTableView;
	@FXML private TableColumn<Owner,String> ownerID;
	@FXML private TableColumn<Owner,String> ownerUserName;
	@FXML private TableColumn<Owner,String> ownerPassword;
	@FXML private TableColumn<Owner,String> ownerFullName;
	@FXML private TableColumn<Owner,String> ownerEmail;
	
	@FXML private TableView<Customer> customerTableView;
	@FXML private TableColumn<Customer,String> customerID;
	@FXML private TableColumn<Customer,String> customerUserName;
	@FXML private TableColumn<Customer,String> customerPassword;
	@FXML private TableColumn<Customer,String> customerFullName;
	@FXML private TableColumn<Customer,String> customerEmail;
	
	@FXML private TableView<Restaurant> restaurantTableView;
	@FXML private TableColumn<Restaurant,String> restaurantID;
	@FXML private TableColumn<Restaurant,String> restaurantName;
	@FXML private TableColumn<Restaurant,Integer> restaurantPostcode;
	@FXML private TableColumn<Restaurant,String> restaurantCuisine;
	@FXML private TableColumn<Restaurant,String> restaurantOwner;
	
	@FXML private TableView<Rating> ratingTableView;
	@FXML private TableColumn<Rating,String> ratingID;
	@FXML private TableColumn<Rating,String> ratingResID;
	@FXML private TableColumn<Rating,String> ratingCusID;
	@FXML private TableColumn<Rating,Integer> ratingValue;
	//</editor-fold>
	
	@FXML private Button viewAdmin;
	
	
	@FXML public void initialize() {
		//<editor-fold desc="Table Variable Initializing">
		adminTableView.setItems(_Overview_.getDatabase().getAdmins());
		adminID.setCellValueFactory(data->data.getValue().IDProperty());
		adminUserName.setCellValueFactory(data->data.getValue().usernameProperty());
		adminPassword.setCellValueFactory(data->data.getValue().passwordProperty());
		adminFullName.setCellValueFactory(data->data.getValue().fullNameProperty());
		adminEmail.setCellValueFactory(data->data.getValue().emailProperty());
		
		ownerTableView.setItems(_Overview_.getDatabase().getOwners());
		ownerID.setCellValueFactory(data->data.getValue().IDProperty());
		ownerUserName.setCellValueFactory(data->data.getValue().usernameProperty());
		ownerPassword.setCellValueFactory(data->data.getValue().passwordProperty());
		ownerFullName.setCellValueFactory(data->data.getValue().fullNameProperty());
		ownerEmail.setCellValueFactory(data->data.getValue().emailProperty());
		
		customerTableView.setItems(_Overview_.getDatabase().getCustomers());
		customerID.setCellValueFactory(data->data.getValue().IDProperty());
		customerUserName.setCellValueFactory(data->data.getValue().usernameProperty());
		customerPassword.setCellValueFactory(data->data.getValue().passwordProperty());
		customerFullName.setCellValueFactory(data->data.getValue().fullNameProperty());
		customerEmail.setCellValueFactory(data->data.getValue().emailProperty());
		
		restaurantTableView.setItems(_Overview_.getDatabase().getRestaurants());
		restaurantID.setCellValueFactory(data->data.getValue().IDProperty());
		restaurantName.setCellValueFactory(data->data.getValue().nameProperty());
		restaurantPostcode.setCellValueFactory(data->data.getValue().postcodeProperty().asObject());
		restaurantCuisine.setCellValueFactory(data->data.getValue().cuisineProperty());
		restaurantOwner.setCellValueFactory(data->data.getValue().ownerIDProperty());
		
		ratingTableView.setItems(_Overview_.getDatabase().getRatings());
		ratingID.setCellValueFactory(data->data.getValue().ratingIDProperty());
		ratingResID.setCellValueFactory(data->data.getValue().restaurantIDProperty());
		ratingCusID.setCellValueFactory(data->data.getValue().customerIDProperty());
		ratingValue.setCellValueFactory(data->data.getValue().ratingProperty().asObject());
		//</editor-fold>
		
		this.viewAdmin.setOnAction(e->showAdmin());
		this.adminTableView.setOnMousePressed(e -> {
			if (e.isPrimaryButtonDown() && e.getClickCount()==2) showAdmin();
		});
	}
	
	private void showAdmin(){
		if(adminTableView.getSelectionModel().getSelectedIndex()>=0){
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(App.class.getResource("views/Admin_Acc.fxml"));
			Node prevPage = getMain().getCenterPiece();
			try {
				getMain().changeCenter(loader.load());
			} catch (IOException e) {
				e.printStackTrace();
			}
			Admin_Acc view = loader.getController();
			view.setMain(getMain());
			view.setPreviousPage(prevPage);
			view.setAdmin(adminTableView.getSelectionModel().getSelectedItem());
		}
	}
}

