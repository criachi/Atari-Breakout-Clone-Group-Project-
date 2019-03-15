/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse223.block.model;

// line 20 "../../../../../Block223PlayGame.ump"
public class SpecificBall
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //SpecificBall Attributes
  private int posX;
  private int posY;
  private int directionX;
  private int directionY;

  //SpecificBall Associations
  private Ball ball;
  private PlayedGame playedGame;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public SpecificBall(Ball aBall, PlayedGame aPlayedGame)
  {
    resetPosX();
    resetPosY();
    resetDirectionX();
    resetDirectionY();
    if (!setBall(aBall))
    {
      throw new RuntimeException("Unable to create SpecificBall due to aBall");
    }
    if (aPlayedGame == null || aPlayedGame.getSpecificBall() != null)
    {
      throw new RuntimeException("Unable to create SpecificBall due to aPlayedGame");
    }
    playedGame = aPlayedGame;
  }

  public SpecificBall(Ball aBall, Player aPlayerForPlayedGame, SpecificPaddle aSpecificPaddleForPlayedGame, Game aGameForPlayedGame)
  {
    resetPosX();
    resetPosY();
    resetDirectionX();
    resetDirectionY();
    boolean didAddBall = setBall(aBall);
    if (!didAddBall)
    {
      throw new RuntimeException("Unable to create specificBall due to ball");
    }
    playedGame = new PlayedGame(aPlayerForPlayedGame, this, aSpecificPaddleForPlayedGame, aGameForPlayedGame);
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template attribute_SetDefaulted */
  public boolean setPosX(int aPosX)
  {
    boolean wasSet = false;
    posX = aPosX;
    wasSet = true;
    return wasSet;
  }

  public boolean resetPosX()
  {
    boolean wasReset = false;
    posX = getDefaultPosX();
    wasReset = true;
    return wasReset;
  }
  /* Code from template attribute_SetDefaulted */
  public boolean setPosY(int aPosY)
  {
    boolean wasSet = false;
    posY = aPosY;
    wasSet = true;
    return wasSet;
  }

  public boolean resetPosY()
  {
    boolean wasReset = false;
    posY = getDefaultPosY();
    wasReset = true;
    return wasReset;
  }
  /* Code from template attribute_SetDefaulted */
  public boolean setDirectionX(int aDirectionX)
  {
    boolean wasSet = false;
    directionX = aDirectionX;
    wasSet = true;
    return wasSet;
  }

  public boolean resetDirectionX()
  {
    boolean wasReset = false;
    directionX = getDefaultDirectionX();
    wasReset = true;
    return wasReset;
  }
  /* Code from template attribute_SetDefaulted */
  public boolean setDirectionY(int aDirectionY)
  {
    boolean wasSet = false;
    directionY = aDirectionY;
    wasSet = true;
    return wasSet;
  }

  public boolean resetDirectionY()
  {
    boolean wasReset = false;
    directionY = getDefaultDirectionY();
    wasReset = true;
    return wasReset;
  }

  public int getPosX()
  {
    return posX;
  }
  /* Code from template attribute_GetDefaulted */
  public int getDefaultPosX()
  {
    return 195;
  }

  public int getPosY()
  {
    return posY;
  }
  /* Code from template attribute_GetDefaulted */
  public int getDefaultPosY()
  {
    return 195;
  }

  public int getDirectionX()
  {
    return directionX;
  }
  /* Code from template attribute_GetDefaulted */
  public int getDefaultDirectionX()
  {
    return ball.getMinBallSpeedX();
  }

  public int getDirectionY()
  {
    return directionY;
  }
  /* Code from template attribute_GetDefaulted */
  public int getDefaultDirectionY()
  {
    return ball.getMinBallSpeedY();
  }
  /* Code from template association_GetOne */
  public Ball getBall()
  {
    return ball;
  }
  /* Code from template association_GetOne */
  public PlayedGame getPlayedGame()
  {
    return playedGame;
  }
  /* Code from template association_SetUnidirectionalOne */
  public boolean setBall(Ball aNewBall)
  {
    boolean wasSet = false;
    if (aNewBall != null)
    {
      ball = aNewBall;
      wasSet = true;
    }
    return wasSet;
  }

  public void delete()
  {
    ball = null;
    PlayedGame existingPlayedGame = playedGame;
    playedGame = null;
    if (existingPlayedGame != null)
    {
      existingPlayedGame.delete();
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "posX" + ":" + getPosX()+ "," +
            "posY" + ":" + getPosY()+ "," +
            "directionX" + ":" + getDirectionX()+ "," +
            "directionY" + ":" + getDirectionY()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "ball = "+(getBall()!=null?Integer.toHexString(System.identityHashCode(getBall())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "playedGame = "+(getPlayedGame()!=null?Integer.toHexString(System.identityHashCode(getPlayedGame())):"null");
  }
}