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

public class Bullet extends Sprite{
	public static final int Y_TO_FADE = 400;
	public static final int Y_TO_DIE = 30;
	
	private int step = 12;
	private boolean alive = true;
	private BufferedImage ene;
	
	public Bullet(int x, int y) {
		super(x-(5/2), y, 5, 7);
		
	}

	@Override
	public void draw(Graphics2D g) {

		g.setColor(Color.PINK);
		g.fillRect(x, y, width, height);
		
	}

	public void proceed(){
		y -= step;
		if(y < Y_TO_DIE){
			alive = false;
		}
	}

	public void death(){
		alive = false;
	}

	public int getW(){
		return width;
	}
	
	public boolean isAlive(){
		return alive;
	}
}