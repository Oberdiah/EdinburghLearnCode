package com.mygdx.script.Blocks;

import com.mygdx.game.entites.Entity;
import com.mygdx.script.Blocks.Block;
import com.mygdx.script.TestScript.Interpreter;

public class BlockOnTick extends BlockTicker {

    //public Entity ticker;


    public BlockOnTick(String e) {
        //ticker = e;
        super(e);
    }

    @Override
    protected void functionality() {
        //Interpreter.relevantEntity = new java.util.ArrayList<Entity>();
        //Interpreter.relevantEntity.add( Interpreter.getEntityFrom( ticker ));
        Interpreter.relevantEntity = (Interpreter.getEntityFrom(ticker));

    }//just a starting point, it does nothing on its own
}
