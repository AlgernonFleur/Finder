package fff.views;

import fff.models.users.UserAccount;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Login_Dialog {
	
	@FXML private TextField usernameField;
	@FXML private TextField passwordField;
	@FXML private Button loginButton;
	@FXML private Text loginFailState;
	
	@FXML public void initialize(){
		usernameField.setOnKeyPressed(e-> {
			if(e.getCode().getName().equals("Enter"))checkDetails();
		});
		passwordField.setOnKeyPressed(e-> {
			if(e.getCode().getName().equals("Enter"))checkDetails();
		});
		loginButton.setOnAction(e->checkDetails());
	}
	
	private void checkDetails(){
		String username = usernameField.getText();
		String password = passwordField.getText();
		
		UserAccount user = _Overview_.getDatabase().getUser(username);
		if(user!=null)
			if(user.getPassword().equals(password)) {
				_Overview_.setUserAccount(user);
				Stage dialog = (Stage) loginFailState.getScene().getWindow();
				dialog.close();
			}
			else loginFailed("Password Invalid");
		else loginFailed("Cannot find User");
	}
	
	private void loginFailed(String message){
		loginFailState.setText(message);
		FadeTransition f = new FadeTransition(Duration.millis(1500),loginFailState);
		f.setFromValue(1);
		f.setToValue(0);
		f.play();
	}
}