package com.Mohammadreza.UD20.Controllers.MenuControllers;

import com.Mohammadreza.UD20.Main;
import com.Mohammadreza.UD20.Models.App;
import com.Mohammadreza.UD20.Models.GameAssetManager;
import com.Mohammadreza.UD20.Models.Player;
import com.Mohammadreza.UD20.Views.ProfileMenu;
import com.Mohammadreza.UD20.Views.SettingsMenu;
import com.Mohammadreza.UD20.Views.SignUpMenu;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Timer;

import java.io.File;

public class ProfileMenuController {
    private ProfileMenu view;
    private final SignUpMenuController signUpController = new SignUpMenuController();

    public void setView(ProfileMenu view) {
        this.view = view;
    }

    public boolean validUsername(String username) {
        for (Player player : App.players) {
            if (player.getUsername().equals(username)) {
                return false;
            }
        }
        return true;
    }

    public void handleNewUsername(){
        if(view != null){
            String newUsername = view.getChangeUsernameField().getText();
            if(newUsername.isEmpty()){
                Dialog emptyFields = new Dialog("Error" , GameAssetManager.getSkin());
                emptyFields.text("please fill username");
                emptyFields.button("Ok");
                emptyFields.show(view.getStage());
                return;
            }

            if(!validUsername(newUsername)){
                Dialog emptyFields = new Dialog("Error" , GameAssetManager.getSkin());
                emptyFields.text("this username is already taken");
                emptyFields.button("Ok");
                emptyFields.show(view.getStage());
                return;
            }

            if(newUsername.equals(App.PrePlayer.getUsername())){
                Dialog emptyFields = new Dialog("Error" , GameAssetManager.getSkin());
                emptyFields.text("enter new username");
                emptyFields.button("Ok");
                emptyFields.show(view.getStage());
                return;
            }

            Dialog successfullyChange = new Dialog("", GameAssetManager.getSkin());
            successfullyChange.text("your username has been changed");
            successfullyChange.getColor().a = 0;
            successfullyChange.show(view.getStage());
            float x = (Gdx.graphics.getWidth() - successfullyChange.getWidth()) / 2f;
            float y = 50;
            successfullyChange.setPosition(x, y);
            successfullyChange.addAction(Actions.fadeIn(1f));


            App.PrePlayer.setUsername(newUsername);
            Timer.schedule(new Timer.Task() {
                @Override
                public void run() {
                    successfullyChange.addAction(Actions.sequence(
                        Actions.fadeOut(1f),
                        Actions.run(successfullyChange::hide)
                    ));
                }
            }, 2);
        }
    }

    public void handleNewPassword(){
        if(view != null){
            String newPassword = view.getChangePasswordField().getText();
            if(newPassword.isEmpty()){
                Dialog emptyFields = new Dialog("Error" , GameAssetManager.getSkin());
                emptyFields.text("please fill password");
                emptyFields.button("Ok");
                emptyFields.show(view.getStage());
                return;
            }

            if(!signUpController.checkPasswordLength(newPassword)){
                Dialog emptyFields = new Dialog("Error" , GameAssetManager.getSkin());
                emptyFields.text("password too short");
                emptyFields.button("Ok");
                emptyFields.show(view.getStage());
                return;
            }

            if(!signUpController.hasUppercaseLetters(newPassword)){
                Dialog emptyFields = new Dialog("Error" , GameAssetManager.getSkin());
                emptyFields.text("please use uppercase letters");
                emptyFields.button("Ok");
                emptyFields.show(view.getStage());
                return;
            }
            if(!signUpController.hasDigits(newPassword)){
                Dialog emptyFields = new Dialog("Error" , GameAssetManager.getSkin());
                emptyFields.text("please use digits");
                emptyFields.button("Ok");
                emptyFields.show(view.getStage());
                return;
            }

            if(!signUpController.passwordHasSpecialChars(newPassword)){
                Dialog emptyFields = new Dialog("Error" , GameAssetManager.getSkin());
                emptyFields.text("please use special characters");
                emptyFields.button("Ok");
                emptyFields.show(view.getStage());
                return;
            }

            Dialog successfullyRegister = new Dialog("", GameAssetManager.getSkin());
            successfullyRegister.text("your password has been changed");
            successfullyRegister.getColor().a = 0;
            successfullyRegister.show(view.getStage());
            float x = (Gdx.graphics.getWidth() - successfullyRegister.getWidth()) / 2f;
            float y = 50;
            successfullyRegister.setPosition(x, y);
            successfullyRegister.addAction(Actions.fadeIn(1f));


            App.PrePlayer.setPassword(newPassword);
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


    public void handleDeleteAccount(){
        if(view != null){
            Dialog successfullyRegister = new Dialog("", GameAssetManager.getSkin());
            successfullyRegister.text("your account has been deleted");
            successfullyRegister.getColor().a = 0;
            successfullyRegister.show(view.getStage());
            float x = (Gdx.graphics.getWidth() - successfullyRegister.getWidth()) / 2f;
            float y = 50;
            successfullyRegister.setPosition(x, y);
            successfullyRegister.addAction(Actions.fadeIn(1f));

            Screen currentScreen = Main.getMain().getScreen();

            App.players.remove(App.PrePlayer);
            App.setPrePlayer(null);


            Timer.schedule(new Timer.Task() {
                @Override
                public void run() {
                    successfullyRegister.addAction(Actions.sequence(
                        Actions.fadeOut(1f),
                        Actions.run(() -> {
                            successfullyRegister.hide();
                            SignUpMenuController controller = new SignUpMenuController();
                            SignUpMenu menu = new SignUpMenu(controller , GameAssetManager.getSkin());
                            Main.getMain().setScreen(menu);
                            currentScreen.dispose();

                        })
                    ));
                }
            }, 2);
        }
    }


    public void handleChangeAvatar(){
        if(view != null){
            if(view.getChangeAvatarButton().isChecked()){
                String avatar = view.getChangeAvatarSelect().getSelected();

                switch(avatar){
                    case "shana" :

                        App.PrePlayer.setAvatarImage(new Image(GameAssetManager.shanaPortraitTex));
                        break;
                    case "diamond" :

                        App.PrePlayer.setAvatarImage(new Image(GameAssetManager.diamondPortraitTex));
                        break;
                    case  "dasher" :
                        App.PrePlayer.setAvatarImage(new Image(GameAssetManager.dasherPortraitTex));
                        break;
                    case  "lilith" :
                         App.PrePlayer.setAvatarImage(new Image(GameAssetManager.lilithPortraitTex));
                         break;
                    case "scarlet" :
                        App.PrePlayer.setAvatarImage(new Image(GameAssetManager.scarletPortraitTex));
                        break;



                }
                Dialog successfullyRegister = new Dialog("", GameAssetManager.getSkin());
                successfullyRegister.text("you avatar has been changed");
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
    }

    public void uploadAvatar(File file){
        Pixmap pixmap = new Pixmap(new FileHandle(file));
        Texture texture = new Texture(pixmap);
        Image avatarPreview = new Image(texture);

        App.PrePlayer.setAvatarImage(avatarPreview);
    }

    public void backToSettingMenu(){
        Dialog successfullyRegister = new Dialog("", GameAssetManager.getSkin());
        successfullyRegister.text("you back to setting menu");
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

                        SettingsMenuController controller = new SettingsMenuController();
                        SettingsMenu menu = new SettingsMenu(controller, GameAssetManager.getSkin());
                        Main.getMain().setScreen(menu);
                        currentScreen.dispose();

                    })
                ));
            }
        }, 2);
    }
}
