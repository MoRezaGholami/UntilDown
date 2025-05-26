package com.Mohammadreza.UD20.Views;

import com.Mohammadreza.UD20.Controllers.KeyBinding;
import com.Mohammadreza.UD20.Controllers.MenuControllers.TalentMenuController;

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

public class TalentMenu implements Screen {
    private final TalentMenuController controller;
    private  Stage stage;
    private final TextButton backButton;
    private final Skin skin;


    public TalentMenu(TalentMenuController controller, Skin skin) {
        this.controller = controller;
        stage = new Stage();
        this.skin = skin;

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

        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);



        Label heroGuide = new Label("3 Heroes Guide:", skin);
        Label hero1 = new Label("- Shana: Strong tank with heavy hits", skin);
        Label hero2 = new Label("- Diamond: Slow but high damage", skin);
        Label hero3 = new Label("- Dasher: Healer and support", skin);

        Label keyboardGuide = new Label("Game Controls:", skin);
        Label key1 = new Label(String.format("- %s, %s, %s ,%s to move"
            , KeyBinding.getStringKeyboardWithNumber(KeyBinding.MOVE_UP)
            , KeyBinding.getStringKeyboardWithNumber(KeyBinding.MOVE_DOWN)
            , KeyBinding.getStringKeyboardWithNumber(KeyBinding.MOVE_RIGHT)
            , KeyBinding.getStringKeyboardWithNumber(KeyBinding.MOVE_LEFT)), skin);
        Label key2 = new Label("- click mouse to shoot", skin);
        Label key3 = new Label("- R to reload weapon", skin);

        Label cheatGuide = new Label("Cheat Codes:", skin);
        Label cheat1 = new Label("- X for Ammo , H for Health", skin);
        Label cheat2 = new Label("- T for Time , L for Xp", skin);

        Label abilityGuide = new Label("Abilities:", skin);
        Label ability1 = new Label("- Amocrease Increase weapon ammo capacity by 5 units: ", skin);
        Label ability2 = new Label("- Speedy : Double player movement speed for 10 seconds", skin);
        Label ability3 = new Label("- Damager : Increase weapon damage by 25% for 10 seconds", skin);
        Label ability4 = new Label("- Vitality : Increase max HP by 1 unit", skin);
        Label ability5 = new Label("- Procrease : Increase weapon projectile count by 1 unit", skin);





        table.add(heroGuide).left().row();
        table.add(hero1).left().row();
        table.add(hero2).left().row();
        table.add(hero3).left().padBottom(20).left().row();

        table.add(keyboardGuide).left().row();
        table.add(key1).left().row();
        table.add(key2).left().row();
        table.add(key3).left().padBottom(20).left().row();

        table.add(cheatGuide).left().row();
        table.add(cheat1).left().row();
        table.add(cheat2).left().padBottom(20).left().row();

        table.add(abilityGuide).left().row();
        table.add(ability1).left().row();
        table.add(ability2).left().padBottom(30).left().row();
        table.add(ability3).left().row();
        table.add(ability4).left().padBottom(30).left().row();
        table.add(ability5).left().row();

        table.add(backButton).center();
    }

    @Override
    public void render(float delta) {
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {
        stage.dispose();
    }

    public TalentMenuController getController() {
        return controller;
    }

    public TextButton getBackButton() {
        return backButton;
    }

    public Stage getStage() {
        return stage;
    }
}
