package ca.mcgill.ecse223.block.view;

import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

import ca.mcgill.ecse223.block.controller.Block223Controller;
import ca.mcgill.ecse223.block.controller.InvalidInputException;
import ca.mcgill.ecse223.block.controller.TOHallOfFame;
import ca.mcgill.ecse223.block.controller.TOUserMode.Mode;

public class HallOfFameView extends JPanel {
	private TOHallOfFame hof;
	private int end;
	private int start;
	private int numOfEntries;
	private JLabel entries[] = new JLabel[10];
	
	public HallOfFameView(int start, int end) {
		super();
		this.start = start;
		this.end = end;
		setSize(390,390);
		try {
			hof = Block223Controller.getHallOfFame(start, end);
			} catch (InvalidInputException e) {
				
			} catch (Exception e) {
				
			}
		if(Block223Controller.getUserMode().getMode() != Mode.Design) {
			initialize();
		}
	}
	
	public int getNumOfEntries() {
		try {
			hof = Block223Controller.getHallOfFame(0, Integer.MAX_VALUE);
		} catch(InvalidInputException e) {
			
		}
		return hof.numberOfEntries();
	}
	
	public void initialize() {
		
		numOfEntries = hof.numberOfEntries();
		int j = start;
		
		for(int i=0; i < numOfEntries; i++) {
			String number = String.valueOf(j++);
			try {
			entries[i] = new JLabel((number) +  ". Name: " + hof.getEntries().get(i).getPlayername() + ". Score: " + hof.getEntries().get(i).getScore());
			} catch (IndexOutOfBoundsException e) {
				break;
			}
			entries[i].setLocation(5,30+i*20);
			add(entries[i]);
		}
	}
		
	public void setEnd(int end) {
		this.end = end;
	}
	
	public void setStart(int start) {
		this.start = start;
	}
	
	public int getStart() {
		return start;
	}
	
	public int getEnd() {
		return end; 
	}
	
}
