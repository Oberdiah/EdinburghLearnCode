package com.mygdx.game.rendering;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Main;
import com.mygdx.game.entites.Entity;
import com.mygdx.game.world.WorldGrid;
import com.mygdx.game.tile.Tile;

public class MainRenderer {
    public static SpriteBatch batch;
    public static Texture rockblock;

    public static final int BLOCKPIXELSIZE = 32;

    public void init() {
        batch = new SpriteBatch();
        rockblock = new Texture("rockblock.png");
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
        Main.cam = new OrthographicCamera(30, 30 * (h / w));

        Main.cam.position.set(Main.cam.viewportWidth / 2f, Main.cam.viewportHeight / 2f, 0);
        Main.cam.update();
    }

    public void render() {
        batch.setProjectionMatrix(Main.cam.combined);

        batch.begin();

        renderWorld();

        //renderEntities();

        batch.end();
    }

    private void renderEntities() {
        for (Entity e : Main.worldGrid.getEntityArrayList()) {
            e.sprite.draw(batch);
        }
    }

    private void renderWorld() {
        for (int xSqr = 0; xSqr < WorldGrid.worldWidth; xSqr++)
        {
            for (int ySqr = 0; ySqr < WorldGrid.worldHeight; ySqr++)
            {
                if (Main.worldGrid.getWorldArray()[xSqr][ySqr].type == Tile.TileType.Rock)
                {
                    batch.draw(rockblock, xSqr*MainRenderer.BLOCKPIXELSIZE, ySqr*MainRenderer.BLOCKPIXELSIZE);
                }
            }
        }
    }

    public void dispose() {
        batch.dispose();
        rockblock.dispose();
    }
}
