package chessTests;

import static org.junit.Assert.*;

import org.junit.Test;

import chessLibrary.Board;


/* Test the tryMove() methods in Board class
 * it is supposed to handle following cases:
 * 		1. out of board bound
 * 		2. check leap for rook, bishop, queen.
 * 		3. for pawn, check if square occupied in different cases.
 * 		4. check if step on other pieces of same player
 * 		5. select an empty square
 * 		6. not moving
 * */
public class BoardTryMoveTests {

	
	@Test
	public void outOfBoundTests() throws Exception{
		Board board = new Board();
		
		assert(!board.tryMove(board.chessPosition[0][0], 10,5));
		assert(!board.tryMove(board.chessPosition[0][0], -1,0));

	}
	/* Test queen bishop rook cannot leap*/
	@Test
	public void leapTests() throws Exception{
		Board board = new Board();
		
		Board.printBoard(board);
		
		/* rook */
		assert(!board.tryMove(board.chessPosition[0][0], 3,0));
		/* queen */
		assert(!board.tryMove(board.chessPosition[0][3], 3,0));
		assert(!board.tryMove(board.chessPosition[0][3], 0,7));
		/* bishop */
		assert(!board.tryMove(board.chessPosition[0][2], 2,0));
	}
	
	@Test
	public void stepOnSelfTests() throws Exception{
		Board board = new Board();
		
		/* rook */
		assert(!board.tryMove(board.chessPosition[0][0], 0,1));
		/* queen */
		assert(!board.tryMove(board.chessPosition[0][3], 1,3));
		/* bishop */
		assert(!board.tryMove(board.chessPosition[0][2], 0,1));
	}
		
	
	/* Test moving rules for pawn */
	@Test
	public void pawnTests() throws Exception{
		Board board = new Board(); 
		
		/* 2 forward when first move */
		assert(board.tryMove(board.chessPosition[1][0], 3,0));
		
		/* 2 forward not first move */
		assert(!board.tryMove(board.chessPosition[1][0], 3,0));
		
		/* 1 forward when first */
		assert(board.tryMove(board.chessPosition[1][1], 2,1));
		
		/* 1 forward when not first */
		assert(board.tryMove(board.chessPosition[1][1], 2,1));
		
		/* move diagonally, but not capturing*/
		assert(!board.tryMove(board.chessPosition[1][0], 2,1));
		
		/* move diagonally and can capture piece*/
		board.chessPosition[6][1].row=2;
		board.chessPosition[2][1]=board.chessPosition[6][1];
		board.chessPosition[6][1]=null;
		Board.printBoard(board);
		assert(board.tryMove(board.chessPosition[1][0], 2,1));

	}
	
	@Test
	public void notMoving() throws Exception{
		Board board = new Board(); 
		
		assert(!board.tryMove(board.chessPosition[1][1], 1,1));	

	}
	
	/* test if input null ptr*/
	@Test
	public void pieceNotExistTest() throws Exception{
		Board board = new Board();
		assert(!board.tryMove(board.chessPosition[5][0], 3,0));
	}
	
	


	
	

}
