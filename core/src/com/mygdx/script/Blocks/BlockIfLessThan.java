package com.mygdx.script.Blocks;

import com.mygdx.script.TestScript.Interpreter;

public class BlockIfLessThan extends Block {

    private String possiblyLower;
    private String possiblyHigher;
    private Block ifFalse;
    private Block after;

    public BlockIfLessThan(String possiblyLower,String possiblyHigher,Block ifFalse,Block after) {
        this.possiblyLower = possiblyLower;
        this.possiblyHigher = possiblyHigher;
        this.ifFalse = ifFalse;
        this.after = after;
    }

    @Override
    protected void functionality() {
        //System.out.println("iffy: " + possiblyLower + "( "+Interpreter.resolveVariable(possiblyLower)+" ) v "
        //        + possiblyHigher + "( "+Interpreter.resolveVariable(possiblyHigher)+" )");
        if (Interpreter.resolveVariable(possiblyLower) < Interpreter.resolveVariable(possiblyHigher)) {
            //System.out.println("Succeed");
        }
        else {
            setTempNext(ifFalse);
            useTempNext = true;
        }
        if (after!=null) {
            Interpreter.nullJumpers.add(after);
        }
    }
}
