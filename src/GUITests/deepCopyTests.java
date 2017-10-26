package GUITests;

import static org.junit.Assert.*;

import org.junit.Test;

import chessGUI.GUIModel;
import chessLibrary.Board;

public class deepCopyTests {

	@Test
	public void boardDeepCopyTest() {
		
		Board b1 = new Board(1);
		// a eat A
		b1.tryMove(b1.chessPosition[5][2], 2, 2);
		// P eat a
		//b1.move(b1.chessPosition[1][1], 2, 2);
		// undo, P get back , a get Back
		
		// find alice in b1 b2
		System.out.println(b1.playerChessList[0][16].representive+
				" "+b1.playerChessList[0][16].live);
		System.out.println(b1.playerChessList[1][16].representive+
				" "+b1.playerChessList[1][16].live);
		
		
		Board b2 = new Board(b1);
		
		System.out.println(b2.playerChessList[0][16].representive+
				" "+b1.playerChessList[0][16].live);
		System.out.println(b2.playerChessList[1][16].representive+
				" "+b1.playerChessList[1][16].live);

		
		
		Board.printBoard(b1);
		Board.printBoard(b2);
	}
	
	
	
	@Test
	public void modelUndoTest() {
		GUIModel model = new GUIModel();
		model.initBoard(1);
		Board b1 = model.board;

		model.doMove(b1.chessPosition[5][2], 2, 2);
		model.doMove(b1.chessPosition[1][1], 2, 2);
		model.doMove(b1.chessPosition[5][5], 2, 2);
		model.undoMove();
		model.undoMove();
		Board.printBoard(b1);
		Board.printBoard(model.board);

		System.out.println(b1.playerChessList[0][16].representive+
				" "+b1.playerChessList[0][16].live);
		System.out.println(b1.playerChessList[1][16].representive+
				" "+b1.playerChessList[1][16].live);
		
		
		System.out.println(model.board.playerChessList[0][16].representive+
				" "+model.board.playerChessList[0][16].live);
		System.out.println(model.board.playerChessList[1][16].representive+
				" "+model.board.playerChessList[1][16].live);
		
		System.out.println(model.board.playerChessList[0][17].representive+
				" "+model.board.playerChessList[0][17].live);
	
		
	}

}
