/**
 * Capture an ConnectFour game. Includes an ConnectFourBoard which keeps track of the game state
 * and which players turn is it (Player1 or Player2 in attribute "whosTurn").
 * 
 * @author Ali Hassan
 *
 */

public class ConnectFour {
	private ConnectFourBoard board = new ConnectFourBoard();
	private char WhosTurn = ConnectFourBoard.P1; //player1's turn to begin
	
	public char getWhosTurn() {
		return WhosTurn;
	}
	
	public boolean gameOver() {
		return true;
	}
	
	public char getWinner() {
		if(gameOver()) {
			return board.findWinner();
		}
		else {
			//to represent there is no winner
			return ConnectFourBoard.EMPTY;
		}
	}
	
}
