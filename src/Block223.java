/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/


import java.io.Serializable;

// line 3 "Block223Persistence.ump"
public class Block223 implements Serializable
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Block223()
  {}

  //------------------------
  // INTERFACE
  //------------------------

  public void delete()
  {}

  // line 8 "Block223Persistence.ump"
   public void reinitialize(){
    Game.reinitializeUniqueGameName(this.getGames());
    User.reinitializeUniqueUserName(this.getUsers());
  	List<Block> blocks = ArrayList<Block>();
  	for( Game game : this.getGames() ) {
  		for ( Block block: game.getBlocks()) {
  	    blocks.add(block);
  	    }
  	}
    Block.reinitializeAutouniqueID(blocks);
  }
  
  //------------------------
  // DEVELOPER CODE - PROVIDED AS-IS
  //------------------------
  
  // line 6 "Block223Persistence.ump"
  private static final long serialVersionUID = -4904473121226232586L ;

  
}