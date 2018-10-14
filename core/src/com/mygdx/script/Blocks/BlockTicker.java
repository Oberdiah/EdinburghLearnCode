package com.mygdx.script.Blocks;

public class BlockTicker extends Block {
    public String ticker;
    public boolean classwise = false;
    BlockTicker(String t) {
        ticker = t;
    }

    @Override
    protected void functionality() {}
}
