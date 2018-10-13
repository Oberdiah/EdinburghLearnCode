package com.mygdx.game.rendering;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Main;
import com.mygdx.game.entites.Entity;
import com.mygdx.game.tile.Tile;
import com.mygdx.game.world.World;

public class MainRenderer {
    SpriteBatch batch;
    public static Texture img;

    public void init() {
        batch = new SpriteBatch();
        img = new Texture("player.png");
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
        Main.cam = new OrthographicCamera(30, 30 * (h / w));

        Main.cam.position.set(Main.cam.viewportWidth / 2f, Main.cam.viewportHeight / 2f, 0);
        Main.cam.update();
    }

    public void render() {
        Main.inputHandler.handleInput(Main.cam);
        Main.cam.update();
        batch.setProjectionMatrix(Main.cam.combined);

        batch.begin();

        renderWorld();
        renderEntities();

        batch.end();
    }

    private void renderEntities() {
        for (Entity e : Main.world.getEntityArrayList()) {
            e.sprite.draw(batch);
        }
    }

    private void renderWorld() {
        for (int xSqr = 0; xSqr < World.worldWidth; xSqr++)
        {
            for (int ySqr = 0; ySqr < World.worldHeight; ySqr++)
            {
                if (Main.world.getWorldArray()[xSqr][ySqr] == Tile.TileType.Rock)
                {
                    batch.draw(img, xSqr*16, ySqr*16);
                }
            }
        }
    }

    public void dispose() {
        batch.dispose();
        img.dispose();
    }
}
