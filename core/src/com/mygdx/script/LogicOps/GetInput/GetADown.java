package com.mygdx.script.LogicOps.GetInput;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.mygdx.script.LogicOps.LogicExp;
import com.mygdx.script.Utils.Exception;


public class GetADown extends LogicExp {
    final public Boolean evaluate(){
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            return true;
        }
        return false;
    }

    public final Exception getException(){
        return null;
    }

}
