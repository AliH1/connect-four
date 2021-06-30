import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBase;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class ConnectFourApplication extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		try {
			GridPane grid	= new GridPane();
			Scene scene = new Scene(grid,400,400);
			grid.setStyle("-fx-background-color: darkblue");
			stage.setScene(scene);
			stage.show();
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		launch(args);
	}

}
