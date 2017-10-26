package chessTests;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.junit.Test;

import chessLibrary.Chess;
import chessLibrary.Board;
 
/* This is a game loop simulating the user end.
 * The loop continues until one player wins*/
public class userEnd {

	@Test
	public void test() throws Exception {
		
		Board b = new Board();
		Board.printBoard(b);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int row;
		int col;
		int newRow;
		int newCol;
		Chess p;
		while(b.winningPlayer==-1) {
			newRow=-1;
			newCol=-1;
					
			do {
				System.out.println("\nPlayer "+(b.turn+1)+"'s turn");
				System.out.print("Choose a piece: \n    enter row:");
				row = Integer.parseInt(br.readLine());
				System.out.print("    enter column: ");
				col = Integer.parseInt(br.readLine());
				p = b.chessPosition[row][col];
				System.out.println("Chosen piece is "+p.representive
						+" from player " +(p.player+1));
				
				/* The chosen piece must belong to the player in turn*/
				if (p.player!=b.turn) {				
					continue;
				}
				
				System.out.print("Move to new position: \n    enter new row:");
				newRow = Integer.parseInt(br.readLine());
				System.out.print("    enter new column: ");
				newCol = Integer.parseInt(br.readLine());
				
				/* The new position must be legal*/
			} while(!b.tryMove(p, newRow, newCol));
			

			b.move(p,newRow,newCol);
			
			Board.printBoard(b);
		}
		System.out.println("Player "+(b.winningPlayer+1)+" wins!");
	}

}
