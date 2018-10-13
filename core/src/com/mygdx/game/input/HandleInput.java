package com.mygdx.game.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.mygdx.game.Main;
import com.mygdx.script.TestScript.Interpreter;

public class HandleInput {

    private float gameZoom = 1;
    private float codeZoom = 21.329294f;

    public void handleInput(OrthographicCamera cam) {
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            cam.zoom *= 1.02;
            //System.out.println(cam.zoom);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.Q)) {
            cam.zoom *= 0.98;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            cam.translate(-3, 0, 0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            cam.translate(3, 0, 0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            cam.translate(0, -3, 0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            cam.translate(0, 3, 0);
        }
//        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
//            cam.rotate(-0.5f, 0, 0, 1);
//        }
//        if (Gdx.input.isKeyPressed(Input.Keys.E)) {
//            cam.rotate(0.5f, 0, 0, 1);
//        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            Main.codemode = !Main.codemode;
            if (Main.codemode == true) {Main.cam.zoom = codeZoom;}
            else {Main.cam.zoom = gameZoom;}
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.P)) {
            Interpreter.initializeInterpreter();
            Interpreter.interpret();
        }

    }
}
