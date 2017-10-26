package chessLibrary;
import static java.lang.Math.abs;

/*
 * This is a 8x8 chess board.
 * We have player1 and player2
 * PLAYER2 gets capital letters, player1 gets lower-case letters.
 *
 * 	  
 *	   |(0)	(1)	(2)	(3)	(4)	(5)	(6)	(7)
 * 	   |a	|b	|c	|d	|e	|f	|g	|h
 * ----|---------------------------------> column#
 * (0)8|R	N	B	Q	K	B	N	R         PLAYER2		
 * (1)7|P	P	P	P	P	P	P	P
 * (2)6|
 * (3)5|
 * (4)4|
 * (5)3|
 * (6)2|p	p	p	p	p	p	p	p		  player1
 * (7)1|r	n	b	q	k	b	n	r	
 *  ---|-------------------------------
 *  	   |(0)	(1)	(2)	(3)	(4)	(5)	(6)	(7)
 *     v
 *  	 row #
 * */



public class Board {
	
	public static int BOARD_SIZE = 8;
	public boolean mute = false; //for test use
	
	/* stores each Chess obj in 2d array according to their position*/
	public Chess[][] chessPosition;
	
	/* A list of all pieces
	 * first row stores all the pieces of player1
	 * second row stores player2
	 * Never delete any element from this list.
	 * when a piece is killed, set piece.live = false*/
	public Chess[][] playerChessList;
	
	/* The column index of king in this list is 4*/
	int kingIndex = 4;	
	
	/* Player 1 goes first. (0 for player1, 1 for player 2) */
	public int turn = 0;
	
	/* Stores if king is in check
	 * [player1's king, player2's king]*/	
	boolean[] playerInCheck= {false,false};
	
	/* winner of this game, -1 if not decided yet
	 * (0 for player1, 1 for player 2)
	 * if 0 or 1 break from loop*/
	public int winningPlayer=-1;
	
	/* mode of chess board: 0 for default, 1 for custom*/
	int mode;	
	int piecesNum; //for default
	
	/* private var for set position to use set back*/
	Chess pieceNeedSetBack;
	
	/* constructor */
	public Board() {
	}
	
	public Board(Board board) {
		this();
		this.chessPosition = new Chess[BOARD_SIZE][BOARD_SIZE];
		this.playerChessList = new Chess[2][board.piecesNum];
		this.piecesNum = board.piecesNum;
		this.turn = board.turn;
		this.mode = board.mode;
		Chess temp;

		for (int i=0; i<2;i++) {
			for(int j=0; j<this.piecesNum;j++) {
				temp = board.playerChessList[i][j];
				//playerChessList stores all pieces, live or not
				this.playerChessList[i][j] = temp.copy(); //hard copy
				if (temp.live) {//chessPosition stores only live pieces
					this.chessPosition[temp.row][temp.col] = 
							this.playerChessList[i][j];
				}
				
			}
		}		
	}
	
	public Board(int mode) {
		
		this.mode = mode;
		
		chessPosition = new Chess[BOARD_SIZE][BOARD_SIZE];
		chessPosition[0][0] = new Rook  (1, 0,0);
		chessPosition[0][1] = new Knight(1, 0,1);
		chessPosition[0][2] = new Bishop(1, 0,2);
		chessPosition[0][3] = new Queen (1, 0,3);
		chessPosition[0][4] = new King  (1, 0,4);
		chessPosition[0][5] = new Bishop(1, 0,5);
		chessPosition[0][6] = new Knight(1, 0,6);
		chessPosition[0][7] = new Rook  (1, 0,7);
		chessPosition[7][0] = new Rook  (0, 7,0);
		chessPosition[7][1] = new Knight(0, 7,1);
		chessPosition[7][2] = new Bishop(0, 7,2);
		chessPosition[7][3] = new Queen (0, 7,3);
		chessPosition[7][4] = new King  (0, 7,4);
		chessPosition[7][5] = new Bishop(0, 7,5);
		chessPosition[7][6] = new Knight(0, 7,6);
		chessPosition[7][7] = new Rook  (0, 7,7);
			
		for (int i=0; i<BOARD_SIZE; i++) {
			chessPosition[1][i] = new Pawn  (1, 1,i);
			chessPosition[6][i] = new Pawn  (0, 6,i);
		}	
		
		// initialize players' chess list (create more)
		if (mode==0) {
			piecesNum = 16;
		}else {
			piecesNum = 18;
		}
		
		playerChessList = new Chess[2][piecesNum];
		
		for (int i=0;i<BOARD_SIZE;i++) {	
			playerChessList[0][i] = chessPosition[7][i];
			playerChessList[0][BOARD_SIZE+i] = chessPosition[6][i];
			playerChessList[1][i] = chessPosition[0][i];
			playerChessList[1][BOARD_SIZE+i] = chessPosition[1][i];	
		}
		

		
		/* set up custom pieces */
		if (mode==1) {
			
			chessPosition[2][2] = new Alice(1,2,2);
			chessPosition[5][2] = new Alice(0,5,2);
			
			chessPosition[5][5] = new MadHatter(0,5,5);
			chessPosition[2][5] = new MadHatter(1,2,5);
				
			playerChessList[0][16] = chessPosition[5][2];
			playerChessList[1][16] = chessPosition[2][2];
			
			playerChessList[0][17] = chessPosition[5][5];
			playerChessList[1][17] = chessPosition[2][5];
		}	

		
	}
	
	
	
	
	
	// called by each piece move
	// if movable, then call this function to set position
	public void move(Chess piece, int row, int col) {
	
		// check if old piece
		Chess capturedPiece = chessPosition[row][col];		
		if (capturedPiece!=null) {
			capturePiece(capturedPiece); // use when the original position is occupied	
		}
	//System.out.println("sdf");
		//now there's nothing on that position
		//set new position
		chessPosition[piece.row ][piece.col ]=null;
		chessPosition[row][col] = piece;
		piece.row = row;
		piece.col = col;
		
		//afterwards check if opponents in check.
		if(!mute) System.out.println("After moving, detect if opponent is in check");
		int player = piece.player;
		int opponent = 1-player;
		
		if (checkInCheck(opponent)) {			
			playerInCheck[opponent] = true; 						
		}
				
		/* Detect winner after every move*/
		if(detectWinner(player,opponent)) {
			winningPlayer = player;
		}
			
		turn = 1-turn;
		
	}

	/* used to check if there are captured piece*/
	public boolean ifCaptured(int row, int col) {
		return (chessPosition[row][col]!=null);
	}
	
	
	/* After the player's move, detect if player wins*/
	public boolean detectWinner(int player,int opponent) {
		
		/* For each chess try each square if movable
		 * If all is not, then no solution then lose*/
		for(int p=0; p<piecesNum;p++) {
			Chess curr = playerChessList[opponent][p];
			if(curr.live) {
				if(!mute) System.out.println(curr.representive+": ");
				for(int i=0;i<BOARD_SIZE;i++) {
					for(int j=0;j<BOARD_SIZE;j++) {
						if(!mute) System.out.println(curr.representive+" try " +i+","+j);
						if(tryMove(curr,i,j)) {
							return false;
						}
					}
				}
				System.out.println("============");
				System.out.println(curr.representive+": no place to move");
				System.out.println("============");
			}
			
		}
		/* Reach this point means that there is no solution for opponents.*/
		return true;
	}

	
	

	
	
	
	public boolean tryMove(Chess piece, int newRow, int newCol){ //while loop in test
		
		if(piece==null) {
			if(!mute) System.out.println("No piece selected.");
			return false;
		}
		
		char p = piece.representive;
		int row = piece.row;
		int col = piece.col;

		// 1.check if out of bound, or not move
		if (!isInBoundMove(row,col,newRow,newCol)) 
			return false;
		
		// 2.check if obey the rules
		if (!piece.checkRules(newRow,newCol)) {
			if (!mute) System.out.println(p+": Not obey moving rules.");
			return false;
		}

		// 3 for rook, bishop, queen: check if there are others on the path
		//	 for pawn that move diagonal, check if eat some one.		
		switch (p) {
			case'r':case'b':case'q':case'B':case'R':case'Q':
				if (!isNotLeap(row,col,newRow,newCol))
					return false;
				break;
			case 'p': case 'P': 	
				if (!isPawnLegal(row,col,newRow,newCol)) 
					return false;
				break;
		}
		
		
		// 4.You should not step on yourself
		if(!isNotSelfCapture(piece,newRow,newCol))
			return false;
		   
		System.out.println("before");
		printBoard(this);
		// 5.You can not put your own king in check.		
		if(!isNotSelfCheck(piece,newRow,newCol))
			return false;

		return true;
	}


	
	
	

	
	// when a piece kills another
	public void capturePiece(Chess piece) {
		if(piece==null) return;
		
		piece.live = false;

		chessPosition[piece.row ][piece.col ]=null;
		
		piece.row = -1;//remove it from the broad
		piece.col = -1;
		
		if (!mute) System.out.println("Player"+(piece.player+1)+"'s "+piece.representive+" is killed.");
	};
	
	
	
	
	/*CHECK IF IN CHECK*/
	public boolean checkInCheck(int player) {
		
		if(!mute) System.out.println("Check if Player "+(player+1)+"'s king is put in check");
		
		int kingRow = playerChessList[player][kingIndex].row;
		int kingCol = playerChessList[player][kingIndex].col;
		
		int opponent = 1-player;
				
		for (int i=0; i<piecesNum;i++) {
			Chess currPiece = playerChessList[opponent][i];
			if (currPiece==null) continue;
			
			// if opponent's live piece can capture my king
			if(currPiece.live&&tryMove(currPiece,kingRow,kingCol)) {
				if (!mute) System.out.println("Player "+(player+1)+"'s king is put in check!");
				if (!mute) System.out.println("By "+(opponent+1)+"'s "+currPiece.representive
						+" at ("+currPiece.row+","+currPiece.col+")");
				return true;

			}
		}
		return false;
	};

	
	
	
	
	/* Set the position of piece to new row and new column
	 * (used for tests to set up)*/
	public void setPosition(Chess piece, int newRow, int newCol, int currRow, int currCol) {
		piece.row = newRow;
		piece.col = newCol;
		pieceNeedSetBack = chessPosition[newRow][newCol];	
		chessPosition[newRow][newCol] = piece;		
		chessPosition[currRow][currCol] = null;	
	}
	public void setBackPosition(Chess piece, int newRow, int newCol, int currRow, int currCol) {
		piece.row = newRow;
		piece.col = newCol;
		chessPosition[newRow][newCol] = piece;		
		chessPosition[currRow][currCol] = pieceNeedSetBack;	
	}

	
	/**/
	public static void printBoard(Board board) {
		System.out.println("    (0)  (1)  (2)  (3)  (4)  (5)  (6)  (7) ");
		System.out.println("     a    b    c    d    e    f    g    h  ");
		System.out.println("   -----------------------------------------");
		for (int i=0;i<Board.BOARD_SIZE;i++) {
			System.out.print((8-i)+"  ");
			for (int j=0;j<Board.BOARD_SIZE;j++) {
				char piece;
				if (board.chessPosition[i][j]==null) piece = ' ';
				else piece = board.chessPosition[i][j].representive;
				System.out.print("| "+ piece +"  ");
			}
			System.out.println("| ("+i+")");
			System.out.println("   -----------------------------------------");
		}
	}

	
	
	
	
	/* =======================================
	 * private helper
	 * =======================================
	 * */
	
	private boolean isInBoundMove(int row, int col, int newRow, int newCol) {
		if (newRow<0||newCol<0||newRow>7||newCol>7) {
			if (!mute) System.out.println("New position out of bound.");
			return false;
		}
		if (row==newRow&&col==newCol) {
			if (!mute) System.out.println("You are not moving.");
			return false;
		}
		return true;
	}
	private boolean isNotLeap(int row, int col, int newRow, int newCol) {
	
		/* the starting and ending position 
		 * to check if there are pieces between them*/
		int start;
		int end;
		
		if(row==newRow) { //move horizontally
			start = (col < newCol) ? col : newCol;
			end = (col > newCol) ? col : newCol;
			for(int i=start+1; i<end; i++) {
				if(chessPosition[row][i]!=null) {
					if (!mute) System.out.println("Can not leap!");
					return false;
				}
			}				
		}else if(col==newCol){//move vertically
			start = (row < newRow) ? row : newRow;
			end = (row > newRow) ? row : newRow;
			for(int i=start+1; i<end; i++) {
				if(chessPosition[i][col]!=null) {
					if (!mute) System.out.println("Can not leap!");
					return false;
				}
			}
		}else { //move diagonally
			int rowIncrement = abs(newRow-row)/(newRow-row);
			int colIncrement = abs(newCol-col)/(newCol-col);
			for (int i=row+rowIncrement; i!=newRow; i+=rowIncrement) {
				for(int j=col+rowIncrement; j!=newCol; j+=colIncrement) {
					if(chessPosition[i][j]!=null) {
						if (!mute) System.out.println("Can not leap!");
						return false;
					}					
				}
				
			}
		}
		return true;
	}
	private boolean isPawnLegal(int row, int col, int newRow, int newCol) {
		if(col!=newCol) {//move diagonally
			if(chessPosition[newRow][newCol]==null)  {//but nothing to eat
				if (!mute) System.out.println("Move diagonally, but no piece to capture.");
				return false;
			}
		}else{//move forward
			if(chessPosition[newRow][newCol]!=null)  {// check new point
				if (!mute) System.out.println("Square occupied.");
				return false;
			}
			if(abs(row-newRow)==2){// if move two check the intermediate position
				if(chessPosition[(newRow+row)/2][col]!=null) {
					if (!mute) System.out.println("Square occupied.");
					return false;						
				}
			}
		}
		return true;
	}
	private boolean isNotSelfCapture(Chess piece, int newRow, int newCol) {
		Chess piece2 = chessPosition[newRow][newCol];
		if(piece2!=null && piece2.player==piece.player) {
			if (!mute) System.out.println("You cannot capture yourself.");
			return false;
		}
		return true;
	}
	private boolean isNotSelfCheck(Chess piece, int newRow, int newCol) {
		// suppose we move anf set them back later
		int row = piece.row;
		int col = piece.col;
			
		setPosition(piece, newRow, newCol, row, col);
			
		boolean inCheck = checkInCheck(piece.player);	
	
		setBackPosition(piece, row, col, newRow, newCol);	

		if(inCheck) {
			if (!mute) System.out.println("You cannot put your king in check.");
			return false;
		}
		return true;
	}
	

	
	
}




		
