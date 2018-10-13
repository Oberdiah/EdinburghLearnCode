package com.mygdx.script.LogicOps.GetInput;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.mygdx.game.Main;
import com.mygdx.script.LogicOps.LogicExp;
import com.mygdx.script.Utils.Exception;


public class GetDDown extends LogicExp {

    final public Boolean evaluate() {
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            return true;
        }
        if (Main.p1deltaX > 0.5) {
            return true;
        }


        return false;
    }

    public final Exception getException() {
        return null;
    }

}
