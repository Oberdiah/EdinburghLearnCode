package com.mygdx.game.physics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public class PhysicsHandler {

    public static World world;

    private static Box2DDebugRenderer debugRenderer;

    public static void render() {
        //debugRenderer.render(world, Main.cam.combined);
    }

    public static Body groundBody;

    public static void init() {
        Box2D.init();
        debugRenderer = new Box2DDebugRenderer();
        world = new World(new Vector2(0, -10), true);
        createFloor();
    }

    public static Fixture createCollisionBlock(int x, int y) {
        PolygonShape groundBox = new PolygonShape();
        groundBox.setAsBox(0.5f, 0.5f, new Vector2(x+0.5f, y + 0.5f), 0.0f);
        Fixture f = groundBody.createFixture(groundBox, 0.0f);
        groundBox.dispose();

        return f;
    }

    private static void createFloor() {
        BodyDef groundBodyDef = new BodyDef();
        groundBodyDef.position.set(new Vector2(0, 0));
        groundBody = world.createBody(groundBodyDef);
    }

    public static void tick() {
        world.step(Gdx.graphics.getDeltaTime(), 6, 2);
    }

    public static Body createPhysicsEntity(int x, int y, int width, int height) {
        return createPhysicsEntity(x,y,width,height,false);
    }

    public static Body createPhysicsEntity(int x, int y, int width, int height, boolean fixedRotation) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(x, y);

        Body body = world.createBody(bodyDef);

        PolygonShape groundBox = new PolygonShape();
        groundBox.setAsBox(width/2.0f, height/2.0f);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = groundBox;
        fixtureDef.density = 0.5f;
        fixtureDef.friction = 0.4f;
        fixtureDef.restitution = 0.1f;

        body.setFixedRotation(fixedRotation);

        body.createFixture(fixtureDef);

        groundBox.dispose();

        return body;
    }
}
