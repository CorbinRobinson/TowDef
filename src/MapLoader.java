
/*
 * Corbin Robinson
 * 4/25/19
 * Cantrell 1410 11am
 */

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.security.SecureRandom;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

@SuppressWarnings({ "serial", "unused" })
public class MapLoader extends JPanel {

	// Instance variables
	ArrayList<Tower> tArr = new ArrayList<Tower>();
	ArrayList<Enemy> eArr = new ArrayList<Enemy>();
	private int lives = 200000;
	private int money = 70;
	private int spawnCount = 1;
	private int wave;
	JLabel lblMoney, lblLives, lblWave;
	ArrayList<BufferedImage> eImg = new ArrayList<BufferedImage>(); // enemy image array
	ArrayList<BufferedImage> tImg = new ArrayList<BufferedImage>(); // tower image array
	SecureRandom sr = new SecureRandom();

	public MapLoader(JLabel lblMoney, JLabel lblLives, JLabel lblWave) {

		int rows = 10;
		int cols = 10;
		this.lblMoney = lblMoney;
		this.lblLives = lblLives;
		this.lblWave = lblWave;
		setImages();
		lblMoney.setText("Money: "+money);
		lblLives.setText("Lives: "+lives);

		MyCanvas myCanvas = null;
		myCanvas = new MyCanvas(rows, cols);

		// load tile images
		for (int x = 0; x < rows; x++)
			for (int y = 0; y < cols; y++)
				myCanvas.addPicture(x, y, "grass_02_RS.png");

		if (myCanvas != null) {
			this.add(myCanvas);
		}
		this.setVisible(true);
	}

	// fills image arrays
	public void setImages() {
		try {
			// enemy images
			eImg.add(ImageIO.read(new File("worm.png")));
			eImg.add(ImageIO.read(new File("bear.png")));
			eImg.add(ImageIO.read(new File("mouse.png")));
			eImg.add(ImageIO.read(new File("sans.png")));
			// tower images
			tImg.add(ImageIO.read(new File("tree.png")));
			tImg.add(ImageIO.read(new File("wizard.png")));
		} catch (IOException e) {
			System.out.println("Unable to load enemy/tower image");
		}
	}

	// creates towers depending on style
	public void createTower(int x, int y, int style, int cost) {
		if (money >= cost) {
			tArr.add(new Tower(x, y, tImg.get(style - 1), 50, 50, cost, style));
			money -= tArr.get(tArr.size() - 1).getCost();
			lblMoney.setText("Money: " + money);
		} else {
			JOptionPane.showMessageDialog(null, "You don't have enough money!", "broke as hell",
					JOptionPane.PLAIN_MESSAGE);
		}
	}

	// creates enemies and random y values, random -x values so not in line
	public void start() {
		for (int i = 0; i < spawnCount; i++) {
			int y = sr.nextInt(500) + 25;
			int image = sr.nextInt(eImg.size());
			int x = -1 * sr.nextInt(100);
			eArr.add(new Enemy(x, y, eImg.get(image), 45, 45, 6, 0, 10));
		}
		spawnCount += 4;
		wave++;
		lblWave.setText("Wave :" + wave);
	}

	public void paint(Graphics g) {
		super.paint(g);
		try {
			for (int i = 0; i < tArr.size(); i++) {
				// tArr = tower array
				// eArr = enemy array
				// bArr = bullet array
				tArr.get(i).drawImage(g);
				Tower currentTower = tArr.get(i);
				ArrayList<Bullet1> bulletList = currentTower.getbArr();
				if (eArr.size() >= 1) {
					int closestEnemy = currentTower.closestEnemy(eArr); // gets closestEnemyst enemy to the tower
					switch (currentTower.getStyle()) { // switch depending on tower style
					case 1:
						// checks range, firerate
						if (currentTower.distance(eArr.get(closestEnemy)) <= 250 && currentTower.canFire(20)) {

							bulletList.add(new Bullet1(currentTower.getX(), currentTower.getY(), 30,
									Math.atan2(eArr.get(closestEnemy).getY() - currentTower.getY(),
											eArr.get(closestEnemy).getX() + eArr.get(closestEnemy).getVX()
													- currentTower.getX()),
									eArr.get(closestEnemy), 20, 20, 1));
						}
						break;
					case 2:
						// spirit bomb
						if (currentTower.distance(eArr.get(closestEnemy)) <= 175 && currentTower.canFire(40)) {
							bulletList.add(new Bullet1(currentTower.getX(), currentTower.getY(), 10,
									Math.atan2(eArr.get(closestEnemy).getY() - currentTower.getY(),
											eArr.get(closestEnemy).getX() + eArr.get(closestEnemy).getVX()
													- currentTower.getX()),
									eArr.get(closestEnemy), 80, 80, 2));
						}
						break;
					}
				}
				// checks if bullet is within 20 of enemy and deletes if so, updates money
				for (int k = 0; k < eArr.size(); k++) {
					Enemy currentEnemy = eArr.get(k);
					for (int j = 0; j < bulletList.size(); j++) {
						if (bulletList.get(j).collisionCheck(currentEnemy)) {
							eArr.remove(k);
							if(bulletList.get(j).getStyle() == 1)
								bulletList.remove(j);
							money += 1;
							lblMoney.setText("Money: " + money);
						}
					}
				}
				//draw bullets, removes bad shots, makes fancy colors
				for (int z = 0; z < bulletList.size(); z++) {
					float hue = sr.nextFloat();
					// Saturation between 0.1 and 0.3
					float saturation = (sr.nextInt(2000) + 1000) / 10000f;
					float luminance = 0.9f;
					Color c = Color.getHSBColor(hue, saturation, luminance);
					bulletList.get(z).drawImage(g, c);
					// clears off screen bullets
					if (Math.abs(bulletList.get(z).getXpos()) >= 625 || Math.abs(bulletList.get(z).getYpos()) >= 625)
						bulletList.remove(z);
				}

			}
			// draws enemies, removes lives
			for (int i = 0; i < eArr.size(); i++) {
				eArr.get(i).drawImage(g);
				Enemy currentEnemy = eArr.get(i);
				if (currentEnemy.posx >= 625) {
					lives--;
					eArr.remove(i);
				}
				lblLives.setText("Lives: " + lives);
			}
			Thread.sleep(45);
			// game over
			if (lives > 0)
				repaint();
			else {
				JOptionPane.showMessageDialog(null, "GAME OVER MAN, GAME OVER", "git gud scrub",
						JOptionPane.PLAIN_MESSAGE);
				System.exit(ABORT);
			}

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}

//MyCanvas taken from file reader lab
//Notice that this too is a panel
@SuppressWarnings("serial")
class MyCanvas extends JPanel {
	private BufferedImage[][] imgs;
	private int rows;
	private int cols;
	private final int tileSize = 64;

	public MyCanvas(int rows, int cols) {
		super();
		this.rows = rows;
		this.cols = cols;
		imgs = new BufferedImage[rows][cols];
	}

	public void addPicture(int x, int y, String filename) {
		if (x < 0 || x >= rows) {
			System.err.println("There is no row " + x);
		} else if (y < 0 || y >= cols) {
			System.err.println("There is no col " + y);
		} else {
			try {
				imgs[x][y] = ImageIO.read(new File(filename));
			} catch (IOException e) {
				System.err.println("Unable to read the file: " + filename);
			}
		}
	}

	public void paint(Graphics g) {
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				g.drawImage(imgs[i][j], j * tileSize, i * tileSize, null);
			}
		}
	}
}