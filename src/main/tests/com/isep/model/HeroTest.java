package com.isep.model;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.text.MessageFormat;
import java.time.Duration;
import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

class HeroTest {

    private static Instant startedAt;

    @BeforeAll
    static public void initStartingTime() {
        startedAt = Instant.now();
    }

    @AfterAll
    static public void showTestDuration() {
        Instant endedAt = Instant.now();
        long duration = Duration.between(startedAt, endedAt).toMillis();
        System.out.println(MessageFormat.format("Durée des tests : {0} ms", duration));
    }

    @Test
    void getType() {
        Hero hunter = new Hunter("hunterHeroTest");
        Hero healer = new Healer("healerHeroTest");
        Hero mage = new Mage("mageHeroTest");
        Hero warrior = new Warrior("warriorHeroTest");
        Assertions.assertEquals("Hunter", hunter.getType());
        Assertions.assertEquals("Healer", healer.getType());
        Assertions.assertEquals("Mage", mage.getType());
        Assertions.assertEquals("Warrior", warrior.getType());
    }

    @Test
    void typeProperty() {
        Hero hunter = new Hunter("hunterHeroTest");
        Hero healer = new Healer("healerHeroTest");
        Hero mage = new Mage("mageHeroTest");
        Hero warrior = new Warrior("warriorHeroTest");
        Assertions.assertEquals("Hunter", hunter.typeProperty().get());
        Assertions.assertEquals("Healer", healer.typeProperty().get());
        Assertions.assertEquals("Mage", mage.typeProperty().get());
        Assertions.assertEquals("Warrior", warrior.typeProperty().get());
    }

    @Test
    void getName() {
        Hero hunter = new Hunter("hunterHeroTest");
        Hero healer = new Healer("healerHeroTest");
        Hero mage = new Mage("mageHeroTest");
        Hero warrior = new Warrior("warriorHeroTest");
        Assertions.assertEquals("hunterHeroTest", hunter.getName());
        Assertions.assertEquals("healerHeroTest", healer.getName());
        Assertions.assertEquals("mageHeroTest", mage.getName());
        Assertions.assertEquals("warriorHeroTest", warrior.getName());
    }

    @Test
    void nameProperty() {
        Hero hunter = new Hunter("hunterHeroTest");
        Hero healer = new Healer("healerHeroTest");
        Hero mage = new Mage("mageHeroTest");
        Hero warrior = new Warrior("warriorHeroTest");
        Assertions.assertEquals("hunterHeroTest", hunter.nameProperty().get());
        Assertions.assertEquals("healerHeroTest", healer.nameProperty().get());
        Assertions.assertEquals("mageHeroTest", mage.nameProperty().get());
        Assertions.assertEquals("warriorHeroTest", warrior.nameProperty().get());
    }

    @Test
    void getLifePoints() {
        Hero hunter = new Hunter("hunterHeroTest");
        Hero healer = new Healer("healerHeroTest");
        Hero mage = new Mage("mageHeroTest");
        Hero warrior = new Warrior("warriorHeroTest");
        Assertions.assertEquals(50, hunter.getLifePoints());
        Assertions.assertEquals(50, healer.getLifePoints());
        Assertions.assertEquals(80, mage.getLifePoints());
        Assertions.assertEquals(70, warrior.getLifePoints());
    }

    @Test
    void getWeaponDamages() {
        Hero hunter = new Hunter("hunterHeroTest");
        Hero healer = new Healer("healerHeroTest");
        Hero mage = new Mage("mageHeroTest");
        Hero warrior = new Warrior("warriorHeroTest");
        Assertions.assertEquals(10, hunter.getWeaponDamages());
        Assertions.assertEquals(5, healer.getWeaponDamages());
        Assertions.assertEquals(10, mage.getWeaponDamages());
        Assertions.assertEquals(20, warrior.getWeaponDamages());
    }

    @Test
    void getSizeOfPotions() {
        Hero hunter = new Hunter("hunterHeroTest");
        Hero healer = new Healer("healerHeroTest");
        Hero mage = new Mage("mageHeroTest");
        Hero warrior = new Warrior("warriorHeroTest");
        Assertions.assertEquals(0, hunter.getSizeOfPotions());
        Assertions.assertEquals(2, healer.getSizeOfPotions());
        Assertions.assertEquals(1, mage.getSizeOfPotions());
        Assertions.assertEquals(0, warrior.getSizeOfPotions());
    }

    @Test
    void getSizeOfFoods() {
        Hero hunter = new Hunter("hunterHeroTest");
        Hero healer = new Healer("healerHeroTest");
        Hero mage = new Mage("mageHeroTest");
        Hero warrior = new Warrior("warriorHeroTest");
        Assertions.assertEquals(0, hunter.getSizeOfFoods());
        Assertions.assertEquals(0, healer.getSizeOfFoods());
        Assertions.assertEquals(0, mage.getSizeOfFoods());
        Assertions.assertEquals(0, warrior.getSizeOfFoods());
    }

    @Test
    void attack() {
        Hero hunter = new Hunter("hunterHeroTest");
        Hero healer = new Healer("healerHeroTest");
        Hero mage = new Mage("mageHeroTest");
        Hero warrior = new Warrior("warriorHeroTest");
        Assertions.assertEquals(10, hunter.attack());
        Assertions.assertEquals(5, healer.attack());
        Assertions.assertEquals(10, mage.attack());
        Assertions.assertEquals(20, warrior.attack());
    }

    @Test
    void addFoods() {
        Hero hunter = new Hunter("hunterHeroTest");
        hunter.addFoods();
        Hero healer = new Healer("healerHeroTest");
        healer.addFoods();
        Hero mage = new Mage("mageHeroTest");
        mage.addFoods();
        Hero warrior = new Warrior("warriorHeroTest");
        warrior.addFoods();
        Assertions.assertEquals(2, hunter.getSizeOfFoods());
        Assertions.assertEquals(2, healer.getSizeOfFoods());
        Assertions.assertEquals(2, mage.getSizeOfFoods());
        Assertions.assertEquals(2, warrior.getSizeOfFoods());
    }

    @Test
    void addPotions() {
        Hero hunter = new Hunter("hunterHeroTest");
        hunter.addPotions();
        Hero healer = new Healer("healerHeroTest");
        healer.addPotions();
        Hero mage = new Mage("mageHeroTest");
        mage.addPotions();
        Hero warrior = new Warrior("warriorHeroTest");
        warrior.addPotions();
        Assertions.assertEquals(2, hunter.getSizeOfPotions());
        Assertions.assertEquals(4, healer.getSizeOfPotions());
        Assertions.assertEquals(3, mage.getSizeOfPotions());
        Assertions.assertEquals(2, warrior.getSizeOfPotions());
    }

    @Test
    void eat() {
        Hero hunter = new Hunter("hunterHeroTest");
        hunter.addFoods();
        hunter.eat();
        Hero healer = new Healer("healerHeroTest");
        healer.addFoods();
        healer.eat();
        Hero mage = new Mage("mageHeroTest");
        mage.addFoods();
        mage.eat();
        Hero warrior = new Warrior("warriorHeroTest");
        warrior.addFoods();
        warrior.eat();
        Assertions.assertEquals(1, hunter.getSizeOfFoods());
        Assertions.assertEquals(1, healer.getSizeOfFoods());
        Assertions.assertEquals(1, mage.getSizeOfFoods());
        Assertions.assertEquals(1, warrior.getSizeOfFoods());
    }

    @Test
    void heal() {
        Hero hunter = new Hunter("hunterHeroTest");
        hunter.addPotions();
        hunter.heal();
        Hero healer = new Healer("healerHeroTest");
        healer.addPotions();
        healer.heal();
        Hero mage = new Mage("mageHeroTest");
        mage.addPotions();
        mage.heal();
        Hero warrior = new Warrior("warriorHeroTest");
        warrior.addPotions();
        warrior.heal();
        Assertions.assertEquals(1, hunter.getSizeOfPotions());
        Assertions.assertEquals(3, healer.getSizeOfPotions());
        Assertions.assertEquals(2, mage.getSizeOfPotions());
        Assertions.assertEquals(1, warrior.getSizeOfPotions());
    }

    @Test
    void getArmor() {
        Hero hunter = new Hunter("hunterHeroTest");
        Hero healer = new Healer("healerHeroTest");
        Hero mage = new Mage("mageHeroTest");
        Hero warrior = new Warrior("warriorHeroTest");
        Assertions.assertEquals(0, hunter.getArmor());
        Assertions.assertEquals(0, healer.getArmor());
        Assertions.assertEquals(0, mage.getArmor());
        Assertions.assertEquals(10, warrior.getArmor());
    }

    @Test
    void upgradeArmor() {
        Hero hunter = new Hunter("hunterHeroTest");
        hunter.upgradeArmor();
        Hero healer = new Healer("healerHeroTest");
        healer.upgradeArmor();
        Hero mage = new Mage("mageHeroTest");
        mage.upgradeArmor();
        Hero warrior = new Warrior("warriorHeroTest");
        warrior.upgradeArmor();
        Assertions.assertEquals(20, hunter.getArmor());
        Assertions.assertEquals(20, healer.getArmor());
        Assertions.assertEquals(20, mage.getArmor());
        Assertions.assertEquals(30, warrior.getArmor());
    }

    @Test
    void upgradeWeapon() {
        Hero hunter = new Hunter("hunterHeroTest");
        hunter.upgradeWeapon();
        Hero healer = new Healer("healerHeroTest");
        healer.upgradeWeapon();
        Hero mage = new Mage("mageHeroTest");
        mage.upgradeWeapon();
        Hero warrior = new Warrior("warriorHeroTest");
        warrior.upgradeWeapon();
        Assertions.assertEquals(20, hunter.getWeaponDamages());
        Assertions.assertEquals(15, healer.getWeaponDamages());
        Assertions.assertEquals(20, mage.getWeaponDamages());
        Assertions.assertEquals(30, warrior.getWeaponDamages());
    }

    @Test
    void deleteLifePoints() {
        Hero hunter = new Hunter("hunterHeroTest");
        hunter.deleteLifePoints(10);
        Hero healer = new Healer("healerHeroTest");
        healer.deleteLifePoints(10);
        Hero mage = new Mage("mageHeroTest");
        mage.deleteLifePoints(10);
        Hero warrior = new Warrior("warriorHeroTest");
        warrior.deleteLifePoints(10);
        Assertions.assertEquals(40, hunter.getLifePoints());
        Assertions.assertEquals(40, healer.getLifePoints());
        Assertions.assertEquals(70, mage.getLifePoints());
        Assertions.assertEquals(70, warrior.getLifePoints());
    }

    @Test
    void setType() {
        Hero hero = new Hero();
        hero.setName("heroTest");
        Assertions.assertEquals("heroTest", hero.getName());
    }

    @Test
    void setName() {
        Hero hero = new Hero();
        hero.setType(1);
        Assertions.assertEquals("Hunter", hero.getType());
    }

}