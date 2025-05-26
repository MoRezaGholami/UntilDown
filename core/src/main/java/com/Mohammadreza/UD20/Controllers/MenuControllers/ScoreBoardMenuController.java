package com.Mohammadreza.UD20.Controllers.MenuControllers;


import com.Mohammadreza.UD20.Main;
import com.Mohammadreza.UD20.Models.GameAssetManager;
import com.Mohammadreza.UD20.Views.MainMenu;
import com.Mohammadreza.UD20.Views.ScoreBoardMenu;
import com.badlogic.gdx.Screen;

public class ScoreBoardMenuController {
    private ScoreBoardMenu view;

    public void setView(ScoreBoardMenu view) {
        this.view = view;
    }

    public void back(){
        if(view != null){
            Screen screen = Main.getMain().getScreen();
            MainMenuController mainMenuController = new MainMenuController();
            MainMenu mainMenu = new MainMenu(mainMenuController  , GameAssetManager.getSkin());
            Main.getMain().setScreen(mainMenu);
            screen.dispose();

        }
    }
}
