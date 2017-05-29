package fff;

import fff.models.Database;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import fff.views._Overview_;

import java.io.IOException;

public class App extends Application {
	
	public App(){
		try {
			Database database = new Database();
			database.readData();
			_Overview_.setDatabase(database);
			_Overview_.setUserAccount(null);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void start(Stage primaryStage) throws IOException {
		_Overview_.setStage(primaryStage);
		
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
