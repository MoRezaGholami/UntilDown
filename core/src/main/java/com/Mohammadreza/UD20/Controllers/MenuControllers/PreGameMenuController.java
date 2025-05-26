package com.Mohammadreza.UD20.Controllers.MenuControllers;

import com.Mohammadreza.UD20.Main;
import com.Mohammadreza.UD20.Models.*;
import com.Mohammadreza.UD20.Views.GameMenu;
import com.Mohammadreza.UD20.Views.MainMenu;
import com.Mohammadreza.UD20.Views.PreGameMenu;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.utils.Timer;

public class PreGameMenuController {
    private PreGameMenu view;
    private PreGame pregame;


    public void setView(PreGameMenu view) {
        this.view = view;
        this.pregame = new PreGame(App.PrePlayer , 1200);
        App.setPreGame(pregame);
    }

    public void handleChangeHero(){
        if(view != null){
            String hero = view.getSelectHero().getSelected().toString();
            switch (hero){
                case "shana":
                    App.PrePlayer.setHero(new Hero(HeroType.Shana , App.PrePlayer));
                    break;
                case "diamond" :
                    App.PrePlayer.setHero(new Hero(HeroType.Diamond , App.PrePlayer));
                    break;
                case "dasher"  :
                    App.PrePlayer.setHero(new Hero(HeroType.Dasher , App.PrePlayer));
                    break;
                case "scarlet"  :
                    App.PrePlayer.setHero(new Hero(HeroType.Scarlet , App.PrePlayer));
                    break;
                case "lilith" :
                    App.PrePlayer.setHero(new Hero(HeroType.Lilith , App.PrePlayer));
                    break;
            }
            Dialog successfullyRegister = new Dialog("", GameAssetManager.getSkin());
            successfullyRegister.text("you hero has been changed");
            successfullyRegister.getColor().a = 0;
            successfullyRegister.show(view.getStage());
            float x = (Gdx.graphics.getWidth() - successfullyRegister.getWidth()) / 2f;
            float y = 50;
            successfullyRegister.setPosition(x, y);
            successfullyRegister.addAction(Actions.fadeIn(1f));






            Timer.schedule(new Timer.Task() {
                @Override
                public void run() {
                    successfullyRegister.addAction(Actions.sequence(
                        Actions.fadeOut(1f),
                        Actions.run(successfullyRegister::hide)
                    ));
                }
            }, 2);
        }
    }

    public void handleChangeWeapon(){
        if(view != null){
            String weapon = view.getSelectWeapon().getSelected().toString();
            switch(weapon){
                case "smg" :
                    App.PrePlayer.setWeapon(new Weapon(WeaponType.Revolver));
                    break;
                case "smgDual" :
                    App.PrePlayer.setWeapon(new Weapon(WeaponType.SMGDual));
                    break;
                case "shotgun" :
                    App.PrePlayer.setWeapon(new Weapon(WeaponType.Shotgun));
                    break;
            }
            Dialog successfullyRegister = new Dialog("", GameAssetManager.getSkin());
            successfullyRegister.text("you weapon has been changed");
            successfullyRegister.getColor().a = 0;
            successfullyRegister.show(view.getStage());
            float x = (Gdx.graphics.getWidth() - successfullyRegister.getWidth()) / 2f;
            float y = 50;
            successfullyRegister.setPosition(x, y);
            successfullyRegister.addAction(Actions.fadeIn(1f));






            Timer.schedule(new Timer.Task() {
                @Override
                public void run() {
                    successfullyRegister.addAction(Actions.sequence(
                        Actions.fadeOut(1f),
                        Actions.run(successfullyRegister::hide)
                    ));
                }
            }, 2);

        }
    }


    public void handleTimerGame(){
        if(view != null){
            String time = view.getGameTimer().getSelected().toString();
            switch(time){
                case "20" :
                    pregame.setGameTime(1200);
                    break;
                case "10" :
                    pregame.setGameTime(600);
                    break;
                case "5" :
                    pregame.setGameTime(300);
                    break;
                case "2" :
                    pregame.setGameTime(120);
                    break;
            }
        }
    }



    public void Play(){
        if(view != null){
            if(view.getPlayButton().isChecked()){
                Dialog successfullyRegister = new Dialog("", GameAssetManager.getSkin());
                successfullyRegister.text("you start the game");
                successfullyRegister.getColor().a = 0;
                successfullyRegister.show(view.getStage());
                float x = (Gdx.graphics.getWidth() - successfullyRegister.getWidth()) / 2f;
                float y = 50;
                successfullyRegister.setPosition(x, y);
                successfullyRegister.addAction(Actions.fadeIn(1f));

                Screen currentScreen = Main.getMain().getScreen();

                Timer.schedule(new Timer.Task() {
                    @Override
                    public void run() {
                        successfullyRegister.addAction(Actions.sequence(
                            Actions.fadeOut(1f),
                            Actions.run(() -> {
                                successfullyRegister.hide();
                                GameMenuController controller = new GameMenuController();
                                GameMenu gameMenu = new GameMenu(controller , GameAssetManager.getSkin());
                                Main.getMain().setScreen(gameMenu);
                                currentScreen.dispose();
                            })
                        ));
                    }
                }, 2);
            }
        }
    }

    public void Back(){
        Dialog successfullyRegister = new Dialog("", GameAssetManager.getSkin());
        successfullyRegister.text("you back to the main menu");
        successfullyRegister.getColor().a = 0;
        successfullyRegister.show(view.getStage());
        float x = (Gdx.graphics.getWidth() - successfullyRegister.getWidth()) / 2f;
        float y = 50;
        successfullyRegister.setPosition(x, y);
        successfullyRegister.addAction(Actions.fadeIn(1f));

        Screen currentScreen = Main.getMain().getScreen();

        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                successfullyRegister.addAction(Actions.sequence(
                    Actions.fadeOut(1f),
                    Actions.run(() -> {
                        successfullyRegister.hide();
                        MainMenuController controller = new MainMenuController();
                        MainMenu mainMenu = new MainMenu(controller , GameAssetManager.getSkin());
                        Main.getMain().setScreen(mainMenu);
                        currentScreen.dispose();
                    })
                ));
            }
        }, 2);
    }
}
