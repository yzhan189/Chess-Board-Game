package chessLibrary;

public abstract class Chess {
	
	public int row; //row position
	public int col; //col position
	
	// TODO: private and getter
	public boolean live; // is it still live or been killed
	public int player; // belongs to which player, 0(1) or 1(2)
	
	public char representive;
	
	//constructor 
	//for different kind of chess to further implement 
	public Chess(int player, int row, int col) {
		this.row = row;
		this.col = col;
		this.player = player;
		live = true;
	}; 
	
	abstract boolean checkRules(int newRow, int newCol);
	abstract Chess copy();
	
	public boolean checkRulesForTest(int newRow, int newCol) {
		return checkRules( newRow,  newCol);
	}
}

