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
        //System.out.println(varVal);
        String v = ""+Interpreter.resolveVariable(varVal);
        Interpreter.variables.put(varName,v);
        Interpreter.relevantEntity.giveVariable(varName,Integer.parseInt(v));
    }



}