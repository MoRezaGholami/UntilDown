package com.Mohammadreza.UD20.Controllers;
import com.Mohammadreza.UD20.Controllers.MenuControllers.EndGameMenuController;
import com.Mohammadreza.UD20.Controllers.MenuControllers.GameMenuController;
import com.Mohammadreza.UD20.Controllers.MenuControllers.PauseMenuController;
import com.Mohammadreza.UD20.Main;
import com.Mohammadreza.UD20.Models.App;
import com.Mohammadreza.UD20.Models.Monster;
import com.Mohammadreza.UD20.Models.Player;
import com.Mohammadreza.UD20.Models.GameAssetManager;
import com.Mohammadreza.UD20.Views.EndGameMenu;
import com.Mohammadreza.UD20.Views.PauseMenu;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;

public class PlayerController {
    private Player player;
    private TreeController treeController;
    private float doubleSpeedTime = 0;
    private GameMenuController gameMenuController;
    private MonsterController monsterController;
    private WorldController worldController;

    public PlayerController(Player player , GameMenuController gameMenuController  ) {
        this.player = player;
        this.gameMenuController = gameMenuController;

    }

    public void setWorldController(WorldController worldController) {
        this.worldController = worldController;
    }

    public void setMonsterController(MonsterController monsterController) {
        this.monsterController = monsterController;
    }

    public void update(float delta) {

        handlePlayerInput(delta);

        if(player.isPlayerIdle()){
            idleAnimation(delta);
        }

        for(Monster monster : monsterController.getMonsters()){

            if(getDistance(monster) <= 0.5f ) {
                monster.DamageCooldown = monster.DamageCooldown + delta;
                if (monster.DamageCooldown >= 3f) {
                    player.getHero().decreaseHp(monster.getDamage());
                    monster.DamageCooldown = 0.0f;
                }

            }
        }


        draw();
    }



    public void handlePlayerInput(float delta) {
        int x = 1;
        if(player.isDoubleSpeed()){
            x = 2;
            doubleSpeedTime += delta;
            if(doubleSpeedTime > 10){
                doubleSpeedTime = 0;
                player.setDoubleSpeed(false);
            }

        }
        // up
        if (Gdx.input.isKeyPressed(KeyBinding.MOVE_UP)) {
            player.setPosY(player.getPosY() + player.getSpeed() * x);
        }
        // right
        if (Gdx.input.isKeyPressed(KeyBinding.MOVE_RIGHT)){
            player.setPosX(player.getPosX() + player.getSpeed() * x);
        }
        // down
        if (Gdx.input.isKeyPressed(KeyBinding.MOVE_DOWN)){
            player.setPosY(player.getPosY() - player.getSpeed() * x);
        }
        // left
        if (Gdx.input.isKeyPressed(KeyBinding.MOVE_LEFT)){
            player.setPosX(player.getPosX() - player.getSpeed() * x);

        }
        if(Gdx.input.isKeyPressed(KeyBinding.MOVE_RIGHT) && Gdx.input.isKeyPressed(KeyBinding.MOVE_UP)){
            player.setPosY(player.getPosY() + player.getSpeed() * x);
            player.setPosX(player.getPosX() - player.getSpeed() * x);
        }

        if(Gdx.input.isKeyPressed(KeyBinding.MOVE_LEFT) && Gdx.input.isKeyPressed(KeyBinding.MOVE_UP)){
            player.setPosY(player.getPosY() + player.getSpeed() * x);
            player.setPosX(player.getPosX() + player.getSpeed() * x);
        }

        if(Gdx.input.isKeyPressed(KeyBinding.MOVE_DOWN) && Gdx.input.isKeyPressed(KeyBinding.MOVE_LEFT)){
            player.setPosY(player.getPosY() - player.getSpeed() * x);
            player.setPosX(player.getPosX() - player.getSpeed() * x);
        }


        if(Gdx.input.isKeyPressed(KeyBinding.MOVE_DOWN) && Gdx.input.isKeyPressed(KeyBinding.MOVE_RIGHT)){
            player.setPosY(player.getPosY() - player.getSpeed() * x);
            player.setPosX(player.getPosX() + player.getSpeed() * x);
        }

        if(Gdx.input.isKeyPressed(Input.Keys.R)){
            System.out.println("R pressed");
            player.getWeapon().startReloading();
            System.out.println("isReloading : " + player.getWeapon().isReloading());
        }

        if(Gdx.input.isKeyPressed(Input.Keys.SPACE)){
            gameMenuController.setPaused(true);
            PauseMenuController pauseMenuController = new PauseMenuController();
            PauseMenu pauseMenu = new PauseMenu(pauseMenuController , gameMenuController , GameAssetManager.getSkin());
            Main.getMain().setScreen(pauseMenu);
        }

        if(Gdx.input.isKeyPressed(Input.Keys.T)){
            gameMenuController.setRemainingTime(gameMenuController.getRemainingTime() - 60);
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.L)){
            App.PrePlayer.getHero().increaseLevel();
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.B)){
            monsterController.spawnDragon(worldController.getMapWidth(), worldController.getMapHeight());
        }

        if(Gdx.input.isKeyPressed(Input.Keys.H)){
            if(player.getHero().getHp() < player.getMaximumHealth()) {
                int o = 0;
                if(player.isIncreaseHealth()){
                    o = 1;
                }
                App.PrePlayer.getHero().setHp(player.getMaximumHealth() + o);
            }
        }

        if(Gdx.input.isKeyPressed(Input.Keys.X)){
            App.PrePlayer.getWeapon().setAmmo(30);
        }




    }

    public void idleAnimation(float delta){
        Animation<Texture> animation = player.getHero().getType().getAnimation();

        player.setTime(player.getTime() + delta);
        animation.setPlayMode(Animation.PlayMode.LOOP);
    }


    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void draw(){
        Animation<Texture> animation = player.getHero().getType().getAnimation();
        Texture currentFrame = animation.getKeyFrame(player.getTime(), true);
        Main.getBatch().draw(currentFrame, player.getPosX(), player.getPosY());
    }


    public float getDistance(Monster ms) {
        float dx = player.getPosX() - ms.getPosX();
        float dy = player.getPosY() - ms.getPosY();
        return (float) Math.sqrt(dx*dx + dy*dy);
    }
}
