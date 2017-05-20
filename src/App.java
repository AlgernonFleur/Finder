import models.Database;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {
	
	Database database;
	Stage stage;
	
	public App() throws IOException {
		this.database = new Database();
		this.database.readData();
	}
	
	@Override
	public void start(Stage primaryStage) throws IOException {
		this.stage = primaryStage;
		
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(App.class.getResource("views/Main_Menu.fxml"));
		
		stage.setScene(new Scene(loader.load()));
		
		stage.setTitle("Fine Food Finder");
		stage.setResizable(false);
		stage.sizeToScene();
		stage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
