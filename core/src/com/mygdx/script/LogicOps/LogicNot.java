package com.mygdx.script.LogicOps;

import com.mygdx.script.Utils.Instruction;
import com.mygdx.script.Utils.Exception;

public class LogicNot extends LogicExp {
    private LogicExp operend;
    private Exception ex;
    public Boolean evaluate(){
        if (operend == null){
            ex = new Exception("EmptyOperandException");
            return false;
        }
        return (!operend.evaluate());
    }

    public Exception getException(){
        return ex;
    }



}
