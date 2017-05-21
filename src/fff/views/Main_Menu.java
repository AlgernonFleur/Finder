package fff.views;


import fff.App;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class Main_Menu {
	
	@FXML private Text welcomeText;
	@FXML private Button accountButton;
	@FXML private Button loginButton;
	@FXML private Button searchButton;
	@FXML private TextField searchField;
	@FXML private ComboBox<String> searchCombo;
	
	@SuppressWarnings("unused")
	@FXML private void initialize(){
		this.loginButton.setOnAction(e->openLoginDialog());
		this.searchButton.setOnAction(e->searchButtonAction());
		this.searchField.setPromptText("Search restaurants");
		
		ObservableList<String> options = FXCollections.observableArrayList(
			"Name","Postcode","Cuisine","Ratings","Price Range");
		this.searchCombo.setItems(options);
		this.searchCombo.getSelectionModel().selectFirst();
	}
	
	private void searchButtonAction(){
		if(!searchField.getText().equals("")){
			System.out.println(searchField.getText()+" "+searchCombo.getValue());
		}
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
			
			Login_Dialog dialog = loader.getController();
			dialog.setDialog(loginDialog);
			
			loginDialog.showAndWait();
			
			if(!_Overview_.getUserAccount().equals(null)){
				welcomeText.setText("Welcome, "+ _Overview_.getUserAccount().getFullName());
				accountButton.setText("View Account");
				loginButton.setText("Logout");
				loginButton.setOnAction(e-> logout());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void logout() {
		_Overview_.setUserAccount(null);
		welcomeText.setText("");
		accountButton.setText("New Account");
		loginButton.setText("Login");
		loginButton.setOnAction(e->openLoginDialog());
	}
}
