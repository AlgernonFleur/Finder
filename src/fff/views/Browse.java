package fff.views;

import fff.App;
import fff.models.Restaurant;
import fff.util.Sort;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.io.IOException;

public class Browse extends _View_ {
	
	@FXML private ComboBox<String> sortOptions;
	@FXML private TableView<Restaurant> restaurantTableView;
	@FXML private TableColumn<Restaurant,String> resName;
	@FXML private TableColumn<Restaurant,String> resCuisine;
	@FXML private TableColumn<Restaurant,String> resPostCode;
	@FXML private TableColumn<Restaurant,String> resRating;
	@FXML private TableColumn<Restaurant,String> resPrice;
	@FXML private Button backButton;
	
	@FXML public void initialize(){
		sortOptions.setItems(FXCollections.observableArrayList(
			"Sort by Name Asc.","Sort by Name Des.",
			"Sort by Cuisine Asc.","Sort by Cuisine Des.",
			"Sort by Postcode Asc.","Sort by Postcode Des.",
			"Sort by Rating Asc.","Sort by Rating Des.",
			"Sort by Avg. Price Asc.","Sort by Avg. Price Des."
		));
		sortOptions.setPromptText("Sort restaurants");
		sortOptions.setOnAction(e->sort());
		restaurantTableView.setItems(_Overview_.getDatabase().getRestaurants());
		resName.setCellValueFactory(d->d.getValue().nameProperty());
		resCuisine.setCellValueFactory(d->d.getValue().cuisineProperty());
		resPostCode.setCellValueFactory(d->d.getValue().postcodeProperty().asString());
		resRating.setCellValueFactory(d->d.getValue().averageRatingsProperty().asString("%1.1f"));
		resPrice.setCellValueFactory(d->d.getValue().priceRangeProperty().asString("%2.2f"));
		
		resName.setSortable(false);
		resCuisine.setSortable(false);
		resPostCode.setSortable(false);
		resRating.setSortable(false);
		resPrice.setSortable(false);
		
		backButton.setOnAction(e->getMain().changeCenter(getPreviousPage()));
		this.restaurantTableView.setOnMousePressed(e -> {
			if (e.isPrimaryButtonDown() && e.getClickCount()==2)
				showRes(restaurantTableView.getSelectionModel().getSelectedItem());
		});
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
	private void sort(){
		ObservableList<Restaurant> results = restaurantTableView.getItems();
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
}
