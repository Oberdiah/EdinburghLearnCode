package com.mygdx.game.rendering;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Main;
import com.mygdx.game.entites.Entity;
import com.mygdx.game.tile.Tile;
import com.mygdx.game.world.WorldGrid;

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
        Vector2 position = WorldGrid.playerEntity.physicsObject.getPosition();
        Main.cam.position.set(position.x, position.y, 0);
        Main.cam.update();

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

    /*
    public void create() {

        // Load the sprite sheet as a Texture
        walkSheet = new Texture(Gdx.files.internal("animation_sheet.png"));

        // Use the split utility method to create a 2D array of TextureRegions. This is
        // possible because this sprite sheet contains frames of equal size and they are
        // all aligned.
        TextureRegion[][] tmp = TextureRegion.split(walkSheet,
                walkSheet.getWidth() / FRAME_COLS,
                walkSheet.getHeight() / FRAME_ROWS);

        // Place the regions into a 1D array in the correct order, starting from the top
        // left, going across first. The Animation constructor requires a 1D array.
        TextureRegion[] walkFrames = new TextureRegion[FRAME_COLS * FRAME_ROWS];
        int index = 0;
        for (int i = 0; i < FRAME_ROWS; i++) {
            for (int j = 0; j < FRAME_COLS; j++) {
                walkFrames[index++] = tmp[i][j];
            }
        }

        // Initialize the Animation with the frame interval and array of frames
        walkAnimation = new Animation<TextureRegion>(0.025f, walkFrames);

        // Instantiate a SpriteBatch for drawing and reset the elapsed animation
        // time to 0
        spriteBatch = new SpriteBatch();
        stateTime = 0f;
    }
*/
    private void renderWorld() {
        for (int xSqr = 0; xSqr < WorldGrid.worldWidth; xSqr++)
        {
            for (int ySqr = 0; ySqr < WorldGrid.worldHeight; ySqr++)
            {
                if (Main.worldGrid.getWorldArray()[xSqr][ySqr].type == Tile.TileType.ROCK)
                {
                    batch.draw(rockblock, xSqr, ySqr, 1, 1);
                }
            }
        }
    }

    public void dispose() {
        batch.dispose();
        rockblock.dispose();
    }
}
