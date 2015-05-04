import java.awt.*; 
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.File;


public class Gun extends Enemy{

	private BufferedImage image;

	public Gun (int x, int y) {
		super(x,y,40,40);
		try{
			image = ImageIO.read(getClass().getResourceAsStream("/gun.gif"));
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	@Override
	public void draw(Graphics2D g) {

		Graphics2D g2 = (Graphics2D)g;
 		g2.drawImage(image, x, y, width, height,null);
		
	}
}