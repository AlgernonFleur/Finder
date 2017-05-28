package fff.views;

import fff.models.Restaurant;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;

public class Search_Name extends _View_ {
	
	@FXML private TextField searchBar;
	@FXML private Button searchButton;
	@FXML private ComboBox<String> sortOptions;
	
	@FXML private TableView<Restaurant> resultsTableView;
	@FXML private TableColumn<Restaurant,String> resName;
	@FXML private TableColumn<Restaurant,String> resCuisine;
	@FXML private TableColumn<Restaurant,String> resPostcode;
	@FXML private TableColumn<Restaurant,String> resRating;
	@FXML private TableColumn<Restaurant,String> resPrice;
	
	@FXML private Button backButton;
	
	@FXML public void initialize(){
		searchBar.setOnKeyPressed(e->{
			if(e.getCode().equals(KeyCode.ENTER))search();
		});
		searchButton.setOnAction(e->search());
		sortOptions.setItems(FXCollections.observableArrayList(
			"Sort by Name Asc.","Sort by Name Des.",
			"Sort by Cuisine Asc.","Sort by Cuisine Des.",
			"Sort by Postcode Asc.","Sort by Postcode Des.",
			"Sort by Rating Asc.","Sort by Rating Des.",
			"Sort by Avg. Price Asc.","Sort by Avg. Price Des."
		));
		backButton.setOnAction(e->getMain().changeCenter(getPreviousPage()));
	}
	
	private void search(){
	
	}
}
