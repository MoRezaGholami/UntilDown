package com.Mohammadreza.UD20.Views;

import com.Mohammadreza.UD20.Controllers.MenuControllers.GameMenuController;
import com.Mohammadreza.UD20.Controllers.MenuControllers.PauseMenuController;
import com.Mohammadreza.UD20.Models.Ability;
import com.Mohammadreza.UD20.Models.GameAssetManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class PauseMenu implements Screen {
    private Stage stage;
    private final TextButton resume;
    private final TextButton giveUp;
    private final Label showEarnedAbilities;
    private final Label showCheatCodes;
    private final PauseMenuController controller;
    private final GameMenuController gameController;
    private final TextButton blackAndWhite;

    private final Skin skin;

    private Table table;



    public PauseMenu(PauseMenuController controller ,GameMenuController gameController ,  Skin skin) {
        this.controller = controller;
        this.gameController = gameController;
        this.skin = skin;
        stage = new Stage();



        resume = new TextButton("Resume", skin);
        resume.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                controller.resume();
            }
        });
        giveUp = new TextButton("Give Up", skin);

        giveUp.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                controller.giveUp();
            }
        });

        blackAndWhite = new TextButton("Black and White", skin);
        blackAndWhite.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                GameAssetManager.blackAndWhiteEnabled = !GameAssetManager.blackAndWhiteEnabled;
            }
        });
        showEarnedAbilities = new Label("show Earned abilities : ", skin);
        showCheatCodes = new Label("show Cheat Codes : ", skin);

        controller.setView(this);

    }

    @Override
    public void show() {

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        table = new Table();
        table.setFillParent(true);
        stage.addActor(table);
        Label showAb = new Label("" , skin);
        StringBuilder sb = new StringBuilder();
        for(Ability ab : gameController.getPlayerController().getPlayer().getAbilities()){
            sb.append(ab.getName()).append("\t");
        }
        showAb.setText(sb.toString());
        table.add(showAb).padBottom(20).left().row();

        Label showCh = new Label("" , skin);
        showCh.setText("infinite Ammo" + "\n" + "infinite Health");
        table.add(showCh).padBottom(20).left().row();


        table.add(showEarnedAbilities).padBottom(20).left().row();
        table.add(showCheatCodes).padBottom(30).left().row();
        table.add(blackAndWhite).padBottom(20).left().row();

        table.add(resume).padBottom(30).left().row();
        table.add(giveUp).padBottom(30).left().row();

        stage.addActor(table);




    }

    @Override
    public void render(float delta) {
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

    public GameMenuController getGameController() {
        return gameController;
    }
}
