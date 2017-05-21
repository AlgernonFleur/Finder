package fff.views;

import fff.models.users.UserAccount;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class Login_Dialog {
	
	@FXML private TextField usernameField;
	@FXML private TextField passwordField;
	@FXML private Button loginButton;
	@FXML private Text loginFailState;
	
	@SuppressWarnings("unused")
	@FXML private void initialize(){
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
		
		UserAccount user = Data_Overview.getDatabase().getUser(username);
		if(user!=null)
			if(user.getPassword().equals(password)) System.out.println(user.getFullName());
			else loginFailState.setText("Password Invalid");
		else loginFailState.setText("Cannot find User");
	}
}
