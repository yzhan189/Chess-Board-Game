package chessGUI;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


/* A class to create icon for each piece*/
public class Icon {
	
	BufferedImage image;
	
	final int squareSize = GUIView.squareSize;
	
	public Icon(String fileName) {
		int iconSize = (int) (squareSize*0.8);
		  try{
			  File file = new File("./Images/"+fileName+".png");
			  if (fileName.contains("p")||fileName.contains("P")
				||fileName.contains("A")||fileName.contains("a"))
				  iconSize = (int) (squareSize*0.6);
		      image = resize(ImageIO.read(file),iconSize,iconSize);
		  }catch (IOException e) {  }
		  
	}
	

	/* Resize icon*/
	private static BufferedImage resize(BufferedImage img, int newW, int newH) { 
		
	    Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
	    BufferedImage dimg = 
	    		new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

	    Graphics2D g2d = dimg.createGraphics();
	    g2d.drawImage(tmp, 0, 0, null);
	    g2d.dispose();

	    return dimg;
	} 
	
	
}
