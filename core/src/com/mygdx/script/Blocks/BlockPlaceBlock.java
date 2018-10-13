package com.mygdx.script.Blocks;

import com.mygdx.game.Main;
import com.mygdx.game.tile.Tile;
import com.mygdx.script.TestScript.Interpreter;

public class BlockPlaceBlock extends Block {

    private String blockName;
    private String posX;
    private String posY;

    public BlockPlaceBlock(String blockname,String px,String py) {
        blockName = blockname;
        posX = px;
        posY = py;
    }

    @Override
    protected void functionality() {
        //draw [blockName] at (posX,posY)
        int x = Interpreter.resolveVariable(posX);
        int y = Interpreter.resolveVariable(posY);
        //System.out.println("Place block at (" + x + "," + y + ")");

        Main.worldGrid.setBlock(x, y, Tile.TileType.getTypeFromString(blockName));
    }



}
