package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.game.input.HandleInput;
import com.mygdx.game.physics.PhysicsHandler;
import com.mygdx.game.rendering.MainRenderer;
import com.mygdx.game.tick.Ticker;
import com.mygdx.game.world.WorldGrid;
import com.mygdx.kiddiecode.*;
import com.mygdx.script.TestScript.Interpreter;

import java.util.ArrayList;

import static com.mygdx.kiddiecode.MasterClass.shapeRenderer;

public class Main extends ApplicationAdapter {

    public static MainRenderer renderer;

    public static WorldGrid worldGrid;

    public static Ticker tick;

    public static OrthographicCamera cam;

    public static HandleInput inputHandler;

    public static boolean codemode = true;

    public static TextInputHandlersAreNotLonely codeblockSearcher = new TextInputHandlersAreNotLonely();
    public static SaveHandler sh = new SaveHandler();
    public static LoadHandler lh = new LoadHandler();


    Skin touchpadSkin;
    Touchpad.TouchpadStyle touchpadStyle;
    Touchpad.TouchpadStyle touchpadStyle2;
    Drawable touchpadBackground;
    Drawable touchKnob;
    Drawable press;
    Touchpad touchpad;
    Touchpad touchpad2;
    Stage stage;
    SpriteBatch sb;
    boolean isStick1Hold;
    boolean isStick2Hold;
    int animeTick;

    //gives position of joystick

    public static float p1deltaX = 0;
    public static float p1deltaY = 0;


    public static float p2deltaX = 0;
    public static float p2deltaY = 0;


    //buttons
    public static TextButton goToCodeButton;
    static  TextButton reloadCodeButton;

    @Override
    public void create() {
        renderer = new MainRenderer();
        renderer.init();
        Main.worldGrid = new WorldGrid();
        PhysicsHandler.init();
        Main.worldGrid.init();
        tick = new Ticker();
        inputHandler = new HandleInput();

        //animeTick = 20;

        touchpadSkin = new Skin();
        touchpadSkin.add("touchBackground", new Texture("bigcircle.png"));
        touchpadSkin.add("touchKnob", new Texture("smallcircle.png"));
        touchpadSkin.add("pressed", new Texture("smallcircleshadow.png"));

        touchpadStyle = new Touchpad.TouchpadStyle();
        touchpadStyle2 = new Touchpad.TouchpadStyle();

        touchpadBackground = touchpadSkin.getDrawable("touchBackground");
        touchKnob = touchpadSkin.getDrawable("touchKnob");
        press = touchpadSkin.getDrawable("pressed");

        touchpadStyle2.background = touchpadBackground;
        touchpadStyle2.knob = touchKnob;

        touchpadStyle.background = touchpadBackground;
        touchpadStyle.knob = touchKnob;

        touchpad = new Touchpad(0, touchpadStyle);

        touchpad.setBounds(0, 0, Gdx.graphics.getWidth() / 5, Gdx.graphics.getWidth() / 5);
        touchpad2 = new Touchpad(0, touchpadStyle2);
        touchpad2.setBounds(Gdx.graphics.getWidth() - Gdx.graphics.getWidth() / 5, 0, Gdx.graphics.getWidth() / 5, Gdx.graphics.getWidth() / 5);




        stage = new Stage(new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        stage.addActor(touchpad);
        stage.addActor(touchpad2);


        Skin buttonSkin = new Skin();
        buttonSkin.add("button", new Texture("bigcircle.png"));

        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle(
                buttonSkin.getDrawable("button"),buttonSkin.getDrawable("button"),buttonSkin.getDrawable("button"),
                new BitmapFont()
        );

        goToCodeButton = new TextButton("CODE", textButtonStyle);
        goToCodeButton.setBounds(Gdx.graphics.getWidth() - Gdx.graphics.getWidth() / 2, 0, Gdx.graphics.getWidth() / 5, Gdx.graphics.getWidth() / 5);
        stage.addActor(goToCodeButton);

        reloadCodeButton = new TextButton("RELOAD", textButtonStyle);
        reloadCodeButton.setBounds(Gdx.graphics.getWidth() - 3* Gdx.graphics.getWidth() / 4, 0, Gdx.graphics.getWidth() / 5, Gdx.graphics.getWidth() / 5);
        stage.addActor(reloadCodeButton);

        //KiddieCode stuff
        Gdx.input.setInputProcessor(new IREALLYDespiseGestureDetectors(new IHateGestureListeners(this)));

        shapeRenderer = new ShapeRenderer();
        MasterClass.fontyWonty = new BitmapFont();
        MasterClass.batch = new SpriteBatch();
        Block.shupooey = shapeRenderer;
        Block.spriteBatch = MasterClass.batch;
        Block.font = MasterClass.fontyWonty;
        MasterClass.blocks = new java.util.ArrayList<Block>();
//        MasterClass.blocks.add(new Block(50, 200, BlockTypes.ONLOAD_TRIGGER));
//
//        MasterClass.blocks.add(new Block(150, 350, BlockTypes.IF_LESS_THAN));
//        MasterClass.blocks.get(0).getOutgoingNodes().get(0).connectTo(
//                MasterClass.blocks.get(1).getIncomingNodes().get(0)
//        );
//        MasterClass.blocks.add(new Block(250, 200, BlockTypes.LOOP_FROM_TO));
//        MasterClass.blocks.add(new Block(350, 350, BlockTypes.LOOP_FROM_TO));
//        MasterClass.blocks.add(new Block(450, 200, BlockTypes.PLACE_BLOCK));
//        MasterClass.blocks.add(new Block(550, 350, BlockTypes.PLACE_PLAYER));
//        MasterClass.blocks.add(new Block(650, 200, BlockTypes.ONTICK_TRIGGER));
//        MasterClass.blocks.add(new Block(750, 350, BlockTypes.MOVE_PLAYER_BY));

        if (codemode) {
            Main.cam.zoom = HandleInput.CODE_ZOOM;//do this if we start in the code section
        } else {
            Main.cam.zoom = 1;
        }

        gesture = new IREALLYDespiseGestureDetectors(new IHateGestureListeners(this));

    }


    public IREALLYDespiseGestureDetectors gesture;

    @Override
    public void render() {
        //toggle this to swap between the platformer sandbox and the scripting sandbox

        Main.inputHandler.handleInput(Main.cam);
        Main.cam.update();

        if (!codemode) {


            Gdx.input.setInputProcessor(stage);
            Gdx.gl.glClearColor(0.4f, 0.4f, 0.4f, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            renderer.render();
            PhysicsHandler.render();

            tick.tick();


            //Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            touchpad.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    // This is run when anything is changed on this actor.

                    float deltaX = ((Touchpad) actor).getKnobPercentX();
                    float deltaY = ((Touchpad) actor).getKnobPercentY();
                    //isStick1Hold = true;
                    //System.out.println(deltaX+" "+deltaY);

                    p1deltaX = ((Touchpad) actor).getKnobPercentX();
                    p1deltaY = ((Touchpad) actor).getKnobPercentY();


                }
            });

            touchpad.addListener(new ClickListener() {
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    touchpad.getStyle().knob = press;
                    return true;
                }

                @Override
                public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                    touchpad.getStyle().knob = touchKnob;
                }
            });

//            if (isStick1Hold){
//
//            }else {
//                touchpad.getStyle().knob = touchKnob;
//            }


            touchpad2.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    // This is run when anything is changed on this actor.

                    float deltaX = ((Touchpad) actor).getKnobPercentX();
                    float deltaY = ((Touchpad) actor).getKnobPercentY();

                    //Stick2Hold = true;
                    //System.out.println(deltaX+" "+deltaY);

                    p2deltaX = deltaX;
                    p2deltaY = deltaY;


                }
            });


            goToCodeButton.addListener(new ClickListener() {
                public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                    Main.codemode = !Main.codemode;
                    if (Main.codemode == true) {
                        HandleInput.gameZoom = Main.cam.zoom;
                        Main.cam.zoom = HandleInput.codeZoom;
                    } else {
                        HandleInput.codeZoom = Main.cam.zoom;
                        Main.cam.zoom = HandleInput.gameZoom;
                    }
                }
            });

            reloadCodeButton.addListener(new ClickListener() {
                public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

                    Interpreter.initializeInterpreter();
                    Interpreter.interpret();
                }
            });

            //Gdx.input.setInputProcessor(stage);
            SpriteBatch batch = new SpriteBatch();
            batch.begin();
            touchpad.draw(batch, 0.6f);
            touchpad2.draw(batch, 0.6f);
            goToCodeButton.draw(batch,0.6f);
            reloadCodeButton.draw(batch,0.6f);
            batch.end();
            //stage.act();
            //stage.draw();

        } else {

            Gdx.input.setInputProcessor(new IREALLYDespiseGestureDetectors(new IHateGestureListeners(this)));

            Gdx.input.setInputProcessor(gesture);

            Gdx.gl.glClearColor(1, 1, 1, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            shapeRenderer.setProjectionMatrix(Main.cam.combined);
            for (Block b : new ArrayList<>(MasterClass.blocks)) {
                b.draw();
                //System.out.println(b);
            }
            //Circle TEMP = getAllNodes().get(0).boundCircle();
            //shapeRenderer.setColor(0,0,1,1);
            //shapeRenderer.circle(TEMP.x,TEMP.y,TEMP.radius);
            if (MasterClass.getStartTerminalNode() != null && MasterClass.getStartTerminalNode().isHighlighted()) {
                //draw line from startTerminalNode.pos to drag.pos
                Vector3 dummy = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
                dummy = Main.cam.unproject(dummy);
                MasterClass.setDrag(dummy.x, dummy.y);
                shapeRenderer.rectLine(MasterClass.getStartTerminalNode().getPosX(), Block.progCoord(MasterClass.getStartTerminalNode().getPosY()), dummy.x, (dummy.y), 5);//, MasterClass.getDragX(),Block.progCoord(MasterClass.getDragY()),5);
            }
            shapeRenderer.end();
            MasterClass.batch.begin();
            MasterClass.batch.setProjectionMatrix(Main.cam.combined);


            for (Block b : new ArrayList<Block>(MasterClass.blocks)) {
                b.drawText();
            }
            MasterClass.batch.end();
        }

        //Gdx.gl.glClearColor(1, 1, 1, 1);
        //Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


    }


    @Override
    public void resize(int width, int height) {
        touchpad.setBounds(0, 0, Gdx.graphics.getWidth() / 5, Gdx.graphics.getWidth() / 5);
        touchpad2.setBounds(Gdx.graphics.getWidth() - Gdx.graphics.getWidth() / 5, 0, Gdx.graphics.getWidth() / 5, Gdx.graphics.getWidth() / 5);

        cam.viewportWidth = 30f;
        cam.viewportHeight = 30f * height / width;
        if (codemode) {
            cam.zoom = HandleInput.CODE_ZOOM;
        }
        cam.update();
        //stage.getViewport().update(width, height);
    }

    @Override
    public void dispose() {
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
