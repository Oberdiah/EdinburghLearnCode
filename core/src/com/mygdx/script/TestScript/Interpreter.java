package com.mygdx.script.TestScript;

import com.mygdx.kiddiecode.BlockTypes;
import com.mygdx.kiddiecode.MasterClass;
import com.mygdx.script.Blocks.BlockForLoop;
import com.mygdx.script.Blocks.BlockOnLoad;


public class Interpreter {
    //This is the class that reads the script code and interacts with the world
    public static com.mygdx.script.Blocks.Block startBlock = null;

    public static void initializeInterpreter() {
        //grab all code blocks
        java.util.ArrayList<com.mygdx.kiddiecode.Block> baileyBlocks = MasterClass.blocks;

        for (com.mygdx.kiddiecode.Block b: baileyBlocks) {
            switch (b.getType()) {
                case ONLOAD_TRIGGER:
                    startBlock = expandBlock(b);
                    break;
                default: continue;
            }
        }
    }

    public static void interpret() {
        startBlock.execute();
    }

    public static java.util.LinkedHashMap<String,String> variables = new java.util.LinkedHashMap<String, String>();


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
                        Integer.parseInt(innerNodes.get("[ValLower]")),
                        Integer.parseInt(innerNodes.get("[ValUpper]")),
                        expandBlock(bB.getOutgoingNodes().get(1).getLinked())
                );
                return yB;
        }
        //yB is yueyangBlock, bB is baileyBlock
        if (yB == null) {
            return null;
        }
        //should only make it here if only has 1 outgoing node, otherwise that should be handled in the switch statement
        yB.setNext(expandBlock(bB.getOutgoingNodes().get(0).getLinked()));

        return yB;
    }

}
