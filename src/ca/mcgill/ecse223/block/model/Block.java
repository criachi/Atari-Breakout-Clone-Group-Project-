/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse223.block.model;

// line 70 "../../../../../Block223.ump"
public class Block
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Block Associations
  private BlockType blockType;
  private GridCell gridCell;
  private Level level;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Block(BlockType aBlockType, GridCell aGridCell, Level aLevel)
  {
    boolean didAddBlockType = setBlockType(aBlockType);
    if (!didAddBlockType)
    {
      throw new RuntimeException("Unable to create block due to blockType");
    }
    if (aGridCell == null || aGridCell.getBlock() != null)
    {
      throw new RuntimeException("Unable to create Block due to aGridCell");
    }
    gridCell = aGridCell;
    boolean didAddLevel = setLevel(aLevel);
    if (!didAddLevel)
    {
      throw new RuntimeException("Unable to create block due to level");
    }
  }

  public Block(BlockType aBlockType, int aRowForGridCell, int aColForGridCell, Level aLevel)
  {
    boolean didAddBlockType = setBlockType(aBlockType);
    if (!didAddBlockType)
    {
      throw new RuntimeException("Unable to create block due to blockType");
    }
    gridCell = new GridCell(aRowForGridCell, aColForGridCell, this);
    boolean didAddLevel = setLevel(aLevel);
    if (!didAddLevel)
    {
      throw new RuntimeException("Unable to create block due to level");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetOne */
  public BlockType getBlockType()
  {
    return blockType;
  }
  /* Code from template association_GetOne */
  public GridCell getGridCell()
  {
    return gridCell;
  }
  /* Code from template association_GetOne */
  public Level getLevel()
  {
    return level;
  }
  /* Code from template association_SetOneToMany */
  public boolean setBlockType(BlockType aBlockType)
  {
    boolean wasSet = false;
    if (aBlockType == null)
    {
      return wasSet;
    }

    BlockType existingBlockType = blockType;
    blockType = aBlockType;
    if (existingBlockType != null && !existingBlockType.equals(aBlockType))
    {
      existingBlockType.removeBlock(this);
    }
    blockType.addBlock(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOneToMany */
  public boolean setLevel(Level aLevel)
  {
    boolean wasSet = false;
    if (aLevel == null)
    {
      return wasSet;
    }

    Level existingLevel = level;
    level = aLevel;
    if (existingLevel != null && !existingLevel.equals(aLevel))
    {
      existingLevel.removeBlock(this);
    }
    level.addBlock(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    BlockType placeholderBlockType = blockType;
    this.blockType = null;
    if(placeholderBlockType != null)
    {
      placeholderBlockType.removeBlock(this);
    }
    GridCell existingGridCell = gridCell;
    gridCell = null;
    if (existingGridCell != null)
    {
      existingGridCell.delete();
    }
    Level placeholderLevel = level;
    this.level = null;
    if(placeholderLevel != null)
    {
      placeholderLevel.removeBlock(this);
    }
  }

}