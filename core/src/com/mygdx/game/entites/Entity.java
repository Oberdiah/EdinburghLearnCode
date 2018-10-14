package com.mygdx.game.entites;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.mygdx.game.rendering.MainRenderer;
import com.mygdx.script.Blocks.Block;

public class Entity {
    public Sprite sprite = new Sprite(MainRenderer.rockblock);

    public java.util.ArrayList<Block> tickScript = new java.util.ArrayList<Block>();
    //public Block tickScript;

    public Body physicsObject;

    public String uniqueEntityName;

    public String type;

    public java.util.HashMap<String,Integer> exposedVariables = new java.util.HashMap<>();

    public Entity(String uID,String type) {
        uniqueEntityName = uID;
        this.type = type;
    }

    public void giveVariable(String varname,int startingValue) {
        exposedVariables.put(varname,startingValue);
    }

}
