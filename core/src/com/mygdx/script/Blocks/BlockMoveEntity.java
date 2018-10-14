package com.mygdx.script.Blocks;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Main;
import com.mygdx.game.entites.Entity;
import com.mygdx.game.physics.PhysicsHandler;
import com.mygdx.game.world.WorldGrid;
import com.mygdx.script.TestScript.Interpreter;

public class BlockMoveEntity extends Block {


    private String fX;
    private String fY;

    public BlockMoveEntity(String fx,String fy) {
        fX = fx;
        fY = fy;
    }

    @Override
    protected void functionality() {
        if (Interpreter.relevantEntity == null) {
            System.out.println("Warning - need to specify an entity!");
            return;
        }
        //player at (posX,posY)
        int x = Interpreter.resolveVariable(fX);
        int y = Interpreter.resolveVariable(fY);
        Vector2 center = Interpreter.relevantEntity.physicsObject.getWorldCenter();
        Interpreter.relevantEntity.physicsObject.applyLinearImpulse(x,y,center.x,center.y,true);
    }

}
