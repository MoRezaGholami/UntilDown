package com.Mohammadreza.UD20.Controllers.MenuControllers;

import com.Mohammadreza.UD20.Main;
import com.Mohammadreza.UD20.Models.GameAssetManager;
import com.Mohammadreza.UD20.Models.Player;
import com.Mohammadreza.UD20.Views.ForgetPasswordMenu;
import com.Mohammadreza.UD20.Views.LoginMenu;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.utils.Timer;

public class ForgetPasswordController {
    private final SignUpMenuController signUpMenuController = new SignUpMenuController();
    private Player fullishPlayer;
    private ForgetPasswordMenu view;

    public void setViewAndPlayer(ForgetPasswordMenu view , Player fullishPlayer) {
        this.view = view;
        this.fullishPlayer = fullishPlayer;
    }

    public void handleForgetPassword() {
        if(view != null) {
            if(view.getEnterNewPasswordButton().isChecked()){
                String answer = view.getAnswerQuestionField().getText();
                String newPassword = view.getEnterNewPasswordField().getText();
                if(newPassword.isEmpty() || answer.isEmpty()){
                    Dialog emptyFields = new Dialog("Error" , GameAssetManager.getSkin());
                    emptyFields.text("please fill all the fields");
                    emptyFields.button("OK");
                    emptyFields.show(view.getStage());
                    return;
                }

                if(!answer.equals(fullishPlayer.getSecurityQuestion().getAnswer())){
                    Dialog wrongAnswer = new Dialog("Error" , GameAssetManager.getSkin());
                    wrongAnswer.text("Wrong answer");
                    wrongAnswer.button("Try again");
                    wrongAnswer.show(view.getStage());
                    return;
                }

                if(!signUpMenuController.checkPasswordLength(newPassword)){
                    Dialog shortPassword = new Dialog("Error" , GameAssetManager.getSkin());
                    shortPassword.text("Password too short");
                    shortPassword.button("Try again");
                    shortPassword.show(view.getStage());
                    return;
                }

                if(!signUpMenuController.hasDigits(newPassword)){
                    Dialog shortPassword = new Dialog("Error" , GameAssetManager.getSkin());
                    shortPassword.text("password hasn't digit");
                    shortPassword.button("Try again");
                    shortPassword.show(view.getStage());
                    return;
                }

                if(!signUpMenuController.passwordHasSpecialChars(newPassword)){
                    Dialog shortPassword = new Dialog("Error" , GameAssetManager.getSkin());
                    shortPassword.text("password hasn't special char");
                    shortPassword.button("Try again");
                    shortPassword.show(view.getStage());
                    return;
                }

                if(!signUpMenuController.hasUppercaseLetters(newPassword)){
                    Dialog shortPassword = new Dialog("Error" , GameAssetManager.getSkin());
                    shortPassword.text("password hasn't uppercase letter");
                    shortPassword.button("Try again");
                    shortPassword.show(view.getStage());
                    return;
                }
                if(newPassword.equals(fullishPlayer.getPassword())){
                    Dialog shortPassword = new Dialog("Error" , GameAssetManager.getSkin());
                    shortPassword.text("enter new password");
                    shortPassword.button("Try again");
                    shortPassword.show(view.getStage());
                    return;
                }

                Dialog successfullyRegister = new Dialog("", GameAssetManager.getSkin());
                successfullyRegister.text("your password has been changed successfully");
                fullishPlayer.setPassword(newPassword);
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
                                LoginMenuController controller = new LoginMenuController();
                                LoginMenu  mainMenu= new LoginMenu(controller, GameAssetManager.getSkin());
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
