package com.Mohammadreza.UD20.Views;

import com.Mohammadreza.UD20.Controllers.MenuControllers.SignUpMenuController;
import com.Mohammadreza.UD20.Main;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import  com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import org.w3c.dom.Text;


public class SignUpMenu implements Screen {
    private Stage stage;
    private SignUpMenuController controller;
    private final Label label;
    private final TextField usernameTextField;
    private final TextField passwordTextField;
    private final TextField confirmPasswordTextField;
    private final TextField securityQuestionAnswer;
    private final TextButton signUpButton;
    private final SelectBox securityQuestionSelectBox;
    private final TextButton goToLoginButton;
    private final TextButton loginAsGuestButton;


    public Table table;

    public SignUpMenu(SignUpMenuController controller , Skin skin) {
        this.controller = controller;
        stage = new Stage();
        usernameTextField = new TextField("Enter your username here", skin);
        passwordTextField = new TextField("Enter your password here", skin);
        confirmPasswordTextField = new TextField("Enter your confirm password here", skin);
        securityQuestionAnswer = new TextField("Enter your security question answer", skin);
        signUpButton = new TextButton("Sign Up", skin);
        signUpButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                controller.handleSignUpMenuButtons();
            }
        });
        goToLoginButton = new TextButton("Go To Login", skin);
        goToLoginButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                controller.goToLogin();
            }
        });

        loginAsGuestButton = new TextButton("Login as Guest", skin);
        loginAsGuestButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                controller.LoginAsGuest();
            }
        });

        securityQuestionSelectBox = new SelectBox<>(skin);
        label = new Label("SignUp Menu" , skin);
        table = new Table();
        controller.setView(this);


    }
    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);


        Array<String> securityQuestions = new Array<>();
        securityQuestions.add("what is your favorite color?");
        securityQuestions.add("what is your favorite football club?");
        securityQuestions.add("what is your favorite sports?");
        securityQuestions.add("what is your favorite foods?");

        securityQuestionSelectBox.setItems(securityQuestions);

        table.setFillParent(true);
        table.center();
        table.add(usernameTextField).width(300).height(40);
        table.row().pad(0 , 70 , 0 ,70);
        table.add(passwordTextField).width(300).height(40);
        table.row().pad(0 , 70 , 0 ,70);
        table.add(confirmPasswordTextField).width(300).height(40);
        table.row().pad(0 , 70 , 0 ,70);
        table.add(signUpButton).width(300).height(90);
        table.row().pad(0 , 70 , 0 ,70);
        table.add(securityQuestionAnswer).width(300).height(40);
        table.row().pad(0 , 70 , 0 ,70);
        table.add(securityQuestionSelectBox).width(300).height(60);
        table.row().pad(0 , 70 , 0 ,70);
        table.add(goToLoginButton).width(300).height(60);
        table.row().pad(0 , 70 , 0 ,70);
        table.add(loginAsGuestButton).width(300).height(60);
        table.row().pad(0 , 70 , 0 ,70);
        stage.addActor(table);

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0 , 0  , 0 , 1);
        Main.getBatch().begin();
        Main.getBatch().end();
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

    public Stage getStage() {
        return stage;
    }

    public TextButton getSignUpButton() {
        return signUpButton;
    }

    public TextField getConfirmPasswordTextField() {
        return confirmPasswordTextField;
    }

    public TextField getPasswordTextField() {
        return passwordTextField;
    }

    public TextField getUsernameTextField() {
        return usernameTextField;
    }

    public Label getLabel() {
        return label;
    }

    public SignUpMenuController getController() {
        return controller;
    }

    public SelectBox getSecurityQuestionSelectBox() {
        return securityQuestionSelectBox;
    }

    public TextField getSecurityQuestionAnswer() {
        return securityQuestionAnswer;
    }
}
