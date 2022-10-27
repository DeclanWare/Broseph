package entities;

import java.awt.Graphics2D;

import utilities.Polygon;

public class Creature extends Entity{
	
	float xpos;
	float ypos;
	float xspd;
	float yspd;
	Polygon bounds;
	Player player;
	
	float maxspd;
	float deltaspd;
	
	public Creature(int xpos, int ypos, Polygon bounds, Player player) {
		this.xpos = xpos;
		this.ypos = ypos;
		this.bounds = bounds;
		this.player = player;
		
		maxspd = 2;
		deltaspd = (float) 0.02;
	}
	
	@Override
	public void tick() {
		
		//follow player x position
		if((int) xpos < player.xpos) {
			xspd += deltaspd;
		}else if((int) xpos > player.xpos){
			xspd -= deltaspd;
		}
		
		//follow player y position
		if((int) ypos < player.ypos) {
			yspd += deltaspd;
		}else if((int) ypos > player.ypos) {
			yspd -= deltaspd;
		}
		
		//enforce max speed...
			//...on x
		if(xspd > maxspd) {
			xspd = maxspd;
		}else if(xspd < ((-1) * maxspd)) {
			xspd = (-1) * maxspd;
		}
		
			//...on y
		if(yspd > maxspd) {
			yspd = maxspd;
		}else if(yspd < ((-1) * maxspd)) {
			yspd = (-1) * maxspd;
		}
		
		//move the creature
		ypos += yspd;
		xpos += xspd;
			
	}

	@Override
	public void render(Graphics2D g2d) {
		g2d.fillOval((int) xpos, (int) ypos,10,10);
		
	}
	
}
