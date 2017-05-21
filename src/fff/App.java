package fff;

import fff.models.Database;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import fff.views.Data_Overview;

import java.io.IOException;

public class App extends Application {
	
	public App() throws IOException {
		Database database = new Database();
		database.readData();
		Data_Overview.setDatabase(database);
		Data_Overview.setUserAccount(null);
	}
	
	@Override
	public void start(Stage primaryStage) throws IOException {
		Data_Overview.setStage(primaryStage);
		
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(
			App.class.getResource("views/Main_Menu.fxml"));
		
		primaryStage.setScene(new Scene(loader.load()));
		
		primaryStage.setTitle("Fine Food Finder");
		primaryStage.setResizable(false);
		primaryStage.sizeToScene();
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
