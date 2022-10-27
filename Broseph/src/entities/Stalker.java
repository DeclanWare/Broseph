package entities;

import java.awt.Graphics2D;

import utilities.Polygon;

public class Stalker extends Entity{
	
	float xpos;
	float ypos;
	float xspd;
	float yspd;
	Polygon bounds;
	Player player;
	
	float maxspd;
	float deltaspd;
	
	public Stalker(int xpos, int ypos, Polygon bounds, Player player) {
		this.xpos = xpos;
		this.ypos = ypos;
		this.bounds = bounds;
		this.player = player;
		
		maxspd = (float) 2;
		deltaspd = (float) 0.02;
	}
	
	@Override
	public void tick() {
		float xdiff = (player.xpos - this.xpos);
		float ydiff = (player.ypos - this.ypos);
		
		//distance from the player x + distance in y
		float totaldiff = Math.abs(xdiff) + Math.abs(ydiff);
		
		//if the stalker is on the player return
		if(totaldiff == 0) {
			System.out.println("you lose");
			return;
		}
		
		//find out the ideal speed allocation
		float xidealspd;
		float yidealspd;
		xidealspd = maxspd * (xdiff / totaldiff);
		yidealspd = maxspd * (ydiff / totaldiff);
		
		//increment speeds corresponding to the portion of the total change which they earn
		if(xspd > xidealspd) {
			xspd -= deltaspd * Math.abs(xidealspd / maxspd);
		}else if(xspd < xidealspd) {
			xspd += deltaspd * Math.abs(xidealspd / maxspd);
		}
		
		if(yspd > yidealspd) {
			yspd -= deltaspd * Math.abs(yidealspd / maxspd);
		}else if (yspd < yidealspd) {
			yspd += deltaspd * Math.abs(yidealspd / maxspd);
		}
			
		//enforce max speed
		enforceMaxSpeed();
		
		//move the stalker
		advanceStalker();
		
		//System.out.println(xspd + "  " + yspd);
	}//end tick

	
	
	@Override
	public void render(Graphics2D g2d) {
		g2d.fillOval((int) xpos, (int) ypos, 10, 15);
	}//end render
	
	
	
	private void enforceMaxSpeed() {
		//find out overall speed
		float totalspd;
		float multiplier;
		
		totalspd = (float) Math.hypot(xspd, yspd);
		multiplier = maxspd/totalspd;
		
		if(totalspd > maxspd) {
			this.xspd *= multiplier;
			this.yspd *= multiplier;
		}
			
		
	}//end enforceMaxSpeed
	
	
	
	private void advanceStalker() {
		xpos += xspd;
		ypos += yspd;
	}
}
