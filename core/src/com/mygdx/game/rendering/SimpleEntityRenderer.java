package com.mygdx.game.rendering;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Main;
import com.mygdx.game.entites.Entity;

public class SimpleEntityRenderer {
    SpriteBatch batch;
    Texture img;

    public void init() {
        batch = new SpriteBatch();
        img = new Texture("player.png");
    }

    public void render() {
        batch.begin();
        for (Entity e : Main.world.getEntityArrayList())
        {
            batch.draw(img, 0, 0);
        }

        batch.end();
    }

    public void dispose() {
        batch.dispose();
        img.dispose();
    }
}
