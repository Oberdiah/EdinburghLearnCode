package com.mygdx.game.entites;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.mygdx.game.rendering.MainRenderer;
import com.mygdx.script.Blocks.Block;

public class Entity {
    public Sprite sprite = new Sprite(MainRenderer.rockblock);

    public Block tickScript;

    public Body physicsObject;

    public String uniqueEntityName;

    public String type;

    public Entity(String uID,String type) {
        uniqueEntityName = uID;
        this.type = type;
    }

}
