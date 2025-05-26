package com.Mohammadreza.UD20;

import com.Mohammadreza.UD20.Controllers.MenuControllers.SignUpMenuController;
import com.Mohammadreza.UD20.Models.GameAssetManager;
import com.Mohammadreza.UD20.Views.SignUpMenu;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends Game {
    private static Main main;
    public static ShaderProgram grayscaleShader;


    private static SpriteBatch batch;


    @Override
    public void create() {
        main = this;
        batch = new SpriteBatch();
        ShaderProgram.pedantic = false;
        grayscaleShader = new ShaderProgram(
            Gdx.files.internal("shaders/default.vert"),
            Gdx.files.internal("shaders/grayscale.frag")
        );
        if (!grayscaleShader.isCompiled()) {
            System.out.println("Shader compilation failed: " + grayscaleShader.getLog());
        }
        getMain().setScreen(new SignUpMenu(new SignUpMenuController(), GameAssetManager.getSkin()));
    }

    @Override
    public void render() {
        super.render();


    }

    @Override
    public void dispose() {
        batch.dispose();
        if (grayscaleShader != null) {
            grayscaleShader.dispose();
        }

    }
    public static Main getMain() {
        return main;
    }

    public static SpriteBatch getBatch() {
        return batch;
    }
}
