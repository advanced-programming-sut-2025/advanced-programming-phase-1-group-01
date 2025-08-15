package com.stardew_valley.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

public class Crow {
    public float x, y;
    public float speedX, speedY;
    public float size;
    private final Texture texture;
    public float alpha = 1f;
    public boolean fadingOut = false;

    public Crow(Texture texture) {
        this.texture = texture;
        this.size = 16f + (float)(Math.random() * 16);
        this.x = (float)(Math.random() * Gdx.graphics.getWidth());
        this.y = (float)(Math.random() * Gdx.graphics.getHeight());
        this.speedX = -100 + (float)(Math.random() * 200);
        this.speedY = -50 + (float)(Math.random() * 80);
    }

    public void update(float delta) {
        x += speedX * delta;
        y += speedY * delta;

        if (x < 0 || x > Gdx.graphics.getWidth()) speedX *= -1;
        if (y < 0 || y > Gdx.graphics.getHeight()) speedY *= -1;

        if (fadingOut) {
            alpha -= delta;
            if (alpha < 0) alpha = 0;
        }
    }

    public void draw(Batch batch) {
        batch.setColor(1f, 1f, 1f, alpha);
        batch.draw(texture, x, y, size, size);
        batch.setColor(1f, 1f, 1f, 1f);
    }

    public boolean isInvisible() {
        return alpha <= 0;
    }
}
