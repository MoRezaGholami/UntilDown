package com.Mohammadreza.UD20.Models;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Tentacle extends Monster {
    private final Texture texture = GameAssetManager.brainMonsterTex;
    private final Sprite sprite = new Sprite(texture);
    private final Animation<Texture> animation = GameAssetManager.brainMonsterAnimation;
    private int hp = 25;
    public Tentacle(float posX, float posY) {
        super(posX, posY);
    }


    @Override
    public Animation<Texture> getAnimation() {
        return animation;
    }


    @Override
    public Texture getTexture() {
        return texture;
    }

    @Override
    public Sprite getSprite() {
        return sprite;
    }

    @Override
    public int getHp() {
        return hp;
    }

    @Override
    public void decreaseHp(int dmg) {
        hp -= dmg;
    }


}
