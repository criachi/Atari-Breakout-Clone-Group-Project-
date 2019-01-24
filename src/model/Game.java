/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package model;
import java.util.*;

// line 21 "../Block223.ump"
public class Game
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static Map<String, Game> gamesByName = new HashMap<String, Game>();

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Game Attributes
  private String name;
  private int playAreaSize;
  private double speedIncreaseFactor;
  private int minBallSpeed;
  private int maxBallSpeed;
  private int minPaddleLength;
  private int maxPaddleLength;

  //Game Associations
  private List<Level> levels;
  private HallOfFame hallOfFame;
  private Player player;
  private Admin admin;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Game(String aName, int aPlayAreaSize, double aSpeedIncreaseFactor, int aMinBallSpeed, int aMaxBallSpeed, int aMinPaddleLength, int aMaxPaddleLength, HallOfFame aHallOfFame, Player aPlayer, Admin aAdmin)
  {
    playAreaSize = aPlayAreaSize;
    speedIncreaseFactor = aSpeedIncreaseFactor;
    minBallSpeed = aMinBallSpeed;
    maxBallSpeed = aMaxBallSpeed;
    minPaddleLength = aMinPaddleLength;
    maxPaddleLength = aMaxPaddleLength;
    if (!setName(aName))
    {
      throw new RuntimeException("Cannot create due to duplicate name");
    }
    levels = new ArrayList<Level>();
    if (aHallOfFame == null || aHallOfFame.getGame() != null)
    {
      throw new RuntimeException("Unable to create Game due to aHallOfFame");
    }
    hallOfFame = aHallOfFame;
    boolean didAddPlayer = setPlayer(aPlayer);
    if (!didAddPlayer)
    {
      throw new RuntimeException("Unable to create game due to player");
    }
    boolean didAddAdmin = setAdmin(aAdmin);
    if (!didAddAdmin)
    {
      throw new RuntimeException("Unable to create game due to admin");
    }
  }

  public Game(String aName, int aPlayAreaSize, double aSpeedIncreaseFactor, int aMinBallSpeed, int aMaxBallSpeed, int aMinPaddleLength, int aMaxPaddleLength, Player aPlayer, Admin aAdmin)
  {
    name = aName;
    playAreaSize = aPlayAreaSize;
    speedIncreaseFactor = aSpeedIncreaseFactor;
    minBallSpeed = aMinBallSpeed;
    maxBallSpeed = aMaxBallSpeed;
    minPaddleLength = aMinPaddleLength;
    maxPaddleLength = aMaxPaddleLength;
    levels = new ArrayList<Level>();
    hallOfFame = new HallOfFame(this);
    boolean didAddPlayer = setPlayer(aPlayer);
    if (!didAddPlayer)
    {
      throw new RuntimeException("Unable to create game due to player");
    }
    boolean didAddAdmin = setAdmin(aAdmin);
    if (!didAddAdmin)
    {
      throw new RuntimeException("Unable to create game due to admin");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setName(String aName)
  {
    boolean wasSet = false;
    String anOldName = getName();
    if (hasWithName(aName)) {
      return wasSet;
    }
    name = aName;
    wasSet = true;
    if (anOldName != null) {
      gamesByName.remove(anOldName);
    }
    gamesByName.put(aName, this);
    return wasSet;
  }

  public boolean setPlayAreaSize(int aPlayAreaSize)
  {
    boolean wasSet = false;
    playAreaSize = aPlayAreaSize;
    wasSet = true;
    return wasSet;
  }

  public boolean setSpeedIncreaseFactor(double aSpeedIncreaseFactor)
  {
    boolean wasSet = false;
    speedIncreaseFactor = aSpeedIncreaseFactor;
    wasSet = true;
    return wasSet;
  }

  public boolean setMinBallSpeed(int aMinBallSpeed)
  {
    boolean wasSet = false;
    minBallSpeed = aMinBallSpeed;
    wasSet = true;
    return wasSet;
  }

  public boolean setMaxBallSpeed(int aMaxBallSpeed)
  {
    boolean wasSet = false;
    maxBallSpeed = aMaxBallSpeed;
    wasSet = true;
    return wasSet;
  }

  public boolean setMinPaddleLength(int aMinPaddleLength)
  {
    boolean wasSet = false;
    minPaddleLength = aMinPaddleLength;
    wasSet = true;
    return wasSet;
  }

  public boolean setMaxPaddleLength(int aMaxPaddleLength)
  {
    boolean wasSet = false;
    maxPaddleLength = aMaxPaddleLength;
    wasSet = true;
    return wasSet;
  }

  public String getName()
  {
    return name;
  }
  /* Code from template attribute_GetUnique */
  public static Game getWithName(String aName)
  {
    return gamesByName.get(aName);
  }
  /* Code from template attribute_HasUnique */
  public static boolean hasWithName(String aName)
  {
    return getWithName(aName) != null;
  }

  public int getPlayAreaSize()
  {
    return playAreaSize;
  }

  public double getSpeedIncreaseFactor()
  {
    return speedIncreaseFactor;
  }

  public int getMinBallSpeed()
  {
    return minBallSpeed;
  }

  public int getMaxBallSpeed()
  {
    return maxBallSpeed;
  }

  public int getMinPaddleLength()
  {
    return minPaddleLength;
  }

  public int getMaxPaddleLength()
  {
    return maxPaddleLength;
  }
  /* Code from template association_GetMany */
  public Level getLevel(int index)
  {
    Level aLevel = levels.get(index);
    return aLevel;
  }

  public List<Level> getLevels()
  {
    List<Level> newLevels = Collections.unmodifiableList(levels);
    return newLevels;
  }

  public int numberOfLevels()
  {
    int number = levels.size();
    return number;
  }

  public boolean hasLevels()
  {
    boolean has = levels.size() > 0;
    return has;
  }

  public int indexOfLevel(Level aLevel)
  {
    int index = levels.indexOf(aLevel);
    return index;
  }
  /* Code from template association_GetOne */
  public HallOfFame getHallOfFame()
  {
    return hallOfFame;
  }
  /* Code from template association_GetOne */
  public Player getPlayer()
  {
    return player;
  }
  /* Code from template association_GetOne */
  public Admin getAdmin()
  {
    return admin;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfLevels()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Level addLevel(int aLevelNumber, Ball aBall, Paddle aPaddle)
  {
    return new Level(aLevelNumber, aBall, aPaddle, this);
  }

  public boolean addLevel(Level aLevel)
  {
    boolean wasAdded = false;
    if (levels.contains(aLevel)) { return false; }
    Game existingGame = aLevel.getGame();
    boolean isNewGame = existingGame != null && !this.equals(existingGame);
    if (isNewGame)
    {
      aLevel.setGame(this);
    }
    else
    {
      levels.add(aLevel);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeLevel(Level aLevel)
  {
    boolean wasRemoved = false;
    //Unable to remove aLevel, as it must always have a game
    if (!this.equals(aLevel.getGame()))
    {
      levels.remove(aLevel);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addLevelAt(Level aLevel, int index)
  {  
    boolean wasAdded = false;
    if(addLevel(aLevel))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfLevels()) { index = numberOfLevels() - 1; }
      levels.remove(aLevel);
      levels.add(index, aLevel);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveLevelAt(Level aLevel, int index)
  {
    boolean wasAdded = false;
    if(levels.contains(aLevel))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfLevels()) { index = numberOfLevels() - 1; }
      levels.remove(aLevel);
      levels.add(index, aLevel);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addLevelAt(aLevel, index);
    }
    return wasAdded;
  }
  /* Code from template association_SetOneToMany */
  public boolean setPlayer(Player aPlayer)
  {
    boolean wasSet = false;
    if (aPlayer == null)
    {
      return wasSet;
    }

    Player existingPlayer = player;
    player = aPlayer;
    if (existingPlayer != null && !existingPlayer.equals(aPlayer))
    {
      existingPlayer.removeGame(this);
    }
    player.addGame(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOneToMany */
  public boolean setAdmin(Admin aAdmin)
  {
    boolean wasSet = false;
    if (aAdmin == null)
    {
      return wasSet;
    }

    Admin existingAdmin = admin;
    admin = aAdmin;
    if (existingAdmin != null && !existingAdmin.equals(aAdmin))
    {
      existingAdmin.removeGame(this);
    }
    admin.addGame(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    gamesByName.remove(getName());
    while (levels.size() > 0)
    {
      Level aLevel = levels.get(levels.size() - 1);
      aLevel.delete();
      levels.remove(aLevel);
    }
    
    HallOfFame existingHallOfFame = hallOfFame;
    hallOfFame = null;
    if (existingHallOfFame != null)
    {
      existingHallOfFame.delete();
    }
    Player placeholderPlayer = player;
    this.player = null;
    if(placeholderPlayer != null)
    {
      placeholderPlayer.removeGame(this);
    }
    Admin placeholderAdmin = admin;
    this.admin = null;
    if(placeholderAdmin != null)
    {
      placeholderAdmin.removeGame(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "name" + ":" + getName()+ "," +
            "playAreaSize" + ":" + getPlayAreaSize()+ "," +
            "speedIncreaseFactor" + ":" + getSpeedIncreaseFactor()+ "," +
            "minBallSpeed" + ":" + getMinBallSpeed()+ "," +
            "maxBallSpeed" + ":" + getMaxBallSpeed()+ "," +
            "minPaddleLength" + ":" + getMinPaddleLength()+ "," +
            "maxPaddleLength" + ":" + getMaxPaddleLength()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "hallOfFame = "+(getHallOfFame()!=null?Integer.toHexString(System.identityHashCode(getHallOfFame())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "player = "+(getPlayer()!=null?Integer.toHexString(System.identityHashCode(getPlayer())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "admin = "+(getAdmin()!=null?Integer.toHexString(System.identityHashCode(getAdmin())):"null");
  }
}