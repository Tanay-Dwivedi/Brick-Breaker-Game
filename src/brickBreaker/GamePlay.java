package brickBreaker;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GamePlay extends JPanel implements KeyListener, ActionListener{
	
	private boolean play = false;
	private int score = 0;
	private int totalBricks = 21;
	private Timer time;
	private int delay = 0;
	private int player1 = 325;
	private int ballPosX = 380;
	private int ballPosY = 680;
	private int ballXDir = -1;
	private int ballYDir = -2;
	
	private BrickGenerator brick;
	
	public GamePlay() {
		brick = new BrickGenerator(3,7);
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(true);
		time = new Timer(delay,this);
		time.start();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		time.start();
		
		if(play == true) {
			
			if(new Rectangle(ballPosX, ballPosY, 30, 30).intersects(new Rectangle(player1, 720, 150, 10))) {
				ballYDir = - ballYDir;
			}
			
			A: for (int i=0; i<brick.brick.length; i++) {
				for (int j=0; j<brick.brick[0].length; j++) {
					if(brick.brick[i][j] > 0) {
						int brickX = j*brick.brickWidth+80;
						int brickY = i*brick.brickHeight+50;
						int brickWidth = brick.brickWidth;
						int brickHeight = brick.brickHeight;
						
						Rectangle rect = new Rectangle(brickX, brickY, brickWidth, brickHeight);
						Rectangle ballRect = new Rectangle(ballPosX, ballPosY, 30, 30);
						Rectangle brickRect = rect;
						
						if(ballRect.intersects(brickRect)) {
							brick.setBrickValue(0,i,j);
							totalBricks--;
							score += 5;
							if(ballPosX+19<= brickRect.x || ballPosX+1>= brickRect.x + brickRect.width) {
								ballXDir = -ballXDir;
							}
							else {
								ballYDir = -ballYDir;
							}
							break A;
						}
					}
				}
			}
			
			ballPosX += ballXDir;
			ballPosY += ballYDir;
			if(ballPosX < 5) {
				ballXDir = -ballXDir;
			}
			if(ballPosY < 5) {
				ballYDir = -ballYDir;
			}
			if(ballPosX > 755) {
				ballXDir = -ballXDir;
			}
		}
		
		repaint();
		
	}

	@Override
	public void keyTyped(KeyEvent e) {}
	
	@Override
	public void keyReleased(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {
		
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			if(player1 > 600) {
				player1 = 630;
			}
			else {
				moveRight();
			}
		}
		
		if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			if(player1 < 50) {
				player1 = 5;
			}
			else {
				moveLeft();
			}
		}
		
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			if(!play) {
				play = true;
				ballPosX = 380;
				ballPosY = 680;
				ballXDir = -1;
				ballYDir = -2;
				player1 = 325;
				score = 0;
				totalBricks = 21;
				brick = new BrickGenerator(3,7);
				repaint();
			}
		}
	}
	
	public void paint(Graphics g) {
		
		//background
		g.setColor(new Color(127,255,212));
		g.fillRect(0, 0, 800, 800);
		
		// Drawing the Brick Map
		brick.Draw((Graphics2D)g);
		
		//borders
		g.setColor(new Color(255,0,0));
		g.fillRect(0, 0, 5, 800);
		g.fillRect(0, 0, 800, 5);
		g.fillRect(781, 0, 5, 800);
		
		//scores
		g.setColor(new Color(0,100,0));
		g.setFont(new Font("Architects Daughter" ,Font.BOLD, 30));
		g.drawString("Score: "+score, 550, 40);
		
		// bottom hitter sled
		g.setColor(new Color(160,82,45));
		g.fillRect(player1, 720, 150, 10);
		
		// ball
		g.setColor(new Color(255,0,255));
		g.fillOval(ballPosX, ballPosY, 30, 30);
		g.dispose();
		
		if(totalBricks <= 0) {
			play = false;
			ballXDir = 0;
			ballYDir = 0;
			g.setColor(new Color(255,0,255));
			g.setFont(new Font("Roboto Slab", Font.BOLD, 30));
			g.drawString("You Won and your Score was "+score, 400, 300);
			
			g.setFont(new Font("Roboto Slab", Font.BOLD, 30));
			g.drawString("Press Enter to Restart", 230, 300);
		}
		
		if(ballPosY > 700) {
			play = false;
			ballXDir = -1;
			ballYDir = -1;
			g.setColor(new Color(255,0,255));
			g.setFont(new Font("Roboto Slab", Font.BOLD, 30));
			g.drawString("You Lost and your Score was "+score, 400, 300);
			
			g.setFont(new Font("Roboto Slab", Font.BOLD, 30));
			g.drawString("Press Enter to Restart", 400, 300);
		}
		g.dispose();
	}
	
	public void moveRight() {
		play = true;
		player1 += 40;
	}
	
	public void moveLeft() {
		play = true;
		player1 -= 40;
	}
	
}
