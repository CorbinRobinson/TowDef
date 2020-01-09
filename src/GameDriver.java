/*
 * Corbin Robinson
 * 4/25/19
 * Cantrell 1410 11am
 */

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon; //added
import java.awt.Image;
import javax.swing.Icon; //added

public class GameDriver extends JFrame{
	private int x;
	private int y;
	
	public GameDriver() {
		
		super("Tower Defense"); //added

		getContentPane().setLayout(null);
		
		JPanel Controls = new JPanel();
		Controls.setBounds(0, 0, 120, 354);
		getContentPane().add(Controls);
		Controls.setLayout(null);
		
		JLabel lblMoney = new JLabel("Money: ");
		lblMoney.setBounds(6, 5, 108, 16);
		Controls.add(lblMoney);
		
		JLabel lblLives = new JLabel("Lives: ");
		lblLives.setBounds(6, 21, 108, 16);
		Controls.add(lblLives);
		
		JLabel lblWave = new JLabel("Wave:"+0);
		lblWave.setBounds(6, 39, 108, 14);
		Controls.add(lblWave);
		
		JPanel Map = new MapLoader(lblMoney, lblLives, lblWave);
		Map.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				x = e.getX();
				y = e.getY();
			}
		});
		Map.setBounds(123, 0, 600, 600);
		getContentPane().add(Map);
		Map.setLayout(new GridLayout(1, 0, 0, 0));
		
		
		//added
		ImageIcon GIcon = new ImageIcon("tree.png");
			Image image = GIcon.getImage();
			Image newImage = image.getScaledInstance(40, 40,  java.awt.Image.SCALE_SMOOTH);
			GIcon = new ImageIcon(newImage);
		
		JButton btnStart = new JButton(GIcon);
		btnStart.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			
			((MapLoader)Map).createTower(x, y, 1, 10);
			}
		});
		btnStart.setBounds(6, 60, 117, 60);
		Controls.add(btnStart);
		
		ImageIcon T2Icon = new ImageIcon("wizard.png");
			Image image2 = T2Icon.getImage();
			Image t2Image = image2.getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH);
			T2Icon = new ImageIcon(t2Image);
		JButton Tower2 = new JButton(T2Icon);
		Tower2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				((MapLoader)Map).createTower(x, y, 2, 40);
			}
		});
		Tower2.setBounds(6, 125, 117, 60);
		Controls.add(Tower2);
		
		JButton btnNewButton = new JButton("START");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				((MapLoader)Map).start();
			}
		});
		btnNewButton.setBounds(6, 196, 117, 29);
		Controls.add(btnNewButton);
		
		
		
		
	}

	public static void main(String[] args) {
	
		//1. Answer the questions found in Questions.txt
		
		GameDriver m = new GameDriver();
		m.setSize(760, 650);
		m.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		m.setVisible(true);
		

	}
}
