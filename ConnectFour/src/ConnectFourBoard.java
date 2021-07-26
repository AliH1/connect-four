public class ConnectFourBoard {
	//P1 refers to the Red disc P2 refers to Yellow disc

	public static final char EMPTY = 'E', P1 = 'R', P2 = 'Y';
	private char[][] board = new char[6][7];
	

	public ConnectFourBoard(){
		for (int row = 0; row < ConnectFourModel.length; row++){
			for (int col = 0; col < ConnectFourModel.width; col++){
				this.board[row][col] = EMPTY;
			}
		}
	}
	
	public void updateBoard(char player, int rowPos, int colPos){
		this.board[rowPos][colPos] = player;
	}
	
	public int dropdownPos(int rowPos,int colPos){
		if(board[0][colPos] == P1 | board[0][colPos] == P2)  
			return -1;
		
		if(rowPos>5)  
			return 5;
		
		if(board[rowPos][colPos] == P1 | board[rowPos][colPos] == P2) 
			return rowPos - 1; 
		
		return dropdownPos(rowPos+1, colPos);
	}
	
	public boolean isDraw() {
		for(int i = 0; i < 7; i++) {
			if(this.board[0][i] == EMPTY)
				return false;
		}
		return true;
	}

	public void display() {
		for (int row = 0; row < ConnectFourModel.length; row++){
			System.out.print("\n");
			for (int col = 0; col < ConnectFourModel.width; col++){
				System.out.print(board[row][col] + " ");
			}
		}
		System.out.print("\n");
	}
	
	public static char otherPlayer(char player) {
		if (player == P1)   //this function should only use P1 and P2 as inputs
			return P2;
		
		return P1;
	
	}
	
	private boolean validCoordinate(int row, int col) {
		return 0 <= row && row < ConnectFourModel.length && 0 <= col && col < ConnectFourModel.width;
	}
	
	public char findWinner(){
		for (int row = 0; row < ConnectFourModel.length; row++){
			for (int col = 0; col < ConnectFourModel.width; col++){
				char player = board[row][col];
				if(player != EMPTY) {
					if(validCoordinate(row, col+3)) { //horizontal right check
						if(player == board[row][col+1] && player == board[row][col+2] && player== board[row][col+3])
							return player;
					}
					if(validCoordinate(row, col-3)) { //horizontal left check
						if(player == board[row][col-1] && player == board[row][col-2] && player== board[row][col-3])
							return player;						
					}
					if(validCoordinate(row+3, col)) { //vertical down check
						if(player == board[row+1][col] && player == board[row+2][col] && player== board[row+3][col])
							return player;						
					}
					if(validCoordinate(row-3, col)) { //vertical up check
						if(player == board[row-1][col] && player == board[row-2][col] && player== board[row-3][col])
							return player;	
					}
					if(validCoordinate(row-3, col-3)) { //upper left diagonal check
						if(player == board[row-1][col-1] && player == board[row-2][col-2] && player== board[row-3][col-3])
							return player;						
					}
					if(validCoordinate(row-3, col+3)) { //upper right diagonal check
						if(player == board[row-1][col+1] && player == board[row-2][col+2] && player== board[row-3][col+3])
							return player;						
					}
					if(validCoordinate(row+3, col-3)) { //lower left diagonal check 
						if(player == board[row+1][col-1] && player == board[row+2][col-2] && player== board[row+3][col-3])
							return player;						
					}
					if(validCoordinate(row+3, col+3)) { //lower right diagonal check
						if(player == board[row+1][col+1] && player == board[row+2][col+2] && player== board[row+3][col+3])
							return player;						
					}					
					
				}
			}
		}
		return EMPTY;
	}
	
	public char getPlayer(int row, int col) {
		return board[row][col];
	}
	
	public boolean checkWinnerAfter(int colPos, char player) {
		boolean check = false;
		int rowPos = dropdownPos(0, colPos);
		if(rowPos == -1) 
			return false;
		updateBoard(player, rowPos, colPos);
		if(findWinner() == player) {
			check = true;
		}
		updateBoard(ConnectFourBoard.EMPTY, rowPos, colPos);
		return check;
	}

	public static void main(String[] args) {
		ConnectFourBoard CFboard = new ConnectFourBoard();
		CFboard.updateBoard(P1, CFboard.dropdownPos(0, 2), 2);
		CFboard.display();
		CFboard.updateBoard(P1, CFboard.dropdownPos(0, 2), 2);
		CFboard.updateBoard(P1, CFboard.dropdownPos(0, 2), 2);
		CFboard.display();

		CFboard.updateBoard(P2, CFboard.dropdownPos(0, 3), 3);
		CFboard.display();
		CFboard.updateBoard(P1, CFboard.dropdownPos(0, 3), 3);
		CFboard.display();
		CFboard.updateBoard(P2, CFboard.dropdownPos(0, 3), 3);
		CFboard.display();
		
		System.out.println(CFboard.findWinner());
		CFboard.updateBoard(P1, CFboard.dropdownPos(0, 2), 2);
		System.out.println(CFboard.findWinner());
	}


	
}