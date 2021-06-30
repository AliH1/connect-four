import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;


public class ConnectFourApplication extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		try {
			BorderPane border = new BorderPane();
			Scene scene = new Scene(border);
			GridPane grid = new GridPane();		
			HBox pane = new HBox();
			pane.setStyle("-fx-background-color: gray");
			for(int i = 0; i<7; i++) {
				for(int j = 0; j<6; j++) {
					Button button = new Button();
					button.setPrefWidth(90);
					button.setPrefHeight(90);
					button.setStyle("-fx-background-color: darkblue; -fx-border-color: darkblue");
					Circle circle = new Circle(40, Paint.valueOf("white"));
					button.setGraphic(circle);
					grid.add(button, i, j);
				
				}
			}
			border.setCenter(grid);
			border.setTop(pane);
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
