package com.mygdx.script.LogicOps.GetInput;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.mygdx.script.LogicOps.LogicExp;
import com.mygdx.script.Utils.Exception;


public class GetSPressed extends LogicExp {
    final public Boolean evaluate(){
        if (Gdx.input.isKeyJustPressed(Input.Keys.S)) {
            return true;
        }
        return false;
    }

    public final Exception getException(){
        return null;
    }

}
