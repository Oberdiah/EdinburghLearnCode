package com.mygdx.script.Blocks;

import com.mygdx.script.TestScript.Interpreter;

abstract public class Block {
    private Block next;
    private Block tempNext;//for temporary shifts that will be undone if ever returned to this block
    public Block execute(){
        functionality();

        if (tempNext != null) {
            Block tR = tempNext;
            tempNext = null;
            return tR;
        }
        else if (next != null){
            //next.execute();
            return next;
        }
        else if (Interpreter.nullJumpers.size() > 0) {
            Block newnext = Interpreter.nullJumpers.get(Interpreter.nullJumpers.size() - 1);
            Interpreter.nullJumpers.remove(Interpreter.nullJumpers.size() - 1);
            //newnext.execute();
            return newnext;
        }
        else {
            return null;
        }
    }

    public void setNext(Block n) {next = n;}
    public void setTempNext(Block n) {tempNext = n;}

    protected abstract void functionality();
}
