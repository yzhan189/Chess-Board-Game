package chessGUI;

import javax.swing.JButton;



/* Wrap up class for JButton:
 * store the coordinate of a JButton*/

public class MyButton extends JButton{
    int row;
    int col;
   
    public MyButton(){
    		super();
    	}
    public MyButton(int row,int col){
    		super();
    		this.row=row;
    		this.col=col;
    	}
}