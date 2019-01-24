/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package model;

// line 54 "../Block223.ump"
public class Block
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

  //Block Attributes
  private int size;
  private int points;
  private Color color;

  //Block Associations
  private GridCell gridCell;
  private Level level;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Block(int aSize, int aPoints, Color aColor, GridCell aGridCell, Level aLevel)
  {
    size = aSize;
    points = aPoints;
    color = aColor;
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

  public Block(int aSize, int aPoints, Color aColor, int aRowForGridCell, int aColForGridCell, GridSystem aGridSystemForGridCell, Level aLevel)
  {
    size = aSize;
    points = aPoints;
    color = aColor;
    gridCell = new GridCell(aRowForGridCell, aColForGridCell, this, aGridSystemForGridCell);
    boolean didAddLevel = setLevel(aLevel);
    if (!didAddLevel)
    {
      throw new RuntimeException("Unable to create block due to level");
    }
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


  public String toString()
  {
    return super.toString() + "["+
            "size" + ":" + getSize()+ "," +
            "points" + ":" + getPoints()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "color" + "=" + (getColor() != null ? !getColor().equals(this)  ? getColor().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "gridCell = "+(getGridCell()!=null?Integer.toHexString(System.identityHashCode(getGridCell())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "level = "+(getLevel()!=null?Integer.toHexString(System.identityHashCode(getLevel())):"null");
  }
}