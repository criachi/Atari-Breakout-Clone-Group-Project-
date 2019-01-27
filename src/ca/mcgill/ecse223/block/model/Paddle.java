/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse223.block.model;

// line 48 "../../../../../Block223.ump"
public class Paddle
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  public static final int WIDTH = 5;

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Paddle Attributes
  private double length;
  private int minLength;
  private int maxLength;
  private double x;
  private double y;

  //Paddle Associations
  private Level level;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Paddle(double aLength, int aMinLength, int aMaxLength, Level aLevel)
  {
    length = aLength;
    minLength = aMinLength;
    maxLength = aMaxLength;
    x = 0;
    y = 0;
    if (aLevel == null || aLevel.getPaddle() != null)
    {
      throw new RuntimeException("Unable to create Paddle due to aLevel");
    }
    level = aLevel;
  }

  public Paddle(double aLength, int aMinLength, int aMaxLength, int aLevelNumberForLevel, Ball aBallForLevel, BlockGame aBlockGameForLevel)
  {
    length = aLength;
    minLength = aMinLength;
    maxLength = aMaxLength;
    x = 0;
    y = 0;
    level = new Level(aLevelNumberForLevel, aBallForLevel, this, aBlockGameForLevel);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setLength(double aLength)
  {
    boolean wasSet = false;
    length = aLength;
    wasSet = true;
    return wasSet;
  }

  public boolean setMinLength(int aMinLength)
  {
    boolean wasSet = false;
    minLength = aMinLength;
    wasSet = true;
    return wasSet;
  }

  public boolean setMaxLength(int aMaxLength)
  {
    boolean wasSet = false;
    maxLength = aMaxLength;
    wasSet = true;
    return wasSet;
  }

  public boolean setX(double aX)
  {
    boolean wasSet = false;
    x = aX;
    wasSet = true;
    return wasSet;
  }

  public boolean setY(double aY)
  {
    boolean wasSet = false;
    y = aY;
    wasSet = true;
    return wasSet;
  }

  public double getLength()
  {
    return length;
  }

  public int getMinLength()
  {
    return minLength;
  }

  public int getMaxLength()
  {
    return maxLength;
  }

  public double getX()
  {
    return x;
  }

  public double getY()
  {
    return y;
  }
  /* Code from template association_GetOne */
  public Level getLevel()
  {
    return level;
  }

  public void delete()
  {
    Level existingLevel = level;
    level = null;
    if (existingLevel != null)
    {
      existingLevel.delete();
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "length" + ":" + getLength()+ "," +
            "minLength" + ":" + getMinLength()+ "," +
            "maxLength" + ":" + getMaxLength()+ "," +
            "x" + ":" + getX()+ "," +
            "y" + ":" + getY()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "level = "+(getLevel()!=null?Integer.toHexString(System.identityHashCode(getLevel())):"null");
  }
}