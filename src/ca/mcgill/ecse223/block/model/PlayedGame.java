/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse223.block.model;
import java.io.Serializable;
import java.util.*;

// line 11 "../../../../../Block223PlayMode.ump"
// line 109 "../../../../../Block223Persistence.ump"
// line 1 "../../../../../Block223States.ump"
public class PlayedGame implements Serializable
{

  //------------------------
  // STATIC VARIABLES
  //------------------------


  /**
   * at design time, the initial wait time may be adjusted as seen fit
   */
  public static final int INITIAL_WAIT_TIME = 30;
  private static int nextId = 1;
  public static final int NR_LIVES = 3;

  /**
   * the PlayedBall and PlayedPaddle are not in a separate class to avoid the bug in Umple that occurred for the second constructor of Game
   * no direct link to Ball, because the ball can be found by navigating to Game and then Ball
   */
  public static final int BALL_INITIAL_X = Game.PLAY_AREA_SIDE / 2;
  public static final int BALL_INITIAL_Y = Game.PLAY_AREA_SIDE / 2;

  /**
   * no direct link to Paddle, because the paddle can be found by navigating to Game and then Paddle
   * pixels moved when right arrow key is pressed
   */
  public static final int PADDLE_MOVE_RIGHT = 5;

  /**
   * pixels moved when left arrow key is pressed
   */
  public static final int PADDLE_MOVE_LEFT = -5;

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //PlayedGame Attributes
  private int score;
  private int lives;
  private int currentLevel;
  private double waitTime;
  private String playername;
  private double ballDirectionX;
  private double ballDirectionY;
  private double currentBallX;
  private double currentBallY;
  private double currentPaddleLength;
  private double currentPaddleX;
  private double currentPaddleY;

  //Autounique Attributes
  private int id;

  //PlayedGame State Machines
  public enum PlayStatus { Ready, Moving, Paused, GameOver }
  private PlayStatus playStatus;

  //PlayedGame Associations
  private Player player;
  private Game game;
  private List<PlayedBlockAssignment> blocks;
  private BouncePoint bounce;
  private Block223 block223;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public PlayedGame(String aPlayername, Game aGame, Block223 aBlock223)
  {
    // line 47 "../../../../../Block223PlayMode.ump"
    boolean didAddGameResult = setGame(aGame);
          if (!didAddGameResult)
          {
             throw new RuntimeException("Unable to create playedGame due to game");
          }
    // END OF UMPLE BEFORE INJECTION
    score = 0;
    lives = NR_LIVES;
    currentLevel = 1;
    waitTime = INITIAL_WAIT_TIME;
    playername = aPlayername;
    resetBallDirectionX();
    resetBallDirectionY();
    resetCurrentBallX();
    resetCurrentBallY();
    currentPaddleLength = getGame().getPaddle().getMaxPaddleLength();
    resetCurrentPaddleX();
    currentPaddleY = Game.PLAY_AREA_SIDE - Paddle.VERTICAL_DISTANCE - Paddle.PADDLE_WIDTH;
    id = nextId++;
    boolean didAddGame = setGame(aGame);
    if (!didAddGame)
    {
      throw new RuntimeException("Unable to create playedGame due to game");
    }
    blocks = new ArrayList<PlayedBlockAssignment>();
    boolean didAddBlock223 = setBlock223(aBlock223);
    if (!didAddBlock223)
    {
      throw new RuntimeException("Unable to create playedGame due to block223");
    }
    setPlayStatus(PlayStatus.Ready);
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

  public boolean setLives(int aLives)
  {
    boolean wasSet = false;
    lives = aLives;
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

  public boolean setWaitTime(double aWaitTime)
  {
    boolean wasSet = false;
    waitTime = aWaitTime;
    wasSet = true;
    return wasSet;
  }

  public boolean setPlayername(String aPlayername)
  {
    boolean wasSet = false;
    playername = aPlayername;
    wasSet = true;
    return wasSet;
  }
  /* Code from template attribute_SetDefaulted */
  public boolean setBallDirectionX(double aBallDirectionX)
  {
    boolean wasSet = false;
    ballDirectionX = aBallDirectionX;
    wasSet = true;
    return wasSet;
  }

  public boolean resetBallDirectionX()
  {
    boolean wasReset = false;
    ballDirectionX = getDefaultBallDirectionX();
    wasReset = true;
    return wasReset;
  }
  /* Code from template attribute_SetDefaulted */
  public boolean setBallDirectionY(double aBallDirectionY)
  {
    boolean wasSet = false;
    ballDirectionY = aBallDirectionY;
    wasSet = true;
    return wasSet;
  }

  public boolean resetBallDirectionY()
  {
    boolean wasReset = false;
    ballDirectionY = getDefaultBallDirectionY();
    wasReset = true;
    return wasReset;
  }
  /* Code from template attribute_SetDefaulted */
  public boolean setCurrentBallX(double aCurrentBallX)
  {
    boolean wasSet = false;
    currentBallX = aCurrentBallX;
    wasSet = true;
    return wasSet;
  }

  public boolean resetCurrentBallX()
  {
    boolean wasReset = false;
    currentBallX = getDefaultCurrentBallX();
    wasReset = true;
    return wasReset;
  }
  /* Code from template attribute_SetDefaulted */
  public boolean setCurrentBallY(double aCurrentBallY)
  {
    boolean wasSet = false;
    currentBallY = aCurrentBallY;
    wasSet = true;
    return wasSet;
  }

  public boolean resetCurrentBallY()
  {
    boolean wasReset = false;
    currentBallY = getDefaultCurrentBallY();
    wasReset = true;
    return wasReset;
  }

  public boolean setCurrentPaddleLength(double aCurrentPaddleLength)
  {
    boolean wasSet = false;
    currentPaddleLength = aCurrentPaddleLength;
    wasSet = true;
    return wasSet;
  }
  /* Code from template attribute_SetDefaulted */
  public boolean setCurrentPaddleX(double aCurrentPaddleX)
  {
    boolean wasSet = false;
    currentPaddleX = aCurrentPaddleX;
    wasSet = true;
    return wasSet;
  }

  public boolean resetCurrentPaddleX()
  {
    boolean wasReset = false;
    currentPaddleX = getDefaultCurrentPaddleX();
    wasReset = true;
    return wasReset;
  }

  public int getScore()
  {
    return score;
  }

  public int getLives()
  {
    return lives;
  }

  public int getCurrentLevel()
  {
    return currentLevel;
  }

  public double getWaitTime()
  {
    return waitTime;
  }

  /**
   * added here so that it only needs to be determined once
   */
  public String getPlayername()
  {
    return playername;
  }

  /**
   * 0/0 is the top left corner of the play area, i.e., a directionX/Y of 0/1 moves the ball down in a straight line
   */
  public double getBallDirectionX()
  {
    return ballDirectionX;
  }
  /* Code from template attribute_GetDefaulted */
  public double getDefaultBallDirectionX()
  {
    return getGame().getBall().getMinBallSpeedX();
  }

  public double getBallDirectionY()
  {
    return ballDirectionY;
  }
  /* Code from template attribute_GetDefaulted */
  public double getDefaultBallDirectionY()
  {
    return getGame().getBall().getMinBallSpeedY();
  }

  /**
   * the position of the ball is at the center of the ball
   */
  public double getCurrentBallX()
  {
    return currentBallX;
  }
  /* Code from template attribute_GetDefaulted */
  public double getDefaultCurrentBallX()
  {
    return BALL_INITIAL_X;
  }

  public double getCurrentBallY()
  {
    return currentBallY;
  }
  /* Code from template attribute_GetDefaulted */
  public double getDefaultCurrentBallY()
  {
    return BALL_INITIAL_Y;
  }

  public double getCurrentPaddleLength()
  {
    return currentPaddleLength;
  }

  /**
   * the position of the paddle is at its top right corner
   */
  public double getCurrentPaddleX()
  {
    return currentPaddleX;
  }
  /* Code from template attribute_GetDefaulted */
  public double getDefaultCurrentPaddleX()
  {
    return (Game.PLAY_AREA_SIDE - currentPaddleLength) / 2;
  }

  public double getCurrentPaddleY()
  {
    return currentPaddleY;
  }

  public int getId()
  {
    return id;
  }

  public String getPlayStatusFullName()
  {
    String answer = playStatus.toString();
    return answer;
  }

  public PlayStatus getPlayStatus()
  {
    return playStatus;
  }

  public boolean play()
  {
    boolean wasEventProcessed = false;
    
    PlayStatus aPlayStatus = playStatus;
    switch (aPlayStatus)
    {
      case Ready:
        setPlayStatus(PlayStatus.Moving);
        wasEventProcessed = true;
        break;
      case Paused:
        setPlayStatus(PlayStatus.Moving);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean pause()
  {
    boolean wasEventProcessed = false;
    
    PlayStatus aPlayStatus = playStatus;
    switch (aPlayStatus)
    {
      case Moving:
        setPlayStatus(PlayStatus.Paused);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean move()
  {
    boolean wasEventProcessed = false;
    
    PlayStatus aPlayStatus = playStatus;
    switch (aPlayStatus)
    {
      case Moving:
        if (hitPaddle())
        {
        // line 12 "../../../../../Block223States.ump"
          doHitPaddleOrWall();
          setPlayStatus(PlayStatus.Moving);
          wasEventProcessed = true;
          break;
        }
        if (isOutOfBoundsAndLastLife())
        {
        // line 13 "../../../../../Block223States.ump"
          doOutOfBounds();
          setPlayStatus(PlayStatus.GameOver);
          wasEventProcessed = true;
          break;
        }
        if (isOutOfBounds())
        {
        // line 14 "../../../../../Block223States.ump"
          doOutOfBounds();
          setPlayStatus(PlayStatus.Paused);
          wasEventProcessed = true;
          break;
        }
        if (hitLastBlockAndLastLevel())
        {
        // line 15 "../../../../../Block223States.ump"
          doHitBlock();
          setPlayStatus(PlayStatus.GameOver);
          wasEventProcessed = true;
          break;
        }
        if (hitLastBlock())
        {
        // line 16 "../../../../../Block223States.ump"
          doHitBlockNextLevel();
          setPlayStatus(PlayStatus.Ready);
          wasEventProcessed = true;
          break;
        }
        if (hitBlock())
        {
        // line 17 "../../../../../Block223States.ump"
          doHitBlock();
          setPlayStatus(PlayStatus.Moving);
          wasEventProcessed = true;
          break;
        }
        if (hitWall())
        {
        // line 18 "../../../../../Block223States.ump"
          doHitPaddleOrWall();
          setPlayStatus(PlayStatus.Moving);
          wasEventProcessed = true;
          break;
        }
        // line 19 "../../../../../Block223States.ump"
        doHitNothingAndNotOutOfBounds();
        setPlayStatus(PlayStatus.Moving);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  private void setPlayStatus(PlayStatus aPlayStatus)
  {
    playStatus = aPlayStatus;

    // entry actions and do activities
    switch(playStatus)
    {
      case Ready:
        // line 7 "../../../../../Block223States.ump"
        doSetup();
        break;
      case GameOver:
        // line 25 "../../../../../Block223States.ump"
        doGameOver();
        break;
    }
  }
  /* Code from template association_GetOne */
  public Player getPlayer()
  {
    return player;
  }

  public boolean hasPlayer()
  {
    boolean has = player != null;
    return has;
  }
  /* Code from template association_GetOne */
  public Game getGame()
  {
    return game;
  }
  /* Code from template association_GetMany */
  public PlayedBlockAssignment getBlock(int index)
  {
    PlayedBlockAssignment aBlock = blocks.get(index);
    return aBlock;
  }

  public List<PlayedBlockAssignment> getBlocks()
  {
    List<PlayedBlockAssignment> newBlocks = Collections.unmodifiableList(blocks);
    return newBlocks;
  }

  public int numberOfBlocks()
  {
    int number = blocks.size();
    return number;
  }

  public boolean hasBlocks()
  {
    boolean has = blocks.size() > 0;
    return has;
  }

  public int indexOfBlock(PlayedBlockAssignment aBlock)
  {
    int index = blocks.indexOf(aBlock);
    return index;
  }
  /* Code from template association_GetOne */
  public BouncePoint getBounce()
  {
    return bounce;
  }

  public boolean hasBounce()
  {
    boolean has = bounce != null;
    return has;
  }
  /* Code from template association_GetOne */
  public Block223 getBlock223()
  {
    return block223;
  }
  /* Code from template association_SetOptionalOneToMany */
  public boolean setPlayer(Player aPlayer)
  {
    boolean wasSet = false;
    Player existingPlayer = player;
    player = aPlayer;
    if (existingPlayer != null && !existingPlayer.equals(aPlayer))
    {
      existingPlayer.removePlayedGame(this);
    }
    if (aPlayer != null)
    {
      aPlayer.addPlayedGame(this);
    }
    wasSet = true;
    return wasSet;
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
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfBlocks()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public PlayedBlockAssignment addBlock(int aX, int aY, Block aBlock)
  {
    return new PlayedBlockAssignment(aX, aY, aBlock, this);
  }

  public boolean addBlock(PlayedBlockAssignment aBlock)
  {
    boolean wasAdded = false;
    if (blocks.contains(aBlock)) { return false; }
    PlayedGame existingPlayedGame = aBlock.getPlayedGame();
    boolean isNewPlayedGame = existingPlayedGame != null && !this.equals(existingPlayedGame);
    if (isNewPlayedGame)
    {
      aBlock.setPlayedGame(this);
    }
    else
    {
      blocks.add(aBlock);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeBlock(PlayedBlockAssignment aBlock)
  {
    boolean wasRemoved = false;
    //Unable to remove aBlock, as it must always have a playedGame
    if (!this.equals(aBlock.getPlayedGame()))
    {
      blocks.remove(aBlock);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addBlockAt(PlayedBlockAssignment aBlock, int index)
  {  
    boolean wasAdded = false;
    if(addBlock(aBlock))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfBlocks()) { index = numberOfBlocks() - 1; }
      blocks.remove(aBlock);
      blocks.add(index, aBlock);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveBlockAt(PlayedBlockAssignment aBlock, int index)
  {
    boolean wasAdded = false;
    if(blocks.contains(aBlock))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfBlocks()) { index = numberOfBlocks() - 1; }
      blocks.remove(aBlock);
      blocks.add(index, aBlock);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addBlockAt(aBlock, index);
    }
    return wasAdded;
  }
  /* Code from template association_SetUnidirectionalOptionalOne */
  public boolean setBounce(BouncePoint aNewBounce)
  {
    boolean wasSet = false;
    bounce = aNewBounce;
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
    if (player != null)
    {
      Player placeholderPlayer = player;
      this.player = null;
      placeholderPlayer.removePlayedGame(this);
    }
    Game placeholderGame = game;
    this.game = null;
    if(placeholderGame != null)
    {
      placeholderGame.removePlayedGame(this);
    }
    while (blocks.size() > 0)
    {
      PlayedBlockAssignment aBlock = blocks.get(blocks.size() - 1);
      aBlock.delete();
      blocks.remove(aBlock);
    }
    
    bounce = null;
    Block223 placeholderBlock223 = block223;
    this.block223 = null;
    if(placeholderBlock223 != null)
    {
      placeholderBlock223.removePlayedGame(this);
    }
  }

  // line 114 "../../../../../Block223Persistence.ump"
   public static  void reinitializeAutouniqueID(List<PlayedGame> playedGames){
    nextId = 0;
  		for(PlayedGame playedGame : playedGames) {
  			if(playedGame.getId() > nextId) {
  				nextId = playedGame.getId();
  			}
  		}
		nextId++;
  }


  /**
   * Guards
   * Haluk Calin - ball hit the wall or paddle
   */
  // line 34 "../../../../../Block223States.ump"
   private boolean hitPaddle(){
    BouncePoint bp = calculateBouncePointPaddle();
     setBounce(bp);
     return bp!=null;
  }


  /**
   * Melis Malki = ball out of bounds method
   */
  // line 42 "../../../../../Block223States.ump"
   private boolean isOutOfBoundsAndLastLife(){
    if (getLives() == 1){ 
     return isOutOfBounds();
     }
     return false;
  }


  /**
   * Melis Malki = ball out of bounds method
   */
  // line 50 "../../../../../Block223States.ump"
   private boolean isOutOfBounds(){
    if ( getCurrentBallY() > getCurrentPaddleY() + 5){
    return true;
    }    
    return false;
  }


  /**
   * Onur Cayci - ball hits block method
   */
  // line 59 "../../../../../Block223States.ump"
   private boolean hitLastBlockAndLastLevel(){
    Game game = this.getGame();
    int nrLevels = game.numberOfLevels();
    this.setBounce(null);
    if(nrLevels == currentLevel) {
    	int nrBlocks = numberOfBlocks();
    	if(nrBlocks == 1) {
    		PlayedBlockAssignment block = this.getBlock(0);
    		BouncePoint bp = this.calculateBouncePointBlock(block);
    		this.setBounce(bp);
    		return bp != null;
    	}
    }
    return false;
  }


  /**
   * Onur Cayci - ball hits block method
   */
  // line 77 "../../../../../Block223States.ump"
   private boolean hitLastBlock(){
    int nrBlocks = this.numberOfBlocks();
    this.setBounce(null);
    if(nrBlocks == 1) {
    	PlayedBlockAssignment block = this.getBlock(0);
    	BouncePoint bp = calculateBouncePointBlock(block);
    	this.setBounce(bp);
    	return bp != null;
    }
    return false;
  }


  /**
   * Onur Cayci - ball hits block method
   */
  // line 91 "../../../../../Block223States.ump"
   private boolean hitBlock(){
    int nrBlocks = this.numberOfBlocks();
    this.setBounce(null);
    for(int i = 0; i < nrBlocks; i++) {
    	PlayedBlockAssignment block = this.getBlock(i);
    	BouncePoint bp = calculateBouncePointBlock(block);
    	BouncePoint bounce = this.getBounce();
    	boolean closer = isCloser(bp, bounce);
    	if(closer) {
    		this.setBounce(bp);
    	}
    }
    return this.getBounce() != null;
  }


  /**
   * Haluk Calin - ball hit the wall or paddle
   */
  // line 108 "../../../../../Block223States.ump"
   private boolean hitWall(){
    BouncePoint bp = calculateBouncePointWall();
    setBounce(bp);

    return bp != null;
  }


  /**
   * Antkin - doSetUp
   */
  // line 119 "../../../../../Block223States.ump"
   private void doSetup(){
    resetCurrentBallX();
	   	resetCurrentBallY();
	   	resetBallDirectionX();
	   	resetBallDirectionY();
	   	resetCurrentPaddleX();
	   	Game game = getGame();
	   	Random rand = new Random();
	   	
	   	Level level = game.getLevel(getCurrentLevel() - 1);
	   	
	   	for(BlockAssignment a : level.getBlockAssignments()) {
	   		new PlayedBlockAssignment(Game.WALL_PADDING + (Block.SIZE + Game.COLUMNS_PADDING)* (a.getGridHorizontalPosition() - 1), Game.WALL_PADDING + (Block.SIZE + Game.ROW_PADDING) * (a.getGridVerticalPosition() - 1), a.getBlock(), this);
	   		
	   	}
	   	
	   	while(blocks.size() < game.getNrBlocksPerLevel()){
	   		boolean positionChosen = false;
	   		int x = rand.nextInt(BlockAssignment.maxNrHorizontalBlocks());
   			int y = rand.nextInt(BlockAssignment.maxNrVerticalBlocks());
   			x++;
   			y++;
	   		
   			while(positionChosen == false) {
   				if(level.findBlockAssignment(x, y) == null) {
   					positionChosen = true;
   				}
   				else {
   					if(x != (BlockAssignment.maxNrHorizontalBlocks())) {
   						x++;
   					}
   					else if(x == (BlockAssignment.maxNrHorizontalBlocks()) && y != (BlockAssignment.maxNrVerticalBlocks())) {
   						x = 1; 
   						y++;
   					}
   					else {
   						x = 1;
   						y = 1;
   					}
   				}
	   		}
   			
   			new PlayedBlockAssignment(Game.WALL_PADDING + (Block.SIZE + Game.COLUMNS_PADDING)* (x - 1), Game.WALL_PADDING + (Block.SIZE + Game.ROW_PADDING) * (y - 1), game.getRandomBlock(), this);
	   	}
  }


  /**
   * Haluk Calin - ball hit the wall or paddle
   */
  // line 167 "../../../../../Block223States.ump"
   private void doHitPaddleOrWall(){
    bounceBall();
  }


  /**
   * Melis Malki = ball out of bounds method
   */
  // line 174 "../../../../../Block223States.ump"
   private void doOutOfBounds(){
    int lives = getLives();
     setLives (lives-1);
     resetCurrentBallX();
     resetCurrentBallY();
     resetBallDirectionX();
     resetBallDirectionY();
     resetCurrentPaddleX();
  }


  /**
   * Onur Cayci - ball hits block method
   */
  // line 186 "../../../../../Block223States.ump"
   private void doHitBlock(){
    int score = this.getScore();
    BouncePoint bounce = this.getBounce();
    PlayedBlockAssignment pblock = bounce.getHitBlock();
    Block block = pblock.getBlock();
    int points = block.getPoints();
    this.setScore(score + points);
    pblock.delete();
    bounceBall();
  }


  /**
   * Onur Cayci - ball hits block method
   */
  // line 199 "../../../../../Block223States.ump"
   private void doHitBlockNextLevel(){
    doHitBlock();
    int level = this.getCurrentLevel();
    this.setCurrentLevel(level + 1);
    this.setCurrentPaddleLength(getGame().getPaddle().getMaxPaddleLength() - (getGame().getPaddle().getMaxPaddleLength() - getGame().getPaddle().getMinPaddleLength()) / (getGame().numberOfLevels()-1)*(getCurrentLevel()-1));
    this.setWaitTime(INITIAL_WAIT_TIME * java.lang.Math.pow(getGame().getBall().getBallSpeedIncreaseFactor(), (getCurrentLevel()-1)));
  }


  /**
   * Christina Riachi move Ball feature
   */
  // line 208 "../../../../../Block223States.ump"
   private void doHitNothingAndNotOutOfBounds(){
    double x = this.getCurrentBallX();
    double y = this.getCurrentBallY();
    double dx = this.getBallDirectionX();
    double dy = this.getBallDirectionY();
    this.setCurrentBallX(x+dx);
    this.setCurrentBallY(y+dy);
  }


  /**
   * Melis Malki = ball out of bounds method
   */
  // line 219 "../../../../../Block223States.ump"
   private void doGameOver(){
    //System.out.println("in do game over bitch");
    Player p  = getPlayer();
    if ( p != null){
    	Game game = getGame();
    	HallOfFameEntry hof = new HallOfFameEntry (score,playername,p,game,block223);
    	game.setMostRecentEntry(hof);
    }
    //System.out.println("before deleting the game bitch");
    this.delete();
    //System.out.println(this);
  }


  /**
   * Onur Cayci - ball hits block method
   */
  // line 234 "../../../../../Block223States.ump"
   private boolean isCloser(BouncePoint first, BouncePoint second){
    if(first == null) {
  		return false;
  	}
  	if(second == null) {
  		return true;
  	}
  	double distance1 = java.lang.Math.sqrt(((first.getX() - getCurrentBallX())*(first.getX() - getCurrentBallX()))+((first.getY() - getCurrentBallY())*(first.getY() - getCurrentBallY())));
  	double distance2 = java.lang.Math.sqrt(((second.getX() - getCurrentBallX())*(second.getX() - getCurrentBallX()))+((second.getY() - getCurrentBallY())*(second.getY() - getCurrentBallY())));
  	if(distance1 < distance2) {
  		return true;
  	}
  	return false;
  }


  /**
   * Onur Cayci - ball hits block method
   */
  // line 251 "../../../../../Block223States.ump"
   private BouncePoint calculateBouncePointBlock(PlayedBlockAssignment block){
    double blockX = block.getX(); //25 * (block.getX() - 1) + 10; //top left corner x-coordinate of the block
  	double blockY = block.getY(); //22 * (block.getY() - 1) + 10; //top left corner y-coordinate of the block
  	java.awt.geom.Rectangle2D blk = new java.awt.geom.Rectangle2D.Double(blockX, blockY, 20, 20);
  	java.awt.geom.Ellipse2D ball = new java.awt.geom.Ellipse2D.Double(currentBallX + ballDirectionX, currentBallY + ballDirectionY, 10, 10);
  	java.awt.geom.Rectangle2D A = new java.awt.geom.Rectangle2D.Double(blockX, blockY - 5, 20, 5);
  	java.awt.geom.Rectangle2D B = new java.awt.geom.Rectangle2D.Double(blockX - 5, blockY, 5, 20);
  	java.awt.geom.Rectangle2D C = new java.awt.geom.Rectangle2D.Double(blockX + 20, blockY, 5, 20);
  	java.awt.geom.Rectangle2D D = new java.awt.geom.Rectangle2D.Double(blockX, blockY + 20, 20, 5);
  	java.awt.geom.Line2D movement = new java.awt.geom.Line2D.Double(currentBallX, currentBallY, currentBallX + ballDirectionX, currentBallY + ballDirectionY);
  	
  	BouncePoint bp = null;
  	
  	double nextX = currentBallX + ballDirectionX;
	double nextY = currentBallY + ballDirectionY;
  	
  	if(A.intersectsLine(movement)) {
  		BouncePoint tmp = new BouncePoint(currentBallX + (ballDirectionX * (blockY-5-currentBallY) / ballDirectionY), blockY - 5, BouncePoint.BounceDirection.FLIP_Y);
  		if(isCloser(tmp, bp)) {
			   bp = tmp;
		   }
  	}
  	if(B.intersectsLine(movement)) {
  		BouncePoint tmp = new BouncePoint(blockX-5, currentBallY + (ballDirectionY * (blockX-5-currentBallX) / ballDirectionX), BouncePoint.BounceDirection.FLIP_X);
  		if(isCloser(tmp, bp)) {
			   bp = tmp;
		   }
  	}
  	if(C.intersectsLine(movement)) {
  		BouncePoint tmp = new BouncePoint(blockX+25,currentBallY + (ballDirectionY * (blockX+25-currentBallX) / ballDirectionX), BouncePoint.BounceDirection.FLIP_X);
  		if(isCloser(tmp, bp)) {
			   bp = tmp;
		   }
  	}
  	if(D.intersectsLine(movement)) {
  		BouncePoint tmp = new BouncePoint(currentBallX + (ballDirectionX * (blockY+25-currentBallY) / ballDirectionY), blockY + 25, BouncePoint.BounceDirection.FLIP_Y);
  		if(isCloser(tmp, bp)) {
			   bp = tmp;
		   }
  	}
  	
  	// compute the euclidean distance between A and B
    double LAB = java.lang.Math.sqrt((ballDirectionX * ballDirectionX)+(ballDirectionY * ballDirectionY));

    // compute the direction vector D from A to B
    double Dx = (ballDirectionX)/LAB;
    double Dy = (ballDirectionY)/LAB;

    // the equation of the line AB is x = Dx*t + Ax, y = Dy*t + Ay with 0 <= t <= LAB.

    // compute the distance between the points A and E, where
    // E is the point of AB closest the circle center (Cx, Cy)
    //For corner E
    double t = Dx*(blockX-currentBallX) + Dy*(blockY-currentBallY); 
    //For corner F
    double k = Dx*(blockX+20-currentBallX) + Dy*(blockY-currentBallY);
    //For corner G
    double z = Dx*(blockX-currentBallX) + Dy*(blockY+20-currentBallY);
    //For corner H
    double b = Dx*(blockX+20-currentBallX) + Dy*(blockY+20-currentBallY);

    // compute the coordinates of the point E
    //for corner E
    double Ex = (t * Dx) + currentBallX;
    double Ey = (t * Dy) + currentBallY;
    //for corner F
    double Fx = (k * Dx) + currentBallX;
    double Fy = (k * Dy) + currentBallY;
    //for corner G
    double Gx = (z * Dx) + currentBallX;
    double Gy = (z * Dy) + currentBallY;
    //for corner H
    double Hx = (b * Dx) + currentBallX;
    double Hy = (b * Dy) + currentBallY;

    // compute the euclidean distance between E and C, for E
    double LEC = java.lang.Math.sqrt(((Ex-blockX)*(Ex-blockX))+((Ey-blockY)*(Ey-blockY)));

    //for F
    double LFC = java.lang.Math.sqrt(((Fx-blockX-20)*(Fx-blockX-20)+(Fy-blockY)*(Fy-blockY)));
    //for G
    double LGC = java.lang.Math.sqrt(((Gx-blockX)*(Gx-blockX)+(Gy-blockY-20)*(Gy-blockY-20)));
    //for H
    double LHC = java.lang.Math.sqrt(((Hx-blockX-20)*(Hx-blockX-20)+(Hy-blockY-20)*(Hy-blockY-20)));

    // test if the line intersects the circle
    if(LEC < 5) {
        // compute distance from t to circle intersection point
        double dt = java.lang.Math.sqrt( 25 - (LEC*LEC));
        // compute first intersection point
        double Ix = (t-dt)*Dx + currentBallX;
        double Iy = (t-dt)*Dy + currentBallY;
        // compute second intersection point
        double Jx = (t+dt)*Dx + currentBallX;
        double Jy = (t+dt)*Dy + currentBallY;
        if(ballDirectionX < 0) {
        	BouncePoint tmp1 = new BouncePoint(Ix, Iy, BouncePoint.BounceDirection.FLIP_Y);
        	BouncePoint tmp2 = new BouncePoint(Jx, Jy, BouncePoint.BounceDirection.FLIP_Y);
        	if(isCloser(tmp1, bp)) {
        		bp = tmp1;
        		 if(Math.sqrt(Math.pow(nextX-blockX, 2) + Math.pow(nextY-blockY, 2)) > 5){
        	        	bp = null;
        	     }
        	}
        	else if(isCloser(tmp2, bp)) {
        		bp = tmp2;
        		if(Math.sqrt(Math.pow(nextX-blockX, 2) + Math.pow(nextY-blockY, 2)) > 5){
    	        	bp = null;
        		}
        	}
        } else {
        	BouncePoint tmp1 = new BouncePoint(Ix, Iy, BouncePoint.BounceDirection.FLIP_X);
        	BouncePoint tmp2 = new BouncePoint(Jx, Jy, BouncePoint.BounceDirection.FLIP_X);
        	if(isCloser(tmp1, bp)) {
        		bp =tmp1;
        		if(Math.sqrt(Math.pow(nextX-blockX, 2) + Math.pow(nextY-blockY, 2)) > 5){
    	        	bp = null;
        		}
        	}
        	else if (isCloser(tmp2, bp)) {
        		bp = tmp2;
        		if(Math.sqrt(Math.pow(nextX-blockX, 2) + Math.pow(nextY-blockY, 2)) > 5){
    	        	bp = null;
        		}
        	}
        }
       
    }

    // else test if the line is tangent to circle
    if( LEC == 5 ) {
        // tangent point to circle is E
    	if(ballDirectionX < 0) {
    		BouncePoint tmp = new BouncePoint(Ex, Ey, BouncePoint.BounceDirection.FLIP_Y);
    		if(isCloser(tmp, bp)) {
    			bp = tmp;
    		}
    	} else {
    		BouncePoint tmp = new BouncePoint(Ex, Ey, BouncePoint.BounceDirection.FLIP_X);
    		if(isCloser(tmp, bp)) {
    			bp = tmp;
    		}
    	}
    }
    
    if(LFC < 5) {
        // compute distance from t to circle intersection point
        double dt = java.lang.Math.sqrt( 25 - (LFC*LFC));

        // compute first intersection point
        double Ix = (k-dt)*Dx + currentBallX;
        double Iy = (k-dt)*Dy + currentBallY;
        // compute second intersection point
        double Jx = (k+dt)*Dx + currentBallX;
        double Jy = (k+dt)*Dy + currentBallY;
        if(ballDirectionX < 0) {
        	BouncePoint tmp1 = new BouncePoint(Ix, Iy, BouncePoint.BounceDirection.FLIP_X);
        	BouncePoint tmp2 = new BouncePoint(Jx, Jy, BouncePoint.BounceDirection.FLIP_X);
        	if(isCloser(tmp1, bp)) {
        		bp = tmp1;
        		if(Math.sqrt(Math.pow(nextX-(blockX+20), 2) + Math.pow(nextY-blockY, 2)) > 5){
                	bp = null;
                }
        	}
        	else if(isCloser(tmp2, bp)) {
        		bp = tmp2;
        		if(Math.sqrt(Math.pow(nextX-(blockX+20), 2) + Math.pow(nextY-blockY, 2)) > 5){
                	bp = null;
                }
        	}
        } else {
        	BouncePoint tmp1 = new BouncePoint(Ix, Iy, BouncePoint.BounceDirection.FLIP_Y);
        	BouncePoint tmp2 = new BouncePoint(Jx, Jy, BouncePoint.BounceDirection.FLIP_Y);
        	if(isCloser(tmp1, bp)) {
        		bp =tmp1;
        		if(Math.sqrt(Math.pow(nextX-(blockX+20), 2) + Math.pow(nextY-blockY, 2)) > 5){
                	bp = null;
                }
        	}
        	else if (isCloser(tmp2, bp)) {
        		bp = tmp2;
        		if(Math.sqrt(Math.pow(nextX-(blockX+20), 2) + Math.pow(nextY-blockY, 2)) > 5){
                	bp = null;
                }
        	}
        }
    }

    // else test if the line is tangent to circle
    if( LFC == 5 ) {
        // tangent point to circle is E
    	if(ballDirectionX < 0) {
    		BouncePoint tmp = new BouncePoint(Fx, Fy, BouncePoint.BounceDirection.FLIP_X);
    		if(isCloser(tmp, bp)) {
    			bp = tmp;
    		}
    	} else {
    		BouncePoint tmp = new BouncePoint(Fx, Fy, BouncePoint.BounceDirection.FLIP_Y);
    		if(isCloser(tmp, bp)) {
    			bp = tmp;
    		}
    	}
    }
    if(LGC < 5) {
        // compute distance from t to circle intersection point
        double dt = java.lang.Math.sqrt( 25 - (LGC*LGC));

        // compute first intersection point
        double Ix = (z-dt)*Dx + currentBallX;
        double Iy = (z-dt)*Dy + currentBallY;
        // compute second intersection point
        double Jx = (z+dt)*Dx + currentBallX;
        double Jy = (z+dt)*Dy + currentBallY;
        if(ballDirectionX < 0) {
        	BouncePoint tmp1 = new BouncePoint(Ix, Iy, BouncePoint.BounceDirection.FLIP_Y);
        	BouncePoint tmp2 = new BouncePoint(Jx, Jy, BouncePoint.BounceDirection.FLIP_Y);
        	if(isCloser(tmp1, bp)) {
        		bp = tmp1;
        		if(Math.sqrt(Math.pow(nextX-blockX, 2) + Math.pow(nextY-blockY-20, 2)) > 5){
                	bp = null;
                }
        	}
        	else if(isCloser(tmp2, bp)) {
        		bp = tmp2;
        		if(Math.sqrt(Math.pow(nextX-blockX, 2) + Math.pow(nextY-blockY-20, 2)) > 5){
                	bp = null;
                }
        	}
        } else {
        	BouncePoint tmp1 = new BouncePoint(Ix, Iy, BouncePoint.BounceDirection.FLIP_X);
        	BouncePoint tmp2 = new BouncePoint(Jx, Jy, BouncePoint.BounceDirection.FLIP_X);
        	if(isCloser(tmp1, bp)) {
        		bp =tmp1;
        		if(Math.sqrt(Math.pow(nextX-blockX, 2) + Math.pow(nextY-blockY-20, 2)) > 5){
                	bp = null;
                }
        	}
        	else if (isCloser(tmp2, bp)) {
        		bp = tmp2;
        		if(Math.sqrt(Math.pow(nextX-blockX, 2) + Math.pow(nextY-blockY-20, 2)) > 5){
                	bp = null;
                }
        	}
        }
    }

    // else test if the line is tangent to circle
    if( LGC == 5 ) {
        // tangent point to circle is E
    	if(ballDirectionX < 0) {
    		BouncePoint tmp = new BouncePoint(Gx, Gy, BouncePoint.BounceDirection.FLIP_X);
    		if(isCloser(tmp, bp)) {
    			bp = tmp;
    		}
    	} else {
    		BouncePoint tmp = new BouncePoint(Gx, Gy, BouncePoint.BounceDirection.FLIP_Y);
    		if(isCloser(tmp, bp)) {
    			bp = tmp;
    		}
    	}
    }
    if(LHC < 5) {
        // compute distance from t to circle intersection point
        double dt = java.lang.Math.sqrt( 25 - (LHC*LHC));

        // compute first intersection point
        double Ix = (b-dt)*Dx + currentBallX;
        double Iy = (b-dt)*Dy + currentBallY;
        // compute second intersection point
        double Jx = (b+dt)*Dx + currentBallX;
        double Jy = (b+dt)*Dy + currentBallY;
        if(ballDirectionX < 0) {
        	BouncePoint tmp1 = new BouncePoint(Ix, Iy, BouncePoint.BounceDirection.FLIP_X);
        	BouncePoint tmp2 = new BouncePoint(Jx, Jy, BouncePoint.BounceDirection.FLIP_X);
        	if(isCloser(tmp1, bp)) {
        		bp = tmp1;
        		if(Math.sqrt(Math.pow(nextX-(blockX+20), 2) + Math.pow(nextY-(blockY+20), 2)) > 5){
                	bp = null;
                }
        	}
        	else if(isCloser(tmp2, bp)) {
        		bp = tmp2;
        		if(Math.sqrt(Math.pow(nextX-(blockX+20), 2) + Math.pow(nextY-(blockY+20), 2)) > 5){
                	bp = null;
                }
        	}
        } else {
        	BouncePoint tmp1 = new BouncePoint(Ix, Iy, BouncePoint.BounceDirection.FLIP_Y);
        	BouncePoint tmp2 = new BouncePoint(Jx, Jy, BouncePoint.BounceDirection.FLIP_Y);
        	if(isCloser(tmp1, bp)) {
        		bp =tmp1;
        		if(Math.sqrt(Math.pow(nextX-(blockX+20), 2) + Math.pow(nextY-(blockY+20), 2)) > 5){
                	bp = null;
                }
        	}
        	else if (isCloser(tmp2, bp)) {
        		bp = tmp2;
        		if(Math.sqrt(Math.pow(nextX-(blockX+20), 2) + Math.pow(nextY-(blockY+20), 2)) > 5){
                	bp = null;
                }
        	}
        }
    }

    // else test if the line is tangent to circle
    if( LHC == 5 ) {
        // tangent point to circle is E
    	if(ballDirectionX < 0) {
    		BouncePoint tmp = new BouncePoint(Hx, Hy, BouncePoint.BounceDirection.FLIP_X);
    		if(isCloser(tmp, bp)) {
    			bp = tmp;
    		}
    	} else {
    		BouncePoint tmp = new BouncePoint(Hx, Hy, BouncePoint.BounceDirection.FLIP_Y);
    		if(isCloser(tmp, bp)) {
    			bp = tmp;
    		}
    	}
    }
  	if(bp != null && nextX == bp.getX() && nextY == bp.getY()) {
		   return bp = null;
	   }
  	
  	
  	if(bp != null) {
  		bp.setHitBlock(block);
  	}
  	return bp;
  }


  /**
   * Haluk ball hits wall method
   */
  // line 583 "../../../../../Block223States.ump"
   private BouncePoint calculateBouncePointWall(){
    java.awt.geom.Rectangle2D A = new java.awt.geom.Rectangle2D.Double(0, 0, 5, 385);
	   java.awt.geom.Rectangle2D B = new java.awt.geom.Rectangle2D.Double(5, 0, 380, 5);
	   java.awt.geom.Rectangle2D C = new java.awt.geom.Rectangle2D.Double(385, 0, 5, 385);
	   java.awt.geom.Line2D movement = new java.awt.geom.Line2D.Double(this.getCurrentBallX(), this.getCurrentBallY(), this.getCurrentBallX() + this.getBallDirectionX(), this.getCurrentBallY() + this.getBallDirectionY());
	   
	   double nextX = currentBallX + ballDirectionX;
	   double nextY = currentBallY + ballDirectionY;
	   
	   BouncePoint bp = null;
	   
	   //A&B corner
	   if(A.intersectsLine(movement) && B.intersectsLine(movement)) {
		   BouncePoint tmp = new BouncePoint(5, 5, BouncePoint.BounceDirection.FLIP_BOTH);
		   if(isCloser(tmp, bp)) {
			   bp = tmp;
		   }
	   }
	   //B&C Corner
	   if(C.intersectsLine(movement) && B.intersectsLine(movement)) {
		   BouncePoint tmp = new BouncePoint(385, 5, BouncePoint.BounceDirection.FLIP_BOTH);
		   if(isCloser(tmp, bp)) {
			   bp = tmp;
		   }
	   }
	   //A
	   if(A.intersectsLine(movement)) {
		   BouncePoint tmp = new BouncePoint(5, currentBallY + (ballDirectionY * (5-currentBallX) / ballDirectionX), BouncePoint.BounceDirection.FLIP_X);
		   if(isCloser(tmp, bp)) {
			   bp = tmp;
		   }
	   }
	   //B
	   if(B.intersectsLine(movement)) {
		   BouncePoint tmp = new BouncePoint(currentBallX + (ballDirectionX * (5-currentBallY) / ballDirectionY), 5, BouncePoint.BounceDirection.FLIP_Y);
		   if(isCloser(tmp, bp)) {
			   bp = tmp;
		   }
	   }
	   //C
	   if(C.intersectsLine(movement)) {
		  BouncePoint tmp = new BouncePoint(385, currentBallY + (ballDirectionY * (385 - currentBallX) / ballDirectionX), BouncePoint.BounceDirection.FLIP_X);
		  if(isCloser(tmp, bp)) {
			  bp = tmp;
		  }
	   }
	   if(bp != null && nextX == bp.getX() && nextY == bp.getY()) {
		   return null;
	   }
	   return bp;
  }

  // line 636 "../../../../../Block223States.ump"
   private BouncePoint calculateBouncePointPaddle(){
    java.awt.geom.Rectangle2D paddle = new java.awt.geom.Rectangle2D.Double(currentPaddleX, currentPaddleY, currentPaddleLength, 5);
	java.awt.geom.Ellipse2D ball = new java.awt.geom.Ellipse2D.Double(currentBallX + ballDirectionX, currentBallY + ballDirectionY, 10, 10);
    java.awt.geom.Rectangle2D A = new java.awt.geom.Rectangle2D.Double(currentPaddleX, currentPaddleY - 5, currentPaddleLength, 5);
    java.awt.geom.Rectangle2D B = new java.awt.geom.Rectangle2D.Double(currentPaddleX - 5 , currentPaddleY, 5, 5);
    java.awt.geom.Rectangle2D C = new java.awt.geom.Rectangle2D.Double(currentPaddleX + currentPaddleLength, currentPaddleY, 5, 5);
    java.awt.geom.Rectangle2D E = new java.awt.geom.Rectangle2D.Double(currentPaddleX - 5, currentPaddleY - 5, 5, 5);
    java.awt.geom.Rectangle2D F = new java.awt.geom.Rectangle2D.Double(currentPaddleX + currentPaddleLength, currentPaddleY - 5, 5, 5);
    java.awt.geom.Line2D movement = new java.awt.geom.Line2D.Double(this.getCurrentBallX(), this.getCurrentBallY(), this.getCurrentBallX() + this.getBallDirectionX(), this.getCurrentBallY() + this.getBallDirectionY());
    
    double nextX = currentBallX + ballDirectionX;
	double nextY = currentBallY + ballDirectionY;
    
    BouncePoint bp = null;
  	
  	//Removed this method because it was not detecting intersections at time
   /*
    if(!paddle.getBounds().intersects(ball.getBounds())) {
    	return null;
    }
   */
  	if(A.intersectsLine(movement)) {
  		BouncePoint tmp = new BouncePoint(currentBallX + (ballDirectionX * (currentPaddleY-5-currentBallY) / ballDirectionY), currentPaddleY - 5, BouncePoint.BounceDirection.FLIP_Y);
  		if(isCloser(tmp, bp)) {
  			bp = tmp;
  		}
  	}
  	//B
  	if(B.intersectsLine(movement)) {
  		BouncePoint tmp = new BouncePoint(currentPaddleX-5, currentBallY + (ballDirectionY * (currentPaddleX-5-currentBallX) / ballDirectionX), BouncePoint.BounceDirection.FLIP_X);
  		if(isCloser(tmp, bp)) {
  			bp = tmp;
  		}
  	}
  	//C
  	if(C.intersectsLine(movement)) {
  		BouncePoint tmp = new BouncePoint(currentPaddleX+currentPaddleLength+5,currentBallY + (ballDirectionY * (currentPaddleX+currentPaddleLength+5-currentBallX) / ballDirectionX), BouncePoint.BounceDirection.FLIP_X);
  		if(isCloser(tmp, bp)) {
  			bp = tmp;
  		}
  	}
  	// compute the euclidean distance between A and B
    double LAB = java.lang.Math.sqrt((ballDirectionX * ballDirectionX)+(ballDirectionY * ballDirectionY));

    // compute the direction vector D from A to B
    double Dx = (ballDirectionX)/LAB;
    double Dy = (ballDirectionY)/LAB;

    // the equation of the line AB is x = Dx*t + Ax, y = Dy*t + Ay with 0 <= t <= LAB.

    // compute the distance between the points A and E, where
    // E is the point of AB closest the circle center (Cx, Cy)
    //For corner E
    double t = Dx*(currentPaddleX-currentBallX) + Dy*(currentPaddleY-currentBallY); 
    //For corner F
    double k = Dx*(currentPaddleX+currentPaddleLength-currentBallX) + Dy*(currentPaddleY-currentBallY);

    // compute the coordinates of the point E
    //for corner E
    double Ex = t * Dx + currentBallX;
    double Ey = t * Dy + currentBallY;
    //for corner F
    double Fx = k * Dx + currentBallX;
    double Fy = k * Dy + currentBallY;

    // compute the euclidean distance between E and C, for E
    double LEC = java.lang.Math.sqrt(((Ex-currentPaddleX)*(Ex-currentPaddleX))+((Ey-currentPaddleY)*(Ey - currentPaddleY)));
    //for F
    double LFC = java.lang.Math.sqrt(((Fx-currentPaddleX-currentPaddleLength)*(Fx-currentPaddleX-currentPaddleLength)+(Fy-currentPaddleY)*(Fy-currentPaddleY)));

    // test if the line intersects the circle
    if(LEC < 5) {
        // compute distance from t to circle intersection point
        double dt = java.lang.Math.sqrt( 25 - (LEC*LEC));
        // compute first intersection point
        double Gx = (t-dt)*Dx + currentBallX;
        double Gy = (t-dt)*Dy + currentBallY;
        // compute second intersection point
        double Hx = (t+dt)*Dx + currentBallX;
        double Hy = (t+dt)*Dy + currentBallY;
        if(ballDirectionX < 0) {
        	BouncePoint tmp1 = new BouncePoint(Gx, Gy, BouncePoint.BounceDirection.FLIP_Y);
        	BouncePoint tmp2 = new BouncePoint(Hx, Hy, BouncePoint.BounceDirection.FLIP_Y);
        	if(isCloser(tmp1, bp)) {
        		bp = tmp1;
        		if(Math.sqrt(Math.pow(currentBallX - currentPaddleX, 2) + Math.pow(currentBallY - currentPaddleY, 2)) > 5) {
        			bp = null;
        		}
        	}
        	else if(isCloser(tmp2, bp)) {
        		bp = tmp2;
        		if(Math.sqrt(Math.pow(currentBallX - currentPaddleX, 2) + Math.pow(currentBallY - currentPaddleY, 2)) > 5) {
        			bp = null;
        		}
        	}
        } else {
        	BouncePoint tmp1 = new BouncePoint(Gx, Gy, BouncePoint.BounceDirection.FLIP_X);
        	BouncePoint tmp2 = new BouncePoint(Hx, Hy, BouncePoint.BounceDirection.FLIP_X);
        	if(isCloser(tmp1, bp)) {
        		bp =tmp1;
        		if(Math.sqrt(Math.pow(currentBallX - currentPaddleX, 2) + Math.pow(currentBallY - currentPaddleY, 2)) > 5) {
        			bp = null;
        		}
        	}
        	else if (isCloser(tmp2, bp)) {
        		bp = tmp2;
        		if(Math.sqrt(Math.pow(currentBallX - currentPaddleX, 2) + Math.pow(currentBallY - currentPaddleY, 2)) > 5) {
        			bp = null;
        		}
        	}
        }
    }

    // else test if the line is tangent to circle
    else if( LEC == 5 ) {
        // tangent point to circle is E
    	if(ballDirectionX < 0) {
    		BouncePoint tmp = new BouncePoint(Ex, Ey, BouncePoint.BounceDirection.FLIP_Y);
    		if(isCloser(tmp, bp)) {
    			bp = tmp;
    			if(Math.sqrt(Math.pow(currentBallX - currentPaddleX, 2) + Math.pow(currentBallY - currentPaddleY, 2)) > 5) {
        			bp = null;
        		}
    		}
    	} else {
    		BouncePoint tmp = new BouncePoint(Ex, Ey, BouncePoint.BounceDirection.FLIP_X);
    		if(isCloser(tmp, bp)) {
    			bp = tmp;
    			if(Math.sqrt(Math.pow(currentBallX - currentPaddleX, 2) + Math.pow(currentBallY - currentPaddleY, 2)) > 5) {
        			bp = null;
        		}
    		}
    	}
    } 
    
    if(LFC < 5) {
        // compute distance from t to circle intersection point
        double dt = java.lang.Math.sqrt( 25 - (LFC*LFC));

        // compute first intersection point
        double Gx = (k-dt)*Dx + currentBallX;
        double Gy = (k-dt)*Dy + currentBallY;
        // compute second intersection point
        double Hx = (k+dt)*Dx + currentBallX;
        double Hy = (k+dt)*Dy + currentBallY;
        if(ballDirectionX < 0) {
        	BouncePoint tmp1 = new BouncePoint(Gx, Gy, BouncePoint.BounceDirection.FLIP_X);
        	BouncePoint tmp2 = new BouncePoint(Hx, Hy, BouncePoint.BounceDirection.FLIP_X);
        	if(isCloser(tmp1, bp)) {
        		bp = tmp1;
        		if(Math.sqrt(Math.pow(currentBallX - currentPaddleX - currentPaddleLength, 2) + Math.pow(currentBallY - currentPaddleY, 2)) > 5) {
        			bp = null;
        		}
        	}
        	else if(isCloser(tmp2, bp)) {
        		bp = tmp2;
        		if(Math.sqrt(Math.pow(currentBallX - currentPaddleX - currentPaddleLength, 2) + Math.pow(currentBallY - currentPaddleY, 2)) > 5) {
        			bp = null;
        		}
        	}
        } else {
        	BouncePoint tmp1 = new BouncePoint(Gx, Gy, BouncePoint.BounceDirection.FLIP_Y);
        	BouncePoint tmp2 = new BouncePoint(Hx, Hy, BouncePoint.BounceDirection.FLIP_Y);
        	if(isCloser(tmp1, bp)) {
        		bp =tmp1;
        		if(Math.sqrt(Math.pow(currentBallX - currentPaddleX - currentPaddleLength, 2) + Math.pow(currentBallY - currentPaddleY, 2)) > 5) {
        			bp = null;
        		}
        	}
        	else if (isCloser(tmp2, bp)) {
        		bp = tmp2;
        		if(Math.sqrt(Math.pow(currentBallX - currentPaddleX - currentPaddleLength, 2) + Math.pow(currentBallY - currentPaddleY, 2)) > 5) {
        			bp = null;
        		}
        	}
        }
    }

    // else test if the line is tangent to circle
    else if( LFC == 5 ) {
        // tangent point to circle is E
    	if(ballDirectionX < 0) {
    		BouncePoint tmp = new BouncePoint(Fx, Fy, BouncePoint.BounceDirection.FLIP_X);
    		if(isCloser(tmp, bp)) {
    			bp = tmp;
    			if(Math.sqrt(Math.pow(currentBallX - currentPaddleX - currentPaddleLength, 2) + Math.pow(currentBallY - currentPaddleY, 2)) > 5) {
        			bp = null;
        		}
    		}
    	} else {
    		BouncePoint tmp = new BouncePoint(Fx, Fy, BouncePoint.BounceDirection.FLIP_Y);
    		if(isCloser(tmp, bp)) {
    			bp = tmp;
    			if(Math.sqrt(Math.pow(currentBallX - currentPaddleX - currentPaddleLength, 2) + Math.pow(currentBallY - currentPaddleY, 2)) > 5) {
        			bp = null;
        		}
    		}
    	}
    }
    
    
  	if(bp != null && nextX == bp.getX() && nextY == bp.getY()) {
		   return null;
	   }
  	
  	/*if(ballDirectionY < 0) {
  		return null; 
  	}*/
  	
  	return bp;
  }

  // line 848 "../../../../../Block223States.ump"
   private void bounceBall(){
    //FLIP_Y case
	   	BouncePoint.BounceDirection bd = this.bounce.getDirection();
	   	if(bd.equals(BouncePoint.BounceDirection.FLIP_Y)) {
	   		double in = bounce.getY() - currentBallY; //incoming distance
	   		double out = ballDirectionY - in; //outgoing distance
	   		if(out == 0) {
	   			currentBallX = bounce.getX();
	   			currentBallY = bounce.getY();
	   		}
	   		double DirectionX = ballDirectionX;
	   		double DirectionY = ballDirectionY;
	   		ballDirectionY = (-1)*DirectionY;
	   		if(DirectionX == 0) {
	   			ballDirectionX = 0.1 * java.lang.Math.abs(ballDirectionY);
	   		} else {
	   			ballDirectionX = DirectionX + (java.lang.Math.signum(DirectionX) * 0.1 * java.lang.Math.abs(ballDirectionY));
	   		}
	   		currentBallY = bounce.getY() + (out/DirectionY * ballDirectionY);
	   		currentBallX = bounce.getX() + (out/DirectionY * ballDirectionX);
	   	}
	   	//FLIP_X case
	   	if(bd.equals(BouncePoint.BounceDirection.FLIP_X)) {
	   		double in = bounce.getX() - currentBallX;
	   		double out = ballDirectionX - in;
	   		if(out == 0) {
	   			currentBallX = bounce.getX();
	   			currentBallY = bounce.getY();
	   		}
	   		double DirectionX = ballDirectionX;
	   		double DirectionY = ballDirectionY;
	   		ballDirectionX = -DirectionX;
	   		if(DirectionY == 0) {
	   			ballDirectionY = 0.1 * java.lang.Math.abs(ballDirectionX);
	   		} else {
	   			ballDirectionY = DirectionY + (java.lang.Math.signum(DirectionY) * 0.1 * java.lang.Math.abs(ballDirectionX));
	   		}
	   		currentBallX = bounce.getX() + (out/DirectionX * ballDirectionX);
	   		currentBallY = bounce.getY() + (out/DirectionX * ballDirectionY);
	   	}
	   	//FLIP_BOTH case
	   	if(bd.equals(BouncePoint.BounceDirection.FLIP_BOTH)) {
	   		double inX = bounce.getX() - currentBallX;
	   		double remX = ballDirectionX - inX;
	   		double inY = bounce.getY() - currentBallY;
	   		double remY = ballDirectionY - inY;
	   		if(remX == 0 && remY == 0) {
	   			currentBallX = bounce.getX();
	   			currentBallY = bounce.getY();
	   		}
	   		double DirectionX = ballDirectionX;
	   		double DirectionY = ballDirectionY;
	   		ballDirectionX = -DirectionX;
	   		ballDirectionY = -DirectionY;
	   		currentBallX = bounce.getX() + (remX/DirectionX * ballDirectionX);
	   		currentBallY = bounce.getY() + (remY/DirectionY * ballDirectionY);
	   	}
	   	if(ballDirectionX >= 10 || ballDirectionY >= 10) {
	   		ballDirectionX /= 2;
	   		ballDirectionY /= 2;
	   	}
  }


  public String toString()
  {
    return super.toString() + "["+
            "id" + ":" + getId()+ "," +
            "score" + ":" + getScore()+ "," +
            "lives" + ":" + getLives()+ "," +
            "currentLevel" + ":" + getCurrentLevel()+ "," +
            "waitTime" + ":" + getWaitTime()+ "," +
            "playername" + ":" + getPlayername()+ "," +
            "ballDirectionX" + ":" + getBallDirectionX()+ "," +
            "ballDirectionY" + ":" + getBallDirectionY()+ "," +
            "currentBallX" + ":" + getCurrentBallX()+ "," +
            "currentBallY" + ":" + getCurrentBallY()+ "," +
            "currentPaddleLength" + ":" + getCurrentPaddleLength()+ "," +
            "currentPaddleX" + ":" + getCurrentPaddleX()+ "," +
            "currentPaddleY" + ":" + getCurrentPaddleY()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "player = "+(getPlayer()!=null?Integer.toHexString(System.identityHashCode(getPlayer())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "game = "+(getGame()!=null?Integer.toHexString(System.identityHashCode(getGame())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "bounce = "+(getBounce()!=null?Integer.toHexString(System.identityHashCode(getBounce())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "block223 = "+(getBlock223()!=null?Integer.toHexString(System.identityHashCode(getBlock223())):"null");
  }  
  //------------------------
  // DEVELOPER CODE - PROVIDED AS-IS
  //------------------------
  
  // line 112 "../../../../../Block223Persistence.ump"
  private static final long serialVersionUID = 8597675110221231714L ;

  
}