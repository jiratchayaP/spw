import java.lang.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import javax.swing.Timer;


public class GameEngine implements KeyListener, GameReporter{
	GamePanel gp;
		
	private ArrayList<Enemy> enemies = new ArrayList<Enemy>();	
	private SpaceShip v;
	private Flag f;	
	
	private Timer timer;
	
	private long score = 0;
	private double difficulty = 0.1;

	int r_x = (int) (Math.random()*350)+25;
	int r_y = (int) (Math.random()*550)+25;
	
	public GameEngine(GamePanel gp, SpaceShip v){
		this.gp = gp;
		this.v = v;	
		this.f = new Flag(r_x,r_y,40,40);
		gp.sprites.add(f);
		gp.sprites.add(v);
		timer = new Timer(50, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				process();
			}
		});
		timer.setRepeats(true);
		
	}
	
	public void start(){
		timer.start();

	}
	
	private void generateEnemy(){
		Enemy e = new Enemy((int)(Math.random()*390), 30);
		gp.sprites.add(e);
		enemies.add(e);
	}
	
	private void process(){
		if(Math.random() < difficulty){
			generateEnemy();

		}
		
		Iterator<Enemy> e_iter = enemies.iterator();
		while(e_iter.hasNext()){
			Enemy e = e_iter.next();
			e.proceed();
			
			if(!e.isAlive()){
				e_iter.remove();
				gp.sprites.remove(e);

			}
		}
		
		gp.updateGameUI(this,v.getLife());
		
		Rectangle2D.Double vr = v.getRectangle();
		Rectangle2D.Double fr = f.getRectangle();
		Rectangle2D.Double er;
		
		if(fr.intersects(vr)){
			gp.sprites.remove(f);
			r_x = (int) (Math.random()*350)+25;
			r_y = (int) (Math.random()*550)+25;
			this.f = new Flag(r_x,r_y,40,40);
			gp.sprites.add(f);
			score += 100;
		}
		for(Enemy e : enemies){
			er = e.getRectangle();

			if(er.intersects(vr)){
				if(v.getLife()>0){
					gp.sprites.remove(e);
					e.death();
					e.isAlive();
					v.intersect();
				}
				else{
					die();
					gp.sprites.remove(e);
					enemies.remove(e);
					regame();
				}
				return;
			}
		}
	}

	int randomWithRange(int min, int max)
	{
	   int range = (max - min) + 1;     
	   return (int)(Math.random() * range) + min;
	}

	public void regame(){
		v.reGame();
		int dialogButton = JOptionPane.showConfirmDialog (null, "Restart game?","input", JOptionPane.YES_NO_OPTION);
		if (dialogButton == JOptionPane.YES_OPTION) {
    			timer.start();
    			generateEnemy();
    		
    		score = 0;
    		v.setY(590-v.getWidth());
		}
		try {
    		Thread.sleep(1000);
		} catch (InterruptedException e) {
    		e.printStackTrace();
    		// handle the exception...        
    		// For example consider calling Thread.currentThread().interrupt(); here.
		}
		
	}
	
	public void die(){
		timer.stop();
	}
	
	void controlVehicle(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			v.move_y(-1);
			break;
		case KeyEvent.VK_DOWN:
			v.move_y(1);
			break;
		case KeyEvent.VK_LEFT:
			v.move_x(-1);
			break;
		case KeyEvent.VK_RIGHT:
			v.move_x(1);
			break;
		case KeyEvent.VK_D:
			difficulty += 0.1;
			break;
		}
	}

	public long getScore(){
		return score;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		controlVehicle(e);
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		//do nothing
	}

	@Override
	public void keyTyped(KeyEvent e) {
		//do nothing		
	}
}
