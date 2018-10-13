package com.mygdx.game.rendering;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Main;
import com.mygdx.game.entites.Entity;
import com.mygdx.game.world.World;

public class MainRenderer {
    SpriteBatch batch;
    Texture img;

    public void init() {
        batch = new SpriteBatch();
        img = new Texture("player.png");
    }

    public void render() {
        batch.begin();

        renderWorld();
        renderEntities();

        batch.end();
    }

    private void renderEntities() {
        for (Entity e : Main.world.getEntityArrayList()) {
            batch.draw(img, 0, 0);
        }
    }

    private void renderWorld() {
        for (int xSqr = 0; xSqr < World.worldWidth; xSqr++)
        {
            for (int ySqr = 0; ySqr < World.worldHeight; ySqr++)
            {
                if (Main.world.getWorldArray()[xSqr][ySqr] != 0)
                {
                    batch.draw(img, xSqr*10, ySqr*10);
                }
            }
        }
    }

    public void dispose() {
        batch.dispose();
        img.dispose();
    }
}
