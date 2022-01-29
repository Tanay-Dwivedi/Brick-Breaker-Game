package brickBreaker;

import java.awt.*;

public class BrickGenerator {

	public int brick[][];
	public int brickWidth;
	public int brickHeight;
	
	public BrickGenerator(int row, int column) {
		brick = new int[row][column];
		for(int i=0; i<brick.length; i++) {
			for (int j=0; j<brick[0].length; j++) {
				brick[i][j] = 1;
			}
		}
		
		brickWidth = 540/column;
		brickHeight = 150/row;
		
	}
	
	public void Draw(Graphics2D g) {
		for(int i=0; i<brick.length; i++) {
			for (int j=0; j<brick[0].length; j++) {
				if(brick[i][j] > 0) {
					g.setColor(new Color(244,164,96));
					g.fillRoundRect(j*brickWidth+80, i*brickHeight+50, brickWidth, brickHeight, 10, 10);
					
					g.setStroke(new BasicStroke(3));
					g.setColor(new Color(210,105,30));
					g.drawRoundRect(j*brickWidth+80, i*brickHeight+50, brickWidth, brickHeight, 10, 10);
				}
			}
		}
	}
	
	public void setBrickValue(int value, int row, int column) {
		brick[row][column] = value;
	}
	
}
