package chessLibrary;

import static java.lang.Math.abs;

public class King extends Chess {
	
	public King(int player, int row, int col) {
		super( player,  row,  col);
				
		if (player==1)
			this.representive='K';
		else 
			this.representive='k';
		
	}
	
	@Override
	boolean checkRules(int newRow, int newCol) {
		return (abs(newRow-row)<=1 && abs(newCol-col)<=1);
	}
	@Override
	King copy() {
		King copy = new King(player,row,col);
		copy.live = this.live;
		return copy;
	}
}

