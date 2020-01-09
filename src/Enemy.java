/*
 * Corbin Robinson
 * 4/25/19
 * Cantrell 1410 11am
 */

import java.awt.image.BufferedImage;

public class Enemy extends MovingObject{
	
	private int hitPoints;
	private int vx;
	private int vy;
	
	public Enemy(int posx, int posy, BufferedImage bi, int imageW, int imageH, int vx, int vy, int hitPoints)
	{
		super(posx, posy, bi, imageW, imageH, vx, vy);
		this.hitPoints = hitPoints;
		this.vx = vx;
		this.vy = vy;
	}
	
	public int getHitPoints()
	{
		return hitPoints;
	}
	
	public int getXpos()
	{
		return posx;
	}
	
	public int getYpos()
	{
		return posy;
	}
	
	public int getVX() {
		return vx;
	}
	
	public int getVY() {
		return vy;
	}
	
	public void slowDown() {
		vx = 1;
	}
}
