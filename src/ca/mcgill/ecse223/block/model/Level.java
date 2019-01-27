/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse223.block.model;
import java.util.*;

// line 31 "../../../../../Block223.ump"
public class Level
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Level Attributes
  private int levelNumber;

  //Level Associations
  private List<Block> blocks;
  private Ball ball;
  private Paddle paddle;
  private BlockGame blockGame;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Level(int aLevelNumber, Ball aBall, Paddle aPaddle, BlockGame aBlockGame)
  {
    levelNumber = aLevelNumber;
    blocks = new ArrayList<Block>();
    if (aBall == null || aBall.getLevel() != null)
    {
      throw new RuntimeException("Unable to create Level due to aBall");
    }
    ball = aBall;
    if (aPaddle == null || aPaddle.getLevel() != null)
    {
      throw new RuntimeException("Unable to create Level due to aPaddle");
    }
    paddle = aPaddle;
    boolean didAddBlockGame = setBlockGame(aBlockGame);
    if (!didAddBlockGame)
    {
      throw new RuntimeException("Unable to create level due to blockGame");
    }
  }

  public Level(int aLevelNumber, int aSpeedForBall, int aMinSpeedForBall, int aMaxSpeedForBall, double aSpeedIncreaseFactorForBall, double aLengthForPaddle, int aMinLengthForPaddle, int aMaxLengthForPaddle, BlockGame aBlockGame)
  {
    levelNumber = aLevelNumber;
    blocks = new ArrayList<Block>();
    ball = new Ball(aSpeedForBall, aMinSpeedForBall, aMaxSpeedForBall, aSpeedIncreaseFactorForBall, this);
    paddle = new Paddle(aLengthForPaddle, aMinLengthForPaddle, aMaxLengthForPaddle, this);
    boolean didAddBlockGame = setBlockGame(aBlockGame);
    if (!didAddBlockGame)
    {
      throw new RuntimeException("Unable to create level due to blockGame");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setLevelNumber(int aLevelNumber)
  {
    boolean wasSet = false;
    levelNumber = aLevelNumber;
    wasSet = true;
    return wasSet;
  }

  public int getLevelNumber()
  {
    return levelNumber;
  }
  /* Code from template association_GetMany */
  public Block getBlock(int index)
  {
    Block aBlock = blocks.get(index);
    return aBlock;
  }

  public List<Block> getBlocks()
  {
    List<Block> newBlocks = Collections.unmodifiableList(blocks);
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

  public int indexOfBlock(Block aBlock)
  {
    int index = blocks.indexOf(aBlock);
    return index;
  }
  /* Code from template association_GetOne */
  public Ball getBall()
  {
    return ball;
  }
  /* Code from template association_GetOne */
  public Paddle getPaddle()
  {
    return paddle;
  }
  /* Code from template association_GetOne */
  public BlockGame getBlockGame()
  {
    return blockGame;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfBlocks()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Block addBlock(BlockType aBlockType, GridCell aGridCell)
  {
    return new Block(aBlockType, aGridCell, this);
  }

  public boolean addBlock(Block aBlock)
  {
    boolean wasAdded = false;
    if (blocks.contains(aBlock)) { return false; }
    Level existingLevel = aBlock.getLevel();
    boolean isNewLevel = existingLevel != null && !this.equals(existingLevel);
    if (isNewLevel)
    {
      aBlock.setLevel(this);
    }
    else
    {
      blocks.add(aBlock);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeBlock(Block aBlock)
  {
    boolean wasRemoved = false;
    //Unable to remove aBlock, as it must always have a level
    if (!this.equals(aBlock.getLevel()))
    {
      blocks.remove(aBlock);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addBlockAt(Block aBlock, int index)
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

  public boolean addOrMoveBlockAt(Block aBlock, int index)
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
  /* Code from template association_SetOneToMany */
  public boolean setBlockGame(BlockGame aBlockGame)
  {
    boolean wasSet = false;
    if (aBlockGame == null)
    {
      return wasSet;
    }

    BlockGame existingBlockGame = blockGame;
    blockGame = aBlockGame;
    if (existingBlockGame != null && !existingBlockGame.equals(aBlockGame))
    {
      existingBlockGame.removeLevel(this);
    }
    blockGame.addLevel(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    while (blocks.size() > 0)
    {
      Block aBlock = blocks.get(blocks.size() - 1);
      aBlock.delete();
      blocks.remove(aBlock);
    }
    
    Ball existingBall = ball;
    ball = null;
    if (existingBall != null)
    {
      existingBall.delete();
    }
    Paddle existingPaddle = paddle;
    paddle = null;
    if (existingPaddle != null)
    {
      existingPaddle.delete();
    }
    BlockGame placeholderBlockGame = blockGame;
    this.blockGame = null;
    if(placeholderBlockGame != null)
    {
      placeholderBlockGame.removeLevel(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "levelNumber" + ":" + getLevelNumber()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "ball = "+(getBall()!=null?Integer.toHexString(System.identityHashCode(getBall())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "paddle = "+(getPaddle()!=null?Integer.toHexString(System.identityHashCode(getPaddle())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "blockGame = "+(getBlockGame()!=null?Integer.toHexString(System.identityHashCode(getBlockGame())):"null");
  }
}