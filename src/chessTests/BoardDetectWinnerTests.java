package chessTests;

import static org.junit.Assert.*;

import org.junit.Test;

import chessLibrary.Board;



public class BoardDetectWinnerTests {
	
	
	@Test
	public void checkmate() throws Exception{
		Board board = new Board();
		board.mute = true;
		/* Give a situation of self check */
		board.setPosition(board.chessPosition[0][4], 2, 4, 0, 4);

		board.capturePiece(board.chessPosition[1][1]);
		for(int i=0;i<8;i++) {
			board.capturePiece(board.chessPosition[6][i]);
			board.capturePiece(board.chessPosition[0][i]);
		}
		board.move(board.chessPosition[7][0], 2, 0);
		board.move(board.chessPosition[7][7], 2, 7);
		board.setPosition(board.chessPosition[7][3], 3, 1, 7, 3);

		System.out.println();
		Board.printBoard(board);
		
		assert(board.detectWinner(0,1));
	}
	
	@Test
	public void stalemate() throws Exception{
		Board board = new Board();
		board.mute = true;
		/* Give a situation of self check */

		for(int i=0;i<8;i++) {
			board.capturePiece(board.chessPosition[6][i]);
			if (i!=4)// keep K
				board.capturePiece(board.chessPosition[0][i]);
			board.capturePiece(board.chessPosition[1][i]);
		}

		board.setPosition(board.chessPosition[7][3], 1, 1, 7, 3);
		board.setPosition(board.chessPosition[7][0], 6, 3, 7, 0);
		board.setPosition(board.chessPosition[7][7], 6, 5, 7, 7);

		System.out.println();
		Board.printBoard(board);
		
		assert(board.detectWinner(0,1));
	}

}
