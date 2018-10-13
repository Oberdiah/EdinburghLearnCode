package com.mygdx.script.Blocks;

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

    }
}
