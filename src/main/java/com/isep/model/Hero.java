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
  private final IntegerProperty type;
  private final StringProperty name;
  private IntegerProperty lifePoints;
  private IntegerProperty armor;
  private IntegerProperty weaponDamages;
  private ArrayList<Potion> potions;
  private IntegerProperty sizeOfPotions;
  private ArrayList<Food> foods;
  private IntegerProperty sizeOfFoods;

  //
  // Constructors
  //
  public Hero (int type, String name, int lifePoints, int armor, int weaponDamages, ArrayList<Potion> potions, ArrayList<Food> foods) {
    this.type = new SimpleIntegerProperty(type); // 1 = Hunter | 2 = Healer | 3 = Mage | 4 = Warrior
    this.name = new SimpleStringProperty(name);
    this.lifePoints = new SimpleIntegerProperty(lifePoints);
    this.armor = new SimpleIntegerProperty(armor);
    this.weaponDamages = new SimpleIntegerProperty(weaponDamages);
    this.potions = potions;
    this.sizeOfPotions = new SimpleIntegerProperty(potions.size());
    this.foods = foods;
    this.sizeOfFoods = new SimpleIntegerProperty(foods.size());
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
    return lifePoints.get();
  }

  public IntegerProperty lifePointsProperty() {
    return lifePoints;
  }

  public int getArmor() {
    return armor.get();
  }

  public IntegerProperty armorProperty() {
    return armor;
  }

  public int getWeaponDamages() {
    return weaponDamages.get();
  }

  public IntegerProperty weaponDamagesProperty() {
    return weaponDamages;
  }

  public ArrayList<Potion> getPotions() {
    return potions;
  }

  public int getSizeOfPotions() {
    return sizeOfPotions.get();
  }

  public IntegerProperty sizeOfPotionsProperty() {
    return sizeOfPotions;
  }

  public ArrayList<Food> getFoods() {
    return foods;
  }

  public int getSizeOfFoods() {
    return sizeOfFoods.get();
  }

  public IntegerProperty sizeOfPFoodsProperty() {
    return sizeOfFoods;
  }

  public int attack(){
    return weaponDamages.get();
  }

  public void eat(){
    lifePoints.set(lifePoints.get() + 5);
    int index = foods.size() - 1;
    foods.remove(index);
    sizeOfFoods = new SimpleIntegerProperty(foods.size());
  }

  public void heal(){
    lifePoints.set(lifePoints.get() + 10);
    int index = potions.size() - 1;
    potions.remove(index);
    sizeOfPotions = new SimpleIntegerProperty(potions.size());
  }

  public void upgradeArmor(){
    armor.set(armor.get() + 20);
  }

  public void upgradeWeapon(){
    weaponDamages.set(weaponDamages.get() + 10);
  }

  public void addPotions(){
    potions.add(new Potion());
    potions.add(new Potion());
    this.sizeOfPotions = new SimpleIntegerProperty(2);
  }

  public void addFoods(){
    foods.add(new Food());
    foods.add(new Food());
    this.sizeOfFoods = new SimpleIntegerProperty(2);
  }

  public void deleteLifePoints(int number) {
    if (armor.get() != 0 && armor.get()<number){
      lifePoints.set(lifePoints.get() - (number - armor.get()));
    } else if (armor.get() == 0){
      lifePoints.set(lifePoints.get() - number);
    }
  }

  public void setType(int type) {
    this.type.set(type);
  }

  public void setName(String name) {
    this.name.set(name);
  }

  public void setLifePoints() {
    switch (type.get()) {
      case 1, 2, 4 -> {
        this.lifePoints.set(50);
      }
      case 3 -> {
        this.lifePoints.set(60);
      }
    }
  }

  public void setArmor() {
    switch (type.get()) {
      case 1, 2, 3 -> {
        this.armor.set(0);
      }
      case 4 -> {
        this.armor.set(5);
      }
    }
  }

  public void setWeaponDamages() {
    switch (type.get()) {
      case 1, 4 -> {
        this.weaponDamages.set(15);
      }
      case 2, 3 -> {
        this.weaponDamages.set(10);
      }
    }
  }

  public void setPotions() {
    ArrayList<Potion> potions = new ArrayList<Potion>();
    potions.add(new Potion());
    potions.add(new Potion());
    switch (type.get()) {
      case 2 -> {
        this.potions = potions;
        this.sizeOfPotions = new SimpleIntegerProperty(this.potions.size());
      }
      case 1, 3, 4 -> {
        this.sizeOfPotions = new SimpleIntegerProperty(0);
      }
    }
  }

  public void setFoods() {
    this.foods = new ArrayList<Food>();
    this.sizeOfFoods = new SimpleIntegerProperty(0);
  }

}
