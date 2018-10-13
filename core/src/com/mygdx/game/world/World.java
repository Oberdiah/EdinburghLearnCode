package com.mygdx.game.world;

import com.mygdx.game.entites.Entity;

import java.util.ArrayList;

public class World {
    private ArrayList<Entity> entityArrayList = new ArrayList<Entity>();

    public ArrayList<Entity> getEntityArrayList() {
        return entityArrayList;
    }

    public void setEntityArrayList(ArrayList<Entity> entityArrayList) {
        this.entityArrayList = entityArrayList;
    }

    public void init() {
        entityArrayList.add(new Entity());
    }
}
