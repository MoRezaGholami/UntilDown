package com.Mohammadreza.UD20.Models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;

public abstract class Monster {
    protected int hp;
    protected int damage = 1;
    protected float spawnRate;
    protected Texture texture = GameAssetManager.brainMonsterTex;
    protected Sprite sprite = new Sprite(texture);
    protected float posX;
    protected float posY;
    protected CollisionRect rect;
    protected boolean isMonsterIdle = true;
    protected float time =0 ;
    protected Animation<Texture> animation;
    protected boolean isDying = false;
    protected boolean isDead = false;
    public float DamageCooldown = 0;

    public Monster(float posX, float posY) {
        this.posX = posX;
        this.posY = posY;

        sprite.setX(posX);
        sprite.setY(posY);
        sprite.setSize(texture.getWidth()  ,texture.getHeight() );
        rect = new CollisionRect(sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight());
    }
    public void decreaseHp(int dmg) {
        hp -= dmg;
    }

    public void shooting(){

    }

    public int getHp() {
        return hp;
    }


    public float getPosX() {
        return posX;
    }

    public void setPosX(float posX) {
        this.posX = posX;

    }

    public float getPosY() {
        return posY;
    }

    public void setPosY(float posY) {
        this.posY = posY;

    }

    public int getDamage() {
        return damage;
    }

    public float getSpawnRate() {
        return spawnRate;
    }

    public Texture getTexture() {
        return texture;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public CollisionRect getRect() {
        return rect;
    }

    public boolean isMonsterIdle() {
        return isMonsterIdle;
    }

    public void setMonsterIdle(boolean isMonsterIdle) {
        this.isMonsterIdle = isMonsterIdle;
    }

    public float getTime() {
        return time;
    }

    public Animation<Texture> getAnimation() {
        return animation;
    }

    public void setTime(float time) {
        this.time = time;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public boolean isDying() {
        return isDying;
    }

    public void setDying(boolean dying) {
        isDying = dying;
    }

    public boolean isDead() {
        return isDead;
    }

    public void setDead(boolean dead) {
        isDead = dead;
    }



}
