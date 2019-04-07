package ca.mcgill.ecse223.block.view;

import javax.swing.JLayeredPane;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import ca.mcgill.ecse223.block.controller.Block223Controller;
import ca.mcgill.ecse223.block.controller.InvalidInputException;
import ca.mcgill.ecse223.block.controller.TOCurrentBlock;
import ca.mcgill.ecse223.block.controller.TOCurrentlyPlayedGame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.HashMap;

public class PlayLevelLayout extends JLayeredPane {

	private int level=0;
	private int score=0;
	private int lives=0;
	Graphics2D g2d;
	
	public PlayLevelLayout() {
		super();
		setSize(390,390);
	}
	
	
	@SuppressWarnings("unused")
	public void doDrawing(Graphics g) {
		TOCurrentlyPlayedGame currentPlayableGame = null;
		try {
			currentPlayableGame = Block223Controller.getCurrentPlayableGame();
		} catch (InvalidInputException e) {
			
		} catch (Exception e) {
		}
		if(currentPlayableGame != null) {
			Graphics2D g2d = (Graphics2D) g.create();
			Border thickBorder = new LineBorder(Color.BLACK, 1);
			this.setBorder(thickBorder);
			score = currentPlayableGame.getScore();
			lives = currentPlayableGame.getLives();
			level = currentPlayableGame.getCurrentLevel();
			
				for(TOCurrentBlock block : currentPlayableGame.getBlocks()) {
					Rectangle2D rectangle = new Rectangle2D.Double(block.getX(), block.getY(), 20, 20);
					Color color = new Color(block.getRed(), block.getGreen(), block.getBlue());
					g2d.setColor(color);
					g2d.fill(rectangle);
					g2d.draw(rectangle);
				}
			// Drawing the paddle
			Rectangle2D paddle = new Rectangle2D.Double(currentPlayableGame.getCurrentPaddleX(), 355, currentPlayableGame.getCurrentPaddleLength(), 5);
			Color paddleColor = new Color(200, 0, 200);
			g2d.setColor(paddleColor);
			g2d.fill(paddle);
			g2d.draw(paddle);
			
			// Drawing the ball
			Ellipse2D ball = new Ellipse2D.Double(currentPlayableGame.getCurrentBallX() -5 , currentPlayableGame.getCurrentBallY() -5, 10, 10);
			Color ballColor = new Color(250, 150, 0);
			g2d.setColor(ballColor);
			g2d.fill(ball);
			g2d.draw(ball);
			
		}
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		doDrawing(g);
	}
	
	public int getScore() {
		return score;
	}
	
	public int getLives() {
		return lives;
	}
	
	public int getCurrentLevel() {
		return level;
	}
}
