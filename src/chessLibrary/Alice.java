package chessLibrary;
import static java.lang.Math.abs;

public class Alice extends Chess{

	public Alice(int player, int row, int col) {
		super(player, row, col);
		if (player==1)
			this.representive='A';
		else 
			this.representive='a';
	}

	/* Move along the edge of the diamond of length 4*/
	@Override
	boolean checkRules(int newRow, int newCol) {
		return (abs(row-newRow)+abs(col-newCol)==3);
	}

	@Override
	Alice copy() {
		Alice copy = new Alice(this.player,this.row,this.col);
		copy.live = this.live;
		return copy;
	}
	
	

}
