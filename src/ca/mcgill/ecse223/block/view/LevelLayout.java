package ca.mcgill.ecse223.block.view;

import ca.mcgill.ecse223.block.controller.Block223Controller;
import ca.mcgill.ecse223.block.controller.InvalidInputException;
import ca.mcgill.ecse223.block.controller.TOGridCell;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JComponent;

public class LevelLayout extends JComponent {
	
	private static final long serialVersionUID = 8753563722302471614L;
	private HashMap<ArrayList<Integer>, Rectangle2D> rectangles;
	private ArrayList<Rectangle2D> fillers;
	
	private int level;
	private List<TOGridCell> blockAssignments;
	
	public void setLevel(int level) {
		this.level = level;
		try {
			blockAssignments = Block223Controller.getBlocksAtLevelOfCurrentDesignableGame(level);
			repaint();
		} catch (InvalidInputException e) {
			e.printStackTrace();
		}
	}
	
	public void doDrawing(Graphics g) {
		Graphics2D g2d = (Graphics2D) g.create();
		BasicStroke stroke = new BasicStroke(1);
		g2d.setStroke(stroke);
		for(int i = 10; i < 390; i = i + 25) {
			int y = 1;
			for(int j = 10; j < 337; j = j + 22) {
				Rectangle2D filler = new Rectangle2D.Double(i, j, 20, 20);
				g2d.draw(filler);
			}
		}
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		doDrawing(g);
	}

}

