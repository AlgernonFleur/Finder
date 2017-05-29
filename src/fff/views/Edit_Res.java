package fff.views;

import fff.models.Restaurant;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.util.Optional;

public class Edit_Res {
	
	@FXML private Label resID;
	@FXML private TextField newResName;
	@FXML private TextField newResPostcode;
	@FXML private TextField newResAddress;
	@FXML private TextField newResCuisine;
	
	@FXML private Button confirmButton;
	@FXML private Button cancelButton;
	
	private Restaurant restaurant;
	
	@FXML public void initialize(){
		this.confirmButton.setOnAction(e->confirm());
		this.cancelButton.setOnAction(e->cancel());
	}
	
	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
		this.resID.setText(restaurant.getID());
		this.newResName.setText(restaurant.getName());
		this.newResPostcode.setText(String.valueOf(restaurant.getPostcode()));
		this.newResAddress.setText(restaurant.getAddress());
		this.newResCuisine.setText(restaurant.getCuisine());
	}
	
	private void confirm(){
		String error="";
		
		if(newResName.getText()==null||newResName.getText().length()==0) error+="Invalid Name\n";
		if(newResPostcode.getText()==null||newResPostcode.getText().length()==0) error+="Invalid Postcode\n";
		if(newResAddress.getText()==null||newResAddress.getText().length()==0) error+="Invalid Address\n";
		if(newResCuisine.getText()==null||newResCuisine.getText().length()==0) error+="Invalid Cuisine\n";
		
		if(error.length()==0){
			int postcode;
			try{
				postcode = Integer.parseInt(newResPostcode.getText());
				Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
				alert.setTitle("Restaurant Details Editing");
				alert.setHeaderText("Confirm changing restaurant details?");
				alert.setContentText(null);
				Optional<ButtonType> result = alert.showAndWait();
				if(result.get()==ButtonType.OK){
					restaurant.setName(newResName.getText());
					restaurant.setPostcode(postcode);
					restaurant.setAddress(newResAddress.getText());
					restaurant.setCuisine(newResCuisine.getText());
					Stage stage = (Stage) cancelButton.getScene().getWindow();
					stage.close();
				}
			}catch (NumberFormatException e){
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setTitle("Input Error");
				alert.setHeaderText("Please fix input errors:");
				alert.setContentText("Postcode must be numeric-only");
				alert.showAndWait();
			}
		}else{
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Input Error");
			alert.setHeaderText("Please fix input errors:");
			alert.setContentText(error);
			alert.showAndWait();
		}
	}
	
	private void cancel(){
		Stage stage = (Stage) cancelButton.getScene().getWindow();
		stage.close();
	}
}
