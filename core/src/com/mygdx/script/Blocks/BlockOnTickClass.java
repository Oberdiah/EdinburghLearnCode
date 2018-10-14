package com.mygdx.script.Blocks;

import com.mygdx.game.entites.Entity;
import com.mygdx.script.TestScript.Interpreter;

public class BlockOnTickClass extends BlockTicker {

    //public Entity ticker;
    //public String ticker;

    public BlockOnTickClass(String e) {
        //ticker = e;
        super(e);
        classwise = true;
    }

    @Override
    protected void functionality() {
        Interpreter.relevantEntity = new java.util.ArrayList<Entity>();
        Interpreter.relevantEntity.addAll( Interpreter.getEntityFromClass( ticker ));

    }//just a starting point, it does nothing on its own
}