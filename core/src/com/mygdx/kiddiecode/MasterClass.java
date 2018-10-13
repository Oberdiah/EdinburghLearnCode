package com.mygdx.kiddiecode;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;

import java.util.ArrayList;

public class MasterClass extends ApplicationAdapter {

    private BitmapFont fontyWonty;
    private ShapeRenderer shapeRenderer;
    private java.util.ArrayList<Block> blocks;
    private SpriteBatch batch;
    public java.util.ArrayList<Block> getBlocks() {return blocks;}
    public java.util.ArrayList<Node> getAllNodes() {
        java.util.ArrayList<Node> toReturn = new java.util.ArrayList<Node>();
        for (Block b : blocks) {
            toReturn.addAll(b.getIncomingNodes());
            toReturn.addAll(b.getOutgoingNodes());
        }
        return toReturn;
    }
    private static Node startTerminalNode = null;//when dragging from node to node, this keeps track of what node dragging from
    private static float dragX = 0;//when dragging from node to node, this keeps track of position you're dragging to.
    private static float dragY = 0;
    private static Node theHoverOver = null;

    public static GlyphLayout layout = new GlyphLayout();
    public static ILikeTextInputHandlers listener = new ILikeTextInputHandlers();

    public static void setDrag(float x,float y) {dragX = x;dragY = y;}
    public static void addDrag(float x,float y) {dragX += x;dragY += y;}
    public static void setStartTerminalNode(Node n) {startTerminalNode = n;}
    public static Node getStartTerminalNode() {return startTerminalNode;}
    public static void setHoverOver(Node n) {theHoverOver = n;}
    public static Node getHoverOver() {return theHoverOver;}


    @Override
    public void create() {
        Gdx.input.setInputProcessor(new IDespiseGestureDetectors(new IHateGestureListeners(this)));

        shapeRenderer = new ShapeRenderer();
        fontyWonty = new BitmapFont();
        batch = new SpriteBatch();
        Block.shupooey = shapeRenderer;
        Block.spadoogle = batch;
        Block.biddangger = fontyWonty;
        blocks = new java.util.ArrayList<Block>();
        blocks.add(new Block(50,200,BlockTypes.ONLOAD_TRIGGER));
        blocks.add(new Block(100,350,BlockTypes.IF_LESS_THAN));
        blocks.add(new Block(150,200,BlockTypes.LOOP_FROM_TO));
        blocks.add(new Block(200,350,BlockTypes.LOOP_FROM_TO));
        blocks.add(new Block(250,200,BlockTypes.PLACE_BLOCK));
        blocks.add(new Block(300,350,BlockTypes.PLACE_PLAYER));
        blocks.add(new Block(350,200,BlockTypes.ONTICK_TRIGGER));
        blocks.add(new Block(400,350,BlockTypes.MOVE_PLAYER_BY));

    }

    private float progCoord(float x) {//returns programming coordinates instead of mathematical
        return Gdx.graphics.getHeight() - x;
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        for (Block b: blocks) {
            b.draw();
        }
        //Circle TEMP = getAllNodes().get(0).boundCircle();
        //shapeRenderer.setColor(0,0,1,1);
        //shapeRenderer.circle(TEMP.x,TEMP.y,TEMP.radius);
        if (startTerminalNode != null && startTerminalNode.isHighlighted()) {
            //draw line from startTerminalNode.pos to drag.pos
            shapeRenderer.rectLine(startTerminalNode.getPosX(),progCoord(startTerminalNode.getPosY()), dragX,progCoord(dragY),5);
        }
        shapeRenderer.end();
        batch.begin();
        for (Block b: blocks) {
            b.drawTheFrigginText();
        }
        batch.end();
    }

    @Override
    public void dispose() {

    }
}
