package ca.mcgill.ecse223.block.view;

import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

import ca.mcgill.ecse223.block.controller.TOHallOfFame;

public class HallOfFameView extends JPanel {
	
	public HallOfFameView(TOHallOfFame hof) {
		super();
		setSize(390,390);
		initialize(hof);
	}
	
	public void initialize(TOHallOfFame hof) {
		//ArrayList<JLabel> jLabels = new ArrayList<JLabel>(hof.numberOfEntries());
		for(int i=0; i==hof.numberOfEntries()-1; i++) {
			String number = String.valueOf(i+1);
			JLabel hofEntry = new JLabel(number + hof.getEntries().get(i).getPlayername() + hof.getEntries().get(i).getScore());
			hofEntry.setLocation(5,30+i*20);
			add(hofEntry);
		}
	}
}
