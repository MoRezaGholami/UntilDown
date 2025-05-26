package com.Mohammadreza.UD20.Models;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Tree {
    private final float PosX;
    private final float PosY;
    private CollisionRect rect;
    private final Texture texture = GameAssetManager.treeMonsterTex;
    private float time = 0;
    private final int damage = 1;
    private final Sprite sprite = new Sprite(texture);
    private final Animation<Texture> animation = GameAssetManager.treeAnimation;
    private float damageCooldown = 0;

    public Tree(float PosX, float PosY){
        this.PosX = PosX;
        this.PosY = PosY;

        sprite.setX(PosX);
        sprite.setY(PosY);

        sprite.setSize(texture.getWidth()  ,texture.getHeight() );
        rect = new CollisionRect(sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight());

    }

    public float getPosX() {
        return PosX;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public Texture getTexture() {
        return texture;
    }

    public CollisionRect getRect() {
        return rect;
    }

    public float getPosY() {
        return PosY;
    }

    public Animation<Texture> getAnimation() {
        return animation;
    }

    public float getTime() {
        return time;
    }

    public void setTime(float time) {
        this.time = time;
    }

    public int getDamage() {
        return damage;
    }

    public float getDamageCooldown() {
        return damageCooldown;
    }

    public void setDamageCooldown(float damageCooldown) {
        this.damageCooldown = damageCooldown;
    }
}
