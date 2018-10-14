package com.mygdx.script.Blocks;

import com.mygdx.game.Main;
import com.mygdx.game.tile.Tile;
import com.mygdx.script.TestScript.Interpreter;

public class BlockAssignVariable extends Block {

    private String varName;
    private String varVal;

    public BlockAssignVariable(String varname,String varval) {
        varName = varname;
        varVal = varval;
    }

    @Override
    protected void functionality() {
        Interpreter.variables.put(varName,""+Interpreter.resolveVariable(varVal));
    }



}