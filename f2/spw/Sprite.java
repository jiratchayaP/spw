import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Double;

public abstract class Sprite {
	int x;
	int y;
	int width;
	int height;
	
	public Sprite(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	abstract public void draw(Graphics2D g);
	
	public Double getRectangle() {
		return new Rectangle2D.Double(x, y, width, height);
	}

	public int getWidth(){
		return this.width;
	}
	public int getHeight(){
		return this.height;
	}

	public void setY(int num){
		this.y = num;
	}
	public void setX(int num){
		this.x = num;
	}
}
