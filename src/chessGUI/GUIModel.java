package chessGUI;

import java.util.Iterator;
import java.util.Map;
import java.util.Stack;
import java.util.Vector;

import chessLibrary.Board;
import chessLibrary.Chess;

public class GUIModel {
	
	public Board board;
	
	/* 0 for classic
	 * 1 for customized*/
	int mode;
	
	/* Store board history for each step*/
	protected Stack<Board> boardHistory;
	
	/* Store score history for each pair of players*/
	protected Vector<PlayerPair> playerPairs;
	
	/* when model is constructed, NO obj set!*/
	public GUIModel() {
		playerPairs = new Vector<PlayerPair>();
		boardHistory = new Stack<Board>();
	}
	
	/* initialize board*/
	public void initBoard(int mode) {

		 board = new Board(mode);

		this.mode = mode;
	}

	/* initialize player pair score history*/
	public void setUpPlayerPairHistory(String player1, String player2) {

		PlayerPair playerPair = new PlayerPair(player1,player2);
		playerPairs.addElement(playerPair);
		
	}
	
	/* returns string of score history, used by listener*/
	public String getScores() {
		return PlayerPair.getScores(playerPairs);
	}

	/* increment score for winner*/
	public void incrementScore(int winner) {
		
		PlayerPair playerPair = playerPairs.lastElement();
		/* winner is -1 for tie*/
		playerPair.incrementScore(winner);
		
	}
	
	/* check legal move*/
	public boolean isMovable(Chess srcPiece, int row, int col) {
		return board.tryMove(srcPiece, row, col);
	}
	
	public void doMove(Chess srcPiece, int row, int col) {
		
		//make a deep copy 
		Board copy = new Board(board);		
		//store the history	
		boardHistory.push(copy); 
		board.move(srcPiece, row, col);

	}
		
	public void undoMove() {	
		if(boardHistory.isEmpty()) return;		
		board = boardHistory.pop();
	//	Board.printBoard(board);
	}
	
	public boolean hasHistory() {
		// if stack is empty, no history stored
		return !(boardHistory.isEmpty());
	}
	
}
