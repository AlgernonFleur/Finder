package fff.views;

import fff.models.users.Admin;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

public class Admin_Acc extends _View_ {
	
	@FXML private Button backButton;
	@FXML private Text adminID;
	@FXML private Text adminUsername;
	@FXML private Text adminPassword;
	@FXML private Text adminFullName;
	@FXML private Text adminEmail;
	private Admin admin;
	
	@FXML public void initialize(){
		this.backButton.setOnAction(e->goBack());
	}
	
	private void goBack(){
		getMain().changeCenter(getPreviousPage());
	}
	
	public void setAdmin(Admin admin) {
		this.admin = admin;
		this.adminID.setText(this.admin.getID());
		this.adminUsername.setText(this.admin.getUsername());
		this.adminPassword.setText(this.admin.getPassword());
		this.adminFullName.setText(this.admin.getFullName());
		this.adminEmail.setText(this.admin.getEmail());
	}
}
