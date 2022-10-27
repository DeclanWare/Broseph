package entities;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

import utilities.LawOfCosines;
import utilities.Polygon;

public class Player extends Entity{
	
	public boolean goalTracking;
	public float goalX;
	public float goalY;
	public boolean clockwise;
	public float xpos;
	public float ypos;
	public float xspd;
	public float yspd;
	Polygon bounds;
	
	public Player(float xpos, float ypos, Polygon bounds) {
		
		//arbitrary initialisation values
		goalX = (int) (Math.random() * 800);
		goalY = (int) (Math.random() * 800);
		clockwise = true;
		
		this.xpos = xpos;
		this.ypos = ypos;
		this.bounds = bounds;
		
		//TEMP DELETE NEXT
		this.xspd = 1;
		this.yspd = 1;
	}
	
	@Override
	public void tick() {
		
		float distanceFromGoal;
		float distanceNextStep;
		float totalspd;
		
		distanceFromGoal = (float) Math.hypot(xpos - goalX, ypos - goalY);
		distanceNextStep = (float) Math.hypot(xpos - goalX + xspd, ypos - goalY + yspd);
		totalspd = (float) Math.hypot(xspd, yspd);
		
		if(distanceNextStep > distanceFromGoal && goalTracking == true) {
			
			float angle = totalspd/distanceFromGoal;
			
			if((xspd >= 0 && ypos >= goalY) || (xspd < 0 && ypos <= goalY)) {
				float newX = (float) (goalX + (xpos - goalX)*Math.cos(angle) - (ypos - goalY)*Math.sin(angle));
				float newY = (float) (goalY + (xpos - goalX)*Math.sin(angle) + (ypos - goalY)*Math.cos(angle));
				xspd = xpos - newX;
				yspd = ypos - newY;
			} else if((xspd >= 0 && ypos <= goalY) || (xspd < 0 && ypos >= goalY)) {
				float newX = (float) (goalX + (xpos - goalX)*Math.cos(angle) + (ypos - goalY)*Math.sin(angle));
				float newY = (float) (goalY - (xpos - goalX)*Math.sin(angle) + (ypos - goalY)*Math.cos(angle));
				xspd = xpos - newX;
				yspd = ypos - newY;
			}
			
			/*
			newX = centerX + (point2x-centerX)*Math.cos(x) - (point2y-centerY)*Math.sin(x);
			newY = centerY + (point2x-centerX)*Math.sin(x) + (point2y-centerY)*Math.cos(x);
			*/
		}
		
		
		
		advancePlayer();
	}

	@Override
	public void render(Graphics2D g2d) {
		
		g2d.setColor(new Color(70,0,0));
		g2d.drawRect((int) xpos, (int) ypos, 20, 20);
		g2d.setColor(new Color(0,0,0));
	}
	
	public void advancePlayer() {
		xpos += xspd;
		ypos += yspd;
	}
	
	public void pointIndicated(MouseEvent e) {
		this.goalX = e.getX();
		this.goalY = e.getY();
		this.goalTracking = true;
		
		//should we rotate clockwise or counterclockwise?
		//find cross product
		float cross = xspd * (goalY-ypos) - yspd * (goalX - xpos);
		if(cross > 0)
			clockwise = false;
		else
			clockwise = true;
	}
	
	
}
