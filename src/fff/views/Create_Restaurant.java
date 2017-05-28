package fff.views;

import fff.models.Restaurant;
import fff.models.users.Owner;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.util.Optional;

public class Create_Restaurant {
	
	@FXML private Label resID;
	@FXML private TextField newResName;
	@FXML private TextField newResPostcode;
	@FXML private TextField newResCuisine;
	
	@FXML private Button confirmButton;
	@FXML private Button cancelButton;
	
	private Restaurant newRestaurant;
	private Owner owner;
	private boolean confirmClicked;
	
	@FXML public void initialize(){
		confirmClicked = false;
		this.confirmButton.setOnAction(e->confirm());
		this.cancelButton.setOnAction(e->cancel());
		
		String ID = "D"+String.format("%05d",_Overview_.getDatabase().getRestaurants().size());
		this.newRestaurant = new Restaurant(ID,"",0,"","");
		this.resID.setText(ID);
	}
	
	private void confirm(){
		String error="";
		
		if(newResName.getText()==null||newResName.getText().length()==0) error+="Invalid Name\n";
		if(newResPostcode.getText()==null||newResPostcode.getText().length()==0) error+="Invalid Postcode\n";
		if(newResCuisine.getText()==null||newResCuisine.getText().length()==0) error+="Invalid Cuisine\n";
		
		if(error.length()==0){
			int postcode;
			try{
				postcode = Integer.parseInt(newResPostcode.getText());
				newRestaurant.setName(newResName.getText());
				newRestaurant.setPostcode(postcode);
				newRestaurant.setCuisine(newResCuisine.getText());
				newRestaurant.setOwnerID(owner.getID());
				newRestaurant.setOwnerObjectProperty(owner);
				Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
				alert.setTitle("Restaurant Creation");
				alert.setHeaderText("Confirm adding new Restaurant?");
				alert.setContentText(null);
				Optional<ButtonType> result = alert.showAndWait();
				if(result.get()==ButtonType.OK){
					owner.addRestaurant(newRestaurant);
					_Overview_.getDatabase().addRestaurant(newRestaurant);
					confirmClicked = true;
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
	
	public void setOwner(Owner owner) {
		this.owner = owner;
	}
	
	public boolean isConfirmClicked() {
		return confirmClicked;
	}
}
