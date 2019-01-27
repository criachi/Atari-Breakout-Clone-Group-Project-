/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse223.block.model;

// line 75 "../../../../../Block223.ump"
public class GridCell
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //GridCell Attributes
  private int row;
  private int col;

  //GridCell Associations
  private Block block;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public GridCell(int aRow, int aCol, Block aBlock)
  {
    row = aRow;
    col = aCol;
    if (aBlock == null || aBlock.getGridCell() != null)
    {
      throw new RuntimeException("Unable to create GridCell due to aBlock");
    }
    block = aBlock;
  }

  public GridCell(int aRow, int aCol, BlockType aBlockTypeForBlock, Level aLevelForBlock)
  {
    row = aRow;
    col = aCol;
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
  public Block getBlock()
  {
    return block;
  }

  public void delete()
  {
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
            "  " + "block = "+(getBlock()!=null?Integer.toHexString(System.identityHashCode(getBlock())):"null");
  }
}