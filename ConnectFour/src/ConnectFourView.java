import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;


public class ConnectFourView extends Application{
	//only nodes that the controller needs access to will be instance variables
	private Stage stage;
	private Scene playMenu, board, gameOver;
	private Label whosturnLabel;
	private GridPane grid;
	private Button PvE, PvH;
	
	@Override
	public void start(Stage stage) throws Exception {
		//menu setup
		this.stage = stage;
		Pane menuPane = new Pane();
		playMenu = new Scene(menuPane, 700, 700);
		menuPane.setStyle("-fx-background-color: lightblue");
		Image cfText = new Image("cftext.png");
		ImageView cfTextView = new ImageView(cfText);
		cfTextView.setLayoutY(0);
		cfTextView.setLayoutX(50);
		Button PvP = new Button("Player Vs Player");
		PvP.setPrefSize(300, 45);
		PvP.setLayoutY(400);
		PvP.setLayoutX(200);
		PvP.setStyle("-fx-font-weight: bold; -fx-font-size: 30; -fx-color: #0000ff");
		//below are 2 eventHandlers inorder to high the play button
		EventHandler<MouseEvent> pvpButtonHighlight = new EventHandler<MouseEvent>() { 
			
	        public void handle(MouseEvent e) { 
	        	PvP.setStyle("-fx-font-weight: bold; -fx-font-size: 30; -fx-color: #5000ff");
	        } 
	    };	
	    EventHandler<MouseEvent> pvpButtonReverse= new EventHandler<MouseEvent>() { 
	        @Override 
	        public void handle(MouseEvent e) { 
	        	PvP.setStyle("-fx-font-weight: bold; -fx-font-size: 30; -fx-color: #0000ff");
	        } 
	    };	
		PvP.addEventFilter(MouseEvent.MOUSE_ENTERED, pvpButtonHighlight);
		PvP.addEventFilter(MouseEvent.MOUSE_EXITED, pvpButtonReverse);
		
		Button PvC = new Button("Player Vs CPU");
		PvC.setPrefSize(300, 45);
		PvC.setLayoutY(500);
		PvC.setLayoutX(200);
		PvC.setStyle("-fx-font-weight: bold; -fx-font-size: 30; -fx-color: #0000ff");
		//below are 2 eventHandlers inorder to high the play button
		EventHandler<MouseEvent> pvcButtonHighlight = new EventHandler<MouseEvent>() { 
			
	        public void handle(MouseEvent e) { 
	        	PvC.setStyle("-fx-font-weight: bold; -fx-font-size: 30; -fx-color: #5000ff");
	        } 
	    };	
	    EventHandler<MouseEvent> pvcButtonReverse= new EventHandler<MouseEvent>() { 
	        @Override 
	        public void handle(MouseEvent e) { 
	        	PvC.setStyle("-fx-font-weight: bold; -fx-font-size: 30; -fx-color: #0000ff");
	        } 
	    };	
		PvC.addEventFilter(MouseEvent.MOUSE_ENTERED, pvcButtonHighlight);
		PvC.addEventFilter(MouseEvent.MOUSE_EXITED, pvcButtonReverse);
		PvP.setOnMouseClicked(click->{
			stage.setScene(board);
		});
		menuPane.getChildren().addAll(cfTextView, PvP, PvC);
		
		//setup for game scene with grid that represents the Connect Four board and upper Pane that shows 
		BorderPane border = new BorderPane();
		Pane topPane = new Pane();
		grid = new GridPane();		
		board = new Scene(border,700,700);
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
		whosturnLabel = new Label();
		whosturnLabel.setLayoutY(5);
		whosturnLabel.setLayoutX(5);
		whosturnLabel.setFont(Font.font("Arial", FontWeight.BOLD, 15));
		menuBtn.setOnAction(click->{
			stage.setScene(playMenu);
		});
		topPane.getChildren().addAll(whosturnLabel, restartBtn, menuBtn);
		
		Image arrow = new Image("arrow.png");
		ImageView arrowimageView = new ImageView(arrow);
		arrowimageView.setFitWidth(100);
		arrowimageView.setFitHeight(70);
		arrowimageView.setY(30);
		
	     //Creating the mouse event handler which will show an arrow indicating where the player is about to move
	     EventHandler<MouseEvent> arrowMouseMovedHandler = new EventHandler<MouseEvent>() { 
	        @Override 
	        public void handle(MouseEvent e) { 
	           int xpos = (int) e.getSceneX();
	           int ypos = (int) e.getSceneY();
	           if(ypos < 100) {
	        	   topPane.getChildren().remove(arrowimageView);
	           }
	           else {
	        	   int x = xpos/100;
	        	   arrowimageView.setX(0+ x*100);
	        	   if(!topPane.getChildren().contains(arrowimageView)) {
	        		   topPane.getChildren().add(arrowimageView);
	        	   }
	           }
	        } 
	    };
	    
	    //if mouse leaves the game window the arrow disappears 
	    EventHandler<MouseEvent> arrowMouseExitHandler = new EventHandler<MouseEvent>() { 
	        @Override 
	        public void handle(MouseEvent e) { 
        	   topPane.getChildren().remove(arrowimageView);
	        } 
	    };	
	    
	    board.addEventHandler(MouseEvent.MOUSE_MOVED, arrowMouseMovedHandler);
	    board.addEventFilter(MouseEvent.MOUSE_EXITED, arrowMouseExitHandler);
	         
		Image icon = new Image("connect4Icon.png");
		stage.getIcons().add(icon);
		stage.setTitle("Connect Four");
		stage.setResizable (false);
		stage.setScene(playMenu);
		stage.show();
	}
	
	
	private void makeGrid(GridPane grid) {
		 for(int i = 0; i<ConnectFourModel.width; i++) {
				for(int j = 0; j<ConnectFourModel.length; j++) {
					Shape shape = new Rectangle(100,100);
			        Circle circle = new Circle(50,50,45);
			        shape = Shape.subtract(shape, circle);
			        shape.setFill(Color.BLUE);
					grid.add(shape, i, j);
				}
			}

	}
	
	public Node getNode(final int row, final int column, GridPane gridPane) {
	    Node result = null;
	    ObservableList<Node> childrens = gridPane.getChildren();

	    for (Node node : childrens) {
	        if(gridPane.getRowIndex(node) == row && gridPane.getColumnIndex(node) == column) {
	            result = node;
	            break;
	        }
	    }

	    return result;
	}
	
	
	public Stage getStage() {
		return stage;
	}
	
	public Scene getPlayMenu() {
		return playMenu;
	}
	
	public Scene getBoard() {
		return board;
	}
	
	public Scene getGameOver() {
		return gameOver;
	}
	
	public Label getWhosTurnLabel() {
		return whosturnLabel;
	}
	
	public GridPane getGrid() {
		return grid;
	}
	
	public Button getPvEButton() {
		return PvE;
	}
	
	public Button getPvHButton() {
		return PvH;
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
