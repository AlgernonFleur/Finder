package fff.views;

import fff.models.Rating;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

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
		this.rating.setRating(ratingsChoices.getValue());
		ratingApproved=true;
		cancel();
	}
	
	private void cancel(){
		Stage stage = (Stage) cancelButton.getScene().getWindow();
		stage.close();
	}
	
	public boolean isRatingApproved() {
		return ratingApproved;
	}
}
