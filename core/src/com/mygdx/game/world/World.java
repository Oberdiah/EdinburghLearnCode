package com.mygdx.game.world;

import com.mygdx.game.entites.Entity;
import com.mygdx.game.tile.Tile;

import java.util.ArrayList;

public class World {
    private ArrayList<Entity> entityArrayList = new ArrayList<Entity>();

    public static final int worldWidth = 500;
    public static final int worldHeight = 100;

    public Tile.TileType[][] getWorldArray() {
        return worldArray;
    }

    private Tile.TileType[][] worldArray = new Tile.TileType[500][100];

    public ArrayList<Entity> getEntityArrayList() {
        return entityArrayList;
    }

    public void setEntityArrayList(ArrayList<Entity> entityArrayList) {
        this.entityArrayList = entityArrayList;
    }

    public void init() {
        entityArrayList.add(new Entity());

        for (int xSqr = 0; xSqr < World.worldWidth; xSqr++) {
            for (int ySqr = 0; ySqr < World.worldHeight; ySqr++) {
                if (Math.random() < 0.5) {
                    worldArray[xSqr][ySqr] = Tile.TileType.Rock;
                }else{
                    worldArray[xSqr][ySqr] = Tile.TileType.Sky;

                }

            }
        }
    }
}
