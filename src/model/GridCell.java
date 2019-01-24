/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package model;

// line 69 "../Block223.ump"
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
  private GridSystem gridSystem;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public GridCell(int aRow, int aCol, Block aBlock, GridSystem aGridSystem)
  {
    row = aRow;
    col = aCol;
    if (aBlock == null || aBlock.getGridCell() != null)
    {
      throw new RuntimeException("Unable to create GridCell due to aBlock");
    }
    block = aBlock;
    boolean didAddGridSystem = setGridSystem(aGridSystem);
    if (!didAddGridSystem)
    {
      throw new RuntimeException("Unable to create gridCell due to gridSystem");
    }
  }

  public GridCell(int aRow, int aCol, int aSizeForBlock, int aPointsForBlock, Color aColorForBlock, Level aLevelForBlock, GridSystem aGridSystem)
  {
    row = aRow;
    col = aCol;
    block = new Block(aSizeForBlock, aPointsForBlock, aColorForBlock, this, aLevelForBlock);
    boolean didAddGridSystem = setGridSystem(aGridSystem);
    if (!didAddGridSystem)
    {
      throw new RuntimeException("Unable to create gridCell due to gridSystem");
    }
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
  /* Code from template association_GetOne */
  public GridSystem getGridSystem()
  {
    return gridSystem;
  }
  /* Code from template association_SetOneToMany */
  public boolean setGridSystem(GridSystem aGridSystem)
  {
    boolean wasSet = false;
    if (aGridSystem == null)
    {
      return wasSet;
    }

    GridSystem existingGridSystem = gridSystem;
    gridSystem = aGridSystem;
    if (existingGridSystem != null && !existingGridSystem.equals(aGridSystem))
    {
      existingGridSystem.removeGridCell(this);
    }
    gridSystem.addGridCell(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Block existingBlock = block;
    block = null;
    if (existingBlock != null)
    {
      existingBlock.delete();
    }
    GridSystem placeholderGridSystem = gridSystem;
    this.gridSystem = null;
    if(placeholderGridSystem != null)
    {
      placeholderGridSystem.removeGridCell(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "row" + ":" + getRow()+ "," +
            "col" + ":" + getCol()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "block = "+(getBlock()!=null?Integer.toHexString(System.identityHashCode(getBlock())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "gridSystem = "+(getGridSystem()!=null?Integer.toHexString(System.identityHashCode(getGridSystem())):"null");
  }
}