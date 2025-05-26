package com.Mohammadreza.UD20.Controllers;

import com.Mohammadreza.UD20.Main;
import com.Mohammadreza.UD20.Models.Bullet;
import com.Mohammadreza.UD20.Models.Monster;
import com.Mohammadreza.UD20.Models.Player;
import com.Mohammadreza.UD20.Models.Weapon;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import java.util.ArrayList;
import java.util.Iterator;

public class WeaponController {
    private Weapon weapon;
    private WorldController worldController;
    private ArrayList<Bullet> bullets = new ArrayList<>();
    private MonsterController monsterController;
    private Player player;
    private OrthographicCamera camera;
    private float moreDamageTime  = 0f;


    public WeaponController(Weapon weapon, MonsterController monsterController, WorldController worldController) {
        this.weapon = weapon;
        this.monsterController = monsterController;
        this.worldController = worldController;
        this.player = worldController.getPlayerController().getPlayer();
        this.camera = worldController.getCamera();

    }

    public void update(float deltaTime) {

        weapon.updateReloading(deltaTime);
        Sprite sprite = weapon.getSmgSprite();
        sprite.setPosition(player.getPosX(), player.getPosY());
        if(weapon.isReloading()){
            Animation<Texture> animation = weapon.getType().getReloadAnimation();
            Texture currentTexture = animation.getKeyFrame(weapon.getReloadAnimation() , true);
            sprite.setRegion(currentTexture);
        }
        else{
            sprite.setRegion(weapon.getType().getTexture());
        }
        //System.out.println("b : " + weapon.getAmmo());

        weapon.getSmgSprite().setPosition(player.getPosX(), player.getPosY());
        weapon.getSmgSprite().draw(Main.getBatch());


        updateBullets(deltaTime);
    }

    public void handleWeaponRotation(int x, int y) {
        Vector3 mousePos = new Vector3(x, y, 0);
        camera.unproject(mousePos);

        Sprite weaponSprite = weapon.getSmgSprite();
        float weaponCenterX = player.getPosX();
        float weaponCenterY = player.getPosY();

        float angleRad = (float) Math.atan2(mousePos.y - weaponCenterY, mousePos.x - weaponCenterX);
        float angleDeg = angleRad * MathUtils.radiansToDegrees;

        weaponSprite.setRotation(angleDeg);
    }


    public void handleWeaponShoot(int x, int y) {
        if (!weapon.canShoot()) return;

        Vector3 mousePos = new Vector3(x, y, 0);
        camera.unproject(mousePos);

        float startX = player.getPosX();
        float startY = player.getPosY();

        int count = player.getWeapon().getType().getProjectile();
        if (player.isIncreaseProjectile()) count++;


        float baseAngle = (float) Math.atan2(mousePos.y - startY, mousePos.x - startX);
        float spread = 15f;


        float spreadRad = (float) Math.toRadians(spread);


        float startAngle = baseAngle - (spreadRad * (count - 1) / 2);

        for (int i = 0; i < count; i++) {
            float angle = startAngle + i * spreadRad;


            float targetX = startX + (float) Math.cos(angle) * 1000;
            float targetY = startY + (float) Math.sin(angle) * 1000;

            bullets.add(new Bullet(startX, startY, targetX, targetY));
        }

        weapon.decreaseAmmo();
    }



    public void updateBullets(float deltaTime) {
        Iterator<Bullet> bulletIterator = bullets.iterator();
        float x = 1f;
        if (player.isMoreDamage()) {
            moreDamageTime += deltaTime;
            x = 1.25f;
            if (moreDamageTime > 10) {
                player.setMoreDamage(false);
                moreDamageTime = 0;
            }
        }

        while (bulletIterator.hasNext()) {
            Bullet b = bulletIterator.next();
            b.update(deltaTime);
            b.getSprite().draw(Main.getBatch());

            for (Monster monster : monsterController.getMonsters()) {
                if (b.getRect().collidesWith(monster.getRect())) {
                    monster.decreaseHp((int) (weapon.getType().getDamage() * x));
                    if (monster.getHp() <= 0 && !monster.isDying()) {
                        monsterController.deathAnimation(monster);
                        player.addKill();
                        monster.setDying(true);
                        monster.setTime(0);
                    }
                    bulletIterator.remove();
                    break;
                }
            }
        }
    }


    public Weapon getWeapon() {
        return weapon;
    }

    public ArrayList<Bullet> getBullets() {
        return bullets;
    }

    public MonsterController getMonsterController() {
        return monsterController;
    }
}

