import java.util.ArrayList;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;
import java.io.File;
import java.awt.*;
import javax.imageio.ImageIO;
import java.io.IOException;

public class GamePanel extends JPanel {
	
	private BufferedImage bi;	
	Graphics2D big;
	BufferedImage img;
	ArrayList<Sprite> sprites = new ArrayList<Sprite>();

	public GamePanel() {
		bi = new BufferedImage(400, 600, BufferedImage.TYPE_INT_ARGB);
		big = (Graphics2D) bi.getGraphics();

		try{img = ImageIO.read(getClass().getResourceAsStream("/bg.jpg"));
		}catch(IOException e){e.printStackTrace();}

		//big.setBackground(Color.BLACK);
		//big.drawImage(img,0,0,null);
	}

	public void updateGameUI(GameReporter reporter,int i){
		big.clearRect(0, 0, 400, 600);
		big.drawImage(img,0,0,null);
		big.setColor(Color.DARK_GRAY);
		big.drawString(String.format("LIFE: %02d",i),20,20);		
		big.drawString(String.format("SCORE: %08d", reporter.getScore()), 80, 20);
		for(Sprite s : sprites){
			s.draw(big);
		}
		repaint();
	}

	@Override
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(bi, null, 0, 0);
	}

}
