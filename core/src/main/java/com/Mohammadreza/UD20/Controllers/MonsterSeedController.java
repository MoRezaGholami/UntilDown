package com.Mohammadreza.UD20.Controllers;

import com.Mohammadreza.UD20.Main;
import com.Mohammadreza.UD20.Models.Monster;
import com.Mohammadreza.UD20.Models.MonsterSeed;
import com.Mohammadreza.UD20.Models.Player;
import com.badlogic.gdx.Gdx;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;

public class MonsterSeedController {
    private final ArrayList<MonsterSeed> monsterSeeds = new ArrayList<>();
    private final WorldController worldController;
    private Player player ;

    public MonsterSeedController(WorldController worldController) {
        this.worldController = worldController;
        this.player = worldController.getPlayerController().getPlayer();
    }

    public void update(float delta) {
        Iterator<MonsterSeed> iterator = monsterSeeds.iterator();

        while(iterator.hasNext()){
            MonsterSeed ms = iterator.next();
            if(getDistance(ms) <= 0.5f){
                System.out.println("collides");
                player.getHero().increaseXp(3);
                ms.setTaken(true);
                iterator.remove();
            }
        }
        updateMonsterSeeds(player , delta);
        for(MonsterSeed ms : monsterSeeds){



            ms.getSprite().draw(Main.getBatch());
        }


        //System.out.println(player.getHero().getXp());

    }

    public void addMonsterSeed(MonsterSeed monsterSeed) {
        monsterSeeds.add(monsterSeed);
    }

    public ArrayList<MonsterSeed> getMonsterSeeds() {
        return monsterSeeds;
    }

    public WorldController getWorldController() {
        return worldController;
    }

    public void updateMonsterSeeds(Player player , float deltaTime) {
        for (MonsterSeed ms : monsterSeeds) {

            float dx = (float) Gdx.graphics.getWidth() / 2 - ms.getPosX();
            float dy = (float) Gdx.graphics.getHeight() / 2 - ms.getPosY();
            float distance = (float) Math.sqrt(dx*dx + dy*dy);

            if (distance != 0) {
                float speed = 1000f;
                float newY = ms.getPosY() + (dy / distance) * speed * deltaTime;
                float newX = ms.getPosX() + (dx / distance) * speed * deltaTime;
                ms.setPosX(newX);
                ms.getSprite().setX(newX);

                ms.setPosY(newY);
                ms.getSprite().setY(newY);
                ms.getRect().move(newX ,newY );
                //System.out.println("Seed moving to " + newX + ", " + newY);

            }
        }
    }

    public float getDistance(MonsterSeed ms )  {
        float dx = (float) Gdx.graphics.getWidth() / 2 - ms.getPosX();
        float dy = (float) Gdx.graphics.getHeight() / 2 - ms.getPosY();
        return (float) Math.sqrt(dx*dx + dy*dy);
    }
}
