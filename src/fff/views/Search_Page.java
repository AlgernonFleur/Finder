package fff.views;

import fff.models.Restaurant;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class Search_Page extends _View_ {
	
	@FXML private TextField searchBar;
	@FXML private Button searchButton;
	@FXML private ComboBox<String> searchOptions;
	@FXML private TableView<Restaurant> searchResults;
	@FXML private Button backButton;
	
	@FXML public void initialize(){
		this.searchBar.setPromptText("Search restaurants");
		this.searchBar.setOnKeyPressed(e-> {
			if(e.getCode().getName().equals("Enter"))search();
		});
		this.searchButton.setOnAction(e->search());
		
		ObservableList<String> options = FXCollections.observableArrayList(
			"Name","Postcode","Cuisine","Ratings","Price Range");
		this.searchOptions.setItems(options);
		this.searchOptions.getSelectionModel().selectFirst();
		this.backButton.setOnAction(e->goBack());
	}
	
	private void search(){
		if(!searchBar.getText().equals("")){
			System.out.println(searchBar.getText()+" "+searchOptions.getValue());
			
		}
	}
	
	private void goBack(){
		getMain().changeCenter(getPreviousPage());
	}
}
