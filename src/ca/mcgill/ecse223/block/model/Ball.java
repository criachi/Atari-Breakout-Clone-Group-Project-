/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse223.block.model;

// line 46 "../../../../../Block223.ump"
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
  private int minSpeed;
  private int maxSpeed;
  private double speedIncreaseFactor;
  private double x;
  private double y;

  //Ball Associations
  private Level level;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Ball(int aSpeed, int aMinSpeed, int aMaxSpeed, double aSpeedIncreaseFactor, Level aLevel)
  {
    speed = aSpeed;
    minSpeed = aMinSpeed;
    maxSpeed = aMaxSpeed;
    speedIncreaseFactor = aSpeedIncreaseFactor;
    x = 0;
    y = 0;
    if (aLevel == null || aLevel.getBall() != null)
    {
      throw new RuntimeException("Unable to create Ball due to aLevel");
    }
    level = aLevel;
  }

  public Ball(int aSpeed, int aMinSpeed, int aMaxSpeed, double aSpeedIncreaseFactor, int aLevelNumberForLevel, Paddle aPaddleForLevel, BlockGame aBlockGameForLevel)
  {
    speed = aSpeed;
    minSpeed = aMinSpeed;
    maxSpeed = aMaxSpeed;
    speedIncreaseFactor = aSpeedIncreaseFactor;
    x = 0;
    y = 0;
    level = new Level(aLevelNumberForLevel, this, aPaddleForLevel, aBlockGameForLevel);
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

  public boolean setMinSpeed(int aMinSpeed)
  {
    boolean wasSet = false;
    minSpeed = aMinSpeed;
    wasSet = true;
    return wasSet;
  }

  public boolean setMaxSpeed(int aMaxSpeed)
  {
    boolean wasSet = false;
    maxSpeed = aMaxSpeed;
    wasSet = true;
    return wasSet;
  }

  public boolean setSpeedIncreaseFactor(double aSpeedIncreaseFactor)
  {
    boolean wasSet = false;
    speedIncreaseFactor = aSpeedIncreaseFactor;
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

  public int getSpeed()
  {
    return speed;
  }

  public int getMinSpeed()
  {
    return minSpeed;
  }

  public int getMaxSpeed()
  {
    return maxSpeed;
  }

  public double getSpeedIncreaseFactor()
  {
    return speedIncreaseFactor;
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
            "speed" + ":" + getSpeed()+ "," +
            "minSpeed" + ":" + getMinSpeed()+ "," +
            "maxSpeed" + ":" + getMaxSpeed()+ "," +
            "speedIncreaseFactor" + ":" + getSpeedIncreaseFactor()+ "," +
            "x" + ":" + getX()+ "," +
            "y" + ":" + getY()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "level = "+(getLevel()!=null?Integer.toHexString(System.identityHashCode(getLevel())):"null");
  }
}