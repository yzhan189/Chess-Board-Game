package chessTests;

import static org.junit.Assert.*;

import org.junit.Test;

import chessLibrary.Board;
import chessLibrary.Chess;

public class BoardCaptureTests {

	@Test
	public void captureTests() {
		Board board = new Board();

		/* Give a situation of self check */
		Chess eaten = board.chessPosition[6][1];
		eaten.row=2;
		board.chessPosition[2][1]=eaten;
		board.chessPosition[6][1]=null;
		
		Board.printBoard(board);

		board.move(board.chessPosition[1][0], 2,1);	
		Board.printBoard(board);
		
		assert(!eaten.live);
		assert(eaten.row==-1&&eaten.col==-1);
		
		System.out.println();
		System.out.println();
	}

}
