//import java.awt.Color;
//import java.awt.Graphics2D;
import java.awt.*; 
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.File;
public class SpaceShip extends Sprite{

	int step = 4;
	private BufferedImage image;
	
	public SpaceShip(int x, int y, int width, int height) {
		//super(x, y, width, height);
		super(x, y, width, height);
		try{
			image = ImageIO.read(getClass().getResourceAsStream("/space.gif"));
		}catch(IOException e){
			e.printStackTrace();
		}
		
	}

	@Override
	public void draw(Graphics2D g) {
		//g.setColor(Color.GREEN);
		//g.fillRect(x, y, width, height);
		Graphics2D g2 = (Graphics2D)g;
 		g2.drawImage(image, x, y, width, height,null);
		
	}

	public void move_x(int direction){
		x += (step * direction);
		if(x < 0)
			x = 0;
		if(x > 400 - width)
			x = 400 - width;
	}
	public void move_y(int direction){
		y += (step * direction);
		if(y < 0)
			y = 0;
		if(y > 600-width)
			y = 600-width;
	}

}
