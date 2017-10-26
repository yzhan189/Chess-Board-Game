package chessLibrary;

import static java.lang.Math.abs;

public class MadHatter extends Chess{

	public MadHatter(int player, int row, int col) {
		super(player, row, col);
		if (player==1)
			this.representive='H';
		else 
			this.representive='h';
	}

	@Override
	/* Can only exist on squares along its own diagonal
	 * it can leap over others*/
	boolean checkRules(int newRow, int newCol) {
		return (newRow==newCol&&player==0)
				||((newRow+newCol)==7&&player==1);
	}

	@Override
	MadHatter copy() {
		MadHatter copy = new MadHatter(player,row,col);
		copy.live = this.live;
		return copy;
	}
}
