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
	private ConnectFourBoard board = new ConnectFourBoard();
	private char WhosTurn = ConnectFourBoard.P1; //player1's turn to begin
	
	public char getWhosTurn() {
		return this.WhosTurn;
	}
	
	public boolean move(int colPos, char player){
		int rowPos = this.board.dropdownPos(0, colPos);
		if(rowPos == -1)
			return false;
		board.updateBoard(player, rowPos, colPos);
		this.WhosTurn = ConnectFourBoard.otherPlayer(this.WhosTurn);
		return true;	
	}
	
	public char getWinner() {
		char winner = board.findWinner();
		if(winner == ConnectFourBoard.EMPTY) {
			if(board.isDraw()) 
				return 'D';
		}
		return winner;
	}
	
}