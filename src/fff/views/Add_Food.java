package fff.views;

import fff.models.Food;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Add_Food {
	
	@FXML private TextField editName;
	@FXML private TextField editPrice;
	
	@FXML private Button confirmButton;
	@FXML private Button cancelButton;
	
	private Food food;
	private boolean confirmPressed;
	
	@FXML public void initialize(){
		this.food = new Food("", (float) 0);
		this.editName.setText(this.food.getName());
		this.editPrice.setText(String.format("%2.2f",this.food.getPrice()));
		confirmButton.setOnAction(e->confirm());
		cancelButton.setOnAction(e->cancel());
		
		confirmPressed = false;
	}
	
	private void confirm(){
		String error = "";
		
		if(editName.getText().length()==0||editName.getText()==null) error += "Invalid Name\n";
		if(	editPrice.getText().length()==0||
			editPrice.getText()==null) error += "Invalid Price\n";
		
		if(error.length()!=0){
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.initOwner(cancelButton.getScene().getWindow());
			alert.setTitle("Invalid Input");
			alert.setHeaderText("Please correct invalid fields");
			alert.setContentText(error);
			alert.showAndWait();
		}else{
			try{
				float f = Float.valueOf(editPrice.getText());
				this.food.setName(editName.getText());
				this.food.setPrice(f);
				confirmPressed = true;
				cancel();
			}catch (NumberFormatException e){
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.initOwner(cancelButton.getScene().getWindow());
				alert.setTitle("Invalid Input");
				alert.setHeaderText("Please correct invalid fields");
				alert.setContentText("Invalid Price\n");
				alert.showAndWait();
			}
		}
	}
	
	private void cancel(){
		Stage stage = (Stage) cancelButton.getScene().getWindow();
		stage.close();
	}
	
	public Food getFood() {
		return food;
	}
	
	public boolean isConfirmPressed() {
		return confirmPressed;
	}
}
