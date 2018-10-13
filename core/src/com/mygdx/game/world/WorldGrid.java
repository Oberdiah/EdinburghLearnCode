package com.mygdx.game.world;

import com.mygdx.game.entites.Entity;
import com.mygdx.game.physics.PhysicsHandler;
import com.mygdx.game.tile.Tile;

import java.util.ArrayList;

public class WorldGrid {
    private ArrayList<Entity> entityArrayList = new ArrayList<Entity>();

    public static final int worldWidth = 100;
    public static final int worldHeight = 50;

    public Tile[][] getWorldArray() {
        return worldArray;
    }

    private Tile[][] worldArray = new Tile[500][100];

    public ArrayList<Entity> getEntityArrayList() {
        return entityArrayList;
    }

    public void setEntityArrayList(ArrayList<Entity> entityArrayList) {
        this.entityArrayList = entityArrayList;
    }

    public void init() {
        entityArrayList.add(new Entity());
    }

    public void setBlock(int x, int y, Tile.TileType t) {
        if (x < 0 || y < 0 || x >=  WorldGrid.worldWidth || y >= WorldGrid.worldHeight)
        {
            return;
        }

        if (worldArray[x][y] == null)
        {
            worldArray[x][y] = new Tile(t);

            if (Tile.Blocks(t))
            {
                worldArray[x][y].collision = PhysicsHandler.createCollisionBlock(x, y);
            }

            return;
        }

        if (Tile.Blocks(t) != Tile.Blocks(worldArray[x][y].type)) {
            if (Tile.Blocks(t))
            {
                worldArray[x][y].collision = PhysicsHandler.createCollisionBlock(x, y);
            }
            else
            {
                System.out.println(worldArray[x][y].collision);
                PhysicsHandler.groundBody.destroyFixture(worldArray[x][y].collision);
            }
        }

        worldArray[x][y].type = t;
    }
}
