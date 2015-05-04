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

public class BigBoss extends Enemy{
	public static final int X_TO_FADE = 400;
	private int step = 12;
	private boolean alive = true;
	private BufferedImage ene;
	
	public BigBoss(int x, int y) {
		super(x, y, 400, 200);
		try{
			ene = ImageIO.read(getClass().getResourceAsStream("/bigboss.gif"));
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	@Override
	public void draw(Graphics2D g) {
		Graphics2D g2 = (Graphics2D)g;
 		g2.drawImage(ene, x, y, width, height,null);
		
	}

	@Override
	public void proceed(){
		x += step;
		if(x > X_TO_FADE){
			alive = false;
		}
	}
}