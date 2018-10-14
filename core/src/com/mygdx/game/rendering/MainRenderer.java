package com.mygdx.game.rendering;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.mygdx.game.Main;
import com.mygdx.game.entites.Entity;
import com.mygdx.game.tile.Tile;
import com.mygdx.game.world.WorldGrid;

public class MainRenderer {
    public static SpriteBatch batch;
    public static Texture rockblock;
    public static Texture squareblock;
    public static Texture ufoSheet;

    public void init() {
        batch = new SpriteBatch();
        rockblock = new Texture("rockblock.png");
        squareblock = new Texture("square.png");
        ufoSheet = new Texture("flyingsaucer.png");
        sky = new Texture("skyblock.png");
        sky.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
        Main.cam = new OrthographicCamera(30, 30 * (h / w));

        Main.cam.position.set(Main.cam.viewportWidth / 2f, Main.cam.viewportHeight / 2f, 0);
        Main.cam.update();

        create();
    }

    public void render() {
        Vector2 position = WorldGrid.playerEntity.physicsObject.getPosition();
        Main.cam.position.set(position.x, position.y, 0);
        Main.cam.update();
        stateTime += Gdx.graphics.getDeltaTime();
        batch.setProjectionMatrix(Main.cam.combined);

        batch.begin();

        renderWorld();

        renderEntities();

        //System.out.println(Gdx.graphics.getFramesPerSecond() + "fps");

        batch.end();
    }

    Animation<TextureRegion> walkAnimation;
    Animation<TextureRegion> ufoAnimation;
    Texture walkSheet;
    float stateTime;

    private void renderEntities() {
        for (Entity e : Main.worldGrid.getEntityArrayList()) {
            // Get current frame of animation for the current stateTime

            Body physicsObj = e.physicsObject;
            Vector2 position = physicsObj.getPosition();

            if (e.type.equals("Player")) {
                float width = 1;
                float height = 2;
                TextureRegion currentFrame;
                currentFrame = walkAnimation.getKeyFrame(stateTime, true);
                batch.draw(currentFrame, position.x-width/2, position.y-height/2, width/2, height/2, width, height, 1,1,physicsObj.getAngle()*180/3.14159f); // Draw current frame at (50, 50)
            } else if (e.type.equals("Square")) {
                float width = 1;
                float height = 1;
                batch.draw(new TextureRegion(squareblock), position.x-width/2, position.y-height/2, width/2, height/2, width, height, 1,1,physicsObj.getAngle()*180/3.14159f); // Draw current frame at (50, 50)
            } else if (e.type.equals("Floatie")) {
                float width = 1;
                float height = 1;
                TextureRegion currentFrame = ufoAnimation.getKeyFrame(stateTime,true);
                batch.draw(currentFrame, position.x-width/2, position.y-height/2, width/2, height/2, width, height, 1,1,physicsObj.getAngle()*180/3.14159f); // Draw current frame at (50, 50)
            } else  {//default
                float width = 1;
                float height = 1;
                batch.draw(new TextureRegion(squareblock), position.x-width/2, position.y-height/2, width/2, height/2, width, height, 1,1,physicsObj.getAngle()*180/3.14159f); // Draw current frame at (50, 50)
            }


        }
    }

    public void create() {
        final int FRAME_COLS = 6, FRAME_ROWS = 3;
        walkSheet = new Texture("player.png");
        TextureRegion[][] tmp = TextureRegion.split(walkSheet,
                walkSheet.getWidth() / FRAME_COLS,
                walkSheet.getHeight() / FRAME_ROWS);

        TextureRegion[] walkFrames = new TextureRegion[FRAME_COLS];
        int index = 0;
        for (int j = 0; j < FRAME_COLS; j++) {
            walkFrames[index++] = tmp[0][j];
        }

        walkAnimation = new Animation<>(0.25f, walkFrames);

        //ufo
        ufoSheet = new Texture("flyingsaucer.png");
        tmp = TextureRegion.split(ufoSheet,
                ufoSheet.getWidth() / 7,
                ufoSheet.getHeight());

        TextureRegion[] ufoFrames = new TextureRegion[7];
        index = 0;
        for (int j = 0; j < 7; j++) {
            ufoFrames[index++] = tmp[0][j];
        }

        ufoAnimation = new Animation<>(0.25f, ufoFrames);

        stateTime = 0f;
    }

    private Texture sky;
    private void renderWorld() {
        batch.draw(sky, -5000, -5000, 1, 1, 10000, 10000);

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
