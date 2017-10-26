package chessTests;

import static org.junit.Assert.*;

import org.junit.Test;

import chessLibrary.Board;

public class PutInCheckTests {
	

	/* This test the case that the player put own king in check
	 * it should detect the case and not move*/
	@Test
	public void selfInCheckTests() throws Exception{
		Board board = new Board();
		
		/* Give a situation of self check */
		board.chessPosition[0][4].row=4;
		board.chessPosition[4][4]=board.chessPosition[0][4];
		board.chessPosition[0][4]=null;
		System.out.println();
		Board.printBoard(board);
		
		assert(!board.tryMove(board.chessPosition[4][4], 5,4));
		
		System.out.println();
		System.out.println();
	}

}
