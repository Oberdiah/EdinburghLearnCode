package com.mygdx.kiddiecode;

public class Node {
    private Block parentBlock;

    private float posX;//position relative to parent block
    private float posY;//position relative to parent block
    private boolean isHighlightedVar;
    private boolean hoverOver;
    private Node nodeConnectedTo;
    private boolean isInputNodeVar;

    public Node getNodeConnectedTo() {
        return nodeConnectedTo;
    }

    public Block getLinked() {
        if (nodeConnectedTo == null) {return null;}
        return nodeConnectedTo.parentBlock;
    }

    public Node(Block b,float x,float y,boolean isIncoming) {
        parentBlock = b;
        posX = x;
        posY = y;
        isHighlightedVar = false;
        hoverOver = false;
        nodeConnectedTo = null;
        isInputNodeVar = isIncoming;
    }

    public boolean isInputNode() {return isInputNodeVar;}

    public void connectTo(Node n) {
        if (nodeConnectedTo != null) {nodeConnectedTo.nodeConnectedTo = null;}
        if (n != null) {
            if (n.nodeConnectedTo != null) {n.nodeConnectedTo.nodeConnectedTo = null;}
            n.nodeConnectedTo = this;
        }
        nodeConnectedTo = n;
    }

    public void draw() {
        if (isHoverOver()) {
            Block.shupooey.setColor(1, 0.5f, 1, 1);
        }
        else if (isHighlighted()) {
            Block.shupooey.setColor(0, 0, 1, 1);
        }
        else {
            Block.shupooey.setColor(0.5f, 0.5f, 0.5f, 1);
        }
        Block.shupooey.circle(posX+parentBlock.getPosX(),Block.progCoord(posY+parentBlock.getPosY()),10);
        Block.shupooey.setColor(1,1,0,1);
        if (nodeConnectedTo != null) {
            Block.shupooey.rectLine(getPosX(), Block.progCoord(getPosY()), nodeConnectedTo.getPosX(), Block.progCoord(nodeConnectedTo.getPosY()),5);
        }
    }
    public boolean isHoverOver() {return hoverOver;}
    public void makeHoverOver() {hoverOver = true;}
    public void unmakeHoverOver() {hoverOver = false;}
    public boolean isHighlighted() {return isHighlightedVar;}
    public void highlight() {
        if (!isHighlightedVar) {System.out.println("Highlighting Node");}
        isHighlightedVar = true;
    }
    public void delight() {
        if (isHighlightedVar) {System.out.println("Delighting Node");}
        isHighlightedVar = false;
    }
    public com.badlogic.gdx.math.Circle boundCircle() {
        return new com.badlogic.gdx.math.Circle(posX+parentBlock.getPosX(),Block.progCoord(posY+parentBlock.getPosY()),10);
    }

    public float getPosX() {return posX+parentBlock.getPosX();}
    public float getPosY() {return posY+parentBlock.getPosY();}
    public float getRawPosX() {return posX;}
    public float getRawPosY() {return posY;}

}
