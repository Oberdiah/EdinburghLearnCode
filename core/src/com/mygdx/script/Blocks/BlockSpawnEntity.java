package com.mygdx.script.Blocks;

import com.mygdx.game.Main;
import com.mygdx.game.entites.Entity;
import com.mygdx.game.physics.PhysicsHandler;
import com.mygdx.script.TestScript.Interpreter;

public class BlockSpawnEntity extends Block {

    public Entity ticker;

    private String entityName;
    private String entityType;
    private String posX;
    private String posY;

    public BlockSpawnEntity(String eN,String eType,String px,String py) {
        entityName = eN;
        entityType = eType;
        posX = px;
        posY = py;
    }

    @Override
    protected void functionality() {
        Entity e = new Entity(entityName,entityType);

        int x = Interpreter.resolveVariable(posX);
        int y = Interpreter.resolveVariable(posY);

        e.physicsObject = PhysicsHandler.createPhysicsEntity(x, y, 1, 2, true);

        System.out.println("Spawned entity " + entityName + " of type " + entityType);

        Main.worldGrid.getEntityArrayList().add(e);
    }
}

//Entity player = new Entity("Player1");
//        player.physicsObject = PhysicsHandler.createPhysicsEntity(5, 5, 1, 2, true);
//        entityArrayList.add(player);