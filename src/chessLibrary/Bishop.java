package chessLibrary;
import static java.lang.Math.abs;

public class Bishop extends Chess{
	
	
	public Bishop(int player, int row, int col) {
		super( player,  row,  col);
		if (player==1)
			this.representive='B';
		else 
			this.representive='b';
	}



	@Override
	boolean checkRules(int newRow, int newCol) {
		return abs(newCol-col)==abs(newRow-row);
	}
	@Override
	Bishop copy() {
		Bishop copy = new Bishop(player,row,col);
		copy.live = this.live;
		return copy;
	}
}
