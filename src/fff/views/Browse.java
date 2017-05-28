package fff.views;

import fff.App;
import fff.models.Restaurant;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.io.IOException;

public class Browse extends _View_ {
	
	@FXML private TableView<Restaurant> restaurantTableView;
	@FXML private TableColumn<Restaurant,String> resName;
	@FXML private TableColumn<Restaurant,String> resCuisine;
	@FXML private TableColumn<Restaurant,String> resPostCode;
	@FXML private TableColumn<Restaurant,String> resRating;
	@FXML private TableColumn<Restaurant,String> resPrice;
	@FXML private Button backButton;
	
	@FXML public void initialize(){
		restaurantTableView.setItems(_Overview_.getDatabase().getRestaurants());
		resName.setCellValueFactory(d->d.getValue().nameProperty());
		resCuisine.setCellValueFactory(d->d.getValue().cuisineProperty());
		resPostCode.setCellValueFactory(d->d.getValue().postcodeProperty().asString());
		resRating.setCellValueFactory(d->d.getValue().averageRatingsProperty().asString("%1.1f"));
		resPrice.setCellValueFactory(d->d.getValue().priceRangeProperty().asString("%2.2f"));
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
}
