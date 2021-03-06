import javafx.animation.PauseTransition;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.util.Duration;


public class ConnectFourController {
	
	private ConnectFourModel model;
	private ConnectFourApp view;
	private ConnectFourAlgorithm strategy;
	
	public ConnectFourController(ConnectFourApp v) {
		model = new ConnectFourModel(new ConnectFourBoard());
		view = v;
	}
	
	public ConnectFourController(ConnectFourApp v, ConnectFourAlgorithm strategy) {
		model = new ConnectFourModel(new ConnectFourBoard());
		view = v;
		this.strategy = strategy;
	}
	
	public void moveOperation(int xpos) {
		int y = model.move(xpos/100);
		int x = xpos/100;
		if(y != -1 ) {
			GridPane grid = view.getGridPane();
		    grid.getChildren().remove(getNode(y, x, grid));
		    Shape rect = new Rectangle(100,100);
		    if(model.getWhosTurn() != ConnectFourBoard.P1) {
		    	//the model has already updated whosTurn still need to reflect it on the view (the alternatives alternatives waste memory)
		    	rect.setFill(Color.RED); 
		    	view.getWhosTurnLabel().setText("Yellows Turn");
		    	view.getWhosTurnLabel().setTextFill(Color.YELLOW);
		    }
		    else {
		    	rect.setFill(Color.YELLOW);
		    	view.getWhosTurnLabel().setText("Reds Turn");
		    	view.getWhosTurnLabel().setTextFill(Color.RED);
		    }
		    grid.add(rect, x, y);
			Shape shape = new Rectangle(100,100);
		    Circle circle = new Circle(50,50,45);
		    shape = Shape.subtract(shape, circle);
		    shape.setFill(Color.BLUE);
			grid.add(shape, x, y);
			char winner = model.getWinner();
			if(winner == 'D') {
				model.setGameOver();
				PauseTransition pause = new PauseTransition(Duration.seconds(2));
				pause.play();
				pause.setOnFinished(event ->{
					view.getWinnerLabel().setText("Game is a Draw");
					view.getWinnerLabel().setTextFill(Color.GREEN);
					view.getStage().setScene(view.getGameOverScene());
					view.getWinnerLabel().setLayoutX(235);
				});
			}
			
			if(winner == ConnectFourBoard.P1) {
				model.setGameOver();
				PauseTransition pause = new PauseTransition(Duration.seconds(2));
				pause.play();
				pause.setOnFinished(event ->{
					view.getWinnerLabel().setText("Red Player Wins");
					view.getWinnerLabel().setTextFill(Color.RED);
					view.getStage().setScene(view.getGameOverScene());
					view.getWinnerLabel().setLayoutX(230);
				});
			}
				
			if(winner == ConnectFourBoard.P2) {
				model.setGameOver();
				PauseTransition pause = new PauseTransition(Duration.seconds(2));
				pause.play();
				pause.setOnFinished(event ->{
					view.getWinnerLabel().setText("Yellow Player Wins");
					view.getWinnerLabel().setTextFill(Color.YELLOW);
					view.getStage().setScene(view.getGameOverScene());
					view.getWinnerLabel().setLayoutX(210);
				});
			}

			if(this.strategy != null) {
				cpuMoveOperation();
			}
			
		}
	}
	
	private void cpuMoveOperation(){
		int x = this.strategy.getMove(this.model.getBoard(), ConnectFourBoard.P2);
		int y = this.model.move(x);
		if(y != -1 ) {
			GridPane grid = view.getGridPane();
		    grid.getChildren().remove(getNode(y, x, grid));
		    Shape rect = new Rectangle(100,100);
		    if(model.getWhosTurn() != ConnectFourBoard.P1) {
		    	//the model has already updated whosTurn still need to reflect it on the view (the alternatives alternatives waste memory)
		    	rect.setFill(Color.RED); 
		    	view.getWhosTurnLabel().setText("Yellows Turn");
		    	view.getWhosTurnLabel().setTextFill(Color.YELLOW);
		    }
		    else {
		    	rect.setFill(Color.YELLOW);
		    	view.getWhosTurnLabel().setText("Reds Turn");
		    	view.getWhosTurnLabel().setTextFill(Color.RED);
		    }
		    grid.add(rect, x, y);
			Shape shape = new Rectangle(100,100);
		    Circle circle = new Circle(50,50,45);
		    shape = Shape.subtract(shape, circle);
		    shape.setFill(Color.BLUE);
			grid.add(shape, x, y);
			char winner = model.getWinner();
			if(winner == 'D') {
				model.setGameOver();
				PauseTransition pause = new PauseTransition(Duration.seconds(2));
				pause.play();
				pause.setOnFinished(event ->{
					view.getWinnerLabel().setText("Game is a Draw");
					view.getWinnerLabel().setTextFill(Color.GREEN);
					view.getStage().setScene(view.getGameOverScene());
					view.getWinnerLabel().setLayoutX(235);
				});
			}
			
			if(winner == ConnectFourBoard.P2) {
				model.setGameOver();
				PauseTransition pause = new PauseTransition(Duration.seconds(2));
				pause.play();
				pause.setOnFinished(event ->{
					view.getWinnerLabel().setText("Yellow Player Wins");
					view.getWinnerLabel().setTextFill(Color.YELLOW);
					view.getStage().setScene(view.getGameOverScene());
					view.getWinnerLabel().setLayoutX(210);
				});
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

	public void restartModel() {
		this.model.restart();
		
	}
	

}
