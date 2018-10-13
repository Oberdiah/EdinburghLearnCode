package com.mygdx.script.Blocks;

import com.mygdx.game.Main;
import com.mygdx.game.tile.Tile;
import com.mygdx.game.world.WorldGrid;
import com.mygdx.script.TestScript.Interpreter;

public class BlockPlacePlayer extends Block {

    private String posX;
    private String posY;

    public BlockPlacePlayer(String px, String py) {
        posX = px;
        posY = py;
    }

    @Override
    protected void functionality() {
        //player at (posX,posY)
        int x = Interpreter.resolveVariable(posX);
        int y = Interpreter.resolveVariable(posY);

        WorldGrid.playerEntity.physicsObject.setTransform(x,y,0);
    }
}
