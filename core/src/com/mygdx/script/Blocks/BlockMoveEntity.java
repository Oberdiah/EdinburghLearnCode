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
        if (Interpreter.relevantEntity == null) {//.isEmpty()) {
            System.out.println("Warning - need to specify an entity!");
            return;
        }
        int x = Interpreter.resolveVariable(fX);
        int y = Interpreter.resolveVariable(fY);
        //for (Entity e : Interpreter.relevantEntity) {
        Entity e = Interpreter.relevantEntity;
            Vector2 center = e.physicsObject.getWorldCenter();
            e.physicsObject.applyLinearImpulse(x, y, center.x, center.y, true);
        //}
    }

}
