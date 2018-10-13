package com.mygdx.game.physics;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.game.Main;
import com.mygdx.game.rendering.MainRenderer;
import com.mygdx.game.tile.Tile;
import com.mygdx.game.world.WorldGrid;

public class PhysicsHandler {

    private static World world;

    private static Box2DDebugRenderer debugRenderer;

    public static void render() {
        debugRenderer.render(world, Main.cam.combined);
    }

    public static Body groundBody;

    public static void init() {
        Box2D.init();
        world = new World(new Vector2(0, -10), true);
        debugRenderer = new Box2DDebugRenderer();
        createSphere();
        createFloor();
    }

    public static Fixture createCollisionBlock(int x, int y) {
        int halfSize = MainRenderer.BLOCKPIXELSIZE / 2;
        PolygonShape groundBox = new PolygonShape();
        groundBox.setAsBox(halfSize, halfSize, new Vector2(x * MainRenderer.BLOCKPIXELSIZE + halfSize, y * MainRenderer.BLOCKPIXELSIZE + halfSize), 0.0f);
        Fixture f = groundBody.createFixture(groundBox, 0.0f);
        groundBox.dispose();
        return f;
    }

    private static void createFloor() {
        BodyDef groundBodyDef = new BodyDef();
        groundBodyDef.position.set(new Vector2(0, 0));
        groundBody = world.createBody(groundBodyDef);

        for (int xSqr = 0; xSqr < WorldGrid.worldWidth; xSqr++) {
            for (int ySqr = 0; ySqr < WorldGrid.worldHeight; ySqr++) {
                if (ySqr == 0) {
                    Main.worldGrid.setBlock(xSqr, ySqr, Tile.TileType.ROCK);
                }
                else
                {
                    Main.worldGrid.setBlock(xSqr, ySqr, Tile.TileType.SKY);

                }
            }
        }

    }

    public static void tick() {
        world.step(1 / 60f, 6, 2);
    }

    private static void createSphere() {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(20, 1000);

        Body body = world.createBody(bodyDef);

        CircleShape circle = new CircleShape();
        circle.setRadius(6f);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = circle;
        fixtureDef.density = 0.5f;
        fixtureDef.friction = 0.4f;
        fixtureDef.restitution = 1.0f;

        body.createFixture(fixtureDef);

        circle.dispose();
    }
}
