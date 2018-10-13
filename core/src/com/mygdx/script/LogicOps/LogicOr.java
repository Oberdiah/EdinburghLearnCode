package com.mygdx.script.LogicOps;

import com.mygdx.script.Utils.Exception;

public class LogicOr extends LogicExp {
    private LogicExp left;
    private LogicExp right;
    private Exception ex;
    public final Boolean evaluate(){
        if (left == null || left == null){
            ex = new Exception("EmptyOperandException");
            ex.announce();
            return false;
        }else{
            return (left.evaluate() || right.evaluate());
        }
    }

    public Exception getException(){
        return this.ex;
    }



}
