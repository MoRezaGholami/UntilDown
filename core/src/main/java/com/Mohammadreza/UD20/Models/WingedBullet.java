package com.Mohammadreza.UD20.Models;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class WingedBullet {
    private final Texture texture = GameAssetManager.wingedBulletTex;
    private final Sprite sprite = new Sprite(texture);
    private final int damage = 1;
    private final float speed = 250f;
    private Vector2 position;
    private Vector2 direction;
    private CollisionRect rect;


    public WingedBullet(float startX, float startY, float targetX, float targetY) {
        sprite.setSize(20 , 20);

        this.position = new Vector2(startX, startY);
        this.direction = new Vector2(targetX - startX, targetY - startY).nor();

        sprite.setPosition(position.x, position.y);
        rect = new CollisionRect(position.x, position.y, sprite.getWidth(), sprite.getHeight());
    }

    public void update(float deltaTime) {
        Vector2 velocity = new Vector2(direction).scl(speed * deltaTime);
        position.add(velocity);
        sprite.setPosition(position.x, position.y);
        rect.move(position.x, position.y);
    }

    public Texture getTexture() {
        return texture;
    }

    public CollisionRect getRect() {
        return rect;
    }

    public Vector2 getDirection() {
        return direction;
    }

    public Vector2 getPosition() {
        return position;
    }

    public float getSpeed() {
        return speed;
    }

    public int getDamage() {
        return damage;
    }

    public Sprite getSprite() {
        return sprite;
    }
}
