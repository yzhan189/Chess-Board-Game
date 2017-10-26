package chessGUI;

import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import chessLibrary.Board;
import chessLibrary.Chess;

public class GUIController {
	
	GUIModel model;
	GUIView view;

	
	/* if a player is currently moving
	 * true if already selected a piece
	 * so the next piece chosen is the destination*/
	boolean moving = false;
	
	boolean canStartNewGame = true;
	boolean canAddNewPlayer = true;
	
	/* variable to store restart button of the requesting player
	 * or null if no restart request
	 * (since restart requires both players' consent)
	 * */ 
	JButton restartRequest;
	
	// the chosen piece to make a movement
	Chess srcPiece;
	
	// the according button of the srcPiece
	MyButton srcButton;	
	
	public GUIController() {		
		this(new GUIModel(), new GUIView());		 
	}
	
	public GUIController(GUIModel model,GUIView view) {
		
		 this.model = model;
		 this.view = view;
		 
		 initPiecesPosition();
		 initSquaresButton();	
		 initTools();
	}
	
	
	/* MENU BAR*/
	
	/* Add listener of classic Chess and custom Chess item under menu*/
	 private void initPiecesPosition(){
	    view.addNewGameListener(new classiPiecesListener()
	    		,new customPiecesListener(), new playWithNewPlayerListener());
	    view.addShowScoresListener(new displayScoreListener());
	 }

	 
	 
	 /* set up piece position for classic chess*/
	 private class classiPiecesListener implements ActionListener{
	     @Override
	     public void actionPerformed(ActionEvent e){
	    	     	 
	    	 	boardListenerHelper(0, "classic");

	     }
	 }

	 /* set up piece position for custom chess*/
	 private class customPiecesListener implements ActionListener{
	     @Override
	     public void actionPerformed(ActionEvent e){
	    	 
	    	 	boardListenerHelper(1, "my customized");
	     }
	 }
	
	 /* set up for player to choose new game*/
	 private class playWithNewPlayerListener implements ActionListener{
	     @Override
	     public void actionPerformed(ActionEvent e){
	    	 	canStartNewGame = true;
	    	 	canAddNewPlayer = true;
	    	 	view.news.setText("    Ready to start new game with new players.");
	     }
	 }

	 
	 
	 /* pop up window to display score history*/	 
	 private class displayScoreListener implements ActionListener{
	     @Override
	     public void actionPerformed(ActionEvent e){
	    	 	/* pop out window with scores*/
	    	 	JPanel popUp = new JPanel();
	    	 	JOptionPane.showMessageDialog(popUp, model.getScores());
	     }
	 }

	 
	 
	 /* helper function for set up listeners
	  * properly handle restart, forfeit, play with new player
	  * and store scores */
	 private void boardListenerHelper(int mode,String chessType) {
		 
		/*if already in a game, cannot set up*/
		if (!canStartNewGame) {
			view.news.setText(
				"    Game exists. You must forfeit or restart.");
			return; 
		}

		/* the case of forfeit 
		 * then restart with same mode*/
		if (mode==-1) {
			mode = model.mode;	
		}
		
		/* case that add new player pair*/
		if(canAddNewPlayer){ // in other case set up new player
			view.setUpPlayerName();
			model.setUpPlayerPairHistory(view.playerName1.getText()
					,view.playerName2.getText());
		}
		
		/* do set up*/
		model.initBoard(mode);
		view.setUpPieces(model.board);

		view.news.setText("    You choose "+chessType+" chess game.");

		/* done set up, cannot start new game from now*/
		canStartNewGame = false;
	}

	 
	 
	 
	 
	 
	 
	 /* TOOL BAR*/
	 private void initTools() {
		 view.addToolsListener(new forfeit1Listenner(),
				 new forfeit2Listenner(), new restartListenner(),
				 new undo1Listenner(), new undo2Listenner());
	 }
	
	 
	 /* Undo: for each player*/
	 private class undo1Listenner implements ActionListener{
	     @Override
	     public void actionPerformed(ActionEvent e){
	    	 	undoListenerHelper(0); // if it is player 1's turn 
	     }
	 }

	 private class undo2Listenner implements ActionListener{
	     @Override
	     public void actionPerformed(ActionEvent e){
	    	 	undoListenerHelper(1); // if it is player 2's turn
	     }
	 }
	
	 private void undoListenerHelper(int player) {
		 
		 if(model==null) return;
		 
		//the opponent already move -> undo not allowed
		if(model.board.turn==player) { 
			view.news.setText("    Redo Not Allowed: Opponent already move.");
		}else {
			if(model.hasHistory()) {
				model.undoMove();
				view.news.setText("    Player "+(player+1)+" Redo 1 step");
				view.setUpPieces(model.board);
				
				
			}else {
				view.news.setText("    Redo Not Allowed: This is starting point.");
			}
		}
	 }

	
	 
	 /* Forfeit: restart with same player and same game mode*/
	 private class forfeit1Listenner implements ActionListener{
	     @Override
	     public void actionPerformed(ActionEvent e){
	    	 	forfeitListenerHelper(1);
	     }
	 }
	 
	 private class forfeit2Listenner implements ActionListener{
	     @Override
	     public void actionPerformed(ActionEvent e){
	    	 	forfeitListenerHelper(0);
	     }
	 } 

	 private void forfeitListenerHelper(int player) {
		 
		 	// if board not set up yet
			if (model==null) return;
			model.incrementScore(player);
			
			// after forfeit, start new game, but play with same player
			canStartNewGame = true;
			canAddNewPlayer = false;
			
			boardListenerHelper(-1," to forfeit ");
	 }

	 
	 
	 /* Restart: same player, same game mode*/
	 private class restartListenner implements ActionListener{
		 
	     @Override
	     public void actionPerformed(ActionEvent e){
	    	 	
	    	 	// get which player request restart
	    	 	JButton currButton = (JButton) e.getSource();
	    	 	
	    	 	//no one requested before
	    	 	if (restartRequest==null) { 
	    	 		// do nothing because restart need two player
		    	 	restartRequest = currButton; 
		    	 	view.news.setText("Requested for restart.");
		    	 	
		    	// the same player have request before
	    	 	}else if (restartRequest==currButton){ 
	    	 		
	    	 		view.news.setText("You have requested, wait for other player.");
	    	 	
	    	 	}else {//the other player also restart, do restart
	    	 		
	    	 		restartRequest = null;//reset to null
	    	 		canStartNewGame = true;
	    	 		canAddNewPlayer = false;
	    	 		boardListenerHelper(-1," to restart ");
	   	 	}
	     }
	 }
	 
	 
	 /* Tools: forfeit restart undo*/
	 /* Move pieces*/
	 /* Set up action listener for each square*/


	 
	 
	 
	 /*BOARD*/
	 
	 /* Add button for each square on board*/
	 private void initSquaresButton(){
	        for (int row = 0; row < view.chessBoardSquares.length; row++) {
	            for (int col = 0; col < view.chessBoardSquares[row].length; col++) {
	            		MyButton button = view.chessBoardSquares[row][col];
	            		view.addSquareListener(new boardSquareListener(),button);
	            }
	        }	    
	 }
	 	 




	 /* handle movement*/
	 private class boardSquareListener implements ActionListener{
	     @Override
	     public void actionPerformed(ActionEvent e){
   	 
	    	 	MyButton button = (MyButton) e.getSource();
	    	 	int row = button.row;
	    	 	int col = button.col;
	    	 	
	    	 	/* if board is not initialized yet, do nothing*/
	    	 	if(model.board==null) {
	    	 		view.news.setText("    Game not started.");
	    	 		return;
	    	 	} 
	    	 	
	    	 	Chess piece = model.board.chessPosition[row][col];
	    	 	
	    	 	// if not in moving, which means the selected piece is srcPiece
	    	 	if (!moving) { 
	    	 		
	    	 		// if no piece on the selected square
	    	 		if(piece==null) { 
		    	 		view.news.setText("    No piece on selected square!");
		    	 		return;
		    	 	// the player is not in turn
		    	 	}else if (model.board.turn!=piece.player){
		    	 		view.news.setText("    It is player "
		    	 				+ (model.board.turn+1)
		    	 				+"'s turn, but the selected piece belongs to player "
		    	 				+(piece.player+1)+".");
		    	 		return;
		    	 		
		    	 	}else{ // select a piece
			    	 	view.news.setText("    "+piece.representive
			    	 			+" from player "
			    	 			+(model.board.chessPosition[row][col].player+1)
			    	 			+" is selected." );
			    	 	moving = true;
			    	 	srcPiece = piece;
			    	 	srcButton = button;
		    	 	}	
	    	 		
	    	 	}else { // in moving, the selected square is destination
		    	 		view.news.setText("in moving");

		    	 		// if movable
		    	 		if(model.board.tryMove(srcPiece, row, col)) {
   	 			
		    	 			model.doMove(srcPiece, row, col);
		    	 			
		    	 			srcPiece = null;
		    	 			view.news.setText("    Moved.");
		    	 			view.switchTurn(model.board.turn);
		    	 			view.updateIcon(srcButton,button);
		    	 			moving = false;// move complete
		    	 			
		    	 			// if someone wins
		    	 			if(model.board.winningPlayer!=-1) {
		    	 				endGameHandler(model.board.winningPlayer);
		    	 			}
		    	 		}else { // if not movable
		    	 			view.news.setText("    Not legal move.");
		    	 		}
		    	 	}    	 	
	     }
	 }
	 


	 
	 
	 /* End of Game: Same Player, same mode
	  * called when detecting some player wins*/
	public void endGameHandler(int winner) {
		view.news.setText("    Player "+(winner+1)
				+" wins!\n You can start new game.");
		model.incrementScore(winner);
		canStartNewGame = true; 
		canAddNewPlayer = false;
		// you can start new game under menu, and player wont change
		
	}
	
	
	
	
	
	
	
	
}
