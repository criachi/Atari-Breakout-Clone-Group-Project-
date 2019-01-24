/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package model;
import java.util.*;

// line 62 "../Block223.ump"
public class GridPosition
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //GridPosition Attributes
  private int row;
  private int col;

  //GridPosition Associations
  private Block block;
  private List<GridSystem> gridSystems;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public GridPosition(int aRow, int aCol, Block aBlock)
  {
    row = aRow;
    col = aCol;
    if (aBlock == null || aBlock.getGridPosition() != null)
    {
      throw new RuntimeException("Unable to create GridPosition due to aBlock");
    }
    block = aBlock;
    gridSystems = new ArrayList<GridSystem>();
  }

  public GridPosition(int aRow, int aCol, int aSizeForBlock, int aPointsForBlock, Color aColorForBlock, Level aLevelForBlock)
  {
    row = aRow;
    col = aCol;
    block = new Block(aSizeForBlock, aPointsForBlock, aColorForBlock, this, aLevelForBlock);
    gridSystems = new ArrayList<GridSystem>();
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
  /* Code from template association_GetMany */
  public GridSystem getGridSystem(int index)
  {
    GridSystem aGridSystem = gridSystems.get(index);
    return aGridSystem;
  }

  public List<GridSystem> getGridSystems()
  {
    List<GridSystem> newGridSystems = Collections.unmodifiableList(gridSystems);
    return newGridSystems;
  }

  public int numberOfGridSystems()
  {
    int number = gridSystems.size();
    return number;
  }

  public boolean hasGridSystems()
  {
    boolean has = gridSystems.size() > 0;
    return has;
  }

  public int indexOfGridSystem(GridSystem aGridSystem)
  {
    int index = gridSystems.indexOf(aGridSystem);
    return index;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfGridSystems()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public GridSystem addGridSystem()
  {
    return new GridSystem(this);
  }

  public boolean addGridSystem(GridSystem aGridSystem)
  {
    boolean wasAdded = false;
    if (gridSystems.contains(aGridSystem)) { return false; }
    GridPosition existingGridPosition = aGridSystem.getGridPosition();
    boolean isNewGridPosition = existingGridPosition != null && !this.equals(existingGridPosition);
    if (isNewGridPosition)
    {
      aGridSystem.setGridPosition(this);
    }
    else
    {
      gridSystems.add(aGridSystem);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeGridSystem(GridSystem aGridSystem)
  {
    boolean wasRemoved = false;
    //Unable to remove aGridSystem, as it must always have a gridPosition
    if (!this.equals(aGridSystem.getGridPosition()))
    {
      gridSystems.remove(aGridSystem);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addGridSystemAt(GridSystem aGridSystem, int index)
  {  
    boolean wasAdded = false;
    if(addGridSystem(aGridSystem))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfGridSystems()) { index = numberOfGridSystems() - 1; }
      gridSystems.remove(aGridSystem);
      gridSystems.add(index, aGridSystem);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveGridSystemAt(GridSystem aGridSystem, int index)
  {
    boolean wasAdded = false;
    if(gridSystems.contains(aGridSystem))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfGridSystems()) { index = numberOfGridSystems() - 1; }
      gridSystems.remove(aGridSystem);
      gridSystems.add(index, aGridSystem);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addGridSystemAt(aGridSystem, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    Block existingBlock = block;
    block = null;
    if (existingBlock != null)
    {
      existingBlock.delete();
    }
    for(int i=gridSystems.size(); i > 0; i--)
    {
      GridSystem aGridSystem = gridSystems.get(i - 1);
      aGridSystem.delete();
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