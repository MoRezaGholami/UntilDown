package com.Mohammadreza.UD20.Controllers.MenuControllers;

import com.Mohammadreza.UD20.Main;
import com.Mohammadreza.UD20.Models.App;
import com.Mohammadreza.UD20.Models.GameAssetManager;
import com.Mohammadreza.UD20.Views.EndGameMenu;
import com.Mohammadreza.UD20.Views.MainMenu;
import com.Mohammadreza.UD20.Views.PauseMenu;
import com.badlogic.gdx.Screen;

public class PauseMenuController {
    private PauseMenu view;
    public void setView(PauseMenu view) {
        this.view = view;
    }

    public void resume(){
        if(view!=null){
            view.getGameController().setPaused(false);
            Main.getMain().setScreen(view.getGameController().getView());
        }
    }


    public void giveUp(){
        if(view!=null){
            Screen screen = Main.getMain().getScreen();
            EndGameMenuController endGameMenuController = new EndGameMenuController();
            EndGameMenu endGameMenu = new EndGameMenu(endGameMenuController , view.getGameController() , GameAssetManager.getSkin() , false);

            Main.getMain().setScreen(endGameMenu);
            screen.dispose();
            App.setPreGame(null);
            screen.dispose();
        }
    }
}
