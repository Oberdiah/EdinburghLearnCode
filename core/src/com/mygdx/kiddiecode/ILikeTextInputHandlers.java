package com.mygdx.kiddiecode;

import com.badlogic.gdx.Input;

public class ILikeTextInputHandlers implements Input.TextInputListener {
    private Block block;
    private String innerNodeKey;

    @Override
    public void input (String text) {
            block.getInnerNodes().put(innerNodeKey, text);
    }

    @Override
    public void canceled () {
    }

    public void setBlock(Block b) {block = b;}
    public void setInnerNodeKey(String s) {innerNodeKey = s;}
}