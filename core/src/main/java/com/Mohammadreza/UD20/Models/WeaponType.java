package com.Mohammadreza.UD20.Models;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;

public enum WeaponType {
    Revolver(20 , 1, 1,6 , GameAssetManager.revolverTexture , GameAssetManager.revolverReloadAnimation),
    Shotgun(10 , 4, 1, 4 , GameAssetManager.smgTexture , GameAssetManager.smgReloadAnimation),
    SMGDual(8 , 1, 2  , 24 , GameAssetManager.smgDoubleTex, GameAssetManager.smgReloadAnimation),;

    private final int Damage;
    private final int Projectile;
    private final float timeReload;
    private final int maxAmmo;
    private final Texture texture;
    private final Animation<Texture> reloadAnimation;

    WeaponType(int Damage, int Projectile, int timeReload, int maxAmmo , Texture texture , Animation<Texture> reloadAnimation) {
        this.Damage = Damage;
        this.Projectile = Projectile;
        this.timeReload = timeReload;
        this.maxAmmo = maxAmmo;
        this.texture = texture;
        this.reloadAnimation = reloadAnimation;


    }

    public int getDamage() {
        return Damage;
    }

    public int getMaxAmmo() {
        return maxAmmo;
    }

    public float getTimeReload() {
        return timeReload;
    }

    public int getProjectile() {
        return Projectile;
    }

    public Texture getTexture() {
        return texture;
    }

    public Animation<Texture> getReloadAnimation() {
        return reloadAnimation;
    }


}
