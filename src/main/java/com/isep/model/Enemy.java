package com.isep.model;

/**
 * Class Enemy
 */
abstract public class Enemy {

  //
  // Fields
  //
  private final int type;
  private final String name;
  private int lifePoints;
  private final int weaponDamages;

  //
  // Constructors
  //
  public Enemy (int type, String name, int lifePoints, int weaponDamages) {
    this.type = type; // 1 = Basic | 2 = Boss
    this.name = name;
    this.lifePoints = lifePoints;
    this.weaponDamages = weaponDamages;
  }

  //
  // Methods
  //
  public String getName() {
    return name;
  }

  public int getLifePoints() {
    return lifePoints;
  }

  public String getType() {
    if (type == 1) {
      return "Basic";
    } else if (type == 2) {
      return "Boss";
    } else {
      return null;
    }
  }

  public int getWeaponDamages() {
    return weaponDamages;
  }

  public int attack() {
    return weaponDamages;
  }

  public void deleteLifePoints(int lifePoints) {
    this.lifePoints = this.lifePoints - lifePoints;
  }

}
