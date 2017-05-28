package fff.views;

import fff.models.users.Customer;
import fff.models.users.Owner;
import fff.models.users.UserAccount;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.util.Optional;

public class Create_User {
	
	@FXML private ChoiceBox<String> newType;
	@FXML private TextField newUsername;
	@FXML private TextField newPassword;
	@FXML private TextField newFullName;
	@FXML private TextField newEmail;
	
	@FXML private Button confirmButton;
	@FXML private Button cancelButton;
	
	private UserAccount newUser;
	
	@FXML public void initialize(){
		newUser = null;
		newType.setItems(FXCollections.observableArrayList("Customer","Owner"));
		confirmButton.setOnAction(e->confirm());
		cancelButton.setOnAction(e->cancel());
	}
	
	private void confirm(){
		String error = "";
		
		if(newType.getSelectionModel().getSelectedIndex()<0||
			newType.getSelectionModel().getSelectedIndex()>=2) error += "Invalid User Type\n";
		if(newUsername.getText()==null||
			newUsername.getText().length()==0) error += "Invalid Username\n";
		if(newPassword.getText()==null||
			newPassword.getText().length()==0) error += "Invalid Password\n";
		if(newFullName.getText()==null||
			newFullName.getText().length()==0) error += "Invalid Full Name\n";
		if(newEmail.getText()==null||
			newEmail.getText().length()==0||
			!newEmail.getText().contains("@")) error += "Invalid Email\n";
		
		if(error.equals("")){
			switch (newType.getSelectionModel().getSelectedIndex()){
				case 0:
					int customerCount =
						_Overview_.getDatabase().getCustomers().size();
					String ID1 = "C"+String.format("%05d",customerCount);
					this.newUser = new Customer(
						ID1,
						newUsername.getText(),
						newPassword.getText(),
						newFullName.getText(),
						newEmail.getText());
					break;
				case 1:
					int ownerCount =
						_Overview_.getDatabase().getOwners().size();
					String ID2 = "B"+String.format("%05d",ownerCount);
					this.newUser = new Owner(
						ID2,
						newUsername.getText(),
						newPassword.getText(),
						newFullName.getText(),
						newEmail.getText());
					break;
			}
			Stage stage = (Stage) cancelButton.getScene().getWindow();
			stage.close();
		}else{
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Input Error");
			alert.setHeaderText("Please fix input errors:");
			alert.setContentText(error);
			alert.showAndWait();
		}
	}
	
	private void cancel(){
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Cancel User Creation ");
		alert.setHeaderText("Do you intend to cancel?");
		alert.setContentText(null);
		Optional<ButtonType> result = alert.showAndWait();
		if(result.get()==ButtonType.OK){
			Stage stage = (Stage) cancelButton.getScene().getWindow();
			stage.close();
		}
	}
	
	public UserAccount getNewUser() {
		return newUser;
	}
}
