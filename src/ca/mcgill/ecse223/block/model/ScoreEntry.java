/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse223.block.model;

// line 29 "../../../../../Block223PlayGame.ump"
public class ScoreEntry
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //ScoreEntry Attributes
  private int score;

  //ScoreEntry Associations
  private Game game;
  private Player player;
  private PlayedGame playedGame;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public ScoreEntry(Game aGame, Player aPlayer, PlayedGame aPlayedGame)
  {
    score = 0;
    boolean didAddGame = setGame(aGame);
    if (!didAddGame)
    {
      throw new RuntimeException("Unable to create scoreEntry due to game");
    }
    boolean didAddPlayer = setPlayer(aPlayer);
    if (!didAddPlayer)
    {
      throw new RuntimeException("Unable to create scoreEntry due to player");
    }
    boolean didAddPlayedGame = setPlayedGame(aPlayedGame);
    if (!didAddPlayedGame)
    {
      throw new RuntimeException("Unable to create scoreEntry due to playedGame");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setScore(int aScore)
  {
    boolean wasSet = false;
    score = aScore;
    wasSet = true;
    return wasSet;
  }

  public int getScore()
  {
    return score;
  }
  /* Code from template association_GetOne */
  public Game getGame()
  {
    return game;
  }
  /* Code from template association_GetOne */
  public Player getPlayer()
  {
    return player;
  }
  /* Code from template association_GetOne */
  public PlayedGame getPlayedGame()
  {
    return playedGame;
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
      existingGame.removeScoreEntry(this);
    }
    game.addScoreEntry(this);
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
      existingPlayer.removeScoreEntry(this);
    }
    player.addScoreEntry(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOneToMany */
  public boolean setPlayedGame(PlayedGame aPlayedGame)
  {
    boolean wasSet = false;
    if (aPlayedGame == null)
    {
      return wasSet;
    }

    PlayedGame existingPlayedGame = playedGame;
    playedGame = aPlayedGame;
    if (existingPlayedGame != null && !existingPlayedGame.equals(aPlayedGame))
    {
      existingPlayedGame.removeScoreEntry(this);
    }
    playedGame.addScoreEntry(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Game placeholderGame = game;
    this.game = null;
    if(placeholderGame != null)
    {
      placeholderGame.removeScoreEntry(this);
    }
    Player placeholderPlayer = player;
    this.player = null;
    if(placeholderPlayer != null)
    {
      placeholderPlayer.removeScoreEntry(this);
    }
    PlayedGame placeholderPlayedGame = playedGame;
    this.playedGame = null;
    if(placeholderPlayedGame != null)
    {
      placeholderPlayedGame.removeScoreEntry(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "score" + ":" + getScore()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "game = "+(getGame()!=null?Integer.toHexString(System.identityHashCode(getGame())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "player = "+(getPlayer()!=null?Integer.toHexString(System.identityHashCode(getPlayer())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "playedGame = "+(getPlayedGame()!=null?Integer.toHexString(System.identityHashCode(getPlayedGame())):"null");
  }
}