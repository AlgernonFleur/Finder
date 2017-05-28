package fff.views;

import fff.models.users.UserAccount;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Edit_User {
	
	@FXML private Label userID;
	@FXML private TextField fullNameField;
	@FXML private TextField usernameField;
	@FXML private TextField passwordField;
	@FXML private TextField emailField;
	
	@FXML private Button okButton;
	@FXML private Button cancelButton;
	
	private UserAccount user;
	
	@FXML public void initialize(){
		this.okButton.setOnAction(e->ok());
		this.cancelButton.setOnAction(e->cancel());
	}
	
	public void setUser(UserAccount user){
		this.user = user;
		userID.setText(user.getID());
		fullNameField.setText(user.getFullName());
		usernameField.setText(user.getUsername());
		emailField.setText(user.getEmail());
	}
	
	private void ok(){
		if(isInputValid()){
			user.setFullName(fullNameField.getText());
			user.setUsername(usernameField.getText());
			user.setPassword(passwordField.getText());
			user.setEmail(emailField.getText());
			cancel();
		}
	}
	
	private boolean isInputValid(){
		String error = "";
		
		if(	fullNameField.getText()==null ||
			fullNameField.getText().length()==0) error+="Invalid Full Name\n";
		if(	usernameField.getText()==null ||
			usernameField.getText().length()==0) error+="Invalid Username\n";
		if(	passwordField.getText()==null ||
			passwordField.getText().length()==0) error+="Invalid Password\n";
		if(	emailField.getText()==null ||
			emailField.getText().length()==0) error+="Invalid Email\n";
		if(!emailField.getText().contains("@")) error+="Invalid Email Format\n";
		
		if(error.length()!=0){
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.initOwner(okButton.getScene().getWindow());
			alert.setTitle("Invalid Input");
			alert.setHeaderText("Please correct invalid fields");
			alert.setContentText(error);
			alert.showAndWait();
			return false;
		}else{
			return true;
		}
	}
	
	private void cancel(){
		Stage stage = (Stage) cancelButton.getScene().getWindow();
		stage.close();
	}
}
