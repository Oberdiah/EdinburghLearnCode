package com.mygdx.game.tick;

import com.mygdx.game.Main;
import com.mygdx.game.entites.Entity;
import com.mygdx.game.physics.PhysicsHandler;

import java.util.ArrayList;

public class Ticker {
    public void tick() {
        ArrayList<Entity> entities = Main.worldGrid.getEntityArrayList();
        for(Entity i : entities){
            if (i.tickScript!=null){
                i.tickScript.execute();
            }
        }
        PhysicsHandler.tick();
    }
}
