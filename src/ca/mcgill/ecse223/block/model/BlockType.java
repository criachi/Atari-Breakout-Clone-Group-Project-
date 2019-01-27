/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse223.block.model;
import java.util.*;

// line 62 "../../../../../Block223.ump"
public class BlockType
{

  //------------------------
  // ENUMERATIONS
  //------------------------

  public enum Color { Blue, Red, Yellow, Green, Orange, Black, White }

  //------------------------
  // STATIC VARIABLES
  //------------------------

  public static final int SIDE = 20;

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //BlockType Attributes
  private int size;
  private int points;
  private Color color;

  //BlockType Associations
  private List<Block> blocks;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public BlockType(int aSize, int aPoints, Color aColor)
  {
    size = aSize;
    points = aPoints;
    color = aColor;
    blocks = new ArrayList<Block>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setSize(int aSize)
  {
    boolean wasSet = false;
    size = aSize;
    wasSet = true;
    return wasSet;
  }

  public boolean setPoints(int aPoints)
  {
    boolean wasSet = false;
    points = aPoints;
    wasSet = true;
    return wasSet;
  }

  public boolean setColor(Color aColor)
  {
    boolean wasSet = false;
    color = aColor;
    wasSet = true;
    return wasSet;
  }

  public int getSize()
  {
    return size;
  }

  public int getPoints()
  {
    return points;
  }

  public Color getColor()
  {
    return color;
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
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfBlocks()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Block addBlock(GridCell aGridCell, Level aLevel)
  {
    return new Block(this, aGridCell, aLevel);
  }

  public boolean addBlock(Block aBlock)
  {
    boolean wasAdded = false;
    if (blocks.contains(aBlock)) { return false; }
    BlockType existingBlockType = aBlock.getBlockType();
    boolean isNewBlockType = existingBlockType != null && !this.equals(existingBlockType);
    if (isNewBlockType)
    {
      aBlock.setBlockType(this);
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
    //Unable to remove aBlock, as it must always have a blockType
    if (!this.equals(aBlock.getBlockType()))
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

  public void delete()
  {
    for(int i=blocks.size(); i > 0; i--)
    {
      Block aBlock = blocks.get(i - 1);
      aBlock.delete();
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "size" + ":" + getSize()+ "," +
            "points" + ":" + getPoints()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "color" + "=" + (getColor() != null ? !getColor().equals(this)  ? getColor().toString().replaceAll("  ","    ") : "this" : "null");
  }
}