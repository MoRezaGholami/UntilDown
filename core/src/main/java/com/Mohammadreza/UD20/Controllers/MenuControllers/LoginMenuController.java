package com.Mohammadreza.UD20.Controllers.MenuControllers;

import com.Mohammadreza.UD20.Main;
import com.Mohammadreza.UD20.Models.App;
import com.Mohammadreza.UD20.Models.GameAssetManager;
import com.Mohammadreza.UD20.Models.Player;
import com.Mohammadreza.UD20.Views.ForgetPasswordMenu;
import com.Mohammadreza.UD20.Views.LoginMenu;
import com.Mohammadreza.UD20.Views.MainMenu;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.utils.Timer;


public class LoginMenuController {
    private LoginMenu view;

    public void setView(LoginMenu view) {
        this.view = view;
    }

    public Player findPlayer(String playerName) {
        for (Player player : App.players) {
            if(playerName.equals(player.getUsername())) {
                return player;
            }
        }
        return null;
    }

    public void handleLogin() {
        if(view != null){
            if(view.getLoginButton().isChecked()){
                String username = view.getUsernameTextField().getText();
                String password = view.getPasswordTextField().getText();
                if(username.isEmpty() || password.isEmpty()){
                    Dialog emptyFields = new Dialog("Error" , GameAssetManager.getSkin());
                    emptyFields.text("you should fill all the fields");
                    emptyFields.button("Ok" , true);
                    emptyFields.show(view.getStage());
                    return;
                }
                if(findPlayer(username) == null){
                    Dialog error = new Dialog("Error" , GameAssetManager.getSkin());
                    error.text("user not found");
                    error.button("Ok" , true);
                    error.show(view.getStage());
                    return;
                }
                Player player = findPlayer(username);
                if(!player.getPassword().equals(password)){
                    Dialog error = new Dialog("Error" , GameAssetManager.getSkin());
                    error.text("wrong password");
                    error.button("Ok" , true);
                    error.show(view.getStage());
                    return;
                }


                Dialog successfullyRegister = new Dialog("", GameAssetManager.getSkin());
                successfullyRegister.text("user logged in successfully!");
                successfullyRegister.getColor().a = 0;
                successfullyRegister.show(view.getStage());
                float x = (Gdx.graphics.getWidth() - successfullyRegister.getWidth()) / 2f;
                float y = 50;
                successfullyRegister.setPosition(x, y);
                successfullyRegister.addAction(Actions.fadeIn(1f));
                App.setPrePlayer(player);
                Screen currentScreen = Main.getMain().getScreen();

                Timer.schedule(new Timer.Task() {
                    @Override
                    public void run() {
                        successfullyRegister.addAction(Actions.sequence(
                            Actions.fadeOut(1f),
                            Actions.run(() -> {
                                successfullyRegister.hide();
                                MainMenuController controller = new MainMenuController();
                                MainMenu mainMenu = new MainMenu(controller, GameAssetManager.getSkin());
                                Main.getMain().setScreen(mainMenu);
                                currentScreen.dispose();

                            })
                        ));
                    }
                }, 2);


            }
        }
    }

    public void handleForgotPassword() {
        String username = view.getUsernameTextField().getText();
        if(username.isEmpty()){
            Dialog emptyFields = new Dialog("Error" , GameAssetManager.getSkin());
            emptyFields.text("please enter your username");
            emptyFields.button("Ok" , true);
            emptyFields.show(view.getStage());
            return;
        }
        if(findPlayer(username) == null){
            Dialog error = new Dialog("Error" , GameAssetManager.getSkin());
            error.text("user not found");
            error.button("Ok" , true);
            error.show(view.getStage());
            return;
        }
        Player player = findPlayer(username);
        Dialog successfullyRegister = new Dialog("", GameAssetManager.getSkin());
        successfullyRegister.text("you enter forget password menu");
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
                        ForgetPasswordController controller = new ForgetPasswordController();
                        ForgetPasswordMenu  mainMenu= new ForgetPasswordMenu(controller, GameAssetManager.getSkin() , player);
                        Main.getMain().setScreen(mainMenu);
                        currentScreen.dispose();
                    })
                ));
            }
        }, 2);


    }
}
