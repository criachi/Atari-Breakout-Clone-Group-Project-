package ca.mcgill.ecse223.block.controller;

import java.util.List;

import ca.mcgill.ecse223.block.application.Block223Application;
import ca.mcgill.ecse223.block.model.*;

public class Block223Controller {

	// ****************************
	// Modifier methods
	// ****************************
	public static void createGame(String name) throws InvalidInputException {
	}

	public static void setGameDetails(int nrLevels, int nrBlocksPerLevel, int minBallSpeedX, int minBallSpeedY,
			Double ballSpeedIncreaseFactor, int maxPaddleLength, int minPaddleLength) throws InvalidInputException {
	}

	public static void deleteGame(String name) throws InvalidInputException {
	}

	public static void selectGame(String name) throws InvalidInputException {
	}

	public static void updateGame(String name, int nrLevels, int nrBlocksPerLevel, int minBallSpeedX, int minBallSpeedY,
			Double ballSpeedIncreaseFactor, int maxPaddleLength, int minPaddleLength) throws InvalidInputException {
	}

	public static void addBlock(int red, int green, int blue, int points) throws InvalidInputException {
	}

	public static void deleteBlock(int id) throws InvalidInputException {
	}
	
	public static Block findBlock(int id) {
		Block foundBlock = null;
		for(Block block: Block223Application.getCurrentGame().getBlocks()) {
			if(block.getId() ==id) {
				foundBlock = block;
				break;
			}
		}
		return foundBlock;
	}

	public static void updateBlock(int id, int red, int green, int blue, int points) throws InvalidInputException {
		
		String error = " ";
		if ( !(Block223Application.getCurrentUserRole() instanceof Admin)) {
			error = "Admin privileges are required to update a block." ;
			}
		if (Block223Application.getCurrentGame() == null) {
			error = error + "A game must be selected to update a block" ; 
			
		}
		if (!(Block223Application.getCurrentUserRole() != Block223Application.getCurrentUserRole())) {  // review
			
			error = error + "Only the admin who created the game can update a block" ;
		
		}
		
		
		Game game = Block223Application.getCurrentGame();
		Block block = findBlock(id);
		
		if (findBlock(id) == null) {
			error = error + "The Block does not exist";
		}
		
		try {
		
		block.setRed(red);
		}
		catch (RuntimeException e) {
			error = e.getMessage();
			throw new InvalidInputException(error);
		}
		try {
		block.setGreen(green);
		}
		catch (RuntimeException e) {
			error = e.getMessage();
			throw new InvalidInputException(error);
		}
		
		try {
		block.setBlue(blue);
		}
		catch (RuntimeException e) {
			error = e.getMessage();
			throw new InvalidInputException(error);
		}
		try {
		block.setPoints(points);
		}
		catch (RuntimeException e) {
			error = e.getMessage();
			throw new InvalidInputException(error);
		}
		
		}

	public static void positionBlock(int id, int level, int gridHorizontalPosition, int gridVerticalPosition)
			throws InvalidInputException {
	}

	public static void moveBlock(int level, int oldGridHorizontalPosition, int oldGridVerticalPosition,
			int newGridHorizontalPosition, int newGridVerticalPosition) throws InvalidInputException {
	}

	public static void removeBlock(int level, int gridHorizontalPosition, int gridVerticalPosition)
			throws InvalidInputException {
		
		Game game = Block223Application.getCurrentGame();
		
		Level aLevel = game.getLevel(level);
		Assignment assignment = aLevel.findBlockAssignment(gridHorizontalPosition, gridVerticalPosition);
		
		if (assignment != null) {
			assignment.delete();
		}
		
	}

	public static void saveGame() throws InvalidInputException {
		
	}

	public static void register(String username, String playerPassword, String adminPassword)
			throws InvalidInputException {
	}

	public static void login(String username, String password) throws InvalidInputException {
	}

	public static void logout() {
	}

	// ****************************
	// Query methods
	// ****************************
	public static List<TOGame> getDesignableGames() {
		return null;
	}

	public static TOGame getCurrentDesignableGame() {
		return null;
	}

	public static List<TOBlock> getBlocksOfCurrentDesignableGame() {
		return null;
	}

	public static TOBlock getBlockOfCurrentDesignableGame(int id) throws InvalidInputException {
		
		String error = "";
		
		if ( !(Block223Application.getCurrentUserRole() instanceof Admin)) {
			error = "Admin privileges are required to access game information." ;
			}
		
		if (Block223Application.getCurrentGame() == null) {
			error = error + "A game must be selected to access its information" ; 
			
		}
		
if (!(Block223Application.getCurrentUserRole() != Block223Application.getCurrentUserRole())) {  // review
			
			error = error + "Only the admin who created the game can access its information" ;
		
		}
		
		
		
		
		Game game = Block223Application.getCurrentGame();
		Block block = findBlock(id);
		
		if (findBlock(id) == null) {
			error = error + "The Block does not exist";
		}
		
		TOBlock to = new TOBlock (block.getId(), block.getRed(), block.getGreen(), block.getBlue(), block.getPoints());
		
				
		return to;
	}

	public List<TOGridCell> getBlocksAtLevelOfCurrentDesignableGame(int level) throws InvalidInputException {
		return null;
	}

	public static TOUserMode getUserMode() {
		return null;
	}

}