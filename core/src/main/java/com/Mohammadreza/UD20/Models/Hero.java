package com.Mohammadreza.UD20.Models;

import java.util.ArrayList;

public class Hero {
    private int hp;
    private final float speed;
    private final String name;
    private int Xp;
    private int level;
    private final HeroType type;
    private Player owner;
    private boolean increasedLevel = false;

    public Hero(HeroType type , Player owner) {
        this.type = type;
        this.hp = type.getHp();
        this.speed = type.getSpeed();
        this.level = 0;
        this.Xp = 0;
        this.name = type.getName();
        this.owner = owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public Player getOwner() {
        return owner;
    }

    public void increaseXp(int increase) {
        Xp += increase;
        int previousLevel = level;

        while (Xp >= neededXpForNextLevel(level)) {
            Xp -= neededXpForNextLevel(level);
            increaseLevel();
        }

        if (level > previousLevel) {
            increasedLevel = true;
        }
    }


    public void decreaseHp(int decrease) {
        hp -= decrease;
    }

    public int neededXpForNextLevel(int level) {
        return 20 * level;
    }

    public int getXp() {
        return Xp;
    }

    public int getHp() {
        return hp;
    }

    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    public HeroType getType() {
        return type;
    }

    public void increaseLevel(){
        level++;
        owner.addAbility(getRandomAbility());
    }

    public float getSpeed() {
        return speed;
    }
    public Ability getRandomAbility() {
        Ability[] abilities = Ability.values();
        return abilities[(int) (Math.random() * abilities.length)];
    }

    public void setIncreasedLevel(boolean increasedLevel) {
        this.increasedLevel = increasedLevel;
    }
    public boolean isIncreasedLevel() {
        return increasedLevel;
    }

    public Ability getLastAbility() {
        return owner.getAbilities().get(owner.getAbilities().size() - 1);
    }


    public void setHp(int hp) {
        this.hp = hp;
    }


}
