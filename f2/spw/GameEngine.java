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
	private ArrayList<Bullet> bullet = new ArrayList<Bullet>();
	private Timer timer;

	private long item_time=0;
	private Boolean item_on = false;

	private long score = 0;
	private double difficulty = 0.1;

	int r_x = (int) (Math.random()*310);
	int r_y = (int) (Math.random()*510);
	int num = 0;
	int num_item =0;

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
		Enemy e = new Enemy((int)(Math.random()*390), 30, 20, 20);
		gp.sprites.add(e);
		enemies.add(e);
	}
	private void generateBullet(){
		Bullet b = new Bullet((int)(v.getX()+(v.getWidth()/2)),(int)(v.getY()));
		gp.sprites.add(b);
		bullet.add(b);
	}
	private void generateBigBoss(){
		Enemy e = new BigBoss(10,(int)(Math.random()*500));
		gp.sprites.add(e);
		enemies.add(e);
	}
	private void generateGun(){
		Enemy e = new Gun((int)(Math.random()*390), 30);
		gp.sprites.add(e);
		enemies.add(e);
	}
	
	private void process(){
		
		if(item_time - System.currentTimeMillis()>0 && item_on){
			generateBullet();
		}
		if(score%500!=0 && num!=0){num=0;}
		if(score%200!=0 && num_item!=0){num_item=0;}

		if(Math.random() < difficulty){
			generateEnemy();
		}

		if(score%200==0 && score!=0 && num_item == 0){
			generateGun();
			num_item = 1;
		}

		if(((score%500)==0) && score!=0 && num==0){
			generateBigBoss();
			num = 1;
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

		Iterator<Bullet> b_iter = bullet.iterator();
		while(b_iter.hasNext()){
			Bullet b = b_iter.next();
			b.proceed();
			
			if(!b.isAlive()){
				b_iter.remove();
				gp.sprites.remove(b);

			}
		}
		
		gp.updateGameUI(this,v.getLife());
		
		Rectangle2D.Double vr = v.getRectangle();
		Rectangle2D.Double fr = f.getRectangle();
		Rectangle2D.Double br;
		Rectangle2D.Double er;
		
		if(fr.intersects(vr)){
			gp.sprites.remove(f);
			r_x = (int) (Math.random()*310);
			r_y = (int) (Math.random()*510);
			this.f = new Flag(r_x,r_y,40,40);
			gp.sprites.add(f);
			score += 100;
		}
		for(Enemy e : enemies){
			er = e.getRectangle();
			for(Bullet b: bullet){
				br = b.getRectangle();
				if(br.intersects(er) && !(e instanceof Gun)){
					gp.sprites.remove(e);
					e.death();
					e.isAlive();
				}
			}
			if(er.intersects(vr)){
				if(e instanceof Gun){
					e.death();
					item_time = System.currentTimeMillis()+10000;
					item_on = true;
				}
				else if(e instanceof BigBoss){
					die();
					regame();
				}
				else if(v.getLife()>0){
					gp.sprites.remove(e);
					e.death();
					v.intersect();
				}
				else{
					die();
					regame();
				}
				return;
			}
		}
	}

	public void regame(){
		for(Enemy e: enemies){
			gp.sprites.remove(e);
			e.death();
		}
		gp.sprites.remove(f);
		for(Bullet b: bullet){
			gp.sprites.remove(b);
			b.death();
		}
		v.reGame();
		gp.sprites.add(f);
		item_time = 0;
		item_on = false;
		int dialogButton = JOptionPane.showConfirmDialog (null, "Restart game?","input", JOptionPane.YES_NO_OPTION);
		if (dialogButton == JOptionPane.YES_OPTION) {
    			timer.start();
    			generateEnemy();
    		score = 0;
    		v.setY(550);
    		v.setX(180);
		}
		try {
    		Thread.sleep(1000);
		} catch (InterruptedException e) {
    		e.printStackTrace();
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
