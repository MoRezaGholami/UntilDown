package com.Mohammadreza.UD20.Models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import java.util.ArrayList;
import java.util.Collections;

public class Player {
    private String username;
    private String password;
    private ArrayList<Integer> scores = new ArrayList<>();
    private ArrayList<Integer> kills = new ArrayList<>();
    private ArrayList<Float> aliveTimes = new ArrayList<>();
    private int score;
    private float timeAlive;
    private Hero hero;
    private SecurityQuestion securityQuestion;
    private Texture playerTexture ;
    private Sprite playerSprite ;
    private float posX = 0;
    private float posY = 0;
    private int maximumHealth;
    private int maxAmmo;
    private CollisionRect rect ;
    private float time = 0;
    private float speed ;
    private int kill;
    private ArrayList<Ability> abilities = new ArrayList<>();
    private Weapon weapon;
    private boolean doubleSpeed = false;
    private boolean moreDamage = false;
    private boolean increaseProjectile = false;
    private Image avatarImage;
    private boolean increaseHealth = false;
    private boolean increaseAmmo = false;

    public Player(String username, String password , SecurityQuestion securityQuestion, Hero hero , Texture avatarTexture) {
        this.username = username;
        this.password = password;
        this.securityQuestion = securityQuestion;
        this.hero = hero;
        this.playerTexture = hero.getType().getTexture();
        this.playerSprite = new Sprite(playerTexture);
        this.speed =  hero.getSpeed();
        playerSprite.setPosition((float) Gdx.graphics.getWidth() / 2, (float) Gdx.graphics.getHeight() / 2);
        playerSprite.setSize(playerTexture.getWidth() , playerTexture.getHeight() );
        rect = new CollisionRect((float) Gdx.graphics.getWidth() / 2, (float) Gdx.graphics.getHeight(), playerTexture.getWidth() * 3, playerTexture.getHeight() * 3);
        this.maximumHealth = hero.getType().getHp();
        this.weapon = new Weapon(WeaponType.Revolver);
        this.maxAmmo = weapon.getAmmo();
        this.avatarImage = new Image(avatarTexture);
        addTimeAlive(0f);
        addScores(0);
        addKill(0);
    }

    // just for app players :
    public Player(String username , String Password){
        this.username = username;
        this.password = Password;
    }

    public void setHero(Hero hero) {
        this.hero = hero;
        this.playerTexture = hero.getType().getTexture();
        this.playerSprite = new Sprite(playerTexture);
        this.speed =  hero.getSpeed();
        this.maximumHealth = hero.getType().getHp();
    }


    public Hero getHero() {
        return hero;
    }

    public float getSpeed() {
        return speed;
    }

    private boolean isPlayerIdle = true;
    private boolean isPlayerRunning = false;



    public Texture getPlayerTexture() {
        return playerTexture;
    }

    public void setPlayerTexture(Texture playerTexture) {
        this.playerTexture = playerTexture;
    }

    public Sprite getPlayerSprite() {
        return playerSprite;
    }

    public void setPlayerSprite(Sprite playerSprite) {
        this.playerSprite = playerSprite;
    }

    public float getPosX() {
        return posX;
    }

    public void setPosX(float posX) {
        this.posX = posX;


    }

    public float getPosY() {
        return posY;
    }

    public void setPosY(float posY) {
        this.posY = posY;

    }

    public int getMaximumHealth() {
        return maximumHealth;
    }

    public CollisionRect getRect() {
        return rect;
    }

    public void setRect(CollisionRect rect) {
        this.rect = rect;
    }


    public boolean isPlayerIdle() {
        return isPlayerIdle;
    }

    public void setPlayerIdle(boolean playerIdle) {
        isPlayerIdle = playerIdle;
    }

    public boolean isPlayerRunning() {
        return isPlayerRunning;
    }

    public void setPlayerRunning(boolean playerRunning) {
        isPlayerRunning = playerRunning;
    }

    public float getTime() {
        return time;
    }

    public void setTime(float time) {
        this.time = time;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public SecurityQuestion getSecurityQuestion() {
        return securityQuestion;
    }

    public int getScore() {
        return score;
    }

    public void addKill(){
        kill++;
    }

    public int getKill() {
        return kill;
    }

    public void addAbility(Ability ability){
        abilities.add(ability);
        if(ability.equals(Ability.Vitality)){
            increaseHealth = true;
            maximumHealth ++;
        }
        if(ability.equals(Ability.Amocrease)){
            maxAmmo += 5;
            increaseAmmo = true;
        }
        if(ability.equals(Ability.Speedy)){
            doubleSpeed = true;
        }

        if(ability.equals(Ability.Damager)){
            moreDamage = true;
        }
        if(ability.equals(Ability.Procrease)){
            increaseProjectile = true;
        }
    }
    public ArrayList<Ability> getAbilities() {
        return abilities;
    }

    public void setWeapon(Weapon weapon){
        this.weapon = weapon;
        this.maxAmmo = weapon.getAmmo();
    }

    public Weapon getWeapon(){
        return weapon;
    }

    public boolean isDoubleSpeed() {
        return doubleSpeed;
    }
    public void setDoubleSpeed(boolean doubleSpeed) {
        this.doubleSpeed = doubleSpeed;
    }

    public boolean isMoreDamage() {
        return moreDamage;
    }

    public void setMoreDamage(boolean moreDamage) {
        this.moreDamage = moreDamage;
    }

    public int getMaxAmmo() {
        return maxAmmo;
    }

    public void setAvatarImage(Image avatarImage) {
        this.avatarImage = avatarImage;
    }

    public Image getAvatarImage() {
        return avatarImage;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public float getTimeAlive() {
        return time;
    }

    public void setTimeAlive(float timeAlive) {
        this.time = timeAlive;
    }

    public void addKill(int kill){
        this.kills.add(kill);
    }

    public void addTimeAlive(float timeAlive){
        this.aliveTimes.add(timeAlive);
    }

    public void addScores(int scores){
        this.scores.add(scores);
    }

    public ArrayList<Integer> getScores() {
        return scores;
    }

    public ArrayList<Integer> getKills() {
        return kills;
    }

    public ArrayList<Float> getAliveTimes() {
        return aliveTimes;
    }

    public void setKill(int kill) {
        this.kill = kill;
    }


    public int getMaxScore() {
        return Collections.max(scores);
    }

    public int getMaxKills() {
        return Collections.max(kills);
    }

    public float getMaxTimeAlive() {
        return Collections.max(aliveTimes);
    }

    public boolean isIncreaseProjectile() {
        return increaseProjectile;
    }

    public void setIncreaseProjectile(boolean increaseProjectile) {
        this.increaseProjectile = increaseProjectile;
    }

    public boolean isIncreaseHealth() {
        return increaseHealth;
    }

    public void setIncreaseHealth(boolean increaseHealth) {
        this.increaseHealth = increaseHealth;
    }

    public boolean isIncreaseAmmo() {
        return increaseAmmo;
    }

    public void setIncreaseAmmo(boolean increaseAmmo) {
        this.increaseAmmo = increaseAmmo;
    }
}
