package ca.mcgill.ecse223.block.controller;

import java.util.ArrayList;
import java.util.List;

import ca.mcgill.ecse223.block.application.Block223Application;
import ca.mcgill.ecse223.block.controller.TOUserMode.Mode;
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

	public static void updateBlock(int id, int red, int green, int blue, int points) throws InvalidInputException {
	}

	public static void positionBlock(int id, int level, int gridHorizontalPosition, int gridVerticalPosition)
			throws InvalidInputException {
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
			error = error + " Level" + level + " does not exist for the game. ";
		}
		List<Block> blocks = game.getBlocks();
		if(blocks.size() == game.getNrBlocksPerLevel()) {
			error = error + "The number of blocks has reached the maximum number (" + game.getNrBlocksPerLevel() + ")"
					+ "allowed for this game. ";
		}
		List<BlockAssignment> blockAssignments = foundLevel.getBlockAssignments();
		BlockAssignment ba = null;
		for (BlockAssignment blockAssignment: blockAssignments) {
			if(blockAssignment.getGridHorizontalPosition() == gridHorizontalPosition && 
					blockAssignment.getGridVerticalPosition() == gridVerticalPosition) {
				ba = blockAssignment;
			}
		}
		if(ba != null) {
			error = error + "A block already exists at location " + gridHorizontalPosition + "/" + gridVerticalPosition
					+ ". ";
		}
		Block foundBlock = null;
		for(Block block: blocks) {
			int foundId = block.getId();
			if(foundId == id) {
				foundBlock = block;
				break;
			}
		}
		if(foundBlock == null) {
			error = error + "The block does not exist. ";
		}
		if(error.length() > 0) {
			throw new InvalidInputException(error.trim());
		}
		try {
			foundLevel.addBlockAssignment(gridHorizontalPosition, gridVerticalPosition, foundBlock, game);
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

	public static void removeBlock(int level, int gridHorizontalPosition, int gridVerticalPosition)
			throws InvalidInputException {
	}

	public static void saveGame() throws InvalidInputException {
	}

	public static void register(String username, String playerPassword, String adminPassword)
			throws InvalidInputException {
	}

	public static void login(String username, String password) throws InvalidInputException {
		String error = "";
		if(Block223Application.getCurrentUserRole() != null) {
			error = "Cannot login a user while a user already logged in. ";
		}
		User foundUser = User.getWithUsername(username);
		if(foundUser == null) {
			error = error + "The username and password do not match. ";
		}
		String foundPassword = null;
		List<UserRole> users = foundUser.getRoles();
		for(UserRole user: users) {
			String rolePassword = user.getPassword();
			if(rolePassword == password) {
				Block223Application.setCurrentUserRole(user);
				Block223Application.getBlock223();
			}
		}
		if(foundPassword == null) {
			error = error + "The username and password do not match. ";
		}
		if(error.length() > 0) {
			throw new InvalidInputException(error.trim());
		}
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
		return null;
	}

	public List<TOGridCell> getBlocksAtLevelOfCurrentDesignableGame(int level) throws InvalidInputException {
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
		if(error.length() > 0) {
			throw new InvalidInputException(error.trim());
		}
		List<TOGridCell> result = new ArrayList<TOGridCell>();
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