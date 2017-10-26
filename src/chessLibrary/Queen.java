package chessLibrary;

import static java.lang.Math.abs;

public class Queen extends Chess{

	public Queen(int player, int row, int col) {
		super( player,  row,  col);
		if (player==1)         //Player 2 is capital
			this.representive='Q';
		else 
			this.representive='q';
	}
	
	@Override
	boolean checkRules(int newRow, int newCol) {
		return (newRow==row)||(newCol==col)||(abs(newCol-col)==abs(newRow-row));
	}
	
	@Override
	Queen copy() {
		Queen copy = new Queen(player,row,col);
		copy.live = this.live;
		return copy;
	}
}
