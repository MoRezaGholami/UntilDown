package com.Mohammadreza.UD20.Models;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Dragon extends Monster{
    private int hp = 400;
    private final Texture texture = GameAssetManager.DragonTex1;
    private final Sprite sprite = new Sprite(texture);
    private final Animation<Texture> idleAnimation = GameAssetManager.dragonIdleAnimation;
    private final Animation<Texture> attackAnimation = GameAssetManager.dragonAttackAnimation;
    private float dashCooldown = 10f;
    private float dashTime = 0f;
    private boolean isDashing = false;
    private float idleTime = 0f;

    private float deathTime = 0f;

    public Dragon(float x, float y) {
        super(x, y);
    }


    @Override
    public int getHp() {
        return hp;
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
    public Animation<Texture> getAnimation(){
        return idleAnimation;
    }

    public Animation<Texture> getAttackAnimation() {
        return attackAnimation;
    }

    public void dashing(Player player, float delta) {
        dashTime += delta;

        if (dashTime >= dashCooldown) {
            dashTime = 0f;
            isDashing = true;
        }

        if (isDashing) {
            float dx = player.getPosX() - getPosX();
            float dy = player.getPosY() - getPosY();
            float distance = (float) Math.sqrt(dx * dx + dy * dy);

            if (distance != 0) {
                float dashSpeed = 300f;
                float newX = getPosX() + (dx / distance) * dashSpeed * delta;
                float newY = getPosY() + (dy / distance) * dashSpeed * delta;

                setPosX(newX);
                setPosY(newY);
                getSprite().setX(newX);
                getSprite().setY(newY);
                getRect().move(newX, newY);
            }


            if (distance < 50f) {
                isDashing = false;
            }
        }
    }

    public float getDashCooldown() {
        return dashCooldown;
    }



    public boolean isDashing() {
        return isDashing;
    }

    public void setIsDashing(boolean isDashing) {
        this.isDashing = isDashing;
    }

    public float getIdleTime() { return idleTime; }
    public void setIdleTime(float t) { idleTime = t; }

    public float getDashTime() { return dashTime; }
    public void setDashTime(float t) { dashTime = t; }

    public float getDeathTime() { return deathTime; }
    public void setDeathTime(float t) { deathTime = t; }

    public void resetTimers() {
        idleTime = 0;
        dashTime = 0;
        deathTime = 0;
    }
}
