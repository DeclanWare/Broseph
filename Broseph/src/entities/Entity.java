package entities;

import java.awt.Graphics2D;

public abstract class Entity {
	
	public abstract void tick();
		
	public abstract void render(Graphics2D g2d);

	
}
