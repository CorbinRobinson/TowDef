/*
 * Corbin Robinson
 * 4/25/19
 * Cantrell 1410 11am
 */

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class MovingObject extends MapObject {
	
	private int vx;
	private int vy;
	
	public MovingObject(int posx, int posy, BufferedImage bi, int imageW, int imageH, int vx, int vy)
	{
		super(posx, posy, bi,  imageW, imageH);
		this.vx=vx;
		this.vy=vy;
	}
	
	public void drawImage(Graphics g)
	{
		g.drawImage(bi,posx+=vx, posy+=vy,imageW,imageH,null);
	}
}
