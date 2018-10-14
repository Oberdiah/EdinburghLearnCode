package com.mygdx.kiddiecode;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.input.GestureDetector;
import com.mygdx.game.Main;
import com.mygdx.game.input.HandleInput;

public class IREALLYDespiseGestureDetectors extends GestureDetector {
    private IHateGestureListeners blahblahblah;
    public IREALLYDespiseGestureDetectors(GestureListener listener) {
        super(listener);
        blahblahblah = (IHateGestureListeners) listener;
    }

    @Override
    public boolean touchUp(int x, int y, int pointer, int button) {
        //System.out.println("Touch up!");
        super.touchUp(x,y,pointer,button);
        //if (MasterClass.getStartTerminalNode() != null) {
        //    MasterClass.setStartTerminalNode(null);
        //}

        y=(int)Block.progCoord(y);
        if (x>Gdx.graphics.getWidth() - Gdx.graphics.getWidth()/ 2){


            if (y >0){
                System.out.println("clicked screen!"+x+","+y);
                if (x <Gdx.graphics.getWidth() - Gdx.graphics.getWidth()/ 2+ Gdx.graphics.getWidth() / 5){
                    if (y < Gdx.graphics.getWidth() / 5){
                        Main.codemode = !Main.codemode;
                        if (Main.codemode == true) {
                            HandleInput.gameZoom = Main.cam.zoom;
                            Main.cam.zoom = HandleInput.codeZoom;
                        } else {
                            HandleInput.codeZoom = Main.cam.zoom;
                            Main.cam.zoom = HandleInput.gameZoom;
                        }
                    }

                }

            }

        }




        blahblahblah.releaseDragData();
        return true;
    }
}
