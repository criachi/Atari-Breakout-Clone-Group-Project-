/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse223.block.model;

// line 38 "../../../../../Block223PlayGame.ump"
public class SpecificPaddle
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //SpecificPaddle Attributes
  private int posX;

  //SpecificPaddle Associations
  private Paddle paddle;
  private PlayedGame playedGame;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public SpecificPaddle(Paddle aPaddle, PlayedGame aPlayedGame)
  {
    posX = 0;
    boolean didAddPaddle = setPaddle(aPaddle);
    if (!didAddPaddle)
    {
      throw new RuntimeException("Unable to create specificPaddle due to paddle");
    }
    if (aPlayedGame == null || aPlayedGame.getSpecificPaddle() != null)
    {
      throw new RuntimeException("Unable to create SpecificPaddle due to aPlayedGame");
    }
    playedGame = aPlayedGame;
  }

  public SpecificPaddle(Paddle aPaddle, Player aPlayerForPlayedGame, SpecificBall aSpecificBallForPlayedGame, Game aGameForPlayedGame)
  {
    posX = 0;
    boolean didAddPaddle = setPaddle(aPaddle);
    if (!didAddPaddle)
    {
      throw new RuntimeException("Unable to create specificPaddle due to paddle");
    }
    playedGame = new PlayedGame(aPlayerForPlayedGame, aSpecificBallForPlayedGame, this, aGameForPlayedGame);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setPosX(int aPosX)
  {
    boolean wasSet = false;
    posX = aPosX;
    wasSet = true;
    return wasSet;
  }

  public int getPosX()
  {
    return posX;
  }
  /* Code from template association_GetOne */
  public Paddle getPaddle()
  {
    return paddle;
  }
  /* Code from template association_GetOne */
  public PlayedGame getPlayedGame()
  {
    return playedGame;
  }
  /* Code from template association_SetOneToMany */
  public boolean setPaddle(Paddle aPaddle)
  {
    boolean wasSet = false;
    if (aPaddle == null)
    {
      return wasSet;
    }

    Paddle existingPaddle = paddle;
    paddle = aPaddle;
    if (existingPaddle != null && !existingPaddle.equals(aPaddle))
    {
      existingPaddle.removeSpecificPaddle(this);
    }
    paddle.addSpecificPaddle(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Paddle placeholderPaddle = paddle;
    this.paddle = null;
    if(placeholderPaddle != null)
    {
      placeholderPaddle.removeSpecificPaddle(this);
    }
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
            "posX" + ":" + getPosX()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "paddle = "+(getPaddle()!=null?Integer.toHexString(System.identityHashCode(getPaddle())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "playedGame = "+(getPlayedGame()!=null?Integer.toHexString(System.identityHashCode(getPlayedGame())):"null");
  }
}