package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.game.input.HandleInput;
import com.mygdx.game.rendering.MainRenderer;
import com.mygdx.game.tick.Ticker;
import com.mygdx.game.tile.Tile;
import com.mygdx.game.world.WorldGrid;
import com.mygdx.kiddiecode.*;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

import java.util.ArrayList;

import static com.mygdx.kiddiecode.MasterClass.shapeRenderer;

public class Main extends ApplicationAdapter {

	public static MainRenderer renderer;

	public static WorldGrid worldGrid;

	public static Ticker tick;

    public static OrthographicCamera cam;

    public static HandleInput inputHandler;

    public static World world;

    public static Box2DDebugRenderer debugRenderer;

	@Override
	public void create () {
        renderer = new MainRenderer();
        renderer.init();
        Main.worldGrid = new WorldGrid();
        Main.worldGrid.init();
        tick = new Ticker();
        inputHandler = new HandleInput();

        Box2D.init();
        world = new World(new Vector2(0, -10), true);
        debugRenderer = new Box2DDebugRenderer();

        createSphere();
        createFloor();

        //KiddieCode stuff
        Gdx.input.setInputProcessor(new IREALLYDespiseGestureDetectors(new IHateGestureListeners(this)));

        shapeRenderer = new ShapeRenderer();
        MasterClass.fontyWonty = new BitmapFont();
        MasterClass.batch = new SpriteBatch();
        Block.shupooey = shapeRenderer;
        Block.spadoogle = MasterClass.batch;
        Block.biddangger = MasterClass.fontyWonty;
        MasterClass.blocks = new java.util.ArrayList<Block>();
        MasterClass.blocks.add(new Block(50,200,BlockTypes.ONLOAD_TRIGGER));
        MasterClass.blocks.add(new Block(100,350,BlockTypes.IF_LESS_THAN));
        MasterClass.blocks.add(new Block(150,200,BlockTypes.LOOP_FROM_TO));
        MasterClass.blocks.add(new Block(200,350,BlockTypes.LOOP_FROM_TO));
        MasterClass.blocks.add(new Block(250,200,BlockTypes.PLACE_BLOCK));
        MasterClass.blocks.add(new Block(300,350,BlockTypes.PLACE_PLAYER));
        MasterClass.blocks.add(new Block(350,200,BlockTypes.ONTICK_TRIGGER));
        MasterClass.blocks.add(new Block(400,350,BlockTypes.MOVE_PLAYER_BY));
    }

    public static void createFloor() {
        BodyDef groundBodyDef = new BodyDef();
        groundBodyDef.position.set(new Vector2(0, -20));
        Body groundBody = world.createBody(groundBodyDef);

        for (int xSqr = 0; xSqr < WorldGrid.worldWidth; xSqr++) {
            for (int ySqr = 0; ySqr < WorldGrid.worldHeight; ySqr++) {
                if (Main.worldGrid.getWorldArray()[xSqr][ySqr] == Tile.TileType.Rock)
                {
                    PolygonShape groundBox = new PolygonShape();
                    groundBox.setAsBox(8, 8, new Vector2(xSqr*16, ySqr * 16), 0.0f);
                    groundBody.createFixture(groundBox, 0.0f);
                    groundBox.dispose();
                }

            }
        }

    }

    public static void createSphere() {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(0, 0);

        Body body = world.createBody(bodyDef);

        CircleShape circle = new CircleShape();
        circle.setRadius(6f);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = circle;
        fixtureDef.density = 0.5f;
        fixtureDef.friction = 0.4f;
        fixtureDef.restitution = 0.6f;

        Fixture fixture = body.createFixture(fixtureDef);

        circle.dispose();
    }

	@Override
	public void render () {
	    //toggle this to swap between the platformer sandbox and the scripting sandbox
	    boolean CODEMODE = false;
	    if (!CODEMODE) {
            Gdx.gl.glClearColor(0.4f, 0.4f, 0.4f, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

            debugRenderer.render(world, cam.combined);
            renderer.render();
            tick.tick();
        }
        else {
            Gdx.gl.glClearColor(1, 1, 1, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            for (Block b: MasterClass.blocks) {
                b.draw();
            }
            //Circle TEMP = getAllNodes().get(0).boundCircle();
            //shapeRenderer.setColor(0,0,1,1);
            //shapeRenderer.circle(TEMP.x,TEMP.y,TEMP.radius);
            if (MasterClass.getStartTerminalNode() != null && MasterClass.getStartTerminalNode().isHighlighted()) {
                //draw line from startTerminalNode.pos to drag.pos
                shapeRenderer.rectLine(MasterClass.getStartTerminalNode().getPosX(),Block.progCoord(MasterClass.getStartTerminalNode().getPosY()), MasterClass.getDragX(),Block.progCoord(MasterClass.getDragY()),5);
            }
            shapeRenderer.end();
            MasterClass.batch.begin();
            for (Block b: MasterClass.blocks) {
                b.drawTheFrigginText();
            }
            MasterClass.batch.end();
        }
	}

    @Override
    public void resize(int width, int height) {
        cam.viewportWidth = 30f;
        cam.viewportHeight = 30f * height/width;
        cam.update();
    }

	@Override
	public void dispose () {
	    renderer.dispose();
	}

	public ArrayList<Block> getBlocks() {
	    return MasterClass.blocks;
    }

    public ArrayList<Node> getAllNodes() {
        java.util.ArrayList<Node> toReturn = new java.util.ArrayList<Node>();
        for (Block b : MasterClass.blocks) {
            toReturn.addAll(b.getIncomingNodes());
            toReturn.addAll(b.getOutgoingNodes());
        }
        return toReturn;
    }
}
