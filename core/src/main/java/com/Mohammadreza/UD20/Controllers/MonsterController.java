package com.Mohammadreza.UD20.Controllers;

import com.Mohammadreza.UD20.Main;
import com.Mohammadreza.UD20.Models.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;

import java.util.ArrayList;
import java.util.Iterator;

public class MonsterController {
    private ArrayList<Monster> monsters = new ArrayList<Monster>();
    private MonsterSeedController monsterSeedController;
    private ArrayList<Explosion> explosions = new ArrayList<>();

    private final float spawnIntervalTentacle = 3f;
    private float elapsedTimeTentacle = 0;

    private float counterToTen = 0;
    private boolean startSpawnWinged = false;
    private int wingedSpawnCount = 0;
    private WorldController worldController;
    private float timeSinceStartGame = 0;
    private Player player;
    private boolean isDragonSpawned = false;


    public ArrayList<Monster> getMonsters() {
        return monsters;
    }

    public MonsterController(WorldController worldController, MonsterSeedController monsterSeedController) {
        this.worldController = worldController;
        this.monsterSeedController = monsterSeedController;
        this.player = worldController.getPlayerController().getPlayer();

    }

    public void update(float delta) {
        Iterator<Monster> monsterIterator = monsters.iterator();
        while (monsterIterator.hasNext()) {
            Monster monster = monsterIterator.next();
            if (monster.isDying()) {
                if(monster instanceof Dragon) {
                    ((Dragon) monster).setIdleTime(0f);
                    ((Dragon) monster).setDashTime(0f);
                }
                deathAnimation(monster);
                if (GameAssetManager.sfxEnabled) {
                    GameAssetManager.monsterDeath.play(1.0f);
                }
                if (GameAssetManager.deathAnimation.isAnimationFinished(monster.getTime())) {
                    float x = (float) Math.random() * worldController.getMapWidth();
                    float y = (float) Math.random() * worldController.getMapHeight();
                    MonsterSeed ms = new MonsterSeed(monster.getPosX(), monster.getPosY());
                    System.out.println("x : " + x + " y : " + y);
                    monsterSeedController.addMonsterSeed(ms);
                    monsterIterator.remove();


                    continue;
                }
            } else {
                if (worldController.getCamera().frustum.boundsInFrustum(
                    monster.getPosX(), monster.getPosY(), 0,
                    monster.getSprite().getWidth() / 2, monster.getSprite().getHeight() / 2, 0)) {
                    monster.getSprite().draw(Main.getBatch());
                    if(monster instanceof  Dragon ){
                        Dragon dragon = (Dragon) monster;
                        if (dragon.isDashing()) {
                            dragon.setIdleTime(0f);
                            dragonAttackAnimation(dragon);
                        } else {
                            dragon.setDashTime(0f);
                            idleAnimation(dragon);
                        }

                    }
                    else {
                        idleAnimation(monster);
                    }
                }

            }
        }
        if(!isDragonSpawned && timeSinceStartGame > App.PreGame.getGameTime() / 2f){
            spawnDragon(worldController.getMapWidth(), worldController.getMapHeight());
            isDragonSpawned = true;
        }
        counterToTen += delta;
        timeSinceStartGame += delta;

        if (timeSinceStartGame >= App.PreGame.getGameTime() / 4) {
            float spawnDelay = (4f * wingedSpawnCount - timeSinceStartGame + 30f) / 30f;

            if (spawnDelay > 0) {
                if (counterToTen >= spawnDelay) {
                    spawnMonsterWinged(worldController.getMapWidth(), worldController.getMapHeight());
                    wingedSpawnCount++;
                    counterToTen = 0;
                }
            } else {
                if (counterToTen >= 10f) {
                    spawnMonsterWinged(worldController.getMapWidth(), worldController.getMapHeight());

                    counterToTen = 0;
                }
            }
        }

        elapsedTimeTentacle += delta;

        if (elapsedTimeTentacle >= 3) {
            spawnMonsterTentacle(worldController.getMapWidth(), worldController.getMapHeight());
            elapsedTimeTentacle = 0;
        }

        updateMonsters(worldController.getPlayerController().getPlayer(), delta);
        for (Monster monster : monsters) {
            if(monster instanceof Dragon){
                Dragon dragon = (Dragon) monster;
                float distance = getDistance(dragon);
                if (distance > 100f) {

                    float dx = player.getPosX() - dragon.getPosX();
                    float dy = player.getPosY() - dragon.getPosY();
                    float dist = (float) Math.sqrt(dx * dx + dy * dy);
                    float speed = 50f;

                    float newX = dragon.getPosX() + (dx / dist) * speed * delta;
                    float newY = dragon.getPosY() + (dy / dist) * speed * delta;

                    dragon.setPosX(newX);
                    dragon.setPosY(newY);
                    dragon.getSprite().setPosition(newX, newY);
                    dragon.getRect().move(newX, newY);
                }

                dragon.dashing(player, delta);
            }
            if (monster instanceof WingedMonster) {
                WingedMonster wm = (WingedMonster) monster;
                Player player = worldController.getPlayerController().getPlayer();
                wm.setShootTimer(wm.getShootTimer() + delta);
                if (wm.getShootTimer() >= 6f) {
                    wm.shooting(player.getPosX(), player.getPosY());
                    wm.setShootTimer(0);
                }
                Iterator<WingedBullet> bulletIterator = wm.getBullets().iterator();
                while (bulletIterator.hasNext()) {
                    WingedBullet bullet = bulletIterator.next();
                    bullet.update(delta);
                    if (worldController.getCamera().frustum.boundsInFrustum(
                        bullet.getPosition().x , bullet.getPosition().y, 0,
                        bullet.getSprite().getWidth() / 2, bullet.getSprite().getHeight() / 2, 0)) {
                        bullet.getSprite().draw(Main.getBatch());

                    }




                    if(getDistance(bullet) <= 1f) {
                        System.out.println("bullet collides with ");
                        player.getHero().decreaseHp(bullet.getDamage());
                        explosions.add(new Explosion(player.getPosX(), player.getPosY()));
                        bulletIterator.remove();
                    }

                        if (bullet.getPosition().x < 0 || bullet.getPosition().x > worldController.getMapWidth()
                            || bullet.getPosition().y < 0 || bullet.getPosition().y > worldController.getMapHeight()) {
                            bulletIterator.remove();
                        }
                }
            }


        }
        Iterator<Explosion> explosionIterator = explosions.iterator();
        while (explosionIterator.hasNext()) {
            Explosion explosion = explosionIterator.next();
            explosion.update(delta);
            explosion.draw(Main.getBatch());
            if (explosion.isFinished()) {
                explosionIterator.remove();
            }
        }

    }

    public void spawnMonsterTentacle(float mapWidth, float mapHeight) {
        int dir = (int) (Math.random() * 4);
        float x = 0, y = 0;

        switch (dir) {
            case 0:
                x = (float) Math.random() * mapWidth;
                y = mapHeight;
                break;
            case 1:
                x = (float) Math.random() * mapWidth;
                y = 0;
                break;
            case 2:
                x = 0;
                y = (float) Math.random() * mapHeight;
                break;
            case 3:
                x = mapWidth;
                y = (float) Math.random() * mapHeight;
                break;
        }

        Tentacle tentacle = new Tentacle(x, y);

        monsters.add(tentacle);
    }

    public void spawnMonsterWinged(float mapWidth, float mapHeight) {
        int dir = (int) (Math.random() * 4);
        float x = 0, y = 0;


        switch (dir) {
            case 0:
                x = (float) Math.random() * mapWidth;
                y = mapHeight;
                break;
            case 1:
                x = (float) Math.random() * mapWidth;
                y = 0;
                break;
            case 2:
                x = 0;
                y = (float) Math.random() * mapHeight;
                break;
            case 3:
                x = mapWidth;
                y = (float) Math.random() * mapHeight;
                break;
        }

        WingedMonster wingedMonster = new WingedMonster(x, y);
        monsters.add(wingedMonster);
    }


    public void updateMonsters(Player player, float deltaTime) {
        for (Monster monster : monsters) {
            if (monster.isDying()) {
                continue;
            }
            if(monster instanceof Dragon){
                continue;
            }
            float dx = player.getPosX() - monster.getPosX();
            float dy = player.getPosY() - monster.getPosY();
            float distance = (float) Math.sqrt(dx * dx + dy * dy);

            if (distance != 0) {
                float speed = 50f;
                float newY = monster.getPosY() + (dy / distance) * speed * deltaTime;
                float newX = monster.getPosX() + (dx / distance) * speed * deltaTime;
                monster.setPosX(newX);
                monster.getSprite().setX(newX);
                monster.getRect().move(newX, newY);
                monster.setPosY(newY);
                monster.getSprite().setY(newY);
            }
        }
    }

    public void idleAnimation(Monster monster) {

        Animation<Texture> animation = monster.getAnimation();
        monster.getSprite().setRegion(animation.getKeyFrame(monster.getTime()));
        if (!animation.isAnimationFinished(monster.getTime())) {
            monster.setTime(monster.getTime() + Gdx.graphics.getDeltaTime());
        } else {
            monster.setTime(0);
        }



    }
    public void dragonAttackAnimation(Dragon monster) {
        Animation<Texture> animation = monster.getAttackAnimation();
        Texture frame = animation.getKeyFrame(monster.getDashTime());
        Main.getBatch().draw(frame, monster.getPosX(), monster.getPosY());

        if (!animation.isAnimationFinished(monster.getDashTime())) {
            monster.setDashTime(monster.getDashTime() + Gdx.graphics.getDeltaTime());
        } else {
            monster.setDashTime(0f);
        }

        animation.setPlayMode(Animation.PlayMode.LOOP);
    }


    public void deathAnimation(Monster monster) {
        Animation<Texture> animation = GameAssetManager.deathAnimation;
        float time;

        if (monster instanceof Dragon) {
            Dragon dragon = (Dragon) monster;
            time = dragon.getDeathTime();
            Texture frame = animation.getKeyFrame(time);
            Main.getBatch().draw(frame, dragon.getPosX(), dragon.getPosY());

            if (!animation.isAnimationFinished(time)) {
                dragon.setDeathTime(time + Gdx.graphics.getDeltaTime());
            } else {
                dragon.setDead(true);
            }
        } else {
            time = monster.getTime();
            Texture frame = animation.getKeyFrame(time);
            Main.getBatch().draw(frame, monster.getPosX(), monster.getPosY());

            if (!animation.isAnimationFinished(time)) {
                monster.setTime(time + Gdx.graphics.getDeltaTime());
            } else {
                monster.setDead(true);
            }
        }

        animation.setPlayMode(Animation.PlayMode.NORMAL);
    }


    public float getDistance(Monster ms) {
        float dx = player.getPosX() - ms.getPosX();
        float dy = player.getPosY() - ms.getPosY();
        return (float) Math.sqrt(dx * dx + dy * dy);
    }

    public float getDistance(WingedBullet ms) {
        float dx = player.getPosX() - ms.getPosition().x;
        float dy = player.getPosY() - ms.getPosition().y;
        return (float) Math.sqrt(dx * dx + dy * dy);
    }

    public void spawnDragon(float mapWidth, float mapHeight) {
        float x = (float) Math.random() * mapWidth;
        float y = (float) Math.random() * mapHeight;
        Dragon dragon = new Dragon(x, y);
        monsters.add(dragon);
    }

    public void idleAnimation(Dragon monster) {
        Animation<Texture> animation = monster.getAnimation();
        Texture frame = animation.getKeyFrame(monster.getIdleTime());
        Main.getBatch().draw(frame, monster.getPosX(), monster.getPosY());

        if (!animation.isAnimationFinished(monster.getIdleTime())) {
            monster.setIdleTime(monster.getIdleTime() + Gdx.graphics.getDeltaTime());
        } else {
            monster.setIdleTime(0f);
        }

        animation.setPlayMode(Animation.PlayMode.LOOP);
    }

}










