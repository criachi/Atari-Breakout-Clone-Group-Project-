/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse223.block.model;
import java.util.*;

// line 27 "../../../../../Block223.ump"
public class HallOfFame
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //HallOfFame Associations
  private List<User> users;
  private BlockGame blockGame;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public HallOfFame(BlockGame aBlockGame)
  {
    users = new ArrayList<User>();
    if (aBlockGame == null || aBlockGame.getHallOfFame() != null)
    {
      throw new RuntimeException("Unable to create HallOfFame due to aBlockGame");
    }
    blockGame = aBlockGame;
  }

  public HallOfFame(String aNameForBlockGame, int aNumLevelsForBlockGame, PlayArea aPlayAreaForBlockGame, Player aPlayerForBlockGame, Admin aAdminForBlockGame)
  {
    users = new ArrayList<User>();
    blockGame = new BlockGame(aNameForBlockGame, aNumLevelsForBlockGame, this, aPlayAreaForBlockGame, aPlayerForBlockGame, aAdminForBlockGame);
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetMany */
  public User getUser(int index)
  {
    User aUser = users.get(index);
    return aUser;
  }

  public List<User> getUsers()
  {
    List<User> newUsers = Collections.unmodifiableList(users);
    return newUsers;
  }

  public int numberOfUsers()
  {
    int number = users.size();
    return number;
  }

  public boolean hasUsers()
  {
    boolean has = users.size() > 0;
    return has;
  }

  public int indexOfUser(User aUser)
  {
    int index = users.indexOf(aUser);
    return index;
  }
  /* Code from template association_GetOne */
  public BlockGame getBlockGame()
  {
    return blockGame;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfUsers()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public User addUser(String aName, Player aPlayer)
  {
    return new User(aName, aPlayer, this);
  }

  public boolean addUser(User aUser)
  {
    boolean wasAdded = false;
    if (users.contains(aUser)) { return false; }
    HallOfFame existingHallOfFame = aUser.getHallOfFame();
    boolean isNewHallOfFame = existingHallOfFame != null && !this.equals(existingHallOfFame);
    if (isNewHallOfFame)
    {
      aUser.setHallOfFame(this);
    }
    else
    {
      users.add(aUser);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeUser(User aUser)
  {
    boolean wasRemoved = false;
    //Unable to remove aUser, as it must always have a hallOfFame
    if (!this.equals(aUser.getHallOfFame()))
    {
      users.remove(aUser);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addUserAt(User aUser, int index)
  {  
    boolean wasAdded = false;
    if(addUser(aUser))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfUsers()) { index = numberOfUsers() - 1; }
      users.remove(aUser);
      users.add(index, aUser);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveUserAt(User aUser, int index)
  {
    boolean wasAdded = false;
    if(users.contains(aUser))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfUsers()) { index = numberOfUsers() - 1; }
      users.remove(aUser);
      users.add(index, aUser);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addUserAt(aUser, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    for(int i=users.size(); i > 0; i--)
    {
      User aUser = users.get(i - 1);
      aUser.delete();
    }
    BlockGame existingBlockGame = blockGame;
    blockGame = null;
    if (existingBlockGame != null)
    {
      existingBlockGame.delete();
    }
  }

}