package GUITests;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import org.junit.Test;

import chessGUI.GUIController;
import chessGUI.GUIView;
import chessLibrary.Board;

public class ViewTESTs {
	@Test
	public void viewSetUpTest() {
		
		GUIView view = new GUIView();
		Runnable r = new Runnable() {

			@Override
			public void run() {
        			// create frame for my view
            		JFrame frame = new JFrame("Chess Game");
            		frame.add(view.getGui());          
            
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
	
	@Test
	public void controllerTest() {
		GUIController controller = new GUIController();
	}
}
