package com.Mohammadreza.UD20.Models;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;

public enum HeroType {

    Shana(4 ,4.0f , GameAssetManager.character1_idle0_tex , GameAssetManager.character1_idle_frames , "Shana"),
    Diamond(7 ,1.0f , GameAssetManager.character2_idle1_tex , GameAssetManager.character2_idle_frames , "Diamond"),
    Scarlet(3, 5.0f , GameAssetManager.character3_idle2_tex , GameAssetManager.character3_idle_frames , "Scarlet" ),
    Lilith(5, 3.0f , GameAssetManager.character4_idle3_tex , GameAssetManager.getCharacter4_idle_frames , "Lilith"),
    Dasher(2 ,10.0f , GameAssetManager.character5_idle0_tex , GameAssetManager.dasherAnimation , "Dasher" ),;






    private final int hp;
    private final float speed;
    private final Texture texture;
    private final Animation<Texture> animation;
    private final String name;
    HeroType(int hp, float speed , Texture texture , Animation<Texture> animation , String name) {
        this.hp = hp;
        this.speed = speed;
        this.texture = texture;
        this.animation = animation;
        this.name = name;
    }

    public int getHp() {
        return hp;
    }

    public Animation<Texture> getAnimation() {
        return animation;
    }

    public Texture getTexture() {
        return texture;
    }

    public float getSpeed() {
        return speed;
    }

    public String getName() {
        return name;
    }
}
