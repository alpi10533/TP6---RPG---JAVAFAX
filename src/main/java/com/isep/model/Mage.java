
package com.isep.model;

import java.util.ArrayList;

/**
 * Class Mage
 */
public class Mage extends SpellCaster {

  //
  // Fields
  //
  
  //
  // Constructors
  //
  public Mage (String name) {
    super(3, name,80,0,10, new ArrayList<Potion>(){{add(new Potion());}}, new ArrayList<Food>());
  }
  
  //
  // Methods
  //

}
