package com.mygdx.game.tick;

import com.mygdx.game.Main;
import com.mygdx.game.entites.Entity;
import com.mygdx.game.physics.PhysicsHandler;
import com.mygdx.script.Blocks.Block;
import com.mygdx.script.TestScript.Interpreter;

import java.util.ArrayList;

public class Ticker {
    public void tick() {
        ArrayList<Entity> entities = Main.worldGrid.getEntityArrayList();
        for(Entity i : entities){
            for (Block tS : i.tickScript) {
                if (tS != null) {
                    Interpreter.relevantEntity = i;
                    Block curBlock = tS.execute();
                    while (curBlock != null) {
                        curBlock = curBlock.execute();
                    }
                }
            }
        }
        PhysicsHandler.tick();
    }


}
