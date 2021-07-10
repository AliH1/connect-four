import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class ConnectFourController {
	
	private ConnectFourModel model;
	private ConnectFourApp view;
	
	public ConnectFourController(ConnectFourApp v) {
		model = new ConnectFourModel();
		view = v;
	}
	
	public void moveOperation(int xpos) {
		int y = model.move(xpos/100);
		int x = xpos/100;
		if(y != -1 ) {
			GridPane grid = view.getGrid();
		    grid.getChildren().remove(getNode(y, x, grid));
		    Shape rect = new Rectangle(100,100);
		    if(model.getWhosTurn() != 'R') {
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
	

}
