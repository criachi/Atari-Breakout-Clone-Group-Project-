package ca.mcgill.ecse223.block.view;

import ca.mcgill.ecse223.block.controller.Block223Controller;
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
import javax.swing.JFrame;

public class LevelLayout extends JComponent {
	
	private HashMap<Rectangle2D, ArrayList<Integer>> grids;
	private HashMap<ArrayList<Integer>, Rectangle2D> rectangles;
	
	private int level;
	private int blockId;
	private List<TOGridCell> blockAssignments;
	
	public void doDrawing(Graphics g) {
		Graphics2D g2d = (Graphics2D) g.create();
		BasicStroke stroke = new BasicStroke(2);
		g2d.setStroke(stroke);
		int x = 1;
		int y = 1;
		for(int i = 10; i < 390; i = i + 25) {
			for(int j = 10; j < 337; j = j + 22) {
				ArrayList<Integer> gridPosition = new ArrayList<Integer>();
				gridPosition.add(x);
				gridPosition.add(y);
				Rectangle2D filler = new Rectangle2D.Double(i, j, 20, 20);
				grids.put(filler, gridPosition);
				rectangles.put(gridPosition, filler);
				g2d.draw(filler);
				y++;
			}
			x++;
		}
		if(blockAssignments.size() != 0) {
			for(TOGridCell cell: blockAssignments) {
				int h = cell.getGridHorizontalPosition();
				int v = cell.getGridVerticalPosition();
				ArrayList<Integer> cellPosition = new ArrayList<Integer>();
				cellPosition.add(h);
				cellPosition.add(v);
				Color cellColor = new Color(cell.getRed(), cell.getGreen(), cell.getBlue());
				Rectangle2D newFiller = rectangles.get(cellPosition);
				grids.remove(newFiller);
				g2d.setColor(cellColor);
				g2d.fill(newFiller);
				rectangles.put(cellPosition, newFiller);
				grids.put(newFiller, cellPosition);
				g2d.draw(newFiller);
			}
		}
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		doDrawing(g);
	}

}

