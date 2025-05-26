package com.Mohammadreza.UD20.Controllers.MenuControllers;

import com.Mohammadreza.UD20.Main;
import com.Mohammadreza.UD20.Models.App;
import com.Mohammadreza.UD20.Models.GameAssetManager;
import com.Mohammadreza.UD20.Views.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.utils.Timer;

public class MainMenuController {
    private MainMenu view;
    public void setView(MainMenu mainMenu) {
        this.view = mainMenu;
    }


    public void gotToSettingMenu(){
        if(view != null){
            if(view.getGoToSettingsButton().isChecked()){
                Dialog successfullyRegister = new Dialog("", GameAssetManager.getSkin());
                successfullyRegister.text("you enter settings menu");
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

                                SettingsMenuController settingsMenuController = new SettingsMenuController();
                                SettingsMenu settingsMenu = new SettingsMenu(settingsMenuController , GameAssetManager.getSkin());
                                Main.getMain().setScreen(settingsMenu);

                                currentScreen.dispose();

                            })
                        ));
                    }
                }, 2);
            }
        }
    }

    public void goToProfileMenu(){
        if(view != null){
            if(view.getGoToProfileButton().isChecked()){
                Dialog successfullyRegister = new Dialog("", GameAssetManager.getSkin());
                successfullyRegister.text("you enter profile menu");
                successfullyRegister.pack();
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

                                ProfileMenuController profileMenuController = new ProfileMenuController();
                                ProfileMenu profileMenu = new ProfileMenu(profileMenuController , GameAssetManager.getSkin());
                                Main.getMain().setScreen(profileMenu);
                                currentScreen.dispose();

                            })
                        ));
                    }
                }, 2);

            }
        }
    }


    public void goToPreGameMenu(){
        if(view != null){
            if(view.getGoToPreGameButton().isChecked()){
                Dialog successfullyRegister = new Dialog("", GameAssetManager.getSkin());
                successfullyRegister.text("You enter pre-game menu");
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
                                PreGameMenuController preGameMenuController = new PreGameMenuController();
                                PreGameMenu preGameMenu = new PreGameMenu(preGameMenuController , GameAssetManager.getSkin());
                                Main.getMain().setScreen(preGameMenu);
                                currentScreen.dispose();
                            })
                        ));
                    }
                }, 2);
            }
        }
    }


    public void goToTalentMenu(){
        if(view != null){
            if(view.getGoToTalentsButton().isChecked()){
                Dialog goToTalent = new Dialog("", GameAssetManager.getSkin());
                goToTalent.text("you enter talent menu");
                goToTalent.getColor().a = 0;
                goToTalent.show(view.getStage());
                float x = (Gdx.graphics.getWidth() - goToTalent.getWidth()) / 2f;
                float y = 50;
                goToTalent.setPosition(x, y);
                goToTalent.addAction(Actions.fadeIn(1f));

                Screen currentScreen = Main.getMain().getScreen();
                Timer.schedule(new Timer.Task() {
                    @Override
                    public void run() {
                        goToTalent.addAction(Actions.sequence(
                            Actions.fadeOut(1f),
                            Actions.run(() -> {
                                goToTalent.hide();

                                TalentMenuController controller = new TalentMenuController();
                                TalentMenu talentMenu = new TalentMenu(controller, GameAssetManager.getSkin());
                                Main.getMain().setScreen(talentMenu);
                                currentScreen.dispose();

                            })
                        ));
                    }
                }, 2);


            }
        }
    }


    public void goToScoreBoardMenu(){
        if(view != null){
            Dialog goToScoreBoard = new Dialog("", GameAssetManager.getSkin());
            goToScoreBoard.text("you enter score board menu");
            goToScoreBoard.getColor().a = 0;
            goToScoreBoard.show(view.getStage());
            float x = (Gdx.graphics.getWidth() - goToScoreBoard.getWidth()) / 2f;
            float y = 50;
            goToScoreBoard.setPosition(x, y);
            goToScoreBoard.addAction(Actions.fadeIn(1f));

            Screen currentScreen = Main.getMain().getScreen();

            Timer.schedule(new Timer.Task() {
                @Override
                public void run() {
                    goToScoreBoard.addAction(Actions.sequence(
                        Actions.fadeOut(1f),
                        Actions.run(() -> {
                            goToScoreBoard.hide();

                            ScoreBoardMenuController controller = new ScoreBoardMenuController();
                            ScoreBoardMenu scoreBoardMenu = new ScoreBoardMenu(controller , GameAssetManager.getSkin() , App.players);
                            Main.getMain().setScreen(scoreBoardMenu);

                            currentScreen.dispose();

                        })
                    ));
                }
            }, 2);
        }
    }

    public void logout(){
        if(view != null){
            Screen currentScreen = Main.getMain().getScreen();
            SignUpMenuController signUpMenuController = new SignUpMenuController();
            SignUpMenu signUpMenu = new SignUpMenu(signUpMenuController , GameAssetManager.getSkin());
            Main.getMain().setScreen(signUpMenu);
            currentScreen.dispose();
        }
    }
}
