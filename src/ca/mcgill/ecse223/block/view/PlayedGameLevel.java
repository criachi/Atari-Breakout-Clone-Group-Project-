package ca.mcgill.ecse223.block.view;

import ca.mcgill.ecse223.block.controller.Block223Controller;
import ca.mcgill.ecse223.block.controller.InvalidInputException;
import ca.mcgill.ecse223.block.controller.TOGridCell;
import ca.mcgill.ecse223.block.controller.TOCurrentlyPlayedGame;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JComponent;

public class PlayedGameLevel extends JComponent {
	
	private static final long serialVersionUID = 8753563722302471614L;
	private HashMap<ArrayList<Integer>, Rectangle2D> rectangles = new HashMap<ArrayList<Integer>, Rectangle2D>();
	
	private int level;
	private List<TOGridCell> blockAssignments;
	private TOCurrentlyPlayedGame game;
	
	public void init() {
		level = 1;
		try {
			blockAssignments = Block223Controller.getBlocksAtLevelOfCurrentDesignableGame(level);
			game = Block223Controller.getCurrentPlayableGame();
		} catch (InvalidInputException e) {
			e.getStackTrace();
		}
		repaint();
	}
	
	public void setLevel(int level) {
		this.level = level;
		try {
			blockAssignments = Block223Controller.getBlocksAtLevelOfCurrentDesignableGame(level);
			repaint();
		} catch (InvalidInputException e) {
			e.printStackTrace();
		}
	}
	
	public void setBlockAssignments() {
		try {
			blockAssignments = Block223Controller.getBlocksAtLevelOfCurrentDesignableGame(level);
			repaint();
		} catch (InvalidInputException e) {
			e.printStackTrace();
		}
	}
	
	public void refreshGame() {
		try {
			game = Block223Controller.getCurrentPlayableGame();
			if(game.getCurrentLevel() != level) {
				level = game.getCurrentLevel();
				blockAssignments = Block223Controller.getBlocksAtLevelOfCurrentDesignableGame(level);
			}
			repaint();
		} catch (InvalidInputException e) {
			e.printStackTrace();
		}
	}
	
	public void doDrawing(Graphics g) {
		Graphics2D g2d = (Graphics2D) g.create();
		BasicStroke stroke = new BasicStroke(1);
		g2d.setStroke(stroke);
		int x = 1;
		for(int i = 10; i < 390; i = i + 25) {
			int y = 1;
			for(int j = 10; j < 337; j = j + 22) {
				ArrayList<Integer> gridPosition = new ArrayList<Integer>();
				gridPosition.add(x);
				gridPosition.add(y);
				Rectangle2D filler = new Rectangle2D.Double(i, j, 20, 20);
				rectangles.put(gridPosition, filler);
				y++;
			}
			x++;
		}
		if(blockAssignments != null) {
			for(TOGridCell cell: blockAssignments) {
				Color blockColor = new Color(cell.getRed(), cell.getGreen(), cell.getBlue());
				ArrayList<Integer> gridPosition = new ArrayList<Integer>();
				gridPosition.add(cell.getGridHorizontalPosition());
				gridPosition.add(cell.getGridVerticalPosition());
				Rectangle2D filler = rectangles.get(gridPosition);
				rectangles.remove(gridPosition);
				g2d.setColor(blockColor);
				g2d.fill(filler);
				g2d.draw(filler);
				rectangles.put(gridPosition, filler);
			}
		}
		Rectangle2D paddle = new Rectangle2D.Double(game.getCurrentPaddleX(), 390-35, game.getCurrentPaddleLength(), 5);
		Color paddleColor = new Color(200, 0, 200);
		g2d.setColor(paddleColor);
		g2d.fill(paddle);
		g2d.draw(paddle);
		Ellipse2D ball = new Ellipse2D.Double(game.getCurrentBallX(), game.getCurrentBallY(), 10, 10);
		Color ballColor = new Color(250, 150, 0);
		g2d.setColor(ballColor);
		g2d.fill(ball);
		g2d.draw(ball);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		doDrawing(g);
	}

}

