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
            Interpreter.variables.put(forVariableName,""+(Interpreter.resolveVariable(startVal)-1));
            System.out.println("Entering for loop.  " + forVariableName + " has value " + "1"+Interpreter.variables.get(forVariableName));
        }
        //increment variable
        Interpreter.variables.put(forVariableName,""+(1+Integer.parseInt(Interpreter.variables.get(forVariableName))));
        if (Integer.parseInt(Interpreter.variables.get(forVariableName)) > Interpreter.resolveVariable(lastVal)) {
            System.out.println("Leaving for loop.  " + forVariableName + " had value -1+" + Interpreter.variables.get(forVariableName));
            Interpreter.variables.put(forVariableName,null);
            //lazarusCommand.add( (Integer seven) -> {Interpreter.variables.put(forVariableName,null);System.out.println("yiggles");} );
            //System.out.println("yoogles");
            useTempNext = true;
            setTempNext(cFN);
        }
        else {
            Interpreter.nullJumpers.add(this);
        }
    }

}
