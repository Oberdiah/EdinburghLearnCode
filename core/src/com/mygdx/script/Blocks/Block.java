package com.mygdx.script.Blocks;

import com.mygdx.script.TestScript.Interpreter;

import java.util.function.Consumer;

abstract public class Block {
    private Block next;
    private Block tempNext;//for temporary shifts that will be undone if ever returned to this block
    protected boolean useTempNext = false;

    public Block execute(){
        functionality();

        if (useTempNext && tempNext != null) {
            Block tR = tempNext;
            tempNext = null;
            useTempNext = false;
            return tR;
        }
        else if (useTempNext && tempNext == null) {
            useTempNext = false;
            if (Interpreter.nullJumpers.size() > 0) {
                //System.out.println("Nulljumping");
                Block newnext = Interpreter.nullJumpers.get(Interpreter.nullJumpers.size() - 1);
                Interpreter.nullJumpers.remove(Interpreter.nullJumpers.size() - 1);
                return newnext;
            }
            else {
                removeVars();
                Interpreter.relevantEntity = null;
                return null;
            }
        }
        else if (next != null){
            return next;
        }
        else if (Interpreter.nullJumpers.size() > 0) {
            //System.out.println("Nulljumping");
            Block newnext = Interpreter.nullJumpers.get(Interpreter.nullJumpers.size() - 1);
            Interpreter.nullJumpers.remove(Interpreter.nullJumpers.size() - 1);
            return newnext;
        }
        else {
            Interpreter.relevantEntity = null;
            removeVars();
            return null;
        }
    }

    private void removeVars() {
        if (Interpreter.relevantEntity != null) {
            for (String vname : Interpreter.relevantEntity.exposedVariables.keySet()) {
                //remove all local variables, store in exposedVariables though
                String temp = Interpreter.variables.get(vname);
                if (temp != null) {
                    Interpreter.relevantEntity.exposedVariables.put(vname, Integer.parseInt(temp));
                }
                Interpreter.variables.put(vname, null);
            }
        }
    }

    public void setNext(Block n) {next = n;}
    public void setTempNext(Block n) {tempNext = n;}

    protected abstract void functionality();
}
