
/*
 * Corbin Robinson
 * 4/25/19
 * Cantrell 1410 11am
 */

import java.awt.Graphics;
import java.util.ArrayList;
import java.awt.Color;

public class Bullet1 {
	private double vx;
	private double vy;
	private int posX;
	private int posY;
	private int width;
	private int height;
	private int style;

	public Bullet1(int posX, int posY, int velocity, double angle, Enemy e, int width, int height, int style) {
		this.posX = posX;
		this.posY = posY;
		vx = velocity * Math.cos(angle) + e.getVX();
		vy = velocity * Math.sin(angle);
		this.width = width;
		this.height = height;
		this.style = style;
	}
	
	// renders our object to the screen
	public void drawImage(Graphics g, Color c) {
		if(style == 1) {
			g.setColor(Color.BLACK);
			g.fillOval(posX += vx, posY += vy, width, height);
		}else {
			g.setColor(c);
			g.fillOval(posX += vx, posY += vy, width, height);
		}
	}

	// If we are going to be able to have enemies take damage, we need a way to know
	// the bullet position
	public int getXpos() {
		return posX;
	}

	public int getYpos() {
		return posY;
	}

	public boolean collisionCheck(Enemy e) {
		if(Math.hypot((e.getX() - posX), (e.getY() - posY)) <= width)
			return true;
		else
			return false;
	}
	
	public int getStyle() {
		return style;
	}

}