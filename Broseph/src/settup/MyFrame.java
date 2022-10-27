package settup;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.Timer;

public class MyFrame extends JFrame implements ActionListener{
	
	MyPanel panel;
	
	MyFrame(){
		//create the frame
		super();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(800,800);
		this.setResizable(false);
		//instantiate the panel
		panel = new MyPanel();
		this.add(panel);
		this.setVisible(true);
		
		//create the action event to deploy every 7 ms
		Timer t = new Timer(7, this);
		t.start();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//start the tick/render sequence for the rest of the program
		panel.tick();
		
	}
	
}
