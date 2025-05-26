package com.Mohammadreza.UD20.Models;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class Bullet {
    private Texture texture = new Texture(GameAssetManager.getBullet());
    private Sprite sprite = new Sprite(texture);
    private int damage = 5;
    private Vector2 position;
    private Vector2 direction;
    private float speed = 500f; // سرعت گلوله (پیکسل بر ثانیه)
    private CollisionRect rect;

    public Bullet(float startX, float startY, float targetX, float targetY) {
        sprite.setSize(20, 20);

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

    public Sprite getSprite() {
        return sprite;
    }

    public int getDamage() {
        return damage;
    }

    public CollisionRect getRect() {
        return rect;
    }
}

