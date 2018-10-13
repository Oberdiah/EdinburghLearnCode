package com.mygdx.game.tick;

import com.mygdx.game.Main;
import com.mygdx.game.entites.Entity;
import com.mygdx.game.physics.PhysicsHandler;
import com.mygdx.script.Blocks.Block;

import java.util.ArrayList;

public class Ticker {
    public void tick() {
        ArrayList<Entity> entities = Main.worldGrid.getEntityArrayList();
        for(Entity i : entities){
            if (i.tickScript!=null){
                Block curBlock = i.tickScript.execute();
                while (curBlock != null) {
                    curBlock = curBlock.execute();
                }
            }
        }
        PhysicsHandler.tick();
    }
}
