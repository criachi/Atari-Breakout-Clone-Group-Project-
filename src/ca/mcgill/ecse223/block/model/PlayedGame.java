/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse223.block.model;
import java.util.*;

// line 10 "../../../../../Block223PlayGame.ump"
// line 1 "../../../../../Block223StateMachine.ump"
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

  //PlayedGame State Machines
  public enum GameStatus { Idle, Play, Paused, Done, Pause }
  private GameStatus gameStatus;

  //PlayedGame Associations
  private Player player;
  private SpecificBall specificBall;
  private SpecificPaddle specificPaddle;
  private List<SpecificBlockAssignment> specificBlockAssignments;
  private Game game;
  private Block223 block223;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public PlayedGame(Player aPlayer, SpecificBall aSpecificBall, SpecificPaddle aSpecificPaddle, Game aGame, Block223 aBlock223)
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
    boolean didAddGame = setGame(aGame);
    if (!didAddGame)
    {
      throw new RuntimeException("Unable to create playedGame due to game");
    }
    boolean didAddBlock223 = setBlock223(aBlock223);
    if (!didAddBlock223)
    {
      throw new RuntimeException("Unable to create playedGame due to block223");
    }
    setGameStatus(GameStatus.Idle);
  }

  public PlayedGame(Player aPlayer, Ball aBallForSpecificBall, Paddle aPaddleForSpecificPaddle, Game aGame, Block223 aBlock223)
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
    boolean didAddGame = setGame(aGame);
    if (!didAddGame)
    {
      throw new RuntimeException("Unable to create playedGame due to game");
    }
    boolean didAddBlock223 = setBlock223(aBlock223);
    if (!didAddBlock223)
    {
      throw new RuntimeException("Unable to create playedGame due to block223");
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

  public String getGameStatusFullName()
  {
    String answer = gameStatus.toString();
    return answer;
  }

  public GameStatus getGameStatus()
  {
    return gameStatus;
  }

<<<<<<< HEAD
  public boolean beginGame()
  {
    boolean wasEventProcessed = false;
    
    GameStatus aGameStatus = gameStatus;
    switch (aGameStatus)
    {
      case Idle:
        if (hasEnoughBlockAssignments())
        {
        // line 6 "../../../../../Block223StateMachine.ump"
          initializeBlockAssignments(); //this copies over info from EXISTING blockAssignments to specificBlockAssignments
          setGameStatus(GameStatus.Play);
          wasEventProcessed = true;
          break;
        }
        if (!(hasEnoughBlockAssignments()))
        {
        // line 10 "../../../../../Block223StateMachine.ump"
          initializeBlockAssignments();
				putRandomBlocks();
          setGameStatus(GameStatus.Play);
          wasEventProcessed = true;
          break;
        }
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

=======
>>>>>>> e4867381e59ef1bd73d2da6c40a2d68715ccd6a8
  public boolean pause()
  {
    boolean wasEventProcessed = false;
    
    GameStatus aGameStatus = gameStatus;
    switch (aGameStatus)
    {
      case Play:
        setGameStatus(GameStatus.Paused);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean moveBall()
  {
    boolean wasEventProcessed = false;
    
    GameStatus aGameStatus = gameStatus;
    switch (aGameStatus)
    {
      case Play:
        if (isPaddleHit())
        {
<<<<<<< HEAD
        // line 17 "../../../../../Block223StateMachine.ump"
=======
        // line 21 "../../../../../Block223StateMachine.ump"
>>>>>>> e4867381e59ef1bd73d2da6c40a2d68715ccd6a8
          bounceBackFromPaddle(getSpecificBall());
          setGameStatus(GameStatus.Play);
          wasEventProcessed = true;
          break;
        }
        if (isWallHit())
        {
<<<<<<< HEAD
        // line 18 "../../../../../Block223StateMachine.ump"
          bounceBackFromWall(getSpecificBall();
=======
        // line 22 "../../../../../Block223StateMachine.ump"
          bounceBackFromWall(getSpecificBall());
>>>>>>> e4867381e59ef1bd73d2da6c40a2d68715ccd6a8
          setGameStatus(GameStatus.Play);
          wasEventProcessed = true;
          break;
        }
        if (isBlockHit()&&isLastBlock()&&isLastLevel())
        {
<<<<<<< HEAD
        // line 19 "../../../../../Block223StateMachine.ump"
=======
        // line 23 "../../../../../Block223StateMachine.ump"
>>>>>>> e4867381e59ef1bd73d2da6c40a2d68715ccd6a8
          updateScore(); deleteSpecificBlock();
          setGameStatus(GameStatus.Done);
          wasEventProcessed = true;
          break;
        }
        if (isBlockHit()&&isLastBlock())
        {
<<<<<<< HEAD
        // line 20 "../../../../../Block223StateMachine.ump"
=======
        // line 24 "../../../../../Block223StateMachine.ump"
>>>>>>> e4867381e59ef1bd73d2da6c40a2d68715ccd6a8
          updateScore(); deleteSpecificBlock(); increaseLevel();
          setGameStatus(GameStatus.Pause);
          wasEventProcessed = true;
          break;
        }
        if (isBlockHit())
        {
<<<<<<< HEAD
        // line 21 "../../../../../Block223StateMachine.ump"
=======
        // line 25 "../../../../../Block223StateMachine.ump"
>>>>>>> e4867381e59ef1bd73d2da6c40a2d68715ccd6a8
          bounceBackFromBlock(getSpecificBall()); updateScore(); deleteSpecificBlock();
          setGameStatus(GameStatus.Play);
          wasEventProcessed = true;
          break;
        }
        if (isBallOutOfBounds()&&(hasOneLifeRemaining()))
        {
<<<<<<< HEAD
        // line 22 "../../../../../Block223StateMachine.ump"
=======
        // line 26 "../../../../../Block223StateMachine.ump"
>>>>>>> e4867381e59ef1bd73d2da6c40a2d68715ccd6a8
          decrementLives(); resetBallandPaddle();
          setGameStatus(GameStatus.Done);
          wasEventProcessed = true;
          break;
        }
        if (isBallOutOfBounds())
        {
<<<<<<< HEAD
        // line 23 "../../../../../Block223StateMachine.ump"
=======
        // line 27 "../../../../../Block223StateMachine.ump"
>>>>>>> e4867381e59ef1bd73d2da6c40a2d68715ccd6a8
          decrementLives();
          setGameStatus(GameStatus.Pause);
          wasEventProcessed = true;
          break;
        }
        setGameStatus(GameStatus.Play);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean resume()
  {
    boolean wasEventProcessed = false;
    
    GameStatus aGameStatus = gameStatus;
    switch (aGameStatus)
    {
      case Paused:
        setGameStatus(GameStatus.Play);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  private void setGameStatus(GameStatus aGameStatus)
  {
    gameStatus = aGameStatus;

    // entry actions and do activities
    switch(gameStatus)
    {
      case Paused:
<<<<<<< HEAD
        // line 27 "../../../../../Block223StateMachine.ump"
        savePlayedGame();
        break;
      case Done:
        // line 33 "../../../../../Block223StateMachine.ump"
        saveScore();
				deletePlayedGame();
=======
        // line 30 "../../../../../Block223StateMachine.ump"
        savePlayedGame();
        break;
      case Done:
        // line 36 "../../../../../Block223StateMachine.ump"
        deletePlayedGame();
>>>>>>> e4867381e59ef1bd73d2da6c40a2d68715ccd6a8
        break;
    }
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
  /* Code from template association_GetOne */
  public Game getGame()
  {
    return game;
  }
  /* Code from template association_GetOne */
  public Block223 getBlock223()
  {
    return block223;
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
<<<<<<< HEAD
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfScoreEntries()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public ScoreEntry addScoreEntry(Game aGame, Player aPlayer, Block223 aBlock223)
  {
    return new ScoreEntry(aGame, aPlayer, this, aBlock223);
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
      existingGame.removePlayedGame(this);
    }
    game.addPlayedGame(this);
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
      existingBlock223.removePlayedGame(this);
    }
    block223.addPlayedGame(this);
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
    
    Game placeholderGame = game;
    this.game = null;
    if(placeholderGame != null)
    {
      placeholderGame.removePlayedGame(this);
    }
    Block223 placeholderBlock223 = block223;
    this.block223 = null;
    if(placeholderBlock223 != null)
    {
      placeholderBlock223.removePlayedGame(this);
    }
  }

<<<<<<< HEAD
  // line 43 "../../../../../Block223StateMachine.ump"
   private void saveScore(){
    
  }

  // line 46 "../../../../../Block223StateMachine.ump"
   private Boolean isLastLevel(){
    return currentLevel == getGame().getLevels().size();
  }

  // line 55 "../../../../../Block223StateMachine.ump"
=======
  // line 47 "../../../../../Block223StateMachine.ump"
   private Boolean hasOneLifeRemaining(){
    
  }

  // line 51 "../../../../../Block223StateMachine.ump"
   private Boolean isBallOutOfBounds(){
    
  }

  // line 55 "../../../../../Block223StateMachine.ump"
   private Boolean isBlockHit(){
    
  }

  // line 59 "../../../../../Block223StateMachine.ump"
   private void bounceBackFromBlock(SpecificBall aBall){
    
  }

  // line 63 "../../../../../Block223StateMachine.ump"
   private void decrementLives(){
    
  }

  // line 67 "../../../../../Block223StateMachine.ump"
   private void resetBallandPaddle(){
    
  }

  // line 71 "../../../../../Block223StateMachine.ump"
   private Boolean isPaddleHit(){
    
  }

  // line 75 "../../../../../Block223StateMachine.ump"
   private Boolean isWallHit(){
    
  }

  // line 79 "../../../../../Block223StateMachine.ump"
   private void bounceBackFromPaddle(SpecificBall aBall){
    
  }

  // line 83 "../../../../../Block223StateMachine.ump"
   private void bounceBackFromWall(SpecificBall aBall){
    
  }

  // line 87 "../../../../../Block223StateMachine.ump"
   private void updateScore(){
    
  }

  // line 91 "../../../../../Block223StateMachine.ump"
   private void deleteSpecificBlock(){
    
  }

  // line 95 "../../../../../Block223StateMachine.ump"
   private void increaseLevel(){
    
  }

  // line 99 "../../../../../Block223StateMachine.ump"
   private Boolean isLastLevel(){
    
  }

  // line 103 "../../../../../Block223StateMachine.ump"
>>>>>>> e4867381e59ef1bd73d2da6c40a2d68715ccd6a8
   private void start(){
    
  }

<<<<<<< HEAD
  // line 59 "../../../../../Block223StateMachine.ump"
=======
  // line 107 "../../../../../Block223StateMachine.ump"
>>>>>>> e4867381e59ef1bd73d2da6c40a2d68715ccd6a8
   private void resume(){
    
  }

<<<<<<< HEAD
  // line 63 "../../../../../Block223StateMachine.ump"
=======
  // line 111 "../../../../../Block223StateMachine.ump"
>>>>>>> e4867381e59ef1bd73d2da6c40a2d68715ccd6a8
   private Boolean isLastBlock(){
    
  }

<<<<<<< HEAD
  // line 67 "../../../../../Block223StateMachine.ump"
=======
  // line 115 "../../../../../Block223StateMachine.ump"
>>>>>>> e4867381e59ef1bd73d2da6c40a2d68715ccd6a8
   private void pause(){
    
  }

<<<<<<< HEAD
  // line 71 "../../../../../Block223StateMachine.ump"
=======
  // line 119 "../../../../../Block223StateMachine.ump"
>>>>>>> e4867381e59ef1bd73d2da6c40a2d68715ccd6a8
   private void gameOver(){
    
  }

<<<<<<< HEAD
  // line 75 "../../../../../Block223StateMachine.ump"
   private Boolean isBallOutOfBounds(){
    
  }

  // line 79 "../../../../../Block223StateMachine.ump"
=======
  // line 123 "../../../../../Block223StateMachine.ump"
>>>>>>> e4867381e59ef1bd73d2da6c40a2d68715ccd6a8
   private void outOfBounds(){
    
  }

<<<<<<< HEAD
  // line 83 "../../../../../Block223StateMachine.ump"
=======
  // line 127 "../../../../../Block223StateMachine.ump"
>>>>>>> e4867381e59ef1bd73d2da6c40a2d68715ccd6a8
   private Boolean hitsBlock(){
    
  }

<<<<<<< HEAD
  // line 87 "../../../../../Block223StateMachine.ump"
=======
  // line 131 "../../../../../Block223StateMachine.ump"
>>>>>>> e4867381e59ef1bd73d2da6c40a2d68715ccd6a8
   private void moveBall(){
    
  }

<<<<<<< HEAD
  // line 91 "../../../../../Block223StateMachine.ump"
=======
  // line 135 "../../../../../Block223StateMachine.ump"
>>>>>>> e4867381e59ef1bd73d2da6c40a2d68715ccd6a8
   private Boolean hitsPaddleOrWall(){
    
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
            "  " + "game = "+(getGame()!=null?Integer.toHexString(System.identityHashCode(getGame())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "block223 = "+(getBlock223()!=null?Integer.toHexString(System.identityHashCode(getBlock223())):"null");
  }
}