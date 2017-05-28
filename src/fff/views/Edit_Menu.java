package fff.views;

import fff.App;
import fff.models.Food;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class Edit_Menu {
	@FXML private TableView<Food> foodTableView;
	@FXML private TableColumn<Food,String> foodName;
	@FXML private TableColumn<Food,String> foodPrice;
	
	@FXML private Button editFood;
	@FXML private Button removeFood;
	
	private ObservableList<Food> menu;
	
	@FXML public void initialize(){
		this.removeFood.setOnAction(e->removeSelected());
		this.editFood.setOnAction(e->editSelected());
	}
	
	public void setMenu(ObservableList<Food> menu){
		this.menu = menu;
		this.foodTableView.setItems(this.menu);
		this.foodName.setCellValueFactory(d->d.getValue().nameProperty());
		this.foodPrice.setCellValueFactory(d->d.getValue().priceProperty().asString("%2.2f"));
	}
	
	private void removeSelected(){
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.initOwner(this.editFood.getScene().getWindow());
		alert.setTitle("Confirm Removal");
		alert.setHeaderText("Are you sure you want to remove this food?");
		alert.setContentText(null);
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {
			int selected = foodTableView.getSelectionModel().getSelectedIndex();
			if (selected >= 0) foodTableView.getItems().remove(selected);
		}
	}
	
	private void editSelected(){
		if(foodTableView.getSelectionModel().getSelectedItem()!=null) {
			try {
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(
					App.class.getResource("views/Edit_Food.fxml"));
				
				Stage editFood = new Stage();
				editFood.setScene(new Scene(loader.load()));
				
				Edit_Food edit_food_ctrl = loader.getController();
				edit_food_ctrl.setFood(foodTableView.getSelectionModel().getSelectedItem());
				
				editFood.setTitle("Edit Food");
				editFood.initModality(Modality.WINDOW_MODAL);
				editFood.initOwner(this.editFood.getScene().getWindow());
				editFood.setResizable(false);
				editFood.sizeToScene();
				editFood.showAndWait();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
