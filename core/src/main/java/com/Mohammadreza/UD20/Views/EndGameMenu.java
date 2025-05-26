package com.Mohammadreza.UD20.Views;

import com.Mohammadreza.UD20.Controllers.MenuControllers.EndGameMenuController;
import com.Mohammadreza.UD20.Controllers.MenuControllers.GameMenuController;
import com.Mohammadreza.UD20.Main;
import com.Mohammadreza.UD20.Models.App;
import com.Mohammadreza.UD20.Models.Player;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;

public class EndGameMenu implements Screen {
    private EndGameMenuController controller;
    private Stage stage;
    private final Label showUserName;
    private final Label  showTimeAlive;
    private final Label showKills;
    private final Label showScore;
    private final Label gameStatus;
    private final TextButton goToMainMenu;
    private final GameMenuController gameMenuController;
    private Player player;
    public Table table;
    private boolean gameState;


    public EndGameMenu(EndGameMenuController controller , GameMenuController gameMenuController , Skin skin ,boolean gameState) {
        this.controller = controller;
        this.gameMenuController = gameMenuController;
        this.player = gameMenuController.getPlayerController().getPlayer();
        this.gameState = gameState;
        stage = new Stage();
        showUserName = new Label("" , skin);
        showTimeAlive = new Label("" , skin);
        showKills = new Label("" , skin);
        showScore = new Label("" , skin);
        gameStatus = new Label("" , skin);
        goToMainMenu = new TextButton("go to mainMenu", skin);
        goToMainMenu.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                controller.gotToMainMenu();
            }
        });
        table = new Table(skin);

        controller.setView(this);
    }


    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);

        table.setFillParent(true);
        table.center().pad(10);

        table.add(new Label("Player Name: ", table.getSkin())).left();
        table.add(showUserName).left().row();

        table.add(new Label("Time Alive: ", table.getSkin())).left();
        table.add(showTimeAlive).left().row();

        table.add(new Label("Kills: ", table.getSkin())).left();
        table.add(showKills).left().row();

        table.add(new Label("Score: ", table.getSkin())).left();
        table.add(showScore).left().row();

        table.add(new Label("Game Status: ", table.getSkin())).left();
        table.add(gameStatus).left().row();

        table.add(goToMainMenu).colspan(2).center().padTop(20).row();

        stage.addActor(table);


        showUserName.setText(player.getUsername());
        showTimeAlive.setText(App.PreGame.getGameTime() - gameMenuController.getRemainingTime() + " seconds");
        showKills.setText(String.valueOf(player.getKill()));
        int score = (int)(App.PreGame.getGameTime() - gameMenuController.getRemainingTime()) * player.getKill();
        showScore.setText(String.valueOf(score));
        if(gameState){
            gameStatus.setText("you won");
        }
        else {
            gameStatus.setText("you lost");
        }

        player.setScore(score);

        player.setTimeAlive(App.PreGame.getGameTime() - gameMenuController.getRemainingTime());
        player.addKill(player.getKill());
        player.addScores(player.getScore());
        player.addTimeAlive(player.getTimeAlive());
        player.setKill(0);
        player.getHero().setHp(player.getMaximumHealth());

        App.setPreGame(null);

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

    public Player getPlayer() {
        return player;
    }

    public GameMenuController getGameMenuController() {
        return gameMenuController;
    }

    public TextButton getGoToMainMenu() {
        return goToMainMenu;
    }

    public Label getGameStatus() {
        return gameStatus;
    }

    public Label getShowScore() {
        return showScore;
    }

    public Label getShowKills() {
        return showKills;
    }

    public Label getShowTimeAlive() {
        return showTimeAlive;
    }

    public Label getShowUserName() {
        return showUserName;
    }

    public Stage getStage() {
        return stage;
    }

    public EndGameMenuController getController() {
        return controller;
    }
}
