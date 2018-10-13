package com.mygdx.script.Blocks;

abstract public class Block {
    private Block next;
    public void execute(){
        functionality();

        if (next != null){
            next.execute();
        }
    }

    protected abstract void functionality();
}
