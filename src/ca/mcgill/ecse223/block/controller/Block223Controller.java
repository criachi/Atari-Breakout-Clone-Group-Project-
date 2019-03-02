package ca.mcgill.ecse223.block.controller;

import java.util.ArrayList;
import java.util.List;
import ca.mcgill.ecse223.block.application.Block223Application;
import ca.mcgill.ecse223.block.controller.TOUserMode.Mode;
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
		if(block223.findGame(name) != null) {
			throw new InvalidInputException("The name of a game must be unique.");
		}
		
		
		try {
			new Game(name, 1, (Admin)user, 1, 1, 1, 10, 10, block223);
		}
		catch(RuntimeException e) {
			String error = new String(e.getMessage());
			throw new InvalidInputException(error);
		}
		
		Block223Application.setCurrentGame(block223.findGame(name));
	}

	public static void setGameDetails(int nrLevels, int nrBlocksPerLevel, int minBallSpeedX, int minBallSpeedY,
			Double ballSpeedIncreaseFactor, int maxPaddleLength, int minPaddleLength) throws InvalidInputException {
		String error = " ";
		
		if (!( Block223Application.getCurrentUserRole() instanceof Admin)) {
			error = "Admin privileges are required to define game settings.";
		}
		if (Block223Application.getCurrentGame() == null) {
			error = error + "A game must be selected to define game settings.";
			throw new InvalidInputException(error);
		} 
		if(Block223Application.getCurrentUserRole() != Block223Application.getCurrentGame().getAdmin()) {
			error = error + "Only the admin who created the game can define its game settings.";
		} 
		if(nrLevels <1 || nrLevels > 99) {
			error = error + "The number of levels must be between 1 and 99.";
		}
		if ( nrBlocksPerLevel > (BlockAssignment.maxNrHorizontalBlocks()* BlockAssignment.maxNrVerticalBlocks()) ) {
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
		try {
		Block223Persistence.save(block223);
		} catch(RuntimeException e) {
			throw new InvalidInputException(e.getMessage());
		}
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
		Block foundBlock = game.findBlock(id);
		if(foundBlock != null) {
			foundBlock.delete();
		}
	}

	public static void updateBlock(int id, int red, int green, int blue, int points) throws InvalidInputException {
		String error = "";
		if(!(Block223Application.getCurrentUserRole() instanceof Admin)) {
			error = "Admin privileges are required to update a block. ";
		}
		if(Block223Application.getCurrentGame() == null) {
			error = error + "A game must be selected to update a block. ";
		}
		if(Block223Application.getCurrentUserRole() != Block223Application.getCurrentGame().getAdmin()) {
			error = error + "Only the admin who created the game can update a block. ";
		}
		// validation check to see if another block w/ same color exists
		for(Block block: Block223Application.getCurrentGame().getBlocks()) {
			if((block.getRed() == red) && (block.getGreen() == green) && (block.getBlue() == blue)) {
				error = error + "A block with the same color already exists for the game. ";
			}
		}
		Game game = Block223Application.getCurrentGame();
		Block block = game.findBlock(id);
		if(block == null ) {
			error = error + "The block does not exist. ";
		}
		// this pattern of adding error messages to empty "error" string and throwing invalid input exception is taken from btms controller method: schedule
		if (error.length() > 0) {
			throw new InvalidInputException(error.trim());
		}
		try {
			block.setRed(red);
			block.setGreen(green);
			block.setBlue(blue);
			block.setPoints(points);
		} catch (RuntimeException e){
			throw new InvalidInputException(e.getMessage());
		}
	}

	public static void positionBlock(int id, int level, int gridHorizontalPosition, int gridVerticalPosition)
			throws InvalidInputException {
		String error = "";
		if(!(Block223Application.getCurrentUserRole() instanceof Admin)) {
			error = "Admin priviliges are required to access game information. ";
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
		Level foundLevel = null;
		try {
			foundLevel = game.getLevel(level -1);
		} catch (IndexOutOfBoundsException e) {
			throw new InvalidInputException("Level " + level + "does not exist for the game. ");
		}
		List<BlockAssignment> assignments = foundLevel.getBlockAssignments();
		if(assignments.size() == game.getNrBlocksPerLevel()) {
			error = error + "The number of blocks has reached the maximum number (" + game.getNrBlocksPerLevel() + ") allowed for this game. ";
		}
		BlockAssignment ba = null;
		for(BlockAssignment assignment: assignments) {
			if(assignment.getGridHorizontalPosition() == gridHorizontalPosition && assignment.getGridVerticalPosition() == gridVerticalPosition) {
				ba = assignment;
			}
		}
		if(ba != null) {
			error = error + "A block already exists at location " + gridHorizontalPosition + "/" + gridVerticalPosition + ".";
		}
		Block block = game.findBlock(id);
		if(block == null) {
			error = error + "The block does not exist. ";
		}
		if(error.length() > 0) {
			throw new InvalidInputException(error.trim());
		}
		try {
			BlockAssignment blockAssignment = new BlockAssignment(gridHorizontalPosition, gridVerticalPosition, foundLevel, block, game);
		} catch (RuntimeException e) {
			throw new InvalidInputException(e.getMessage());
		}
	}

	public static void moveBlock(int level, int oldGridHorizontalPosition, int oldGridVerticalPosition,
			int newGridHorizontalPosition, int newGridVerticalPosition) throws InvalidInputException {
		String error = "";
		if(!(Block223Application.getCurrentUserRole() instanceof Admin)) {
			error = "Admin priviliges are required to access game information. ";
		}
		Game game = Block223Application.getCurrentGame();
		if(Block223Application.getCurrentGame() == null) {
			error = error + "A game must be selected to access its information. ";
		}
		if(Block223Application.getCurrentUserRole() != Block223Application.getCurrentGame().getAdmin()) {
			error = error + "Only the admin who created the game can access its information. ";
		}
		Level foundLevel = null;
		try {
			foundLevel = game.getLevel(level);
		} catch (IndexOutOfBoundsException e) {
			error = error + " Level" + level + " does not exist for the game.";
		}
		BlockAssignment foundBA = null;
		BlockAssignment existingBA = null;
		foundBA = foundLevel.findBlockAssignment(oldGridHorizontalPosition, oldGridVerticalPosition);
		if(foundBA == null) {
			error = error + "A block does not exist at location " + oldGridHorizontalPosition +
					"/" + oldGridVerticalPosition + ".";
		}
		existingBA = foundLevel.findBlockAssignment(newGridHorizontalPosition, newGridVerticalPosition);
		if(existingBA != null) {
			error = error + "A block already exists at location " + newGridHorizontalPosition + 
					"/" + newGridVerticalPosition + ".";
		}
		if(error.length() > 0) {
			throw new InvalidInputException(error.trim());
		}
		try {
			foundBA.setGridHorizontalPosition(newGridHorizontalPosition);
			foundBA.setGridVerticalPosition(newGridVerticalPosition);
		} catch (RuntimeException e) {
			throw new InvalidInputException(e.getMessage());
		}
	}

	public static void removeBlock(int level, int gridHorizontalPosition, int gridVerticalPosition) throws InvalidInputException {
		Game game = Block223Application.getCurrentGame();
		Level thisLevel = game.getLevel(level);
		BlockAssignment assignment = thisLevel.findBlockAssignment(gridHorizontalPosition, gridVerticalPosition);
		
		if(assignment!=null) {
			assignment.delete();
		}
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
		try {
		Block223Persistence.save(block223); 
		} catch (RuntimeException e) {
			throw new InvalidInputException(e.getMessage());
		}
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
			 Block223Persistence.save(block223); //maybe?
		} catch (RuntimeException e) {
			if(player != null) player.delete();
			if(admin != null) admin.delete();
			throw new InvalidInputException(e.getMessage());
		}
		
	}

	public static void login(String username, String password) throws InvalidInputException {
		String error = "";
		Block223Application.resetBlock223();
		if(Block223Application.getCurrentUserRole() != null) {
			error = "Cannot login a user while a user already logged in.\n";
		}
		User foundUser = User.getWithUsername(username);
		if(foundUser == null) {
			error = error + "The username cannot be found.\n";
			throw new InvalidInputException(error);
		}
		String foundPassword = null;
		List<UserRole> users = foundUser.getRoles();
		for(UserRole user: users) {
			String rolePassword = user.getPassword();
			if(rolePassword.contentEquals(password)) {
				foundPassword = password;
				Block223Application.setCurrentUserRole(user);
			}
		}
		if(foundPassword == null) {
			error = error + "The username and password do not match.\n"
					+ "";
		}
		if(error.length() > 0) {
			throw new InvalidInputException(error.trim());
		}
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
    public static TOBlock getBlockOfCurrentDesignableGame(int id) throws InvalidInputException {
		
		String error = "";
		
		if (!(Block223Application.getCurrentUserRole() instanceof Admin)) {
			error = "Admin privileges are required to access game information. ";
		}
		if (Block223Application.getCurrentGame() == null) {
			error = error + "A game must be selected to access its information. "; 	
		}
		if (Block223Application.getCurrentUserRole() != Block223Application.getCurrentGame().getAdmin()) {
			error = error + "Only the admin who created the game can access its information. ";
		}
		Game game = Block223Application.getCurrentGame();
		Block block = game.findBlock(id);
		if(block == null ) {
			error = error + "The block does not exist. ";
		}
		if (error.length() > 0) {
			throw new InvalidInputException(error.trim());
		}
		TOBlock to = new TOBlock(block.getId(), block.getRed(), block.getGreen(), block.getBlue(), block.getPoints());
		return to;
    }
    
	public List<TOGridCell> getBlocksAtLevelOfCurrentDesignableGame(int level) throws InvalidInputException {
		String error = "";
		if(!(Block223Application.getCurrentUserRole() instanceof Admin)) {
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
		List<TOGridCell> result = new ArrayList<TOGridCell>();
		Level foundLevel = null;
		try {
			foundLevel = game.getLevel(level-1);
		} catch (IndexOutOfBoundsException e) {
			throw new InvalidInputException("Level " + level + "does not exist for the game. ");
		}
		List<BlockAssignment> assignments = foundLevel.getBlockAssignments();
		for(BlockAssignment assignment: assignments) {
			TOGridCell to = new TOGridCell(assignment.getGridVerticalPosition(), assignment.getGridHorizontalPosition(),
					assignment.getBlock().getId(), assignment.getBlock().getRed(), assignment.getBlock().getGreen(),
					assignment.getBlock().getBlue(), assignment.getBlock().getPoints());
				result.add(to);
		}
		return result;
	}

	public static TOUserMode getUserMode() {
		UserRole userRole = Block223Application.getCurrentUserRole();
		if(userRole == null) {
			TOUserMode to = new TOUserMode(Mode.None);
			return to;
		}
		if(userRole instanceof Player) {
			TOUserMode to = new TOUserMode(Mode.Play);
			return to;
		}
		if(userRole instanceof Admin ) {
			TOUserMode to = new TOUserMode(Mode.Design);
			return to;
		}
	    return null;
	}


}