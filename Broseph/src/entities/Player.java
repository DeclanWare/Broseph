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
	public float xacl;
	public float yacl;
	//topspd is the theoretical top speed
	//spdlmt is the current max speed
	public final float TOPSPD;
	public float spdlmt;
	public float timetoacl;
	
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
		
		this.xacl = 1;
		this.yacl = 1;
		
		this.TOPSPD = 3;
		this.timetoacl = 20;
		
	}
	
	@Override
	public void tick() {
		
		this.spdlmt = TOPSPD;
		
		float distanceFromGoal;
		float distanceNextStep;
		float totalspd;
		
		distanceFromGoal = (float) Math.hypot(xpos - goalX, ypos - goalY);
		distanceNextStep = (float) Math.hypot(xpos - goalX + xspd, ypos - goalY + yspd);
		totalspd = (float) Math.hypot(xspd, yspd);
		
		if(distanceNextStep > distanceFromGoal && goalTracking == true) {
			
			//I thought this would be good but i hate the feeling of it
			//this.spdlmt = TOPSPD * (distanceFromGoal/(distanceFromGoal + 40));
			
			float angle = totalspd/distanceFromGoal;
			
			determineClockwise();
			
			if(clockwise) {
				float newX = (float) (goalX + (xpos - goalX)*Math.cos(angle) - (ypos - goalY)*Math.sin(angle));
				float newY = (float) (goalY + (xpos - goalX)*Math.sin(angle) + (ypos - goalY)*Math.cos(angle));
				xspd = xpos - newX;
				yspd = ypos - newY;
			} else if(!clockwise) {
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
		
		incrementSpeed();
	}
	
	public void incrementSpeed() {
		xspd *= xacl;
		yspd *= yacl;
		
		incrementAcceleration();
	}
	
	public void incrementAcceleration() {
		float totalspd = (float) Math.hypot(xspd, yspd);
		
		if(spdlmt > totalspd) {
			xacl = 1 + (spdlmt/totalspd)/50;
			yacl = 1 + (spdlmt/totalspd)/50;
		} else {
			xacl = 1 - (spdlmt/totalspd)/50;
			yacl = 1 - (spdlmt/totalspd)/50;
		}
		
		spdlmt += (TOPSPD - spdlmt)/200;
		
		System.out.println(totalspd);
	}
	
	public void determineClockwise(){
		float cross = xspd * (goalY-ypos) - yspd * (goalX - xpos);
		if(cross > 0)
			clockwise = false;
		else
			clockwise = true;
	}
	
	public void pointIndicated(MouseEvent e) {
		this.goalX = e.getX();
		this.goalY = e.getY();
		this.goalTracking = true;
		
		//should we rotate clockwise or counterclockwise?
		//find cross product
		determineClockwise();
	}

	public void pointReleased(MouseEvent e) {
		this.goalTracking = false;
		
		
	}
	
	
}
