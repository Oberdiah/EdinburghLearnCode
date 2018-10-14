package com.mygdx.script.Blocks;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.world.WorldGrid;
import com.mygdx.script.TestScript.Interpreter;

public class BlockApplyForceToPlayer extends Block {
    private String fX;
    private String fY;

    public BlockApplyForceToPlayer(String fx, String fy) {
        fX = fx;
        fY = fy;
    }

    @Override
    protected void functionality() {
        //player at (posX,posY)
        int x = Interpreter.resolveVariable(fX);
        int y = Interpreter.resolveVariable(fY);

        //System.out.println("Yeah");
        Vector2 center = WorldGrid.playerEntity.physicsObject.getWorldCenter();
        WorldGrid.playerEntity.physicsObject.applyLinearImpulse(x/10f,y,center.x/10f,center.y,true);
    }
}
