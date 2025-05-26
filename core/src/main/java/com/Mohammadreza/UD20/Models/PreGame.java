package com.Mohammadreza.UD20.Models;

public class PreGame {
    private final Player player;
    private int duration;
    private float gameTime;

    public PreGame(Player player , float gameTime) {
        this.player = player;
        this.gameTime = gameTime;
    }
    public Player getPlayer() {
        return player;
    }

    public void setGameTime(float gameTime) {
        this.gameTime = gameTime;
    }

    public float getGameTime() {
        return gameTime;
    }
}
