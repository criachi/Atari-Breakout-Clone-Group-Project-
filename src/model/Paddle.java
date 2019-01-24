/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package model;

// line 49 "../Block223.ump"
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
  private int length;

  //Paddle Associations
  private Level level;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Paddle(int aLength, Level aLevel)
  {
    length = aLength;
    if (aLevel == null || aLevel.getPaddle() != null)
    {
      throw new RuntimeException("Unable to create Paddle due to aLevel");
    }
    level = aLevel;
  }

  public Paddle(int aLength, int aLevelNumberForLevel, Ball aBallForLevel, Game aGameForLevel)
  {
    length = aLength;
    level = new Level(aLevelNumberForLevel, aBallForLevel, this, aGameForLevel);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setLength(int aLength)
  {
    boolean wasSet = false;
    length = aLength;
    wasSet = true;
    return wasSet;
  }

  public int getLength()
  {
    return length;
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
            "length" + ":" + getLength()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "level = "+(getLevel()!=null?Integer.toHexString(System.identityHashCode(getLevel())):"null");
  }
}