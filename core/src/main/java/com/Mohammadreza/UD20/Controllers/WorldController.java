package com.Mohammadreza.UD20.Controllers;

import com.Mohammadreza.UD20.Main;
import com.Mohammadreza.UD20.Models.GameAssetManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class WorldController {

    private PlayerController playerController;
    private Texture backgroundTexture;
    private final float mapWidth;
    private final float mapHeight;
    private Stage uiStage;
    private float remainingTime;
    private final float maxTime = 1200f;

    private OrthographicCamera camera;
    private Viewport viewport;

    public WorldController(PlayerController playerController) {
        this.playerController = playerController;
        this.backgroundTexture = new Texture("background.png");
        this.mapWidth = backgroundTexture.getWidth();
        this.mapHeight = backgroundTexture.getHeight();

        camera = new OrthographicCamera();
        viewport = new FitViewport(800, 600, camera);
        camera.position.set(viewport.getWorldWidth() / 2, viewport.getWorldHeight() / 2, 0);
        camera.update();


    }

    public void update() {
        camera.position.set(playerController.getPlayer().getPosX(), playerController.getPlayer().getPosY(), 0);
        camera.update();
        SpriteBatch batch = Main.getBatch();
        batch.setProjectionMatrix(camera.combined);
        batch.draw(backgroundTexture, 0, 0);
    }

    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    public OrthographicCamera getCamera() {
        return camera;
    }

    public PlayerController getPlayerController() {
        return playerController;
    }

    public float getMapHeight() {
        return mapHeight;
    }

    public float getMapWidth() {
        return mapWidth;
    }

    public float getRemainingTime() {
        return remainingTime;
    }

}

