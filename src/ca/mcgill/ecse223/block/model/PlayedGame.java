/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse223.block.model;
import java.util.*;

// line 10 "../../../../../Block223PlayGame.ump"
public class PlayedGame
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static int nextId = 1;

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //PlayedGame Attributes
  private int nrLives;
  private int currentLevel;

  //Autounique Attributes
  private int id;

  //PlayedGame Associations
  private Player player;
  private SpecificBall specificBall;
  private SpecificPaddle specificPaddle;
  private List<SpecificBlockAssignment> specificBlockAssignments;
  private List<ScoreEntry> scoreEntries;
  private Game game;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public PlayedGame(Player aPlayer, SpecificBall aSpecificBall, SpecificPaddle aSpecificPaddle, Game aGame)
  {
    nrLives = 3;
    currentLevel = 1;
    id = nextId++;
    boolean didAddPlayer = setPlayer(aPlayer);
    if (!didAddPlayer)
    {
      throw new RuntimeException("Unable to create playedGame due to player");
    }
    if (aSpecificBall == null || aSpecificBall.getPlayedGame() != null)
    {
      throw new RuntimeException("Unable to create PlayedGame due to aSpecificBall");
    }
    specificBall = aSpecificBall;
    if (aSpecificPaddle == null || aSpecificPaddle.getPlayedGame() != null)
    {
      throw new RuntimeException("Unable to create PlayedGame due to aSpecificPaddle");
    }
    specificPaddle = aSpecificPaddle;
    specificBlockAssignments = new ArrayList<SpecificBlockAssignment>();
    scoreEntries = new ArrayList<ScoreEntry>();
    boolean didAddGame = setGame(aGame);
    if (!didAddGame)
    {
      throw new RuntimeException("Unable to create playedGame due to game");
    }
  }

  public PlayedGame(Player aPlayer, Ball aBallForSpecificBall, Paddle aPaddleForSpecificPaddle, Game aGame)
  {
    nrLives = 3;
    currentLevel = 1;
    id = nextId++;
    boolean didAddPlayer = setPlayer(aPlayer);
    if (!didAddPlayer)
    {
      throw new RuntimeException("Unable to create playedGame due to player");
    }
    specificBall = new SpecificBall(aBallForSpecificBall, this);
    specificPaddle = new SpecificPaddle(aPaddleForSpecificPaddle, this);
    specificBlockAssignments = new ArrayList<SpecificBlockAssignment>();
    scoreEntries = new ArrayList<ScoreEntry>();
    boolean didAddGame = setGame(aGame);
    if (!didAddGame)
    {
      throw new RuntimeException("Unable to create playedGame due to game");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setNrLives(int aNrLives)
  {
    boolean wasSet = false;
    nrLives = aNrLives;
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

  public int getNrLives()
  {
    return nrLives;
  }

  public int getCurrentLevel()
  {
    return currentLevel;
  }

  public int getId()
  {
    return id;
  }
  /* Code from template association_GetOne */
  public Player getPlayer()
  {
    return player;
  }
  /* Code from template association_GetOne */
  public SpecificBall getSpecificBall()
  {
    return specificBall;
  }
  /* Code from template association_GetOne */
  public SpecificPaddle getSpecificPaddle()
  {
    return specificPaddle;
  }
  /* Code from template association_GetMany */
  public SpecificBlockAssignment getSpecificBlockAssignment(int index)
  {
    SpecificBlockAssignment aSpecificBlockAssignment = specificBlockAssignments.get(index);
    return aSpecificBlockAssignment;
  }

  public List<SpecificBlockAssignment> getSpecificBlockAssignments()
  {
    List<SpecificBlockAssignment> newSpecificBlockAssignments = Collections.unmodifiableList(specificBlockAssignments);
    return newSpecificBlockAssignments;
  }

  public int numberOfSpecificBlockAssignments()
  {
    int number = specificBlockAssignments.size();
    return number;
  }

  public boolean hasSpecificBlockAssignments()
  {
    boolean has = specificBlockAssignments.size() > 0;
    return has;
  }

  public int indexOfSpecificBlockAssignment(SpecificBlockAssignment aSpecificBlockAssignment)
  {
    int index = specificBlockAssignments.indexOf(aSpecificBlockAssignment);
    return index;
  }
  /* Code from template association_GetMany */
  public ScoreEntry getScoreEntry(int index)
  {
    ScoreEntry aScoreEntry = scoreEntries.get(index);
    return aScoreEntry;
  }

  public List<ScoreEntry> getScoreEntries()
  {
    List<ScoreEntry> newScoreEntries = Collections.unmodifiableList(scoreEntries);
    return newScoreEntries;
  }

  public int numberOfScoreEntries()
  {
    int number = scoreEntries.size();
    return number;
  }

  public boolean hasScoreEntries()
  {
    boolean has = scoreEntries.size() > 0;
    return has;
  }

  public int indexOfScoreEntry(ScoreEntry aScoreEntry)
  {
    int index = scoreEntries.indexOf(aScoreEntry);
    return index;
  }
  /* Code from template association_GetOne */
  public Game getGame()
  {
    return game;
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
      existingPlayer.removePlayedGame(this);
    }
    player.addPlayedGame(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfSpecificBlockAssignments()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public SpecificBlockAssignment addSpecificBlockAssignment(int aPositionX, int aPositionY, Block aBlock)
  {
    return new SpecificBlockAssignment(aPositionX, aPositionY, aBlock, this);
  }

  public boolean addSpecificBlockAssignment(SpecificBlockAssignment aSpecificBlockAssignment)
  {
    boolean wasAdded = false;
    if (specificBlockAssignments.contains(aSpecificBlockAssignment)) { return false; }
    PlayedGame existingPlayedGame = aSpecificBlockAssignment.getPlayedGame();
    boolean isNewPlayedGame = existingPlayedGame != null && !this.equals(existingPlayedGame);
    if (isNewPlayedGame)
    {
      aSpecificBlockAssignment.setPlayedGame(this);
    }
    else
    {
      specificBlockAssignments.add(aSpecificBlockAssignment);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeSpecificBlockAssignment(SpecificBlockAssignment aSpecificBlockAssignment)
  {
    boolean wasRemoved = false;
    //Unable to remove aSpecificBlockAssignment, as it must always have a playedGame
    if (!this.equals(aSpecificBlockAssignment.getPlayedGame()))
    {
      specificBlockAssignments.remove(aSpecificBlockAssignment);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addSpecificBlockAssignmentAt(SpecificBlockAssignment aSpecificBlockAssignment, int index)
  {  
    boolean wasAdded = false;
    if(addSpecificBlockAssignment(aSpecificBlockAssignment))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfSpecificBlockAssignments()) { index = numberOfSpecificBlockAssignments() - 1; }
      specificBlockAssignments.remove(aSpecificBlockAssignment);
      specificBlockAssignments.add(index, aSpecificBlockAssignment);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveSpecificBlockAssignmentAt(SpecificBlockAssignment aSpecificBlockAssignment, int index)
  {
    boolean wasAdded = false;
    if(specificBlockAssignments.contains(aSpecificBlockAssignment))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfSpecificBlockAssignments()) { index = numberOfSpecificBlockAssignments() - 1; }
      specificBlockAssignments.remove(aSpecificBlockAssignment);
      specificBlockAssignments.add(index, aSpecificBlockAssignment);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addSpecificBlockAssignmentAt(aSpecificBlockAssignment, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfScoreEntries()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public ScoreEntry addScoreEntry(Game aGame, Player aPlayer)
  {
    return new ScoreEntry(aGame, aPlayer, this);
  }

  public boolean addScoreEntry(ScoreEntry aScoreEntry)
  {
    boolean wasAdded = false;
    if (scoreEntries.contains(aScoreEntry)) { return false; }
    PlayedGame existingPlayedGame = aScoreEntry.getPlayedGame();
    boolean isNewPlayedGame = existingPlayedGame != null && !this.equals(existingPlayedGame);
    if (isNewPlayedGame)
    {
      aScoreEntry.setPlayedGame(this);
    }
    else
    {
      scoreEntries.add(aScoreEntry);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeScoreEntry(ScoreEntry aScoreEntry)
  {
    boolean wasRemoved = false;
    //Unable to remove aScoreEntry, as it must always have a playedGame
    if (!this.equals(aScoreEntry.getPlayedGame()))
    {
      scoreEntries.remove(aScoreEntry);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addScoreEntryAt(ScoreEntry aScoreEntry, int index)
  {  
    boolean wasAdded = false;
    if(addScoreEntry(aScoreEntry))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfScoreEntries()) { index = numberOfScoreEntries() - 1; }
      scoreEntries.remove(aScoreEntry);
      scoreEntries.add(index, aScoreEntry);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveScoreEntryAt(ScoreEntry aScoreEntry, int index)
  {
    boolean wasAdded = false;
    if(scoreEntries.contains(aScoreEntry))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfScoreEntries()) { index = numberOfScoreEntries() - 1; }
      scoreEntries.remove(aScoreEntry);
      scoreEntries.add(index, aScoreEntry);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addScoreEntryAt(aScoreEntry, index);
    }
    return wasAdded;
  }
  /* Code from template association_SetOneToMany */
  public boolean setGame(Game aGame)
  {
    boolean wasSet = false;
    if (aGame == null)
    {
      return wasSet;
    }

    Game existingGame = game;
    game = aGame;
    if (existingGame != null && !existingGame.equals(aGame))
    {
      existingGame.removePlayedGame(this);
    }
    game.addPlayedGame(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Player placeholderPlayer = player;
    this.player = null;
    if(placeholderPlayer != null)
    {
      placeholderPlayer.removePlayedGame(this);
    }
    SpecificBall existingSpecificBall = specificBall;
    specificBall = null;
    if (existingSpecificBall != null)
    {
      existingSpecificBall.delete();
    }
    SpecificPaddle existingSpecificPaddle = specificPaddle;
    specificPaddle = null;
    if (existingSpecificPaddle != null)
    {
      existingSpecificPaddle.delete();
    }
    while (specificBlockAssignments.size() > 0)
    {
      SpecificBlockAssignment aSpecificBlockAssignment = specificBlockAssignments.get(specificBlockAssignments.size() - 1);
      aSpecificBlockAssignment.delete();
      specificBlockAssignments.remove(aSpecificBlockAssignment);
    }
    
    for(int i=scoreEntries.size(); i > 0; i--)
    {
      ScoreEntry aScoreEntry = scoreEntries.get(i - 1);
      aScoreEntry.delete();
    }
    Game placeholderGame = game;
    this.game = null;
    if(placeholderGame != null)
    {
      placeholderGame.removePlayedGame(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "id" + ":" + getId()+ "," +
            "nrLives" + ":" + getNrLives()+ "," +
            "currentLevel" + ":" + getCurrentLevel()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "player = "+(getPlayer()!=null?Integer.toHexString(System.identityHashCode(getPlayer())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "specificBall = "+(getSpecificBall()!=null?Integer.toHexString(System.identityHashCode(getSpecificBall())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "specificPaddle = "+(getSpecificPaddle()!=null?Integer.toHexString(System.identityHashCode(getSpecificPaddle())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "game = "+(getGame()!=null?Integer.toHexString(System.identityHashCode(getGame())):"null");
  }
}