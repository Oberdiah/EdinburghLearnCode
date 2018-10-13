package com.mygdx.script.TestScript;

import com.mygdx.kiddiecode.BlockTypes;
import com.mygdx.kiddiecode.MasterClass;
import com.mygdx.script.Blocks.*;


public class Interpreter {
    //This is the class that reads the script code and interacts with the world
    public static com.mygdx.script.Blocks.Block startBlock = null;
    public static com.mygdx.script.Blocks.Block tickBlock = null;

    public static int resolveVariable(String potential) {
        try {
            return Integer.parseInt(potential);
        }
        catch (Exception e) {//if it can't parse it, must be a variable name!
            System.out.println(variables.toString());
            return Integer.parseInt(variables.get(potential));
        }
    }

    public static void initializeInterpreter() {
        //grab all code blocks
        java.util.ArrayList<com.mygdx.kiddiecode.Block> baileyBlocks = MasterClass.blocks;

        for (com.mygdx.kiddiecode.Block b: baileyBlocks) {
            switch (b.getType()) {
                case ONLOAD_TRIGGER:
                    startBlock = expandBlock(b);
                    break;
                case ONTICK_TRIGGER:
                    tickBlock = expandBlock(b);

                    break;
                default: continue;
            }
        }
    }

    public static void interpret() {
        //run the onload stuffs
        com.mygdx.script.Blocks.Block curBlock = startBlock;
        while (true) {
            if (curBlock == null) {break;}
            curBlock = curBlock.execute();
        }
    }


    public static java.util.LinkedHashMap<String,String> variables = new java.util.LinkedHashMap<String, String>();

    public static java.util.ArrayList<com.mygdx.script.Blocks.Block> nullJumpers = new java.util.ArrayList<com.mygdx.script.Blocks.Block>();
    //^^^ This list keeps track of where you return to when you hit a null next.  If this list is empty, thread terminates.

    private static com.mygdx.script.Blocks.Block expandBlock(com.mygdx.kiddiecode.Block bB) {
        com.mygdx.script.Blocks.Block yB = null;
        if (bB == null) {return null;}
        java.util.Map<String,String> innerNodes = bB.getInnerNodes();
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
                        expandBlock(bB.getOutgoingNodes().get(1).getLinked())
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
            case MOVE_PLAYER_BY:
                yB = new BlockApplyForceToPlayer(
                        innerNodes.get("[MoveX]"),
                        innerNodes.get("[MoveY]")
                );
        }
        //yB is yueyangBlock, bB is baileyBlock
        if (yB == null) {
            return null;
        }
        //should only make it here if default is 1st outgoing node, otherwise that should be handled in the switch statement
        yB.setNext(expandBlock(bB.getOutgoingNodes().get(0).getLinked()));

        return yB;
    }

}
