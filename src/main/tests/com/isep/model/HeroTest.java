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
        Assertions.assertEquals(80, hunter.getLifePoints());
        Assertions.assertEquals(50, healer.getLifePoints());
        Assertions.assertEquals(80, mage.getLifePoints());
        Assertions.assertEquals(70, warrior.getLifePoints());
    }

    @Test
    void getWeaponDamages() {
    }

    @Test
    void getSizeOfPotions() {
    }

    @Test
    void getSizeOfFoods() {
    }

    @Test
    void attack() {
    }

    @Test
    void eat() {
    }

    @Test
    void heal() {
    }

    @Test
    void upgradeArmor() {
    }

    @Test
    void upgradeWeapon() {
    }

    @Test
    void addPotions() {
    }

    @Test
    void addFoods() {
    }

    @Test
    void deleteLifePoints() {
    }

    @Test
    void setType() {
    }

    @Test
    void setName() {
    }
}