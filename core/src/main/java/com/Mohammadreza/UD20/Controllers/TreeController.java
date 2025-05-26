package com.Mohammadreza.UD20.Controllers;

import com.Mohammadreza.UD20.Main;
import com.Mohammadreza.UD20.Models.Monster;
import com.Mohammadreza.UD20.Models.Player;
import com.Mohammadreza.UD20.Models.Tree;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;

import java.util.ArrayList;
import java.util.Random;

public class TreeController {
    private ArrayList<Tree> trees = new ArrayList<>();
    private final WorldController worldController;
    private Player player;

    public TreeController(WorldController worldController) {
        this.worldController = worldController;
        this.player = worldController.getPlayerController().getPlayer();
    }

    public ArrayList<Tree> getTrees() {
        return trees;
    }

    public WorldController getWorldController() {
        return worldController;
    }

    public void update(float delta) {
        for (Tree tree : trees) {
            tree.getSprite().draw(Main.getBatch());
            idleAnimation(tree);

            tree.setDamageCooldown(tree.getDamageCooldown() + delta);

            if (getDistance(tree) <= 60f) {


                if (tree.getDamageCooldown() >= 1f) {
                    player.getHero().decreaseHp(tree.getDamage());
                    tree.setDamageCooldown(0);
                }
            }
        }

    }

    public void spawnTree(float mapWidth, float mapHeight) {
        Random rand = new Random();
        int count = rand.nextInt(10) + 2;
        for (int i = 0; i < count; i++) {
            float x = (float) Math.random() * mapWidth;
            float y = (float) Math.random() * mapHeight;
            Tree tree = new Tree(x, y);
            trees.add(tree);
        }
    }

    public void idleAnimation(Tree tree) {
        Animation<Texture> treeAnimation = tree.getAnimation();
        tree.getSprite().setRegion(treeAnimation.getKeyFrame(tree.getTime()));

        if (!treeAnimation.isAnimationFinished(tree.getTime())) {
            tree.setTime(tree.getTime() + Gdx.graphics.getDeltaTime());
        }
        else {
            tree.setTime(0);
        }

        treeAnimation.setPlayMode(Animation.PlayMode.LOOP);
    }

    public void render(){

    }

    public float getDistance(Tree ms) {
        float dx = player.getPosX() - ms.getPosX();
        float dy = player.getPosY() - ms.getPosY();
        return (float) Math.sqrt(dx*dx + dy*dy);
    }
}
