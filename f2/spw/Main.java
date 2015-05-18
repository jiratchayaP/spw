import java.awt.BorderLayout;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class Main {
	public static void main(String[] args){

		JFrame frame = new JFrame("Space War");
		final JButton bt_pause = new JButton("pause");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(410, 675);
		frame.getContentPane().setLayout(new BorderLayout());
		SpaceShip v = new SpaceShip(180, 550, 50, 50);
		GamePanel gp = new GamePanel();
		final GameEngine engine = new GameEngine(gp, v);
		frame.addKeyListener(engine);
		frame.getContentPane().add(gp, BorderLayout.CENTER);
		frame.setVisible(true);
		engine.start();
		bt_pause.addActionListener(new ActionListener(){
			Boolean pause = false;
			public void actionPerformed(ActionEvent e){
				if(pause == false){
					engine.die();
					pause = true;
					bt_pause.setFocusable(false);
				}
				else{
					engine.unpause();
					pause = false;
					bt_pause.setFocusable(false);
				}
				
			}
		});
		frame.getContentPane().add(bt_pause,BorderLayout.NORTH);
	}
}
