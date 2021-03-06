package com.mygdx.kiddiecode;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.Main;

public class TextInputHandlersAreNotLonely implements Input.TextInputListener {

    BlockTypes found = null;

    @Override
    public void input (String text) {
        for (BlockTypes blocktype : BlockTypes.values()) {
            if (text.toUpperCase().equals( blocktype.s.toUpperCase() ) || text.toUpperCase().equals( blocktype.toString() )) {
                found = blocktype;
            }
        }


        //spawn a new code block
        if (found != null) {
            System.out.println("Spawning " + found.toString());
            Vector3 dummy = Main.cam.unproject(new Vector3(Gdx.graphics.getWidth()/2f,Gdx.graphics.getHeight()/2f,0));
            MasterClass.blocks.add(new Block(dummy.x, Block.progCoord(dummy.y), found));
        }

    }

    @Override
    public void canceled () {
    }

}
