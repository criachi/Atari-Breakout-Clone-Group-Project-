/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse223.block.model;
import java.io.Serializable;
import java.util.*;

// line 11 "../../../../../Block223PlayMode.ump"
// line 100 "../../../../../Block223Persistence.ump"
// line 1 "../../../../../Block223States.ump"
public class PlayedGame implements Serializable
{

  //------------------------
  // STATIC VARIABLES
  //------------------------


  /**
   * at design time, the initial wait time may be adjusted as seen fit
   */
  public static final int INITIAL_WAIT_TIME = 1000;
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
  public static final int PADDLE_MOVE_RIGHT = 1;

  /**
   * pixels moved when left arrow key is pressed
   */
  public static final int PADDLE_MOVE_LEFT = -1;

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

  // line 105 "../../../../../Block223Persistence.ump"
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
	 if(bp == null) { 
		 return false;
     }
     setBounce(bp);
     return bp!=null;
  }


  /**
   * Melis Malki = ball out of bounds method
   */
  // line 45 "../../../../../Block223States.ump"
   private boolean isOutOfBoundsAndLastLife(){
    if (getLives() == 1){ 
     return isOutOfBounds();
     }
     return false;
  }


  /**
   * Melis Malki = ball out of bounds method
   */
  // line 53 "../../../../../Block223States.ump"
   private boolean isOutOfBounds(){
    if ( getCurrentBallY() > getCurrentPaddleY()){
    return true;
    }    
    return false;
  }


  /**
   * Onur Cayci - ball hits block method
   */
  // line 62 "../../../../../Block223States.ump"
   private boolean hitLastBlockAndLastLevel(){
    Game game = this.getGame();
    int nrLevels = game.numberOfLevels();
    this.setBounce(null);
    if(nrLevels == this.currentLevel) {
    	int nrBlocks = this.numberOfBlocks();
    	if(nrBlocks == 1) {
    		PlayedBlockAssignment block = this.getBlock(0);
    		BouncePoint bp = this.calculateBouncePointBlock(block);
    		if(bp == null) {
    			return false;
    		}
    		this.setBounce(bp);
    		return true;
    	}
    }
    return false;
  }


  /**
   * Onur Cayci - ball hits block method
   */
  // line 83 "../../../../../Block223States.ump"
   private boolean hitLastBlock(){
    int nrBlocks = this.numberOfBlocks();
    this.setBounce(null);
    if(nrBlocks == 1) {
    	PlayedBlockAssignment block = this.getBlock(0);
    	BouncePoint bp = calculateBouncePointBlock(block);
    	if(bp == null) {
    		return false;
    	}
    	this.setBounce(bp);
    	return true;
    }
    return false;
  }


  /**
   * Onur Cayci - ball hits block method
   */
  // line 100 "../../../../../Block223States.ump"
   private boolean hitBlock(){
    int nrBlocks = this.numberOfBlocks();
    this.setBounce(null);
    for(int i = 0; i < nrBlocks; i++) {
    	PlayedBlockAssignment block = this.getBlock(i);
    	BouncePoint bp = calculateBouncePointBlock(block);
    	if(bp == null) {
    		return false;
    	}
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
  // line 120 "../../../../../Block223States.ump"
   private boolean hitWall(){
    BouncePoint bp = calculateBouncePointWall();
	 if(bp == null) { 
		 return false;
    }
    setBounce(bp);

    return bp != null;
  }


  /**
   * Antkin - doSetUp
   */
  // line 134 "../../../../../Block223States.ump"
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
	   		new PlayedBlockAssignment((Game.WALL_PADDING + Block.SIZE + Game.COLUMNS_PADDING)* (a.getGridHorizontalPosition() - 1), Game.WALL_PADDING + (Block.SIZE + Game.ROW_PADDING) * (a.getGridVerticalPosition() - 1), a.getBlock(), this);
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
   			
   			new PlayedBlockAssignment(x, y, game.getRandomBlock(), this);
	   	}
  }


  /**
   * Haluk Calin - ball hit the wall or paddle
   */
  // line 181 "../../../../../Block223States.ump"
   private void doHitPaddleOrWall(){
    bounceBall();
  }


  /**
   * Melis Malki = ball out of bounds method
   */
  // line 188 "../../../../../Block223States.ump"
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
  // line 200 "../../../../../Block223States.ump"
   private void doHitBlock(){
    int score = this.getScore();
    BouncePoint bounce = this.getBounce();
    PlayedBlockAssignment pblock = bounce.getHitBlock();
    Block block = pblock.getBlock();
    int bscore = block.getPoints();
    this.setScore(score + bscore);
    pblock.delete();
    this.bounceBall();
  }


  /**
   * Onur Cayci - ball hits block method
   */
  // line 213 "../../../../../Block223States.ump"
   private void doHitBlockNextLevel(){
    this.doHitBlock();
    int level = this.getCurrentLevel();
    this.setCurrentLevel(level + 1);
    this.setCurrentPaddleLength(getGame().getPaddle().getMaxPaddleLength() - (getGame().getPaddle().getMinPaddleLength()) / (getGame().numberOfLevels()-1)*(getCurrentLevel()-1));
    this.setWaitTime(INITIAL_WAIT_TIME * java.lang.Math.pow(getGame().getBall().getBallSpeedIncreaseFactor(), (getCurrentLevel()-1)));
  }


  /**
   * Christina Riachi move Ball feature
   */
  // line 222 "../../../../../Block223States.ump"
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
  // line 233 "../../../../../Block223States.ump"
   private void doGameOver(){
    Player p  = getPlayer();
    if ( p != null){
    	Game game = getGame();
    	HallOfFameEntry hof = new HallOfFameEntry (score,playername,p,game,block223);
    	game.setMostRecentEntry(hof);
    	}
    delete();
  }


  /**
   * Onur Cayci - ball hits block method
   */
  // line 245 "../../../../../Block223States.ump"
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
  // line 262 "../../../../../Block223States.ump"
   private BouncePoint calculateBouncePointBlock(PlayedBlockAssignment block){
    double blockX = 25 * (block.getX() - 1); //top left corner x-coordinate of the block
  	double blockY = 22 * (block.getY() - 1); //top left corner y-coordinate of the block
  	java.awt.geom.Rectangle2D rect = new java.awt.geom.Rectangle2D.Double(blockX, blockY, 30, 30);
  	java.awt.geom.Ellipse2D ball = new java.awt.geom.Ellipse2D.Double(this.getCurrentBallX() + this.getBallDirectionX(), this.getCurrentBallY() + this.getBallDirectionY(), 10, 10);
  	if(!rect.getBounds2D().intersects(ball.getBounds2D())) return null;
  	
  	if(blockY > this.getCurrentBallY()) {
  	//option A
  		if((this.getCurrentBallX() + this.getBallDirectionX()) != blockX || (this.getCurrentBallX() + this.getBallDirectionX()) != (blockX + 20)) {
  			return new BouncePoint(((2*currentBallX + ballDirectionX)*(blockY - 5)/(2*currentBallY + ballDirectionY)), blockY - 5, BouncePoint.BounceDirection.FLIP_Y);
  		}
  	//option E approaching from right
  		if((this.getCurrentBallX() + this.getBallDirectionX()) == blockX && (this.getCurrentBallX() + this.getBallDirectionX()) < this.getBallDirectionX()) { 
  			return new BouncePoint(((2*currentBallX + ballDirectionX)*(blockY - 5)/(2*currentBallY + ballDirectionY)), blockY - 5, BouncePoint.BounceDirection.FLIP_Y);
  		}
  	//option E approaching from left
  		if((currentBallX + ballDirectionX) == blockX && (currentBallX + ballDirectionX) > currentBallX) {
  			return new BouncePoint(((2*currentBallX + ballDirectionX)*(blockY - 5)/(2*currentBallY + ballDirectionY)), blockY - 5, BouncePoint.BounceDirection.FLIP_X);
  		}
  	//option F aproaching from right
  		if((this.getCurrentBallX() + this.getBallDirectionX()) == (blockX + 20) && (currentBallX + ballDirectionX) < currentBallX) {
  			return new BouncePoint(((2*currentBallX + ballDirectionX)*(blockY - 5)/(2*currentBallY + ballDirectionY)), blockY - 5, BouncePoint.BounceDirection.FLIP_X);
  		}
  	//option F approaching from left
  		if((currentBallX + ballDirectionX) == (blockX + 20) && (currentBallX + ballDirectionX) > currentBallX) {
  		return new BouncePoint(((2*currentBallX + ballDirectionX)*(blockY - 5)/(2*currentBallY + ballDirectionY)), blockY - 5, BouncePoint.BounceDirection.FLIP_Y);
  		}
  	}
  	
  	//if the ball comes from the bottom
  	
  	if((blockY + 20) < this.getCurrentBallY()) {
  	//option D
  		if((this.getCurrentBallX() + this.getBallDirectionX()) != blockX || (this.getCurrentBallX() + this.getBallDirectionX()) != (blockX + 20)) {
  			return new BouncePoint(((2*currentBallX + ballDirectionX)*(blockY + 25)/(2*currentBallY + ballDirectionY)), blockY + 25, BouncePoint.BounceDirection.FLIP_Y);
  		}
  	//option G approaching from right
  		if((this.getCurrentBallX() + this.getBallDirectionX()) == blockX && (currentBallX + ballDirectionX) < currentBallX) {
  			return new BouncePoint(((2*currentBallX + ballDirectionX)*(blockY + 25)/(2*currentBallY + ballDirectionY)), blockY + 25, BouncePoint.BounceDirection.FLIP_Y);
  		}
  	//option E approaching from left
  		if((currentBallX + ballDirectionX) == blockX && (currentBallX + ballDirectionX) > currentBallX) {
  			return new BouncePoint(((2*currentBallX + ballDirectionX)*(blockY + 25)/(2*currentBallY + ballDirectionY)), blockY + 25, BouncePoint.BounceDirection.FLIP_X);
  		}
  	//option F aproaching from right
  		if((this.getCurrentBallX() + this.getBallDirectionX()) == (blockX + 20) && (currentBallX + ballDirectionX) < currentBallX) {
  			return new BouncePoint(((2*currentBallX + ballDirectionX)*(blockY + 25)/(2*currentBallY + ballDirectionY)), blockY + 25, BouncePoint.BounceDirection.FLIP_X);
  		}
  	//option F approaching from left
  		if((currentBallX + ballDirectionX) == (blockX + 20) && (currentBallX + ballDirectionX) > currentBallX) {
  		return new BouncePoint(((2*currentBallX + ballDirectionX)*(blockY + 25)/(2*currentBallY + ballDirectionY)), blockY + 25, BouncePoint.BounceDirection.FLIP_Y);
  		}
  	}
  	
  	//if the ball comes from the left
  	
  	if(blockX > this.getCurrentBallX()) {
  	//option B
  	if((this.getCurrentBallY() + this.getBallDirectionY()) != blockY || (this.getCurrentBallY() + this.getBallDirectionY()) != (blockY + 20)) {
  			return new BouncePoint(blockX - 5, this.getCurrentBallY() + this.getBallDirectionY(), BouncePoint.BounceDirection.FLIP_X);
  		}
  	//option E
  		if((this.getCurrentBallY() + this.getBallDirectionY()) == blockY) { 
  			return new BouncePoint(blockX - 5, blockY - 5, BouncePoint.BounceDirection.FLIP_X);
  		}
  	//option G
 		if((this.getCurrentBallY() + this.getBallDirectionY()) == (blockY + 20)) {
  			return new BouncePoint(blockX - 5, blockY + 25, BouncePoint.BounceDirection.FLIP_X);
  		}
  	}
  	
  	//if the ball comes from the right
  	
  	if(blockX + 20 < this.getCurrentBallX()) {
  	//option C
  		if((this.getCurrentBallY() + this.getBallDirectionY()) != blockY || (this.getCurrentBallY() + this.getBallDirectionY()) != (blockY + 20)) {
  			return new BouncePoint(blockX + 25, this.getCurrentBallY() + this.getBallDirectionY(), BouncePoint.BounceDirection.FLIP_X);
  		}
  	//option F
  		if((this.getCurrentBallY() + this.getBallDirectionY()) == blockY) { //might need to add extra if statement to better detect the bounce on the edge, it should be good so far without it
  			return new BouncePoint(blockX + 25, blockY - 5, BouncePoint.BounceDirection.FLIP_Y);
  		}
  	//option H
  		if((this.getCurrentBallY() + this.getBallDirectionY()) == (blockY + 20)) {
  			return new BouncePoint(blockX + 25, blockY + 25, BouncePoint.BounceDirection.FLIP_Y);
  		}
  	}
  	return null;
  }


  /**
   * Haluk ball hits wall method
   */
  // line 355 "../../../../../Block223States.ump"
   private BouncePoint calculateBouncePointWall(){
    java.awt.geom.Rectangle2D rectA = new java.awt.geom.Rectangle2D.Double(0, 5, 5, 385);
  java.awt.geom.Rectangle2D rectB = new java.awt.geom.Rectangle2D.Double(5, 0, 380, 5);
  java.awt.geom.Rectangle2D rectC = new java.awt.geom.Rectangle2D.Double(385, 5, 380, 5);
  java.awt.geom.Rectangle2D rectE = new java.awt.geom.Rectangle2D.Double(0, 0, 5, 5);
  java.awt.geom.Rectangle2D rectF = new java.awt.geom.Rectangle2D.Double(385, 0, 5, 5);

  java.awt.geom.Ellipse2D ball = new java.awt.geom.Ellipse2D.Double(this.getCurrentBallX() + this.getBallDirectionX(), this.getCurrentBallY() + this.getBallDirectionY(), 10, 10);
  if(!rectA.getBounds2D().intersects(ball.getBounds2D())) return null;
  if(!rectB.getBounds2D().intersects(ball.getBounds2D())) return null;
  if(!rectC.getBounds2D().intersects(ball.getBounds2D())) return null;
  if(!rectE.getBounds2D().intersects(ball.getBounds2D())) return null;
  if(!rectF.getBounds2D().intersects(ball.getBounds2D())) return null;
  
  
  // Option A 
  if((this.getCurrentBallY() + this.getBallDirectionY()) > 5 && (this.getCurrentBallX() + this.getBallDirectionX()) <= 5  ) {
  			return new BouncePoint(5, ((2*currentBallY + ballDirectionY)*(5)/(2*currentBallX + ballDirectionX)), BouncePoint.BounceDirection.FLIP_X);
  		}

  //Option B
  if((this.getCurrentBallY() + this.getBallDirectionY()) <= 5 && (this.getCurrentBallX() + this.getBallDirectionX()) > 5 && (this.getCurrentBallX() + this.getBallDirectionX()) < 385   ) {
   return new BouncePoint(((2*currentBallX + ballDirectionX)*(5)/(2*currentBallY + ballDirectionY)), 5, BouncePoint.BounceDirection.FLIP_Y);
   }

   //A&B corner
  if((this.getCurrentBallX() + this.getBallDirectionX()) <= 5 && (this.getCurrentBallY() + this.getBallDirectionY()) <=  5) {
  	return new BouncePoint(5, 5, BouncePoint.BounceDirection.FLIP_BOTH);
  		}
   	
   
   //B&C corner
   if((this.getCurrentBallX() + this.getBallDirectionX()) <= 385  && (this.getCurrentBallY() + this.getBallDirectionY()) <=  5) {
    return new BouncePoint(5, 5, BouncePoint.BounceDirection.FLIP_BOTH);
   }
   
  
  //Option C
  
  if((this.getCurrentBallY() + this.getBallDirectionY()) > 5 && (this.getCurrentBallX() + this.getBallDirectionX()) >= 385) {
  	return new BouncePoint(385, ((2*currentBallY + ballDirectionY)*(5)/(2*currentBallX + ballDirectionX)), BouncePoint.BounceDirection.FLIP_X);
  		}
  
  	return null;
  }

  // line 402 "../../../../../Block223States.ump"
   private BouncePoint calculateBouncePointPaddle(){
    java.awt.geom.Rectangle2D rect = new java.awt.geom.Rectangle2D.Double(this.getCurrentPaddleX(), this.getCurrentPaddleY(), currentPaddleLength, 5);
  	java.awt.geom.Ellipse2D ball = new java.awt.geom.Ellipse2D.Double(this.getCurrentBallX() + this.getBallDirectionX(), this.getCurrentBallY() + this.getBallDirectionY(), 10, 10);
  	if(!rect.getBounds2D().intersects(ball.getBounds2D())) return null;
  	
  	//ball comes from the top
  	if(this.getCurrentBallY() < currentPaddleY && currentPaddleY - (currentBallY + ballDirectionY) <= 5) {
  	//option A
  		if((currentBallX + ballDirectionX) != currentPaddleX || (currentBallX + ballDirectionX) != (currentPaddleX + currentPaddleLength)) {
  			return new BouncePoint(((2*currentBallX + ballDirectionX)*(currentPaddleY - 5)/(2*currentBallY + ballDirectionY)), currentPaddleY - 5, BouncePoint.BounceDirection.FLIP_Y);
  		}
  	//option E approaching from right
  		if((currentBallX + ballDirectionX) == currentPaddleX && (currentBallX + ballDirectionX) < currentBallX) {
  			return new BouncePoint(((2*currentBallX + ballDirectionX)*(currentPaddleY - 5)/(2*currentBallY + ballDirectionY)), currentPaddleY - 5, BouncePoint.BounceDirection.FLIP_Y);
  		}
  	//option E approaching from left
  		if((currentBallX + ballDirectionX) == currentPaddleX && (currentBallX + ballDirectionX) > currentBallX) {
  			return new BouncePoint(((2*currentBallX + ballDirectionX)*(currentPaddleY - 5)/(2*currentBallY + ballDirectionY)), currentPaddleY - 5, BouncePoint.BounceDirection.FLIP_X);
  		}
  	//option F from the right
  		if((currentBallX + ballDirectionX) == (currentPaddleX + currentPaddleLength) && (currentBallX + ballDirectionX) > currentBallX) {
  			return new BouncePoint(((2*currentBallX + ballDirectionX)*(currentPaddleY - 5)/(2*currentBallY + ballDirectionY)), currentPaddleY - 5, BouncePoint.BounceDirection.FLIP_Y);
  		}
  	//option F from the right
  		if((currentBallX + ballDirectionX) == (currentPaddleX + currentPaddleLength) && (currentBallX + ballDirectionX) > currentBallX) {
  			return new BouncePoint(((2*currentBallX + ballDirectionX)*(currentPaddleY - 5)/(2*currentBallY + ballDirectionY)), currentPaddleY - 5, BouncePoint.BounceDirection.FLIP_Y);
  		}
  	}
  	//ball comes from the left
  	if(currentBallX < currentPaddleX) {
  	//option B
  		if((currentBallY + ballDirectionY) != currentPaddleY || (currentBallY + ballDirectionY) != (currentPaddleY + 5)) {
  			return new BouncePoint((currentPaddleX - 5), ((2*currentBallY + ballDirectionY)*(currentPaddleX - 5)/(2*currentBallX + ballDirectionX)), BouncePoint.BounceDirection.FLIP_X);
  		}
  	//option E
  		if((currentBallY + ballDirectionY) == currentPaddleY) {
  			return new BouncePoint((currentPaddleX - 5), ((2*currentBallY + ballDirectionY)*(currentPaddleX - 5)/(2*currentBallX + ballDirectionX)), BouncePoint.BounceDirection.FLIP_X);
  		}
  	}
  	//ball comes from the right
  	if(currentBallX > currentPaddleX) {
  	//option C
  		if((currentBallY + ballDirectionY) != currentPaddleY || (currentBallY + ballDirectionY) != (currentPaddleY + 5)) {
  			return new BouncePoint((currentPaddleX + currentPaddleLength + 5), ((2*currentBallY + ballDirectionY)*(currentPaddleX + currentPaddleLength + 5)/(2*currentBallX + ballDirectionX)), BouncePoint.BounceDirection.FLIP_X);
  		}
  	//option F
  		if((currentBallY + ballDirectionY) == currentPaddleY) {
  			return new BouncePoint((currentPaddleX + currentPaddleLength + 5), ((2*currentBallY + ballDirectionY)*(currentPaddleX + currentPaddleLength + 5)/(2*currentBallX + ballDirectionX)), BouncePoint.BounceDirection.FLIP_X);
  		}	
  	}
  	return null;
  }

  // line 455 "../../../../../Block223States.ump"
   private void bounceBall(){
    //FLIP_Y case
  	BouncePoint.BounceDirection bd = this.bounce.getDirection();
  	if(bd.equals(BouncePoint.BounceDirection.FLIP_Y)) {
  		double in = bounce.getY() - currentBallY;
  		double rem = ballDirectionY - in;
  		if(rem == 0) {
  			currentBallX = bounce.getX();
  			currentBallY = bounce.getY();
  		}
  		double oldDirectionX = ballDirectionX;
  		double oldDirectionY = ballDirectionY;
  		ballDirectionX = oldDirectionX + (0.1 * oldDirectionY);
  		ballDirectionY = (oldDirectionY * (-1));
  		currentBallX = bounce.getX() + rem/oldDirectionX * ballDirectionX;
  		currentBallY = bounce.getY() + rem/oldDirectionY * ballDirectionY;
  	}
  	//FLIP_X case
  	if(bd.equals(BouncePoint.BounceDirection.FLIP_X)) {
  		double in = bounce.getX() - currentBallX;
  		double rem = ballDirectionX - in;
  		if(rem == 0) {
  			currentBallX = bounce.getX();
  			currentBallY = bounce.getY();
  		}
  		double oldDirectionX = ballDirectionX;
  		double oldDirectionY = ballDirectionY;
  		ballDirectionX = (oldDirectionX * (-1));
  		ballDirectionY = oldDirectionY + (0.1 * oldDirectionX);
  		currentBallX = bounce.getX() + rem/oldDirectionX * ballDirectionX;
  		currentBallY = bounce.getY() + rem/oldDirectionY * ballDirectionY;
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
  		double oldDirectionX = ballDirectionX;
  		double oldDirectionY = ballDirectionY;
  		ballDirectionX = (oldDirectionX * (-1));
  		ballDirectionY = (oldDirectionY * (-1));
  		currentBallX = bounce.getX() + remX/oldDirectionX * ballDirectionX;
  		currentBallY = bounce.getY() + remY/oldDirectionY * ballDirectionY;
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
  
  // line 103 "../../../../../Block223Persistence.ump"
  private static final long serialVersionUID = 8597675110221231714L ;

  
}