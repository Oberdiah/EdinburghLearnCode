package com.mygdx.kiddiecode;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.Main;

public class IHateGestureListeners implements GestureListener {

    private Main connecticut;
    private static boolean isCurrentlyDraggingSomething;
    private float startX;
    private float startY;

    public IHateGestureListeners(Main mgd) {
        connecticut = mgd;
        isCurrentlyDraggingSomething = false;
        hasBeenPanning = false;
    }


    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {
        Vector3 woohoo = Main.cam.unproject(new Vector3(x,y,0));
        startX = woohoo.x;
        startY = woohoo.y;
        java.util.ArrayList<Block> blocks = connecticut.getBlocks();
        java.util.ArrayList<Node> nodes = connecticut.getAllNodes();
        if (MasterClass.getStartTerminalNode() != null) {
            MasterClass.getStartTerminalNode().delight();
            MasterClass.setStartTerminalNode(null);
        }
        for (Node node : nodes) {
            if ( (Intersector.overlaps(node.boundCircle(),pointToRect(startX,(startY))) && !isCurrentlyDraggingSomething)) {

                System.out.println("Found node");
                node.highlight();
                isCurrentlyDraggingSomething = true;
                MasterClass.setStartTerminalNode(node);
                MasterClass.setDrag(node.getPosX(),node.getPosY());
                break;
            }
        }
        for (Block block : blocks) {
            java.util.Map<String,com.badlogic.gdx.math.Rectangle> bboxes = block.getInnerNodesEditBoundingBoxes();
            for (String editable : bboxes.keySet()) {
                if ( (Intersector.overlaps(bboxes.get(editable),pointToRect(startX,Block.progCoord(startY))) && !isCurrentlyDraggingSomething)) {
                    //clicked on the thingy!
                    System.out.println(editable + " clicked!");
                    MasterClass.listener.setBlock(block);
                    MasterClass.listener.setInnerNodeKey(editable);
                    Gdx.input.getTextInput(MasterClass.listener, editable, block.getInnerNodes().get(editable), "");
                    //System.out.println(startX);
                    //System.out.println(startY);
                    //System.out.println(bboxes.get(editable).x);
                    //System.out.println(bboxes.get(editable).y);
                }
            }
        }

        return false;
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {
        return false;
    }

    @Override
    public boolean longPress(float x, float y) {
        return false;
    }

    @Override
    public boolean fling(float velocityX, float velocityY, int button) {
        return false;
    }

    private com.badlogic.gdx.math.Rectangle pointToRect(float x,float y) {
        return new com.badlogic.gdx.math.Rectangle(x,y,1,1);
    }

    private boolean hasBeenPanning;


    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {
        Vector3 woohoo = Main.cam.unproject(new Vector3(x,y,0));
        x = woohoo.x;
        y = woohoo.y;
        java.util.ArrayList<Block> transcontinentalRailroad = connecticut.getBlocks();
        java.util.ArrayList<Node> phillyCheeseSteak = connecticut.getAllNodes();

        Node node = MasterClass.getStartTerminalNode();
        /*if ( node != null) {
            if (!hasBeenPanning) {
                //MasterClass.setDrag(x,y);
            }
            //MasterClass.addDrag(deltaX,deltaY);
        }*/

        //check if dragging over a node
        for (Node n : phillyCheeseSteak) {
            if (n.isHighlighted()) {continue;}

            //check if x+deltaX,y+deltaY is over it
            if ( (Intersector.overlaps(n.boundCircle(),pointToRect(MasterClass.getDragX(),(MasterClass.getDragY()))))) {
                //we want to mark this node as 'hoverOver'
                //but only if it would not create input-input or output-output connection
                if (MasterClass.getStartTerminalNode() != null && n.isInputNode() != MasterClass.getStartTerminalNode().isInputNode()) {
                    n.makeHoverOver();
                    MasterClass.setHoverOver(n);
                }
            }
            else {
                n.unmakeHoverOver();
                if (MasterClass.getHoverOver() == n) {
                    MasterClass.setHoverOver(null);
                }
            }
        }

        for (Block block : transcontinentalRailroad) {

            if ((Intersector.overlaps(block.boundRect(),pointToRect(startX,block.progCoord(startY))) && !isCurrentlyDraggingSomething)
                    || block.isHighlighted()) {

                block.highlight();
                isCurrentlyDraggingSomething = true;

                block.setPosX(block.getPosX() + deltaX);
                block.setPosY(block.getPosY() + deltaY);

                break;
            }
        }
        hasBeenPanning = true;
        return false;
    }

    @Override
    public boolean panStop(float x, float y, int pointer, int button) {
        hasBeenPanning = false;
        releaseDragData();
        return false;
    }

    public void releaseDragData() {
        java.util.ArrayList<Block> transcontinentalRailroad = connecticut.getBlocks();
        java.util.ArrayList<Node> phillyCheeseSteak = connecticut.getAllNodes();

        Node terminalNode = MasterClass.getStartTerminalNode();
        if (terminalNode != null && terminalNode.isHighlighted()) {
            //connect up with other nodes!
            terminalNode.connectTo(MasterClass.getHoverOver());
        }

        for (Block block : transcontinentalRailroad) {
            block.delight();
        }

        for (Node node : phillyCheeseSteak) {
            node.delight();
            node.unmakeHoverOver();
        }

        isCurrentlyDraggingSomething = false;
        MasterClass.setStartTerminalNode(null);
    }

    @Override
    public boolean zoom(float originalDistance, float currentDistance){

        Main.cam.zoom += (originalDistance- currentDistance) * Gdx.graphics.getDeltaTime() ;
        return false;
    }

    @Override
    public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
        return false;
    }

    @Override
    public void pinchStop () {

    }
}
