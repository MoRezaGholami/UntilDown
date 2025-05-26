package com.Mohammadreza.UD20.Controllers.MenuControllers;

import com.Mohammadreza.UD20.Main;
import com.Mohammadreza.UD20.Models.GameAssetManager;
import com.Mohammadreza.UD20.Views.MainMenu;
import com.Mohammadreza.UD20.Views.SettingsMenu;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.utils.Timer;

public class SettingsMenuController {
    private SettingsMenu view;
    private String forwardKey, backwardKey, leftKey, rightKey;
    private Music currentMusic;

    public void setKeySelectBoxes(SelectBox<String> f, SelectBox<String> b, SelectBox<String> l, SelectBox<String> r) {
        this.forwardKey = f.getSelected();
        this.backwardKey = b.getSelected();
        this.leftKey = l.getSelected();
        this.rightKey = r.getSelected();
    }

    public void setForwardKey(String key) {
        this.forwardKey = key;
    }

    public void setBackwardKey(String key) {
        this.backwardKey = key;
    }

    public void setLeftKey(String key) {
        this.leftKey = key;
    }

    public void setRightKey(String key) {
        this.rightKey = key;
    }

    public String getForwardKey() {
        return forwardKey;
    }

    public String getBackwardKey() {
        return backwardKey;
    }

    public String getLeftKey() {
        return leftKey;
    }

    public String getRightKey() {
        return rightKey;
    }


    public void setView(SettingsMenu view) {
        this.view = view;
        setupListeners();

    }

    public void setupListeners() {
        view.getMusicVolumeSlider().addListener((event) -> {
            if (currentMusic != null) {
                currentMusic.setVolume(view.getMusicVolumeSlider().getValue());
            }
            return true;
        });

        view.getPlayMusicButton().addListener((event) -> {
            String selected = view.getMusics().getSelected().toString();
            PlayMusic(selected);
            return true;
        });

        view.getStopMusicButton().addListener((event) -> {
            if (currentMusic != null) {
                currentMusic.stop();
            }
            return true;
        });
    }

    public void PlayMusic(String name) {
        if (currentMusic != null) {
            currentMusic.stop();
        }

        currentMusic = GameAssetManager.getMusicByName(name);
        currentMusic.setLooping(true);
        currentMusic.setVolume(view.getMusicVolumeSlider().getValue());
        currentMusic.play();
    }


    public Music getCurrentMusic() {
        return currentMusic;
    }

    public void back() {
        if (view != null) {
            Dialog successfullyRegister = new Dialog("", GameAssetManager.getSkin());
            successfullyRegister.text("you back to main menu");
            successfullyRegister.getColor().a = 0;
            successfullyRegister.show(view.getStage());
            float x = (Gdx.graphics.getWidth() - successfullyRegister.getWidth()) / 2f;
            float y = 50;
            successfullyRegister.setPosition(x, y);
            successfullyRegister.addAction(Actions.fadeIn(1f));


            Timer.schedule(new Timer.Task() {
                @Override
                public void run() {
                    successfullyRegister.addAction(Actions.sequence(
                        Actions.fadeOut(1f),
                        Actions.run(() -> {
                            successfullyRegister.hide();

                            MainMenuController controller = new MainMenuController();
                            MainMenu mainMenu = new MainMenu(controller, GameAssetManager.getSkin());
                            Main.getMain().setScreen(mainMenu);


                        })
                    ));
                }
            }, 2);
        }
    }
}
