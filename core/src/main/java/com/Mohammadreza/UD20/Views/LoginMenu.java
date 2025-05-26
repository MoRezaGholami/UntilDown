package com.Mohammadreza.UD20.Views;

import com.Mohammadreza.UD20.Controllers.MenuControllers.LoginMenuController;
import com.Mohammadreza.UD20.Main;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class LoginMenu implements Screen {
    private Stage stage;
    private final LoginMenuController controller;
    private final Label label;
    private final TextField usernameTextField;
    private final TextField passwordTextField;
    private final TextButton loginButton;
    private final TextButton forgivePasswordButton;

    public Table table;

    public LoginMenu(LoginMenuController controller, Skin skin) {
        this.controller = controller;
        this.stage = new Stage();
        label = new Label("Login Menu", skin);
        usernameTextField = new TextField("Enter your username", skin);
        passwordTextField = new TextField("Enter your password", skin);
        loginButton = new TextButton("Login", skin);
        forgivePasswordButton = new TextButton("Forgive Password", skin , "default");
        forgivePasswordButton.getLabel().setColor(Color.SKY);
        forgivePasswordButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                controller.handleForgotPassword();
            }
        });

        table = new Table();
        loginButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                controller.handleLogin();
            }
        });
        controller.setView(this);
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        table.setFillParent(true);
        table.center();
        table.defaults().width(300).height(40).padBottom(20);

        table.add(usernameTextField);
        table.row();

        table.add(passwordTextField);
        table.row();

        forgivePasswordButton.getLabel().setFontScale(0.85f);
        forgivePasswordButton.getLabel().setColor(Color.SKY);
        table.add(forgivePasswordButton).height(50);
        table.row();

        loginButton.getLabel().setFontScale(1.1f);
        table.add(loginButton).height(50);
        table.row();

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

    public TextButton getForgivePasswordButton() {
        return forgivePasswordButton;
    }

    public TextButton getLoginButton() {
        return loginButton;
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

    public LoginMenuController getController() {
        return controller;
    }
}
