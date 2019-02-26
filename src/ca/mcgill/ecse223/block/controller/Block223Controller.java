package ca.mcgill.ecse223.block.controller;

import java.util.ArrayList;
import java.util.List;

import ca.mcgill.ecse223.block.application.Block223Application;
import ca.mcgill.ecse223.block.model.*;

public class Block223Controller {

	// ****************************
	// Modifier methods
	// ****************************
	public static void createGame(String name) throws InvalidInputException {
		UserRole user = Block223Application.getCurrentUserRole();
		
		/*
		 * The method below will check if the user is an admin
		 */
		if(!(user instanceof Admin)) {
			throw new InvalidInputException("Admin privileges are required to create a game");
		}
		
		Block223 block223 = Block223Application.getBlock223();
		
		/*
		 * Had to write a new findGame method in the Block223 class 
		 * due to the second game constructor not checking for 
		 * duplicate names. The getWithName() method in the game class
		 * also doesn't work, hence I wrote a new method in the Block223 class
		 * which searches for a game based on name.
		 * The method will return the game if its found, or null otherwise
		 */
		if(block223.findGame(name) == null) {
			throw new InvalidInputException("The name of a game must be unique.");
		}
		
		
		try {
			new Game(name, 1, (Admin)user, 1, 1, 1, 10, 10, block223);
		}
		catch(RuntimeException e) {
			String error = e.getMessage();
			throw new InvalidInputException(error);
		}
	}

	public static void setGameDetails(int nrLevels, int nrBlocksPerLevel, int minBallSpeedX, int minBallSpeedY,
			Double ballSpeedIncreaseFactor, int maxPaddleLength, int minPaddleLength) throws InvalidInputException {
	}

	public static void deleteGame(String name) throws InvalidInputException {
		Block223 block223 = Block223Application.getBlock223();
		UserRole user = Block223Application.getCurrentUserRole();
		
		//Check that logged in user is an admin
		if(!(user instanceof Admin)) {
			throw new InvalidInputException("Admin privileges are required to delete a game.");
		}
		
		//Check if game exists
		Game gameToDelete = block223.findGame(name);
		if(gameToDelete == null) {
			return;
		}
		//Check that game admin is currently signed in admin
		else if(!(gameToDelete.getAdmin().equals((Admin)user))){
			throw new InvalidInputException("Only the admin who created the game can delete the game.");
		}
		
		gameToDelete.delete();
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

	public static void updateBlock(int id, int red, int green, int blue, int points) throws InvalidInputException {
	}

	public static void positionBlock(int id, int level, int gridHorizontalPosition, int gridVerticalPosition)
			throws InvalidInputException {
	}

	public static void moveBlock(int level, int oldGridHorizontalPosition, int oldGridVerticalPosition,
			int newGridHorizontalPosition, int newGridVerticalPosition) throws InvalidInputException {
	}

	public static void removeBlock(int level, int gridHorizontalPosition, int gridVerticalPosition)
			throws InvalidInputException {
	}

	public static void saveGame() throws InvalidInputException {
	}

	public static void register(String username, String playerPassword, String adminPassword)
			throws InvalidInputException {
	}

	public static void login(String username, String password) throws InvalidInputException {
	}

	public static void logout() {
		Block223Application.setCurrentUserRole(null);
	}

	// ****************************
	// Query methods
	// ****************************
	public static List<TOGame> getDesignableGames() throws InvalidInputException {
		//Get the block instance and logged in user
		Block223 block223 = Block223Application.getBlock223();
		UserRole user = Block223Application.getCurrentUserRole();
		
		//Check if logged in user is an admin, non admins CANNOT access game information
		if(!(user instanceof Admin)) {
			throw new InvalidInputException("Admin privileges are required to access game information.");
		}
		
		ArrayList<TOGame> result = new ArrayList<TOGame>();
		
		//Run through all games, send back the games belonging to the logged in user
		for(Game game : block223.getGames()) {
			Admin gameAdmin = game.getAdmin();
			if(gameAdmin.equals((Admin)user)) {
				TOGame to = new TOGame(game.getName(), game.getLevels().size(), game.getNrBlocksPerLevel(), game.getBall().getMinBallSpeedX(), game.getBall().getMinBallSpeedY(), game.getBall().getBallSpeedIncreaseFactor(), game.getPaddle().getMaxPaddleLength(), game.getPaddle().getMinPaddleLength());
				result.add(to);
			}
		}
		
		return result;
	}

	public static TOGame getCurrentDesignableGame() {
		return null;
	}

	public static List<TOBlock> getBlocksOfCurrentDesignableGame() {
		return null;
	}

	public static TOBlock getBlockOfCurrentDesignableGame(int id) throws InvalidInputException {
		return null;
	}

	public List<TOGridCell> getBlocksAtLevelOfCurrentDesignableGame(int level) throws InvalidInputException {
		return null;
	}

	public static TOUserMode getUserMode() {
		return null;
	}

}