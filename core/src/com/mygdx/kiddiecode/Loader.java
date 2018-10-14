package com.mygdx.kiddiecode;


import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;

import java.io.FileInputStream;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Loader {
    public static void load(String filename){
        List<Block> alBlocks = new ArrayList<Block>();
        List<String> loadedBlocks = new ArrayList<String>();
        String outString = "";
        try {
            Scanner in = new Scanner(new FileReader(filename));
            StringBuilder sb = new StringBuilder();
            while(in.hasNext()) {
                sb.append(in.next());
            }
            in.close();
            outString = sb.toString();
        }catch (Exception e){

        }
        loadedBlocks = Arrays.asList(outString.split("="));
        for (String s : loadedBlocks){
            System.out.println(s);

            Pattern tp = Pattern.compile("(?<=type\\().*?(?=\\))");
            Pattern xp = Pattern.compile("(?<=posX\\().*?(?=\\))");
            Pattern yp = Pattern.compile("(?<=posY\\().*?(?=\\))");

            Matcher m = tp.matcher(s);
            m.find();
            System.out.println(m.group(0));
            BlockTypes type = BlockTypes.valueOf(m.group(0));


            m = xp.matcher(s);
            m.find();
            int posX = (int)Float.parseFloat(m.group(0));


            m = yp.matcher(s);
            m.find();
            int posY = (int)Float.parseFloat(m.group(0));

            Block x = new Block(posX, posY, type);

            MasterClass.blocks.add(x);
            alBlocks.add(x);
//            MasterClass.blocks.add(new Block(150, 350, BlockTypes.IF_LESS_THAN));

        }
        List<String> nodeInfo = new ArrayList<String>();
        int bIndex = 0;
        for (String s : loadedBlocks){
            nodeInfo = Arrays.asList(s.split("@"));
            for (String n : nodeInfo){
                int parBIndex, lIndex, rIndex = -1;
                System.out.println(n);
                Pattern ticn = Pattern.compile("(?<=to_incoming_node\\().*?(?=\\))");
                Pattern togn = Pattern.compile("(?<=to_outgoing_node\\().*?(?=\\))");
                Matcher m = ticn.matcher(n);
                if (m.find()){
                    rIndex = Integer.parseInt(m.group(0));
                    Pattern ogn = Pattern.compile("(?<=outgoing_node\\().*?(?=\\))");
                    m = ogn.matcher(n);
                    m.find();
                    lIndex = Integer.parseInt(m.group(0));
                    Pattern parBlk = Pattern.compile("(?<=of_block\\().*?(?=\\))");
                    m = parBlk.matcher(n);
                    m.find();
                    parBIndex = Integer.parseInt(m.group(0));
                    System.out.println(lIndex+" "+rIndex+" "+parBIndex);
                    MasterClass.blocks.get(bIndex).getOutgoingNodes().get(lIndex).connectTo(
                            MasterClass.blocks.get(parBIndex).getIncomingNodes().get(rIndex)
                    );
                }
                m = togn.matcher(n);
                if (m.find()){
                    rIndex = Integer.parseInt(m.group(0));
                    Pattern ogn = Pattern.compile("(?<=incoming_node\\().*?(?=\\))");
                    m = ogn.matcher(n);
                    m.find();
                    lIndex = Integer.parseInt(m.group(0));
                    Pattern parBlk = Pattern.compile("(?<=of_block\\().*?(?=\\))");
                    m = parBlk.matcher(n);
                    m.find();
                    parBIndex = Integer.parseInt(m.group(0));
                    System.out.println(lIndex+" "+rIndex+" "+parBIndex+" "+bIndex);
                    MasterClass.blocks.get(bIndex).getIncomingNodes().get(lIndex).connectTo(
                            MasterClass.blocks.get(parBIndex).getOutgoingNodes().get(rIndex)
                    );
                }

                Pattern innVar = Pattern.compile("(?<=var\\().*?(?=\\))");
                Pattern innValue = Pattern.compile("(?<=value\\().*?(?=\\))");
                Pattern innIndex = Pattern.compile("(?<=inner_node\\().*?(?=\\))");
                String var, value = "";
                int innIndx = -1;
                m = innVar.matcher(n);
                if (m.find()){
                    var = m.group(0);
                    m = innValue.matcher(n);
                    m.find();
                    value = m.group(0);
                    m = innIndex.matcher(n);
                    m.find();
                    innIndx = Integer.parseInt(m.group(0));
                    MasterClass.blocks.get(bIndex).setInnerNode(var, value);
                }

            }
        bIndex++;
        }
    }
}
