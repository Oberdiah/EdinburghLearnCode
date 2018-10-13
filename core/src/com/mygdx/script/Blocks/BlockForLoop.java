package com.mygdx.script.Blocks;

import com.mygdx.script.TestScript.Interpreter;

public class BlockForLoop extends Block {

    private String forVariableName;
    private int lastVal;
    private Block cFN;

    public BlockForLoop(String varName,int start,int last,Block conditionFailNext) {
        forVariableName = varName;
        lastVal = last;
        Interpreter.variables.put(varName,""+(start-1));
    }

    @Override
    protected void functionality() {
        //increment variable
        Interpreter.variables.put(forVariableName,""+(1+Integer.parseInt(Interpreter.variables.get(forVariableName))));
        System.out.println("Entering for loop.  " + forVariableName + " has value " + Interpreter.variables.get(forVariableName));
        if (Integer.parseInt(Interpreter.variables.get(forVariableName)) > lastVal) {
            System.out.println("Leaving For Loop");
            setNext(cFN);
        }
    }

}
