package fff.views;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class Login_Dialog {
	
	@FXML private TextField usernameField;
	@FXML private TextField passwordField;
	@FXML private Button loginButton;
	@FXML private Text loginFailState;
	
	@FXML private void initialize(){
		loginButton.setOnAction(e->checkDetails());
	}
	
	private void checkDetails(){
		System.out.println(	usernameField.getText()+" "+
							passwordField.getText());
	}
}
