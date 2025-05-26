package com.Mohammadreza.UD20.Controllers.MenuControllers;


import com.Mohammadreza.UD20.Main;
import com.Mohammadreza.UD20.Models.*;
import com.Mohammadreza.UD20.Views.LoginMenu;
import com.Mohammadreza.UD20.Views.MainMenu;
import com.Mohammadreza.UD20.Views.SignUpMenu;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.utils.Timer;

import java.util.ArrayList;
import java.util.Random;


public class SignUpMenuController {
    private SignUpMenu menu;



    public void setView(SignUpMenu menu) {
        this.menu = menu;
    }

    public boolean checkUsernameRepeatedly(String username) {
        for (Player player : App.players) {
            if(player.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkPasswordLength(String password) {
        return password.length() >= 8;
    }

    public boolean passwordHasSpecialChars(String password) {
        return password.matches(".*[@%$#&*()_].*");
    }

    public boolean hasDigits(String password) {
        return password.matches(".*\\d.*");
    }

    public boolean hasUppercaseLetters(String password) {
        return password.matches(".*[A-Z].*");
    }


    public void handleSignUpMenuButtons(){
        if(menu != null) {
            if(menu.getSignUpButton().isChecked()) {
                String username = menu.getUsernameTextField().getText();
                String password = menu.getPasswordTextField().getText();
                String confirmPassword = menu.getConfirmPasswordTextField().getText();
                String Question = menu.getSecurityQuestionSelectBox().getSelected().toString();
                String answer = menu.getSecurityQuestionAnswer().getText();
                if(username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() || Question.isEmpty()) {
                    Dialog emptyFieldsDialog = new Dialog("Empty fields"  , GameAssetManager
                        .getSkin()){
                        protected void result(Object object) {
                            if(Boolean.TRUE.equals(object)) {
                                this.hide();
                            }
                        }
                    };
                    emptyFieldsDialog.text("you should fill every field");
                    emptyFieldsDialog.button("OK" , true);

                    emptyFieldsDialog.show(menu.getStage());
                    return;
                }
                else if(checkUsernameRepeatedly(username)) {
                    Dialog alreadyTakenUsername = new Dialog("Error"
                        , GameAssetManager.getSkin());
                    alreadyTakenUsername.text("username is already taken");
                    alreadyTakenUsername.button("OK" , true);
                    alreadyTakenUsername.show(menu.getStage());
                    return;
                }
                else if(!checkPasswordLength(password)) {
                    Dialog PasswordLengthError = new Dialog("Warning" , GameAssetManager.getSkin());
                    PasswordLengthError.text("password is too short");
                    PasswordLengthError.button("OK" , true);
                    PasswordLengthError.show(menu.getStage());
                    return;
                }
                else if(!passwordHasSpecialChars(password)) {
                    Dialog specialCharactersError = new Dialog("Warning" , GameAssetManager.getSkin());
                    specialCharactersError.text("please use special characters");
                    specialCharactersError.button("OK" , true);
                    specialCharactersError.show(menu.getStage());
                    return;
                }
                else if(!password.equals(confirmPassword)) {
                    Dialog UnequalPasswordError = new Dialog("Warning" , GameAssetManager.getSkin());
                    UnequalPasswordError.text("passwords do not match");
                    UnequalPasswordError.button("OK" , true);
                    UnequalPasswordError.show(menu.getStage());
                    return;
                }
                else if(!hasDigits(password)) {
                    Dialog NumberError = new Dialog("Warning" , GameAssetManager.getSkin());
                    NumberError.text("please use digits in your password");
                    NumberError.button("OK" , true);
                    NumberError.show(menu.getStage());
                    return;
                }
                else if(!hasUppercaseLetters(password)) {
                    Dialog UpperCaseLetterError = new Dialog("Warning" , GameAssetManager.getSkin());
                    UpperCaseLetterError.text("please use uppercase letters");
                    UpperCaseLetterError.button("OK" , true);
                    UpperCaseLetterError.show(menu.getStage());
                    return;
                }
                else {
                    doSignUp(username, password, Question, answer);

                    Dialog successfullyRegister = new Dialog("", GameAssetManager.getSkin());
                    successfullyRegister.text("You registered successfully!");
                    successfullyRegister.getColor().a = 0;
                    successfullyRegister.show(menu.getStage());
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
                                    LoginMenuController controller = new LoginMenuController();
                                    LoginMenu mainMenu = new LoginMenu(controller, GameAssetManager.getSkin());
                                    Main.getMain().setScreen(mainMenu);
                                    currentScreen.dispose();
                                })
                            ));
                        }
                    }, 2);
                }



            }
        }


    }


    public void doSignUp(String username, String password , String question ,String answer) {
        SecurityQuestion securityQuestion = new SecurityQuestion(question , answer);
        Hero playerHero = new Hero(HeroType.Shana , null);
        Texture t = getRandomAvatarTextures();
        Player player = new Player(username , password , securityQuestion , playerHero , t);
        player.getHero().setOwner(player);

        System.out.println(player.getHero().getName());



        player.setPlayerTexture(GameAssetManager.character1_idle0_tex);

        App.players.add(player);

    }
    public HeroType getRandomHeroType() {
        Random rand = new Random();
        return HeroType.values()[rand.nextInt(HeroType.values().length)];
    }

    public Texture getRandomAvatarTextures() {
        ArrayList<Texture> textures = new ArrayList<>();
        textures.add(GameAssetManager.shanaPortraitTex);
        textures.add(GameAssetManager.diamondPortraitTex);
        textures.add(GameAssetManager.dasherPortraitTex);
        textures.add(GameAssetManager.scarletPortraitTex);
        textures.add(GameAssetManager.lilithPortraitTex);
        Random rand = new Random();
        return textures.get(rand.nextInt(textures.size()));
    }

    public void goToLogin(){
        if(menu != null) {
            Screen screen = Main.getMain().getScreen();
            LoginMenuController controller = new LoginMenuController();
            LoginMenu mainMenu = new LoginMenu(controller, GameAssetManager.getSkin());
            Main.getMain().setScreen(mainMenu);
            screen.dispose();
        }
    }

    public void LoginAsGuest(){
        SecurityQuestion securityQuestion = new SecurityQuestion("are you a guest?" , "yes");
        Hero playerHero = new Hero(HeroType.Shana , null);
        Texture t = getRandomAvatarTextures();
        Player player = new Player("Guest" , "WrongPassword1@", securityQuestion , playerHero , t);
        player.getHero().setOwner(player);
        player.setPlayerTexture(GameAssetManager.character1_idle0_tex);

        App.players.add(player);
        App.setPrePlayer(player);

        Screen screen = Main.getMain().getScreen();
        MainMenuController controller = new MainMenuController();
        MainMenu mainMenu = new MainMenu(controller, GameAssetManager.getSkin());
        Main.getMain().setScreen(mainMenu);
        screen.dispose();
    }
}
