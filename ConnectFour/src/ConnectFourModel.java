/**
 * Capture an ConnectFour game. Includes an ConnectFourBoard which keeps track of the game state
 * and which players turn is it (Player1 or Player2 in attribute "whosTurn").
 * 
 * @author Ali Hassan
 *
 */

public class ConnectFourModel {
	public static final int length = 6;
	public static final int width = 7;
	private ConnectFourBoard board;
	private char whosTurn = ConnectFourBoard.P1; //player1's turn to begin
	boolean gameOver = false;
	
	public ConnectFourModel(ConnectFourBoard board){
		this.board = board;
	}
	public char getWhosTurn() {
		return this.whosTurn;
	}
	
	public int move(int colPos){
		if(gameOver) {
			return -1;
		}
		int rowPos = this.board.dropdownPos(0, colPos);
		if(rowPos != -1) {
			board.updateBoard(whosTurn, rowPos, colPos);
			this.whosTurn = ConnectFourBoard.otherPlayer(this.whosTurn);
		}
		return rowPos;	
	}
	
	public char getWinner() {
		char winner = board.findWinner();
		if(winner == ConnectFourBoard.EMPTY) {
			if(board.isDraw()) 
				return 'D';
		}
		return winner;
	}
	
	public void restart() {
		this.board = new ConnectFourBoard();
		this.whosTurn = ConnectFourBoard.P1;
	}
	
	public ConnectFourBoard getBoard() {
		return this.board;
	}
	public void setGameOver() {
		gameOver = true;
		
	}
	
}