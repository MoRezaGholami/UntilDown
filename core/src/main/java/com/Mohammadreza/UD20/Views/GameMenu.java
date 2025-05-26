package com.Mohammadreza.UD20.Views;

import com.Mohammadreza.UD20.Controllers.MenuControllers.GameMenuController;
import com.Mohammadreza.UD20.Main;
import com.Mohammadreza.UD20.Models.GameAssetManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class GameMenu implements Screen , InputProcessor {
    private Stage stage;
    private GameMenuController controller;
    private float showLevel = 0;
    private Texture cursorTexture ;
    private Sprite cursorSprite;
    private Camera camera;
    private ShapeRenderer shapeRenderer;

    public GameMenu(GameMenuController controller, Skin skin) {
        this.controller = controller;
        controller.setView(this);
        this.cursorTexture = GameAssetManager.cursorTexture;
        this.cursorSprite = new Sprite(cursorTexture);
        this.camera = controller.getWorldController().getCamera();
        this.shapeRenderer = new ShapeRenderer();

        Pixmap pm = new Pixmap(Gdx.files.internal("cursor.png"));
        Gdx.graphics.setCursor(Gdx.graphics.newCursor(pm, pm.getWidth() / 2, pm.getHeight() / 2));
        pm.dispose();
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        InputMultiplexer multiplexer = new InputMultiplexer();
        multiplexer.addProcessor(stage);
        multiplexer.addProcessor(this);
        Gdx.input.setInputProcessor(multiplexer);
    }


    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);
        if (GameAssetManager.blackAndWhiteEnabled) {
            Main.getBatch().setShader(Main.grayscaleShader);
        } else {
            Main.getBatch().setShader(null);
        }



        Main.getBatch().begin();
        controller.updateGame(delta);
        controller.renderUI();

        Vector3 mousePos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
        camera.unproject(mousePos);

        cursorSprite.setPosition(mousePos.x - cursorSprite.getWidth() / 2f,
            mousePos.y - cursorSprite.getHeight() / 2f);
        cursorSprite.draw(Main.getBatch());
        Main.getBatch().end();
        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);

        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);


        float playerX = controller.getWorldController().getPlayerController().getPlayer().getPosX();
        float playerY = controller.getWorldController().getPlayerController().getPlayer().getPosY();


        shapeRenderer.setColor(1, 1, 0, 0.3f);


        shapeRenderer.circle(playerX, playerY, 150);

        shapeRenderer.end();

        Gdx.gl.glDisable(GL20.GL_BLEND);


        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();

    }

    @Override
    public void resize(int width, int height) {
        controller.getWorldController().resize(width, height);
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

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        controller.getWeaponController().handleWeaponShoot(screenX, screenY);
        if(GameAssetManager.sfxEnabled && !controller.isPaused()){
            GameAssetManager.shootSFX.play(1.0f);

        }
        return false;
    }


    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        controller.getWeaponController().handleWeaponRotation(screenX, screenY);
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}
