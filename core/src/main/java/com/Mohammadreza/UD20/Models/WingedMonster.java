package com.Mohammadreza.UD20.Models;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;

import java.util.ArrayList;

public class WingedMonster extends Monster{
    private final Texture texture = GameAssetManager.wingedMonsterTex0;
    private final Sprite sprite = new Sprite(texture);
    private final Animation animation = GameAssetManager.wingedMonsterAnimation;
    private final Texture bulletTExture = GameAssetManager.wingedBulletTex;
    private ArrayList<WingedBullet> bullets = new ArrayList<>();
    private float shootTimer = 0f;
    private int hp = 50;
    public WingedMonster(float x, float y) {
        super(x , y);
    }

    public void shooting(float targetX, float targetY) {
        WingedBullet wb = new WingedBullet(getPosX(), getPosY(), targetX, targetY);
        bullets.add(wb);
    }

    public Texture getBulletTExture() {
        return bulletTExture;
    }

    @Override
    public Sprite getSprite() {
        return sprite;
    }

    @Override
    public Animation getAnimation() {
        return animation;
    }

    @Override
    public Texture getTexture() {
        return texture;
    }

    @Override
    public int getHp() {
        return hp;
    }

    @Override
    public void decreaseHp(int dmg) {
        hp -= dmg;
    }

    public ArrayList<WingedBullet> getBullets() {
        return bullets;
    }

    public float getShootTimer(){
        return shootTimer;
    }

    public void setShootTimer(float timer) {
        this.shootTimer = timer;
    }
}
