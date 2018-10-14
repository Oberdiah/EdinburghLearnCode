package com.mygdx.script.TestScript;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.mygdx.game.Main;
import com.mygdx.game.entites.Entity;
import com.mygdx.game.world.WorldGrid;
import com.mygdx.kiddiecode.MasterClass;
import com.mygdx.script.Blocks.*;

import java.util.ArrayList;


public class Interpreter {
    //This is the class that reads the script code and interacts with the world
    public static com.mygdx.script.Blocks.Block startBlock = null;
    public static java.util.ArrayList<com.mygdx.script.Blocks.Block> tickBlocks = new java.util.ArrayList<com.mygdx.script.Blocks.Block>();

    public static java.util.ArrayList<Entity> relevantEntity = new java.util.ArrayList<Entity>();//if in an ontrigger, what are we using?

    public static int resolveVariable(String potential) {

        switch (potential) {
            case "getW":
                return boolToInt(GetW());
            case "getA":
                return boolToInt(GetA());
            case "getS":
                return boolToInt(GetS());
            case "getD":
                return boolToInt(GetD());
            case "getUp":
                return boolToInt(GetUp());
            case "getDown":
                return boolToInt(GetDown());
            case "getLeft":
                return boolToInt(GetLeft());
            case "getRight":
                return boolToInt(GetRight());
            //case "getPosX":
                //return relevantEntity.get(0)
        }
        try {
            return Integer.parseInt(potential);
        } catch (Exception e) {//if it can't parse it, must be a variable name!
            System.out.println(variables.toString());
            return Integer.parseInt(variables.get(potential));
        }
    }

    public static void initializeInterpreter() {
        //grab all code blocks
        java.util.ArrayList<com.mygdx.kiddiecode.Block> baileyBlocks = MasterClass.blocks;
        tickBlocks.clear();
        for (com.mygdx.kiddiecode.Block b : baileyBlocks) {
            switch (b.getType()) {
                case ONLOAD_TRIGGER:
                    startBlock = expandBlock(b);
                    break;
                case ONTICK_TRIGGER:
                case ONTICK_CLASS_TRIGGER:
                    tickBlocks.add(expandBlock(b));
                    break;
                default: continue;

            }
        }
    }

    public static void interpret() {
        //run the onload stuffs
        com.mygdx.script.Blocks.Block curBlock = startBlock;
        while (true) {
            if (curBlock == null) {
                break;
            }
            curBlock = curBlock.execute();
        }
        //start all the ticker scripts
        //System.out.println(tickBlocks.size());
        //for (com.mygdx.script.Blocks.Block b : tickBlocks) {
            //((BlockOnTick)b).ticker.tickScript = b;
        //}
        for (com.mygdx.script.Blocks.Block b : tickBlocks) {
            if (((BlockTicker)b).ticker.equals("Player1") || ((BlockTicker)b).ticker.equals("Player")) {
                WorldGrid.playerEntity.tickScript.add(b);
            }
        }
    }

    public static java.util.LinkedHashMap<String,String> variables = new java.util.LinkedHashMap<String, String>();


    public static java.util.ArrayList<com.mygdx.script.Blocks.Block> nullJumpers = new java.util.ArrayList<com.mygdx.script.Blocks.Block>();
    //^^^ This list keeps track of where you return to when you hit a null next.  If this list is empty, thread terminates.

    private static com.mygdx.script.Blocks.Block expandBlock(com.mygdx.kiddiecode.Block bB) {
        com.mygdx.script.Blocks.Block yB = null;
        if (bB == null) {
            return null;
        }
        java.util.Map<String, String> innerNodes = bB.getInnerNodes();
        switch (bB.getType()) {
            case ONLOAD_TRIGGER:
                yB = new BlockOnLoad();
                break;
            case LOOP_FROM_TO:
                yB = new BlockForLoop(innerNodes.get("[Var]"),
                        (innerNodes.get("[ValLower]")),
                        (innerNodes.get("[ValUpper]")),
                        expandBlock(bB.getOutgoingNodes().get(1).getLinked())
                );
                break;
            case IF_LESS_THAN:
                yB = new BlockIfLessThan(innerNodes.get("[Val1]"),
                        innerNodes.get("[Val2]"),
                        expandBlock(bB.getOutgoingNodes().get(1).getLinked()),
                        expandBlock(bB.getOutgoingNodes().get(2).getLinked())
                );
                break;
            case IF_GREATER_THAN:
                yB = new BlockIfGreaterThan(innerNodes.get("[Val1]"),
                        innerNodes.get("[Val2]"),
                        expandBlock(bB.getOutgoingNodes().get(1).getLinked()),
                        expandBlock(bB.getOutgoingNodes().get(2).getLinked())
                );
                break;
            case IF_EQUAL_TO:
                yB = new BlockIfEqualTo(innerNodes.get("[Val1]"),
                        innerNodes.get("[Val2]"),
                        expandBlock(bB.getOutgoingNodes().get(1).getLinked()),
                        expandBlock(bB.getOutgoingNodes().get(2).getLinked())
                );
                break;
            case IF_NOT_EQUAL_TO:
                yB = new BlockIfNotEqualTo(innerNodes.get("[Val1]"),
                        innerNodes.get("[Val2]"),
                        expandBlock(bB.getOutgoingNodes().get(1).getLinked()),
                        expandBlock(bB.getOutgoingNodes().get(2).getLinked())
                );
                break;
            case PLACE_BLOCK:
                yB = new BlockPlaceBlock(innerNodes.get("[BlockName]"),
                        innerNodes.get("[PosX]"),
                        innerNodes.get("[PosY]")
                );
                break;
            case PLACE_PLAYER:
                yB = new BlockPlacePlayer(
                        innerNodes.get("[PosX]"),
                        innerNodes.get("[PosY]")
                );
                break;
            case MOVE_PLAYER_BY:
                yB = new BlockApplyForceToPlayer(
                        innerNodes.get("[MoveX]"),
                        innerNodes.get("[MoveY]")
                );
                break;
            case SPAWN_ENTITY_AT:
                yB = new BlockSpawnEntity(innerNodes.get("[EntityName]"),
                        innerNodes.get("[EntityType]"),
                        innerNodes.get("[PosX]"),
                        innerNodes.get("[PosY]")
                );
                break;
            case MOVE_ENTITY:
                yB = new BlockMoveEntity(innerNodes.get("[MoveX]"),innerNodes.get("[MoveY]"));
                break;
            case ONTICK_TRIGGER:
                yB = new BlockOnTick((innerNodes.get("[EntityName]")));
                break;
            case ONTICK_CLASS_TRIGGER:
                yB = new BlockOnTickClass((innerNodes.get("[EntityClass]")));
                break;
        }
        //yB is yueyangBlock, bB is baileyBlock
        if (yB == null) {
            return null;
        }
        //should only make it here if default is 1st outgoing node, otherwise that should be handled in the switch statement
        yB.setNext(expandBlock(bB.getOutgoingNodes().get(0).getLinked()));

        return yB;
    }

    private static boolean GetW() {
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            return true;
        }
        if (Main.p1deltaY > 0.5) {
            return true;
        }
        return false;

    }

    private static boolean GetA() {

        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            return true;
        }

        if (Main.p1deltaX <= -0.5) {
            return true;
        }

        return false;

    }

    private static boolean GetS() {
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            return true;
        }
        if (Main.p1deltaY < -0.5) {
            return true;
        }


        return false;

    }

    private static boolean GetD() {
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            return true;
        }
        if (Main.p1deltaX > 0.5) {
            return true;
        }


        return false;
    }

    private static boolean GetUp() {
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            return true;
        }
        if (Main.p2deltaY > 0.5) {
            return true;
        }


        return false;

    }

    private static boolean GetDown() {
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            return true;
        }
        if (Main.p2deltaY < -0.5) {
            return true;
        }


        return false;

    }


    private static boolean GetRight() {
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            return true;
        }
        if (Main.p2deltaX > 0.5) {
            return true;
        }


        return false;

    }

    private static boolean GetLeft() {
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            return true;
        }
        if (Main.p2deltaX < -0.5) {
            return true;
        }


        return false;

    }


    private static int boolToInt(Boolean b) {
        return (b ? 1 : 0);
    }

    public static Entity getEntityFrom(String s) {
        if (s == "Player1") {
            return WorldGrid.playerEntity;
        }
        else {
            ArrayList<Entity> allEnts = Main.worldGrid.getEntityArrayList();
            for (Entity e : allEnts) {
                if (e.uniqueEntityName.equals(s)) {
                    return e;
                }
            }
            System.out.println("Warning - no such entity!");
            return null;
        }
    }

    public static ArrayList<Entity> getEntityFromClass(String s) {
        ArrayList<Entity> toReturn = new ArrayList<>();
        ArrayList<Entity> allEnts = Main.worldGrid.getEntityArrayList();
        for (Entity e : allEnts) {
            if (e.type.equals(s)) {
                toReturn.add(e);
            }
        }
        return toReturn;
    }
}
