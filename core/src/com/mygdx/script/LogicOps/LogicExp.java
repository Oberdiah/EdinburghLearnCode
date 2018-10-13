package com.mygdx.script.LogicOps;

import com.mygdx.script.Utils.Instruction;
import com.mygdx.script.Utils.Exception;

abstract public class LogicExp {

    abstract public Boolean evaluate();
    abstract public Exception getException();

    //TODO: base statement needed

    //TODO: make a exception on logic operators
    //suggested implementation: evaulation returns an enum of True, False, and NOnexistentOperandExceptaion


}
