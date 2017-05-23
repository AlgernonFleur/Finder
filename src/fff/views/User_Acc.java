package fff.views;

import fff.models.users.UserAccount;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

public class User_Acc extends _View_ {
	
	@FXML private Text type;
	@FXML private Text ID;
	@FXML private Text username;
	@FXML private Text fullName;
	@FXML private Text email;
	@FXML private Button backButton;
	
	private UserAccount user;
	
	@FXML public void initialize(){
		this.backButton.setOnAction(e->goBack());
	}
	
	private void goBack(){
		getMain().changeCenter(getPreviousPage());
	}
	
	public void setUser(UserAccount user) {
		this.user = user;
		this.type.setText(this.user.getClass().getSimpleName());
		this.ID.setText(this.user.getID());
		this.username.setText(this.user.getUsername());
		this.fullName.setText(this.user.getFullName());
		this.email.setText(this.user.getEmail());
	}
}
