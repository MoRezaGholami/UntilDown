package com.Mohammadreza.UD20.Models;

import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;

public class App {
    public static ArrayList<Player> players = new ArrayList<>();
    public static Player PrePlayer ;
    public static ArrayList<PreGame> preGames = new ArrayList<>();
    public static PreGame PreGame;


    static {

        Player p1 = new Player("Mamad", "jdskfd");
        p1.addKill(23);
        p1.addScores(7800);
        p1.addTimeAlive(300);
        players.add(p1);
        Player p2 = new Player("Hossein", "jdskfd");
        p2.addKill(29);
        p2.addScores(8000);
        p2.addTimeAlive(320);
        players.add(p2);
        Player p3 = new Player("Sina", "jdskfd");
        players.add(p3);
        p3.addKill(25);
        p3.addScores(2900);
        p3.addTimeAlive(350);
        Player p4 = new Player("Sara", "jdskfd");
        p4.addKill(13);
        p4.addScores(3000);
        p4.addTimeAlive(200);
        players.add(p4);
        Player p5 = new Player("Zahra", "jdskfd");
        p5.addKill(90);
        p5.addScores(84955);
        p5.addTimeAlive(1200);
        players.add(p5);
        Player p6 = new Player("Asghar", "jdskfd");
        p6.addKill(23);
        p6.addScores(7800);
        p6.addTimeAlive(300);
        players.add(p6);
        Player p7 = new Player("Mohsen", "jdskfd");
        p7.addKill(2);
        p7.addScores(800);
        p7.addTimeAlive(30);
        players.add(p7);
        Player p8 = new Player("Kasra", "jdskfd");
        p8.addKill(60);
        p8.addScores(71800);
        p8.addTimeAlive(1100);
        players.add(p8);
        Player p9 = new Player("Emad", "jdskfd");
        p9.addKill(3);
        p9.addScores(700);
        p9.addTimeAlive(100);
        players.add(p9);
        Player p10 = new Player("Sana", "jdskfd");
        p10.addKill(0);
        p10.addScores(0);
        p10.addTimeAlive(5);
        players.add(p10);
    }

    public static void setPrePlayer(Player player) {
        PrePlayer = player;
    }
    public static void setPreGame(PreGame preGame) {
        PreGame = preGame;
    }


}
