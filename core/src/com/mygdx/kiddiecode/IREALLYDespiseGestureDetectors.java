package com.mygdx.kiddiecode;

import com.badlogic.gdx.input.GestureDetector;

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
        blahblahblah.releaseDragData();
        return true;
    }
}
