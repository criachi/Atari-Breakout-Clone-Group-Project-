/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse223.block.model;
import java.util.*;

// line 26 "../../../../../Block223.ump"
public class BlockGame
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static Map<String, BlockGame> blockgamesByName = new HashMap<String, BlockGame>();

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //BlockGame Attributes
  private String name;
  private int numLevels;

  //BlockGame Associations
  private List<Level> levels;
  private HallOfFame hallOfFame;
  private PlayArea playArea;
  private List<BlockType> blockTypes;
  private BlockApplication blockApplication;
  private Player player;
  private Admin admin;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public BlockGame(String aName, int aNumLevels, HallOfFame aHallOfFame, PlayArea aPlayArea, BlockApplication aBlockApplication, Player aPlayer, Admin aAdmin)
  {
    numLevels = aNumLevels;
    if (!setName(aName))
    {
      throw new RuntimeException("Cannot create due to duplicate name");
    }
    levels = new ArrayList<Level>();
    if (aHallOfFame == null || aHallOfFame.getBlockGame() != null)
    {
      throw new RuntimeException("Unable to create BlockGame due to aHallOfFame");
    }
    hallOfFame = aHallOfFame;
    if (aPlayArea == null || aPlayArea.getBlockGame() != null)
    {
      throw new RuntimeException("Unable to create BlockGame due to aPlayArea");
    }
    playArea = aPlayArea;
    blockTypes = new ArrayList<BlockType>();
    boolean didAddBlockApplication = setBlockApplication(aBlockApplication);
    if (!didAddBlockApplication)
    {
      throw new RuntimeException("Unable to create blockGame due to blockApplication");
    }
    boolean didAddPlayer = setPlayer(aPlayer);
    if (!didAddPlayer)
    {
      throw new RuntimeException("Unable to create blockGame due to player");
    }
    boolean didAddAdmin = setAdmin(aAdmin);
    if (!didAddAdmin)
    {
      throw new RuntimeException("Unable to create blockGame due to admin");
    }
  }

  public BlockGame(String aName, int aNumLevels, int aWidthForPlayArea, int aLengthForPlayArea, BlockApplication aBlockApplication, Player aPlayer, Admin aAdmin)
  {
    name = aName;
    numLevels = aNumLevels;
    levels = new ArrayList<Level>();
    hallOfFame = new HallOfFame(this);
    playArea = new PlayArea(aWidthForPlayArea, aLengthForPlayArea, this);
    blockTypes = new ArrayList<BlockType>();
    boolean didAddBlockApplication = setBlockApplication(aBlockApplication);
    if (!didAddBlockApplication)
    {
      throw new RuntimeException("Unable to create blockGame due to blockApplication");
    }
    boolean didAddPlayer = setPlayer(aPlayer);
    if (!didAddPlayer)
    {
      throw new RuntimeException("Unable to create blockGame due to player");
    }
    boolean didAddAdmin = setAdmin(aAdmin);
    if (!didAddAdmin)
    {
      throw new RuntimeException("Unable to create blockGame due to admin");
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
      blockgamesByName.remove(anOldName);
    }
    blockgamesByName.put(aName, this);
    return wasSet;
  }

  public boolean setNumLevels(int aNumLevels)
  {
    boolean wasSet = false;
    numLevels = aNumLevels;
    wasSet = true;
    return wasSet;
  }

  public String getName()
  {
    return name;
  }
  /* Code from template attribute_GetUnique */
  public static BlockGame getWithName(String aName)
  {
    return blockgamesByName.get(aName);
  }
  /* Code from template attribute_HasUnique */
  public static boolean hasWithName(String aName)
  {
    return getWithName(aName) != null;
  }

  public int getNumLevels()
  {
    return numLevels;
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
  public PlayArea getPlayArea()
  {
    return playArea;
  }
  /* Code from template association_GetMany */
  public BlockType getBlockType(int index)
  {
    BlockType aBlockType = blockTypes.get(index);
    return aBlockType;
  }

  public List<BlockType> getBlockTypes()
  {
    List<BlockType> newBlockTypes = Collections.unmodifiableList(blockTypes);
    return newBlockTypes;
  }

  public int numberOfBlockTypes()
  {
    int number = blockTypes.size();
    return number;
  }

  public boolean hasBlockTypes()
  {
    boolean has = blockTypes.size() > 0;
    return has;
  }

  public int indexOfBlockType(BlockType aBlockType)
  {
    int index = blockTypes.indexOf(aBlockType);
    return index;
  }
  /* Code from template association_GetOne */
  public BlockApplication getBlockApplication()
  {
    return blockApplication;
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
    BlockGame existingBlockGame = aLevel.getBlockGame();
    boolean isNewBlockGame = existingBlockGame != null && !this.equals(existingBlockGame);
    if (isNewBlockGame)
    {
      aLevel.setBlockGame(this);
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
    //Unable to remove aLevel, as it must always have a blockGame
    if (!this.equals(aLevel.getBlockGame()))
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
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfBlockTypes()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public BlockType addBlockType(int aSize, int aPoints, BlockType.Color aColor)
  {
    return new BlockType(aSize, aPoints, aColor, this);
  }

  public boolean addBlockType(BlockType aBlockType)
  {
    boolean wasAdded = false;
    if (blockTypes.contains(aBlockType)) { return false; }
    BlockGame existingBlockGame = aBlockType.getBlockGame();
    boolean isNewBlockGame = existingBlockGame != null && !this.equals(existingBlockGame);
    if (isNewBlockGame)
    {
      aBlockType.setBlockGame(this);
    }
    else
    {
      blockTypes.add(aBlockType);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeBlockType(BlockType aBlockType)
  {
    boolean wasRemoved = false;
    //Unable to remove aBlockType, as it must always have a blockGame
    if (!this.equals(aBlockType.getBlockGame()))
    {
      blockTypes.remove(aBlockType);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addBlockTypeAt(BlockType aBlockType, int index)
  {  
    boolean wasAdded = false;
    if(addBlockType(aBlockType))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfBlockTypes()) { index = numberOfBlockTypes() - 1; }
      blockTypes.remove(aBlockType);
      blockTypes.add(index, aBlockType);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveBlockTypeAt(BlockType aBlockType, int index)
  {
    boolean wasAdded = false;
    if(blockTypes.contains(aBlockType))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfBlockTypes()) { index = numberOfBlockTypes() - 1; }
      blockTypes.remove(aBlockType);
      blockTypes.add(index, aBlockType);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addBlockTypeAt(aBlockType, index);
    }
    return wasAdded;
  }
  /* Code from template association_SetOneToMany */
  public boolean setBlockApplication(BlockApplication aBlockApplication)
  {
    boolean wasSet = false;
    if (aBlockApplication == null)
    {
      return wasSet;
    }

    BlockApplication existingBlockApplication = blockApplication;
    blockApplication = aBlockApplication;
    if (existingBlockApplication != null && !existingBlockApplication.equals(aBlockApplication))
    {
      existingBlockApplication.removeBlockGame(this);
    }
    blockApplication.addBlockGame(this);
    wasSet = true;
    return wasSet;
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
      existingPlayer.removeBlockGame(this);
    }
    player.addBlockGame(this);
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
      existingAdmin.removeBlockGame(this);
    }
    admin.addBlockGame(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    blockgamesByName.remove(getName());
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
    PlayArea existingPlayArea = playArea;
    playArea = null;
    if (existingPlayArea != null)
    {
      existingPlayArea.delete();
    }
    while (blockTypes.size() > 0)
    {
      BlockType aBlockType = blockTypes.get(blockTypes.size() - 1);
      aBlockType.delete();
      blockTypes.remove(aBlockType);
    }
    
    BlockApplication placeholderBlockApplication = blockApplication;
    this.blockApplication = null;
    if(placeholderBlockApplication != null)
    {
      placeholderBlockApplication.removeBlockGame(this);
    }
    Player placeholderPlayer = player;
    this.player = null;
    if(placeholderPlayer != null)
    {
      placeholderPlayer.removeBlockGame(this);
    }
    Admin placeholderAdmin = admin;
    this.admin = null;
    if(placeholderAdmin != null)
    {
      placeholderAdmin.removeBlockGame(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "name" + ":" + getName()+ "," +
            "numLevels" + ":" + getNumLevels()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "hallOfFame = "+(getHallOfFame()!=null?Integer.toHexString(System.identityHashCode(getHallOfFame())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "playArea = "+(getPlayArea()!=null?Integer.toHexString(System.identityHashCode(getPlayArea())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "blockApplication = "+(getBlockApplication()!=null?Integer.toHexString(System.identityHashCode(getBlockApplication())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "player = "+(getPlayer()!=null?Integer.toHexString(System.identityHashCode(getPlayer())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "admin = "+(getAdmin()!=null?Integer.toHexString(System.identityHashCode(getAdmin())):"null");
  }
}