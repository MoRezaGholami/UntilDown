package com.Mohammadreza.UD20.Controllers.MenuControllers;

import com.Mohammadreza.UD20.Main;
import com.Mohammadreza.UD20.Models.GameAssetManager;
import com.Mohammadreza.UD20.Views.MainMenu;
import com.Mohammadreza.UD20.Views.TalentMenu;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.utils.Timer;

public class TalentMenuController {
    private TalentMenu view;

    public void setView(TalentMenu view) {
        this.view = view;
    }

    public void back(){
        if(view !=  null) {
            if(view.getBackButton().isChecked()){
                Dialog backToMainMenu = new Dialog("", GameAssetManager.getSkin());
                backToMainMenu.text("you back to the main menu");
                backToMainMenu.getColor().a = 0;
                backToMainMenu.show(view.getStage());
                float x = (Gdx.graphics.getWidth() - backToMainMenu.getWidth()) / 2f;
                float y = 50;
                backToMainMenu.setPosition(x, y);
                backToMainMenu.addAction(Actions.fadeIn(1f));

                Screen currentScreen = Main.getMain().getScreen();

                Timer.schedule(new Timer.Task() {
                    @Override
                    public void run() {
                        backToMainMenu.addAction(Actions.sequence(
                            Actions.fadeOut(1f),
                            Actions.run(() -> {
                                backToMainMenu.hide();

                                MainMenuController controller = new MainMenuController();
                                MainMenu menu = new MainMenu(controller , GameAssetManager.getSkin());
                                Main.getMain().setScreen(menu);

                                currentScreen.dispose();

                            })
                        ));
                    }
                }, 2);
            }
        }
    }
}
