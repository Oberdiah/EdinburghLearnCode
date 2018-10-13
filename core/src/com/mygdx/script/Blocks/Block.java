package com.mygdx.script.Blocks;

abstract public class Block {
    private Block next;
    public void execute(){
        functionality();

        if (next != null){
            next.execute();
        }
    }

    public void setNext(Block n) {next = n;}

    protected abstract void functionality();
}
