/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse223.block.model;

// line 33 "../../../../../Block223PlayGame.ump"
public class SpecificBlockAssignment
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //SpecificBlockAssignment Attributes
  private int positionX;
  private int positionY;

  //SpecificBlockAssignment Associations
  private Block block;
  private PlayedGame playedGame;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public SpecificBlockAssignment(int aPositionX, int aPositionY, Block aBlock, PlayedGame aPlayedGame)
  {
    positionX = aPositionX;
    positionY = aPositionY;
    boolean didAddBlock = setBlock(aBlock);
    if (!didAddBlock)
    {
      throw new RuntimeException("Unable to create specificBlockAssignment due to block");
    }
    boolean didAddPlayedGame = setPlayedGame(aPlayedGame);
    if (!didAddPlayedGame)
    {
      throw new RuntimeException("Unable to create specificBlockAssignment due to playedGame");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public int getPositionX()
  {
    return positionX;
  }

  public int getPositionY()
  {
    return positionY;
  }
  /* Code from template association_GetOne */
  public Block getBlock()
  {
    return block;
  }
  /* Code from template association_GetOne */
  public PlayedGame getPlayedGame()
  {
    return playedGame;
  }
  /* Code from template association_SetOneToMany */
  public boolean setBlock(Block aBlock)
  {
    boolean wasSet = false;
    if (aBlock == null)
    {
      return wasSet;
    }

    Block existingBlock = block;
    block = aBlock;
    if (existingBlock != null && !existingBlock.equals(aBlock))
    {
      existingBlock.removeSpecificBlockAssignment(this);
    }
    block.addSpecificBlockAssignment(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOneToMany */
  public boolean setPlayedGame(PlayedGame aPlayedGame)
  {
    boolean wasSet = false;
    if (aPlayedGame == null)
    {
      return wasSet;
    }

    PlayedGame existingPlayedGame = playedGame;
    playedGame = aPlayedGame;
    if (existingPlayedGame != null && !existingPlayedGame.equals(aPlayedGame))
    {
      existingPlayedGame.removeSpecificBlockAssignment(this);
    }
    playedGame.addSpecificBlockAssignment(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Block placeholderBlock = block;
    this.block = null;
    if(placeholderBlock != null)
    {
      placeholderBlock.removeSpecificBlockAssignment(this);
    }
    PlayedGame placeholderPlayedGame = playedGame;
    this.playedGame = null;
    if(placeholderPlayedGame != null)
    {
      placeholderPlayedGame.removeSpecificBlockAssignment(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "positionX" + ":" + getPositionX()+ "," +
            "positionY" + ":" + getPositionY()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "block = "+(getBlock()!=null?Integer.toHexString(System.identityHashCode(getBlock())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "playedGame = "+(getPlayedGame()!=null?Integer.toHexString(System.identityHashCode(getPlayedGame())):"null");
  }
}