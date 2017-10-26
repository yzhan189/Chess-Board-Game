package chessTests;

import static org.junit.Assert.*;

import org.junit.Test;

import chessLibrary.Board;
import chessLibrary.Chess;

/* Test if the board is set up correctly with initial pieces in correct position. */
public class BoardCstrTests {
	Board board = new Board();
	
	/* Print the initial position of pieces to test chessPosition. */
	@Test
	public void initialPosition() throws Exception{	
		assert(board.chessPosition[5][5]==null);
		Board.printBoard(board);		
	}
	
	/* Test playerChessList */
	@Test
	public void playerChessListTests() throws Exception{	
		Chess[][] l = board.playerChessList;
		assert(l[0][6].player==0);
		assert(l[1][15].player==1);
		assert(l[0][4].representive=='k');
		assert(l[1][14].representive=='P');
		
	}	
	
	

}
