/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse223.block.model;
import java.util.*;

// line 21 "../../../../../Block223.ump"
public class Admin
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Admin Attributes
  private String password;

  //Admin Associations
  private List<BlockGame> blockGames;
  private BlockApplication blockApplication;
  private User user;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Admin(String aPassword, BlockApplication aBlockApplication, User aUser)
  {
    password = aPassword;
    blockGames = new ArrayList<BlockGame>();
    boolean didAddBlockApplication = setBlockApplication(aBlockApplication);
    if (!didAddBlockApplication)
    {
      throw new RuntimeException("Unable to create admin due to blockApplication");
    }
    boolean didAddUser = setUser(aUser);
    if (!didAddUser)
    {
      throw new RuntimeException("Unable to create admin due to user");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setPassword(String aPassword)
  {
    boolean wasSet = false;
    password = aPassword;
    wasSet = true;
    return wasSet;
  }

  public String getPassword()
  {
    return password;
  }
  /* Code from template association_GetMany */
  public BlockGame getBlockGame(int index)
  {
    BlockGame aBlockGame = blockGames.get(index);
    return aBlockGame;
  }

  public List<BlockGame> getBlockGames()
  {
    List<BlockGame> newBlockGames = Collections.unmodifiableList(blockGames);
    return newBlockGames;
  }

  public int numberOfBlockGames()
  {
    int number = blockGames.size();
    return number;
  }

  public boolean hasBlockGames()
  {
    boolean has = blockGames.size() > 0;
    return has;
  }

  public int indexOfBlockGame(BlockGame aBlockGame)
  {
    int index = blockGames.indexOf(aBlockGame);
    return index;
  }
  /* Code from template association_GetOne */
  public BlockApplication getBlockApplication()
  {
    return blockApplication;
  }
  /* Code from template association_GetOne */
  public User getUser()
  {
    return user;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfBlockGames()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public BlockGame addBlockGame(String aName, int aNumLevels, HallOfFame aHallOfFame, PlayArea aPlayArea, BlockApplication aBlockApplication, Player aPlayer)
  {
    return new BlockGame(aName, aNumLevels, aHallOfFame, aPlayArea, aBlockApplication, aPlayer, this);
  }

  public boolean addBlockGame(BlockGame aBlockGame)
  {
    boolean wasAdded = false;
    if (blockGames.contains(aBlockGame)) { return false; }
    Admin existingAdmin = aBlockGame.getAdmin();
    boolean isNewAdmin = existingAdmin != null && !this.equals(existingAdmin);
    if (isNewAdmin)
    {
      aBlockGame.setAdmin(this);
    }
    else
    {
      blockGames.add(aBlockGame);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeBlockGame(BlockGame aBlockGame)
  {
    boolean wasRemoved = false;
    //Unable to remove aBlockGame, as it must always have a admin
    if (!this.equals(aBlockGame.getAdmin()))
    {
      blockGames.remove(aBlockGame);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addBlockGameAt(BlockGame aBlockGame, int index)
  {  
    boolean wasAdded = false;
    if(addBlockGame(aBlockGame))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfBlockGames()) { index = numberOfBlockGames() - 1; }
      blockGames.remove(aBlockGame);
      blockGames.add(index, aBlockGame);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveBlockGameAt(BlockGame aBlockGame, int index)
  {
    boolean wasAdded = false;
    if(blockGames.contains(aBlockGame))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfBlockGames()) { index = numberOfBlockGames() - 1; }
      blockGames.remove(aBlockGame);
      blockGames.add(index, aBlockGame);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addBlockGameAt(aBlockGame, index);
    }
    return wasAdded;
  }
  /* Code from template association_SetOneToMany */
  public boolean setBlockApplication(BlockApplication aBlockApplication)
  {
    boolean wasSet = false;
    if (aBlockApplication == null)
    {
      return wasSet;
    }

    BlockApplication existingBlockApplication = blockApplication;
    blockApplication = aBlockApplication;
    if (existingBlockApplication != null && !existingBlockApplication.equals(aBlockApplication))
    {
      existingBlockApplication.removeAdmin(this);
    }
    blockApplication.addAdmin(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOneToOptionalOne */
  public boolean setUser(User aNewUser)
  {
    boolean wasSet = false;
    if (aNewUser == null)
    {
      //Unable to setUser to null, as admin must always be associated to a user
      return wasSet;
    }
    
    Admin existingAdmin = aNewUser.getAdmin();
    if (existingAdmin != null && !equals(existingAdmin))
    {
      //Unable to setUser, the current user already has a admin, which would be orphaned if it were re-assigned
      return wasSet;
    }
    
    User anOldUser = user;
    user = aNewUser;
    user.setAdmin(this);

    if (anOldUser != null)
    {
      anOldUser.setAdmin(null);
    }
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    for(int i=blockGames.size(); i > 0; i--)
    {
      BlockGame aBlockGame = blockGames.get(i - 1);
      aBlockGame.delete();
    }
    BlockApplication placeholderBlockApplication = blockApplication;
    this.blockApplication = null;
    if(placeholderBlockApplication != null)
    {
      placeholderBlockApplication.removeAdmin(this);
    }
    User existingUser = user;
    user = null;
    if (existingUser != null)
    {
      existingUser.setAdmin(null);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "password" + ":" + getPassword()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "blockApplication = "+(getBlockApplication()!=null?Integer.toHexString(System.identityHashCode(getBlockApplication())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "user = "+(getUser()!=null?Integer.toHexString(System.identityHashCode(getUser())):"null");
  }
}