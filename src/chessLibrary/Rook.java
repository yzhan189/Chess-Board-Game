package chessLibrary;

public class Rook extends Chess{

	public Rook(int player, int row, int col) {
		super( player,  row,  col);
		if (player==1)
			this.representive='R';
		else 
			this.representive='r';
	}
	
	
	
	@Override
	boolean checkRules(int newRow, int newCol) {
		return (newRow==row)||(newCol==col);
	}
	
	@Override
	Rook copy() {
		Rook copy = new Rook(player,row,col);
		copy.live = this.live;
		return copy;
	}
}
