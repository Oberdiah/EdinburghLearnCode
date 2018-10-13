package com.mygdx.script.Blocks;

public class BlockIfLessThan extends Block {

    private String possiblyLower;
    private String possiblyHigher;
    private Block ifFalse;

    public BlockIfLessThan(String possiblyLower,String possiblyHigher,Block ifFalse) {
        this.possiblyLower = possiblyLower;
        this.possiblyHigher = possiblyHigher;
        this.ifFalse = ifFalse;
    }

    @Override
    protected void functionality() {
        if (Integer.parseInt(possiblyLower) < Integer.parseInt(possiblyHigher)) { }
        else {
            setNext(ifFalse);
        }
    }
}
