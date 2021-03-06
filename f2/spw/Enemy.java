import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.File;
import java.awt.*; 

import java.awt.geom.Ellipse2D;

public class Enemy extends Sprite{
	public static final int Y_TO_FADE = 400;
	public static final int Y_TO_DIE = 600;
	
	private int step = 12;
	private boolean alive = true;
	private BufferedImage ene;
	
	public Enemy(int x, int y, int widght, int hieght) {
		super(x, y, widght, hieght);
		try{
			ene = ImageIO.read(getClass().getResourceAsStream("/enemy.gif"));
		}catch(IOException e){
			e.printStackTrace();
		}
	}

	@Override
	public void draw(Graphics2D g) {
		Graphics2D g2 = (Graphics2D)g;
 		g2.drawImage(ene, x, y, width, height,null);
		/*Ellipse2D.Double circle = new Ellipse2D.Double(x, y, width, height);
		g.setColor(Color.LIGHT_GRAY);
		g.fill(circle);*/
		//g.fillRect(x, y, width, height);
		
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