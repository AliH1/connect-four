import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EasyConnectFourAlgorithm implements ConnectFourAlgorithm{
	/**
	*This a custom made algorithm which covers some basic concepts of winning a connect four game, this algorithm has allot of weakness
	*but that is fine because it is made for Easy Mode in a connect four game. The biggest weakness is probably step 5 which does not account 
	*for if connecting the most discs will even give us an avenue to win the game (the 4th position might already be blocked). 
	*
	*OverView of Algorithm below:
	* 1. Check and block if other player can get 3 in a row(horizontally) yielding them a guaranteed win next turn
	* 2. Check if you can win the game 4 in a row
	* 3. Check to see if other player has 3 in a row discs so you can block there win
	* 4. occupy the middle if it is open and does not allow opposing player to win
	* 5. Move to position that connect the most discs from 3/2/1 in a row randomized while not allowing opposing player to win, 
	*    positions that connect in multiple directions will have a higher chance of being picked
	* 
	* @param  board the connect four board we are working with and using the algorithm to find a move
	* @return integer representing the column we want the piece to drop
	*/
	@Override
	public int getMove(ConnectFourBoard board, char player) {
		
		int pos = blockThreeInARowWin(board);
		if(pos != -1) 
			return pos;
		
		pos = checkWin(board, player);
		if(pos != -1)
			return pos;
		
		pos = blockFourInARowWin(board, player);
		if(pos != -1)
			return pos;
		
		pos = occupyMid(board, player);
		if(pos != -1)
			return pos;
		
		pos = getMostConnected(board, player);
		return pos;
	}
	
	private boolean validCoordinate(int row, int col) {
		return 0 <= row && row < ConnectFourModel.length && 0 <= col && col < ConnectFourModel.width;
	}
	
	
	private int blockThreeInARowWin(ConnectFourBoard board) {
		char empty = ConnectFourBoard.EMPTY;
		for (int row = 0; row < ConnectFourModel.length; row++){
			for (int col = 1; col < 6; col++){
				char player = board.getPlayer(row, col);
				if(player == ConnectFourBoard.P1 && validCoordinate(row, col+3) && player == board.getPlayer(row, col+1) && empty == board.getPlayer(row, col-1) && 
					empty == board.getPlayer(row, col+2) && empty == board.getPlayer(row, col+3)) { //horizontal right check
					if(row == 5) 
						return col+2;
					else 
						if(empty != board.getPlayer(row+1, col-1) && empty != board.getPlayer(row+1, col+2) && empty != board.getPlayer(row+1, col+3))
							return col+2;
				}					
				if(player == ConnectFourBoard.P1 && validCoordinate(row, col-3) && player == board.getPlayer(row, col-1) && empty == board.getPlayer(row, col+1) && 
					empty == board.getPlayer(row, col-2) && empty == board.getPlayer(row, col-3)) {  //horizontal left check
					if(row == 5) 
						return col-2;
					else 
						if(empty != board.getPlayer(row+1, col+1) && empty != board.getPlayer(row+1, col-2) && empty != board.getPlayer(row+1, col-3))
							return col-2;
				}	
			}
		}
		return -1;
	}
	
	private int blockFourInARowWin(ConnectFourBoard board, char player) {
		for(int col = 0; col < 7; col++) {
			if(board.checkWinnerAfter(col, ConnectFourBoard.otherPlayer(player))) 
				return col;
		}
		return -1;
	}
	
	private int checkWin(ConnectFourBoard board, char player) {
		for(int col = 0; col < 7; col++) {
			if(board.checkWinnerAfter(col, player)) 
				return col;
		}
		return -1;
	}
	
	private int getMostConnected(ConnectFourBoard board, char player) {
		 Random rand = new Random();
		 List<Integer> moves = getThreeInARow(board, player);
		 if(!moves.isEmpty()) {
			 int index = rand.nextInt(moves.size());
			 return moves.get(index);
		 }
		 moves = getTwoInARow(board, player);
		 if(!moves.isEmpty()) {
			 int index = rand.nextInt(moves.size());
			 return moves.get(index);
		 }
		 //if no 2 in a row or 3 in a row get a random column position to move in unless it yields a win for opposing player
		for(int i=0; i<7; i++) {
			int rowPos = board.dropdownPos(0, i);
			if(rowPos == -1) 
				continue;
			board.updateBoard(player, rowPos, i);
			boolean moveCondition = board.checkWinnerAfter(rowPos, ConnectFourBoard.otherPlayer(player));
			board.updateBoard(ConnectFourBoard.EMPTY, rowPos, i);
			if(!moveCondition)
				moves.add(i);
		}
		if(moves.isEmpty()) {
			for(int i=0; i<7; i++) {
				int rowPos = board.dropdownPos(0, i);
				if(rowPos == -1) 
					continue;
				moves.add(i);
			}
		}
		return moves.get(rand.nextInt(moves.size()));
	}
	
	private int occupyMid(ConnectFourBoard board, char player) {
		if(board.getPlayer(0, 3) == ConnectFourBoard.EMPTY) {
			int rowPos = board.dropdownPos(0, 3);
			if(rowPos == -1) 
				return -1;
			board.updateBoard(player, rowPos, 3);
			boolean moveCondition = !board.checkWinnerAfter(3, ConnectFourBoard.otherPlayer(player));
			board.updateBoard(ConnectFourBoard.EMPTY, rowPos, 3);
			if(moveCondition)
				return 3;
		}	
		return -1;
	}

	private List<Integer> getThreeInARow(ConnectFourBoard board, char player) {
		List<Integer> moves = new ArrayList<>();
		for (int row = 0; row < ConnectFourModel.length; row++){
			for (int col = 0; col < ConnectFourModel.width; col++){
				char playeratPos = board.getPlayer(row, col);
				if(playeratPos == ConnectFourBoard.EMPTY && board.dropdownPos(0, col) == row){
					int rowPos = board.dropdownPos(0, col);
					board.updateBoard(player, rowPos, col);
					boolean moveCondition = board.checkWinnerAfter(col, ConnectFourBoard.otherPlayer(player));
					board.updateBoard(ConnectFourBoard.EMPTY, rowPos, col);
					if(!moveCondition) {
						if(validCoordinate(row, col+2)) { //horizontal right check
							if(player == board.getPlayer(row, col+1) && player == board.getPlayer(row, col+2))
								moves.add(col);	
						}
						if(validCoordinate(row, col-2)) { //horizontal left check
							if(player == board.getPlayer(row, col-1) && player == board.getPlayer(row, col-2)) 
								moves.add(col);						
						}
						if(validCoordinate(row-2, col)) { //vertical up check
							if(player == board.getPlayer(row-1, col) && player == board.getPlayer(row-2, col))
								moves.add(col);	
						}
						if(validCoordinate(row-2, col-2)) { //upper left diagonal check
							if(player == board.getPlayer(row-1, col-1) && player == board.getPlayer(row-2, col-2))
								moves.add(col);							
						}
						if(validCoordinate(row-2, col+2)) { //upper right diagonal check
							if(player == board.getPlayer(row-1, col+1) && player == board.getPlayer(row-2, col+2))
								moves.add(col);							
						}
						if(validCoordinate(row+2, col-2)) { //lower left diagonal check 
							if(player == board.getPlayer(row+1, col-1) && player == board.getPlayer(row+2, col-2))
								moves.add(col);							
						}
						if(validCoordinate(row+2, col+2)) { //lower right diagonal check
							if(player == board.getPlayer(row+1, col+1) && player == board.getPlayer(row+2, col+2))
								moves.add(col);						
						}					
					}
				}
			}
		}
		return moves;	
	}
	
	private List<Integer> getTwoInARow(ConnectFourBoard board, char player) {
		List<Integer> moves = new ArrayList<>();
		for (int row = 0; row < ConnectFourModel.length; row++){
			for (int col = 0; col < ConnectFourModel.width; col++){
				char playeratPos = board.getPlayer(row, col);
				if(playeratPos == ConnectFourBoard.EMPTY && board.dropdownPos(0, col) == row) {
					int rowPos = board.dropdownPos(0, col);
					board.updateBoard(player, rowPos, col);
					boolean moveCondition = board.checkWinnerAfter(col, ConnectFourBoard.otherPlayer(player));
					board.updateBoard(ConnectFourBoard.EMPTY, rowPos, col);
					if(!moveCondition) {
						if(validCoordinate(row, col+1) && player == board.getPlayer(row, col+1))
							moves.add(col);	
						if(validCoordinate(row, col-1) && player == board.getPlayer(row, col-1)) 
							moves.add(col);						
						if(validCoordinate(row-1, col) && player == board.getPlayer(row-1, col))
							moves.add(col);	
						if(validCoordinate(row-1, col-1) && player == board.getPlayer(row-1, col-1))
							moves.add(col);							
						if(validCoordinate(row-1, col+1) && player == board.getPlayer(row-1, col+1))
							moves.add(col);							
						if(validCoordinate(row+1, col-1) && player == board.getPlayer(row+1, col-1))
							moves.add(col);							
						if(validCoordinate(row+1, col+1) && player == board.getPlayer(row+1, col+1))
							moves.add(col);						
					}
				}
			}
		}
		return moves;
	}

}