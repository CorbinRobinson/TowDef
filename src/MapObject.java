/*
 * Corbin Robinson
 * 4/25/19
 * Cantrell 1410 11am
 */

import java.awt.image.BufferedImage;
import java.awt.Graphics;

public abstract class MapObject
{
	protected int posx; 
	protected int posy;
	protected BufferedImage bi; 
	protected int imageW;
	protected int imageH;

	MapObject(int posx, int posy, BufferedImage bi, int imageW, int imageH)
	{
		this.posx = posx;
		this.posy = posy; 
		this.bi= bi;
		this.imageW = imageW; 
		this.imageH = imageH;
	}
	
	//renders our object to the screen
	public void drawImage(Graphics g)
	{
		g.drawImage(bi,posx, posy,imageW,imageH,null);
	}
	
	public int getX() {
		return posx;
	}
	
	public int getY() {
		return posy;
	}
}