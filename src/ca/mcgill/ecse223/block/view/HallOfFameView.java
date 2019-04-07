package ca.mcgill.ecse223.block.view;

import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

import ca.mcgill.ecse223.block.controller.Block223Controller;
import ca.mcgill.ecse223.block.controller.InvalidInputException;
import ca.mcgill.ecse223.block.controller.TOHallOfFame;

public class HallOfFameView extends JPanel {
	private TOHallOfFame hof;
	private int end = 10;
	private int start = 0;
	
	public HallOfFameView() {
		super();
		setSize(390,390);
		try {
			hof = Block223Controller.getHallOfFame(start, end);
			} catch (InvalidInputException e) {
				
			} catch (Exception e) {
				
			}
		initialize();
	}
	
	public void initialize() {
		//ArrayList<JLabel> jLabels = new ArrayList<JLabel>(hof.numberOfEntries());
		System.out.println(end);
		System.out.println(start);
		for(int i=end; i<=start; i++) {
			System.out.println("in for loop in initialize");
			String number = String.valueOf(i+1);
			JLabel hofEntry = null;
			try {
			hofEntry = new JLabel(number +  " " + hof.getEntries().get(i).getPlayername() + " " + hof.getEntries().get(i).getScore());
			} catch (IndexOutOfBoundsException e) {
				System.out.println("size of hall of fame entries: " + hof.numberOfEntries());
				System.out.println("index out of bounds exception; index i is now: " + i);
				break;
			}
			hofEntry.setLocation(5,30+i*20);
			add(hofEntry);
		}
		start = end;
		end = start;
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
