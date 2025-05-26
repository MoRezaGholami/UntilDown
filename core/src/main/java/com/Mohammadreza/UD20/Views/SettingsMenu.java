package com.Mohammadreza.UD20.Views;

import com.Mohammadreza.UD20.Controllers.KeyBinding;
import com.Mohammadreza.UD20.Controllers.MenuControllers.SettingsMenuController;
import com.Mohammadreza.UD20.Main;
import com.Mohammadreza.UD20.Models.GameAssetManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.InputAdapter;

import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class SettingsMenu implements Screen {
    private Stage stage;
    private SettingsMenuController controller;
    private final Label SettingMenu;
    private final Slider MusicVolumeSlider;
    private final Label showMusicVolumeLabel;
    private final SelectBox musics;
    private final TextButton playMusicButton;
    private final TextButton stopMusicButton;
    private final CheckBox SFXButton;

    private final TextButton changeKeyUpButton;
    private final TextButton changeKeyDownButton;
    private final TextButton changeKeyLeftButton;
    private final TextButton changeKeyRightButton;
    private final CheckBox autoReload;
    private final CheckBox blackAndWhiteMode;
    private final TextButton backButton;
    private Skin skin;


    public Table table;


    public SettingsMenu(SettingsMenuController controller , Skin skin) {
        this.controller = controller;
        stage = new Stage();
        SettingMenu = new Label("Settings", skin);
        musics = new SelectBox(skin);
        Array<String> musicsArray = new Array<>();
        musicsArray.add("Bullet-Nf");
        musicsArray.add("Good things go-Linkin Park");
        musicsArray.add("Know-Nf");
        musics.setItems(musicsArray);
        MusicVolumeSlider =new Slider(0f, 1f, 0.01f, false, skin);
        MusicVolumeSlider.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if(controller.getCurrentMusic() != null){
                    controller.getCurrentMusic().setVolume(controller.getCurrentMusic().getVolume());
                }
            }
        });
        showMusicVolumeLabel = new Label("Show Music Volume", skin);
        playMusicButton = new TextButton("Play" , skin);
        stopMusicButton = new TextButton("Stop" , skin);

        changeKeyUpButton = new TextButton("Change Key Up", skin);
        changeKeyDownButton = new TextButton("Change Key Down", skin);
        changeKeyLeftButton = new TextButton("Change Key Left", skin);
        changeKeyRightButton = new TextButton("Change Key Right", skin);
        autoReload = new CheckBox("Auto Reload", skin);
        blackAndWhiteMode = new CheckBox("Black and White Mode", skin);
        blackAndWhiteMode.setChecked(GameAssetManager.blackAndWhiteEnabled);
        blackAndWhiteMode.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                GameAssetManager.blackAndWhiteEnabled = blackAndWhiteMode.isChecked();
            }
        });
        SFXButton = new CheckBox("SFX", skin);
        SFXButton.setChecked(GameAssetManager.sfxEnabled);
        SFXButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                GameAssetManager.sfxEnabled = SFXButton.isChecked();
            }
        });

        backButton = new TextButton("Back", skin);
        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                controller.back();
            }
        });

        controller.setView(this);
        table = new Table();

        this.skin = skin;
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        table.setFillParent(true);
        Gdx.input.setInputProcessor(stage);

        table.center();
        stage.addActor(table);
        table.getColor().a = 0f;
        table.addAction(Actions.fadeIn(1f));



        changeKeyUpButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.input.setInputProcessor(new InputAdapter() {
                    @Override
                    public boolean keyDown(int keycode) {
                        KeyBinding.MOVE_UP = keycode;
                        changeKeyUpButton.setText("Key Up: " + Input.Keys.toString(keycode));
                        Gdx.input.setInputProcessor(stage);
                        return true;
                    }
                });
            }
        });

        changeKeyDownButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.input.setInputProcessor(new InputAdapter() {
                    @Override
                    public boolean keyDown(int keycode) {
                        KeyBinding.MOVE_DOWN = keycode;
                        changeKeyDownButton.setText("Key Down: " + Input.Keys.toString(keycode));
                        Gdx.input.setInputProcessor(stage);
                        return true;
                    }
                });
            }
        });

        changeKeyLeftButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.input.setInputProcessor(new InputAdapter() {
                    @Override
                    public boolean keyDown(int keycode) {
                        KeyBinding.MOVE_LEFT = keycode;
                        changeKeyLeftButton.setText("Key Left: " + Input.Keys.toString(keycode));
                        Gdx.input.setInputProcessor(stage);
                        return true;
                    }
                });
            }
        });

        changeKeyRightButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.input.setInputProcessor(new InputAdapter() {
                    @Override
                    public boolean keyDown(int keycode) {
                        KeyBinding.MOVE_RIGHT = keycode;
                        changeKeyRightButton.setText("Key Right: " + Input.Keys.toString(keycode));
                        Gdx.input.setInputProcessor(stage);
                        return true;
                    }
                });
            }
        });

        float delay = 0f;
        Actor[] actors = {
            SettingMenu,
            showMusicVolumeLabel, MusicVolumeSlider,
            new Label("Select Music", skin), musics,
            playMusicButton, stopMusicButton,
            SFXButton, autoReload, blackAndWhiteMode,


        };

        for (Actor actor : actors) {
            actor.getColor().a = 0f;
            actor.addAction(Actions.sequence(
                Actions.delay(delay),
                Actions.fadeIn(0.3f)
            ));
            delay += 0.05f;
        }

        table.add(SettingMenu).colspan(2).padBottom(30).row();
        table.add(showMusicVolumeLabel);
        table.add(MusicVolumeSlider).width(200).row();
        table.add(new Label("Select Music", skin));
        table.add(musics).width(200).row();
        table.add(playMusicButton);
        table.add(stopMusicButton).row();
        table.add(SFXButton).colspan(2).row();
        table.add(autoReload).colspan(2).row();
        table.add(blackAndWhiteMode).colspan(2).padBottom(20).row();

        table.add(changeKeyUpButton).colspan(2).padBottom(20).row();
        table.add(changeKeyDownButton).colspan(2).padBottom(20).row();
        table.add(changeKeyLeftButton).colspan(2).padBottom(20).row();
        table.add(changeKeyRightButton).colspan(2).padBottom(20).row();


        table.add(backButton).width(100).row();
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

    public SettingsMenuController getController() {
        return controller;
    }

    public Label getSettingMenu() {
        return SettingMenu;
    }

    public Slider getMusicVolumeSlider() {
        return MusicVolumeSlider;
    }

    public Label getShowMusicVolumeLabel() {
        return showMusicVolumeLabel;
    }

    public SelectBox getMusics() {
        return musics;
    }

    public TextButton getPlayMusicButton() {
        return playMusicButton;
    }

    public TextButton getStopMusicButton() {
        return stopMusicButton;
    }

    public CheckBox getSFXButton() {
        return SFXButton;
    }



    public CheckBox getAutoReload() {
        return autoReload;
    }

    public CheckBox getBlackAndWhiteMode() {
        return blackAndWhiteMode;
    }
}
