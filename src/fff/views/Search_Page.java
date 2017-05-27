package fff.views;

import fff.models.Restaurant;
import fff.util.Search;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

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
		switch (searchOptions.getValue()){
			default:
			case "Name": 		searchByName();			break;
			case "Postcode": 	searchByPostcode(); 	break;
			case "Cuisine": 	searchByCuisine();		break;
			case "Ratings": 	searchByRatings();		break;
			case "Price Range": searchByRatings(); 	break;
		}
	}
	
	private void searchByName(){
		ObservableList<Restaurant> resByName =
			Search.getSearchResultsName(_Overview_.getDatabase().getRestaurants(),searchBar.getText());
		searchResults.setItems(resByName);
		searchResults.getColumns().clear();
		
		TableColumn<Restaurant,String> resNames = new TableColumn<>("Names");
		resNames.setCellValueFactory(data->data.getValue().nameProperty());
		TableColumn<Restaurant,String> resPostcode = new TableColumn<>("Postcode");
		resPostcode.setCellValueFactory(data->data.getValue().postcodeProperty().asString());
		TableColumn<Restaurant,String> resCuisine = new TableColumn<>("Cuisine");
		resCuisine.setCellValueFactory(data->data.getValue().cuisineProperty());
		TableColumn<Restaurant,String> resRatings = new TableColumn<>("Ratings");
		resRatings.setCellValueFactory(data->data.getValue().averageRatingsProperty().asString());
		TableColumn<Restaurant,String> resPrice = new TableColumn<>("Price");
		resPrice.setCellValueFactory(data->data.getValue().priceRangeProperty().asString());
		searchResults.getColumns().addAll(resNames,resPostcode,resCuisine,resRatings,resPrice);
	}
	
	private void searchByPostcode(){
		ObservableList<Restaurant> resByName =
			Search.getSearchResultsPostcode(_Overview_.getDatabase().getRestaurants(),searchBar.getText());
		searchResults.setItems(resByName);
		searchResults.getColumns().clear();
		
		TableColumn<Restaurant,String> resNames = new TableColumn<>("Names");
		resNames.setCellValueFactory(data->data.getValue().nameProperty());
		TableColumn<Restaurant,String> resPostcode = new TableColumn<>("Postcode");
		resPostcode.setCellValueFactory(data->data.getValue().postcodeProperty().asString());
		TableColumn<Restaurant,String> resCuisine = new TableColumn<>("Cuisine");
		resCuisine.setCellValueFactory(data->data.getValue().cuisineProperty());
		TableColumn<Restaurant,String> resRatings = new TableColumn<>("Ratings");
		resRatings.setCellValueFactory(data->data.getValue().averageRatingsProperty().asString());
		TableColumn<Restaurant,String> resPrice = new TableColumn<>("Price");
		resPrice.setCellValueFactory(data->data.getValue().priceRangeProperty().asString());
		searchResults.getColumns().addAll(resPostcode,resNames,resCuisine,resRatings,resPrice);
	}
	
	private void searchByCuisine(){
		ObservableList<Restaurant> resByName =
			Search.getSearchResultsCuisine(_Overview_.getDatabase().getRestaurants(),searchBar.getText());
		searchResults.setItems(resByName);
		searchResults.getColumns().clear();
		
		TableColumn<Restaurant,String> resNames = new TableColumn<>("Names");
		resNames.setCellValueFactory(data->data.getValue().nameProperty());
		TableColumn<Restaurant,String> resPostcode = new TableColumn<>("Postcode");
		resPostcode.setCellValueFactory(data->data.getValue().postcodeProperty().asString());
		TableColumn<Restaurant,String> resCuisine = new TableColumn<>("Cuisine");
		resCuisine.setCellValueFactory(data->data.getValue().cuisineProperty());
		TableColumn<Restaurant,String> resRatings = new TableColumn<>("Ratings");
		resRatings.setCellValueFactory(data->data.getValue().averageRatingsProperty().asString());
		TableColumn<Restaurant,String> resPrice = new TableColumn<>("Price");
		resPrice.setCellValueFactory(data->data.getValue().priceRangeProperty().asString());
		searchResults.getColumns().addAll(resCuisine,resNames,resPostcode,resRatings,resPrice);
	}
	
	private void searchByRatings(){
		ObservableList<Restaurant> resByName =
			Search.getSearchResultsRatings(_Overview_.getDatabase().getRestaurants(),searchBar.getText());
		searchResults.setItems(resByName);
		searchResults.getColumns().clear();
		
		TableColumn<Restaurant,String> resNames = new TableColumn<>("Names");
		resNames.setCellValueFactory(data->data.getValue().nameProperty());
		TableColumn<Restaurant,String> resPostcode = new TableColumn<>("Postcode");
		resPostcode.setCellValueFactory(data->data.getValue().postcodeProperty().asString());
		TableColumn<Restaurant,String> resCuisine = new TableColumn<>("Cuisine");
		resCuisine.setCellValueFactory(data->data.getValue().cuisineProperty());
		TableColumn<Restaurant,String> resRatings = new TableColumn<>("Ratings");
		resRatings.setCellValueFactory(data->data.getValue().averageRatingsProperty().asString());
		TableColumn<Restaurant,String> resPrice = new TableColumn<>("Price");
		resPrice.setCellValueFactory(data->data.getValue().priceRangeProperty().asString());
		searchResults.getColumns().addAll(resRatings,resNames,resPostcode,resCuisine,resPrice);
	}
	
	private void searchByPriceRange(){
		ObservableList<Restaurant> resByName =
			Search.getSearchResultsRatings(_Overview_.getDatabase().getRestaurants(),searchBar.getText());
		searchResults.setItems(resByName);
		searchResults.getColumns().clear();
		
		TableColumn<Restaurant,String> resNames = new TableColumn<>("Names");
		resNames.setCellValueFactory(data->data.getValue().nameProperty());
		TableColumn<Restaurant,String> resPostcode = new TableColumn<>("Postcode");
		resPostcode.setCellValueFactory(data->data.getValue().postcodeProperty().asString());
		TableColumn<Restaurant,String> resCuisine = new TableColumn<>("Cuisine");
		resCuisine.setCellValueFactory(data->data.getValue().cuisineProperty());
		TableColumn<Restaurant,String> resRatings = new TableColumn<>("Ratings");
		resRatings.setCellValueFactory(data->data.getValue().averageRatingsProperty().asString());
		TableColumn<Restaurant,String> resPrice = new TableColumn<>("Price");
		resPrice.setCellValueFactory(data->data.getValue().priceRangeProperty().asString());
		searchResults.getColumns().addAll(resPrice,resNames,resPostcode,resCuisine,resRatings);
	}
	private void goBack(){
		getMain().changeCenter(getPreviousPage());
	}
}
