package com.isep.model;

import javafx.beans.property.*;
import java.util.ArrayList;

/**
 * Class Hero
 */
public class Hero {

  //
  // Fields
  //

  // on utilise des "IntegerProperty" et "StringProperty" pour que la vue soit toujours synchronisée avec les données du héro

  private final IntegerProperty type;
  private final StringProperty name;
  private int lifePoints;
  private int armor;
  private int weaponDamages;
  private ArrayList<Potion> potions;
  private int sizeOfPotions;
  private ArrayList<Food> foods;
  private int sizeOfFoods;

  //
  // Constructors
  //
  public Hero (int type, String name, int lifePoints, int armor, int weaponDamages, ArrayList<Potion> potions, ArrayList<Food> foods) {
    this.type = new SimpleIntegerProperty(type); // 1 = Hunter | 2 = Healer | 3 = Mage | 4 = Warrior
    this.name = new SimpleStringProperty(name);
    this.lifePoints = lifePoints;
    this.armor = armor;
    this.weaponDamages = weaponDamages;
    this.potions = potions;
    this.sizeOfPotions = potions.size();
    this.foods = foods;
    this.sizeOfFoods = foods.size();
  }

  public Hero () {
    this(0, "", 0, 0, 0, new ArrayList<Potion>(), new ArrayList<Food>());
  }

  //
  // Methods
  //
  public String getType() {
    if (type.get() == 1) {
      return "Hunter";
    } else if (type.get() == 2) {
      return "Healer";
    } else if (type.get() == 3) {
      return "Mage";
    } else if (type.get() == 4) {
      return "Warrior";
    } else {
      return null;
    }
  }

  public StringProperty typeProperty() {
    if (type.get() == 1) {
      return new SimpleStringProperty("Hunter");
    } else if (type.get() == 2) {
      return new SimpleStringProperty("Healer");
    } else if (type.get() == 3) {
      return new SimpleStringProperty("Mage");
    } else if (type.get() == 4) {
      return new SimpleStringProperty("Warrior");
    } else {
      return null;
    }
  }

  public String getName() {
    return name.get();
  }

  public StringProperty nameProperty() {
    return name;
  }

  public int getLifePoints() {
    return lifePoints;
  }

  public int getArmor() {
    return armor;
  }

  public int getWeaponDamages() {
    return weaponDamages;
  }

  public int getSizeOfPotions() {
    return sizeOfPotions;
  }

  public int getSizeOfFoods() {
    return sizeOfFoods;
  }

  public int attack(){
    return weaponDamages;
  }

  public void eat(){
    lifePoints = lifePoints + 5;
    int index = foods.size() - 1;
    foods.remove(index);
    sizeOfFoods = foods.size();
  }

  public void heal(){
    lifePoints = lifePoints + 10;
    int index = potions.size() - 1;
    potions.remove(index);
    sizeOfPotions = potions.size();
  }

  public void upgradeArmor(){
    armor = armor + 20;
  }

  public void upgradeWeapon(){
    weaponDamages = weaponDamages + 10;
  }

  public void addPotions(){
    potions.add(new Potion());
    potions.add(new Potion());
    sizeOfPotions = potions.size();
  }

  public void addFoods(){
    foods.add(new Food());
    foods.add(new Food());
    sizeOfFoods = foods.size();
  }

  public void deleteLifePoints(int number) {
    if (armor != 0 && armor<number){
      lifePoints = lifePoints - (number - armor);
    } else if (armor == 0){
      lifePoints = lifePoints - number;
    }
  }

  public void setType(int type) {
    this.type.set(type);
  }

  public void setName(String name) {
    this.name.set(name);
  }

}
