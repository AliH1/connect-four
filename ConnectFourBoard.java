public class ConnectFourBoard {
	//P1 refers to the Red disc P2 refers to Yellow disc
	public static final char EMPTY =' ', P1 = 'O', P2 = 'X';
	private char[][] board;

	public ConnectFourBoard(){
		for (int row = 0; row < 7; row++){
			for (int col = 0; col < 6; col++){
				this.board[row][col] = EMPTY;
			}
		}
	}
	
	public void updateBoard(char player, int rowPos, int colPos){
		if(player==P1) {
			this.board[rowPos][colPos] = P1;
		}
		else {
			this.board[rowPos][colPos] = P2;
		}
	}
	
	public int dropdownPos(int cur_row,int col){
		//invalid move so return -1 if dropdownPos is -1 then nothing will happen on board
		if(cur_row<0) {
			return -1;
		}
		if(board[cur_row][col] != P1 | board[cur_row][col] != P2) {
			return cur_row;
		}
		else {
			return dropdownPos(cur_row-1, col);
		}
	}
	
	public char findWinner(){
		return P1;
		
	}
}