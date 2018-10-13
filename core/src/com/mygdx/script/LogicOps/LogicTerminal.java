package com.mygdx.script.LogicOps;

import com.mygdx.script.Utils.Exception;
import com.mygdx.script.Utils.Instruction;

public class LogicTerminal extends LogicExp{
    private Instruction i;
    private Exception e;

    public LogicTerminal(Instruction i){
        this.i = i;
    }
    public Boolean evaluate(){
        return this.i.evaluate();
    }
    public Exception getException() {
        return e;
    }
}
