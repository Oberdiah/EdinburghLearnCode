package com.mygdx.game.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.Main;
import com.mygdx.game.tile.Tile;
import com.mygdx.script.TestScript.Interpreter;

public class HandleInput {
    boolean wasTouched0 = false;
    boolean wasTouched1 = false;

    public static final float CODE_ZOOM = 21.329294f;

    private float gameZoom = 1;
    private float codeZoom = CODE_ZOOM;

    public void handleInput(OrthographicCamera cam) {


        // Yes, it's duplicated code, but it was fast to write. Deal with it.
        {
            if (Gdx.input.isButtonPressed(0)) {
                if (!wasTouched0) {
                    int x = Gdx.input.getX();
                    int y = Gdx.input.getY();

                    Vector3 vec = Main.cam.unproject(new Vector3(x, y,0));

                    int blockPressedX = (int) (vec.x);
                    int blockPressedY = (int) (vec.y);

                    Main.worldGrid.setBlock(blockPressedX, blockPressedY, Tile.TileType.ROCK);

                }
                wasTouched0 = true;
            } else {
                wasTouched0 = false;
            }

            if (Gdx.input.isButtonPressed(1)) {
                if (!wasTouched1) {
                    int x = Gdx.input.getX();
                    int y = Gdx.input.getY();

                    Vector3 vec = Main.cam.unproject(new Vector3(x, y,0));

                    int blockPressedX = (int) (vec.x);
                    int blockPressedY = (int) (vec.y);

                    Main.worldGrid.setBlock(blockPressedX, blockPressedY, Tile.TileType.SKY);

                }
                wasTouched1 = true;
            } else {
                wasTouched1 = false;
            }
        }


        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            if (!Main.codemode) {
                cam.zoom *= 1.02;
            }
            //System.out.println(cam.zoom);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.Q)) {
            if (!Main.codemode) {//code zooming is STUPID it sucks DON'T do it bro
                cam.zoom *= 0.98;
            }
        }
//        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
//            cam.rotate(-0.5f, 0, 0, 1);
//        }
//        if (Gdx.input.isKeyPressed(Input.Keys.E)) {
//            cam.rotate(0.5f, 0, 0, 1);
//        }
        if (Main.codemode == true) {
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
            if (Gdx.input.isKeyJustPressed(Input.Keys.S)) {
                Gdx.input.getTextInput(Main.sh, "Save as", "", "");
                //Saver.save();
            }
            if (Gdx.input.isKeyJustPressed(Input.Keys.L)) {
                Gdx.input.getTextInput(Main.lh, "Load script", "", "");
                //Loader.load();
            }
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            Main.codemode = !Main.codemode;
            if (Main.codemode == true) {gameZoom = Main.cam.zoom;Main.cam.zoom = codeZoom;}
            else {codeZoom = Main.cam.zoom;Main.cam.zoom = gameZoom;}
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.P)) {
            Interpreter.initializeInterpreter();
            Interpreter.interpret();
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.O) && Main.codemode == true) {
            Gdx.input.getTextInput(Main.codeblockSearcher, "Find Block", "", "");
        }

    }
}
