package com.Mohammadreza.UD20.Controllers.MenuControllers;

import com.Mohammadreza.UD20.Controllers.*;
import com.Mohammadreza.UD20.Main;
import com.Mohammadreza.UD20.Models.*;
import com.Mohammadreza.UD20.Views.EndGameMenu;
import com.Mohammadreza.UD20.Views.GameMenu;
import com.Mohammadreza.UD20.Views.MainMenu;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class GameMenuController {
    private GameMenu view;
    private PlayerController playerController;
    private WorldController worldController;
    private WeaponController weaponController;
    private MonsterController monsterController;
    private TreeController treeController;
    private MonsterSeedController monsterSeedController;
    private float remainingTime = App.PreGame.getGameTime();
    private final float maxGameTime = 20 * 60;
    private Stage uiStage;
    private Label timerLabel;
    private Label increasedLevelLabel;
    private Label showLastEarnedAbility;
    private float timeShowIncreasedLevel = 0;
    private boolean showLevelUpMessage = false;
    private float levelUpMessageTimer = 0;
    private Texture heartTexture;
    private Group heartGroup;
    private Label killCounterLabel;
    private Label ammoCounterLabel;
    private ProgressBar xpProgressBar;
    private Label xpLabel;
    private TextButton pauseTextButton;
    public boolean isPaused = false;
    private Window pauseWindow;



    public void setView(GameMenu view) {
        this.view = view;
        playerController = new PlayerController(App.PrePlayer  ,this);
        worldController = new WorldController(playerController);
        playerController.setWorldController(worldController);
        treeController = new TreeController(worldController);
        treeController.spawnTree(getWorldController().getMapWidth(), getWorldController().getMapHeight());
        monsterSeedController = new MonsterSeedController(worldController);
        monsterController = new MonsterController(worldController, monsterSeedController);
        playerController.setMonsterController(monsterController);

        weaponController = new WeaponController(playerController.getPlayer().getWeapon(), monsterController, worldController);

        uiStage = new Stage(new ScreenViewport());
        Skin skin = GameAssetManager.getSkin();
        timerLabel = new Label("Time Remaining: ", skin);
        increasedLevelLabel = new Label("congratulations you have increased level", skin);
        showLastEarnedAbility = new Label("", skin);
        killCounterLabel = new Label("", skin);
        ammoCounterLabel = new Label("", skin);
        increasedLevelLabel.setPosition(50, Gdx.graphics.getHeight() - 80);
        timerLabel.setPosition(30, Gdx.graphics.getHeight() - 30);
        killCounterLabel.setPosition(30, Gdx.graphics.getHeight() - 180);
        ammoCounterLabel.setPosition(30, Gdx.graphics.getHeight() - 230);
        showLastEarnedAbility.setPosition(30, Gdx.graphics.getHeight() - 330);
        heartTexture = GameAssetManager.heartTex;
        heartGroup = new Group();
        heartGroup.setPosition(30 , Gdx.graphics.getHeight() - 130 );
        xpProgressBar = new ProgressBar(0 , 1 , 0.01f, false, skin);
        xpProgressBar.setSize(200 , 20);
        xpProgressBar.setPosition(30 , Gdx.graphics.getHeight() - 280);
        xpLabel = new Label("", skin);
        xpLabel.setPosition(240 , Gdx.graphics.getHeight() - 280);
        pauseTextButton = new TextButton("Pause", skin);
        pauseTextButton.setPosition(Gdx.graphics.getWidth() - 150, Gdx.graphics.getHeight() - 50);
        pauseTextButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                setPaused(true);
                showPauseMenu();
            }
        });

        uiStage.addActor(heartGroup);
        uiStage.addActor(timerLabel);
        uiStage.addActor(killCounterLabel );
        uiStage.addActor(ammoCounterLabel);
        uiStage.addActor(xpProgressBar);
        uiStage.addActor(xpLabel);
        uiStage.addActor(pauseTextButton);



    }

    public void updateGame(float delta) {
        if (isPaused) {
            uiStage.act(delta);
            return;
        }
        remainingTime -= delta;
        if(remainingTime <= 0){
            Screen screen = Main.getMain().getScreen();

            EndGameMenuController endGameMenuController = new EndGameMenuController();
            EndGameMenu endGameMenu = new EndGameMenu(endGameMenuController , this , GameAssetManager.getSkin() , true);
            Main.getMain().setScreen(endGameMenu);
            screen.dispose();
            return;


        }

        if(playerController.getPlayer().getHero().getHp() <= 0 ){
            Screen screen = Main.getMain().getScreen();
            EndGameMenuController endGameMenuController = new EndGameMenuController();
            EndGameMenu endGameMenu = new EndGameMenu(endGameMenuController ,this , GameAssetManager.getSkin() , false);
            Main.getMain().setScreen(endGameMenu);
            screen.dispose();
            return;
        }


        int minutes = (int) (remainingTime / 60);
        int seconds = (int) (remainingTime % 60);
        timerLabel.setText(String.format("Time Left: %02d:%02d", minutes, seconds));
        killCounterLabel.setText("your kills : " + playerController.getPlayer().getKill());
        ammoCounterLabel.setText("your ammo : " + playerController.getPlayer().getWeapon().getAmmo());


        Hero hero = playerController.getPlayer().getHero();
        int currentHp = hero.getHp();
        heartGroup.clear();

        for (int i = 0; i < currentHp; i++) {
            Image heart = new Image(heartTexture);
            heart.setSize(20, 20);
            heart.setPosition(i * 22, 0);
            heartGroup.addActor(heart);
        }
        int currentXp = hero.getXp();
        int xpForNextLevel = hero.neededXpForNextLevel(hero.getLevel());
        float progress = (float) currentXp / xpForNextLevel;
        xpProgressBar.setValue(progress);
        xpLabel.setText(currentXp + " / " + xpForNextLevel + " XP");

        if (hero.isIncreasedLevel()) {
            showLevelUpMessage = true;
            levelUpMessageTimer = 0;
            hero.setIncreasedLevel(false);
            increasedLevelLabel.setText("Congratulations! You reached level " + hero.getLevel());
            showLastEarnedAbility.setText("Congratulations! you earned new Ability " + hero.getLastAbility().getName());
            if (!uiStage.getActors().contains(increasedLevelLabel, true)
                && !uiStage.getActors().contains(showLastEarnedAbility, true)) {
                uiStage.addActor(increasedLevelLabel);
                uiStage.addActor(showLastEarnedAbility);
            }
        }


        if (showLevelUpMessage) {
            levelUpMessageTimer += delta;
            if (levelUpMessageTimer >= 4f) {
                increasedLevelLabel.remove();
                showLastEarnedAbility.remove();
                showLevelUpMessage = false;
            }
        }




        uiStage.act(delta);
        if (view != null) {
            worldController.update();
            playerController.update(delta);
            weaponController.update(delta);
            treeController.update(delta);

            monsterController.update(delta);
            monsterSeedController.update(delta);
        }
    }

    public PlayerController getPlayerController() {
        return playerController;
    }

    public WeaponController getWeaponController() {
        return weaponController;
    }

    public MonsterController getMonsterController() {
        return monsterController;
    }

    public WorldController getWorldController() {
        return worldController;
    }

    public void renderUI(){
        uiStage.draw();
    }

    public void dispose() {
        uiStage.dispose();
        heartTexture.dispose();
    }

    public TreeController getTreeController() {
        return treeController;
    }

    public MonsterSeedController getMonsterSeedController() {
        return monsterSeedController;
    }

    public boolean isPaused() {
        return isPaused;
    }

    public void setPaused(boolean isPaused) {
        this.isPaused = isPaused;
    }

    public void showPauseMenu(){
        if(pauseWindow != null){
            return;
        }

        Skin skin = GameAssetManager.getSkin();
        pauseWindow = new Window("Paused", skin);
        pauseWindow.setSize(1280, 720);

        TextButton resumeButton = new TextButton("Resume", skin);
        resumeButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                setPaused(false);
                pauseWindow.remove();
                pauseWindow = null;
            }
        });
        pauseWindow.add(resumeButton).pad(10);
        pauseWindow.row();

        Label showAbilityLabel = new Label("Show Ability", skin);
        StringBuilder sb = new StringBuilder();
        for(Ability ab : playerController.getPlayer().getAbilities()){
            sb.append(ab.getName());
            sb.append("\n");
        }
        showAbilityLabel.setText(sb.toString());
        pauseWindow.add(showAbilityLabel).pad(10);


        Label showCheatCodes = new Label("Show Cheat Codes", skin);
        sb = new StringBuilder();
        sb.append("press X for add 30 ammo");
        sb.append("\n");
        sb.append("press H  for health");
        sb.append("\n");
        sb.append("press T for decrease time of the game");
        sb.append("\n");
        sb.append("press L for increase Xp");
        sb.append("\n");
        showCheatCodes.setText(sb.toString());
        pauseWindow.add(showCheatCodes).pad(10);

        TextButton giveUpButton = new TextButton("Give Up", skin);
        giveUpButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Dialog giveUpDialog = new Dialog("Give Up", skin);
                giveUpDialog.text("you gave up");
                giveUpDialog.button("Yes");
                giveUpDialog.show(uiStage);
                Screen screen = Main.getMain().getScreen();
                MainMenuController menuController = new MainMenuController();
                MainMenu mainMenu = new MainMenu(menuController , GameAssetManager.getSkin());
                Main.getMain().setScreen(mainMenu);
                screen.dispose();
                App.setPreGame(null);
            }
        });
        pauseWindow.add(giveUpButton).pad(10);

        pauseWindow.setPosition(
            (Gdx.graphics.getWidth() - pauseWindow.getWidth()) / 2,
            (Gdx.graphics.getHeight() - pauseWindow.getHeight()) / 2
        );

        uiStage.addActor(pauseWindow);
    }

    public Stage getUiStage(){
        return uiStage;
    }


    public GameMenu getView(){
        return view;
    }

    public float getRemainingTime() {
        return remainingTime;
    }

    public void setRemainingTime(float remainingTime){
        this.remainingTime = remainingTime;
    }
}
