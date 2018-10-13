package com.mygdx.script.Blocks.Actions;

import com.mygdx.script.Blocks.Block;
import com.mygdx.script.Utils.Instruction;

public class Action extends Block {
    private Instruction i;
    public Action(Instruction i){
        this.i = i;
    }
    public void functionality(){
        i.evaluate();
    }

}
