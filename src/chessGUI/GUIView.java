package chessGUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.swing.*;
import javax.swing.border.*;

import chessLibrary.Board;
import chessLibrary.Chess;

public class GUIView {

	// the outer panel
    private final JPanel window = new JPanel(new BorderLayout(0, 10));
    
    // the chess board
    private JPanel chessBoard;
    
    MyButton[][] chessBoardSquares = new MyButton[Board.BOARD_SIZE][Board.BOARD_SIZE];
    
    JMenuBar menu;
    JMenuItem classicChess;
	JMenuItem customChess;	
	JMenuItem newPlayer;
	JMenuItem showScores;
    
	// tool bar for player 1 and player 2
    JToolBar tools1;
    JToolBar tools2;
    
    // messages to display 
    public JLabel news = new JLabel("    Welcome!");
    public JLabel turns = new JLabel("    Player 1's turn.");
    
    /* some attribute to set!!*/
    final static int squareSize = 72;
    
    final Color light = new Color(250,230,200);
    final Color dark = new Color(135,0,0);
  
    final int topBorder = 0;
    final int leftBorder = 0;
    final int downBorder = 30;
    final int rightBorder = 30;
    
    final int boardLength = Board.BOARD_SIZE;
    
    ImageIcon emptyIcon = new ImageIcon(
            new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB));
    
    JLabel playerName1 = new JLabel("Player 1");
    JLabel playerName2 = new JLabel("Player 2");
    
    JButton player1;
    JButton player2;
    JButton forfeit1 = new JButton("Forfeit");
    JButton forfeit2 = new JButton("Forfeit");
    JButton restart1 = new JButton("Restart");
    JButton restart2 = new JButton("Restart");
    JButton undo1 = new JButton("Undo");
    JButton undo2 = new JButton("Undo");
    

    /* the view knows nothing about the model*/
    public GUIView() {
    		viewInit();
    }

    public void viewInit() {
    	
        /* set up the main GUI */
        window.setBorder(new EmptyBorder(topBorder, leftBorder, downBorder, rightBorder));
        window.setBackground(Color.WHITE);
 
		/* set up the board*/
        chessBoard = new JPanel(new GridLayout(boardLength+1, boardLength+1));
        window.add(chessBoard);
        chessBoard.setBackground(Color.WHITE);
        setUpBoardBackground();
        
        /* set up tool bar*/
        JPanel toolsPanel = new JPanel(new BorderLayout());
        window.add(toolsPanel,BorderLayout.EAST);       
        tools1 = new JToolBar();
        tools2 = new JToolBar();
        toolsPanel.add(tools1, BorderLayout.SOUTH);        
        toolsPanel.add(tools2, BorderLayout.NORTH); 
        
     
        setUpTools();
     
        
        /* set up menu bar*/
		menu = new JMenuBar();
		setUpMenuBar(menu);
		window.add(menu,BorderLayout.NORTH);
				
		/* set up bottom message for test*/
		JPanel bottomMessage = new JPanel();
		bottomMessage.add(turns, BorderLayout.SOUTH);
		bottomMessage.add(news, BorderLayout.NORTH);
		window.add(bottomMessage, BorderLayout.SOUTH);
		
    }
    
    
    /* create button for each square and add to JPanel chessBoard*/
    private void setUpBoardBackground() {
        /* create button for each chess board squares*/
        for (int row = 0; row < chessBoardSquares.length; row++) {
            for (int col = 0; col < chessBoardSquares[row].length; col++) {
            	
            	/* store position in it*/
            	MyButton button = new MyButton(row,col);
                button.setMargin(new Insets(0,0,0,0));
                button.setPreferredSize(new Dimension(squareSize,squareSize));
                
                	/* Set background color*/
                if (col%2 == row%2 ) {
                		button.setBackground(light);
                } else {
                		button.setBackground(dark);
                }
                button.setOpaque(true);
                button.setBorderPainted(false);

                chessBoardSquares[row][col] = button;
            }
        }
     
        // top-left is empty
        chessBoard.add(new JLabel(""));        
        // fill the top row
        for (int i = 0; i < boardLength; i++) {
            chessBoard.add(new JLabel((char)('A'+i)+"",
                    SwingConstants.CENTER));   
        }
        // fill the rest
        for (int row = 0; row < boardLength; row++) {
            for (int col = 0; col < boardLength; col++) {
            		if (col==0) {  // first column 
            			chessBoard.add(new JLabel("" + (row + 1),
                                SwingConstants.CENTER));
            		}  	 // add icon 
            		
            		chessBoard.add(chessBoardSquares[row][col]);
             
            }
        }
        
        
	}
    


    /* set up tool bar*/
	private void setUpTools() {
		
		player1 = new JButton();
		player1.add(playerName1);
		tools1.add(player1); // TODO - add functionality!
		tools1.addSeparator();
        tools1.add(forfeit1); // TODO - add functionality!
        tools1.add(restart1);
        tools1.add(undo1);
		
        player2 = new JButton();		
        player2.add(playerName2);
        tools2.add(player2); // TODO - add functionality!      
        tools2.addSeparator();
        tools2.add(forfeit2); // TODO - add functionality!
        tools2.add(restart2);
        tools2.add(undo2);
		
	}


	
	/* set up menu bar and its items*/
	private void setUpMenuBar(JMenuBar menuBar) {
		
		final JMenu newGame = new JMenu("New Game");	
		setUpNewGame(newGame);
		menuBar.add(newGame);	
		
				
		final JMenu scoreHistory = new JMenu("Score History");
		setUpScoreHistory(scoreHistory );
		menuBar.add(scoreHistory);	
		
		final JMenu rules = new JMenu("Rules");
		setUpRules( rules);
		//menuBar.add(rules);
	}
	
	/* helper for setUpMenuBar*/
	private void setUpNewGame(JMenu newGame) {
		classicChess = new JMenuItem("Classic Chess");
		customChess = new JMenuItem("Custom Chess");	
		newPlayer = new JMenuItem("Play with New Player");
		
		newGame.add(classicChess);
		newGame.add(customChess);
		newGame.addSeparator();
		newGame.add(newPlayer);
	}
	/* helper for setUpMenuBar*/
	private void setUpScoreHistory(JMenu scoreHistory ) {
	    showScores = new JMenuItem("Show Scores");
		scoreHistory.add(showScores);
	}
	/* helper for setUpMenuBar*/
	private void setUpRules(JMenu rules) {
		JMenuItem classicRule = new JMenuItem("Classic Chess");
		JMenuItem customRule = new JMenuItem("Customized Chess");
		rules.add(classicRule);
		rules.add(customRule);
	}

	
	
	
	
	
	
	
	
	
	
	/* add Listener for new game, called by controller*/
	
	public void addNewGameListener(ActionListener classic
			,ActionListener custom, ActionListener newP) {
		classicChess.addActionListener(classic);
		customChess.addActionListener(custom);	
		newPlayer.addActionListener(newP);
	}

	public void addShowScoresListener(ActionListener displayScore) {
		showScores.addActionListener(displayScore);
	}
	
	public void addToolsListener(ActionListener forfeit1L, 
			ActionListener forfeit2L, ActionListener restart,
			ActionListener undo1L, ActionListener undo2L) {
		forfeit1.addActionListener(forfeit1L);
		forfeit2.addActionListener(forfeit2L);
		restart1.addActionListener(restart);
		restart2.addActionListener(restart);
		undo1.addActionListener(undo1L);
		undo2.addActionListener(undo2L);
	}

	/* add square listener for each square*/
	public void addSquareListener(ActionListener selectPiece,MyButton button) {
		button.addActionListener(selectPiece);
	}
  

	
	/*==================used by controller======================*/
	
    /* place the pieces, used by controller*/
    public void setUpPieces(Board board) {
        for (int row = 0; row < chessBoardSquares.length; row++) {
            for (int col = 0; col < chessBoardSquares[row].length; col++) {
                MyButton button = chessBoardSquares[row][col];

                Chess piece = board.chessPosition[row][col];
                ImageIcon icon;
                if (piece==null) {  // empty if no piece
                	 	icon = emptyIcon;
                }else {
              //  	System.out.println(piece.representive+""+piece.player);
                		icon = new ImageIcon( new Icon(piece.representive+""+piece.player).image );
                }                                              
                	button.setIcon(icon);
            }
        }
    }

    /* helper function to give name for new Player*/
    public void setUpPlayerName() {
    		playerName1.setText(PlayerPair.generateName());
    		playerName2.setText(PlayerPair.generateName());			
    }   

    
    /* set up bottom turn message*/
    public void switchTurn(int player) {
    		turns.setText("    Player "+(player+1)+"'s trun. ");
    }

    /* when complete moving move the icon*/
    public void updateIcon(MyButton src, MyButton dest) {
    		ImageIcon icon = (ImageIcon) src.getIcon();
    		dest.setIcon(icon);
    		src.setIcon(emptyIcon);
    }
    
	/*==================used by controller======================*/

	

	public final JComponent getChessBoard() {
        return chessBoard;
    }

    public final JComponent getGui() {
        return window;
    }

    

}
