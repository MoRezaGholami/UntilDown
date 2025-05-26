package com.Mohammadreza.UD20.Views;


import com.Mohammadreza.UD20.Controllers.MenuControllers.ScoreBoardMenuController;
import com.Mohammadreza.UD20.Models.App;
import com.Mohammadreza.UD20.Models.Player;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import java.util.*;

public class ScoreBoardMenu implements Screen {
    private ScoreBoardMenuController controller;
    private Stage stage;
    private Skin skin;
    private Table mainTable;
    private Table buttonsTable;
    private ArrayList<Player> players = new ArrayList<>();
    private final TextButton backButton;
    private String currentUsername = "Mohammadreza";

    public ScoreBoardMenu(ScoreBoardMenuController controller, Skin skin  , ArrayList<Player> players) {

        this.players.addAll(players);
        this.skin = skin;
        this.controller = controller;
        backButton = new TextButton("Back", skin);
        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                controller.back();
            }
        });

        controller.setView(this);
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        mainTable = new Table();
        mainTable.setFillParent(true);
        stage.addActor(mainTable);

        buttonsTable = new Table();
        addSortButtons();
        drawTable();
    }

    private void addSortButtons() {
        TextButton sortByScore = new TextButton("Sort by Score", skin);
        TextButton sortByKills = new TextButton("Sort by Kills", skin);
        TextButton sortByUsername = new TextButton("Sort by Username", skin);
        TextButton sortByTime = new TextButton("Sort by Time Alive", skin);

        sortByScore.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                players.sort((a, b) -> b.getMaxScore() - a.getMaxScore());
                refreshTable();
            }
        });

        sortByKills.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                players.sort((a, b) -> b.getMaxKills() - a.getMaxKills());
                refreshTable();
            }
        });

        sortByUsername.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                players.sort(Comparator.comparing(p -> p.getUsername().toLowerCase()));
                refreshTable();
            }
        });

        sortByTime.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                players.sort((a, b) -> Float.compare(b.getMaxTimeAlive(), a.getMaxTimeAlive()));
                refreshTable();
            }
        });

        buttonsTable.add(sortByScore).pad(5);
        buttonsTable.add(sortByKills).pad(5);
        buttonsTable.add(sortByUsername).pad(5);
        buttonsTable.add(sortByTime).pad(5);
        buttonsTable.row();

        mainTable.add(buttonsTable).pad(10);
        mainTable.add(backButton).pad(10);
        mainTable.row();
    }

    private void drawTable() {


        mainTable.add(new Label("Username", skin)).pad(10);
        mainTable.add(new Label("Score", skin)).pad(10);
        mainTable.add(new Label("Kills", skin)).pad(10);
        mainTable.add(new Label("Time Alive", skin)).pad(10);
        mainTable.row();


        int index = 0;
        for (Player p : players) {
            Label nameLabel = new Label(p.getUsername(), skin);
            if (p.equals(App.PrePlayer)) {
                nameLabel.setColor(Color.GREEN);
            } else if (index < 3) {
                nameLabel.setColor(Color.GOLD);
            }
            System.out.println("Drawing player: " + p);
            System.out.println("Username: " + p.getUsername());
            System.out.println("Score: " + p.getScore());
            System.out.println("Kill: " + p.getKill());
            System.out.println("Time Alive: " + p.getMaxTimeAlive());

            mainTable.add(nameLabel).pad(10);
            mainTable.add(new Label(p.getMaxScore() + "", skin)).pad(10);
            mainTable.add(new Label(p.getMaxKills() + "", skin)).pad(10);
            mainTable.add(new Label(String.format("%.2f", p.getMaxTimeAlive()), skin)).pad(10);
            mainTable.row();

            index++;
        }
    }

    private void refreshTable() {
        mainTable.clear();
        mainTable.add(buttonsTable).pad(10);
        mainTable.row();
        drawTable();
    }

    @Override public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        stage.draw();
    }

    @Override public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void hide() {}
    @Override public void dispose() {
        stage.dispose();
    }
}

