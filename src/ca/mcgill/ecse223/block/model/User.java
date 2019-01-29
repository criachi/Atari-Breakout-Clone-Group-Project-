/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse223.block.model;
import java.util.*;

// line 10 "../../../../../Block223.ump"
public class User
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static Map<String, User> usersByName = new HashMap<String, User>();

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //User Attributes
  private String name;

  //User Associations
  private Player player;
  private Admin admin;
  private BlockApplication blockApplication;
  private HallOfFame hallOfFame;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public User(String aName, Player aPlayer, BlockApplication aBlockApplication, HallOfFame aHallOfFame)
  {
    if (!setName(aName))
    {
      throw new RuntimeException("Cannot create due to duplicate name");
    }
    if (aPlayer == null || aPlayer.getUser() != null)
    {
      throw new RuntimeException("Unable to create User due to aPlayer");
    }
    player = aPlayer;
    boolean didAddBlockApplication = setBlockApplication(aBlockApplication);
    if (!didAddBlockApplication)
    {
      throw new RuntimeException("Unable to create user due to blockApplication");
    }
    boolean didAddHallOfFame = setHallOfFame(aHallOfFame);
    if (!didAddHallOfFame)
    {
      throw new RuntimeException("Unable to create user due to hallOfFame");
    }
  }

  public User(String aName, String aPasswordForPlayer, BlockApplication aBlockApplicationForPlayer, BlockApplication aBlockApplication, HallOfFame aHallOfFame)
  {
    name = aName;
    player = new Player(aPasswordForPlayer, aBlockApplicationForPlayer, this);
    boolean didAddBlockApplication = setBlockApplication(aBlockApplication);
    if (!didAddBlockApplication)
    {
      throw new RuntimeException("Unable to create user due to blockApplication");
    }
    boolean didAddHallOfFame = setHallOfFame(aHallOfFame);
    if (!didAddHallOfFame)
    {
      throw new RuntimeException("Unable to create user due to hallOfFame");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setName(String aName)
  {
    boolean wasSet = false;
    String anOldName = getName();
    if (hasWithName(aName)) {
      return wasSet;
    }
    name = aName;
    wasSet = true;
    if (anOldName != null) {
      usersByName.remove(anOldName);
    }
    usersByName.put(aName, this);
    return wasSet;
  }

  public String getName()
  {
    return name;
  }
  /* Code from template attribute_GetUnique */
  public static User getWithName(String aName)
  {
    return usersByName.get(aName);
  }
  /* Code from template attribute_HasUnique */
  public static boolean hasWithName(String aName)
  {
    return getWithName(aName) != null;
  }
  /* Code from template association_GetOne */
  public Player getPlayer()
  {
    return player;
  }
  /* Code from template association_GetOne */
  public Admin getAdmin()
  {
    return admin;
  }

  public boolean hasAdmin()
  {
    boolean has = admin != null;
    return has;
  }
  /* Code from template association_GetOne */
  public BlockApplication getBlockApplication()
  {
    return blockApplication;
  }
  /* Code from template association_GetOne */
  public HallOfFame getHallOfFame()
  {
    return hallOfFame;
  }
  /* Code from template association_SetOptionalOneToOne */
  public boolean setAdmin(Admin aNewAdmin)
  {
    boolean wasSet = false;
    if (admin != null && !admin.equals(aNewAdmin) && equals(admin.getUser()))
    {
      //Unable to setAdmin, as existing admin would become an orphan
      return wasSet;
    }

    admin = aNewAdmin;
    User anOldUser = aNewAdmin != null ? aNewAdmin.getUser() : null;

    if (!this.equals(anOldUser))
    {
      if (anOldUser != null)
      {
        anOldUser.admin = null;
      }
      if (admin != null)
      {
        admin.setUser(this);
      }
    }
    wasSet = true;
    return wasSet;
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
      existingBlockApplication.removeUser(this);
    }
    blockApplication.addUser(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOneToMany */
  public boolean setHallOfFame(HallOfFame aHallOfFame)
  {
    boolean wasSet = false;
    if (aHallOfFame == null)
    {
      return wasSet;
    }

    HallOfFame existingHallOfFame = hallOfFame;
    hallOfFame = aHallOfFame;
    if (existingHallOfFame != null && !existingHallOfFame.equals(aHallOfFame))
    {
      existingHallOfFame.removeUser(this);
    }
    hallOfFame.addUser(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    usersByName.remove(getName());
    Player existingPlayer = player;
    player = null;
    if (existingPlayer != null)
    {
      existingPlayer.delete();
    }
    Admin existingAdmin = admin;
    admin = null;
    if (existingAdmin != null)
    {
      existingAdmin.delete();
    }
    BlockApplication placeholderBlockApplication = blockApplication;
    this.blockApplication = null;
    if(placeholderBlockApplication != null)
    {
      placeholderBlockApplication.removeUser(this);
    }
    HallOfFame placeholderHallOfFame = hallOfFame;
    this.hallOfFame = null;
    if(placeholderHallOfFame != null)
    {
      placeholderHallOfFame.removeUser(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "name" + ":" + getName()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "player = "+(getPlayer()!=null?Integer.toHexString(System.identityHashCode(getPlayer())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "admin = "+(getAdmin()!=null?Integer.toHexString(System.identityHashCode(getAdmin())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "blockApplication = "+(getBlockApplication()!=null?Integer.toHexString(System.identityHashCode(getBlockApplication())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "hallOfFame = "+(getHallOfFame()!=null?Integer.toHexString(System.identityHashCode(getHallOfFame())):"null");
  }
}