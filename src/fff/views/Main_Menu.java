package fff.views;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class Main_Menu {
	
	@FXML private Button searchButton;
	@FXML private TextField searchField;
	@FXML private ComboBox<String> searchCombo;
	
	@SuppressWarnings("unused")
	@FXML private void initialize(){
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
		FXMLLoader loader = new FXMLLoader();
	}
}
