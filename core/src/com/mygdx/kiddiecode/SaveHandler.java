package com.mygdx.kiddiecode;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.Main;

import java.util.EnumSet;

public class SaveHandler implements Input.TextInputListener{

    String fileName = null;

    @Override
    public void input (String text) {
        fileName = text;
        Saver.save(fileName);

    }

    @Override
    public void canceled () {
    }

}
