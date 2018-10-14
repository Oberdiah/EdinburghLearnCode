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
        super.touchUp(x, y, pointer, button);
        //if (MasterClass.getStartTerminalNode() != null) {
        //    MasterClass.setStartTerminalNode(null);
        //}








        y = (int) Block.progCoord(y);
        if (y > Main.goToCodeButton.getY()) {
            if (y< Main.goToCodeButton.getHeight()+Main.goToCodeButton.getY()) {
                if (x > Main.goToCodeButton.getX()) {
                    if (x < Main.goToCodeButton.getX()+Main.goToCodeButton.getWidth()) {

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

                //add new button
                if (x > Main.addBlockButton.getX()) {

                    if (x < Main.addBlockButton.getX() + Main.addBlockButton.getWidth()) {

                        Gdx.input.getTextInput(Main.codeblockSearcher, "Find Block", "", "");
                    }
                }

                if (x > Main.saveButton.getX()) {

                    if (x < Main.saveButton.getX() + Main.saveButton.getWidth()) {
                        Gdx.input.getTextInput(Main.sh, "Save as", "", "");
                    }
                }

                if (x > Main.loadButton.getX()) {

                    if (x < Main.loadButton.getX() + Main.loadButton.getWidth()) {
                        Gdx.input.getTextInput(Main.lh, "Load script", "", "");
                    }
                }


            }


        }


        blahblahblah.releaseDragData();
        return true;
    }
}
