/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse223.block.model;

// line 83 "../../../../../Block223.ump"
public class GridCell
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //GridCell Attributes
  private int row;
  private int col;

  //GridCell Associations
  private BlockApplication blockApplication;
  private Block block;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public GridCell(int aRow, int aCol, BlockApplication aBlockApplication, Block aBlock)
  {
    row = aRow;
    col = aCol;
    boolean didAddBlockApplication = setBlockApplication(aBlockApplication);
    if (!didAddBlockApplication)
    {
      throw new RuntimeException("Unable to create gridCell due to blockApplication");
    }
    if (aBlock == null || aBlock.getGridCell() != null)
    {
      throw new RuntimeException("Unable to create GridCell due to aBlock");
    }
    block = aBlock;
  }

  public GridCell(int aRow, int aCol, BlockApplication aBlockApplication, BlockType aBlockTypeForBlock, Level aLevelForBlock)
  {
    row = aRow;
    col = aCol;
    boolean didAddBlockApplication = setBlockApplication(aBlockApplication);
    if (!didAddBlockApplication)
    {
      throw new RuntimeException("Unable to create gridCell due to blockApplication");
    }
    block = new Block(aBlockTypeForBlock, this, aLevelForBlock);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setRow(int aRow)
  {
    boolean wasSet = false;
    row = aRow;
    wasSet = true;
    return wasSet;
  }

  public boolean setCol(int aCol)
  {
    boolean wasSet = false;
    col = aCol;
    wasSet = true;
    return wasSet;
  }

  public int getRow()
  {
    return row;
  }

  public int getCol()
  {
    return col;
  }
  /* Code from template association_GetOne */
  public BlockApplication getBlockApplication()
  {
    return blockApplication;
  }
  /* Code from template association_GetOne */
  public Block getBlock()
  {
    return block;
  }
  /* Code from template association_SetOneToMany */
  public boolean setBlockApplication(BlockApplication aBlockApplication)
  {
    boolean wasSet = false;
    if (aBlockApplication == null)
    {
      return wasSet;
    }

    BlockApplication existingBlockApplication = blockApplication;
    blockApplication = aBlockApplication;
    if (existingBlockApplication != null && !existingBlockApplication.equals(aBlockApplication))
    {
      existingBlockApplication.removeGridCell(this);
    }
    blockApplication.addGridCell(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    BlockApplication placeholderBlockApplication = blockApplication;
    this.blockApplication = null;
    if(placeholderBlockApplication != null)
    {
      placeholderBlockApplication.removeGridCell(this);
    }
    Block existingBlock = block;
    block = null;
    if (existingBlock != null)
    {
      existingBlock.delete();
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "row" + ":" + getRow()+ "," +
            "col" + ":" + getCol()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "blockApplication = "+(getBlockApplication()!=null?Integer.toHexString(System.identityHashCode(getBlockApplication())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "block = "+(getBlock()!=null?Integer.toHexString(System.identityHashCode(getBlock())):"null");
  }
}