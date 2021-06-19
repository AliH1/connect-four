public class ConnectFourBoard {
	//P1 refers to the Red disc P2 refers to Yellow disc

	public static final char EMPTY =' ', P1 = 'R', P2 = 'Y';
	private char[][] board;

	public ConnectFourBoard(){
		for (int row = 0; row < ConnectFour.length; row++){
			for (int col = 0; col < ConnectFour.width; col++){
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
	
	public int dropdownPos(int rowPos,int colPos){
		if(board[6][colPos] == P1 | board[6][colPos] == P2)  
			return -1;
		
		if(rowPos<0)  
			return 0;
		
		if(board[rowPos][colPos] == P1 | board[rowPos][colPos] == P2) 
			return rowPos + 1; 
		
		return dropdownPos(rowPos-1, colPos);
	}

	
	public char findWinner(){
		return P1;
		
	}

}