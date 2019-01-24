/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package model;
import java.util.*;

// line 63 "../Block223.ump"
public class GridSystem
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //GridSystem Attributes
  private int gridLength;
  private int gridWidth;

  //GridSystem Associations
  private List<GridCell> gridCells;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public GridSystem(int aGridLength, int aGridWidth)
  {
    gridLength = aGridLength;
    gridWidth = aGridWidth;
    gridCells = new ArrayList<GridCell>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setGridLength(int aGridLength)
  {
    boolean wasSet = false;
    gridLength = aGridLength;
    wasSet = true;
    return wasSet;
  }

  public boolean setGridWidth(int aGridWidth)
  {
    boolean wasSet = false;
    gridWidth = aGridWidth;
    wasSet = true;
    return wasSet;
  }

  public int getGridLength()
  {
    return gridLength;
  }

  public int getGridWidth()
  {
    return gridWidth;
  }
  /* Code from template association_GetMany */
  public GridCell getGridCell(int index)
  {
    GridCell aGridCell = gridCells.get(index);
    return aGridCell;
  }

  public List<GridCell> getGridCells()
  {
    List<GridCell> newGridCells = Collections.unmodifiableList(gridCells);
    return newGridCells;
  }

  public int numberOfGridCells()
  {
    int number = gridCells.size();
    return number;
  }

  public boolean hasGridCells()
  {
    boolean has = gridCells.size() > 0;
    return has;
  }

  public int indexOfGridCell(GridCell aGridCell)
  {
    int index = gridCells.indexOf(aGridCell);
    return index;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfGridCells()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public GridCell addGridCell(int aRow, int aCol, Block aBlock)
  {
    return new GridCell(aRow, aCol, aBlock, this);
  }

  public boolean addGridCell(GridCell aGridCell)
  {
    boolean wasAdded = false;
    if (gridCells.contains(aGridCell)) { return false; }
    GridSystem existingGridSystem = aGridCell.getGridSystem();
    boolean isNewGridSystem = existingGridSystem != null && !this.equals(existingGridSystem);
    if (isNewGridSystem)
    {
      aGridCell.setGridSystem(this);
    }
    else
    {
      gridCells.add(aGridCell);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeGridCell(GridCell aGridCell)
  {
    boolean wasRemoved = false;
    //Unable to remove aGridCell, as it must always have a gridSystem
    if (!this.equals(aGridCell.getGridSystem()))
    {
      gridCells.remove(aGridCell);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addGridCellAt(GridCell aGridCell, int index)
  {  
    boolean wasAdded = false;
    if(addGridCell(aGridCell))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfGridCells()) { index = numberOfGridCells() - 1; }
      gridCells.remove(aGridCell);
      gridCells.add(index, aGridCell);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveGridCellAt(GridCell aGridCell, int index)
  {
    boolean wasAdded = false;
    if(gridCells.contains(aGridCell))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfGridCells()) { index = numberOfGridCells() - 1; }
      gridCells.remove(aGridCell);
      gridCells.add(index, aGridCell);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addGridCellAt(aGridCell, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    for(int i=gridCells.size(); i > 0; i--)
    {
      GridCell aGridCell = gridCells.get(i - 1);
      aGridCell.delete();
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "gridLength" + ":" + getGridLength()+ "," +
            "gridWidth" + ":" + getGridWidth()+ "]";
  }
}