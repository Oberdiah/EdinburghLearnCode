package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.game.input.HandleInput;
import com.mygdx.game.physics.PhysicsHandler;
import com.mygdx.game.rendering.MainRenderer;
import com.mygdx.game.tick.Ticker;
import com.mygdx.game.world.WorldGrid;
import com.mygdx.kiddiecode.MasterClass;
import com.mygdx.kiddiecode.*;

import java.util.ArrayList;

import static com.mygdx.kiddiecode.MasterClass.shapeRenderer;

public class Main extends ApplicationAdapter {

	public static MainRenderer renderer;

	public static WorldGrid worldGrid;

	public static Ticker tick;

    public static OrthographicCamera cam;

    public static HandleInput inputHandler;

    public static boolean codemode = true;

    Touchpad touchPad;
    Touchpad.TouchpadStyle style;
    TextureRegionDrawable background;
    TextureRegionDrawable knobRegion;
    Texture texture;
    Texture killer;
    SpriteBatch batch;
    Stage stage;
    public static int speed = 3;
    int x = 0;
    int y = 0;


	@Override
	public void create () {
        renderer = new MainRenderer();
        renderer.init();
        Main.worldGrid = new WorldGrid();
        Main.worldGrid.init();
        tick = new Ticker();
        inputHandler = new HandleInput();

        PhysicsHandler.init();

        //KiddieCode stuff
        Gdx.input.setInputProcessor(new IREALLYDespiseGestureDetectors(new IHateGestureListeners(this)));

        shapeRenderer = new ShapeRenderer();
        MasterClass.fontyWonty = new BitmapFont();
        MasterClass.batch = new SpriteBatch();
        Block.shupooey = shapeRenderer;
        Block.spriteBatch = MasterClass.batch;
        Block.font = MasterClass.fontyWonty;
        MasterClass.blocks = new java.util.ArrayList<Block>();
        MasterClass.blocks.add(new Block(50,200,BlockTypes.ONLOAD_TRIGGER));
        MasterClass.blocks.add(new Block(100,350,BlockTypes.IF_LESS_THAN));
        MasterClass.blocks.add(new Block(150,200,BlockTypes.LOOP_FROM_TO));
        MasterClass.blocks.add(new Block(200,350,BlockTypes.LOOP_FROM_TO));
        MasterClass.blocks.add(new Block(250,200,BlockTypes.PLACE_BLOCK));
        MasterClass.blocks.add(new Block(300,350,BlockTypes.PLACE_PLAYER));
        MasterClass.blocks.add(new Block(350,200,BlockTypes.ONTICK_TRIGGER));
        MasterClass.blocks.add(new Block(400,350,BlockTypes.MOVE_PLAYER_BY));


        //onscreen joystick
        stage = new Stage();
        texture = new Texture("skyblock.png");
        killer = new Texture("rockblock.png");
        background = new TextureRegionDrawable(new TextureRegion(texture, 0, 0,128,128));
        knobRegion = new TextureRegionDrawable(new TextureRegion(texture,128,0,128,128));
        style = new Touchpad.TouchpadStyle(background, knobRegion);
        touchPad = new Touchpad(15, style);
        touchPad.setBounds(0, 0, 150, 150);
        batch = new SpriteBatch();
        stage.addActor(touchPad);
        Gdx.input.setInputProcessor(stage);
    }

    public void update(){
        if(touchPad.isTouched()){
            x += touchPad.getKnobPercentX()*speed;
            y += touchPad.getKnobPercentY()*speed;

        }
    }

	@Override
	public void render () {
	    //toggle this to swap between the platformer sandbox and the scripting sandbox
        Main.inputHandler.handleInput(Main.cam);
        Main.cam.update();
	    if (!codemode) {
            Gdx.gl.glClearColor(0.4f, 0.4f, 0.4f, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            PhysicsHandler.render();

            renderer.render();
            tick.tick();
            update();
            batch.begin();
            batch.draw(killer, x,y, 70,70);
            batch.end();
            stage.act();
            stage.draw();
        }
        else {
            Gdx.gl.glClearColor(1, 1, 1, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            shapeRenderer.setProjectionMatrix(Main.cam.combined);

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
            MasterClass.batch.setProjectionMatrix(Main.cam.combined);
            for (Block b: MasterClass.blocks) {
                b.drawText();
            }
            MasterClass.batch.end();
        }

        //Gdx.gl.glClearColor(1, 1, 1, 1);
        //Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);





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
