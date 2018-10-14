package com.mygdx.script.Blocks;

import com.mygdx.game.entites.Entity;
import com.mygdx.script.Blocks.Block;
import com.mygdx.script.TestScript.Interpreter;

public class BlockOnTick extends Block {

    //public Entity ticker;
    public String ticker;

    public BlockOnTick(String e) {
        ticker = e;
    }

    @Override
    protected void functionality() {
        Interpreter.relevantEntity = Interpreter.getEntityFrom( ticker );
    }//just a starting point, it does nothing on its own
}
