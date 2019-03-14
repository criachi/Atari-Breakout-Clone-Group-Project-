/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse223.block.model;
import java.io.Serializable;
import java.util.*;

// line 70 "../../../../../Block223Persistence.ump"
// line 7 "../../../../../Block223PlayGame.ump"
// line 46 "../../../../../Block223 v2.ump"
public class Player extends UserRole implements Serializable
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Player Associations
  private List<ScoreEntry> scoreEntries;
  private List<PlayedGame> playedGames;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Player(String aPassword, Block223 aBlock223)
  {
    super(aPassword, aBlock223);
    scoreEntries = new ArrayList<ScoreEntry>();
    playedGames = new ArrayList<PlayedGame>();
  }

  //------------------------
  // INTERFACE
  //------------------------
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
  /* Code from template association_GetMany */
  public PlayedGame getPlayedGame(int index)
  {
    PlayedGame aPlayedGame = playedGames.get(index);
    return aPlayedGame;
  }

  public List<PlayedGame> getPlayedGames()
  {
    List<PlayedGame> newPlayedGames = Collections.unmodifiableList(playedGames);
    return newPlayedGames;
  }

  public int numberOfPlayedGames()
  {
    int number = playedGames.size();
    return number;
  }

  public boolean hasPlayedGames()
  {
    boolean has = playedGames.size() > 0;
    return has;
  }

  public int indexOfPlayedGame(PlayedGame aPlayedGame)
  {
    int index = playedGames.indexOf(aPlayedGame);
    return index;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfScoreEntries()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public ScoreEntry addScoreEntry(Game aGame, PlayedGame aPlayedGame)
  {
    return new ScoreEntry(aGame, this, aPlayedGame);
  }

  public boolean addScoreEntry(ScoreEntry aScoreEntry)
  {
    boolean wasAdded = false;
    if (scoreEntries.contains(aScoreEntry)) { return false; }
    Player existingPlayer = aScoreEntry.getPlayer();
    boolean isNewPlayer = existingPlayer != null && !this.equals(existingPlayer);
    if (isNewPlayer)
    {
      aScoreEntry.setPlayer(this);
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
    //Unable to remove aScoreEntry, as it must always have a player
    if (!this.equals(aScoreEntry.getPlayer()))
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
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfPlayedGames()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public PlayedGame addPlayedGame(SpecificBall aSpecificBall, SpecificPaddle aSpecificPaddle, Game aGame)
  {
    return new PlayedGame(this, aSpecificBall, aSpecificPaddle, aGame);
  }

  public boolean addPlayedGame(PlayedGame aPlayedGame)
  {
    boolean wasAdded = false;
    if (playedGames.contains(aPlayedGame)) { return false; }
    Player existingPlayer = aPlayedGame.getPlayer();
    boolean isNewPlayer = existingPlayer != null && !this.equals(existingPlayer);
    if (isNewPlayer)
    {
      aPlayedGame.setPlayer(this);
    }
    else
    {
      playedGames.add(aPlayedGame);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removePlayedGame(PlayedGame aPlayedGame)
  {
    boolean wasRemoved = false;
    //Unable to remove aPlayedGame, as it must always have a player
    if (!this.equals(aPlayedGame.getPlayer()))
    {
      playedGames.remove(aPlayedGame);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addPlayedGameAt(PlayedGame aPlayedGame, int index)
  {  
    boolean wasAdded = false;
    if(addPlayedGame(aPlayedGame))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfPlayedGames()) { index = numberOfPlayedGames() - 1; }
      playedGames.remove(aPlayedGame);
      playedGames.add(index, aPlayedGame);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMovePlayedGameAt(PlayedGame aPlayedGame, int index)
  {
    boolean wasAdded = false;
    if(playedGames.contains(aPlayedGame))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfPlayedGames()) { index = numberOfPlayedGames() - 1; }
      playedGames.remove(aPlayedGame);
      playedGames.add(index, aPlayedGame);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addPlayedGameAt(aPlayedGame, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    for(int i=scoreEntries.size(); i > 0; i--)
    {
      ScoreEntry aScoreEntry = scoreEntries.get(i - 1);
      aScoreEntry.delete();
    }
    for(int i=playedGames.size(); i > 0; i--)
    {
      PlayedGame aPlayedGame = playedGames.get(i - 1);
      aPlayedGame.delete();
    }
    super.delete();
  }
  
  //------------------------
  // DEVELOPER CODE - PROVIDED AS-IS
  //------------------------
  
  // line 73 "../../../../../Block223Persistence.ump"
  private static final long serialVersionUID = -8062584216235283243L ;

  
}