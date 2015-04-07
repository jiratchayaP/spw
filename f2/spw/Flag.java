import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

public class Flag extends Sprite{

	public Flag (int x, int y, int width, int height) {
		super(x, y, width, height);
	}
	@Override
	public void draw(Graphics2D g) {
		Graphics2D g2 = (Graphics2D) g;
		Image img1 = Toolkit.getDefaultToolkit().getImage("flag.gif");
		g2.drawImage(img1, width, height, this);
		g2.finalize();
		
	}

}