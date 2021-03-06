package com.mygdx.kiddiecode;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.Map;
import java.util.regex.Pattern;

public class Block {
    private float posX;
    private float posY;
    private float minwidth;
    private float width;
    private float height;
    private BlockTypes type;
    private boolean isHighlightedVar;

    static public ShapeRenderer shupooey;
    static public SpriteBatch spriteBatch;
    static public BitmapFont font;

    private java.util.ArrayList<Node> incomingNodes;
    private java.util.ArrayList<Node> outgoingNodes;
    private java.util.Map<String,String> innerNodes;//inline data (like integer literals)
    private java.util.Map<String,com.badlogic.gdx.math.Rectangle> innerNodesEditBoundingBoxes;
    public java.util.Map<String,com.badlogic.gdx.math.Rectangle> getInnerNodesEditBoundingBoxes() {return innerNodesEditBoundingBoxes;}

    public BlockTypes getType() {return type;}

    public void setInnerNode(String key, String value) {
        this.innerNodes.put(key, value);
    }

    public static float progCoord(float x) {//returns programming coordinates instead of mathematical
        return Gdx.graphics.getHeight() - x;
    }

    public void draw() {
        if (type == BlockTypes.ONLOAD_TRIGGER || type == BlockTypes.ONTICK_TRIGGER) {
            shupooey.setColor(1, 0, 0, 1);
        }
        else if (type == BlockTypes.IF_LESS_THAN || type == BlockTypes.IF_EQUAL_TO
                || type == BlockTypes.PLACE_BLOCK.IF_GREATER_THAN || type == BlockTypes.IF_NOT_EQUAL_TO) {
            shupooey.setColor(0, 0.7f, 0, 1);
        }
        else {
            shupooey.setColor(1, 0.25f, 0, 1);
        }
        if (isHighlighted()) {
            shupooey.getColor().mul(0.5f);

        }

        shupooey.rect(posX, progCoord(posY), width, height);
        //now draw outgoing and incoming nodes
        for (Node n : incomingNodes) {
            n.draw();
        }
        for (Node n : outgoingNodes) {
            n.draw();
        }
        //add edit button for each key thingy
        int counter = 0;
        for (String k : innerNodes.keySet()) {
            String toMatch = k.replace('[',' ').replace(']',' ') + "="+innerNodes.get(k);
            shupooey.setColor(0.8f, 0.8f, 0.8f, 1);
            MasterClass.layout.setText(font,toMatch);
            shupooey.rect(posX + counter + 5, progCoord(posY+50-height), MasterClass.layout.width, 20);
            innerNodesEditBoundingBoxes.put(k,
                    new com.badlogic.gdx.math.Rectangle(
                            (int)(posX + counter + 2),
                            (int)(((posY+50-height-20))),
                            (int)MasterClass.layout.width,
                            (int)20
                    )
            );
            counter += MasterClass.layout.width + 5;
        }

        //drawText();
    }

    public void drawText() {
        spriteBatch.setColor(0,0,0,1);
        String str = "yiggledoog";
        if (type == BlockTypes.ONLOAD_TRIGGER) {
            str = "OnLoad";
        }
        else if (type == BlockTypes.IF_LESS_THAN) {
            str = "If [Val1] Less Than [Val2]";
        }
        else if (type == BlockTypes.IF_GREATER_THAN) {
            str = "If [Val1] Greater Than [Val2]";
        }
        else if (type == BlockTypes.IF_NOT_EQUAL_TO) {
            str = "If [Val1] Not Equal To [Val2]";
        }
        else if (type == BlockTypes.IF_EQUAL_TO) {
            str = "If [Val1] Equal To [Val2]";
        }
        else if (type == BlockTypes.LOOP_FROM_TO) {
            str = "Loop [Var] From [ValLower] To [ValUpper]";
        }
        else if (type == BlockTypes.VAR_DECLARE) {
            str = "Var [Name] = [Val]";
        }
        else if (type == BlockTypes.PLACE_BLOCK) {
            str = "Place Block [BlockName] at ([PosX],[PosY])";
        }
        else if (type == BlockTypes.PLACE_PLAYER) {
            str = "Place Player at ([PosX],[PosY])";
        }
        else if (type == BlockTypes.ONTICK_TRIGGER) {
            str = "OnTick For Entity: [EntityName]";
        }
        else if (type == BlockTypes.ONTICK_CLASS_TRIGGER) {
            str = "OnTick For Entity Class: [EntityClass]";
        }
        else if (type == BlockTypes.MOVE_PLAYER_BY) {
            str = "Move Player by ([MoveX],[MoveY])";
        }
        else if (type == BlockTypes.SPAWN_ENTITY_AT) {
            str = "Spawn Entity [EntityName] of Type [EntityType] at ([PosX],[PosY])";
        }
        else if (type == BlockTypes.MOVE_ENTITY) {
            str = "Move This Entity by ([MoveX],[MoveY])";
        }
        for (String k : innerNodes.keySet()) {
            str = str.replaceAll(Pattern.quote(k),innerNodes.get(k));
        }
        font.draw(spriteBatch,str,posX+2,progCoord(posY)+height-2);
        //add edit button for each key thingy
        int counter = 0;
        for (String k : innerNodes.keySet()) {
            spriteBatch.setColor(0,0,0,1);
            String toMatch = k.replace('[',' ').replace(']',' ')+"="+innerNodes.get(k);
            font.draw(spriteBatch,toMatch,posX + counter + 2, progCoord(posY+50-height)+18);
            MasterClass.layout.setText(font,toMatch);
            counter += MasterClass.layout.width + 5;
        }
        width = counter > minwidth ? counter + 5 : minwidth;
    }

    private java.util.ArrayList<Node> makeNodes(int amount,boolean isIncoming) {
        java.util.ArrayList<Node> toReturn = new java.util.ArrayList<Node>();
        for (int i = 1;i<amount+1;i++) {
            toReturn.add(new Node(this, i * width / (amount+1), (isIncoming ? -height : 0),isIncoming));
        }
        return toReturn;
    }

    public Block(float x,float y,BlockTypes t) {
        posX = x;
        posY = y;
        type = t;
        minwidth = 150;
        width = 150;
        height = 70;
        isHighlightedVar = false;
        incomingNodes = new java.util.ArrayList<Node>();
        outgoingNodes = new java.util.ArrayList<Node>();
        innerNodes = new java.util.LinkedHashMap<String,String>();
        innerNodesEditBoundingBoxes = new java.util.HashMap<String,com.badlogic.gdx.math.Rectangle>();
        //now we put the right amount of nodes for the thingy stuffs
        if (type == BlockTypes.ONLOAD_TRIGGER) {
            outgoingNodes.addAll(makeNodes(1,false));//outgoing: next line
        }
        else if (type == BlockTypes.IF_LESS_THAN || type == BlockTypes.IF_GREATER_THAN || type == BlockTypes.IF_EQUAL_TO || type == BlockTypes.IF_NOT_EQUAL_TO) {
            incomingNodes.addAll(makeNodes(1,true));//incoming: prev line,
            outgoingNodes.addAll(makeNodes(3,false));//outgoing: next line when true, next line when false, line after all if
            innerNodes.put("[Val1]","0");
            innerNodes.put("[Val2]","0");
        }
        else if (type == BlockTypes.LOOP_FROM_TO) {
            incomingNodes.addAll(makeNodes(1,true));//incoming: prev line
            outgoingNodes.addAll(makeNodes(2,false));//outgoing: first line in loop, first line after loop
            innerNodes.put("[Var]","x");
            innerNodes.put("[ValLower]","1");
            innerNodes.put("[ValUpper]","100");
        }
        else if (type == BlockTypes.VAR_DECLARE) {
            incomingNodes.addAll(makeNodes(1,true));//incoming: prev line
            outgoingNodes.addAll(makeNodes(1,false));//outgoing: next line
            innerNodes.put("[Name]","x");
            innerNodes.put("[Val]","0");
        }
        else if (type == BlockTypes.PLACE_PLAYER) {
            incomingNodes.addAll(makeNodes(1,true));//incoming: prev line
            outgoingNodes.addAll(makeNodes(1,false));//outgoing: next line
            innerNodes.put("[PosX]","0");
            innerNodes.put("[PosY]","0");
        }
        else if (type == BlockTypes.PLACE_BLOCK) {
            incomingNodes.addAll(makeNodes(1,true));//incoming: prev line
            outgoingNodes.addAll(makeNodes(1,false));//outgoing: next line
            innerNodes.put("[BlockName]","Dirt");
            innerNodes.put("[PosX]","0");
            innerNodes.put("[PosY]","0");
        }
        else if (type == BlockTypes.ONTICK_TRIGGER) {
            outgoingNodes.addAll(makeNodes(1,false));//outgoing: next line
            innerNodes.put("[EntityName]","Player1");
        }
        else if (type == BlockTypes.ONTICK_CLASS_TRIGGER) {
            outgoingNodes.addAll(makeNodes(1,false));//outgoing: next line
            innerNodes.put("[EntityClass]","Player");
        }
        else if (type == BlockTypes.MOVE_PLAYER_BY) {
            incomingNodes.addAll(makeNodes(1,true));//incoming: prev line
            outgoingNodes.addAll(makeNodes(1,false));//outgoing: next line
            innerNodes.put("[MoveX]","0");
            innerNodes.put("[MoveY]","0");
        }
        else if (type == BlockTypes.SPAWN_ENTITY_AT) {
            incomingNodes.addAll(makeNodes(1,true));//incoming: prev line
            outgoingNodes.addAll(makeNodes(1,false));//outgoing: next line
            innerNodes.put("[EntityName]","George");
            innerNodes.put("[EntityType]","Player");
            innerNodes.put("[PosX]","0");
            innerNodes.put("[PosY]","0");
        }
        else if (type == BlockTypes.MOVE_ENTITY) {
            incomingNodes.addAll(makeNodes(1,true));//incoming: prev line
            outgoingNodes.addAll(makeNodes(1,false));//outgoing: next line
            innerNodes.put("[MoveX]","0");
            innerNodes.put("[MoveY]","0");
        }
        else {
            //throw Exception("The following blocktype is not fully defined: " + type.toString());
            System.out.println("The following blocktype is not fully defined: " + type.toString());
        }

    }

    public boolean isHighlighted() {return isHighlightedVar;}
    public void highlight() {
        //if (!isHighlightedVar) {System.out.println("Highlighting " + type);}
        isHighlightedVar = true;
    }
    public void delight() {
        //if (isHighlightedVar) {System.out.println("Delighting " + type);}
        isHighlightedVar = false;
    }

    public void setPosX(float posX) {
        this.posX = posX;
    }

    public void setPosY(float posY) {
        this.posY = posY;
    }

    public float getPosX() {return posX;}
    public float getPosY() {return posY;}
    public float getHeight() {return height;}

    public com.badlogic.gdx.math.Rectangle boundRect() {
        return new com.badlogic.gdx.math.Rectangle((int)(posX),(int)(posY-height),(int)(width),(int)(height));
    }

    public java.util.ArrayList<Node> getIncomingNodes() {
        return incomingNodes;
    }

    public java.util.ArrayList<Node> getOutgoingNodes() {
        return outgoingNodes;
    }

    public java.util.Map<String,String> getInnerNodes() {
        return innerNodes;
    }

}
