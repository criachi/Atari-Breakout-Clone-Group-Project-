/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse223.block.model;
import java.util.*;

// line 3 "../../../../../Block223.ump"
public class BlockApplication
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //BlockApplication Associations
  private List<User> users;
  private List<BlockGame> blockGames;
  private List<Player> players;
  private List<Admin> admins;
  private List<GridCell> gridCells;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public BlockApplication()
  {
    users = new ArrayList<User>();
    blockGames = new ArrayList<BlockGame>();
    players = new ArrayList<Player>();
    admins = new ArrayList<Admin>();
    gridCells = new ArrayList<GridCell>();
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetMany */
  public User getUser(int index)
  {
    User aUser = users.get(index);
    return aUser;
  }

  public List<User> getUsers()
  {
    List<User> newUsers = Collections.unmodifiableList(users);
    return newUsers;
  }

  public int numberOfUsers()
  {
    int number = users.size();
    return number;
  }

  public boolean hasUsers()
  {
    boolean has = users.size() > 0;
    return has;
  }

  public int indexOfUser(User aUser)
  {
    int index = users.indexOf(aUser);
    return index;
  }
  /* Code from template association_GetMany */
  public BlockGame getBlockGame(int index)
  {
    BlockGame aBlockGame = blockGames.get(index);
    return aBlockGame;
  }

  public List<BlockGame> getBlockGames()
  {
    List<BlockGame> newBlockGames = Collections.unmodifiableList(blockGames);
    return newBlockGames;
  }

  public int numberOfBlockGames()
  {
    int number = blockGames.size();
    return number;
  }

  public boolean hasBlockGames()
  {
    boolean has = blockGames.size() > 0;
    return has;
  }

  public int indexOfBlockGame(BlockGame aBlockGame)
  {
    int index = blockGames.indexOf(aBlockGame);
    return index;
  }
  /* Code from template association_GetMany */
  public Player getPlayer(int index)
  {
    Player aPlayer = players.get(index);
    return aPlayer;
  }

  public List<Player> getPlayers()
  {
    List<Player> newPlayers = Collections.unmodifiableList(players);
    return newPlayers;
  }

  public int numberOfPlayers()
  {
    int number = players.size();
    return number;
  }

  public boolean hasPlayers()
  {
    boolean has = players.size() > 0;
    return has;
  }

  public int indexOfPlayer(Player aPlayer)
  {
    int index = players.indexOf(aPlayer);
    return index;
  }
  /* Code from template association_GetMany */
  public Admin getAdmin(int index)
  {
    Admin aAdmin = admins.get(index);
    return aAdmin;
  }

  public List<Admin> getAdmins()
  {
    List<Admin> newAdmins = Collections.unmodifiableList(admins);
    return newAdmins;
  }

  public int numberOfAdmins()
  {
    int number = admins.size();
    return number;
  }

  public boolean hasAdmins()
  {
    boolean has = admins.size() > 0;
    return has;
  }

  public int indexOfAdmin(Admin aAdmin)
  {
    int index = admins.indexOf(aAdmin);
    return index;
  }
  /* Code from template association_GetMany */
  public GridCell getGridCell(int index)
  {
    GridCell aGridCell = gridCells.get(index);
    return aGridCell;
  }

  public List<GridCell> getGridCells()
  {
    List<GridCell> newGridCells = Collections.unmodifiableList(gridCells);
    return newGridCells;
  }

  public int numberOfGridCells()
  {
    int number = gridCells.size();
    return number;
  }

  public boolean hasGridCells()
  {
    boolean has = gridCells.size() > 0;
    return has;
  }

  public int indexOfGridCell(GridCell aGridCell)
  {
    int index = gridCells.indexOf(aGridCell);
    return index;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfUsers()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public User addUser(String aName, Player aPlayer, HallOfFame aHallOfFame)
  {
    return new User(aName, aPlayer, this, aHallOfFame);
  }

  public boolean addUser(User aUser)
  {
    boolean wasAdded = false;
    if (users.contains(aUser)) { return false; }
    BlockApplication existingBlockApplication = aUser.getBlockApplication();
    boolean isNewBlockApplication = existingBlockApplication != null && !this.equals(existingBlockApplication);
    if (isNewBlockApplication)
    {
      aUser.setBlockApplication(this);
    }
    else
    {
      users.add(aUser);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeUser(User aUser)
  {
    boolean wasRemoved = false;
    //Unable to remove aUser, as it must always have a blockApplication
    if (!this.equals(aUser.getBlockApplication()))
    {
      users.remove(aUser);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addUserAt(User aUser, int index)
  {  
    boolean wasAdded = false;
    if(addUser(aUser))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfUsers()) { index = numberOfUsers() - 1; }
      users.remove(aUser);
      users.add(index, aUser);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveUserAt(User aUser, int index)
  {
    boolean wasAdded = false;
    if(users.contains(aUser))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfUsers()) { index = numberOfUsers() - 1; }
      users.remove(aUser);
      users.add(index, aUser);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addUserAt(aUser, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfBlockGames()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public BlockGame addBlockGame(String aName, int aNumLevels, HallOfFame aHallOfFame, PlayArea aPlayArea, Player aPlayer, Admin aAdmin)
  {
    return new BlockGame(aName, aNumLevels, aHallOfFame, aPlayArea, this, aPlayer, aAdmin);
  }

  public boolean addBlockGame(BlockGame aBlockGame)
  {
    boolean wasAdded = false;
    if (blockGames.contains(aBlockGame)) { return false; }
    BlockApplication existingBlockApplication = aBlockGame.getBlockApplication();
    boolean isNewBlockApplication = existingBlockApplication != null && !this.equals(existingBlockApplication);
    if (isNewBlockApplication)
    {
      aBlockGame.setBlockApplication(this);
    }
    else
    {
      blockGames.add(aBlockGame);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeBlockGame(BlockGame aBlockGame)
  {
    boolean wasRemoved = false;
    //Unable to remove aBlockGame, as it must always have a blockApplication
    if (!this.equals(aBlockGame.getBlockApplication()))
    {
      blockGames.remove(aBlockGame);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addBlockGameAt(BlockGame aBlockGame, int index)
  {  
    boolean wasAdded = false;
    if(addBlockGame(aBlockGame))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfBlockGames()) { index = numberOfBlockGames() - 1; }
      blockGames.remove(aBlockGame);
      blockGames.add(index, aBlockGame);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveBlockGameAt(BlockGame aBlockGame, int index)
  {
    boolean wasAdded = false;
    if(blockGames.contains(aBlockGame))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfBlockGames()) { index = numberOfBlockGames() - 1; }
      blockGames.remove(aBlockGame);
      blockGames.add(index, aBlockGame);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addBlockGameAt(aBlockGame, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfPlayers()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Player addPlayer(String aPassword, User aUser)
  {
    return new Player(aPassword, this, aUser);
  }

  public boolean addPlayer(Player aPlayer)
  {
    boolean wasAdded = false;
    if (players.contains(aPlayer)) { return false; }
    BlockApplication existingBlockApplication = aPlayer.getBlockApplication();
    boolean isNewBlockApplication = existingBlockApplication != null && !this.equals(existingBlockApplication);
    if (isNewBlockApplication)
    {
      aPlayer.setBlockApplication(this);
    }
    else
    {
      players.add(aPlayer);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removePlayer(Player aPlayer)
  {
    boolean wasRemoved = false;
    //Unable to remove aPlayer, as it must always have a blockApplication
    if (!this.equals(aPlayer.getBlockApplication()))
    {
      players.remove(aPlayer);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addPlayerAt(Player aPlayer, int index)
  {  
    boolean wasAdded = false;
    if(addPlayer(aPlayer))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfPlayers()) { index = numberOfPlayers() - 1; }
      players.remove(aPlayer);
      players.add(index, aPlayer);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMovePlayerAt(Player aPlayer, int index)
  {
    boolean wasAdded = false;
    if(players.contains(aPlayer))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfPlayers()) { index = numberOfPlayers() - 1; }
      players.remove(aPlayer);
      players.add(index, aPlayer);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addPlayerAt(aPlayer, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfAdmins()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Admin addAdmin(String aPassword, User aUser)
  {
    return new Admin(aPassword, this, aUser);
  }

  public boolean addAdmin(Admin aAdmin)
  {
    boolean wasAdded = false;
    if (admins.contains(aAdmin)) { return false; }
    BlockApplication existingBlockApplication = aAdmin.getBlockApplication();
    boolean isNewBlockApplication = existingBlockApplication != null && !this.equals(existingBlockApplication);
    if (isNewBlockApplication)
    {
      aAdmin.setBlockApplication(this);
    }
    else
    {
      admins.add(aAdmin);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeAdmin(Admin aAdmin)
  {
    boolean wasRemoved = false;
    //Unable to remove aAdmin, as it must always have a blockApplication
    if (!this.equals(aAdmin.getBlockApplication()))
    {
      admins.remove(aAdmin);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addAdminAt(Admin aAdmin, int index)
  {  
    boolean wasAdded = false;
    if(addAdmin(aAdmin))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfAdmins()) { index = numberOfAdmins() - 1; }
      admins.remove(aAdmin);
      admins.add(index, aAdmin);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveAdminAt(Admin aAdmin, int index)
  {
    boolean wasAdded = false;
    if(admins.contains(aAdmin))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfAdmins()) { index = numberOfAdmins() - 1; }
      admins.remove(aAdmin);
      admins.add(index, aAdmin);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addAdminAt(aAdmin, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfGridCells()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public GridCell addGridCell(int aRow, int aCol, Block aBlock)
  {
    return new GridCell(aRow, aCol, this, aBlock);
  }

  public boolean addGridCell(GridCell aGridCell)
  {
    boolean wasAdded = false;
    if (gridCells.contains(aGridCell)) { return false; }
    BlockApplication existingBlockApplication = aGridCell.getBlockApplication();
    boolean isNewBlockApplication = existingBlockApplication != null && !this.equals(existingBlockApplication);
    if (isNewBlockApplication)
    {
      aGridCell.setBlockApplication(this);
    }
    else
    {
      gridCells.add(aGridCell);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeGridCell(GridCell aGridCell)
  {
    boolean wasRemoved = false;
    //Unable to remove aGridCell, as it must always have a blockApplication
    if (!this.equals(aGridCell.getBlockApplication()))
    {
      gridCells.remove(aGridCell);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addGridCellAt(GridCell aGridCell, int index)
  {  
    boolean wasAdded = false;
    if(addGridCell(aGridCell))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfGridCells()) { index = numberOfGridCells() - 1; }
      gridCells.remove(aGridCell);
      gridCells.add(index, aGridCell);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveGridCellAt(GridCell aGridCell, int index)
  {
    boolean wasAdded = false;
    if(gridCells.contains(aGridCell))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfGridCells()) { index = numberOfGridCells() - 1; }
      gridCells.remove(aGridCell);
      gridCells.add(index, aGridCell);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addGridCellAt(aGridCell, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    while (users.size() > 0)
    {
      User aUser = users.get(users.size() - 1);
      aUser.delete();
      users.remove(aUser);
    }
    
    while (blockGames.size() > 0)
    {
      BlockGame aBlockGame = blockGames.get(blockGames.size() - 1);
      aBlockGame.delete();
      blockGames.remove(aBlockGame);
    }
    
    while (players.size() > 0)
    {
      Player aPlayer = players.get(players.size() - 1);
      aPlayer.delete();
      players.remove(aPlayer);
    }
    
    while (admins.size() > 0)
    {
      Admin aAdmin = admins.get(admins.size() - 1);
      aAdmin.delete();
      admins.remove(aAdmin);
    }
    
    while (gridCells.size() > 0)
    {
      GridCell aGridCell = gridCells.get(gridCells.size() - 1);
      aGridCell.delete();
      gridCells.remove(aGridCell);
    }
    
  }

}