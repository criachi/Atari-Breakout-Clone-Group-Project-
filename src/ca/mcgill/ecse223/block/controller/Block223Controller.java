package ca.mcgill.ecse223.block.controller;

import java.util.ArrayList;
import java.util.List;

import ca.mcgill.ecse223.block.application.Block223Application;
import ca.mcgill.ecse223.block.model.Game;
import ca.mcgill.ecse223.block.model.Admin;
import ca.mcgill.ecse223.block.model.Ball;
import ca.mcgill.ecse223.block.model.Block223;
import ca.mcgill.ecse223.block.model.Paddle;
import ca.mcgill.ecse223.block.model.Level;
import ca.mcgill.ecse223.block.persistence.Block223Persistence;


public class Block223Controller {

	// ****************************
	// Modifier methods
	// ****************************
	public static void createGame(String name) throws InvalidInputException {
	}

	public static void setGameDetails(int nrLevels, int nrBlocksPerLevel, int minBallSpeedX, int minBallSpeedY,
			Double ballSpeedIncreaseFactor, int maxPaddleLength, int minPaddleLength) throws InvalidInputException {
		String error = " ";
		
		if (!( Block223Application.getCurrentUserRole() instanceof Admin)) {
			error = "Admin privileges are required to define game settings.";
		}
		if (Block223Application.getCurrentGame() == null) {
			error = error + "A game must be selected to define game settings.";
		}
		if(Block223Application.getCurrentUserRole() != Block223Application.getCurrentGame().getAdmin()) {
			error = error + "Only the admin who created the game can define its game settings.";
		}
		if(nrLevels <1 || nrLevels > 99) {
			error = error + "The number of levels must be between 1 and 99.";
		}
		if (error.length() > 0) {
			throw new InvalidInputException(error.trim());
		}
			Game game = Block223Application.getCurrentGame();
			
			try {
				game.setNrBlocksPerLevel(nrBlocksPerLevel);
			}
			catch (RuntimeException e) {
				error = e.getMessage();
				throw new InvalidInputException(error);
			}	
				
			Ball ball = game.getBall();
			try {
				ball.setMinBallSpeedX(minBallSpeedX);
			}
			catch (RuntimeException e) {
				error = e.getMessage();
				throw new InvalidInputException(error);
			}
			try {
				ball.setMinBallSpeedY(minBallSpeedY);
			}
			catch (RuntimeException e) {
				error = e.getMessage();
				throw new InvalidInputException(error);
			}
			try {
				ball.setBallSpeedIncreaseFactor(ballSpeedIncreaseFactor);
			}
			catch (RuntimeException e) {
				error = e.getMessage();
				throw new InvalidInputException(error);
			}			
			
			Paddle paddle = game.getPaddle();
			
			try {
				paddle.setMaxPaddleLength(maxPaddleLength);
			}
			catch (RuntimeException e) {
				error = e.getMessage();
				throw new InvalidInputException(error);
			}
			Block223 block223 = new Block223();
			try {
				paddle.setMinPaddleLength(minPaddleLength);
				Block223Persistence.save(block223);
			}
			catch (RuntimeException e) {
				error = e.getMessage();
				throw new InvalidInputException(error);
			}
			
			List <Level> levels = game.getLevels();
				levels.size();
				if (nrLevels > levels.size()) {
					game.addLevel();
					levels.size();
					
				}else if (nrLevels < levels.size()) {
					Level level = game.getLevel(levels.size()-1);
					level.delete();
					levels.size();
				}			
	}

	public static void deleteGame(String name) throws InvalidInputException {
	}

	public static void selectGame(String name) throws InvalidInputException {
		String error = " ";
		
		if (!( Block223Application.getCurrentUserRole() instanceof Admin)) {
			error = "Admin privileges are required to define game settings.";
		}
		if(Block223Application.getCurrentUserRole() != Block223Application.getCurrentGame().getAdmin()) {
			error = error + "Only the admin who created the game can define its game settings.";
		}
				
		Game game = findGame(name);
		if(game == null) {
			error = error + "A game with name "+ name +" does not exist.";
		}
		if (error.length() > 0) {
			throw new InvalidInputException(error.trim());
		}
		Block223Application.setCurrentGame(game);
		Block223 block223 = new Block223 (); //??
		Block223Persistence.save(block223);
	}
	
	public static void updateGame(String name, int nrLevels, int nrBlocksPerLevel, int minBallSpeedX, int minBallSpeedY,
			Double ballSpeedIncreaseFactor, int maxPaddleLength, int minPaddleLength) throws InvalidInputException {
		String error = " ";
		if (!( Block223Application.getCurrentUserRole() instanceof Admin)) {
			error = "Admin privileges are required to define game settings.";
		}
		if (Block223Application.getCurrentGame() == null) {
			error = error + "A game must be selected to define game settings.";
		}
		if(Block223Application.getCurrentUserRole() != Block223Application.getCurrentGame().getAdmin()) {
			error = error + "Only the admin who created the game can define its game settings.";
		}
		if (error.length() > 0) {
			throw new InvalidInputException(error.trim());
		}
		Game game = Block223Application.getCurrentGame();
		String currentName = game.getName();
		if(currentName != name) {
			///unique name is already checked in the game class line 55
			Block223 block223 = new Block223();
			try {
				game.setName(name);
			Block223Persistence.save(block223);
			}
			catch (RuntimeException e) {
				error = e.getMessage();
				throw new InvalidInputException(error);
			}
			
		}
		setGameDetails(nrLevels, nrBlocksPerLevel, minBallSpeedX, minBallSpeedY, ballSpeedIncreaseFactor, maxPaddleLength, minPaddleLength);
	
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
	}

	// ****************************
	// Query methods
	// ****************************
	public static List<TOGame> getDesignableGames() throws InvalidInputException {
	return null;
	}

	public static TOGame getCurrentDesignableGame() throws InvalidInputException {
		String error = " ";
		
		if (!( Block223Application.getCurrentUserRole() instanceof Admin)) {
			error = "Admin privileges are required to define game settings.";
		}
		if (Block223Application.getCurrentGame() == null) {
			error = error + "A game must be selected to define game settings.";
		}
		if(Block223Application.getCurrentUserRole() != Block223Application.getCurrentGame().getAdmin()) {
			error = error + "Only the admin who created the game can define its game settings.";
		}
		if (error.length() > 0) {
			throw new InvalidInputException(error.trim());
		}
		
		Game game = Block223Application.getCurrentGame();
		TOGame to = new TOGame(game.getName(),game.getLevels().size(),game.getNrBlocksPerLevel(),game.getBall().getMinBallSpeedX(),game.getBall().getMinBallSpeedY(),game.getBall().getBallSpeedIncreaseFactor(),game.getPaddle().getMaxPaddleLength(),game.getPaddle().getMinPaddleLength());
		return to;
	}
	

	public static List<TOBlock> getBlocksOfCurrentDesignableGame() throws InvalidInputException {
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

	private static Game findGame(String name) {
		Game foundGame = null;
		for (Game game : Block223Application.getBlock223().getGames()) {
			if (game.getName() == name) {
				foundGame = game;
				break;
			}
		}
		return foundGame;
	}

}