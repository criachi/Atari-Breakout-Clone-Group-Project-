/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse223.block.model;
import java.util.*;

// line 65 "../../../../../Block223.ump"
public class PlayArea
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //PlayArea Attributes
  private int width;
  private int length;

  //PlayArea Associations
  private BlockGame blockGame;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public PlayArea(int aWidth, int aLength, BlockGame aBlockGame)
  {
    width = aWidth;
    length = aLength;
    if (aBlockGame == null || aBlockGame.getPlayArea() != null)
    {
      throw new RuntimeException("Unable to create PlayArea due to aBlockGame");
    }
    blockGame = aBlockGame;
  }

  public PlayArea(int aWidth, int aLength, String aNameForBlockGame, int aNumLevelsForBlockGame, HallOfFame aHallOfFameForBlockGame, BlockApplication aBlockApplicationForBlockGame, Player aPlayerForBlockGame, Admin aAdminForBlockGame)
  {
    width = aWidth;
    length = aLength;
    blockGame = new BlockGame(aNameForBlockGame, aNumLevelsForBlockGame, aHallOfFameForBlockGame, this, aBlockApplicationForBlockGame, aPlayerForBlockGame, aAdminForBlockGame);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setWidth(int aWidth)
  {
    boolean wasSet = false;
    width = aWidth;
    wasSet = true;
    return wasSet;
  }

  public boolean setLength(int aLength)
  {
    boolean wasSet = false;
    length = aLength;
    wasSet = true;
    return wasSet;
  }

  public int getWidth()
  {
    return width;
  }

  public int getLength()
  {
    return length;
  }
  /* Code from template association_GetOne */
  public BlockGame getBlockGame()
  {
    return blockGame;
  }

  public void delete()
  {
    BlockGame existingBlockGame = blockGame;
    blockGame = null;
    if (existingBlockGame != null)
    {
      existingBlockGame.delete();
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "width" + ":" + getWidth()+ "," +
            "length" + ":" + getLength()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "blockGame = "+(getBlockGame()!=null?Integer.toHexString(System.identityHashCode(getBlockGame())):"null");
  }
}