/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package model;

// line 44 "../Block223.ump"
public class Ball
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  public static final int DIAMETER = 10;

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Ball Attributes
  private int speed;

  //Ball Associations
  private Level level;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Ball(int aSpeed, Level aLevel)
  {
    speed = aSpeed;
    if (aLevel == null || aLevel.getBall() != null)
    {
      throw new RuntimeException("Unable to create Ball due to aLevel");
    }
    level = aLevel;
  }

  public Ball(int aSpeed, int aLevelNumberForLevel, Paddle aPaddleForLevel, Game aGameForLevel)
  {
    speed = aSpeed;
    level = new Level(aLevelNumberForLevel, this, aPaddleForLevel, aGameForLevel);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setSpeed(int aSpeed)
  {
    boolean wasSet = false;
    speed = aSpeed;
    wasSet = true;
    return wasSet;
  }

  public int getSpeed()
  {
    return speed;
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
            "speed" + ":" + getSpeed()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "level = "+(getLevel()!=null?Integer.toHexString(System.identityHashCode(getLevel())):"null");
  }
}