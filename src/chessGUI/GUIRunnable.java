package chessGUI;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import chessLibrary.Board;


public class GUIRunnable {
	
    public static void main(String[] args) {
    	
		GUIController controller = new GUIController();
		
		Runnable r = new Runnable() {

			@Override
			public void run() {
        			// create frame for my view
            		JFrame frame = new JFrame("Chess Game");
            		frame.add(controller.view.getGui());          
            
           		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            		frame.setLocationByPlatform(true);

            		// the frame is the minimum size 
            		frame.pack();
            		frame.setMinimumSize(frame.getSize());
            		frame.setVisible(true);
        		}
		};
		SwingUtilities.invokeLater(r);
    }


}
