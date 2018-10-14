package com.mygdx.script.Blocks;

import com.mygdx.game.entites.Entity;
import com.mygdx.script.Blocks.Block;

public class BlockOnTick extends Block {

    public Entity ticker;

    public BlockOnTick(Entity e) {
        ticker = e;
    }

    @Override
    protected void functionality() { }//just a starting point, it does nothing on its own
}
