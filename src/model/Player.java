/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package model;
import java.util.*;

// line 10 "../Block223.ump"
public class Player
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Player Attributes
  private String password;
  private int currentLevel;

  //Player Associations
  private List<Game> games;
  private User user;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Player(String aPassword, int aCurrentLevel, User aUser)
  {
    password = aPassword;
    currentLevel = aCurrentLevel;
    games = new ArrayList<Game>();
    if (aUser == null || aUser.getPlayer() != null)
    {
      throw new RuntimeException("Unable to create Player due to aUser");
    }
    user = aUser;
  }

  public Player(String aPassword, int aCurrentLevel, String aNameForUser, HallOfFame aHallOfFameForUser)
  {
    password = aPassword;
    currentLevel = aCurrentLevel;
    games = new ArrayList<Game>();
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

  public boolean setCurrentLevel(int aCurrentLevel)
  {
    boolean wasSet = false;
    currentLevel = aCurrentLevel;
    wasSet = true;
    return wasSet;
  }

  public String getPassword()
  {
    return password;
  }

  public int getCurrentLevel()
  {
    return currentLevel;
  }
  /* Code from template association_GetMany */
  public Game getGame(int index)
  {
    Game aGame = games.get(index);
    return aGame;
  }

  public List<Game> getGames()
  {
    List<Game> newGames = Collections.unmodifiableList(games);
    return newGames;
  }

  public int numberOfGames()
  {
    int number = games.size();
    return number;
  }

  public boolean hasGames()
  {
    boolean has = games.size() > 0;
    return has;
  }

  public int indexOfGame(Game aGame)
  {
    int index = games.indexOf(aGame);
    return index;
  }
  /* Code from template association_GetOne */
  public User getUser()
  {
    return user;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfGames()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Game addGame(String aName, int aPlayAreaSize, double aSpeedIncreaseFactor, int aMinBallSpeed, int aMaxBallSpeed, int aMinPaddleLength, int aMaxPaddleLength, HallOfFame aHallOfFame, Admin aAdmin)
  {
    return new Game(aName, aPlayAreaSize, aSpeedIncreaseFactor, aMinBallSpeed, aMaxBallSpeed, aMinPaddleLength, aMaxPaddleLength, aHallOfFame, this, aAdmin);
  }

  public boolean addGame(Game aGame)
  {
    boolean wasAdded = false;
    if (games.contains(aGame)) { return false; }
    Player existingPlayer = aGame.getPlayer();
    boolean isNewPlayer = existingPlayer != null && !this.equals(existingPlayer);
    if (isNewPlayer)
    {
      aGame.setPlayer(this);
    }
    else
    {
      games.add(aGame);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeGame(Game aGame)
  {
    boolean wasRemoved = false;
    //Unable to remove aGame, as it must always have a player
    if (!this.equals(aGame.getPlayer()))
    {
      games.remove(aGame);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addGameAt(Game aGame, int index)
  {  
    boolean wasAdded = false;
    if(addGame(aGame))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfGames()) { index = numberOfGames() - 1; }
      games.remove(aGame);
      games.add(index, aGame);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveGameAt(Game aGame, int index)
  {
    boolean wasAdded = false;
    if(games.contains(aGame))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfGames()) { index = numberOfGames() - 1; }
      games.remove(aGame);
      games.add(index, aGame);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addGameAt(aGame, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    for(int i=games.size(); i > 0; i--)
    {
      Game aGame = games.get(i - 1);
      aGame.delete();
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
            "password" + ":" + getPassword()+ "," +
            "currentLevel" + ":" + getCurrentLevel()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "user = "+(getUser()!=null?Integer.toHexString(System.identityHashCode(getUser())):"null");
  }
}