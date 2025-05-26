package com.Mohammadreza.UD20.Models;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class MonsterSeed {

    private final Texture texture = GameAssetManager.monsterSeedTex;
    private final Sprite sprite = new Sprite(texture);

    private  float PosX;
    private  float PosY;
    private CollisionRect rect;
    private boolean taken = false;
    public MonsterSeed(float PosX, float PosY) {
        this.PosX = PosX;
        this.PosY = PosY;

        sprite.setX(PosX);
        sprite.setY(PosY);

        sprite.setSize(texture.getWidth() * 10, texture.getHeight() * 10);
        rect = new CollisionRect(sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight());

    }


    public float getPosX() {
        return PosX;
    }

    public float getPosY() {
        return PosY;
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

    public boolean isTaken() {
        return taken;
    }

    public void setTaken(boolean taken) {
        this.taken = taken;
    }

    public void setPosX(float posX) {
        PosX = posX;
    }

    public void setPosY(float posY) {
        PosY = posY;
    }
}
