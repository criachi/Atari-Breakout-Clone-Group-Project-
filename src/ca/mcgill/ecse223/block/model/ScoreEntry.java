/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse223.block.model;

// line 28 "../../../../../Block223PlayGame.ump"
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
<<<<<<< HEAD
  private PlayedGame playedGame;
  private Block223 block223;
=======
>>>>>>> e4867381e59ef1bd73d2da6c40a2d68715ccd6a8

  //------------------------
  // CONSTRUCTOR
  //------------------------

<<<<<<< HEAD
  public ScoreEntry(Game aGame, Player aPlayer, PlayedGame aPlayedGame, Block223 aBlock223)
=======
  public ScoreEntry(Game aGame, Player aPlayer)
>>>>>>> e4867381e59ef1bd73d2da6c40a2d68715ccd6a8
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
<<<<<<< HEAD
    boolean didAddPlayedGame = setPlayedGame(aPlayedGame);
    if (!didAddPlayedGame)
    {
      throw new RuntimeException("Unable to create scoreEntry due to playedGame");
    }
    boolean didAddBlock223 = setBlock223(aBlock223);
    if (!didAddBlock223)
    {
      throw new RuntimeException("Unable to create scoreEntry due to block223");
    }
=======
>>>>>>> e4867381e59ef1bd73d2da6c40a2d68715ccd6a8
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
<<<<<<< HEAD
  /* Code from template association_GetOne */
  public PlayedGame getPlayedGame()
  {
    return playedGame;
  }
  /* Code from template association_GetOne */
  public Block223 getBlock223()
  {
    return block223;
  }
=======
>>>>>>> e4867381e59ef1bd73d2da6c40a2d68715ccd6a8
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
<<<<<<< HEAD
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
  /* Code from template association_SetOneToMany */
  public boolean setBlock223(Block223 aBlock223)
  {
    boolean wasSet = false;
    if (aBlock223 == null)
    {
      return wasSet;
    }

    Block223 existingBlock223 = block223;
    block223 = aBlock223;
    if (existingBlock223 != null && !existingBlock223.equals(aBlock223))
    {
      existingBlock223.removeScoreEntry(this);
    }
    block223.addScoreEntry(this);
    wasSet = true;
    return wasSet;
  }
=======
>>>>>>> e4867381e59ef1bd73d2da6c40a2d68715ccd6a8

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
<<<<<<< HEAD
    PlayedGame placeholderPlayedGame = playedGame;
    this.playedGame = null;
    if(placeholderPlayedGame != null)
    {
      placeholderPlayedGame.removeScoreEntry(this);
    }
    Block223 placeholderBlock223 = block223;
    this.block223 = null;
    if(placeholderBlock223 != null)
    {
      placeholderBlock223.removeScoreEntry(this);
    }
=======
>>>>>>> e4867381e59ef1bd73d2da6c40a2d68715ccd6a8
  }


  public String toString()
  {
    return super.toString() + "["+
            "score" + ":" + getScore()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "game = "+(getGame()!=null?Integer.toHexString(System.identityHashCode(getGame())):"null") + System.getProperties().getProperty("line.separator") +
<<<<<<< HEAD
            "  " + "player = "+(getPlayer()!=null?Integer.toHexString(System.identityHashCode(getPlayer())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "playedGame = "+(getPlayedGame()!=null?Integer.toHexString(System.identityHashCode(getPlayedGame())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "block223 = "+(getBlock223()!=null?Integer.toHexString(System.identityHashCode(getBlock223())):"null");
=======
            "  " + "player = "+(getPlayer()!=null?Integer.toHexString(System.identityHashCode(getPlayer())):"null");
>>>>>>> e4867381e59ef1bd73d2da6c40a2d68715ccd6a8
  }
}