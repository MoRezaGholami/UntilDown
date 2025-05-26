package com.Mohammadreza.UD20.Views;

import com.Mohammadreza.UD20.Controllers.MenuControllers.ProfileMenuController;
import com.Mohammadreza.UD20.Models.FilePicker;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import java.io.File;

public class ProfileMenu implements Screen {
    private final Stage stage;
    private final ProfileMenuController controller;
    private final Table table;
    private final Label label;

    private final TextField changeUsernameField;
    private final TextField changePasswordField;
    private TextButton changePasswordButton;
    private final TextButton deleteAccountButton;
    private final TextButton changeUsernameButton;
    private final SelectBox<String> changeAvatarSelect;
    private final TextButton changeAvatarButton;
    private final TextButton uploadAvatarButton;
    private final TextButton backButton;

    public ProfileMenu(ProfileMenuController controller, Skin skin) {
        this.controller = controller;
        this.stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        label = new Label("Profile Menu", skin);

        changeUsernameField = new TextField("", skin);
        changeUsernameField.setMessageText("New username");

        changePasswordField = new TextField("", skin);
        changePasswordField.setMessageText("New password");

        changeUsernameButton = new TextButton("Change Username", skin);
        changeUsernameButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                controller.handleNewUsername();
            }
        });

        changePasswordButton = new TextButton("Change Password", skin);
        changePasswordButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                controller.handleNewPassword();
            }
        });
        deleteAccountButton = new TextButton("Delete Account", skin);
        deleteAccountButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                controller.handleDeleteAccount();
            }
        });
        changeAvatarButton = new TextButton("Change Avatar", skin);
        changeAvatarButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                controller.handleChangeAvatar();
            }
        });
        uploadAvatarButton = new TextButton("Upload Avatar", skin);
        uploadAvatarButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                File file = FilePicker.pickImageFile();
                if (file != null) {
                    controller.uploadAvatar(file);
                }
            }
        });

        backButton = new TextButton("Back", skin);
        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                controller.backToSettingMenu();
            }
        });

        changeAvatarSelect = new SelectBox<>(skin);
        Array<String> avatars = new Array<>();
        avatars.addAll("shana", "dasher", "scarlet", "diamond", "lilith");
        changeAvatarSelect.setItems(avatars);

        table = new Table();
        table.setFillParent(true);
        table.center();

        table.add(label).colspan(2).padBottom(30).row();
        table.add(new Label("Change Username:", skin)).pad(10);
        table.add(changeUsernameField).width(200).pad(10).row();
        table.add(changeUsernameButton).colspan(2).pad(10).row();
        table.add(new Label("Change Password:", skin)).pad(10);
        table.add(changePasswordField).width(200).pad(10).row();
        table.add(changePasswordButton).colspan(2).pad(10).row();
        table.add(new Label("Change Avatar:", skin)).pad(10);
        table.add(changeAvatarSelect).width(200).pad(10).row();
        table.add(changeAvatarButton).colspan(2).pad(10).row();
        table.add(uploadAvatarButton).colspan(3).pad(10).row();
        table.add(deleteAccountButton).colspan(2).padTop(30).row();
        table.add(backButton).colspan(2).padTop(30).row();

        stage.addActor(table);

        controller.setView(this);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() { }

    @Override
    public void resume() { }

    @Override
    public void hide() { }

    @Override
    public void dispose() {
        stage.dispose();
    }

    public Stage getStage() { return stage; }
    public SelectBox<String> getChangeAvatarSelect() { return changeAvatarSelect; }
    public TextButton getChangeUsernameButton() { return changeUsernameButton; }
    public TextButton getDeleteAccountButton() { return deleteAccountButton; }
    public TextButton getChangePasswordButton() { return changePasswordButton; }
    public TextField getChangePasswordField() { return changePasswordField; }
    public TextField getChangeUsernameField() { return changeUsernameField; }
    public Label getLabel() { return label; }
    public ProfileMenuController getController() { return controller; }
    public TextButton getChangeAvatarButton() { return changeAvatarButton; }
}

