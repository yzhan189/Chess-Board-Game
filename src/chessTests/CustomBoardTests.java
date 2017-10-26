package chessTests;

import static org.junit.Assert.*;

import org.junit.Test;

import chessLibrary.Board;

public class CustomBoardTests {

	// @Test
	public void BoardCstrTests() {
		Board b = new Board(1);
		Board.printBoard(b);
		assert(b.chessPosition[2][2].representive=='A');
	}
	
	/* king is in check by alice madhatter and rook*/
	@Test
	public void BoardInCheckTests() {
		Board board = new Board(1);

		board.mute = true;
		/* Give a situation of self check */
		board.setPosition(board.chessPosition[0][4], 2, 3, 0, 4);

		board.capturePiece(board.chessPosition[1][1]);
		for(int i=0;i<8;i++) {
			board.capturePiece(board.chessPosition[6][i]);
			board.capturePiece(board.chessPosition[0][i]);
		}
		board.capturePiece(board.chessPosition[2][2]);
		board.capturePiece(board.chessPosition[2][5]);

		board.move(board.chessPosition[7][7], 2, 7);

		assert(!board.detectWinner(0,1));
		assert(!board.detectWinner(1,0));
		
		board.setPosition(board.chessPosition[5][2], 5, 3, 5, 2);

		System.out.println();
		Board.printBoard(board);
		
		assert(board.detectWinner(0,1));
		assert(!board.detectWinner(1,0));

	}

}
