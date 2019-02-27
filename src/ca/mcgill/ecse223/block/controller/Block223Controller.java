package ca.mcgill.ecse223.block.controller;

import java.util.ArrayList;
import java.util.List;

import ca.mcgill.ecse223.block.application.Block223Application;

import ca.mcgill.ecse223.block.model.*;
import ca.mcgill.ecse223.block.persistence.Block223Persistence;


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
		if ( nrBlocksPerLevel > (maxNrHorizontalBlocks()* maxNrVerticalBlocks) ) {
			error = error + " The number of blocks does not fit the play area.";
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
			
			try {
				paddle.setMinPaddleLength(minPaddleLength);
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
		Block223Persistence.save(block223);
	}

	public static void selectGame(String name) throws InvalidInputException {
		String error = " ";
		
		if (!( Block223Application.getCurrentUserRole() instanceof Admin)) {
			error = "Admin privileges are required to define game settings.";
		}
		if(Block223Application.getCurrentUserRole() != Block223Application.getCurrentGame().getAdmin()) {
			error = error + "Only the admin who created the game can define its game settings.";
		}
				
		Game game = Block223Application.getBlock223().findGame(name);
		if(game == null) {
			error = error + "A game with name "+ name +" does not exist.";
		}
		if (error.length() > 0) {
			throw new InvalidInputException(error.trim());
		}
		Block223Application.setCurrentGame(game);
		
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
			
			try {
				game.setName(name);
			}
			catch (RuntimeException e) {
				error = e.getMessage();
				throw new InvalidInputException(error);
			}
			
		}
		setGameDetails(nrLevels, nrBlocksPerLevel, minBallSpeedX, minBallSpeedY, ballSpeedIncreaseFactor, maxPaddleLength, minPaddleLength);
	
	}

	public static void addBlock(int red, int green, int blue, int points) throws InvalidInputException {
		String error = "";
		if(!(Block223Application.getCurrentUserRole() instanceof Admin)) {
			error = "Admin privileges are required to add a block. ";
		}
		if(Block223Application.getCurrentGame() == null) {
			error = error + "A game must be selected to add a block. ";
		}
		if(Block223Application.getCurrentUserRole() != Block223Application.getCurrentGame().getAdmin()) {
			error = error + "Only the admin who created the game can add a block. ";
		}
		// validation check to see if another block w/ same color exists
		for(Block block: Block223Application.getCurrentGame().getBlocks()) {
			if((block.getRed() == red) && (block.getGreen() == green) && (block.getBlue() == blue)) {
				error = error + "A block with the same color already exists for the game. ";
			}
		}
		if (error.length() > 0) {
			throw new InvalidInputException(error.trim());
		}
		Game game = Block223Application.getCurrentGame();
		try {
			Block block = new Block(red, green, blue, points, game);
		}
		catch (RuntimeException e) {
			throw new InvalidInputException(e.getMessage());
		}		
		// validation checks in constructor of block added as before checks in Umple file 
	}

	// helper method
	// should this even be public? why did he make it public? 
	public static Block findBlock(int id) {
		Block foundBlock = null;
		for(Block block: Block223Application.getCurrentGame().getBlocks()) {
			if(block.getId() == id) {
				foundBlock = block;
				break;
			}
		}
		return foundBlock; 
	}
	public static void deleteBlock(int id) throws InvalidInputException {
		String error = "";
		if (!(Block223Application.getCurrentUserRole() instanceof Admin)) {
			error = "Admin privileges are required to delete a block. ";
		} 
		if (Block223Application.getCurrentGame() == null) {
			error = error + "A game must be selected to delete a block. ";
		}
		if (Block223Application.getCurrentUserRole() != Block223Application.getCurrentGame().getAdmin()) {
			error = error + "Only the admin who created the game can delete a block. ";
		}
		// this pattern of adding error messages to empty "error" string and throwing invalid input exception is taken from btms controller method: schedule
		if (error.length() > 0) {
			throw new InvalidInputException(error.trim());
		}
		Game game = Block223Application.getCurrentGame();
		Block foundBlock = findBlock(id);
		if(foundBlock != null) {
			foundBlock.delete();
		}
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
		String error = "";
		if(!(Block223Application.getCurrentUserRole() instanceof Admin)) {
			error = "Admin privileges are required to save a game. ";
		}
		if(Block223Application.getCurrentGame() == null) {
			error = error + "A game must be selected to save it. ";
		}
		if(Block223Application.getCurrentUserRole() != Block223Application.getCurrentGame().getAdmin()) {
			error = error + "Only the admin who created the game can save it. ";
		}
		if (error.length() > 0) {
			throw new InvalidInputException(error.trim());
		}
		Block223 block223 = Block223Application.getBlock223();
		Block223Persistence.save(block223); //need to implement save method in Persistence class
		// might need to wrap the call to save in a try catch block like mentioned in page 23/27 of the sample solution of iteration 2...?
	}

	public static void register(String username, String playerPassword, String adminPassword) throws InvalidInputException {
		String error = "";
		if(Block223Application.getCurrentUserRole() != null) {
			error = "Cannot register a new user while a user is logged in. ";
		}
		if(playerPassword.equals(adminPassword)) {
			error = error + "The passwords have to be different. ";
		}
		if (error.length() > 0) {
			throw new InvalidInputException(error.trim());
		}
		Block223 block223 = Block223Application.getBlock223();
		// 4th and 5th validation checks are caught this way with try-catch block  			
		Player player = null;
		Admin admin = null;
		try {
			 player = new Player(playerPassword, block223);
			 User user = new User(username, block223, player);
			 if(adminPassword != null && adminPassword != "") {
					admin = new Admin(adminPassword, block223);
					user.addRole(admin);
				 }
		}
		catch (RuntimeException e) {
			if(player != null) player.delete();
			if(admin != null) admin.delete();
			throw new InvalidInputException(e.getMessage());
		}
		
		Block223Persistence.save(block223); //need to implement save
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
		String error = "";
		if(!(Block223Application.getCurrentUserRole() instanceof Admin)){
			error = "Admin privileges are required to access game information. ";
		}
		if(Block223Application.getCurrentGame() == null) {
			error = error + "A game must be selected to access its information. ";
		}
		if(Block223Application.getCurrentUserRole() != Block223Application.getCurrentGame().getAdmin()) {
			error = error + "Only the admin who created the game can access its information. ";
		}
		if (error.length() > 0) {
			throw new InvalidInputException(error.trim());
		}
		Game game = Block223Application.getCurrentGame();
		ArrayList<TOBlock> result = new ArrayList<TOBlock>();
		for(Block block : game.getBlocks()) {
			TOBlock to = new TOBlock(block.getId(), block.getRed(), block.getGreen(), block.getBlue(), block.getPoints());
			result.add(to);
		}
		return result;
	}

	public List<TOGridCell> getBlocksAtLevelOfCurrentDesignableGame(int level) throws InvalidInputException {
	return null;
	}

	public static TOUserMode getUserMode() {
	return null;
	}


}