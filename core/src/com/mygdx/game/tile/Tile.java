package com.mygdx.game.tile;

import com.badlogic.gdx.physics.box2d.Fixture;

public class Tile {
    public enum TileType {
        Rock,
        Sky
    }

    public TileType type;

    public Tile(TileType t) {
        type = t;
    }

    public Fixture collision = null;

    public  static  boolean Blocks(TileType t){
        switch (t){

            case Rock:
                return  true;

            case Sky:
            default:
                return false;

        }

    }



}
