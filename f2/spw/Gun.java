import java.awt.*; 
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.File;


public class Gun extends Sprite{
	public static final int Y_TO_FADE = 400;
	public static final int Y_TO_DIE = 600;
	
	private int step = 12;
	private boolean alive = true;

	private BufferedImage image;

	public Gun (int x, int y, int width, int height) {
		super(x, y, width, height);
		try{
			image = ImageIO.read(getClass().getResourceAsStream("/flag.gif"));
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	@Override
	public void draw(Graphics2D g) {

		Graphics2D g2 = (Graphics2D)g;
 		g2.drawImage(image, x, y, width, height,null);
		
	}

	public void proceed(){
		y += step;
		if(y > Y_TO_DIE){
			alive = false;
		}
	}
	public void death(){
		alive = false;
	}
	
	public boolean isAlive(){
		return alive;
	}

}