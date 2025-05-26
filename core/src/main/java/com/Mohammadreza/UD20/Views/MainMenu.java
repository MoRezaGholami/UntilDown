package com.Mohammadreza.UD20.Views;

import com.Mohammadreza.UD20.Controllers.MenuControllers.MainMenuController;
import com.Mohammadreza.UD20.Main;
import com.Mohammadreza.UD20.Models.App;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class MainMenu implements Screen {
    private Stage stage;
    private Skin skin;
    private final MainMenuController controller;
    public Table table;
    private final TextButton goToSettingsButton;
    private final TextButton goToProfileButton;
    private final TextButton goToPreGameButton;
    private final TextButton goToScoreBoardButton;
    private final TextButton goToTalentsButton;
    private final TextButton logoutButton;
    private final TextButton continueLastGameButton;

    private final Image playerAvatar;
    private final Label playerName;
    private final Label playerScore;


    public MainMenu(MainMenuController controller , Skin skin) {
        this.controller = controller;
        this.skin = skin;
        stage = new Stage();
        table = new Table();


        goToSettingsButton = new TextButton("Settings", skin);
        goToSettingsButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                controller.gotToSettingMenu();
            }
        });

        goToProfileButton = new TextButton("Profile", skin);
        goToProfileButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                controller.goToProfileMenu();
            }
        });


        goToPreGameButton = new TextButton("Pre Game", skin);
        goToPreGameButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                controller.goToPreGameMenu();
            }
        });


        goToScoreBoardButton = new TextButton("Scoreboard", skin);
        goToScoreBoardButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                controller.goToScoreBoardMenu();
            }
        });

        goToTalentsButton = new TextButton("Talents", skin);
        goToTalentsButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                controller.goToTalentMenu();
            }
        });


        logoutButton = new TextButton("Logout", skin);
        logoutButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                controller.logout();
            }
        });


        continueLastGameButton = new TextButton("Continue Last Game", skin);
        continueLastGameButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {

            }
        });



        playerAvatar = App.PrePlayer.getAvatarImage();
        playerName = new Label(App.PrePlayer.getUsername(), skin);
        playerScore = new Label(App.PrePlayer.getScore() + "", skin);
        controller.setView(this);

    }
    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        table = new Table();
        table.setFillParent(true);
        table.center();

        Table profileTable = new Table();
        profileTable.top().left();
        profileTable.setFillParent(true);

        playerAvatar.setSize(400, 400);
        playerName.setSize(400, 400);
        profileTable.add(playerAvatar).pad(10);
        profileTable.add(playerName).padLeft(10);
        profileTable.row();
        profileTable.add();
        profileTable.add(playerScore).padLeft(10).padTop(5);

        table.add(new Label("Main Menu", skin)).padBottom(30).row();

        TextButton[] buttons = {
            goToSettingsButton,
            goToProfileButton,
            goToPreGameButton,
            goToScoreBoardButton,
            goToTalentsButton,
            continueLastGameButton,
            logoutButton
        };

        for (TextButton button : buttons) {
            table.add(button).pad(10).width(250).height(50).row();
        }

        for (int i = 0; i < buttons.length; i++) {
            Actor actor = buttons[i];
            actor.getColor().a = 0f;
            actor.addAction(Actions.sequence(
                Actions.delay(0.15f * i),
                Actions.parallel(
                    Actions.fadeIn(0.4f),
                    Actions.moveBy(0, 20, 0.4f)
                )
            ));
        }

        stage.addActor(profileTable);
        stage.addActor(table);
    }


    @Override
    public void render(float v) {
        ScreenUtils.clear(0 , 0  , 0 , 1);
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

    public Stage getStage() {
        return stage;
    }

    public MainMenuController getController() {
        return controller;
    }

    public TextButton getGoToSettingsButton() {
        return goToSettingsButton;
    }

    public TextButton getGoToProfileButton() {
        return goToProfileButton;
    }

    public TextButton getGoToPreGameButton() {
        return goToPreGameButton;
    }

    public TextButton getGoToScoreBoardButton() {
        return goToScoreBoardButton;
    }

    public TextButton getGoToTalentsButton() {
        return goToTalentsButton;
    }

    public TextButton getLogoutButton() {
        return logoutButton;
    }

    public TextButton getContinueLastGameButton() {
        return continueLastGameButton;
    }

    public Image getPlayerAvatar() {
        return playerAvatar;
    }

    public Label getPlayerName() {
        return playerName;
    }

    public Label getPlayerScore() {
        return playerScore;
    }
}
