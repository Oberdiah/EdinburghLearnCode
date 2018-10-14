package com.mygdx.game.world;

import com.mygdx.game.Main;
import com.mygdx.game.entites.Entity;
import com.mygdx.game.physics.PhysicsHandler;
import com.mygdx.game.tile.Tile;

import java.util.ArrayList;

public class WorldGrid {
    private ArrayList<Entity> entityArrayList = new ArrayList<Entity>();

    public static final int worldWidth = 100;
    public static final int worldHeight = 50;

    public static Entity playerEntity;

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
        resetWorld();
    }

    public void resetWorld() {

        for (Entity e : entityArrayList) {
            PhysicsHandler.world.destroyBody(e.physicsObject);
        }

        entityArrayList.clear();

        Entity player = new Entity("Player1","Player");
        player.physicsObject = PhysicsHandler.createPhysicsEntity(5, 5, 1, 2, true);
        entityArrayList.add(player);
        playerEntity = player;

        for (int xSqr = 0; xSqr < WorldGrid.worldWidth; xSqr++) {
            for (int ySqr = 0; ySqr < WorldGrid.worldHeight; ySqr++) {
                if (ySqr == 0) {
                    Main.worldGrid.setBlock(xSqr, ySqr, Tile.TileType.ROCK);
                }
                else
                {
                    Main.worldGrid.setBlock(xSqr, ySqr, Tile.TileType.SKY);

                }
            }
        }
    }

    public static Tile.TileType getBlockType(int x, int y) {
        if (x < 0 || y < 0 || x >=  WorldGrid.worldWidth || y >= WorldGrid.worldHeight)
        {
            return null;
        }
        return Main.worldGrid.getWorldArray()[x][y].type;
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
                PhysicsHandler.groundBody.destroyFixture(worldArray[x][y].collision);
            }
        }

        worldArray[x][y].type = t;
    }
}
