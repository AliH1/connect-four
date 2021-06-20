/**
 * Capture an ConnectFour game. Includes an ConnectFourBoard which keeps track of the game state
 * and which players turn is it (Player1 or Player2 in attribute "whosTurn").
 * 
 * @author Ali Hassan
 *
 */

public class ConnectFour {
	public static final int length = 6;
	public static final int width = 7;
	private ConnectFourBoard board = new ConnectFourBoard();
	private char WhosTurn = ConnectFourBoard.P1; //player1's turn to begin
	
	public char getWhosTurn() {
		return WhosTurn;
	}
	
	public boolean move(int colPos, char player){
		int rowPos = this.board.dropdownPos(0, colPos);
		if(rowPos == -1)
			return false;
		board.updateBoard(player, rowPos, colPos);
		return true;	
	}
	
	public char getWinner() {
		if(board.isDraw())
			return 'D'; //for draw
		return board.findWinner();

	}
	
}