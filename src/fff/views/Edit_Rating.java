package fff.views;

import fff.models.Rating;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.util.Optional;

public class Edit_Rating {
	
	@FXML private Label resName;
	@FXML private Label currRating;
	@FXML private ComboBox<Integer> ratingsChoices;
	@FXML private Button okButton;
	@FXML private Button cancelButton;
	
	private Rating rating;
	private boolean ratingApproved;
	
	@FXML public void initialize(){
		ObservableList<Integer> ratings = FXCollections.observableArrayList(1,2,3,4,5,6,7,8,9,10);
		this.ratingsChoices.setItems(ratings);
		this.okButton.setOnAction(e->ok());
		this.cancelButton.setOnAction(e->cancel());
		ratingApproved = false;
	}
	
	public void setRating(Rating rating) {
		this.rating = rating;
		this.resName.setText(rating.getRestaurantObjectProperty().getName());
		this.currRating.setText(String.valueOf(rating.getRating()));
		this.ratingsChoices.getSelectionModel().select(rating.getRating()-1);
	}
	
	private void ok(){
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Approve rating?");
		alert.setHeaderText(null);
		alert.setContentText("You have rated "+ratingsChoices.getValue()+" for the "+
			resName.getText()+"!\n"
			+"Are you ok with this?");
		
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK){
			this.rating.setRating(ratingsChoices.getValue());
			ratingApproved=true;
		}
		Stage stage = (Stage) cancelButton.getScene().getWindow();
		stage.close();
	}
	
	private void cancel(){
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Quit rating?");
		alert.setHeaderText(null);
		alert.setContentText("Do you wish to cancel your rating?");
		
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK){
			Stage stage = (Stage) cancelButton.getScene().getWindow();
			stage.close();
		}
	}
	
	public boolean isRatingApproved() {
		return ratingApproved;
	}
}
