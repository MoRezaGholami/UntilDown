package com.Mohammadreza.UD20.Models;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

public class Explosion {
    private float x, y;
    private float time = 0;
    private boolean finished = false;

    public Explosion(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void update(float delta) {
        time += delta;
        if (GameAssetManager.explosionAnimation.isAnimationFinished(time)) {
            finished = true;
        }
    }

    public void draw(Batch batch) {
        Texture frame = GameAssetManager.explosionAnimation.getKeyFrame(time);
        batch.draw(frame, x, y);
    }

    public boolean isFinished() {
        return finished;
    }
}
