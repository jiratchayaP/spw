import java.awt.Color;
import java.awt.Graphics2D;


public class Flag extends Sprite{

	public Flag (int x, int y, int width, int height) {
		super(x, y, width, height);
	}
	@Override
	public void draw(Graphics2D g) {
		g.setColor(Color.PINK);
		g.fillRect(x, y, width, height);
		
	}

}