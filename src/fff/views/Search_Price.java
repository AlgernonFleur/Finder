package fff.views;

import fff.App;
import fff.models.Restaurant;
import fff.util.Search;
import fff.util.Sort;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;

import java.io.IOException;

public class Search_Price extends _View_ {
	
	@FXML private TextField searchMin;
	@FXML private TextField searchMax;
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
		resName.setSortable(false);
		resCuisine.setSortable(false);
		resPostcode.setSortable(false);
		resRating.setSortable(false);
		resPrice.setSortable(false);
		searchMin.setOnKeyPressed(e->{
			if(e.getCode().equals(KeyCode.ENTER))search();
		});
		searchMax.setOnKeyPressed(e->{
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
		sortOptions.setPromptText("Sort results");
		sortOptions.setOnAction(e->sort());
		resultsTableView.setOnMousePressed(e -> {
			if (e.isPrimaryButtonDown() && e.getClickCount()==2)
				showRes(resultsTableView.getSelectionModel().getSelectedItem());
		});
		backButton.setOnAction(e->getMain().changeCenter(getPreviousPage()));
	}
	
	private void search(){
		try{
			ObservableList<Restaurant> results =
				Search.getSearchResultsPrice(_Overview_.getDatabase().getRestaurants(),
					searchMin.getText(),searchMax.getText());
			if(results.size()==0){
				Alert alert = new Alert(Alert.AlertType.WARNING);
				alert.setTitle("Results Empty");
				alert.setHeaderText(null);
				alert.setContentText("Your search query returned no results!");
				alert.showAndWait();
			}else{
				resultsTableView.setItems(results);
				resName.setCellValueFactory(d->d.getValue().nameProperty());
				resCuisine.setCellValueFactory(d->d.getValue().cuisineProperty());
				resPostcode.setCellValueFactory(d->d.getValue().postcodeProperty().asString());
				resRating.setCellValueFactory(d->d.getValue().averageRatingsProperty().asString("%1.1f"));
				resPrice.setCellValueFactory(d->d.getValue().priceRangeProperty().asString("%2.2f"));
				sort();
			}
		}catch (NumberFormatException e){
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Input Error");
			alert.setHeaderText(null);
			alert.setContentText("Remove any non-numeric values from your input");
			alert.showAndWait();
		}
	}
	
	private void sort(){
		ObservableList<Restaurant> results = resultsTableView.getItems();
		switch(sortOptions.getSelectionModel().getSelectedIndex()){
			default:
			case 0: results.sort(Sort.ResNameCompAsc);break;
			case 1: results.sort(Sort.ResNameCompDes);break;
			case 2: results.sort(Sort.ResCuisineCompAsc);break;
			case 3: results.sort(Sort.ResCuisineCompDes);break;
			case 4: results.sort(Sort.ResPostcodeCompAsc);break;
			case 5: results.sort(Sort.ResPostcodeCompDes);break;
			case 6: results.sort(Sort.ResRatingCompAsc);break;
			case 7: results.sort(Sort.ResRatingCompDes);break;
			case 8: results.sort(Sort.ResPriceCompAsc);break;
			case 9: results.sort(Sort.ResPriceCompDes);break;
		}
	}
	
	private void showRes(Restaurant r){
		if(r!=null){
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(App.class.getResource("views/Res_Acc.fxml"));
			Node prevPage = getMain().getCenterPiece();
			try {
				getMain().changeCenter(loader.load());
			} catch (IOException e) {
				e.printStackTrace();
			}
			Res_Acc view = loader.getController();
			view.setMain(getMain());
			view.setPreviousPage(prevPage);
			view.setRestaurant(r);
		}
	}
}
