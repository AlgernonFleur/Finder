package fff.views;

import fff.models.users.Admin;
import fff.models.users.Customer;
import fff.models.users.Owner;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class Admin_View {
	
	@FXML private TableView<Admin> adminTableView;
	@FXML private TableColumn<Admin,String> adminID;
	@FXML private TableColumn<Admin,String> adminUserName;
	@FXML private TableColumn<Admin,String> adminPassword;
	@FXML private TableColumn<Admin,String> adminFullName;
	@FXML private TableColumn<Admin,String> adminEmail;
	
	@FXML private TableView<Customer> customerTableView;
	@FXML private TableColumn<Customer,String> customerID;
	@FXML private TableColumn<Customer,String> customerUserName;
	@FXML private TableColumn<Customer,String> customerPassword;
	@FXML private TableColumn<Customer,String> customerFullName;
	@FXML private TableColumn<Customer,String> customerEmail;
	
	@FXML private TableView<Owner> ownerTableView;
	@FXML private TableColumn<Owner,String> ownerID;
	@FXML private TableColumn<Owner,String> ownerUserName;
	@FXML private TableColumn<Owner,String> ownerPassword;
	@FXML private TableColumn<Owner,String> ownerFullName;
	@FXML private TableColumn<Owner,String> ownerEmail;
	
	@FXML public void initialize() {
		adminTableView.setItems(_Overview_.getDatabase().getAdmins());
		adminID.setCellValueFactory(data->data.getValue().IDProperty());
		adminUserName.setCellValueFactory(data->data.getValue().usernameProperty());
		adminPassword.setCellValueFactory(data->data.getValue().passwordProperty());
		adminFullName.setCellValueFactory(data->data.getValue().fullNameProperty());
		adminEmail.setCellValueFactory(data->data.getValue().emailProperty());
		
		customerTableView.setItems(_Overview_.getDatabase().getCustomers());
		customerID.setCellValueFactory(data->data.getValue().IDProperty());
		customerUserName.setCellValueFactory(data->data.getValue().usernameProperty());
		customerPassword.setCellValueFactory(data->data.getValue().passwordProperty());
		customerFullName.setCellValueFactory(data->data.getValue().fullNameProperty());
		customerEmail.setCellValueFactory(data->data.getValue().emailProperty());
		
		ownerTableView.setItems(_Overview_.getDatabase().getOwners());
		ownerID.setCellValueFactory(data->data.getValue().IDProperty());
		ownerUserName.setCellValueFactory(data->data.getValue().usernameProperty());
		ownerPassword.setCellValueFactory(data->data.getValue().passwordProperty());
		ownerFullName.setCellValueFactory(data->data.getValue().fullNameProperty());
		ownerEmail.setCellValueFactory(data->data.getValue().emailProperty());
	}
}

