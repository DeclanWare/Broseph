package settup;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import entities.Creature;
import entities.Entity;
import entities.Player;
import entities.Stalker;

public class Arena {
	
	Creature bruh;
	Stalker stalker;
	Player player;
	
	//prepare for the entities
	ArrayList<Entity> entities = new ArrayList<Entity>();
	
	public Arena() {
		player = new Player(50, 50, null);
		bruh = new Creature(10,10, null, player);
		stalker = new Stalker(200,200,null,player);
	}
	
	public void tick() {
		bruh.tick();
		player.tick();
		stalker.tick();
	}
	
	public void render(Graphics2D g2d) {
		bruh.render(g2d);
		player.render(g2d);
		stalker.render(g2d);
	}
	
	//MOUSE RELATED STUFF
	public void pointIndicated(MouseEvent e) {
		player.pointIndicated(e);
	}
}
