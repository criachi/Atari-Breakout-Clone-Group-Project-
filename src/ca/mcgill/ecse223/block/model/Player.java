/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse223.block.model;
import java.util.*;

// line 9 "../../../../../Block223.ump"
public class Player
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Player Attributes
  private String password;

  //Player Associations
  private List<BlockGame> blockGames;
  private User user;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Player(String aPassword, User aUser)
  {
    password = aPassword;
    blockGames = new ArrayList<BlockGame>();
    if (aUser == null || aUser.getPlayer() != null)
    {
      throw new RuntimeException("Unable to create Player due to aUser");
    }
    user = aUser;
  }

  public Player(String aPassword, String aNameForUser, HallOfFame aHallOfFameForUser)
  {
    password = aPassword;
    blockGames = new ArrayList<BlockGame>();
    user = new User(aNameForUser, this, aHallOfFameForUser);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setPassword(String aPassword)
  {
    boolean wasSet = false;
    password = aPassword;
    wasSet = true;
    return wasSet;
  }

  public String getPassword()
  {
    return password;
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
  /* Code from template association_GetOne */
  public User getUser()
  {
    return user;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfBlockGames()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public BlockGame addBlockGame(String aName, int aNumLevels, HallOfFame aHallOfFame, PlayArea aPlayArea, Admin aAdmin)
  {
    return new BlockGame(aName, aNumLevels, aHallOfFame, aPlayArea, this, aAdmin);
  }

  public boolean addBlockGame(BlockGame aBlockGame)
  {
    boolean wasAdded = false;
    if (blockGames.contains(aBlockGame)) { return false; }
    Player existingPlayer = aBlockGame.getPlayer();
    boolean isNewPlayer = existingPlayer != null && !this.equals(existingPlayer);
    if (isNewPlayer)
    {
      aBlockGame.setPlayer(this);
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
    //Unable to remove aBlockGame, as it must always have a player
    if (!this.equals(aBlockGame.getPlayer()))
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

  public void delete()
  {
    for(int i=blockGames.size(); i > 0; i--)
    {
      BlockGame aBlockGame = blockGames.get(i - 1);
      aBlockGame.delete();
    }
    User existingUser = user;
    user = null;
    if (existingUser != null)
    {
      existingUser.delete();
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "password" + ":" + getPassword()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "user = "+(getUser()!=null?Integer.toHexString(System.identityHashCode(getUser())):"null");
  }
}