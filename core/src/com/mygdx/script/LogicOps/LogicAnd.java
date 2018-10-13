package com.mygdx.script.LogicOps;

import com.mygdx.script.Utils.Instruction;
import com.mygdx.script.Utils.Exception;

public class LogicAnd extends LogicExp {
    private LogicExp left;
    private LogicExp right;
    private Exception ex;
    public final Boolean evaluate(){
        if (left == null || right == null){
            ex = new Exception("EmptyOperandException");
            ex.announce();
            return false;
        }else {
            return (left.evaluate() && right.evaluate());
        }
    }

    public void setLeft(LogicExp left) {
        this.left = left;
    }

    public void setRight(LogicExp right) {
        this.right = right;
    }

    public Exception getException() {
        return ex;
    }
}
