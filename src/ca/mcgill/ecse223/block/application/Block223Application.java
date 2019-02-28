
package ca.mcgill.ecse223.block.application;


import ca.mcgill.ecse223.block.model.Block223;
import ca.mcgill.ecse223.block.model.Game;
import ca.mcgill.ecse223.block.model.UserRole;
import ca.mcgill.ecse223.block.view.Block223Page;
import ca.mcgill.ecse223.block.view.WelcomeWindow;
import ca.mcgill.ecse223.block.persistence.*;

public class Block223Application {
	
	private static Block223 block223 = null;
	private static UserRole currentUserRole = null; 
	private static Game currentGame = null; 
	
	// needs to be done 
	public static void main(String[] args) { 
		java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new WelcomeWindow();
            }
        });
	}
	public static Block223 getBlock223() {
		if ( block223 == null) {
			// for now, we are just creating an empty Block223
			block223 = new Block223();
		}
 		return block223;
	}
	
	/**
	 *  resetBlock223 added using persistence, but I am not sure if this going to work
	 * @author Onur Cayci
	 */
	public static Block223 resetBlock223() {
		block223 = Block223Persistence.load();
		return block223;
	}
	
	public static void setCurrentUserRole(UserRole aUserRole) {
		currentUserRole = aUserRole;
	}
	
	public static UserRole getCurrentUserRole() { // throws InvalidInputException? unsure bc we dk how this will be used
		return currentUserRole;
	}

	public static void setCurrentGame(Game aGame) {
		currentGame = aGame;
	}
	
	public static Game getCurrentGame() { //same question: shld we throw InvalidInputException?
		return currentGame;
	}
}
