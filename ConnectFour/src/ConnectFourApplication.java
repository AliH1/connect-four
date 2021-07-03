import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
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
		
		
		Button menuBtn = new Button("Menu");
		menuBtn.setLayoutX(318);
		menuBtn.setLayoutY(5);
		menuBtn.setPrefWidth(65);
		menuBtn.setStyle("-fx-font-weight: bold;");
		
		Button restartBtn = new Button("Restart");
		restartBtn.setLayoutX(630);
		restartBtn.setLayoutY(5);
		restartBtn.setPrefWidth(65);
		restartBtn.setStyle("-fx-font-weight: bold;");
		
		Label whosTurnLabel = new Label();
		whosTurnLabel.setLayoutY(5);
		whosTurnLabel.setLayoutX(5);
		whosTurnLabel.setFont(Font.font("Arial", FontWeight.BOLD, 15));
		if(cf.getWhosTurn() == 'R') {
			whosTurnLabel.setText("Reds Turn");
			whosTurnLabel.setTextFill(Color.RED);
		}else {
			whosTurnLabel.setText("Yellows Turn");
			whosTurnLabel.setTextFill(Color.YELLOW);
		}

		topPane.getChildren().addAll(whosTurnLabel, restartBtn, menuBtn);
		
		Image arrow = new Image("arrow.png");
		ImageView imageView = new ImageView(arrow);
		imageView.setFitWidth(100);
		imageView.setFitHeight(70);
		imageView.setY(30);
		
	     //Creating the mouse event handler which will show an arrow indicating where the player is about to move
	     EventHandler<MouseEvent> arrowMouseMovedHandler = new EventHandler<MouseEvent>() { 
	        @Override 
	        public void handle(MouseEvent e) { 
	           int xpos = (int) e.getSceneX();
	           int ypos = (int) e.getSceneY();
	           if(ypos < 100) {
	        	   topPane.getChildren().remove(imageView);
	           }
	           else {
	        	   int x = xpos/100;
	        	   imageView.setX(0+ x*100);
	        	   if(!topPane.getChildren().contains(imageView)) {
	        		   topPane.getChildren().add(imageView);
	        	   }
	           }
	        } 
	    };
	    
	    //if mouse leaves the game window the arrow disappears 
	    EventHandler<MouseEvent> arrowMouseExitHandler = new EventHandler<MouseEvent>() { 
	        @Override 
	        public void handle(MouseEvent e) { 
        	    if(topPane.getChildren().contains(imageView)) {
        	    	topPane.getChildren().remove(imageView);
        	   }
	        } 
	    };	
	    
	    scene.addEventHandler(MouseEvent.MOUSE_MOVED, arrowMouseMovedHandler);
	    scene.addEventFilter(MouseEvent.MOUSE_EXITED, arrowMouseExitHandler);
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
