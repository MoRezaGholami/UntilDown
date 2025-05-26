package com.Mohammadreza.UD20.Views;

import com.Mohammadreza.UD20.Controllers.MenuControllers.ForgetPasswordController;
import com.Mohammadreza.UD20.Main;
import com.Mohammadreza.UD20.Models.Player;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class ForgetPasswordMenu implements Screen {
    private final Player fullishPlayer;
    private final ForgetPasswordController controller;
    private Stage stage;
    private final Label showSecurityQuestion;
    private final Label label;
    private final TextField answerQuestionField;
    private final TextField enterNewPasswordField;
    private final TextButton enterNewPasswordButton;


    public Table table;

    public ForgetPasswordMenu(ForgetPasswordController controller , Skin skin ,Player player) {
        this.controller = controller;
        this.stage = new Stage();
        this.fullishPlayer = player;
        showSecurityQuestion = new Label(player.getSecurityQuestion().getQuestion(), skin);
        label = new Label("Forget Password Menu", skin);
        answerQuestionField = new TextField("enter your answer", skin);
        enterNewPasswordField = new TextField("enter new password", skin);
        enterNewPasswordButton = new TextButton("Apply" , skin);
        enterNewPasswordButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                controller.handleForgetPassword();
            }
        });
        table = new Table();

        controller.setViewAndPlayer(this , player);
    }


    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        table = new Table();
        table.setFillParent(true);
        table.center();
        table.defaults().width(350).height(40).padBottom(20);

        label.setFontScale(1.2f);
        showSecurityQuestion.setWrap(true);

        table.add(label).colspan(1);
        table.row();

        table.add(showSecurityQuestion).width(400);
        table.row();

        table.add(answerQuestionField);
        table.row();

        table.add(enterNewPasswordField);
        table.row();

        table.add(enterNewPasswordButton);
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

    public Player getFullishPlayer() {
        return fullishPlayer;
    }

    public TextButton getEnterNewPasswordButton() {
        return enterNewPasswordButton;
    }

    public TextField getEnterNewPasswordField() {
        return enterNewPasswordField;
    }

    public TextField getAnswerQuestionField() {
        return answerQuestionField;
    }

    public Label getLabel() {
        return label;
    }

    public Label getShowSecurityQuestion() {
        return showSecurityQuestion;
    }

    public Stage getStage() {
        return stage;
    }

    public ForgetPasswordController getController() {
        return controller;
    }
}
