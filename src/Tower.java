
/*
 * Corbin Robinson
 * 4/25/19
 * Cantrell 1410 11am
 */

import java.awt.Graphics;
import java.util.ArrayList;
import java.awt.image.BufferedImage;

public class Tower extends MapObject {

	private ArrayList<Bullet1> bArr;
	private int count = 0;
	private int cost;
	private int style;

	public Tower(int posx, int posy, BufferedImage bi, int imageW, int imageH, int cost, int style) {
		super(posx, posy, bi, imageW, imageH);
		bArr = new ArrayList<Bullet1>();
		this.cost = cost;
		this.style = style;
	}

	// Added this to override parent
	// Wanted it to draw a bullet when the tower was drawn
	public void drawImage(Graphics g) {
		g.drawImage(bi, posx, posy, imageW, imageH, null);
	}

	// Got to return the bullet to check the position
	public ArrayList<Bullet1> getbArr() {
		return bArr;
	}

	public boolean canFire(int x) {
		if (count == 0) {
			count++;
			return true;
		} else if (count == x) {
			count = -1;
		}
		count++;
		return false;
	}

	public void destroyBullet(Bullet1 b1) {
		b1 = null;
	}
	
	public int closestEnemy(ArrayList<Enemy> e) { // gets the index of the closest enemy
		double closestDistance = Integer.MAX_VALUE;
		double currentDistance;
		int index = 0;
		for (int i = 0; i < e.size(); i++) {
			currentDistance = Math.hypot((e.get(i).getX() - posx), (e.get(i).getY() - posy));
			if (currentDistance < closestDistance) {
				closestDistance = currentDistance;
				index = i;
			}
		}
		return index;
	}

	public double distance(Enemy e) { // distance calculator
		double x = Math.hypot((e.getX() - posx), (e.getY() - posy));
		return x;
	}
	
	public int getStyle() {
		return style;
	}
	
	public int getCost() {
		return cost;
	}
}
