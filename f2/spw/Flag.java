//import java.awt.Color;
//import java.awt.Graphics2D;
//import java.awt.Graphics;
import java.awt.*; 
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.File;


public class Flag extends Sprite{

	private BufferedImage image;

	public Flag (int x, int y, int width, int height) {
		super(x, y, width, height);
		try{
			image = ImageIO.read(getClass().getResourceAsStream("/flag.gif"));
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	@Override
	public void draw(Graphics2D g) {
		
		//Image image = ImageIO.read(new File("/flag.gif"));
	
		Graphics2D g2 = (Graphics2D)g;
 		g2.drawImage(image, x, y, width, height,null);
		
	}

}