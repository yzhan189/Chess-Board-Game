package chessTests;

import static org.junit.Assert.*;

import org.junit.Test;

import chessLibrary.Bishop;
import chessLibrary.Chess;
import chessLibrary.King;
import chessLibrary.Knight;
import chessLibrary.Pawn;
import chessLibrary.Queen;
import chessLibrary.Rook;


/* 
 * Test the methods and constructors in Chess class 
 * the checkRules check the basic moving rules isolately:
 * 		assume an infinite large board 
 * 		do not check leap
 * 		do not check if square occupied for pawn
 * 		do not check if step on other pieces of same player
 * 		do not check if put own king in check
 * (those cases should all be handled in Board class)
 * */

public class ChessClassTests {

	/* Test shared super constructor and rules check method in king*/
	@Test
	public void kingTests() throws Exception{
		Chess king = new King(0,1,2);
		assert(king.player==0);
		assert(king.row==1);
		assert(king.col==2);
		assert(king.live);
		assert(king.checkRulesForTest(2,3));
		assert(!king.checkRulesForTest(-1,2));
		assert(!king.checkRulesForTest(4,5));
	}
	
	/* Test rules check method in queen*/
	@Test
	public void queenTests() throws Exception{
		Chess rook = new Queen(0,1,2);
		assert(rook.checkRulesForTest(6,7));
		assert(!rook.checkRulesForTest(7,6));
		assert(rook.checkRulesForTest(7,2));
	}

	/* Test rules check method in Bishop*/
	@Test
	public void bishopTests() throws Exception{
		Chess bishop = new Bishop(0,1,2);
		assert(bishop.checkRulesForTest(6,7));
		assert(!bishop.checkRulesForTest(7,6));
		assert(!bishop.checkRulesForTest(7,2));
	}
	
	/* Test rules check method in rook*/
	@Test
	public void rookTests() throws Exception{
		Chess rook = new Rook(0,1,2);
		assert(!rook.checkRulesForTest(6,7));
		assert(!rook.checkRulesForTest(7,6));
		assert(rook.checkRulesForTest(7,2));
	}
	
	
	/* Test rules check method in knight*/
	@Test
	public void knightTests() throws Exception{
		Chess knight = new Knight(0,1,2);
		assert(knight.checkRulesForTest(0,0));
		assert(!knight.checkRulesForTest(1,0));
		assert(!knight.checkRulesForTest(2,3));
	}
	
	
	/* Test rules check method in pawn*/
	@Test
	public void pawnTests() throws Exception{
		Chess pawn = new Pawn(1,1,2);
		assert(pawn.checkRulesForTest(2,2));
		assert(!pawn.checkRulesForTest(0,2));
		assert(!pawn.checkRulesForTest(1,1));
		assert(!pawn.checkRulesForTest(0,3));
		assert(pawn.checkRulesForTest(2,3));
	}
	
	

	
	
	
}
