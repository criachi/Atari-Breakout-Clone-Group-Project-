/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse223.block.model;
import java.io.Serializable;

// line 91 "../../../../../Block223Persistence.ump"
// line 190 "../../../../../Block223 v3.ump"
public class BlockAssignment implements Serializable
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //BlockAssignment Attributes
  private int gridHorizontalPosition;
  private int gridVerticalPosition;

  //BlockAssignment Associations
  private Level level;
  private Block block;
  private Game game;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public BlockAssignment(int aGridHorizontalPosition, int aGridVerticalPosition, Level aLevel, Block aBlock, Game aGame)
  {
    // line 196 "../../../../../Block223 v3.ump"
    if(aGridHorizontalPosition <= 0 || aGridHorizontalPosition > maxNrHorizontalBlocks()) {
       			throw new RuntimeException("The horizontal position must be between 1 and " + maxNrHorizontalBlocks() + ".");
       		}
       		if(aGridVerticalPosition <= 0 || aGridVerticalPosition > maxNrVerticalBlocks()) {
       			throw new RuntimeException("The vertical position must be between 1 and " + maxNrVerticalBlocks() + ".");
       		}
    // END OF UMPLE BEFORE INJECTION
    gridHorizontalPosition = aGridHorizontalPosition;
    gridVerticalPosition = aGridVerticalPosition;
    boolean didAddLevel = setLevel(aLevel);
    if (!didAddLevel)
    {
      throw new RuntimeException("Unable to create blockAssignment due to level");
    }
    boolean didAddBlock = setBlock(aBlock);
    if (!didAddBlock)
    {
      throw new RuntimeException("Unable to create blockAssignment due to block");
    }
    boolean didAddGame = setGame(aGame);
    if (!didAddGame)
    {
      throw new RuntimeException("Unable to create blockAssignment due to game");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setGridHorizontalPosition(int aGridHorizontalPosition)
  {
    boolean wasSet = false;
    // line 204 "../../../../../Block223 v3.ump"
    if(aGridHorizontalPosition <= 0 || aGridHorizontalPosition > maxNrHorizontalBlocks()) {
       			throw new RuntimeException("The horizontal position must be between 1 and " + maxNrHorizontalBlocks() + ".");
       		}
    // END OF UMPLE BEFORE INJECTION
    gridHorizontalPosition = aGridHorizontalPosition;
    wasSet = true;
    return wasSet;
  }

  public boolean setGridVerticalPosition(int aGridVerticalPosition)
  {
    boolean wasSet = false;
    // line 209 "../../../../../Block223 v3.ump"
    if(aGridVerticalPosition <= 0 || aGridVerticalPosition > maxNrVerticalBlocks()) {
       			throw new RuntimeException("The vertical position must be between 1 and " + maxNrVerticalBlocks() + ".");
       		}
    // END OF UMPLE BEFORE INJECTION
    gridVerticalPosition = aGridVerticalPosition;
    wasSet = true;
    return wasSet;
  }

  public int getGridHorizontalPosition()
  {
    return gridHorizontalPosition;
  }

  public int getGridVerticalPosition()
  {
    return gridVerticalPosition;
  }
  /* Code from template association_GetOne */
  public Level getLevel()
  {
    return level;
  }
  /* Code from template association_GetOne */
  public Block getBlock()
  {
    return block;
  }
  /* Code from template association_GetOne */
  public Game getGame()
  {
    return game;
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
      existingLevel.removeBlockAssignment(this);
    }
    level.addBlockAssignment(this);
    wasSet = true;
    return wasSet;
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
      existingBlock.removeBlockAssignment(this);
    }
    block.addBlockAssignment(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOneToMany */
  public boolean setGame(Game aGame)
  {
    boolean wasSet = false;
    if (aGame == null)
    {
      return wasSet;
    }

    Game existingGame = game;
    game = aGame;
    if (existingGame != null && !existingGame.equals(aGame))
    {
      existingGame.removeBlockAssignment(this);
    }
    game.addBlockAssignment(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Level placeholderLevel = level;
    this.level = null;
    if(placeholderLevel != null)
    {
      placeholderLevel.removeBlockAssignment(this);
    }
    Block placeholderBlock = block;
    this.block = null;
    if(placeholderBlock != null)
    {
      placeholderBlock.removeBlockAssignment(this);
    }
    Game placeholderGame = game;
    this.game = null;
    if(placeholderGame != null)
    {
      placeholderGame.removeBlockAssignment(this);
    }
  }

  // line 215 "../../../../../Block223 v3.ump"
   public static  int maxNrHorizontalBlocks(){
    int max = (390 - 20 + 5)/25;
		return max;
  }

  // line 220 "../../../../../Block223 v3.ump"
   public static  int maxNrVerticalBlocks(){
    int max = (390 - 30 - 5 - 10 - 10 + 2) / 22;
	return max;
  }


  public String toString()
  {
    return super.toString() + "["+
            "gridHorizontalPosition" + ":" + getGridHorizontalPosition()+ "," +
            "gridVerticalPosition" + ":" + getGridVerticalPosition()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "level = "+(getLevel()!=null?Integer.toHexString(System.identityHashCode(getLevel())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "block = "+(getBlock()!=null?Integer.toHexString(System.identityHashCode(getBlock())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "game = "+(getGame()!=null?Integer.toHexString(System.identityHashCode(getGame())):"null");
  }  
  //------------------------
  // DEVELOPER CODE - PROVIDED AS-IS
  //------------------------
  
  // line 94 "../../../../../Block223Persistence.ump"
  private static final long serialVersionUID = -6011556325373584641L ;

  
}