package com.mygdx.script.Blocks;

import com.mygdx.game.Main;
import com.mygdx.game.entites.Entity;
import com.mygdx.script.TestScript.Interpreter;

public class BlockSpawnEntity extends Block {

    public Entity ticker;

    private String entityName;
    private String posX;
    private String posY;

    public BlockSpawnEntity(String eN,String px,String py) {
        entityName = eN;
        posX = px;
        posY = py;
    }

    @Override
    protected void functionality() {
        Entity e = new Entity(entityName);

        int x = Interpreter.resolveVariable(posX);
        int y = Interpreter.resolveVariable(posY);

        e.physicsObject.setTransform(x,y,0);
        Main.worldGrid.getEntityArrayList().add(e);
    }
}

//Entity player = new Entity("Player1");
//        player.physicsObject = PhysicsHandler.createPhysicsEntity(5, 5, 1, 2, true);
//        entityArrayList.add(player);