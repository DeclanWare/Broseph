package settup;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class MyPanel extends JPanel implements MouseListener{
	
	Arena bearArena;
	
	MyPanel(){
		//setup
		super();
		setDoubleBuffered(true);
		
		//instantiate the arena
		bearArena = new Arena();
		
		this.addMouseListener(this);
	}
	
	/**
	 * Override the paintComponent method to display the content
	 * 
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		
		//render the game
		bearArena.render(g2d);
	}
	
	public void tick() {
		
		//advance the game one time step
		bearArena.tick();
		
		//passes to rendering step (calls the overrided paint component method)
		repaint();
	}
	
	//MOUSE RELATED STUFF
	
	@Override
	public void mouseClicked(MouseEvent e) {
		
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		System.out.println("brtuh");
		bearArena.pointIndicated(e);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		bearArena.pointReleased(e);
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
