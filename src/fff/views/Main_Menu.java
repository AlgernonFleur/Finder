package fff.views;


import fff.App;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class Main_Menu {
	
	@FXML private BorderPane layout;
	@FXML private Text welcomeText;
	@FXML private Button accountButton;
	@FXML private Button loginButton;
	@FXML private Button homeButton;
	
	private Node centerPiece;
	
	@FXML public void initialize(){
		this.loginButton.setOnAction(e->openLoginDialog());
		this.homeButton.setOnAction(e->goHome());
		
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(App.class.getResource("views/Default_View.fxml"));
		try {
			this.centerPiece = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		_View_ view = loader.getController();
		view.setMain(this);
		view.setPreviousPage(null);
		this.layout.setCenter(centerPiece);
	}
	
	public void changeCenter(Node node){
		this.centerPiece = node;
		this.layout.setCenter(node);
	}
	
	public Node getCenterPiece() {
		return centerPiece;
	}
	
	private void goHome(){
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(App.class.getResource("views/Default_View.fxml"));
		
		try {
			this.centerPiece = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		_View_ view = loader.getController();
		view.setMain(this);
		view.setPreviousPage(null);
		this.layout.setCenter(centerPiece);
	}
	
	private void openLoginDialog(){
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(
				App.class.getResource("views/Login_Dialog.fxml"));
		
			Stage loginDialog = new Stage();
			loginDialog.setScene(new Scene(loader.load()));
			loginDialog.setTitle("Login");
			loginDialog.initModality(Modality.WINDOW_MODAL);
			loginDialog.initOwner(_Overview_.getStage());
			loginDialog.setResizable(false);
			loginDialog.sizeToScene();
			loginDialog.showAndWait();
			
			if(_Overview_.getUserAccount() != null){
				welcomeText.setText("Welcome, "+ _Overview_.getUserAccount().getFullName());
				
				loginButton.setText("Logout");
				loginButton.setOnAction(e-> logout());
				switch(_Overview_.getUserAccount().getClass().getSimpleName()){
					case "Admin":
						accountButton.setText("Admin View");
						accountButton.setOnAction(e->goToAdminView());
						break;
					default:
						accountButton.setText("View Account");
						accountButton.setOnAction(e->goToUserAccView());
						break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void goToAdminView(){
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(App.class.getResource("views/Admin_View.fxml"));
		
		try {
			this.centerPiece = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		_View_ view = loader.getController();
		view.setMain(this);
		view.setPreviousPage(null);
		this.layout.setCenter(centerPiece);
	}
	
	private void goToUserAccView(){
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(App.class.getResource("views/User_Acc.fxml"));
		Node prevPage = centerPiece;
		try {
			this.centerPiece = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		User_Acc view = loader.getController();
		view.setMain(this);
		view.setPreviousPage(prevPage);
		view.setUser(_Overview_.getUserAccount());
		this.layout.setCenter(centerPiece);
	}
	
	private void logout() {
		_Overview_.setUserAccount(null);
		welcomeText.setText("");
		accountButton.setText("New Account");
		loginButton.setText("Login");
		loginButton.setOnAction(e->openLoginDialog());
	}
}
