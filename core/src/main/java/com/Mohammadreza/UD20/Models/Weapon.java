package com.Mohammadreza.UD20.Models;

import com.Mohammadreza.UD20.Controllers.MonsterController;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.Texture;

public class Weapon {
    private Texture smgTexture ;
    private Sprite smgSprite ;
    private int ammo;
    private WeaponType type;
    private boolean isReloading = false;
    private  float reloadTime = 0f;
    private float reloadAnimation = 0f;
    private Player player;
    public Weapon(WeaponType type){
        this.type = type;
        this.smgTexture = type.getTexture();
        this.smgSprite = new Sprite(this.smgTexture);
        smgSprite.setX((float) Gdx.graphics.getWidth() / 2 );
        smgSprite.setY((float) Gdx.graphics.getHeight() / 2);
        smgSprite.setSize(30,30);
        this.ammo = type.getMaxAmmo();

    }



    public Sprite getSmgSprite() {
        return smgSprite;
    }

    public int getAmmo() {
        return ammo;
    }

    public void setAmmo(int ammo){
        this.ammo = ammo;
    }

    public WeaponType getType() {
        return type;
    }

    public void setType(WeaponType type) {
        this.type = type;
        this.smgTexture = type.getTexture();
        this.smgSprite = new Sprite(this.smgTexture);
    }

    public void decreaseAmmo(){
        this.ammo--;
    }

    public void startReloading(){
        if(!isReloading){
            isReloading = true;
            reloadTime = 0;
            if(GameAssetManager.sfxEnabled){
                GameAssetManager.reloadSFX.play(1.0f);
            }
        }
    }

    public void updateReloading(float delta){
        if(isReloading){
            reloadTime += delta;
            reloadAnimation += delta;


            if(reloadTime >= type.getTimeReload()){
                int o = 0;
                if(App.PrePlayer.isIncreaseAmmo()){
                    o = 5;
                }
                this.ammo = type.getMaxAmmo() + o;
                reloadTime = 0;
                isReloading = false;
                reloadAnimation = 0;

            }
        }
    }

    public boolean isReloading(){
        return isReloading;
    }

    public void setReloading(boolean isReloading){
        this.isReloading = isReloading;
    }

    public float getReloadAnimation() {
        return reloadAnimation;
    }

    public boolean canShoot(){
        return ammo > 0 && !isReloading;
    }
}
