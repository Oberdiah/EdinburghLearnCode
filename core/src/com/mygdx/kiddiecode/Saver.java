package com.mygdx.kiddiecode;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Saver {
    public static boolean save(String filename){
        int blockIndex = 0;
        List<String> lines = new ArrayList<String>();
        Path file = Paths.get(filename);

        for (Block b : MasterClass.blocks){
            //b.get
            //if (lines.get(0).equals("rabbit! tank!")){lines.remove(0);}
            String blockName = "block("+MasterClass.blocks.indexOf(b)+") type("+b.getType()+") posX("+b.getPosX()+") posY("+b.getPosY()+")";
            lines.add(blockName);
            lines.add("@");
            System.out.println("block("+MasterClass.blocks.indexOf(b)+") type("+b.getType()+") posX("+b.getPosX()+") posY("+b.getPosY()+")");
            for (Node n : b.getIncomingNodes()){
                if (n.getLinked() != null) {
                    int numOfctn = n.getLinked().getOutgoingNodes().indexOf(n.getNodeConnectedTo());
                    lines.add("incoming_node(" + b.getIncomingNodes().indexOf(n) + ") to_outgoing_node(" + numOfctn + ") of_block(" + MasterClass.blocks.indexOf(n.getLinked())+")");
                    System.out.println("incoming_node(" + b.getIncomingNodes().indexOf(n) + ") to_outgoing_node(" + numOfctn + ") of_block(" + MasterClass.blocks.indexOf(n.getLinked())+")");
                    lines.add("@");
                }
            }
            for (Node n : b.getOutgoingNodes()){
                if (n.getLinked() != null) {
                    int numOfogn = n.getLinked().getIncomingNodes().indexOf(n.getNodeConnectedTo());
                    lines.add("outgoing_node(" + b.getOutgoingNodes().indexOf(n) + ") to_incoming_node(" + numOfogn + ") of_block(" + MasterClass.blocks.indexOf(n.getLinked())+")");
                    System.out.println("outgoing_node(" + b.getOutgoingNodes().indexOf(n) + ") to_incoming_node(" + numOfogn + ") of_block(" + MasterClass.blocks.indexOf(n.getLinked())+")");
                    lines.add("@");
                }
            }
            int innerIndex = 0;
            for (Map.Entry<String, String> n : b.getInnerNodes().entrySet()){
                lines.add("inner_node("+innerIndex+") var("+n.getKey()+") value("+n.getValue()+")");
                System.out.println("inner_node("+innerIndex+") var("+n.getKey()+") value("+n.getValue()+")");
                lines.add("@");
                innerIndex++;
            }
            lines.add("=");

        }
        try {
            Files.write(file, lines, Charset.forName("UTF-8"));
        }catch (Exception e){

        }
        return true;
    }
}
