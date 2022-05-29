package ui;



import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application{
	private AirportController air;

	public Main() {
		air = new AirportController();
	}

	public static void main(String[] args) {
		
		launch(args);
	
	}

	public void start(Stage primaryStage) throws Exception {
		
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("view.fxml"));
		fxmlLoader.setController(air);
		Parent root = fxmlLoader.load();
		Image icon = new Image("/Image/avion.png");
		primaryStage.getIcons().add(icon);
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Airport");
		primaryStage.setResizable(false);
		primaryStage.show();
		air.initializeTableCountryOr();
		
	}
}
