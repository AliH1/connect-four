import java.util.Optional;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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


public class ConnectFourApp extends Application{	
	private ConnectFourController controller; 
	private Stage stage; 
	private Scene playMenu, board, gameOver; 
	private Label whosturnLabel, winner;
	private GridPane grid;
		
	private void makeMenu(){
		//menu setup
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
		//below are 2 eventHandlers in order to highlight the button
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
		PvP.setOnAction(click-> {
			controller = new ConnectFourController(this, "PvP");
			stage.setScene(board);
		});
		Button PvC = new Button("Player Vs CPU");
		PvC.setPrefSize(300, 45);
		PvC.setLayoutY(500);
		PvC.setLayoutX(200);
		PvC.setStyle("-fx-font-weight: bold; -fx-font-size: 30; -fx-color: #0000ff");
		//below are 2 eventHandlers in order to highlight the button
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
		menuPane.getChildren().addAll(cfTextView, PvP, PvC);
		//difficulty select scene section below promoted when Player versus CPU is clicked
		Pane difficultyselectPane = new Pane();
		Scene difficultyselectScene = new Scene(difficultyselectPane, 700, 700);
		PvC.setOnAction(click->{
			stage.setScene(difficultyselectScene);
		});
		difficultyselectPane.setStyle("-fx-background-color: lightblue");
		Button easyBtn = new Button("Easy Mode");
    	easyBtn.setStyle("-fx-font-weight: bold; -fx-font-size: 40; -fx-color: #5000ff");
    	easyBtn.setPrefSize(500, 90);
    	easyBtn.setLayoutX(100);
    	easyBtn.setLayoutY(250);
		EventHandler<MouseEvent> easyButtonHighlight = new EventHandler<MouseEvent>() { 
			
	        public void handle(MouseEvent e) { 
	        	easyBtn.setStyle("-fx-font-weight: bold; -fx-font-size: 40; -fx-color: #5000ff");
	        } 
	    };	
	    EventHandler<MouseEvent> easyButtonReverse= new EventHandler<MouseEvent>() { 
	        @Override 
	        public void handle(MouseEvent e) { 
	        	easyBtn.setStyle("-fx-font-weight: bold; -fx-font-size: 40; -fx-color: #0000ff");
	        } 
	    };	
		easyBtn.addEventFilter(MouseEvent.MOUSE_ENTERED, easyButtonHighlight);
		easyBtn.addEventFilter(MouseEvent.MOUSE_EXITED, easyButtonReverse);
	    easyBtn.setOnAction(click->{
			controller = new ConnectFourController(this, "PvE");
	    	stage.setScene(board);
	    });
		Button hardBtn = new Button("Hard Mode");
		hardBtn.setStyle("-fx-font-weight: bold; -fx-font-size: 40; -fx-color: #5000ff");
    	hardBtn.setPrefSize(500, 90);
    	hardBtn.setLayoutX(100);
    	hardBtn.setLayoutY(400);
		EventHandler<MouseEvent> hardButtonHighlight = new EventHandler<MouseEvent>() { 
			
	        public void handle(MouseEvent e) { 
	        	hardBtn.setStyle("-fx-font-weight: bold; -fx-font-size: 40; -fx-color: #5000ff");
	        } 
	    };	
	    EventHandler<MouseEvent> hardButtonReverse= new EventHandler<MouseEvent>() { 
	        @Override 
	        public void handle(MouseEvent e) { 
	        	hardBtn.setStyle("-fx-font-weight: bold; -fx-font-size: 40; -fx-color: #0000ff");
	        } 
	    };	
		hardBtn.addEventFilter(MouseEvent.MOUSE_ENTERED, hardButtonHighlight);
		hardBtn.addEventFilter(MouseEvent.MOUSE_EXITED, hardButtonReverse);
	    hardBtn.setOnAction(click->{
			controller = new ConnectFourController(this, "PvH");
	    	stage.setScene(board);
	    });
	    Image selectdiffText = new Image("selectdifficultyText.png");
		ImageView selectdifftextView = new ImageView(selectdiffText);
		selectdifftextView.setLayoutY(70);
		selectdifftextView.setLayoutX(10);
	    difficultyselectPane.getChildren().addAll(selectdifftextView, easyBtn, hardBtn);
	}
	
	private void makeBoard(){
		//setup for game scene with grid that represents the Connect Four board and upper Pane that shows 
		BorderPane border = new BorderPane();
		board = new Scene(border,700,700);
		Pane topPane = new Pane();
		grid = new GridPane();		
		border.setCenter(grid);
		border.setTop(topPane);
		topPane.setPrefHeight(100);
		grid.setPrefHeight(600);
		makeGrid(grid);
		topPane.setStyle("-fx-background-color: #005363");
		Button menuBtn = new Button("Menu");
		menuBtn.setLayoutX(315);
		menuBtn.setLayoutY(5);
		menuBtn.setPrefWidth(65);
		menuBtn.setStyle("-fx-font-weight: bold;");

		menuBtn.setOnAction(click->{
			if(this.menuConfirmation()) {
				this.makeBoard(); //reset board
				stage.setScene(playMenu);
			}
		});
		Button restartBtn = new Button("Restart");
		restartBtn.setLayoutX(630);
		restartBtn.setLayoutY(5);
		restartBtn.setPrefWidth(65);
		restartBtn.setStyle("-fx-font-weight: bold;");
		restartBtn.setOnAction(click->{
			this.restart();
		});
		whosturnLabel = new Label();
		whosturnLabel.setLayoutY(5);
		whosturnLabel.setLayoutX(5);
		whosturnLabel.setFont(Font.font("Arial", FontWeight.BOLD, 15));
		whosturnLabel.setText("Reds Turn");
		whosturnLabel.setTextFill(Color.RED);
		
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
	    EventHandler<MouseEvent> moveRequest = new EventHandler<MouseEvent>() { 
	        @Override 
	        public void handle(MouseEvent e) {
	           int xpos = (int) e.getSceneX();
        	   controller.moveOperation(xpos);
	        } 
	    };	
	    grid.addEventFilter(MouseEvent.MOUSE_CLICKED, moveRequest);
	}
	
	private void makeGameOverScreen() {
		Pane gameoverScreen = new Pane();
		gameOver = new Scene(gameoverScreen, 700, 700);
		gameoverScreen.setStyle("-fx-background-color: lightblue");
		Image gameoverText = new Image("gameoverText.png");
		ImageView gotextView = new ImageView(gameoverText);
		gotextView.setLayoutY(250);
		gotextView.setLayoutX(20);
		winner = new Label();
		winner.setLayoutY(400);
		winner.setLayoutX(230);
		winner.setFont(Font.font("Arial", FontWeight.BOLD, 30));
		Button replayBtn = new Button("Replay");
		replayBtn.setPrefSize(300,45);
		replayBtn.setStyle("-fx-font-weight: bold; -fx-font-size: 30; -fx-color: #0000ff");
		replayBtn.setLayoutX(200);
		replayBtn.setLayoutY(500);
		//below are 2 eventHandlers in order to highlight the button
		EventHandler<MouseEvent> replayButtonHighlight = new EventHandler<MouseEvent>() { 
			
	        public void handle(MouseEvent e) { 
	        	replayBtn.setStyle("-fx-font-weight: bold; -fx-font-size: 30; -fx-color: #5000ff");
	        } 
	    };	
	    EventHandler<MouseEvent> replayButtonReverse= new EventHandler<MouseEvent>() { 
	        @Override 
	        public void handle(MouseEvent e) { 
	        	replayBtn.setStyle("-fx-font-weight: bold; -fx-font-size: 30; -fx-color: #0000ff");
	        } 
	    };	
		replayBtn.addEventFilter(MouseEvent.MOUSE_ENTERED, replayButtonHighlight);
		replayBtn.addEventFilter(MouseEvent.MOUSE_EXITED, replayButtonReverse);
		replayBtn.setOnAction(click->{;
			this.replay();
		});
	
		gameoverScreen.getChildren().addAll(winner, replayBtn, gotextView);
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		this.stage = stage;
		this.makeMenu();
		this.makeBoard();   
		this.makeGameOverScreen();
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
	
	public Stage getStage() {
		return stage;
	}
	
	public Scene getPlayMenuScene() {
		return playMenu;
	}
	
	public Scene getBoardScene() {
		return board;
	}
	
	public Scene getGameOverScene() {
		return gameOver;
	}
	
	public Label getWhosTurnLabel() {
		return whosturnLabel;
	}
	
	public GridPane getGridPane() {
		return grid;
	}
		
	public Label getWinnerLabel() {
		return winner;
	}
	
	//source: https://www.youtube.com/watch?v=XXxl1iVSuaU
	private boolean menuConfirmation() {
		Alert menuConfirmation = new Alert(AlertType.CONFIRMATION);
		menuConfirmation.setHeaderText("Are you sure you want to procede to menu? Game progress will be lost.");
		menuConfirmation.setTitle("Warning!");
		Optional<ButtonType> result = menuConfirmation.showAndWait();
		if(result.isPresent() && result.get() == ButtonType.OK)
			return true;
		return false;
	}
	
	private void replay() {
		stage.close();
		try {
			this.start(stage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void restart() {
		this.makeBoard();
		stage.setScene(board);
		controller.restartModel();
	}
	public static void main(String[] args) {
		launch(args);
	}

}
