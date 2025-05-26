package com.Mohammadreza.UD20.Controllers.MenuControllers;

import com.Mohammadreza.UD20.Main;
import com.Mohammadreza.UD20.Models.GameAssetManager;
import com.Mohammadreza.UD20.Views.EndGameMenu;
import com.Mohammadreza.UD20.Views.MainMenu;
import com.Mohammadreza.UD20.Views.SettingsMenu;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.utils.Timer;

public class EndGameMenuController {
    private EndGameMenu view;

    public void setView(EndGameMenu view) {
        this.view = view;
    }

    public void gotToMainMenu() {
        if(view != null){

            Dialog successfullyRegister = new Dialog("", GameAssetManager.getSkin());
            successfullyRegister.text("you back to main menu");
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

                            MainMenuController mainMenuController = new MainMenuController();
                            MainMenu mainMenu = new MainMenu(mainMenuController , GameAssetManager.getSkin());
                            Main.getMain().setScreen(mainMenu);

                            currentScreen.dispose();

                        })
                    ));
                }
            }, 2);
        }
    }
}
