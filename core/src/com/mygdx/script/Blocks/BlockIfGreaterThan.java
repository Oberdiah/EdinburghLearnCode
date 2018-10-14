package com.mygdx.script.Blocks;

import com.mygdx.script.TestScript.Interpreter;

public class BlockIfGreaterThan extends Block {

    private String possiblyLower;
    private String possiblyHigher;
    private Block ifFalse;

    public BlockIfGreaterThan(String possiblyLower,String possiblyHigher,Block ifFalse) {
        this.possiblyLower = possiblyLower;
        this.possiblyHigher = possiblyHigher;
        this.ifFalse = ifFalse;
    }

    @Override
    protected void functionality() {
        //System.out.println("iffy: " + possiblyLower + "( "+Interpreter.resolveVariable(possiblyLower)+" ) v "
        //        + possiblyHigher + "( "+Interpreter.resolveVariable(possiblyHigher)+" )");
        if (Interpreter.resolveVariable(possiblyLower) > Interpreter.resolveVariable(possiblyHigher)) {
            //System.out.println("Succeed");
        }
        else {
            setTempNext(ifFalse);
            useTempNext = true;
        }
    }
}

