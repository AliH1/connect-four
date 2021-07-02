import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;


public class ConnectFourApplication extends Application {
	
	private int ROWS = ConnectFour.length;
	private int COLS = ConnectFour.width;
	@Override
	public void start(Stage stage) throws Exception {
		//Model
		ConnectFour cf = new ConnectFour();
		
		BorderPane border = new BorderPane();
		Pane topPane = new Pane();
		GridPane grid = new GridPane();	
		
		Scene scene = new Scene(border,700,700);
		
		scene.setOnMouseDragEntered(null);
		
		border.setCenter(grid);
		border.setTop(topPane);
		
		topPane.setPrefHeight(100);
		grid.setPrefHeight(600);
		
		makeGrid(grid);
		
		topPane.setStyle("-fx-background-color: #f0f0f0");
		
		Image arrow = new Image("arrow.png");
		ImageView imageView = new ImageView(arrow);
		
		imageView.setX(0);
		imageView.setY(30);
		imageView.setFitWidth(100);
		imageView.setFitHeight(70);
		
		topPane.getChildren().add(imageView);
	
		
		Image icon = new Image("connect4Icon.png");
		stage.getIcons().add(icon);
		stage.setTitle("Connect Four");
		stage.setResizable (false);
		stage.setScene(scene);
		stage.show();
	}
	
	
	private void makeGrid(GridPane grid) {
		 for(int i = 0; i<COLS; i++) {
				for(int j = 0; j<ROWS; j++) {
					Shape shape = new Rectangle(100,100);
			        Circle circle = new Circle(50,50,45);
			        circle.setFill(Color.BLACK);
			        shape = Shape.subtract(shape, circle);
			        shape.setFill(Color.BLUE);
					grid.add(shape, i, j);
				}
			}

	}
	
	
	public static void main(String[] args) {
		launch(args);
	}

}
