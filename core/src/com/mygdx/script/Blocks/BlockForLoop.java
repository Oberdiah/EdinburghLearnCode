package com.mygdx.script.Blocks;

import com.mygdx.script.TestScript.Interpreter;

import java.util.function.Consumer;

public class BlockForLoop extends Block {

    private String forVariableName;
    private String lastVal;
    private String startVal;
    private Block cFN;

    public BlockForLoop(String varName,String start,String last,Block conditionFailNext) {
        forVariableName = varName;
        lastVal = last;
        startVal = start;
        cFN = conditionFailNext;
        //Interpreter.variables.put(varName,""+(start-1));
    }

    @Override
    protected void functionality() {
        if (Interpreter.variables.get(forVariableName) == null) {
            Interpreter.variables.put(forVariableName,startVal);
            System.out.println("Entering for loop.  " + forVariableName + " has value " + Interpreter.variables.get(forVariableName));
        }
        //increment variable
        Interpreter.variables.put(forVariableName,""+(1+Integer.parseInt(Interpreter.variables.get(forVariableName))));
        if (Integer.parseInt(Interpreter.variables.get(forVariableName)) >= Integer.parseInt(lastVal)) {
            System.out.println("Leaving for loop.  " + forVariableName + " had value " + Interpreter.variables.get(forVariableName));
            lazarusCommand.add( (Integer seven) -> Interpreter.variables.put(forVariableName,null));
            setTempNext(cFN);
        }
        else {
            Interpreter.nullJumpers.add(this);
        }
    }

}
