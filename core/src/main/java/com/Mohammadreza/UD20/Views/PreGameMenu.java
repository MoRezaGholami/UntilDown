package com.Mohammadreza.UD20.Views;

import com.Mohammadreza.UD20.Controllers.MenuControllers.PreGameMenuController;
import com.Mohammadreza.UD20.Main;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class PreGameMenu implements Screen {
    private Stage stage;
    private final Label gameTitle;
    private final TextButton playButton;
    private final SelectBox selectHero;
    private final TextButton changeHero;
    private final SelectBox selectWeapon;
    private final TextButton changeWeapon;
    private final SelectBox gameTimer;
    private final TextButton gameTimerButton;
    private final TextButton backButton;
    private final Skin skin;
    public Table table;
    private PreGameMenuController controller;
    public PreGameMenu(PreGameMenuController preGameMenuController, Skin skin) {
        this.controller = preGameMenuController;
        this.gameTitle = new Label("Pregame Menu", skin);
        this.selectHero = new SelectBox<>(skin);
        this.skin = skin;



        this.playButton = new TextButton("Play", skin);
        playButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                controller.Play();
            }
        });
        this.changeHero = new TextButton("Change Hero", skin);
        changeHero.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                controller.handleChangeHero();

            }
        });
        this.changeWeapon = new TextButton("Change Weapon", skin);
        changeWeapon.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                controller.handleChangeWeapon();

            }
        });
        this.gameTimerButton = new TextButton("Game Timer", skin);
        gameTimerButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                controller.handleTimerGame();
            }
        });

        this.backButton = new TextButton("Back", skin);
        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {

            }
        });

        this.selectWeapon = new SelectBox<>(skin);

        this.gameTimer = new SelectBox<>(skin);


        this.table = new Table();

        controller.setView(this);
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);


        Array<String> heroes = new Array<>(new String[]{"shana", "diamond", "scarlet", "lilith", "dasher"});
        Array<String> weapons = new Array<>(new String[]{"smg", "smgDual", "shotgun"});
        Array<String> times = new Array<>(new String[]{"20", "10", "5", "2"});

        selectHero.setItems(heroes);
        selectWeapon.setItems(weapons);
        gameTimer.setItems(times);

        table.setFillParent(true);
        table.center();


        table.row().pad(15);
        table.add(gameTitle).colspan(2);

        table.row().pad(10);
        table.add(new Label("Select Hero:", skin)).left().padRight(10);
        table.add(selectHero).width(200);

        table.row().pad(5);
        table.add(changeHero).colspan(2).width(200);

        table.row().pad(10);
        table.add(new Label("Select Weapon:", skin)).left().padRight(10);
        table.add(selectWeapon).width(200);

        table.row().pad(5);
        table.add(changeWeapon).colspan(2).width(200);

        table.row().pad(10);
        table.add(new Label("Select Time (sec):", skin)).left().padRight(10);
        table.add(gameTimer).width(200);

        table.row().pad(5);
        table.add(gameTimerButton).colspan(2).width(200);

        table.row().pad(20);
        table.add(playButton).colspan(2).width(200).height(50);

        table.row().pad(10);
        table.add(backButton).colspan(2).width(150);


        stage.addActor(table);
    }


    @Override
    public void render(float v) {
        ScreenUtils.clear(0, 0, 0, 1);
        Main.getBatch().begin();
        Main.getBatch().end();
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();

    }

    @Override
    public void resize(int i, int i1) {

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

    public PreGameMenuController getPreGameMenuController() {
        return controller;
    }


    public Stage getStage() {
        return stage;
    }

    public PreGameMenuController getController() {
        return controller;
    }

    public Skin getSkin() {
        return skin;
    }

    public TextButton getBackButton() {
        return backButton;
    }

    public SelectBox getGameTimer() {
        return gameTimer;
    }

    public TextButton getGameTimerButton() {
        return gameTimerButton;
    }

    public TextButton getChangeWeapon() {
        return changeWeapon;
    }

    public SelectBox getSelectWeapon() {
        return selectWeapon;
    }

    public TextButton getChangeHero() {
        return changeHero;
    }

    public SelectBox getSelectHero() {
        return selectHero;
    }

    public TextButton getPlayButton() {
        return playButton;
    }

    public Label getGameTitle() {
        return gameTitle;
    }
}
