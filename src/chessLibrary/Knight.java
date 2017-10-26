package chessLibrary;
import static java.lang.Math.abs;

public class Knight extends Chess{

	public Knight(int player, int row, int col) {
		super( player,  row,  col);
		if (player==1)
			this.representive='N';
		else 
			this.representive='n';
	}	
	
	
	@Override
	boolean checkRules(int newRow, int newCol) {
		return ( (abs(newRow-row)==1 && abs(newCol-col)==2) 
				||(abs(newRow-row)==2 && abs(newCol-col)==1));
	}
	@Override
	Knight copy() {
		Knight copy = new Knight(player,row,col);
		copy.live = this.live;
		return copy;
	}
	
}
