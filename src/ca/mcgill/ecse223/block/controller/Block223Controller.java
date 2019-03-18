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
		
		String error = new String();
		/*
		 * The method below will check if the user is an admin
		 */
		if(!(user instanceof Admin)) {
			error += "Admin privileges are required to create a game.";
			throw new InvalidInputException(error);
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
			error += "The name of a game must be unique.";
			throw new InvalidInputException(error);
		}
		
		
		try {
			new Game(name, 1, (Admin)user, 1, 1, 1, 10, 10, block223);
		}
		catch(RuntimeException e) {
			error += e.getMessage();
			throw new InvalidInputException(error);
		}
		
		Block223Application.setCurrentGame(block223.findGame(name));
	}

	public static void setGameDetails(int nrLevels, int nrBlocksPerLevel, int minBallSpeedX, int minBallSpeedY,
			Double ballSpeedIncreaseFactor, int maxPaddleLength, int minPaddleLength) throws InvalidInputException {	
		String error = "";
		if (!( Block223Application.getCurrentUserRole() instanceof Admin)) {
			throw new InvalidInputException("Admin privileges are required to define game settings.");
		}
		if (Block223Application.getCurrentGame() == null) {
			throw new InvalidInputException("A game must be selected to define game settings.");
		} 
		if(Block223Application.getCurrentUserRole() != Block223Application.getCurrentGame().getAdmin()) {
			throw new InvalidInputException("Only the admin who created the game can define its game settings.");
		} 
		if(nrLevels <1 || nrLevels > 99) {
			throw new InvalidInputException("The number of levels must be between 1 and 99.");
		}
		if ( nrBlocksPerLevel > (BlockAssignment.maxNrHorizontalBlocks()* BlockAssignment.maxNrVerticalBlocks()) ) {
			throw new InvalidInputException(" The number of blocks does not fit the play area.");
		}
		if(minBallSpeedX == 0  && minBallSpeedY==0) {
			throw new InvalidInputException("The minimum speed of the ball must be greater than zero.");
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
			
			List<Level> levels = game.getLevels();
				while (levels.size() < nrLevels) {
					game.addLevel();
					
				}
				while(nrLevels < levels.size()) {
					Level level = game.getLevel(levels.size()-1);
					level.delete();
				}	
	}

	public static void deleteGame(String name) throws InvalidInputException {
		Block223 block223 = Block223Application.getBlock223();
		UserRole user = Block223Application.getCurrentUserRole();
		String error = new String();
		
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
			error += "Only the admin who created the game can delete the game.";
			throw new InvalidInputException(error);
		}
		
		gameToDelete.delete();
		try {
		Block223Persistence.save(block223);
		} catch(RuntimeException e) {
			error += e.getMessage();
			throw new InvalidInputException(error);
		}
	}

	public static void selectGame(String name) throws InvalidInputException {
		if (!( Block223Application.getCurrentUserRole() instanceof Admin)) {
			throw new InvalidInputException("Admin privileges are required to select a game.");
		}
		
		Game game = Block223Application.getBlock223().findGame(name);
		if(game == null) {
			throw new InvalidInputException("A game with name "+ name +" does not exist.");
		}
		
		
		if(Block223Application.getCurrentUserRole() != game.getAdmin()) {
			throw new InvalidInputException("Only the admin who created the game can select the game.");
		}
		Block223Application.setCurrentGame(game);
		
	}
	
	public static void updateGame(String name, int nrLevels, int nrBlocksPerLevel, int minBallSpeedX, int minBallSpeedY,
			Double ballSpeedIncreaseFactor, int maxPaddleLength, int minPaddleLength) throws InvalidInputException {
		String error = "";
		if (!( Block223Application.getCurrentUserRole() instanceof Admin)) {
			error += "Admin privileges are required to define game settings.";
		}
		if (Block223Application.getCurrentGame() == null) {
			error = error + "A game must be selected to define game settings.";
			throw new InvalidInputException(error);
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
				error += e.getMessage();
				throw new InvalidInputException(error);
			}
			
		}
		try {
			setGameDetails(nrLevels, nrBlocksPerLevel, minBallSpeedX, minBallSpeedY, ballSpeedIncreaseFactor, maxPaddleLength, minPaddleLength);
		}
		catch(InvalidInputException e) {
			error += e.getMessage();
			throw new InvalidInputException(error);
		}
	
	}

	public static void addBlock(int red, int green, int blue, int points) throws InvalidInputException {
		if(!(Block223Application.getCurrentUserRole() instanceof Admin)) {
			throw new InvalidInputException("Admin privileges are required to add a block.");
		}
		if(Block223Application.getCurrentGame() == null) {
			throw new InvalidInputException("A game must be selected to add a block.");
		}
		if(Block223Application.getCurrentUserRole() != Block223Application.getCurrentGame().getAdmin()) {
			throw new InvalidInputException("Only the admin who created the game can add a block.");
		}
		// validation check to see if another block w/ same color exists
		for(Block block: Block223Application.getCurrentGame().getBlocks()) {
			if((block.getRed() == red) && (block.getGreen() == green) && (block.getBlue() == blue)) {
				throw new InvalidInputException("A block with the same color already exists for the game.");
			}
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
		if (!(Block223Application.getCurrentUserRole() instanceof Admin)) {
			throw new InvalidInputException("Admin privileges are required to delete a block. ");
		} 
		if (Block223Application.getCurrentGame() == null) {
			throw new InvalidInputException("A game must be selected to delete a block. ");
		}
		if (Block223Application.getCurrentUserRole() != Block223Application.getCurrentGame().getAdmin()) {
			throw new InvalidInputException("Only the admin who created the game can delete a block. ");
		}
		Game game = Block223Application.getCurrentGame();
		Block foundBlock = game.findBlock(id);
		if(foundBlock != null) {
			foundBlock.delete();
		}
	}

	public static void updateBlock(int id, int red, int green, int blue, int points) throws InvalidInputException {
		if(!(Block223Application.getCurrentUserRole() instanceof Admin)) {
			throw new InvalidInputException("Admin privileges are required to update a block. ");
		}
		if(Block223Application.getCurrentGame() == null) {
			throw new InvalidInputException("A game must be selected to update a block. ");
		}
		if(Block223Application.getCurrentUserRole() != Block223Application.getCurrentGame().getAdmin()) {
			throw new InvalidInputException("Only the admin who created the game can update a block. ");
		}
		// validation check to see if another block w/ same color exists
		for(Block block: Block223Application.getCurrentGame().getBlocks()) {
			if((block.getRed() == red) && (block.getGreen() == green) && (block.getBlue() == blue)) {
				throw new InvalidInputException("A block with the same color already exists for the game. ");
			}
		}
		Game game = Block223Application.getCurrentGame();
		Block block = game.findBlock(id);
		if(block == null ) {
			throw new InvalidInputException("The block does not exist. ");
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
			error = "Admin privileges are required to position a block. ";
		}
		if(Block223Application.getCurrentGame() == null) {
			error = error + "A game must be selected to position a block. ";
		}
		if(Block223Application.getCurrentUserRole() != Block223Application.getCurrentGame().getAdmin()) {
			error = error + "Only the admin who created the game can position a block. ";
		}
		if (error.length() > 0) {
			throw new InvalidInputException(error.trim());
		}
		Game game = Block223Application.getCurrentGame();
		Level foundLevel = null;
		try {
			foundLevel = game.getLevel(level -1);
		} catch (IndexOutOfBoundsException e) {
			throw new InvalidInputException("Level " + level + " does not exist for the game. ");
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
			new BlockAssignment(gridHorizontalPosition, gridVerticalPosition, foundLevel, block, game);
		} catch (RuntimeException e) {
			throw new InvalidInputException(e.getMessage());
		}
	}

	public static void moveBlock(int level, int oldGridHorizontalPosition, int oldGridVerticalPosition,
			int newGridHorizontalPosition, int newGridVerticalPosition) throws InvalidInputException {
		if(!(Block223Application.getCurrentUserRole() instanceof Admin)) {
			throw new InvalidInputException("Admin privileges are required to move a block. ");
		}
		Game game = Block223Application.getCurrentGame();
		if(Block223Application.getCurrentGame() == null) {
			throw new InvalidInputException("A game must be selected to move a block. ");
		}
		if(Block223Application.getCurrentUserRole() != Block223Application.getCurrentGame().getAdmin()) {
			throw new InvalidInputException("Only the admin who created the game can move a block. ");
		}
		Level foundLevel = null;
		try {
			foundLevel = game.getLevel(level-1);
		} catch (IndexOutOfBoundsException e) {
			throw new InvalidInputException(" Level " + level + " does not exist for the game.");
		}
		BlockAssignment foundBA = null;
		BlockAssignment existingBA = null;
		foundBA = foundLevel.findBlockAssignment(oldGridHorizontalPosition, oldGridVerticalPosition);
		if(foundBA == null) {
			 throw new InvalidInputException("A block does not exist at location " + oldGridHorizontalPosition +
					"/" + oldGridVerticalPosition + ".");
		}
		existingBA = foundLevel.findBlockAssignment(newGridHorizontalPosition, newGridVerticalPosition);
		if(existingBA != null) {
			throw new InvalidInputException("A block already exists at location " + newGridHorizontalPosition + 
					"/" + newGridVerticalPosition + ".");
		}
		try {
			foundBA.setGridHorizontalPosition(newGridHorizontalPosition);
			foundBA.setGridVerticalPosition(newGridVerticalPosition);
		} catch (RuntimeException e) {
			foundBA.setGridHorizontalPosition(oldGridHorizontalPosition);
			throw new InvalidInputException(e.getMessage());
		}
	}

	public static void removeBlock(int level, int gridHorizontalPosition, int gridVerticalPosition) throws InvalidInputException {
		if(!(Block223Application.getCurrentUserRole() instanceof Admin)) {
			throw new InvalidInputException("Admin privileges are required to remove a block. ");
		}
		Game game = Block223Application.getCurrentGame();
		if(Block223Application.getCurrentGame() == null) {
			throw new InvalidInputException("A game must be selected to remove a block. ");
		}
		if(Block223Application.getCurrentUserRole() != Block223Application.getCurrentGame().getAdmin()) {
			throw new InvalidInputException("Only the admin who created the game can remove a block. ");
		}
		Level thisLevel = game.getLevel(level-1);
		BlockAssignment assignment = thisLevel.findBlockAssignment(gridHorizontalPosition, gridVerticalPosition);
		if(assignment!=null) {
			assignment.delete();
		}
	}

	public static void saveGame() throws InvalidInputException {
		if(!(Block223Application.getCurrentUserRole() instanceof Admin)) {
			throw new InvalidInputException("Admin privileges are required to save a game. ");
		}
		if(Block223Application.getCurrentGame() == null) {
			throw new InvalidInputException("A game must be selected to save it. ");
		}
		if(Block223Application.getCurrentUserRole() != Block223Application.getCurrentGame().getAdmin()) {
			throw new InvalidInputException("Only the admin who created the game can save it. ");
		}
		Block223 block223 = Block223Application.getBlock223();
		try {
		Block223Persistence.save(block223); 
		} catch (RuntimeException e) {
			throw new InvalidInputException(e.getMessage());
		}
	}

	public static void register(String username, String playerPassword, String adminPassword) throws InvalidInputException {
		//String error = "";
		if(Block223Application.getCurrentUserRole() != null) {
			//error = "Cannot register a new user while a user is logged in. ";
			throw new InvalidInputException("Cannot register a new user while a user is logged in. ");
		}
		if(playerPassword == adminPassword){ // both used to be .trim()
			//error = error + "The passwords have to be different. ";
			throw new InvalidInputException("The passwords have to be different. ");
		}
		/*if (error.length() > 0) {
			throw new InvalidInputException(error.trim());
		}*/
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
		/*if(Block223Application.getBlock223().getCurrentUserRole != null) {
			throw new InvalidInputException("Cannot register a new user while a user is logged in.");
		}
		if(playerPassword.equals(adminPassword)) {
			throw new InvalidInputException("The passwords have to be different.");
		}*/
		
	}

	public static void login(String username, String password) throws InvalidInputException {
		//Should be changed back resetBlock223 only once persistence is fixed
		//At the moment load doesn't really work and it was just making a new 223
		//block every time I logged in meaning nothing would save between logins.
		if(Block223Application.getBlock223() == null) {
			Block223Application.resetBlock223();
		}
		//String error = "";
		if(Block223Application.getCurrentUserRole() != null) {
			throw new InvalidInputException("Cannot login a user while a user already logged in.");
		}
		User foundUser = User.getWithUsername(username);
		if(foundUser == null) {
			throw new InvalidInputException("The username and password do not match.");
			//throw new InvalidInputException(error);
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
			throw new InvalidInputException("The username and password do not match.\n"
					+ "");
		}
		
		/*if(error.length() > 0) {
			throw new InvalidInputException(error.trim());
		}*/
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
		String error = "";
		
		if (!( Block223Application.getCurrentUserRole() instanceof Admin)) {
			error = "Admin privileges are required to access game information.";
		}
		if (Block223Application.getCurrentGame() == null) {
			error = error + "A game must be selected to access its information.";
			throw new InvalidInputException(error);
		}
		if(Block223Application.getCurrentUserRole() != Block223Application.getCurrentGame().getAdmin()) {
			error = error + "Only the admin who created the game can access its information.";
		}
		if (error.length() > 0) {
			throw new InvalidInputException(error.trim());
		}
		
		Game game = Block223Application.getCurrentGame();
		TOGame to = new TOGame(game.getName(),game.getLevels().size(),game.getNrBlocksPerLevel(),game.getBall().getMinBallSpeedX(),game.getBall().getMinBallSpeedY(),game.getBall().getBallSpeedIncreaseFactor(),game.getPaddle().getMaxPaddleLength(),game.getPaddle().getMinPaddleLength());
		return to;
	}

	public static List<TOBlock> getBlocksOfCurrentDesignableGame() throws InvalidInputException {
		if(!(Block223Application.getCurrentUserRole() instanceof Admin)){
			throw new InvalidInputException("Admin privileges are required to access game information. ");
		}
		if(Block223Application.getCurrentGame() == null) {
			throw new InvalidInputException("A game must be selected to access its information. ");
		}
		if(Block223Application.getCurrentUserRole() != Block223Application.getCurrentGame().getAdmin()) {
			throw new InvalidInputException("Only the admin who created the game can access its information. ");
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
		if (!(Block223Application.getCurrentUserRole() instanceof Admin)) {
			throw new InvalidInputException("Admin privileges are required to access game information. ");
		}
		if (Block223Application.getCurrentGame() == null) {
			throw new InvalidInputException("A game must be selected to access its information. "); 	
		}
		if (Block223Application.getCurrentUserRole() != Block223Application.getCurrentGame().getAdmin()) {
			throw new InvalidInputException("Only the admin who created the game can access its information. ");
		}
		Game game = Block223Application.getCurrentGame();
		Block block = game.findBlock(id);
		if(block == null ) {
			throw new InvalidInputException("The block does not exist. ");
		}
		TOBlock to = new TOBlock(block.getId(), block.getRed(), block.getGreen(), block.getBlue(), block.getPoints());
		return to;
    }
    
	public static List<TOGridCell> getBlocksAtLevelOfCurrentDesignableGame(int level) throws InvalidInputException {
		if(!(Block223Application.getCurrentUserRole() instanceof Admin)) {
			throw new InvalidInputException("Admin privileges are required to access game information. ");
		}
		if(Block223Application.getCurrentGame() == null) {
			throw new InvalidInputException("A game must be selected to access its information. ");
		}
		if(Block223Application.getCurrentUserRole() != Block223Application.getCurrentGame().getAdmin()) {
			throw new InvalidInputException("Only the admin who created the game can access its information. ");
		}
		Game game = Block223Application.getCurrentGame();
		List<TOGridCell> result = new ArrayList<TOGridCell>();
		Level foundLevel = null;
		try {
			foundLevel = game.getLevel(level-1);
		} catch (IndexOutOfBoundsException e) {
			throw new InvalidInputException("Level " + level + " does not exist for the game. ");
		}
		List<BlockAssignment> assignments = foundLevel.getBlockAssignments();
		for(BlockAssignment assignment: assignments) {
			TOGridCell to = new TOGridCell(assignment.getGridHorizontalPosition(), assignment.getGridVerticalPosition(),
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