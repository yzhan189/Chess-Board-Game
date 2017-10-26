package chessLibrary;
import static java.lang.Math.abs;

public class Pawn extends Chess{
	
	//if it is first move.
	boolean first = true;

	public Pawn(int player, int row, int col) {
		super( player,  row,  col);
		if (player==1)
			this.representive='P';
		else 
			this.representive='p';
	}
	
	
	
	@Override
	boolean checkRules(int newRow, int newCol) {
		
		// determine the front of the pawn
		int front = 1;
		if (player==0) front = -1;
		
		// 1.move forward
		if (newCol==col) {
			if(first) {// first time either 2 steps or 1 step
				first=false;
				if (newRow==(row+front)||(newRow==(row+front*2))) {
					// System.out.println("First step.");
					return true;
				}					
			} else {// not first, only 1 step allowed
				if (newRow==(row+front)) {
					// System.out.println("Not First step.");
					return true;	
				}
					
			}		
		}
		
		// 2.diagonal one step
		if (abs(newCol-col)==1 && newRow==(row+front)) 
			return true;
		
		// in no case above
		return false;
	}
	
	@Override
	Pawn copy() {
		Pawn copy = new Pawn(player,row,col);
		copy.first = this.first;
		copy.live = this.live;
		return copy;
	}
	
}
