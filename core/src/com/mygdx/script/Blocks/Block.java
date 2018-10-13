package com.mygdx.script.Blocks;

import com.mygdx.script.TestScript.Interpreter;

abstract public class Block {
    private Block next;
    public void execute(){
        functionality();

        if (next != null){
            next.execute();
        }
        else if (Interpreter.nullJumpers.size() > 0) {
            Block newnext = Interpreter.nullJumpers.get(Interpreter.nullJumpers.size() - 1);
            Interpreter.nullJumpers.remove(Interpreter.nullJumpers.size() - 1);
            newnext.execute();
        }
    }

    public void setNext(Block n) {next = n;}

    protected abstract void functionality();
}
